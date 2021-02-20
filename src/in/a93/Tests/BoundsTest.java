package in.a93.Tests;

import in.a93.*;

public class BoundsTest {
	public static void test1() {
		Sphere s = new Sphere();
		s.setTransform(s.getTransform().scale(2, 2, 2).translate(2, 5, -3));
		Cylinder c = new Cylinder();
		c.setMin(-2);
		c.setMax(2);
		c.setTransform(c.getTransform().scale(0.5f, 1, 0.5f).translate(-4, -1, 4));
		Group g = new Group();
		g.addChild(s);
		g.addChild(c);
		
		Bounds boundingBox = g.parentSpaceBounds();
		
		System.out.println("Min of bounding box is: " + boundingBox.getMinExtent());
		System.out.println("Max of bounding box is: " + boundingBox.getMaxExtent());
	}
	
	public static void test2() {
		Bounds box = new Bounds(new Point(-1, -1, -1), new Point(1, 1, 1));
		Vector[] directions = new Vector[13];
		Point[] origins = new Point[13];
		
		directions[0] = new Vector(-1, 0, 0); 
		directions[1] = new Vector(1, 0, 0); 
		directions[2] = new Vector(0, -1, 0); 
		directions[3] = new Vector(0, 1, 0); 
		directions[4] = new Vector(0, 0, -1); 
		directions[5] = new Vector(0, 0, 1); 
		directions[6] = new Vector(0, 0, 1); 
		directions[7] = new Vector(2, 4, 6); 
		directions[8] = new Vector(6, 2, 4); 
		directions[9] = new Vector(4, 6, 2); 
		directions[10] = new Vector(0, 0, -1); 
		directions[11] = new Vector(0, -1, 0); 
		directions[12] = new Vector(-1, 0, 0);
		
		origins[0] = new Point(5, 0.5f, 0); 
		origins[1] = new Point(-5, 0.5f, 0);
		origins[2] = new Point(0.5f, 5, 0); 
		origins[3] = new Point(0.5f, -5, 0);
		origins[4] = new Point(0.5f, 0, 5); 
		origins[5] = new Point(0.5f, 0, -5);
		origins[6] = new Point(0, 0.5f, 0);
		origins[7] = new Point(-2, 0, 0);
		origins[8] = new Point(0, -2, 0);
		origins[9] = new Point(0, 0, -2);
		origins[10] = new Point(2, 0, 2); 
		origins[11] = new Point(0, 2, 2); 
		origins[12] = new Point(2, 2, 0);
		
		for (int i = 0; i < directions.length; i++) {
			Vector normalizedDirection = Vector.normalize(directions[i]);
			Ray r = new Ray(origins[i], normalizedDirection);
			System.out.println("Ray intersects the box: " + (box.rayIntersects(r) == null ? "false" : "true"));
		}

	}
	
	public static void test3() {
		Bounds box = new Bounds(new Point(5, -2, 0), new Point(11, 4, 7));
		Vector[] directions = new Vector[13];
		Point[] origins = new Point[13];
		
		directions[0] = new Vector(-1, 0, 0); 
		directions[1] = new Vector(1, 0, 0); 
		directions[2] = new Vector(0, -1, 0); 
		directions[3] = new Vector(0, 1, 0); 
		directions[4] = new Vector(0, 0, -1); 
		directions[5] = new Vector(0, 0, 1); 
		directions[6] = new Vector(0, 0, 1); 
		directions[7] = new Vector(2, 4, 6); 
		directions[8] = new Vector(6, 2, 4); 
		directions[9] = new Vector(4, 6, 2); 
		directions[10] = new Vector(0, 0, -1); 
		directions[11] = new Vector(0, -1, 0); 
		directions[12] = new Vector(-1, 0, 0);
		
		origins[0] = new Point(15, 1, 2); 
		origins[1] = new Point(-5, -1, 4);
		origins[2] = new Point(7, 6, 5); 
		origins[3] = new Point(9, -5, 6);
		origins[4] = new Point(8, 2, 12); 
		origins[5] = new Point(6, 0, -5);
		origins[6] = new Point(8, 1, 3.5f);
		origins[7] = new Point(9, -1, -8);
		origins[8] = new Point(8, 3, -4);
		origins[9] = new Point(9, -1, -2);
		origins[10] = new Point(4, 0, 9); 
		origins[11] = new Point(8, 6, -1); 
		origins[12] = new Point(12, 5, 4);
		
		for (int i = 0; i < directions.length; i++) {
			Vector normalizedDirection = Vector.normalize(directions[i]);
			Ray r = new Ray(origins[i], normalizedDirection);
			System.out.println("Ray intersects the box: " + (box.rayIntersects(r) == null ? "false" : "true"));
		}
		
	}
}
