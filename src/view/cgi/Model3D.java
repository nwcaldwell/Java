package view.cgi;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import org.lwjgl.opengl.GL11;

public class Model3D {

	public static final String OBJ_VERTEX="v";
	public static final String OBJ_TEXTURE_COORD="vt";
	public static final String OBJ_FACE="f";
	
	private ArrayList<Face3D> faces=new ArrayList<Face3D>();
	
	public Model3D(ArrayList<Face3D> faces){
		this.faces=faces;
	}
	
	/**makes a model from an obj file with a SINGLE OBJECT
	 * and a texture reference.*/
	public static Model3D makeFromObj(File model){
		Scanner scan = null;
		try {
			scan = new Scanner(model);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}

		//list of faces for this model
		ArrayList<Face3D> faces=new ArrayList<Face3D>();

		//vertices
		ArrayList<Vector3D> vertices=new ArrayList<Vector3D>();
		
		String fileLine="#";
		while (scan.hasNext()){
			fileLine=scan.nextLine();
			
			//split the line with whitespaces
			String[] tokens = fileLine.split("\\s+");
			
			//skip empty strings and comments
			if (tokens.length>0&&tokens[0].length()>0&&tokens[0].charAt(0)!='#'){
				if (tokens[0].equalsIgnoreCase(OBJ_VERTEX)){
					//it's a vertex
					float x=Float.parseFloat(tokens[1]);
					float y=Float.parseFloat(tokens[2]);
					float z=Float.parseFloat(tokens[3]);
					vertices.add(new Vector3D(x, y, z));
				}
				if (tokens[0].equalsIgnoreCase(OBJ_FACE)){
					//it's a vertex
					Vector3D[] fv=new Vector3D[tokens.length-1];
					for (int i=1;i<tokens.length;i++){
						String[] indicies=tokens[i].split("/");
						fv[tokens.length-(i+1)]=vertices.get(Integer.parseInt(indicies[0])-1);
					}
					int rendType=GL11.GL_TRIANGLES;
					if (fv.length==4){
						rendType=GL11.GL_QUADS;
					}
					try {
						faces.add(new Face3D(fv, rendType));
					} catch (Exception e) {
						System.err.println("OBJ file parsing: impossible error: complain to Christopher");
						e.printStackTrace();
					}
				}
			}
		}
		scan.close();
		return new Model3D(faces);
	}
	
	/**makes a model from an obj file with a SINGLE OBJECT
	 * and a texture reference.*/
	public static Model3D makeFromObj(File model, int texture){
		Scanner scan = null;
		try {
			scan = new Scanner(model);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}

		//list of faces for this model
		ArrayList<Face3D> faces=new ArrayList<Face3D>();

		//vertices
		ArrayList<Vector3D> vertices=new ArrayList<Vector3D>();
		
		//texture coordinates
		ArrayList<Vector2D> coords=new ArrayList<Vector2D>();
		
		String fileLine="#";
		while (scan.hasNext()){
			fileLine=scan.nextLine();
			
			//split the line with whitespaces
			String[] tokens = fileLine.split("\\s+");
			
			//skip empty strings and comments
			if (tokens.length>0&&tokens[0].charAt(0)!='#'){
				if (tokens[0].equalsIgnoreCase(OBJ_VERTEX)){
					//it's a vertex
					float x=Float.parseFloat(tokens[1]);
					float y=Float.parseFloat(tokens[2]);
					float z=Float.parseFloat(tokens[3]);
					vertices.add(new Vector3D(x, y, z));
				}
				if (tokens[0].equalsIgnoreCase(OBJ_TEXTURE_COORD)){
					//it's a vertex
					float x=Float.parseFloat(tokens[1]);
					float y=Float.parseFloat(tokens[2]);
					coords.add(new Vector2D(x, y));
				}
				if (tokens[0].equalsIgnoreCase(OBJ_FACE)){
					//it's a vertex
					Vector3D[] fv=new Vector3D[tokens.length-1];
					Vector2D[] fc=new Vector2D[tokens.length-1];
					for (int i=1;i<tokens.length;i++){
						String[] indicies=tokens[i].split("/");
						fv[tokens.length-(i+1)]=vertices.get(Integer.parseInt(indicies[0])-1);
						fc[tokens.length-(i+1)]=coords.get(Integer.parseInt(indicies[1])-1);
					}
					int rendType=GL11.GL_TRIANGLES;
					if (fv.length==4){
						rendType=GL11.GL_QUADS;
					}
					try {
						faces.add(new TexturedFace3D(texture, fv, fc, rendType));
					} catch (Exception e) {
						System.err.println("OBJ file parsing: impossible error: complain to Christopher");
						e.printStackTrace();
					}
				}
			}
		}
		
		scan.close();
		return new Model3D(faces);
	}
	
	public void render(){
		for (Face3D f:faces){
			f.render();
		}
	}
}
