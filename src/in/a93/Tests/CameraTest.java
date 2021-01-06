package in.a93.Tests;

import in.a93.*;

public class CameraTest {
	public static void test1() {
		Camera c = new Camera(160, 120, (float) Math.PI / 2);
		
		System.out.println(c.getHsize());
		System.out.println(c.getVsize());
		System.out.println(c.getFieldOfView());
		System.out.println(c.getTransform());
	}

	public static void test2() {
		Camera c = new Camera(200, 125, (float) Math.PI / 2);
		
		System.out.println(c.getPixelSize());
	}
	
	public static void test3() {
		Camera c = new Camera(201, 101, (float) Math.PI / 2);
		
		System.out.println(c.getPixelSize());
	}

	public static void test4() {
		Camera c = new Camera(201, 101, (float) Math.PI / 2);
		Ray r = c.rayForPixel(100, 50);
		
		System.out.println(r);
	}
		
	public static void test5() {
		Camera c = new Camera(201, 101, (float) Math.PI / 2);
		Ray r = c.rayForPixel(0, 0);
		
		System.out.println(r);
	}
	
	public static void test6() {
		Camera c = new Camera(201, 101, (float) Math.PI / 2);
		Matrix transform = c.getTransform().translate(0, -2, 5).rotateY((float) Math.PI / 4);
		c.setTransform(transform);
		
		Ray r = c.rayForPixel(100, 50);
		
		System.out.println(r);
	}

	public static void test7() {
		World w = new World();
		Camera c = new Camera(11, 11, (float) Math.PI / 2);
		
		Point from = new Point(0, 0, -5);
		Point to = new Point();
		Vector up = new Vector(0, 1, 0);
		
		c.setTransform(Matrix.getViewTransform(from, to, up));
		
		Canvas image = c.render(w, 0);
		
		System.out.println(image.getColorAt(5, 5));
	}









}
