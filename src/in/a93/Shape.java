package in.a93;

import in.a93.Exceptions.MatrixNotInvertibleException;

public class Shape {
	private Matrix transform;
	private Material material;
	
	public Shape() {
		this.transform = Matrix.getIdentityMatrix(4, 4);
		this.material = new Material();
	}
	
	public static Intersection[] intersect(Shape shape, Ray originalRay) {
		Intersection[] result = null;
		
		Ray ray;
		try {
			ray = Ray.transformRay(originalRay, Matrix.getInverseMatrix(shape.getTransform()));
		} catch (MatrixNotInvertibleException e) {
			e.printStackTrace();
			return result;
		}
		
		Vector shapeToRay = ray.getOrigin().subtract(new Point(0, 0, 0));
		float a = Tuple.dot(ray.getDirection(), ray.getDirection());
		float b = 2 * Tuple.dot(ray.getDirection(), shapeToRay);
		float c = Tuple.dot(shapeToRay, shapeToRay) - 1;
		
		float discriminant = (float) Math.sqrt(Math.pow(b, 2) - (4 * a * c));
		
		if (discriminant >= 0) {
			result = new Intersection[2];
			result[0] = new Intersection(Math.min((float) ((-b + discriminant) / (2 * a)), (float) ((-b - discriminant) / (2 * a))), shape);
			result[1] = new Intersection(Math.max((float) ((-b + discriminant) / (2 * a)), (float) ((-b - discriminant) / (2 * a))), shape);
		}
		return result;
	}
	
	
	public static Vector normalAt(Shape shape, Point point) {
		Point objectPoint = null;
		Vector worldNormal = null;
		Matrix invTransform = null;
		
		try {	
			invTransform = Matrix.getInverseMatrix(shape.getTransform());
		} catch (MatrixNotInvertibleException e) {
			e.printStackTrace();
			return worldNormal;
		}
	
		objectPoint = Matrix.matrix2Point(Matrix.multiply(invTransform, Matrix.tuple2Matrix(point)));	
		Vector objectNormal = objectPoint.subtract(new Point(0, 0, 0));
		worldNormal = Matrix.matrix2Vector(Matrix.multiply(Matrix.transpose(invTransform), Matrix.tuple2Matrix(objectNormal)));
		
		worldNormal.setW(0);
		return Vector.normalize(worldNormal); 
	}
	
	
	public Matrix getTransform() { return this.transform; }
	public void setTransform(Matrix transform) { this.transform = transform; }
	public Material getMaterial() { return this.material; }
	public void setMaterial(Material material) { this.material = material; }
	public String getUuid() { return "";}
	
}
