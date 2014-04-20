package view.cgi;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import org.lwjgl.opengl.GL11;

/**A factory class for loading models from files.
 * Currently only loads OBJ files.
 * 
 * This class is distinct from MediaController because of
 * its dependency on LWJGL, which is non-standard, and should
 * not be a dependency for implementations that may not have 
 * LWJGL.*/
public class ModelFactory {
	
	public static final String OBJ_VERTEX="v";
	public static final String OBJ_TEXTURE_COORD="vt";
	public static final String OBJ_SURFACE_NORMAL="vn";
	public static final String OBJ_FACE="f";
	
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
		
		//surface normals by vertex
		ArrayList<Vector3D> normals=new ArrayList<Vector3D>();
		
		String fileLine="#";
		while (scan.hasNext()){
			fileLine=scan.nextLine();
			
			//split the line with whitespaces
			String[] tokens = fileLine.split("\\s+");
			
			//skip empty strings
			if (tokens.length>0){
				if (tokens[0].equalsIgnoreCase(OBJ_VERTEX)){
					//it's a vertex
					float x=Float.parseFloat(tokens[1]);
					float y=Float.parseFloat(tokens[2]);
					float z=Float.parseFloat(tokens[3]);
					vertices.add(new Vector3D(x, y, z));
				}
				if (tokens[0].equalsIgnoreCase(OBJ_SURFACE_NORMAL)){
					//it's a vertex
					float x=Float.parseFloat(tokens[1]);
					float y=Float.parseFloat(tokens[2]);
					float z=Float.parseFloat(tokens[3]);
					normals.add(new Vector3D(x, y, z));
				}
				if (tokens[0].equalsIgnoreCase(OBJ_TEXTURE_COORD)){
					//it's a vertex
					float x=Float.parseFloat(tokens[1]);
					float y=Float.parseFloat(tokens[2]);
					//y values must flip
					coords.add(new Vector2D(x, 1-y));
				}
				if (tokens[0].equalsIgnoreCase(OBJ_FACE)){
					//it's a vertex
					Vector3D[] fv=new Vector3D[tokens.length-1];
					Vector2D[] fc=new Vector2D[tokens.length-1];
					Vector3D[] fn=new Vector3D[tokens.length-1];
					
					boolean fullTextureData=true;
					boolean fullNormalData=true;
					boolean tristrip=false;
					for (int i=1;i<tokens.length;i++){
						String[] indicies=tokens[i].split("/");
						fv[tokens.length-(i+1)]=vertices.get(Integer.parseInt(indicies[0])-1);
						
						if (indicies[1].length()>0){
							fc[tokens.length-(i+1)]=coords.get(Integer.parseInt(indicies[1])-1);
						}else{
							fullTextureData=false;
						}
						
						if (indicies.length>2&&indicies[2].length()>0){
							fn[tokens.length-(i+1)]=normals.get(Integer.parseInt(indicies[2])-1);
						}else{
							fullNormalData=false;
						}
						if (i==5){
							tristrip=true;
						}
					}
					int rendType=GL11.GL_TRIANGLES;
					if (fv.length==4){
						rendType=GL11.GL_QUADS;
					}
					try {
						//simple triangulatioin for concave shapes.
						if (rendType!=GL11.GL_QUADS){
							for (int i=2;i<fv.length;i++){
								Vector3D[] triverts=new Vector3D[3];
								triverts[0]=fv[0];
								triverts[1]=fv[i-1];
								triverts[2]=fv[i];
								if (fullTextureData){
									Vector2D[] tritex=new Vector2D[3];
									tritex[0]=fc[0];
									tritex[1]=fc[i-1];
									tritex[2]=fc[i];
									faces.add(new TexturedFace3D(texture, triverts, tritex, rendType));
								}else{
									faces.add(new Face3D(triverts, rendType));
								}
								if (fullNormalData){
									Vector3D[] trinorms=new Vector3D[3];
									trinorms[0]=fv[0];
									trinorms[1]=fv[i-1];
									trinorms[2]=fv[i];
									faces.get(faces.size()-1).setNormals(trinorms);
								}
							}
						} else {
							for (int i=2;i<fv.length;i++){
								if (fullTextureData){
									faces.add(new TexturedFace3D(texture, fv, fc, rendType));
								}else{
									faces.add(new Face3D(fv, rendType));
								}
								if (fullNormalData){
									faces.get(faces.size()-1).setNormals(fn);
								}
							}
						}
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
}
