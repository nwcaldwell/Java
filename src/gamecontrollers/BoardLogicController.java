package gamecontrollers;

import gamecontrollers.checks.*;
import models.board.*;
import models.palacefestival.JavaPlayer;

import java.util.*;

public class BoardLogicController {
	private Board board;
	private int numTiles;
    private DevMoveController devMoveController;

	public BoardLogicController(Board board) {
		this.board = board;
		numTiles = getAllSpaces().size();
	}

	public Board getBoard() {
		return board;
	}
	
	public List<Space> getCitySpaces(Space s) {
		return new ArrayList<Space>();
	}
	
	public List<Space> getPoolAndBeach(Space s, List<Space> pool, List<Space> beach) {
		return new ArrayList<Space>();
	}
	
	public boolean isOutOfBounds(Space s) {
		return false;
	}
	private List<Space> getWilderness()
	{
		List<Space> allSpaces = getAllSpaces();
		List<Space> wilderness = new LinkedList<Space>();
		Iterator<Space> it = allSpaces.iterator();
		while(it.hasNext())
		{
			Space space = it.next();
			if(!inCentralJava(space))
				wilderness.add(space);
		}
		return wilderness;
	}
	private List<Space> getInternalBorderSpaces()
	{
		List<Space> wilderness = getWilderness();
		Iterator<Space> it = wilderness.iterator();
		HashSet<Space> visitedInternalBorderSpaces = new HashSet<Space>();
		List<Space> internals = new LinkedList<Space>();
		while(it.hasNext())
		{
			Space wild = it.next();
			Direction dir = wild.getDirections();
			Iterator<Direction> dirs = dir.iterator();
			while(dirs.hasNext())
			{
				Direction d = dirs.next();
				if(!wild.hasAdjacentSpace(d))
					continue;
				Space adj = wild.getAdjacentSpace(d);
				if(inCentralJava(adj) && !visitedInternalBorderSpaces.contains(adj))
				{
					visitedInternalBorderSpaces.add(adj);
					internals.add(adj);
				}
			}
		}
        return internals;
	}

	//Assuming the board is a connected graph...
	private List<Space> getAllSpaces()
	{
		Space root = board.getRoot();
		List<Space> allSpaces = new LinkedList<Space>();
		LinkedList<Space> q = new LinkedList<Space>();
		HashSet<Space> visited = new HashSet<Space>();
		q.offer(root);
		visited.add(root);

		while(!q.isEmpty())
		{
			Space space = q.poll();
			allSpaces.add(space);
			Iterator<Direction> it = space.getDirections().iterator();
			while(it.hasNext())
			{
				Direction dir = it.next();
				if(!space.hasAdjacentSpace(dir))
					continue;
				Space neighbor = space.getAdjacentSpace(dir);
				if(visited.contains(neighbor))
					continue;
				visited.add(neighbor);
				q.offer(neighbor);
			}
		}
		return allSpaces;
	}
	static final int IMPOSSIBLE = -1;
	private int nodeScoreToActionPointConversion(int nodeScore)
	{
		if(nodeScore == IMPOSSIBLE)
			return nodeScore;
		return nodeScore / numTiles;
	}

