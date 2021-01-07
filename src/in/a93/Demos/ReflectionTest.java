package in.a93.Demos;

import in.a93.*;

public class ReflectionTest {
	private static Color wallColor = new Color(1, 0.9f, 0.9f);
	
	public void drawSpheresOnPlane(String absolutePath) {
		World w = new World();
		Plane p = new Plane();
		Sphere s = new Sphere();
		
		p.getMaterial().setReflective(1);
		p.getMaterial().setColor(wallColor);
		p.getMaterial().setPattern(new CheckerPattern(new Color(0.4f, 0.4f, 0.4f), new Color(0, 0, 0)));
		
		s.getMaterial().setReflective(1);
		s.getMaterial().setSpecular(1);
		s.getMaterial().setDiffuse(0);
		s.getMaterial().setColor(new Color(0.8f, 0.01f, 0.1f));
		s.setTransform(s.getTransform().rotateZ(-(float) Math.PI / 3).translate(0, 1, 0));
		
		w.addObjects(p);
		w.addObjects(s);
		
		Camera c = new Camera(720, 480, (float) Math.PI / 3);
		c.setTransform(Matrix.multiply(c.getTransform(), Matrix.getViewTransform(new Point(0, 1.5f, -5), new Point(0, 1, 0), new Vector(0, 1, 0))));
		Canvas canvas = c.render(w, 4);
		
		
		String ppmData = Canvas.canvasToPpm(canvas);
		Save.saveToDiskAsText(absolutePath, ppmData);
	}
}