package in.a93;

import java.lang.Math;
import java.util.Comparator;

@SuppressWarnings(value = { "unused" }) 
public class Tuple {
	private float x;
	private float y;
	private float z;
	private float w;
	public static final float DELTA = 0.00001f;
	
	// return a point at origin
	public Tuple() {
		this.x = 0;
		this.y = 0;
		this.z = 0;
		this.w = 1;
	}
	public Tuple(float x, float y, float z, float w) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		
		Tuple other = (Tuple) obj;
		return Math.abs(this.x - other.x) < Tuple.DELTA
				&& Math.abs(this.y - other.y) < Tuple.DELTA 
				&& Math.abs(this.z - other.z) < Tuple.DELTA
				&& Math.abs(this.w - other.w) < Tuple.DELTA;
	}

	@Override
	public String toString() {
		StringBuilder retval = new StringBuilder("(");
		retval.append(this.x + ", ");
		retval.append(this.y + ", ");
		retval.append(this.z + ", ");
		retval.append(this.w);
		retval.append(")");
		
		return retval.toString();
	}
	
	public Tuple add(Tuple other) {
		return new Tuple(this.x + other.x, this.y + other.y, this.z + other.z, this.w + other.w);
	}
	
	public Tuple subtract(Tuple other) {
		return new Tuple(this.x - other.x, this.y - other.y, this.z - other.z, this.w - other.w);
	}
	
	public Tuple negate() {
		return new Tuple(-this.x, -this.y, -this.z, -this.w);
	}
	
	public Tuple scalarMultiply(float x) {
		return new Tuple(this.x * x, this.y * x, this.z * x, this.w * x);
	}

	public Tuple scalarDivide(float x) {
		return new Tuple(this.x / x, this.y / x, this.z / x, this.w / x);
	}
	
	public float magnitude() {
		return (float) Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2) + Math.pow(this.z, 2) + Math.pow(this.w, 2));
	}
	
	public static float dot(Tuple a, Tuple b) {
		return (a.x * b.x) + (a.y * b.y) + (a.z * b.z) + (a.w * b.w);
	}
	
	public static Vector reflect(Vector in, Vector normal) {
		return in.subtract(normal.scalarMultiply(2 * Tuple.dot(in, normal)));
	}
	
	public float getX() {
		return this.x;
	}
	
	public float getY() {
		return this.y;
	}
	
	public float getZ() {
		return this.z;
	}
	
	public float getW() {
		return this.w;
	}

	public void setX(float x) {
		this.x = x;
	}
	
	public void setY(float y) {
		this.y = y;
	}
	
	public void setZ(float z) {
		this.z = z;
	}
	
	public void setW(float w) {
		this.w = w;
	}

}
