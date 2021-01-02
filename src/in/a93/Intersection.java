package in.a93;

public class Intersection {
	private float t;
	private Sphere object;
	private static float DELTA = 0.00001f;
	
	public Intersection(float t, Sphere object) {
		this.t = t;
		this.object = object;
	}
	
	public static Intersection[] intersections(Intersection... intersections) {	return intersections; } //TODO: Sort based on t value
	
	public float getT() { return this.t; }
	
	public Sphere getObject() {	return this.object; }
	
	public static Intersection hit(Intersection[] intersections) {

		Intersection result = intersections[0];
		
		for (Intersection i : intersections) {

			if (i.getT() < 0) continue;
			else if (result == null) result = i;
			else if ((result.getT() - i.getT()) < Intersection.DELTA) continue;
			else if ((result.getT() - i.getT()) < 0) continue;
			else result = i;
		}
		
		return result;
	}
	
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		
		s.append(t);
		
		return s.toString();
	}
}
