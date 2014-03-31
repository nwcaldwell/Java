package view.cgi;

import static org.lwjgl.opengl.GL11.*;

/**Represents a face in 3D space
 * with texture information.*/
public class Face3D {

	Vector3D[] vertices;
	Vector2D[] texCoords;
	int texture=TextureFactory.MISSING_TEXTURE;
	int renderType=GL_TRIANGLES;
	
	/**vertices for making a simple square
	 * makes a square at 0,0 with dimensions 1,1*/
	protected static final Vector3D[] squareVerts={
			new Vector3D(0, 1, 0),
			new Vector3D(1, 1, 0),
			new Vector3D(1, 0, 0),
			new Vector3D(0, 0, 0),
	};
	/**texture coordinates matching a square texture
	 * to squareVerts.*/
	protected static final Vector2D[] squareFrontCoords={
		new Vector2D(0,0),
		new Vector2D(1,0),
		new Vector2D(1,1),
		new Vector2D(0,1)
	};
	
	/**creates a new renderable face with the given texture, local cooridinates,
	 * texture coordinates, and RenderType (e.g. GL11.GL_TRIANGLES or GL11.GL_QUADS.)
	 * @throws Exception */
	public Face3D(int texture, Vector3D[] vertices, Vector2D[] textureCoords, int renderType) throws Exception{
		if (textureCoords.length!=vertices.length){
			throw new Exception("Face3D requires the same number of vectors and texture coordinates.");
		}
		this.texture=texture;
		this.renderType=renderType;
		this.vertices=vertices;
		this.texCoords=textureCoords;
	}
	
	/**makes a square face with the given texture, coordinates, and dimensions.*/
	public static Face3D MakeQuad(int texture, int x, int y, int width,int height){
		Face3D result=null;
		Vector3D[] verts=squareVerts.clone();
		for(int i = 0; i<verts.length;i++){
			verts[i]=verts[i].scale(width, height, 1).translate(x, y, 0);
		}
		try{
			result=new Face3D(texture, verts, squareFrontCoords, GL_QUADS);
		} catch (Exception e){
			System.err.println("If this happens, then the squareVerts and squareFrontCoords arrays");
			System.err.println("built into Face3D are wrong.  Complain to Christopher.");
			e.printStackTrace();
		}
		return result;
	}
	
	public void render(){
		glBindTexture(GL_TEXTURE_2D, texture);
		glBegin(renderType);
		for (int i=0;i<vertices.length;i++){
			glTexCoord2f(texCoords[i].x, texCoords[i].y);
			glVertex3f(vertices[i].x, vertices[i].y, vertices[i].z);
		}
		glEnd();
	}
}
