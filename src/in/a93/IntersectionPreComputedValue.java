package in.a93;

public class IntersectionPreComputedValue {
	private float intersectionT;
	private Shape object;
	private Point point;
	private Vector eyeVector;
	private Vector normalVector;
	private boolean inside;
	private Point overPoint;
	private Vector reflectVector;
	
	// Prevents acne in the renders
	private static float DELTA = 0.0015f;
	
	public IntersectionPreComputedValue(Intersection intersection, Ray ray) {
		this.intersectionT = intersection.getT();
		this.object = intersection.getObject();
		this.point = Ray.getPosition(ray, intersection.getT());
		this.eyeVector = ray.getDirection().scalarMultiply(-1);
		Vector _tempNormalVector = intersection.getObject().normalAt(this.point);
		
		if (Vector.dot(_tempNormalVector, eyeVector) < 0) {
			this.inside = true;
			this.normalVector = _tempNormalVector.scalarMultiply(-1);
		} else {
			this.inside = false;
			this.normalVector = _tempNormalVector.scalarMultiply(1);
		}
		
		this.overPoint = this.point.add(this.normalVector.scalarMultiply(DELTA));
		
		// No need to negate the normal here as it has already been done above
		this.reflectVector = Vector.reflect(ray.getDirection(), this.normalVector);
		
	}

	public Point getOverPoint() {
		return overPoint;
	}

	public static float getDELTA() {
		return DELTA;
	}

	public float getIntersectionT() {
		return intersectionT;
	}

	public Shape getObject() {
		return object;
	}

	public Point getPoint() {
		return point;
	}

	public Vector getEyeVector() {
		return eyeVector;
	}

	public Vector getNormalVector() {
		return normalVector;
	}
	
	public boolean getInside() {
		return this.inside;
	}

	public Vector getReflectVector() {
		return reflectVector;
	}

	public void setReflectVector(Vector reflectVector) {
		this.reflectVector = reflectVector;
	}
	
}