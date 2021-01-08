package in.a93.Demos;

import in.a93.*;

public class CubeDemo {
	public void drawScene(String absolutePath) {
		World w = new World();
		
		Plane p = new Plane();
		Plane p2 = new Plane();
		Cube c1 = new Cube();

		c1.getMaterial().setShininess(300);
		c1.getMaterial().setDiffuse(0);
		c1.getMaterial().setAmbient(0.1f);
		c1.getMaterial().setSpecular(1f);

		c1.getMaterial().setPattern(new RingPattern(new Color(1, 1, 1), new Color(1, 0, 0)));
		c1.getMaterial().setTransparency(0.9f);
		c1.getMaterial().getPattern().setTransform(Matrix.getIdentityMatrix(4, 4).scale(0.25f, 0.25f, 0.25f).rotateY((float) Math.PI / 18));
		c1.setTransform(c1.getTransform().translate(0, 1, 0));
		
		p2.setTransform(p2.getTransform().rotateX((float) Math.PI / 2).translate(0, 0, 5));
		p2.getMaterial().setReflective(0);
		p2.getMaterial().setSpecular(0);
		w.addObjects(c1);
		w.addObjects(p);
		w.addObjects(p2);
		
		Camera camera = new Camera(720, 480, 1.05f);
		camera.setTransform(camera.getTransform().rotateY(-(float) Math.PI / 18));
		camera.setTransform(Matrix.multiply(camera.getTransform(), Matrix.getViewTransform(new Point(-0.25f, 3, -5), new Point(0, 1, 0), new Vector(0, 1, 0))));
		Canvas canvas = camera.render(w, 5);
		
		String ppmData = Canvas.canvasToPpm(canvas);
		Save.saveToDiskAsText(absolutePath, ppmData);
	}
	
}
