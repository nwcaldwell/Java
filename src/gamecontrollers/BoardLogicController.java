package gamecontrollers;

import gamecontrollers.checks.*;
import models.Pair;
import models.board.*;
import models.board.TileComponentContents.Palace;
import models.palacefestival.JavaPlayer;

import java.util.*;

public class BoardLogicController {
	private Board board;
	private int numTiles;
    private DevMoveController devMoveController;

	public BoardLogicController(Board board) {
		this.board = board;
		numTiles = getAllSpaces().size();
		this.devMoveController = new DevMoveController(this);
	}



	public Board getBoard() {
		return board;
	}
	private List<Space> getSortedListOfDevelopersInCity(Space palace)
	{
		List<Space> spacesWithDevs = new ArrayList<Space>();
		List<Space> citySpaces = getCitySpaces(palace);
		for(Space citySpace : citySpaces)
		{
			if(board.hasDeveloperOn(citySpace))
			{
				spacesWithDevs.add(citySpace);
			}
		}
		Collections.sort(spacesWithDevs, new HeightRankings());
		return spacesWithDevs;
	}


	//call with h == max height + 1
	void breakTie(List<Space> spacesWithDevs, List<List<JavaPlayer>> ranks, List<JavaPlayer> involved, int h, HashSet<JavaPlayer> ranked)
	{
		if(involved.isEmpty())
			return;
		if(h == 0) {
			LinkedList<JavaPlayer> players = new LinkedList<JavaPlayer>();
			for(JavaPlayer jp : involved)
				if(!ranked.contains(jp))
				{
					players.add(jp);
					ranked.add(jp);
				}
			if(!players.isEmpty())
				ranks.add(players);
			return;
		}
		List<List<JavaPlayer>> willBeStillInvolved = new ArrayList<List<JavaPlayer>> ();
		List<JavaPlayer>  willBeNoLongerInvolved = new ArrayList<JavaPlayer>();

		separateInvolved(spacesWithDevs, involved, h-1, willBeStillInvolved, willBeNoLongerInvolved);

		for(List<JavaPlayer> tie : willBeStillInvolved)
			breakTie(spacesWithDevs, ranks, tie, h, ranked);

		breakTie(spacesWithDevs, ranks, willBeNoLongerInvolved, h-1, ranked);
	}
	void separateInvolved(List<Space> spacesWithDevs, List<JavaPlayer> wasInvolved,
	                        int h, List<List<JavaPlayer>> willBeStillInvolved, List<JavaPlayer>  willBeNoLongerInvolved)
	{
		int firstIndexOfRank = spacesWithDevs.indexOf(h);
		if(firstIndexOfRank == -1)
			return;
		int lastIndexOfRank = spacesWithDevs.lastIndexOf(h);
		Space[] spacesAtThisRank = new Space[lastIndexOfRank - firstIndexOfRank + 1];
		for(int i = firstIndexOfRank; i <= lastIndexOfRank; i++)
			spacesAtThisRank[i - firstIndexOfRank] = spacesWithDevs.get(i);

		JavaPlayer currentPlayer = board.getDeveloperOn(spacesAtThisRank[0]).getPlayer();
		ArrayList<Pair<JavaPlayer, Integer>> peeps = new ArrayList<Pair<JavaPlayer, Integer>>();
		peeps.add(new Pair<JavaPlayer, Integer>(currentPlayer, 0)); //numdevelopers person JP has
		int peepsIndex = 0;

		for(int i = 0; i < spacesAtThisRank.length; i++)
		{
			if(board.getDeveloperOn(spacesAtThisRank[i]).getPlayer() == currentPlayer)
				peeps.set(peepsIndex, new Pair<JavaPlayer, Integer>(currentPlayer, peeps.get(peepsIndex).getValue() + 1));
			else
			{
				currentPlayer = board.getDeveloperOn(spacesAtThisRank[i]).getPlayer();
				peeps.add(new Pair<JavaPlayer, Integer>(currentPlayer, 1));
				peepsIndex++;
			}
		}
		Collections.sort(peeps, new PeepsComparator());

		for(JavaPlayer jp : wasInvolved)
			willBeNoLongerInvolved.add(jp);
		willBeNoLongerInvolved.remove(peeps.get(0).getKey());

		int numDevelopersLastGuyHad = peeps.get(0).getValue();
		willBeStillInvolved.add(new ArrayList<JavaPlayer>());
		int currIndex = 0;
		for(int i = 0; i < peeps.size(); i++)
		{
			if(!wasInvolved.contains(peeps.get(i).getKey())) //we don't care about him if he wasn't involved before
				continue;
			if(peeps.get(i).getValue() != numDevelopersLastGuyHad)
			{
				willBeStillInvolved.add(new ArrayList<JavaPlayer>());
				++currIndex;
				numDevelopersLastGuyHad = peeps.get(i).getValue(); //numDevelopers
				willBeNoLongerInvolved.remove(peeps.get(i).getKey());
			}
			willBeStillInvolved.get(currIndex).add(peeps.get(i).getKey()); //JP
		}
	}
	private class PeepsComparator implements Comparator<Pair<JavaPlayer, Integer>>
	{
		public int compare(Pair<JavaPlayer, Integer> p1, Pair<JavaPlayer, Integer> p2)
		{
			return p1.getValue().compareTo(p2.getValue()) * -1; //biggest pairs first
		}
	}
	/*
		NOTE: Must check for a null list upon execution of the following two methods.
	 */
	private List<List<JavaPlayer>> getDeveloperStandingsWithTieBreaker(Space palace)
	{
		List<Space> spacesWithDevs = getSortedListOfDevelopersInCity(palace);
		if(spacesWithDevs.isEmpty())
			return null;

		List<List<JavaPlayer>> ranks = new ArrayList<List<JavaPlayer>>();

		breakTie(spacesWithDevs, ranks, playersWithDevelopersInCity(palace), spacesWithDevs.get(0).getHeight(), new HashSet<JavaPlayer>());
		return ranks;
	}
	private List<List<JavaPlayer>> getDeveloperStandingsNoTieBreaker(Space palace)
	{
		List<Space> spacesWithDevs = getSortedListOfDevelopersInCity(palace);
		if(spacesWithDevs.isEmpty())
			return null;

		List<List<JavaPlayer>> ranks = new ArrayList<List<JavaPlayer>>();

		HashSet<JavaPlayer> playersConsidered = new HashSet<JavaPlayer>();
		int algHeight = spacesWithDevs.get(0).getHeight();
		int algRank = 0;
		for(int i = 0; i < spacesWithDevs.size(); i++)
		{
			JavaPlayer player = board.getDeveloperOn(spacesWithDevs.get(i)).getPlayer();
			if(!playersConsidered.contains(player))
			{
				if(spacesWithDevs.get(i).getHeight() != algHeight)
				{
					algHeight = spacesWithDevs.get(i).getHeight();
					++algRank;
				}
				playersConsidered.add(player);
				ranks.get(algRank).add(player);
			}
		}
		return ranks;
	}
	private class HeightRankings implements Comparator<Space> {
		@Override
		public int compare(Space a, Space b) {
			if(a.getHeight() < b.getHeight()) //backwards lexicographic so that highest dev comes first
				return 1;
			else if(a.getHeight() > b.getHeight())
				return -1;
			//heights tie
			return board.getDeveloperOn(a).getPlayer().getName().compareTo(board.getDeveloperOn(b).getPlayer().getName());
		}
	}
    //TODO WILL SECTION
    //this tells if the passed player has the absolute highest or highest by tiebreaker, for palace placement.
    //the given space should be the space of a palace tile component content
    public boolean holdsHighestDeveloper(Space palace, JavaPlayer player){
        List<List<JavaPlayer>> ranks = getDeveloperStandingsWithTieBreaker(palace);
	    if(ranks.isEmpty())
		    return false;
	    if(ranks.get(0).size() > 1)
		    return false;
	    if(ranks.get(0).get(0) != player)
		    return false;
	    return true;
    }
    //this tells if the passed player has one of the highest tying developers
    //the given space should be the space of a palace tile component content
    public boolean holdsHighestDeveloperOnLastTurn(Space palace, JavaPlayer player){
        List<List<JavaPlayer>> ranks = getDeveloperStandingsNoTieBreaker(palace);
	    if(ranks.isEmpty())
		    return false;
	    if(!ranks.get(0).contains(player))
		    return false;
	    return true;
    }
	public boolean holdsSecondHighestDeveloperOnLastTurn(Space palace, JavaPlayer player)
	{
		List<List<JavaPlayer>> ranks = getDeveloperStandingsNoTieBreaker(palace);
		if(ranks.size() < 2)
			return false;
		if(!ranks.get(1).contains(player))
			return false;
		return true;
	}
    //END TODO WILL SECTION
	
