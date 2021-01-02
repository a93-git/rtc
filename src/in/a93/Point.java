package in.a93;

public class Point extends Tuple {
	
	public Point() {
		super();
	}
	public Point(float x, float y, float z) {
		super(x, y, z, 1.0f);
	}
	
	public Point(float x, float y, float z, float w) {
		super(x, y, z, w);
	}
	
	public Point add(Point other) {
		return new Point(this.getX() + other.getX(), this.getY() + other.getY(), this.getZ() + other.getZ(), this.getW() + other.getW());
	}

	public Vector subtract(Point other) {
		return new Vector(this.getX() - other.getX(), this.getY() - other.getY(), this.getZ() - other.getZ(), this.getW() - other.getW());
	}
	
	@Override
	public Point negate() {
		return new Point(-this.getX(), -this.getY(), -this.getZ(), -this.getW());
	}
	
	@Override
	public Point scalarMultiply(float x) {
		return new Point(this.getX() * x, this.getY() * x, this.getZ() * x, this.getW() * x);
	}

	@Override
	public Point scalarDivide(float x) {
		return new Point(this.getX() / x, this.getY() / x, this.getZ() / x, this.getW() / x);
	}
	
}
