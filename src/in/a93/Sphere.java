package in.a93;

import java.util.UUID;

import in.a93.Exceptions.MatrixNotInvertibleException;

@SuppressWarnings(value = { "unused" })
public class Sphere {
	/*
	 * Unit sphere (i.e. radius is 1 unit)
	 * Centered at the origin (0, 0, 0)
	 */

	private static final float DELTA = 0.00001f;
	
	private String uuid;
	private Matrix transform;
	private Material material;
	
	public Sphere() {
		this.transform = Matrix.getIdentityMatrix(4, 4);
		this.uuid = UUID.randomUUID().toString();
		this.material = new Material();
	}
	
	public static Intersection[] intersect(Sphere sphere, Ray originalRay) {
		Intersection[] result = null;
		
		Ray ray;
		try {
			ray = Ray.transformRay(originalRay, Matrix.getInverseMatrix(sphere.getTransform()));
		} catch (MatrixNotInvertibleException e) {
			e.printStackTrace();
			return result;
		}
		
		Vector sphereToRay = ray.getOrigin().subtract(new Point(0, 0, 0));
		float a = Tuple.dot(ray.getDirection(), ray.getDirection());
		float b = 2 * Tuple.dot(ray.getDirection(), sphereToRay);
		float c = Tuple.dot(sphereToRay, sphereToRay) - 1;
		
		float discriminant = (float) Math.sqrt(Math.pow(b, 2) - (4 * a * c));
		
		if (discriminant >= 0) {
			result = new Intersection[2];
			result[0] = new Intersection(Math.min((float) ((-b + discriminant) / (2 * a)), (float) ((-b - discriminant) / (2 * a))), sphere);
			result[1] = new Intersection(Math.max((float) ((-b + discriminant) / (2 * a)), (float) ((-b - discriminant) / (2 * a))), sphere);
		}
		return result;
	}
	
	public static int intersectCount(Sphere sphere, Ray ray) {
		Intersection[] out = Sphere.intersect(sphere, ray);
		
		if (out == null) return 0;
		else if (Math.abs(out[0].getT() - out[1].getT()) < Sphere.DELTA) return 1;
		else return 2;
	}
	
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		
		s.append("UUID: " + this.uuid);
		
		return s.toString();
	}
	
	public String getUuid() { return this.uuid;	}

	public void setTransform(Matrix m) {
		this.transform = Matrix.multiply(m, this.transform);
	}
	
	public Matrix getTransform() { return this.transform; }

	public Material getMaterial() {
		return this.material;
	}
	
	public void setMaterial(Material newMaterial) {
		this.material = newMaterial;
	}
	
	public static Vector normalAt(Sphere sphere, Point point) {
		/*
		 * Normal at the point is equal to the Vector between sphere's origin and the point
		 * 
		 * Here the origin is assumed to be at Point(0, 0, 0)
		 */
		
		Point objectPoint = null;
		Vector worldNormal = null;
		
		try {
			objectPoint = Matrix.matrix2Point(Matrix.multiply(Matrix.getInverseMatrix(sphere.getTransform()), Matrix.tuple2Matrix(point)));
		} catch (MatrixNotInvertibleException e) {
			e.printStackTrace();
			return worldNormal;
		}
		
		Vector objectNormal = objectPoint.subtract(new Point(0, 0, 0));

		try {
			worldNormal = Matrix.matrix2Vector(Matrix.multiply(Matrix.transpose(Matrix.getInverseMatrix(sphere.getTransform())), Matrix.tuple2Matrix(objectNormal)));
		} catch (MatrixNotInvertibleException e) {
			e.printStackTrace();
			return worldNormal;
		}
		
		worldNormal.setW(0);
		return Vector.normalize(worldNormal); 
	}




















}
