package in.a93.Tests;

import in.a93.*;

public class CylinderTest {
	public static void test1() {
		Cylinder c = new Cylinder();
		
		Vector d = Vector.normalize(new Vector(0, 1, 0));
		Vector d2 = Vector.normalize(new Vector(0, 1, 0));
		Vector d3 = Vector.normalize(new Vector(1, 1, 1));
		
		Ray r = new Ray(new Point(1, 0, 0), d);
		Ray r2 = new Ray(new Point(0, 0, 0), d2);
		Ray r3 = new Ray(new Point(0, 0, -5), d3);
		
		Intersection[] xs = c.intersect(r);
		Intersection[] xs2 = c.intersect(r2);
		Intersection[] xs3 = c.intersect(r3);
		
		System.out.println("Intersection count is: " + (xs == null ? 0 : xs.length));
		System.out.println("Intersection count is: " + (xs2 == null ? 0 : xs2.length));
		System.out.println("Intersection count is: " + (xs3 == null ? 0 : xs3.length));
	}

	public static void test2() {
		Cylinder c = new Cylinder();
		
		Vector d = Vector.normalize(new Vector(0, 0, 1));
		Vector d2 = Vector.normalize(new Vector(0, 0, 1));
		Vector d3 = Vector.normalize(new Vector(0.1f, 1, 1));
		
		Ray r = new Ray(new Point(1, 0, -5), d);
		Ray r2 = new Ray(new Point(0, 0, -5), d2);
		Ray r3 = new Ray(new Point(0.5f, 0, -5), d3);
		
		Intersection[] xs = c.intersect(r);
		Intersection[] xs2 = c.intersect(r2);
		Intersection[] xs3 = c.intersect(r3);

		System.out.println("Intersection count is: " + (xs == null ? 0 : xs.length)
				+ "\txs[0]: " + xs[0].getT() + " xs[1]: " + xs[1].getT());
		System.out.println("Intersection count is: " + (xs2 == null ? 0 : xs2.length)
				+ "\txs[0]: " + xs2[0].getT() + " xs[1]: " + xs2[1].getT());
		System.out.println("Intersection count is: " + (xs3 == null ? 0 : xs3.length)
				+ "\txs[0]: " + xs3[0].getT() + " xs[1]: " + xs3[1].getT());
	}
	
	public static void test3() {
		Cylinder c = new Cylinder();
		Vector n = c.normalAt(new Point(1, 0, 0));
		Vector n2 = c.normalAt(new Point(0, 5, -1));
		Vector n3 = c.normalAt(new Point(0, -2, 1));
		Vector n4 = c.normalAt(new Point(-1, 1, 0));
		
		System.out.println("n: " + n);
		System.out.println("n2: " + n2);
		System.out.println("n3: " + n3);
		System.out.println("n4: " + n4);
		
	}
	
	public static void test4(String filePath) {
		Cylinder cyl = new Cylinder();
		Plane p = new Plane();
		World w = new World();
		
		cyl.getMaterial().setPattern(new CheckerPattern(new Color(1, 1, 1), new Color(1, 0, 0)));
		cyl.getMaterial().getPattern().setTransform(Matrix.getIdentityMatrix(4, 4).scale(0.25f, 0.25f, 0.25f));
		
		w.addObjects(p);
		w.addObjects(cyl);
		
		Camera c = new Camera(360, 240, (float) Math.PI / 3);
		c.setTransform(Matrix.multiply(c.getTransform(), Matrix.getViewTransform(new Point(0, 1.75f, -5f), new Point(0, 1, 0), new Vector(0, 1, 0))));
		Canvas canvas = c.render(w, 5);
		
		String ppmData = Canvas.canvasToPpm(canvas);
		Save.saveToDiskAsText(filePath, ppmData);
	}
	
	public static void test5() {
		Cylinder c = new Cylinder();
		System.out.println("Min: " + c.getMin());
		System.out.println("Max: " + c.getMax());
	}
	
	public static void test6() {
		Cylinder c = new Cylinder();
		
		c.setMin(1);
		c.setMax(2);
		
		Vector d = new Vector(0.1f, 1, 0);
		Vector d2 = new Vector(0, 0, 1);
		
		Ray r = new Ray(new Point(0, 1.5f, 0), d);
		Ray r2 = new Ray(new Point(0, 3, -5), d2);
		Ray r3 = new Ray(new Point(0, 0, -5), d2);
		Ray r4 = new Ray(new Point(0, 2, -5), d2);
		Ray r5 = new Ray(new Point(0, 1, -5), d2);
		Ray r6 = new Ray(new Point(0, 1.5f, -2), d2);
		
		Intersection[] xs = c.intersect(r);
		Intersection[] xs2 = c.intersect(r2);
		Intersection[] xs3 = c.intersect(r3);
		Intersection[] xs4 = c.intersect(r4);
		Intersection[] xs5 = c.intersect(r5);
		Intersection[] xs6 = c.intersect(r6);
		
		System.out.println("Intersection count: " + (xs == null ? 0 : xs.length));
		System.out.println("Intersection count: " + (xs2 == null ? 0 : xs2.length));
		System.out.println("Intersection count: " + (xs3 == null ? 0 : xs3.length));
		System.out.println("Intersection count: " + (xs4 == null ? 0 : xs4.length));
		System.out.println("Intersection count: " + (xs5 == null ? 0 : xs5.length));
		System.out.println("Intersection count: " + (xs6 == null ? 0 : xs6.length));
	}
	
