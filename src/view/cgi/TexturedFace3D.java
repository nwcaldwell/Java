package view.cgi;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glVertex3f;

/**Represents a face in 3D space
 * with texture information.*/
public class TexturedFace3D extends Face3D{

	Vector2D[] texCoords;
	int texture=TextureFactory.MISSING_TEXTURE;
	
	/**creates a new renderable face with the given texture, local cooridinates,
	 * texture coordinates, and RenderType (e.g. GL11.GL_TRIANGLES or GL11.GL_QUADS.)
	 * @throws Exception */
	public TexturedFace3D(int texture, Vector3D[] vertices, Vector2D[] textureCoords, int renderType) throws Exception{
		super(vertices, renderType);
		if (textureCoords.length!=vertices.length){
			throw new Exception("Face3D requires the same number of vectors and texture coordinates.");
		}
		this.texture=texture;
		this.texCoords=textureCoords;
	}
	
	/**makes a square face with the given texture, coordinates, and dimensions.*/
	public static TexturedFace3D MakeQuad(int texture, float f, float g, float width,float height){
		TexturedFace3D result=null;
		Vector3D[] verts=squareVerts.clone();
		for(int i = 0; i<verts.length;i++){
			verts[i]=verts[i].scale(width, height, 1).translate(f, g, 0);
		}
		try{
			result=new TexturedFace3D(texture, verts, squareFrontCoords, GL_QUADS);
		} catch (Exception e){
			System.err.println("If this happens, then the squareVerts and squareFrontCoords arrays");
			System.err.println("built into Face3D are wrong.  Complain to Christopher.");
			e.printStackTrace();
		}
		return result;
	}
	
	@Override
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
