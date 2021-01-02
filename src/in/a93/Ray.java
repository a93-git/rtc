package in.a93;

public class Ray {
	private Point origin;
	private Vector direction;
	
	public Ray(Point origin, Vector direction) {
		this.origin = origin;
		this.direction = direction;
	}
	
	public Point getOrigin() {
		return this.origin;
	}
	
	public Vector getDirection() {
		return this.direction;
	}
	
	public static Point getPosition(Ray ray, float t) {
		float x = ray.getOrigin().getX() + ray.getDirection().getX() * t;
		float y = ray.getOrigin().getY() + ray.getDirection().getY() * t;
		float z = ray.getOrigin().getZ() + ray.getDirection().getZ() * t;
		return new Point(x, y, z);
	}
	
	public static Ray transformRay(Ray ray, Matrix matrix) {
		Ray result = null;
		
		Point p = Matrix.matrix2Point(Matrix.multiply(matrix, Matrix.tuple2Matrix(ray.getOrigin())));
		Vector v = Matrix.matrix2Vector(Matrix.multiply(matrix, Matrix.tuple2Matrix(ray.getDirection())));
		
		result = new Ray(p, v);
		
		return result;
	}
	
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		
		s.append("Origin: " + this.origin);
		s.append("\nDirection: " + this.direction);
		
		return s.toString();
	}
}
