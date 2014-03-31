package view.cgi;

/**an immutable vector in two dimensions*/
public class Vector2D {
	
	public final float x,y;
	
	public Vector2D(float x, float y){
		this.x=x;
		this.y=y;
	}
	
	/**returns a new vector representing 
	 * this vector with its
	 * cooridnates negated*/
	public Vector2D negate(){
		return new Vector2D(-x,-y);
	}
	
	/**returns a new vector representing 
	 * this vector translated by
	 * the given vector*/
	public Vector2D translate(Vector2D other){
		return translate(other.x, other.y);
	}

	/**returns a new vector representing 
	 * this vector translated by
	 * the given values*/
	public Vector2D translate(float x, float y){
		return new Vector2D(this.x+x, this.y+y);
	}
	
	/**returns the square of the length of this vector
	 * faster than length()*/
	public float lengthSquared(){
		return x*x+y*y;
	}
	
	/**returns the length of this vector.
	 * slower than lengthSquared()*/
	public float length(){
		return (float) Math.sqrt(lengthSquared());
	}
	
	/**returns a new vector representing
	 * this vector scaled by the given x and y proportions.*/
	public Vector2D scale(float scaleX, float scaleY){
		return new Vector2D(x*scaleX,y*scaleY);
	}
}
