package in.a93;

public class IntersectionPreComputedValue {
	private float intersectionT;
	private Sphere object;
	private Point point;
	private Vector eyeVector;
	private Vector normalVector;
	private boolean inside;
	private Point overPoint;
	
	// Prevents acne in the renders
	private static float DELTA = 0.0015f;
	
	public IntersectionPreComputedValue(Intersection intersection, Ray ray) {
		this.intersectionT = intersection.getT();
		this.object = intersection.getObject();
		this.point = Ray.getPosition(ray, intersection.getT());
		this.eyeVector = ray.getDirection().scalarMultiply(-1);
		Vector _tempNormalVector = Sphere.normalAt(intersection.getObject(), this.point);
		
		if (Vector.dot(_tempNormalVector, eyeVector) < 0) {
			this.inside = true;
			this.normalVector = _tempNormalVector.scalarMultiply(-1);
		} else {
			this.inside = false;
			this.normalVector = _tempNormalVector.scalarMultiply(1);
		}
		
		this.overPoint = this.point.add(this.normalVector.scalarMultiply(DELTA));
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

	public Sphere getObject() {
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
	
}