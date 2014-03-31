package view.cgi;

import static org.lwjgl.opengl.GL11.*;

/**Represents a face in 3D space*/
public class Face3D {

	Vector3D[] vertices;
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
	public Face3D(Vector3D[] vertices, int renderType) throws Exception{
		this.renderType=renderType;
		this.vertices=vertices;
	}
	
	public void render(){
		glBegin(renderType);
		for (int i=0;i<vertices.length;i++){
			glVertex3f(vertices[i].x, vertices[i].y, vertices[i].z);
		}
		glEnd();
	}
}