	public List<Space> getCitySpaces(Space palace) {
		/*
		List<Space> allSpaces = getAllSpaces();
		List<Space> citySpaces = new LinkedList<Space>();
		Iterator<Space> it = allSpaces.iterator();
		while(it.hasNext())
		{
			Space s = it.next();

			citySpaces.add(s);
		}
		*/
		throw new UnsupportedOperationException();
	}
	private List<JavaPlayer> playersWithDevelopersInCity(Space palace)
	{
		List<Space> citySpaces = getCitySpaces(palace);
		HashSet<JavaPlayer> visited = new HashSet<JavaPlayer>();
		List<JavaPlayer> answer = new ArrayList<JavaPlayer>();
		for(Space citySpace : citySpaces)
		{
			if(!board.hasDeveloperOn(citySpace))
				continue;
			JavaPlayer jp = board.getDeveloperOn(citySpace).getPlayer();
			if(!visited.contains(jp)){
				answer.add(jp);
				visited.add(jp);
			}
		}
		return answer;
	}
	public boolean playerHasDeveloperInCity(Space palace, JavaPlayer jp)
	{
		List<JavaPlayer> playersWithDevelopersInCity = playersWithDevelopersInCity(palace);
		return playersWithDevelopersInCity.contains(jp);
	}
	
