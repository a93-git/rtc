package in.a93.Tests;

import in.a93.Tuple;
import in.a93.Vector;

public class NormalReflectTest {
	
	public static void normalReflectTest() {
		Vector v = new Vector(1, -1, 0);
		Vector n = new Vector(0, 1, 0);
		
		System.out.println(Tuple.reflect(v, n));
	}
	
	public static void normalReflectSlantTest() {
		Vector v = new Vector(0, -1, 0);
		Vector n = new Vector((float) Math.sqrt(2)/ 2, (float) Math.sqrt(2)/ 2, 0);
		
		System.out.println(Tuple.reflect(v, n));		
	}
	
	
}
