package in.a93;

import java.util.UUID;

import in.a93.Exceptions.MatrixNotInvertibleException;

@SuppressWarnings(value = { "unused" })
public class Sphere extends Shape {
	/*
	 * Unit sphere (i.e. radius is 1 unit)
	 * Centered at the origin (0, 0, 0)
	 */

	private static final float DELTA = 0.00001f;
	
	private String uuid;
	private Matrix transform;
	private Material material;
	
	public Sphere() {
		super();
		this.uuid = UUID.randomUUID().toString();
	}
	
	public static int intersectCount(Sphere sphere, Ray ray) {
		Intersection[] out = Shape.intersect(sphere, ray);
		
		if (out == null) return 0;
		else if (Math.abs(out[0].getT() - out[1].getT()) < Sphere.DELTA) return 1;
		else return 2;
	}
	
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		
		s.append("UUID: " + this.uuid);
		
		return s.toString();
	}
	
	public String getUuid() { return this.uuid;	}

}
