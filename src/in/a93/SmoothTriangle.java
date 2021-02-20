package in.a93;

public class SmoothTriangle extends Shape{
	private static final float DELTA = 0.00001f;
	private Point p1;
	private Point p2;
	private Point p3;
	
	private Vector e1; //edge 1
	private Vector e2; //edge 2
	
	private Vector n1;
	private Vector n2;
	private Vector n3;
	
	public SmoothTriangle(Point p1, Point p2, Point p3, Vector n1, Vector n2, Vector n3) {
		this.p1 = p1;
		this.p2 = p2;
		this.p3 = p3;
		this.n1 = n1;
		this.n2 = n2;
		this.n3 = n3;
		
		this.e1 = p2.subtract(p1);
		this.e2 = p3.subtract(p1);
	}
	
	public Vector getE1() {
		return e1;
	}

	public Vector getE2() {
		return e2;
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append("Point 1: " + this.getP1() + "\n");
		s.append("Point 2: " + this.getP2() + "\n");
		s.append("Point 3: " + this.getP3() + "\n");
		s.append("Normal at point 1: " + this.getN1() + "\n");
		s.append("Normal at point 2: " + this.getN2() + "\n");
		s.append("Normal at point 3: " + this.getN3() + "\n");
		
		return s.toString();
		
	}

	public Point getP1() {
		return p1;
	}

	public Point getP2() {
		return p2;
	}

	public Point getP3() {
		return p3;
	}

	public Vector getN1() {
		return n1;
	}

	public Vector getN2() {
		return n2;
	}

	public Vector getN3() {
		return n3;
	}

	@Override
	public Bounds parentSpaceBounds() {
		Bounds bounds = new Bounds();
		bounds.boundsOf(this);
		return bounds.setTransformation(this.getTransform());
//		return bounds;		
	}

	@Override
	public Intersection[] intersect(Ray originalRay) {
		Ray localRay = super.getLocalRay(this, originalRay);
		return this.localIntersect(localRay);
	}

	private Intersection[] localIntersect(Ray localRay) {
		Vector dirCrossEdge2 = Vector.cross(localRay.getDirection(), this.getE2());
		float temp = Vector.dot(this.getE1(), dirCrossEdge2);
		
		if (Math.abs(temp) < SmoothTriangle.DELTA) return null;
		
		float f = 1.0f / temp;
		Vector p1ToOrigin = localRay.getOrigin().subtract(this.getP1());
		float u = f * Vector.dot(p1ToOrigin, dirCrossEdge2);
		
		if (u < 0 || u > 1) return null;
		
		Vector originCrossEdge1 = Vector.cross(p1ToOrigin, this.getE1());
		float v = f * Vector.dot(localRay.getDirection(), originCrossEdge1);
		
		if (v < 0 || (u + v) > 1) return null;
		
		float t = f * Vector.dot(this.getE2(), originCrossEdge1);
		
		return Intersection.intersections(new Intersection(t, this, u, v));
	}
	
	@Override
	protected Vector localNormalAt(Point localPoint, Intersection intersection) {
		// In case of triangles, the normal is same at everypoint
		return this.getN2().scalarMultiply(intersection.getU())
				.add(this.getN3().scalarMultiply(intersection.getV()))
				.add(this.getN1().scalarMultiply(1 - intersection.getU() - intersection.getV())); 
	}
	
}