	private int pathLogic(JavaPlayer p, Space origin, Space destination, List<Space> path)
	{

		//edge case
		if(origin.equals(destination))
		{
			path.add(destination);
			return 0;
		}
		//terminal checks
		if(!validSource(origin) || !validDest(destination))
			return IMPOSSIBLE;
		//Dijkstra's
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		pq.offer(new Node(0, origin));

		HashMap<Space, Integer> minCostToGetHere = new HashMap<Space, Integer>();
		Iterator<Space> it = getAllSpaces().iterator();
		while(it.hasNext())
			minCostToGetHere.put(it.next(), Integer.MAX_VALUE);

		HashMap<Space, Space> cameFrom = new HashMap<Space, Space>();

		while(!pq.isEmpty())
		{
			Node n = pq.poll();
			Space pos = n.position;
			if(pos.equals(destination))
			{
				if(n.nodeScore >= numTiles * numTiles) //impossible
					return IMPOSSIBLE;
				Iterator<Space> pathTakenIterator = pathTaken(cameFrom, destination).iterator();
				while(pathTakenIterator.hasNext())
					path.add(pathTakenIterator.next());

				return n.nodeScore;
			}

			Iterator<Direction> directionIterator = pos.getDirections().iterator();
			while(it.hasNext())
			{
				Direction dir = directionIterator.next();
				if(!pos.hasAdjacentSpace(dir))
					continue;
				Space buddy = pos.getAdjacentSpace(dir);
				
				int potentialWeight = n.nodeScore;
				++potentialWeight; //to dissuade longer paths than necessary

				if(!spaceIsExplorable(buddy, p))  //to dissuade breaking the rules
					potentialWeight += (numTiles * numTiles);
				else if(typeSwitch(pos, buddy)) //to dissuade switching between tile types
					potentialWeight += numTiles;

				if(potentialWeight < minCostToGetHere.get(buddy))
				{
					minCostToGetHere.put(buddy, potentialWeight);
					cameFrom.put(buddy, pos);
					pq.offer(new Node(potentialWeight, buddy));
				}
			}
		}

		throw new UnsupportedOperationException();
	}
	//TODO: double check these checks
	private boolean typeSwitch(Space a, Space b)
	{
		if(new TypeSwitching().check(a.getTileComponentContent(), b.getTileComponentContent()))
			return true;
		return false;
	}
	private boolean validSource(Space space)
	{
		if(new NotWalkableCheck().check(space))
			return false;
		if(new UnfertilizedLand().check(space))
			return false;
		return true;
	}
	private boolean validDest(Space space)
	{
		if(new DeveloperLivesHere(this).check(space))
			return false;
		return true;
	}
	private boolean spaceIsExplorable(Space space, JavaPlayer p)
	{
		if(new NotWalkableCheck().check(space))
			return false;
		if(new DevOfDifferentPlayerThere(this).check(space, p))
			return false;
		if(new UnfertilizedLand().check(space))
			return false;
		if(!inCentralJava(space))
			return false;
		return true;
	}
	private boolean inCentralJava(Space space)
	{
		return space.isInCentralJava();
	}
	private int minWildernessTransitionCost (Space borderSpace)
	{
		Iterator<Direction> directionIterator = borderSpace.getDirections().iterator();
		int minCost = Integer.MAX_VALUE;
		while(directionIterator.hasNext())
		{
			Direction dir = directionIterator.next();
			if(!borderSpace.hasAdjacentSpace(dir))
				continue;
			Space adj = borderSpace.getAdjacentSpace(dir);
			if(inCentralJava(adj))
				continue;
			//Rule check
			int cost = 10;
			if(cost < minCost)
				minCost = cost;
		}

		if(minCost == Integer.MAX_VALUE)
			throw new IllegalStateException("Mookie Bear has eaten your developer movement logic");
		return minCost;
	}
	private List<Space> pathTaken(HashMap<Space, Space> cameFrom, Space destination)
	{
		Space currSpace = destination;
		LinkedList<Space> path = new LinkedList<Space>();
		while(cameFrom.containsKey(currSpace))
		{
			path.push(currSpace);
			currSpace = cameFrom.get(currSpace);
		}
		path.push(currSpace); //the source won't have a key but it's still part of the path
		return path;
	}

	static class Node implements Comparator<Node>
	{
		int nodeScore;
		Space position;

		Node(int ns, Space p)
		{
			nodeScore = ns;
			position = p;
		}
		@Override
		public int compare(Node o1, Node o2) {
			if(o1.nodeScore < o2.nodeScore)
				return -1;
			if(o1.nodeScore > o2.nodeScore)
				return 1;
			return 0;
		}
	}

