package in.a93;

import java.util.UUID;

import in.a93.Exceptions.MatrixNotInvertibleException;

@SuppressWarnings(value = { "unused" })
public class Sphere extends Shape {
	/*
	 * Unit sphere (i.e. radius is 1 unit)
	 * Centered at the origin (0, 0, 0)
	 */

	private static final float DELTA = 0.00001f;
	
	private String uuid;
	private Matrix transform;
	private Material material;
	
	public Sphere() {
		super();
		this.uuid = UUID.randomUUID().toString();
	}

	public int intersectCount(Ray ray) {
		Intersection[] out = this.intersect(ray);
		
		if (out == null) return 0;
		else if (Math.abs(out[0].getT() - out[1].getT()) < Sphere.DELTA) return 1;
		else return 2;
	}
	
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append("Object type: Sphere\n");
		s.append("UUID: " + this.uuid);
		
		return s.toString();
	}
	
	public String getUuid() { return this.uuid;	}

	public Intersection[] intersect(Ray originalRay) {
		Ray localRay = super.getLocalRay(this, originalRay);
		return this.localIntersect(localRay);
	}
	
	public Intersection[] localIntersect(Ray localRay) {
		Intersection[] result = null;
		
		Vector shapeToRay = localRay.getOrigin().subtract(new Point(0, 0, 0));
		float a = Tuple.dot(localRay.getDirection(), localRay.getDirection());
		float b = 2 * Tuple.dot(localRay.getDirection(), shapeToRay);
		float c = Tuple.dot(shapeToRay, shapeToRay) - 1;
		
		float discriminant = (float) Math.sqrt(Math.pow(b, 2) - (4 * a * c));
		
		if (discriminant >= 0) {
			result = new Intersection[2];
			result[0] = new Intersection(Math.min((float) ((-b + discriminant) / (2 * a)), (float) ((-b - discriminant) / (2 * a))), this);
			result[1] = new Intersection(Math.max((float) ((-b + discriminant) / (2 * a)), (float) ((-b - discriminant) / (2 * a))), this);
		}
		
		return result;		
	}

	/*
	 * Convert the point from world space to local space
	 * Calculate the normal
	 * Transform the point by (the inverse of the transpose) of the transformation matrix of the object
	 */
	@Override
	public Vector normalAt(Point point) {		
		Vector worldNormal = null;
		Matrix invTransposeTransform = null;
		
		try {	
			invTransposeTransform = Matrix.getInverseMatrix(Matrix.transpose(this.getTransform()));
		} catch (MatrixNotInvertibleException e) {
			e.printStackTrace();
			return worldNormal;
		}

		Point localPoint = super.getLocalPoint(point, invTransposeTransform);
		Vector localNormal = this.localNormalAt(localPoint); 
		worldNormal = Matrix.matrix2Vector(Matrix.multiply(invTransposeTransform, Matrix.tuple2Matrix(localNormal)));
		worldNormal.setW(0);
		return Vector.normalize(worldNormal);
	}

	private Vector localNormalAt(Point localPoint) {
		Vector worldNormal = null;
		Vector objectNormal = localPoint.subtract(new Point());
		
		return objectNormal;
	}
	
	public static Sphere getGlassSphere() {
		Sphere s = new Sphere();
		s.getMaterial().setRefractiveIndex(1.5f);
		s.getMaterial().setTransparency(1.0f);
		
		return s;
		
	}
}
