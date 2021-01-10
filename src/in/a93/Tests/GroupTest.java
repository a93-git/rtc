package in.a93.Tests;

import in.a93.*;

public class GroupTest {
	public static void test1() {
		Group g = new Group();
		System.out.println("Group transformation matrix is: " + g.getTransform());
		System.out.println("Children count in group: " + (g.getChildren() == null ? 0 : g.getChildren().size()));
	}
	
	public static void test2() {
		Plane s = new Plane();
		System.out.println("Shape parent is: " + s.getParent());
	}
	
	public static void test3() {
		Plane s = new Plane();
		Group g = new Group();
		
		g.addChild(s);
		
		System.out.println("Group g is empty: " + (g.getChildren() == null ? true : false));
		System.out.println("Group g contains: " + (g.getChildren() == null ? "-" : g.getChildren().toString()));
		System.out.println("Paren of shape is: " + (s.getParent() == null ? "-" : s.getParent()));
	}
	
	public static void test4() {
		Group g = new Group();
		Ray r = new Ray(new Point(0, 0, 0), new Vector(0, 0, 1));
		
		Intersection[] xs = g.intersect(r);
		System.out.println("Intersection count: " + (xs == null ? 0 : xs.length));
	}
	
	public static void test5() {
		Group g = new Group();
		Sphere s1 = new Sphere();
		Sphere s2 = new Sphere();
		Sphere s3 = new Sphere();
		
		s2.setTransform(s2.getTransform().translate(0, 0, -3));
		s3.setTransform(s3.getTransform().translate(5, 0, 0));
		
		g.addChild(s1);
		g.addChild(s2);
		g.addChild(s3);
		
		Ray r = new Ray(new Point(0, 0, -5), new Vector(0, 0, 1));
		
		Intersection[] xs = g.intersect(r);
		
		System.out.println("Intersection count is: " + (xs == null ? 0 : xs.length));
		if (!(xs == null)) {
			for (Intersection i : xs) {
				System.out.println(i.getT() + " " + i.getObject().getUuid());
			}
		}
	}
	
	public static void test6() {
		Group g = new Group();
		Sphere s = new Sphere();
		
		g.setTransform(g.getTransform().scale(2, 2, 2));
		s.setTransform(s.getTransform().translate(5, 0, 0));
		
		g.addChild(s);
		
		Ray r = new Ray(new Point(10, 0, 10), new Vector(0, 0, 1));
		
		Intersection[] xs = g.intersect(r);
		System.out.println("Intersection count: " + (xs == null ? 0 : xs.length));
	}
	
	public static void test7() {
		Group g1 = new Group();
		g1.setTransform(g1.getTransform().rotateY((float) Math.PI / 2));
		
		Group g2 = new Group();
		g2.setTransform(g2.getTransform().scale(2, 2, 2));
		
		g1.addChild(g2);
		
		Sphere s = new Sphere();
		s.setTransform(s.getTransform().translate(5, 0, 0));
		
		g2.addChild(s);
		
		Point p = s.worldToObject(new Point(-2, 0, -10));
		System.out.println("WorldToObject point is: " + p);
		
	}
	
	public static void test8() {
		Group g1 = new Group();
		g1.setTransform(g1.getTransform().rotateY((float) Math.PI / 2));
		
		Group g2 = new Group();
		g2.setTransform(g2.getTransform().scale(1, 2, 3));
		
		g1.addChild(g2);
		
		Sphere s = new Sphere();
		s.setTransform(s.getTransform().translate(5, 0, 0));
		
		g2.addChild(s);
		
		Vector n = s.normalToWorld(new Vector((float) Math.sqrt(3) / 3, (float) Math.sqrt(3) / 3, (float) Math.sqrt(3) / 3));
		System.out.println("NormalToWorld vector is: " + n);
		
	}
	
	public static void test9() {
		Group g1 = new Group();
		g1.setTransform(g1.getTransform().rotateY((float) Math.PI / 2));
		
		Group g2 = new Group();
		g2.setTransform(g2.getTransform().scale(1, 2, 3));
		
		g1.addChild(g2);
		
		Sphere s = new Sphere();
		s.setTransform(s.getTransform().translate(5, 0, 0));
		
		g2.addChild(s);
		
		Vector n = s.normalAt(new Point(1.7321f, 1.1547f, -5.5774f));
		System.out.println("NormalToWorld vector is: " + n);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
