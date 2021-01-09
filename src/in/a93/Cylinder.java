package in.a93;

import java.util.ArrayList;
import java.util.UUID;

import in.a93.Exceptions.MatrixNotInvertibleException;

public class Cylinder extends Shape {

	private static final float DELTA = 0.00001f;
	private String uuid;
	private float min; // -y
	private float max; // +y
	private boolean capped;
	
	public Cylinder() {
		super();
		this.uuid = UUID.randomUUID().toString();
		this.min = (float) Double.NEGATIVE_INFINITY;
		this.max = (float) Double.POSITIVE_INFINITY;
		this.capped = false;
	}

	@Override
	public Intersection[] intersect(Ray originalRay) {
		Ray localRay = super.getLocalRay(this, originalRay);
		return this.localIntersect(localRay);
	}

	private Intersection[] localIntersect(Ray localRay) {
		ArrayList<Intersection> result = new ArrayList<Intersection>();
		
		float a = (float) (Math.pow(localRay.getDirection().getX(), 2) + Math.pow(localRay.getDirection().getZ(), 2));
		
		if (a - 0 < Cylinder.DELTA) return this.arrayListToArray(this.intersectCaps(localRay, result));
		
		float b = (2 * (localRay.getOrigin().getX() * localRay.getDirection().getX())) 
				+ (2 * (localRay.getOrigin().getZ() * localRay.getDirection().getZ()));
		
		float c = (float) (Math.pow(localRay.getOrigin().getX(), 2) + Math.pow(localRay.getOrigin().getZ(), 2)) - 1;
		
		float discriminant = (float) (Math.pow(b, 2) - (4 * a * c));
		if (discriminant < 0) return null;
		else {
			float t0 = (float) ((-b - Math.sqrt(discriminant)) / (2 * a));
			float t1 = (float) ((-b + Math.sqrt(discriminant)) / (2 * a));
			
			// Capping the cylinder
			if (t0 > t1) {
				float temp = t0;
				t1 = t0;
				t0 = temp;
			}
//			
//			Intersection i1 = null;
//			Intersection i2 = null;
			
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
		for (int i = 0; i < result.size() - 1; i++) {
			if (result.get(i).equals(result.get(i+1))) result.remove(i);
		}
		return this.arrayListToArray(result);
	}

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
		Vector localNormal = this.localNormaAt(localPoint);
	
		worldNormal = Matrix.matrix2Vector(Matrix.multiply(invTransposeTransform, Matrix.tuple2Matrix(localNormal)));
		worldNormal.setW(0);
		return Vector.normalize(worldNormal);
	}

	private Vector localNormaAt(Point localPoint) {
		float dSquare = (float) (Math.pow(localPoint.getX(), 2) + Math.pow(localPoint.getZ(), 2));
		
		if (dSquare < 1 
				&& ((localPoint.getY() > (this.getMax() - Cylinder.DELTA)) 
						|| Math.abs(localPoint.getY() - this.getMax() - Cylinder.DELTA) < Cylinder.DELTA
						))
			return new Vector(0, 1, 0);
		else if (dSquare < 1 
				&& ((localPoint.getY() < (this.getMin() + Cylinder.DELTA)) 
						|| Math.abs(localPoint.getY() - this.getMax() - Cylinder.DELTA) < Cylinder.DELTA
						))
			return new Vector(0, -1, 0);
		else
			return new Vector(localPoint.getX(), 0, localPoint.getZ());
	}

	private ArrayList<Intersection> intersectCaps(Ray ray, ArrayList<Intersection> result) {
		if (!(this.isCapped()) || (Math.abs((ray.getDirection().getY()) - 0) < Cylinder.DELTA)) {
			return null;
		}
		
		float t = (this.getMin() - ray.getOrigin().getY()) / ray.getDirection().getY();
		
		if (checkCap(ray, t)) {
				result.add(new Intersection(t, this));
		}
		
		t = (this.getMax() - ray.getOrigin().getY()) / ray.getDirection().getY();
		
		if (checkCap(ray, t)) {
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
	
	
	
	private boolean checkCap(Ray ray, float t) {
		float x = ray.getOrigin().getX() + (t * ray.getDirection().getX());
		float z = ray.getOrigin().getZ() + (t * ray.getDirection().getZ());
		
		return ((float) (Math.pow(x, 2) + Math.pow(z, 2)) < 1) || 
				((float) (Math.pow(x, 2) + Math.pow(z, 2))) - 1 < Cylinder.DELTA;
	}
	
	public String getUuid() {
		return uuid;
	}
	
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append("Shape: Cylinder\n");
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
	
}