	public static void test7(String filePath) {
		Cylinder cyl = new Cylinder();
//		Plane p = new Plane();
		World w = new World();
		
		cyl.getMaterial().setPattern(new CheckerPattern(new Color(1, 1, 1), new Color(1, 0, 0)));
		cyl.setTransform(cyl.getTransform().translate(0, 1, 0).rotateX((float) Math.PI / 2));
		cyl.getMaterial().getPattern().setTransform(Matrix.getIdentityMatrix(4, 4).scale(0.25f, 0.25f, 0.25f));
		cyl.setMax(1);
		cyl.setMin(0);
//		w.addObjects(p);
		w.addObjects(cyl);
		
		Camera c = new Camera(360, 240, (float) Math.PI / 3);
		c.setTransform(Matrix.multiply(c.getTransform(), Matrix.getViewTransform(new Point(0, 1.75f, -5f), new Point(0, 1, 0), new Vector(0, 1, 0))));
		Canvas canvas = c.render(w, 5);
		
		String ppmData = Canvas.canvasToPpm(canvas);
		Save.saveToDiskAsText(filePath, ppmData);
	}
	
	public static void test8() {
		Cylinder cyl = new Cylinder();
		
		System.out.println("Is capped: " + cyl.isCapped());
	}
	
	public static void test9() {
		Cylinder cyl = new Cylinder();
		
		cyl.setMin(1);
		cyl.setMax(2);
		cyl.setCapped(true);
		
		Vector d = Vector.normalize(new Vector(0, -1, 0));
		Vector d2 = Vector.normalize(new Vector(0, -1, 2));
		Vector d3 = Vector.normalize(new Vector(0, -1, 1));
		Vector d4 = Vector.normalize(new Vector(0, 1, 2));
		Vector d5 = Vector.normalize(new Vector(0, 1, 1));
		
		Ray r = new Ray(new Point(0, 3, 0), d);
		Ray r2 = new Ray(new Point(0, 3, -2), d2);
		Ray r3 = new Ray(new Point(0, 4, -2), d3);
		Ray r4 = new Ray(new Point(0, 0, -2), d4);
		Ray r5 = new Ray(new Point(0, -1, -2), d5);
		
		Intersection[] xs = cyl.intersect(r);
		Intersection[] xs2 = cyl.intersect(r2);
		Intersection[] xs3 = cyl.intersect(r3);
		Intersection[] xs4 = cyl.intersect(r4);
		Intersection[] xs5 = cyl.intersect(r5);
		
		System.out.println("Intersection count: " + (xs == null ? 0 : xs.length));
		System.out.println("Intersection count: " + (xs2 == null ? 0 : xs2.length));
		System.out.println("Intersection count: " + (xs3 == null ? 0 : xs3.length));
		System.out.println("Intersection count: " + (xs4 == null ? 0 : xs4.length));
		System.out.println("Intersection count: " + (xs5 == null ? 0 : xs5.length));
	}
	
	public static void test10(String filePath) {
		Cylinder cyl = new Cylinder();
		Plane p = new Plane();
		World w = new World();
		
		cyl.getMaterial().setPattern(new RingPattern(new Color(1, 1, 1), new Color(1, 0, 0)));
		cyl.setTransform(cyl.getTransform().rotateX((float) Math.PI / 3).rotateY((float) Math.PI / 9).translate(0, 1, 0));
		cyl.getMaterial().getPattern().setTransform(Matrix.getIdentityMatrix(4, 4).scale(0.25f, 0.25f, 0.25f));
		cyl.setMax(2);
		cyl.setMin(0);
		cyl.setCapped(true);
		
		p.getMaterial().setReflective(0.5f);
		w.addObjects(p);
		w.addObjects(cyl);
		
		Camera c = new Camera(720, 480, (float) Math.PI / 3.75f);
		c.setTransform(Matrix.multiply(c.getTransform(), Matrix.getViewTransform(new Point(0, 1.5f, -5), new Point(0, 1, 0), new Vector(0, 1, 0))));
		Canvas canvas = c.render(w, 5);
		
		String ppmData = Canvas.canvasToPpm(canvas);
		Save.saveToDiskAsText(filePath, ppmData);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
