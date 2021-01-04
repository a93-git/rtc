package in.a93;

import java.util.Arrays;

public class Intersection implements Comparable<Intersection> {
	private float t;
	private Shape object;
//	private static float DELTA = 0.00001f;
	
	public Intersection(float t, Shape object) {
		this.t = t;
		this.object = object;
	}
	
	public static Intersection[] intersections(Intersection... intersections) {	
		Arrays.sort(intersections);
		return intersections;
	}
	
	public float getT() { return this.t; }
	
	public Shape getObject() {	return this.object; }
	
	public static Intersection getHit(Intersection[] intersections) {
		Intersection result = null;
		for (Intersection i : intersections) {
			if ((i == null) || (i.getT() < 0)) continue;
			else return i;
		}
		
		return result;
	}
	
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();		
		s.append(t);		
		return s.toString();
	}

	@Override
	public int compareTo(Intersection o) { return Double.compare(this.getT(), o.getT()); }
	
	public static IntersectionPreComputedValue preComputedValue(Intersection intersection, Ray ray ) {
		IntersectionPreComputedValue value = new IntersectionPreComputedValue(intersection, ray);
		return value;
	}
}


