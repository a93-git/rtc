package in.a93;

public class Vector extends Tuple {
	
	public Vector() {
		super();
	}
	public Vector(float x, float y, float z) {
		super(x, y, z, 0.0f);
	}

	public static Vector normalize(Vector t) {
		float m = t.magnitude();
		return new Vector(t.getX() / m, t.getY() / m, t.getZ() / m, t.getW() / m);
	}
	
	public static Vector cross(Vector a, Vector b) {
		return new Vector(
				a.getY() * b.getZ() - a.getZ() * b.getY(), 
				a.getZ() * b.getX() - a.getX() * b.getZ(), 
				a.getX() * b.getY() - a.getY() * b.getX()
				);
	}
	
	@Override
	public float magnitude() {
		return (float) Math.sqrt(Math.pow(this.getX(), 2) + Math.pow(this.getY(), 2) + Math.pow(this.getZ(), 2) + Math.pow(this.getW(), 2));
	}
	
	public Vector(float x, float y, float z, float w) {
		super(x, y, z, w);
	}
	
	public Vector add(Vector other) {
		return new Vector(this.getX() + other.getX(), this.getY() + other.getY(), this.getZ() + other.getZ(), this.getW() + other.getW());
	}

	public Vector subtract(Vector other) {
		return new Vector(this.getX() - other.getX(), this.getY() - other.getY(), this.getZ() - other.getZ(), this.getW() - other.getW());
	}
	
	@Override
	public Vector negate() {
		return new Vector(-this.getX(), -this.getY(), -this.getZ(), -this.getW());
	}
	
	@Override
	public Vector scalarMultiply(float x) {
		return new Vector(this.getX() * x, this.getY() * x, this.getZ() * x, this.getW() * x);
	}

	@Override
	public Vector scalarDivide(float x) {
		return new Vector(this.getX() / x, this.getY() / x, this.getZ() / x, this.getW() / x);
	}
}
