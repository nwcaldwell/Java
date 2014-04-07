package view.cgi;

/**A Vector in 3D space*/
public class Vector3D {

	public final float x,y,z;
	
	public Vector3D(float x, float y, float z){
		this.x=x;
		this.y=y;
		this.z=z;
	}
	
	/**returns a new vector representing 
	 * this vector with its
	 * cooridnates negated*/
	public Vector3D negate(){
		return new Vector3D(-x,-y,-z);
	}
	
	/**returns a new vector representing 
	 * this vector translated by
	 * the given vector*/
	public Vector3D translate(Vector3D other){
		return translate(other.x, other.y, other.z);
	}

	/**returns a new vector representing 
	 * this vector translated by
	 * the given values*/
	public Vector3D translate(float x, float y, float z){
		return new Vector3D(this.x+x, this.y+y, this.z+z);
	}
	
	/**returns the square of the length of this vector
	 * faster than length()*/
	public float lengthSquared(){
		return x*x+y*y+z*z;
	}
	
	/**returns the length of this vector.
	 * slower than lengthSquared()*/
	public float length(){
		return (float) Math.sqrt(lengthSquared());
	}
	
	/**returns a new vector representing
	 * this vector scaled by the given x and y proportions.*/
	public Vector3D scale(float scaleX, float scaleY, float scaleZ){
		return new Vector3D(x*scaleX,y*scaleY, z*scaleZ);
	}
}
