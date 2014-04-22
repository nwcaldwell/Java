package view.cgi;

import java.util.ArrayList;

import org.lwjgl.opengl.GL11;

public class Model3D {
	
	/**the faces that compose this model*/
	private ArrayList<Face3D> faces=new ArrayList<Face3D>();
	
	/**Stores 3D rotation.  Uses degrees, not radians.*/
	float pitch,yaw,roll;
	
	/**Stores the scale of the scene.*/
	private float scale=1;
	
	/**the position of this Model relative to the origin.*/
	Vector3D translation=new Vector3D(0, 0, 0);
	
	/**Creates a new Model3D with the given faces.*/
	public Model3D(ArrayList<Face3D> faces){
		this.faces=faces;
	}
	
	/**Creates a new Model3D with the composite faces of
	 * a set of provided models.*/
	public Model3D(Model3D... others){
		for (Model3D other:others){
			this.faces.addAll(other.faces);
		}
	}
	
	/**renders this model with local transformations.*/
	public void render(){
		GL11.glPushMatrix();
		GL11.glTranslatef(translation.x, translation.y, translation.z);
		GL11.glScalef(getScale(), getScale(), getScale());
		
		GL11.glRotatef(yaw, 0, 1, 0);
		GL11.glRotatef(pitch, 0, 0, 1);
		GL11.glRotatef(roll, 1, 0, 0);
		
		for (Face3D f:faces){
			f.render();
		}
		
		GL11.glPopMatrix();
	}
	
	public void setRotation(float pitch, float yaw, float roll){
		this.pitch=pitch;
		this.yaw=yaw;
		this.roll=roll;
	}
	
	public void setTranslation(Vector3D translation){
		this.translation=translation;
	}
	
	public float getPitch(){
		return pitch;
	}
	
	public float getYaw(){
		return yaw;
	}
	
	public float getRoll(){
		return roll;
	}
	
	public Vector3D getTranslation(){
		return translation;
	}
	
	/**creates a copy of this model*/
	public Model3D clone(){
		Model3D clone=new Model3D(faces);
		clone.setTranslation(translation);
		clone.setRotation(pitch, yaw, roll);
		clone.setScale(getScale());
		return clone;
	}
	
	/**overrides the normals of all faces and makes them 
	 * perpendicular to the face*/
	public void setFlat(){
		for (Face3D face:faces)
			face.generateDefaultNormals();
	}
	
	/**overrides the normals of all faces and makes them 
	 * the same as the vertices.*/
	public void setSphere(){
		for (Face3D face:faces)
			face.generateSphereNormals();
	}

	public float getScale() {
		return scale;
	}

	public void setScale(float scale) {
		this.scale = scale;
	}
}
