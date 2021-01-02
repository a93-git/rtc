package in.a93.Tests;

import in.a93.Point;
import in.a93.Tuple;
import in.a93.Vector;

@SuppressWarnings(value = { "unused" })
public class Test {
	public static void test() {
		Point a = new Point(4.001f, -4.20f, 2.55f);
		Vector b = new Vector(3.3f, -1.6f, 0.0f);
		Point c = new Point(4.001f, -4.20f, 2.55f);
		
		if (Math.abs(a.getW() - 1.0f) < Tuple.DELTA) {
			System.out.println("This is a point AND not a vector");
		}
		
		if (Math.abs(b.getW() - 0.0f) < Tuple.DELTA) {
			System.out.println("This is a vector AND not a point");
		}
		
		if (a.equals(c)) System.out.println("Equal");
		
		Point d = new Point(3f, 2f, 1f);
		Vector e = new Vector(5f, 6f, 7f);
		
		System.out.println(d.subtract(e));
		
		// subtracting a vector from a zero vector (0, 0, 0, 0)
		// expected output (-1, 2, -3, 0)
		Vector f = new Vector(0, 0, 0);
		Vector g = new Vector(1, -2, 3);
		
		System.out.println(f.subtract(g));
		
		// negation instead of subtraction
		// expected output (-1, 2, -3, 4)
		Tuple h = new Tuple(1, -2, 3, -4);
		System.out.println(h.negate());
		
		// scalar multiplication
		// expected output (3.5, -7, 10.5, -14)
		Tuple i = new Tuple(1, -2, 3, -4);
		System.out.println(i.scalarMultiply(3.5f));
		
		// scalar division
		// expected output (0.5, -1, 1.5, -2)
		Tuple j = new Tuple(1, -2, 3, -4);
		System.out.println(j.scalarDivide(2f));
		
		// magnitude 
		// expected value is 1 in all the following cases
		Vector k = new Vector(1, 0, 0);
		System.out.println(k.magnitude());
		Vector l = new Vector(0, 1, 0);
		System.out.println(l.magnitude());
		Tuple m = new Vector(0, 0, 1);
		System.out.println(m.magnitude());
		
		// expected value ~ Math.sqrt(14)
		Vector n = new Vector(1, 2, 3);
		System.out.println(n.magnitude());
		Vector o = new Vector(-1, -2, -3);
		System.out.println(o.magnitude());
		
		// normalization
		Vector p = new Vector(4, 0, 0);
		System.out.println(Vector.normalize(p));
		
		Vector q = new Vector(1, 2, 3);
		Vector r = Vector.normalize(q);
		System.out.println(r);
		
		// magnitude of normalized vector should be 1
		System.out.println("Magnitude of " + r + " is " + r.magnitude());
		
		// dot product
		// expected output 20
		Vector s = new Vector(1, 2, 3);
		Vector t = new Vector(2, 3, 4);
		System.out.println(Tuple.dot(s, t));
		
		// cross product
		// expected output (-1, 2, -1)
		System.out.println(Vector.cross(s, t));
		
		// expected output (1, -2, 1)
		System.out.println(Vector.cross(t, s));
	}
}
