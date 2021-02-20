package in.a93;

import java.util.ArrayList;
import java.util.UUID;

//import in.a93.Exceptions.MatrixNotInvertibleException;

public class Cone extends Shape {
	/*
	 * Double napped cone
	 * Two cones - one upside down and other is right-side up
	 * Both intersect at the origin
	 * 
	 */
	private static final float DELTA = 0.00001f;
	private String uuid;
	private float min; // -y
	private float max; // +y
	private boolean capped;
	private Matrix inverse;
	
	public Cone() {
		super();
		this.uuid = UUID.randomUUID().toString();
		this.min = (float) Double.NEGATIVE_INFINITY;
		this.max = (float) Double.POSITIVE_INFINITY;
		this.capped = false;
		this.inverse = Matrix.getIdentityInverse(4, 4);
	}

	@Override
	public Intersection[] intersect(Ray originalRay) {
		Ray localRay = super.getLocalRay(this, originalRay);
		return this.localIntersect(localRay);
	}

	private Intersection[] localIntersect(Ray localRay) {
		ArrayList<Intersection> result = new ArrayList<Intersection>();
		
		float a = localRay.getDirection().getX() * localRay.getDirection().getX() - 
				  localRay.getDirection().getY() * localRay.getDirection().getY() + 
				  localRay.getDirection().getZ() * localRay.getDirection().getZ();
		
//		if (Math.abs(a) - 0 < Cone.DELTA) return this.arrayListToArray(this.intersectCaps(localRay, result));
		
		float b =  2 * localRay.getOrigin().getX() * localRay.getDirection().getX() - 
				   2 * localRay.getOrigin().getY() * localRay.getDirection().getY() + 
				   2 * localRay.getOrigin().getZ() * localRay.getDirection().getZ();
		
		float c = localRay.getOrigin().getX() * localRay.getOrigin().getX() - 
				  localRay.getOrigin().getY() * localRay.getOrigin().getY() + 
				  localRay.getOrigin().getZ() * localRay.getOrigin().getZ();
		
		if (Math.abs(a) - 0 < Cone.DELTA) {
			if (Math.abs(b) - 0 < Cone.DELTA) return null; // no intersection
			else {
				float t = -c / (2*b);
				return Intersection.intersections(new Intersection(t, this));
			}
		}

		float discriminant = (b * b) - (4 * a * c);

		// Very small numbers e.g. -1 * 10^-6 can be treated as 0
		if (discriminant < -Cone.DELTA) return null;
		else {
			// Small negative numbers can be taken as 0
			float t0 = (float) (-b - Math.sqrt(Math.abs(discriminant))) / (2 * a);
			float t1 = (float) (-b + Math.sqrt(Math.abs(discriminant))) / (2 * a);
			
			// Capping the Cone
			if (t0 > t1) {
				float temp = t1;
				t1 = t0;
				t0 = temp;
			}
			
			float y0 = localRay.getOrigin().getY() + (t0 * localRay.getDirection().getY());
			if (this.getMin() < y0 && y0 < this.getMax()) result.add(new Intersection(t0, this));
			
			float y1 = localRay.getOrigin().getY() + (t1 * localRay.getDirection().getY());
			if (this.getMin() < y1 && y1 < this.getMax()) result.add(new Intersection(t1, this));
		}
		ArrayList<Intersection> _tempResult = this.intersectCaps(localRay, result);
		
		if (_tempResult != null) result.addAll(_tempResult);
		
		// Removing duplicates
		// TODO: Find a better way to do this
		result.sort(new IntersectionComparator());
		if (result.size() == 2 && result.get(0).equals(result.get(1))) {}
		else {
			for (int i = 0; i < result.size() - 1; i++) {
				if (result.get(i).equals(result.get(i+1))) result.remove(i);
			}
		}
		return this.arrayListToArray(result);
	}
	
	@Override
	protected Vector localNormalAt(Point localPoint) {
		float dSquare = (float) (Math.pow(localPoint.getX(), 2) + Math.pow(localPoint.getZ(), 2));
		
		// end cap normal
		if (dSquare < Math.pow(this.getMax(), 2) 
				&& ((localPoint.getY() > (this.getMax() - Cone.DELTA)) 
						|| Math.abs(localPoint.getY() - this.getMax() + Cone.DELTA) < Cone.DELTA
						))
			return new Vector(0, 1, 0);
		else if (dSquare < Math.pow(this.getMax(), 2) 
				&& ((localPoint.getY() < (this.getMin() + Cone.DELTA)) 
						|| Math.abs(localPoint.getY() - this.getMax() - Cone.DELTA) < Cone.DELTA
						))
			return new Vector(0, -1, 0);
		else {
			// cone normal
			float y = (float) Math.sqrt(Math.pow(localPoint.getX(), 2) + Math.pow(localPoint.getZ(), 2));
//			System.out.println("y: " + y);
			if (localPoint.getY() > 0) { System.out.println("y: " + y); y = -y;}
			
			return new Vector(localPoint.getX(), y, localPoint.getZ());
		}
	}
	
	private ArrayList<Intersection> intersectCaps(Ray ray, ArrayList<Intersection> result) {
		if (!(this.isCapped()) || 
				(Math.abs(ray.getDirection().getY()) - 0) < Cone.DELTA) {
			return null;
		}
		
		float t = (this.getMin() - ray.getOrigin().getY()) / ray.getDirection().getY();
		
		if (checkCap(ray, t, this.getMin())) {
				result.add(new Intersection(t, this));
		}
		
		t = (this.getMax() - ray.getOrigin().getY()) / ray.getDirection().getY();
		
		if (checkCap(ray, t, this.getMin())) {
				result.add(new Intersection(t, this));
			}
		
		return result;
	}
	
	private Intersection[] arrayListToArray(ArrayList<Intersection> xs) {
		if (xs == null) return null;
		Intersection[] intersections = new Intersection[xs.size()];
		for (int i = 0; i < xs.size(); i++) {
			intersections[i] = xs.get(i);
		}
		
		return intersections;
	}
	
	private boolean checkCap(Ray ray, float t, float yPos) {
		float x = ray.getOrigin().getX() + (t * ray.getDirection().getX());
		float z = ray.getOrigin().getZ() + (t * ray.getDirection().getZ());
		
		// Re check this value
		return ((float) (Math.pow(x, 2) + Math.pow(z, 2)) < Math.pow(yPos, 2)) || 
				((float) (Math.pow(x, 2) + Math.pow(z, 2))) - Math.pow(yPos, 2) < Cone.DELTA;
	}
	
	public String getUuid() {
		return uuid;
	}
	
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append("Shape: Cone\n");
		s.append("Object ID: " + this.getUuid() + "\n");
		s.append(super.toString());
		return s.toString();
	}
	
	public float getMin() {
		return min;
	}
	
	public void setMin(float min) {
		this.min = min;
	}
	
	public float getMax() {
		return max;
	}
	
	public void setMax(float max) {
		this.max = max;
	}

	public boolean isCapped() {
		return capped;
	}

	public void setCapped(boolean capped) {
		this.capped = capped;
	}

	@Override
	public Bounds parentSpaceBounds() {
		Bounds bounds = new Bounds();
		bounds.boundsOf(this);
		bounds.setTransform(this.getTransform());
		return bounds;		
	}

	@Override
	public Matrix getTransformInverse() {
		return this.inverse;
	}

	
}