	public List<Space> getPoolAndBeach(Space s, List<Space> pool, List<Space> beach) {
		return new ArrayList<Space>();
	}

	public int moveDeveloperWithinCentralJava(JavaPlayer p, Space origin, Space destination, List<Space> path)
	{
		return devMoveController.nodeScoreToActionPointConversion(devMoveController.findPathWithinCentralJava(p, origin, destination, path));
	}
	public int moveDeveloperOnBoard(JavaPlayer p, Space destination, List<Space> path) { // n
		return devMoveController.nodeScoreToActionPointConversion(devMoveController.findOnBoardPath(p, destination, path));
	}
	public int moveDeveloperOffBoard(JavaPlayer p, Space origin, List<Space> path) // n
	{
		return devMoveController.nodeScoreToActionPointConversion(devMoveController.findOffBoardPath(p, origin, path));
	}
	
	//The two methods below weren't in the design doc. 
	//They are used to collect all palace spaces. 
	public List<Space> getAllActivePalaceSpaces() {
		List<Space> spaces = getAllSpaces();
		int size = spaces.size();
		
		PalaceVisitor pv = new PalaceVisitor();
		
		for (int i = 0; i < size; i++) {
			spaces.get(i).getTileComponentContent().accept(pv);
		}
		
		List<Palace> activePalaces = pv.getActivePalaces();
		List<Space> activeSpaces = new ArrayList<Space>();
		
		for (int i = 0; i < spaces.size(); i++) {
			if (activePalaces.contains(spaces.get(i).getTileComponentContent())) {
				activeSpaces.add(spaces.get(i));
			}
		}
		
		return activeSpaces;
	}
	
	//Assuming the board is a connected graph...
	List<Space> getAllSpaces()
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
	public int getNumTiles()
	{
		return numTiles;
	}

	public boolean inCentralJava(Space space)
	{
		return space.isInCentralJava();
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
