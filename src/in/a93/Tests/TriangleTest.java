package in.a93.Tests;

import in.a93.Triangle;
import in.a93.*;

public class TriangleTest {
	public void test1() {
		Triangle t = new Triangle(new Point(0, 1, 0), new Point(-1, 0, 0), new Point(1, 0, 0));
		System.out.println(t.getP1());
		System.out.println(t.getP2());
		System.out.println(t.getP3());
		System.out.println(t.getE1());
		System.out.println(t.getE2());
		System.out.println(t.getN());
		
	}
	
	public void test2() {
		Triangle t = new Triangle(new Point(0, 1, 0), new Point(-1, 0, 0), new Point(1, 0, 0));
		Ray r = new Ray(new Point(0, -1, -2), new Vector(0, 1, 0));
		
		Intersection[] xs = t.intersect(r);
		
		System.out.println("xs is empty?: " + ((xs == null) ? true : false));
		
		Ray r2 = new Ray(new Point(1, 1, -2), new Vector(0, 0, 1));
		Intersection[] xs2 = t.intersect(r2);
		
		System.out.println("xs2 is empty?: " + ((xs2 == null) ? true : false));
		
		Ray r3 = new Ray(new Point(-1, 1, -2), new Vector(0, 0, 1));
		Intersection[] xs3 = t.intersect(r3);
		
		System.out.println("xs3 is empty?: " + ((xs3 == null) ? true : false));
		
		Ray r4 = new Ray(new Point(0, -1, -2), new Vector(0, 0, 1));
		Intersection[] xs4 = t.intersect(r4);
		
		System.out.println("xs4 is empty?: " + ((xs4 == null) ? true : false));
		
	}
	
	public void test3() {
		Triangle t = new Triangle(new Point(0, 1, 0), new Point(-1, 0, 0), new Point(1, 0, 0));
		Ray r = new Ray(new Point(0, 0.5f, -2), new Vector(0, 0, 1));
		Intersection[] xs = t.intersect(r);
		
		System.out.println("xs is: " + ((xs == null ? "empty" : xs[0].getT())));
	}
}
