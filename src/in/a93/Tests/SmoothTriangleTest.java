package in.a93.Tests;

import in.a93.*;

public class SmoothTriangleTest {

	public static void test1() {
		Point p1 = new Point(0, 1, 0);
		Point p2 = new Point(-1, 0, 0);
		Point p3 = new Point(1, 0, 0);
		
		Vector n1 = new Vector(0, 1, 0);
		Vector n2 = new Vector(-1, 0, 0);
		Vector n3 = new Vector(1, 0, 0);
		
		SmoothTriangle st = new SmoothTriangle(p1, p2, p3, n1, n2, n3);
		System.out.println(st);
	}
	
	public static void test2() {
		Triangle t = new Triangle(new Point(0, 1, 0), new Point(-1, 0, 0), new Point(1, 0, 0));
		Intersection i = new Intersection(3.5f, t, 0.2f, 0.4f);
		
		System.out.println("U is: " + i.getU());
		System.out.println("V is: " + i.getV());
	}

	public static void test3() {
		Triangle t = new Triangle(new Point(0, 1, 0), new Point(-1, 0, 0), new Point(1, 0, 0));
		Ray r = new Ray(new Point(-0.2f, 0.3f, -2f), new Vector(0, 0, 1));
		Intersection[] i = t.intersect(r);
		
		for (Intersection j : i) {
			System.out.println("U is: " + j.getU());
			System.out.println("V is: " + j.getV());			
		}
	}

	public static void test4() {
		Point p1 = new Point(0, 1, 0);
		Point p2 = new Point(-1, 0, 0);
		Point p3 = new Point(1, 0, 0);
		
		Vector n1 = new Vector(0, 1, 0);
		Vector n2 = new Vector(-1, 0, 0);
		Vector n3 = new Vector(1, 0, 0);
		
		SmoothTriangle st = new SmoothTriangle(p1, p2, p3, n1, n2, n3);
		
		Intersection i = new Intersection(1, st, 0.45f, 0.25f);
		
		System.out.println("Normal for smooth triangle is: " + st.normalAt(new Point(0, 0, 0), i));
	}
	
	public static void test5() {
		Point p1 = new Point(0, 1, 0);
		Point p2 = new Point(-1, 0, 0);
		Point p3 = new Point(1, 0, 0);
		
		Vector n1 = new Vector(0, 1, 0);
		Vector n2 = new Vector(-1, 0, 0);
		Vector n3 = new Vector(1, 0, 0);
		
		SmoothTriangle st = new SmoothTriangle(p1, p2, p3, n1, n2, n3);
		
		Intersection i = new Intersection(1, st, 0.45f, 0.25f);
		Ray r = new Ray(new Point(-0.2f, 0.3f, -2f), new Vector(0, 0, 1));
		
		Intersection[] xs = Intersection.intersections(i);
		
		IntersectionCompute computedValue = new IntersectionCompute(i, r, xs);
		
		System.out.println("Normal vector is: " + computedValue.getNormalVector());
	}
}
