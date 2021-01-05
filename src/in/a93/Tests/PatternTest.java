package in.a93.Tests;

import in.a93.StripePattern;
import in.a93.Save;
import in.a93.Sphere;
import in.a93.Vector;
import in.a93.World;
import in.a93.Camera;
import in.a93.Canvas;
import in.a93.Color;
import in.a93.Matrix;
import in.a93.Plane;
import in.a93.Point;

public class PatternTest {
	public static void test1() {
		System.out.println(StripePattern.WHITE);
		System.out.println(StripePattern.BLACK);
	}

	public static void test2() {
		StripePattern a = new StripePattern(StripePattern.BLACK, StripePattern.WHITE);
		System.out.println(a.getFirstColor());
		System.out.println(a.getSecondColor());
	}
	
//	public static void test3() {
//		RenderPattern a = new RenderPattern();
//		System.out.println(a.getStripeAt(new Point(0, 0, 0)));
//		System.out.println(a.getStripeAt(new Point(0, 1, 0)));
//		System.out.println(a.getStripeAt(new Point(0, 2, 0)));
//	}
//	
//	public static void test4() {
//		RenderPattern a = new RenderPattern();
//		System.out.println(a.getStripeAt(new Point(0, 0, 0)));
//		System.out.println(a.getStripeAt(new Point(0, 0, 1)));
//		System.out.println(a.getStripeAt(new Point(0, 2, 2)));
//	}
//	
//	public static void test5() {
//		RenderPattern a = new RenderPattern();
//		System.out.println(a.getStripeAt(new Point(0, 0, 0)));
//		System.out.println(a.getStripeAt(new Point(0.9f, 0, 0)));
//		System.out.println(a.getStripeAt(new Point(1, 0, 0)));
//		System.out.println(a.getStripeAt(new Point(-0.1f, 0, 0)));
//		System.out.println(a.getStripeAt(new Point(-1, 0, 0)));
//		System.out.println(a.getStripeAt(new Point(-1.1f, 0, 0)));
//	}
	
	public static void test6() {
		World w = new World();
		Plane p = new Plane();
		Plane p2 = new Plane();
		Sphere s = new Sphere();
		
		StripePattern pattern = new StripePattern(new Color(1, 0, 1), new Color(1, 1, 0));
		pattern.setTransform(pattern.getTransform().scale(0.5f, 0.5f, 0.5f));
		p.getMaterial().setPattern(new StripePattern(new Color(1, 0, 0), new Color(0, 1, 0)));
		p2.getMaterial().setPattern(new StripePattern(new Color(1, 0, 0), new Color(0, 1, 0)));
		s.getMaterial().setPattern(pattern);
		
		p2.setTransform(p2.getTransform().rotateX((float) Math.PI / 2).translate(0, 0, 5));
		s.setTransform(s.getTransform().rotateZ((float) Math.PI/3).translate(0, 1, 0));
		w.addObjects(p);
		w.addObjects(p2);
		w.addObjects(s);
		
		Camera camera = new Camera(180, 120, (float) Math.PI / 3);
		camera.setTransform(Matrix.multiply(camera.getTransform(), Matrix.getViewTransform(new Point(0, 1.8f, -5), new Point(0, 1, 0), new Vector(0, 1, 0))));
		Canvas canvas = camera.render(w);
		String ppmData = Canvas.canvasToPpm(canvas);
		
		Save.saveToDiskAsText("E:\\pattern1.ppm", ppmData);
	}
}
