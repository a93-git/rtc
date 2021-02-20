package in.a93.Tests;

import in.a93.*;
import java.util.ArrayList;

public class CubeTest {
	public static void test1() {
		Cube c = new Cube();
		ArrayList<Vector> direction = new ArrayList<Vector>();
		ArrayList<Point> origin = new ArrayList<Point>();

		direction.add(new Vector(-1, 0, 0));
		direction.add(new Vector(1, 0, 0));
		direction.add(new Vector(0, -1, 0));
		direction.add(new Vector(0, 1, 0));
		direction.add(new Vector(0, 0, -1));
		direction.add(new Vector(0, 0, 1));
		direction.add(new Vector(0, 0, 1));

		origin.add(new Point(5, 0.5f, 0));
		origin.add(new Point(-5, 0.5f, 0));
		origin.add(new Point(0.5f, 5, 0));
		origin.add(new Point(0.5f, -5, 0));
		origin.add(new Point(0.5f, 0, 5));
		origin.add(new Point(0.5f, 0, -5));
		origin.add(new Point(0, 0.5f, 0));

		int[] t1 = new int[direction.size()];
		int[] t2 = new int[direction.size()];
		
		t1[0] = 4;	t2[0] = 6;
		t1[1] = 4;	t2[1] = 6;
		t1[2] = 4;	t2[2] = 6;
		t1[3] = 4;	t2[3] = 6;
		t1[4] = 4;	t2[4] = 6;
		t1[5] = 4;	t2[5] = 6;
		t1[6] = -1;	t2[6] = 1;

		for (int i = 0; i < direction.size(); i++) {
			Ray r = new Ray(origin.get(i), direction.get(i));
			Intersection[] xs = c.intersect(r);
			
			System.out.println("Count of intersections is 2: " + (xs.length == 2));
			System.out.println("xs[0].t: " + xs[0].getT() + " t1: " + t1[i] + "\txs[0] equals t1: " + (xs[0].getT() == t1[i]));
			System.out.println("xs[1].t: " + xs[1].getT() + " t2: " + t2[i] + "\txs[1] equals t2: " + (xs[1].getT() == t2[i]));
			
		}
	}

	public static void test2() {
		Cube c = new Cube();
		ArrayList<Vector> direction = new ArrayList<Vector>();
		ArrayList<Point> origin = new ArrayList<Point>();
		
		direction.add(new Vector(0.2673f, 0.5345f, 0.8018f));
		direction.add(new Vector(0.8018f, 0.2673f, 0.5345f));
		direction.add(new Vector(0.5345f, 0.8018f, 0.2673f));
		direction.add(new Vector(0, 0, -1));
		direction.add(new Vector(0, -1, 0));
		direction.add(new Vector(-1, 0, 0));
		
		origin.add(new Point(-2, 0, 0));
		origin.add(new Point(0, -2, 0));
		origin.add(new Point(0, 0, -2));
		origin.add(new Point(2, 0, 2));
		origin.add(new Point(0, 2, 2));
		origin.add(new Point(2, 2, 0));
		
		
		for (int i = 0; i < direction.size(); i++) {
			Ray r = new Ray(origin.get(i), direction.get(i));
			Intersection[] xs = c.intersect(r);
			
			System.out.println("Count of intersections is: " + ((xs == null) ? 0 : xs.length));
			
		}
	}
	
	public static void test3() {
		Cube c = new Cube();
		ArrayList<Vector> direction = new ArrayList<Vector>();
		ArrayList<Point> origin = new ArrayList<Point>();
		
		direction.add(new Vector(1, 0, 0));
		direction.add(new Vector(-1, 0, 0));
		
		origin.add(new Point(-1, -1, -1));
		origin.add(new Point(1, 1, 1));
		
		for (int i = 0; i < direction.size(); i++) {
			Vector normal = c.normalAt(origin.get(i), null);
			
			System.out.println("Normal at: " + origin.get(i) + " is: " + normal);
		}
	}
}