	public int findPathWithinCentralJava(JavaPlayer p, Space origin, Space destination, List<Space> path) { // 3 * n
		LinkedList<Space> simplePath = new LinkedList<Space>();
		int simpleCost = pathLogic(p, origin, destination, simplePath);
		LinkedList<Space> offPath = new LinkedList<Space>();
		LinkedList<Space> onPath = new LinkedList<Space>();
		int offCost = findOffBoardPath(p, origin, offPath);
		int onCost = findOnBoardPath(p, destination, onPath);

		if(simpleCost == IMPOSSIBLE && (offCost == IMPOSSIBLE || onCost == IMPOSSIBLE))
			return IMPOSSIBLE;

		boolean simple;
		if(simpleCost == IMPOSSIBLE)
			simple = false;
		else if(offCost == IMPOSSIBLE || onCost == IMPOSSIBLE)
			simple = true;
		else if(simpleCost <= offCost + onCost)
			simple = true;
		else //off-road is cheaper
			simple = false;

		if(simple)
		{
			Iterator<Space> it = simplePath.iterator();
			while(it.hasNext())
				path.add(it.next());
			return simpleCost;
		}
		else
		{
			Iterator<Space> itOff = offPath.iterator();
			Iterator<Space> itOn = onPath.iterator();
			while(itOff.hasNext())
				path.add(itOff.next());
			while(itOn.hasNext())
				path.add(itOn.next());
			return offCost + onCost;
		}
	}
	public int findOffBoardPath(JavaPlayer p, Space origin, List<Space> path) { // n
		Iterator<Space> borderSpacesIterator = getInternalBorderSpaces().iterator();
		int minCost = Integer.MAX_VALUE;
		LinkedList<Space> bestPath = new LinkedList<Space>();
		while(borderSpacesIterator.hasNext())
		{
			Space bs = borderSpacesIterator.next();
			LinkedList<Space> hisPath = new LinkedList<Space>();
			int cost = findPathWithinCentralJava(p, origin, bs, hisPath);
			if(cost == IMPOSSIBLE)
				continue;
			cost += (minWildernessTransitionCost(bs) * numTiles);
			if(cost < minCost)
			{
				minCost = cost;
				bestPath = hisPath;
			}
		}
		if(minCost == Integer.MAX_VALUE)
			return IMPOSSIBLE;
		Iterator<Space> it = bestPath.iterator();
		while(it.hasNext())
			path.add(it.next());
		return minCost;
	}
	public int findOnBoardPath(JavaPlayer p, Space destination, List<Space> path) { // n
		Iterator<Space> borderSpacesIterator = getInternalBorderSpaces().iterator();
		int minCost = Integer.MAX_VALUE;
		LinkedList<Space> bestPath = new LinkedList<Space>();
		while(borderSpacesIterator.hasNext())
		{
			Space bs = borderSpacesIterator.next();
			LinkedList<Space> hisPath = new LinkedList<Space>();
			int cost = findPathWithinCentralJava(p, bs, destination, hisPath);
			if(cost == IMPOSSIBLE)
				continue;
			cost += (minWildernessTransitionCost(bs) * numTiles);
			if(cost < minCost)
			{
				minCost = cost;
				bestPath = hisPath;
			}
		}
		if(minCost == Integer.MAX_VALUE)
			return IMPOSSIBLE;
		Iterator<Space> it = bestPath.iterator();
		while(it.hasNext())
			path.add(it.next());
		return minCost;
	}
	public int moveDeveloperWithinCentralJava(JavaPlayer p, Space origin, Space destination, List<Space> path)
	{
		return nodeScoreToActionPointConversion(findPathWithinCentralJava(p, origin, destination, path));
	}
	public int moveDeveloperOnBoard(JavaPlayer p, Space destination, List<Space> path) { // n
		return nodeScoreToActionPointConversion(findOnBoardPath(p, destination, path));
	}
	public int moveDeveloperOffBoard(JavaPlayer p, Space origin, List<Space> path) // n
	{
		return nodeScoreToActionPointConversion(findOnBoardPath(p, origin, path));
	}
	
	public boolean validPlacement(TileComponent t, Space s) {
		return false;
	}
	
	public List<Space> getAllPlacedPalaces() {
		return null;
	}
	
	public Developer getNextDeveloper(Developer d) {
        return board.getNextDeveloper(d);
	}
	
	public boolean hasDeveloper(Space s, Developer d) {
		return false;
	}

    public boolean hasDeveloperOn(Space s){
        return board.hasDeveloperOn(s);
    }

    public Developer getDeveloperOn(Space space){
        Developer dev = new Developer();
        ArrayList<Developer> devs = board.getDevelopers();

        for(Developer temp : devs){
            if(temp.getSpace() == space){
                dev = temp;
            }
        }
        return dev;
    }

}
