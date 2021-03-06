package in.a93;

import java.util.Arrays;

public class Intersection implements Comparable<Intersection> {
	private float t;
	private Shape object;
	private static float DELTA = 0.0001f;
	private float u;
	private float v;
	
	public Intersection(float t, Shape object) {
		this.t = t;
		this.object = object;
	}
	
	public Intersection(float t, Shape shape, float u, float v) {
		this.t = t;
		this.object = shape;
		this.u = u;
		this.v = v;
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
		s.append(this.getT());		
		s.append("\n" + this.getObject());		
		return s.toString();
	}

	@Override
	public int compareTo(Intersection o) { return Double.compare(this.getT(), o.getT()); }
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (this == obj) return true;
		if (getClass() != obj.getClass()) return false;
		
		Intersection other = (Intersection) obj; 
		return ((this.getT() - other.getT()) < Intersection.DELTA && this.getObject().equals(other.getObject()));
	}
	
	public static IntersectionCompute preComputedValue(Intersection intersection, Ray ray ) {
		IntersectionCompute value = new IntersectionCompute(intersection, ray, null);
		return value;
	}
	
	public static float getSchlick(IntersectionCompute computed) {
		float cos = Vector.dot(computed.getEyeVector(), computed.getNormalVector());
		
		// TIR only occurs if n1 > n2
		if (computed.getFirstRI() > computed.getSecondRI()) {
			float etaRatio = computed.getFirstRI() / computed.getSecondRI();
			float sin2T = (float) (Math.pow(etaRatio, 2) * (1.0 - Math.pow(cos, 2)));
			if (sin2T > 1.0f) return 1.0f;
			
			float cosT = (float) Math.sqrt(1 - sin2T);
			cos = cosT;
		}
		
		float r = (float) Math.pow(((computed.getFirstRI() - computed.getSecondRI()) / (computed.getFirstRI() + computed.getSecondRI())), 2);
		return r + (1 - r) * (float) Math.pow((1 - cos), 5);
	}

	public float getU() {
		return u;
	}

	public float getV() {
		return v;
	}

	public void setT(float t) {
		this.t = t;
	}

	public void setObject(Shape object) {
		this.object = object;
	}

	public void setU(float u) {
		this.u = u;
	}

	public void setV(float v) {
		this.v = v;
	}
	
}


