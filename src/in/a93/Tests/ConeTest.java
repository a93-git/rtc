package in.a93.Tests;

import in.a93.*;

public class ConeTest {
	public static void test1(String filePath) {
		Cone cone = new Cone();
		World w = new World();
		Plane p = new Plane();
		Plane p2 = new Plane();
//		Light l = new Light(new Point(10, -10, 10), new Color(1, 1, 1));
		cone.getMaterial().setColor(new Color(1, 0, 0));
		cone.getMaterial().setPattern(new RingPattern(new Color(0.8f, 0.3f, 0.4f), new Color(0.3f, 0.5f, 0.4f)));
		cone.getMaterial().getPattern().setTransform(Matrix.getIdentityMatrix(4, 4).scale(0.25f, 0.25f, 0.25f));
		cone.setMax(1);
		cone.setMin(-1);
		cone.setTransform(cone.getTransform().rotateX((float) Math.PI / 4).rotateY((float) Math.PI / 3).translate(0, 1, 0));
		cone.setCapped(true);
		
		p2.setTransform(p2.getTransform().rotateX((float) Math.PI / 2).translate(0, 0, 5));
		p2.getMaterial().setSpecular(0);
		w.addObjects(cone);
//		w.addLight(l);
		w.addObjects(p);
		w.addObjects(p2);
		
		Camera camera = new Camera(360, 240, 1.152f);
		camera.setTransform(Matrix.multiply(camera.getTransform(), Matrix.getViewTransform(new Point(0, 1.5f, -5), new Point(-0.6f, 1, -0.8f), new Vector(0, 1, 0))));
		Canvas canvas = camera.render(w, 5);
		String ppmData = Canvas.canvasToPpm(canvas);
		
		Save.saveToDiskAsText(filePath, ppmData);
	}
	
	public static void test2() {
		Cone c = new Cone();
		
		Vector d = Vector.normalize(new Vector(0, 0, 1));
		Vector d2 = Vector.normalize(new Vector(1, 1, 1));
		Vector d3 = Vector.normalize(new Vector(-0.5f, -1, 1));
		
		Ray r = new Ray(new Point(0, 0, -5), d);
		Ray r2 = new Ray(new Point(0, 0, -5), d2);
		Ray r3 = new Ray(new Point(1, 1, -5), d3);
		
		Intersection[] xs = c.intersect(r);
		Intersection[] xs2 = c.intersect(r2);
		Intersection[] xs3 = c.intersect(r3);
		
		System.out.println("Intersection count: " + (xs == null ? 0 : xs.length));
		System.out.println("Intersection count: " + (xs2 == null ? 0 : xs2.length));
		System.out.println("Intersection 1: " + (xs2 == null ? "-" : xs2[0].getT()) + " Intersection 2: " + (xs2 == null ? "-" : xs2[1].getT()));
		System.out.println("Intersection count: " + (xs3 == null ? 0 : xs3.length));
		System.out.println("Intersection 1: " + (xs3 == null ? "-" : xs3[0].getT()) + " Intersection 2: " + (xs3 == null ? "-" : xs3[1].getT()));
		
		
	}
	
	public static void test3() {
		Cone c = new Cone();
		Vector d = Vector.normalize(new Vector(0, 1, 1));
		Ray r = new Ray(new Point(0, 0, -1), d);
		Intersection[] xs = c.intersect(r);
		
		System.out.println("Intersection count: " + (xs == null ? 0 : xs.length));
		System.out.println("Intersection 1: " + (xs == null ? "-" : xs[0].getT()));
		
	}
	
	public static void test4() {
		Cone c = new Cone();
		c.setMin(-0.5f);
		c.setMax(0.5f);
		c.setCapped(true);
		
		Vector d = new Vector(0, 1, 0);
		Vector d2 = new Vector(0, 1, 1);
		Vector d3 = new Vector(0, 1, 0);
		
		Ray r = new Ray(new Point(0, 0, -5), d);
		Ray r2 = new Ray(new Point(0, 0, -0.25f), d2);
		Ray r3 = new Ray(new Point(0, 0, -0.25f), d3);
		
//		Intersection[] xs = c.intersect(r);
		Intersection[] xs2 = c.intersect(r2);
//		Intersection[] xs3 = c.intersect(r3);
		
//		System.out.println("Intersection count: " + (xs == null ? 0 : xs.length));
		System.out.println("Intersection count: " + (xs2 == null ? 0 : xs2.length));
//		System.out.println("Intersection count: " + (xs3 == null ? 0 : xs3.length));
	}
	
	public static void test5() {
		
	}
}
