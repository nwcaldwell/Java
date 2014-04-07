package view.cgi;

import static org.lwjgl.opengl.GL11.*;

/**Represents a face in 3D space*/
public class Face3D {

	Vector3D[] vertices;
	Vector3D[] normals;
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
		generateDefaultNormals();
	}
	
	protected void generateDefaultNormals(){
		int faceVerts=0;
		if (renderType==GL_TRIANGLES){
			faceVerts=3;
		}
		if (renderType==GL_QUADS){
			faceVerts=4;
		}
		if (faceVerts==0){
			generateSphereNormals();
		}else{
			normals=new Vector3D[vertices.length];
			for (int i=0;i<=vertices.length-faceVerts;i+=faceVerts){
				Vector3D normal=
						vertices[0].negate().translate(vertices[1]).cross(
						vertices[0].negate().translate(vertices[2]));
				System.out.println("first vert"+faceVerts+":"+vertices[0].negate());//.translate(vertices[1]));
				System.out.println("second vert"+faceVerts+":"+vertices[1].negate());
				System.out.println("third vert"+faceVerts+":"+vertices[2].negate());
				System.out.println("edge1: "+vertices[0].negate().translate(vertices[1]));
				System.out.println("edge2: "+vertices[0].negate().translate(vertices[2]));
				System.out.println(normal.toString());
				for (int j=0;j<faceVerts;j++){
					normals[i+j]=normal;
				}
			}
		}
		if (faceVerts==4){
			for (Vector3D v:normals){
			}
		}
	}
	
	public void generateSphereNormals(){
		normals=new Vector3D[vertices.length];
		for (int i=0;i<vertices.length;i++){
			normals[i]=vertices[i];
		}
	}
	
	/**renders this texture*/
	public void render(){
		glBegin(renderType);
		for (int i=0;i<vertices.length;i++){
			glNormal3f(normals[i].x, normals[i].y, normals[i].z);
			glVertex3f(vertices[i].x, vertices[i].y, vertices[i].z);
		}
		glEnd();
	}
}
