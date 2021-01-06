package in.a93.Demos;

import in.a93.Camera;
import in.a93.Canvas;
import in.a93.CheckerPattern;
import in.a93.Color;
import in.a93.GradientPattern;
import in.a93.Light;
import in.a93.Matrix;
import in.a93.Plane;
import in.a93.Point;
import in.a93.RingPattern;
import in.a93.Save;
import in.a93.Sphere;
import in.a93.StripePattern;
import in.a93.Vector;
import in.a93.World;

public class PatternsDemo {
	public static void drawWorld() {
		World w = new World();
		Plane p = new Plane();
		Plane p2 = new Plane();
		Plane p3 = new Plane();
		Plane p4 = new Plane();
		Plane p5 = new Plane();
		Sphere s = new Sphere();
		Sphere s2 = new Sphere();
		Sphere s3 = new Sphere();
		Light l = new Light(new Point(8, 0, -4), new Color(1, 1, 1));
		
		RingPattern ringPattern = new RingPattern(new Color(0, 0.1f, 0), new Color(0.5f, 0.4f, 0.1f));
		GradientPattern gradientPattern = new GradientPattern(new Color(0.5f, 0.1f, 0), new Color(0.4f, 0.2f, 0.3f));
		StripePattern stripePattern = new StripePattern(new Color(0, 0.1f, 0.3f), new Color(0.12f, 0.3f, 1));
		CheckerPattern checkerPattern = new CheckerPattern(new Color(0.22f, 0.7f, 0.08f), new Color(0.01f, 0.09f, 0.9f));
		//			pattern.setTransform(pattern.getTransform().scale(0.15f, 0.15f, 0.5f));
		
		p.getMaterial().setPattern(ringPattern);
		p.getMaterial().setSpecular(0);
		p.setTransform(p2.getTransform().translate(0, 0, 5));
		
		p2.getMaterial().setPattern(ringPattern);
		p2.getMaterial().setSpecular(0);
		p2.setTransform(p2.getTransform().rotateX((float) Math.PI / 2).translate(0, 0, 5));
		
		p3.getMaterial().setPattern(checkerPattern);
		p3.getMaterial().setSpecular(0);
		p3.setTransform(p3.getTransform().rotateZ((float) Math.PI / 2).translate(-12, 0, 0));
		
		p4.getMaterial().setPattern(stripePattern);
		p4.getMaterial().setSpecular(0);
		p4.setTransform(p4.getTransform().rotateZ((float) Math.PI / 2).translate(10, 0, 0));
		
		p5.getMaterial().setPattern(gradientPattern);
		p5.getMaterial().setSpecular(0);
		p5.setTransform(p5.getTransform().translate(0, 12, 0));
		
		s.setTransform(s.getTransform().rotateZ(-(float) Math.PI/3).translate(0, 1, 0));
		s.getMaterial().setPattern(gradientPattern);
		
		s2.setTransform(s2.getTransform().translate(-5, 1, 5));
		s2.getMaterial().setPattern(checkerPattern);
		
		s3.setTransform(s2.getTransform().rotateZ((float) Math.PI / 12).translate(2, 0, 0));
		s2.getMaterial().setPattern(checkerPattern);
		
		l.setPosition(new Point(8, 0, -4));
		
		w.addObjects(p);
		w.addObjects(p2);
		w.addObjects(p3);
		w.addObjects(p4);
		w.addObjects(p5);
		w.addObjects(s);
		w.addObjects(s2);
		w.addObjects(s3);
		w.addLight(l);
		
		Camera camera = new Camera(720, 480, (float) Math.PI / 1.75f);
		camera.setTransform(Matrix.multiply(camera.getTransform(), Matrix.getViewTransform(new Point(0, 1.8f, -5), new Point(0, 1, 0), new Vector(0, 1, 0))));
		camera.setTransform(camera.getTransform().rotateX((float) Math.PI / -12).translate(0, 0, -5));
		
		Canvas canvas = camera.render(w);
		String ppmData = Canvas.canvasToPpm(canvas);
		
		Save.saveToDiskAsText("E:\\patterns_demo.ppm", ppmData);
	}
}
