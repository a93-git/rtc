package in.a93;

import java.util.ArrayList;

public class IntersectionCompute {
	private float intersectionT;
	private Shape object;
	private Point point;
	private Vector eyeVector;
	private Vector normalVector;
	private boolean inside;
	private Point overPoint;
	private Point underPoint;
	private Vector reflectVector;
	private float firstRI; // refractive index one
	private float secondRI; // refractive index two
	
	// Prevents acne in the renders
	private static float DELTA = 0.0015f;
	
	public IntersectionCompute(Intersection intersection, Ray ray, Intersection[] intersections) {
		this.intersectionT = intersection.getT();
		this.object = intersection.getObject();
		this.point = Ray.getPosition(ray, intersection.getT());
		this.eyeVector = ray.getDirection().scalarMultiply(-1);
		Vector _tempNormalVector = intersection.getObject().normalAt(this.point, intersection);
		
		if (Vector.dot(_tempNormalVector, eyeVector) < 0) {
			this.inside = true;
			this.normalVector = _tempNormalVector.scalarMultiply(-1);
		} else {
			this.inside = false;
			this.normalVector = _tempNormalVector.scalarMultiply(1);
		}
		
		this.overPoint = this.point.add(this.normalVector.scalarMultiply(DELTA));
		this.underPoint = this.point.subtract(this.normalVector.scalarMultiply(DELTA));
		
		// No need to negate the normal here as it has already been done above
		this.reflectVector = Vector.reflect(ray.getDirection(), this.normalVector);
		
		// refractive indices
		ArrayList<Shape> shapeContainer = new ArrayList<Shape>();
		
		for (Intersection i : intersections) {
			if (i.equals(intersection)) {
				if (shapeContainer.isEmpty()) this.firstRI = 1.0f;
				else this.firstRI = shapeContainer.get(shapeContainer.size() - 1).getMaterial().getRefractiveIndex();
			}
			
			if (shapeContainer.contains(i.getObject())) {shapeContainer.remove(i.getObject()); }
			else shapeContainer.add(i.getObject());
			
			if (i.equals(intersection)) {
				if (shapeContainer.isEmpty()) this.secondRI = 1.0f;
				else this.secondRI = shapeContainer.get(shapeContainer.size() - 1).getMaterial().getRefractiveIndex();
			}
		}
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

	public float getFirstRI() {
		return firstRI;
	}

	public float getSecondRI() {
		return secondRI;
	}

	public Point getUnderPoint() {
		return underPoint;
	}
	
	
}