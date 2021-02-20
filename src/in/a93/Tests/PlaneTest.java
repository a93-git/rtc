package in.a93.Tests;

import in.a93.*;

public class PlaneTest {
	public static void test1() {
		Plane p = new Plane();
		
		Vector n1 = p.normalAt(new Point(0, 0, 0), null);
		Vector n2 = p.normalAt(new Point(10, 0, -10), null);
		Vector n3 = p.normalAt(new Point(-5, 0, 150), null);
		
		System.out.println(n1);
		System.out.println(n2);
		System.out.println(n3);
	}

	public static void test2() {
		Plane p = new Plane();
		Ray r = new Ray(new Point(0, 10, 0), new Vector(0, 0, 1));
		
		Intersection[] xs = p.intersect(r);
		
		if (xs == null) System.out.println("Success");
		else System.out.println("Faliure");
	}
	
	public static void test3() {
		Plane p = new Plane();
		Ray r = new Ray(new Point(0, 0, 0), new Vector(0, 0, 1));
		
		Intersection[] xs = p.intersect(r);
		
		if (xs == null) System.out.println("Success");
		else System.out.println("Faliure");
	}
	
	public static void test4() {
		Plane p = new Plane();
		Ray r = new Ray(new Point(0, 1, 0), new Vector(0, -1, 0));
		
		Intersection[] xs = p.intersect(r);
		
		if (xs == null) System.out.println("Failure");
		else {
			System.out.println(xs.length == 1);
			System.out.println(xs[0].getT() == 1);
			System.out.println(xs[0].getObject());
		}
	}
	
	public static void test5() {
		Plane p = new Plane();
		Plane p2 = new Plane();
		Plane p3 = new Plane();
		p.getMaterial().setColor(new Color(1, 0, 0));
		p2.getMaterial().setColor(new Color(0, 1, 0));
		p3.getMaterial().setColor(new Color(0, 0, 1));
		Light light = new Light(new Point(-10, 10, -10), new Color(1, 1, 1));
		
		p2.setTransform(p2.getTransform().rotateX((float) Math.PI / -2).translate(0, 0, 5));
		
		p2.setTransform(p2.getTransform().rotateZ((float) Math.PI / 2).translate(5, 0, 0));
		
		
		World w = new World();
		w.addObjects(p);
		w.addObjects(p2);
		w.addObjects(p3);
		w.addLight(light);
		
		w.setLight(0, new Light(new Point(-10, 10, -10), new Color(1, 1, 1)));
		
		Camera c = new Camera(180, 120, (float) Math.PI / 3);
		c.setTransform(Matrix.multiply(c.getTransform(), Matrix.getViewTransform(new Point(0, 1.5f, -5), new Point(0, 1, 0), new Vector(0, 1, 0))));
		Canvas canvas = c.render(w, 0);
		
		String ppmData = Canvas.canvasToPpm(canvas);
		Save.saveToDiskAsText("E:\\Plane1.ppm", ppmData);
		
	}
}
