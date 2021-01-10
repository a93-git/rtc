package in.a93;

import java.util.UUID;


public class Cube extends Shape {
	private String uuid;
	private static float DELTA = 0.00001f;
	
	public Cube() {
		super();
		this.uuid = UUID.randomUUID().toString();
	}
	
	@Override
	public Intersection[] intersect(Ray originalRay) {
		Ray localRay = super.getLocalRay(this, originalRay);
		return this.localIntersect(localRay);
	}

	private Intersection[] localIntersect(Ray localRay) {
		float[] x = this.checkAxis(localRay.getOrigin().getX(), localRay.getDirection().getX());
		float[] y = this.checkAxis(localRay.getOrigin().getY(), localRay.getDirection().getY());
		float[] z = this.checkAxis(localRay.getOrigin().getZ(), localRay.getDirection().getZ());
		float tmin = Math.max(Math.max(x[0], y[0]), z[0]);
		float tmax = Math.min(Math.min(x[1], y[1]), z[1]);

		Intersection[] result = null;
		
		if (tmin > tmax) return result;
		else { 
			result = Intersection.intersections(new Intersection(tmin, this), new Intersection(tmax, this));
			return result;
		}
	}

	private float[] checkAxis(float origin, float direction) {
		float tminN = (-1 - origin);
		float tmaxN =  (1 - origin);
		
		float tmin, tmax;
		
		if (!(Math.abs(direction) > Cube.DELTA)) direction += Cube.DELTA;
		tmin = tminN / direction;
		tmax = tmaxN / direction;

		
		float[] result = new float[2];
		
		if (tmin > tmax) {
			float _t = tmax;
			tmax = tmin;
			tmin = _t;
		}
		result[0] = tmin; result[1] = tmax;
		return result;
	}

//	@Override
//	public Vector normalAt(Point point) {
//		Vector worldNormal = null;
//		Matrix invTransposeTransform = null;
//		
//		try {	
//			invTransposeTransform = Matrix.getInverseMatrix(Matrix.transpose(this.getTransform()));
//		} catch (MatrixNotInvertibleException e) {
//			e.printStackTrace();
//			return worldNormal;
//		}
//
//		Point localPoint = super.getLocalPoint(point, invTransposeTransform);
//		Vector localNormal = this.localNormaAt(localPoint);
//	
//		worldNormal = Matrix.matrix2Vector(Matrix.multiply(invTransposeTransform, Matrix.tuple2Matrix(localNormal)));
//		worldNormal.setW(0);
//		return Vector.normalize(worldNormal);
//	}

	
	@Override
	public String getUuid() {
		return this.uuid;
	}

	@Override
	public boolean equals(Object other) {
		return false;
	}
	
	@Override
	public String toString() {
		return null;
	}

	@Override
	protected Vector localNormalAt(Point localPoint) {
		float max = Math.max(
				Math.abs(localPoint.getX()), 
				Math.max(
						Math.abs(localPoint.getY()),
						Math.abs(localPoint.getZ())
						)
				);
		
		if (max - Math.abs(localPoint.getX()) < Cube.DELTA) return new Vector(localPoint.getX(), 0, 0);
		else if (max - Math.abs(localPoint.getY()) < Cube.DELTA) return new Vector(0, localPoint.getY(), 0); 
		return new Vector(0, 0, localPoint.getZ());			
	}

}
