package in.a93.Tests;

import in.a93.Camera;
import in.a93.Canvas;
import in.a93.Color;
import in.a93.Matrix;
import in.a93.Plane;
import in.a93.Point;
import in.a93.RingPattern;
import in.a93.Save;
import in.a93.Sphere;
import in.a93.Vector;
import in.a93.World;

public class RingPatternTest {
	public static void test1() {
		World w = new World();
		Plane p = new Plane();
		Plane p2 = new Plane();
		Sphere s = new Sphere();
		
		RingPattern pattern = new RingPattern(new Color(0, 1, 0), new Color(0, 0, 1));
//		pattern.setTransform(pattern.getTransform().scale(0.15f, 0.15f, 0.5f));
//		p.getMaterial().setPattern(new RingPattern(new Color(1, 0, 0), new Color(0, 0, 1)));
//		p2.getMaterial().setPattern(new RingPattern(new Color(1, 0, 0), new Color(0, 1, 0)));

		p.getMaterial().setPattern(pattern);
		p2.getMaterial().setPattern(pattern);
		s.getMaterial().setPattern(pattern);
		
		p2.setTransform(p2.getTransform().rotateX((float) Math.PI / 2).translate(0, 0, 5));
		s.setTransform(s.getTransform().rotateZ(-(float) Math.PI/3).translate(0, 1, 0));

		w.addObjects(p);
		w.addObjects(p2);
		w.addObjects(s);
		
		Camera camera = new Camera(180, 120, (float) Math.PI / 3);
		camera.setTransform(Matrix.multiply(camera.getTransform(), Matrix.getViewTransform(new Point(0, 1.8f, -5), new Point(0, 1, 0), new Vector(0, 1, 0))));
		Canvas canvas = camera.render(w, 0);
		String ppmData = Canvas.canvasToPpm(canvas);
		
		Save.saveToDiskAsText("E:\\pattern3.ppm", ppmData);
	}
}
