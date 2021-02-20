package in.a93;

import java.util.UUID;

import in.a93.Exceptions.MatrixNotInvertibleException;

public class Plane extends Shape {
	private String uuid;
	private static float DELTA = 0.00001f;
	
	public Plane() {
		super();
		this.uuid = UUID.randomUUID().toString();
	}
	
	public Intersection[] intersect(Ray originalRay) {
		Ray localRay = super.getLocalRay(this, originalRay);
		return this.localIntersect(localRay);
	}

	private Intersection[] localIntersect(Ray localRay) {
		Intersection[] result = null;
		if ((float) Math.abs(localRay.getDirection().getY()) < DELTA) {return result;}
		else {
			result = new Intersection[1];
//			System.out.printf("originY: %s\tdirectionY: %s\n", originalRay.getOrigin().getY(), originalRay.getDirection().getY());
			float t = -1 * (localRay.getOrigin().getY() / localRay.getDirection().getY());
			result[0] = new Intersection(t, this);
			return result;
		}	
		
	}
	
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append("Object type: Plane\n");
		s.append("UUID: " + this.uuid);
		
		return s.toString();
	}
	
	public String getUuid() { return this.uuid; }
	
	
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

	@Override
	protected Vector localNormalAt(Point localPoint) {
		return new Vector(0, 1, 0);
	}

	@Override
	public Bounds parentSpaceBounds() {
		Bounds bounds = new Bounds();
		bounds.boundsOf(this);
		bounds.setTransform(this.getTransform());
		return bounds;		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
