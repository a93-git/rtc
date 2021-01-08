package in.a93.Demos;

import in.a93.Camera;
import in.a93.Matrix;
import in.a93.*;

public class Ch11Cover {
	public void drawCh11Cover(String absolutePath) {
		// Constants
		Material wallMaterial = new Material();
		wallMaterial.setPattern(new StripePattern(new Color(0.45f, 0.45f, 0.45f), new Color(0.55f, 0.55f, 0.55f)));
		wallMaterial.getPattern().setTransform(wallMaterial.getPattern().getTransform().scale(0.25f, 0.25f, 0.25f).rotateY(1.5708f));
		wallMaterial.setAmbient(0);
		wallMaterial.setSpecular(0);
		wallMaterial.setReflective(0.3f);
		wallMaterial.setDiffuse(0.4f);
		
		Plane floor = new Plane();
		floor.setTransform(floor.getTransform().rotateY(0.31415f));
		floor.getMaterial().setPattern(new CheckerPattern(new Color(0.35f, 0.35f, 0.35f), new Color(0.65f, 0.65f, 0.65f)));
		floor.getMaterial().setSpecular(0);
		floor.getMaterial().setReflective(0.4f);
		
		Plane ceiling = new Plane();
		ceiling.setTransform(ceiling.getTransform().translate(0, 5, 0));
		ceiling.getMaterial().setColor(new Color(0.8f, 0.8f, 0.8f));
		ceiling.getMaterial().setAmbient(0.3f);
		ceiling.getMaterial().setSpecular(0);
		
		Plane wallW = new Plane();
		wallW.setTransform(wallW.getTransform().rotateY(1.5708f).rotateZ(1.5708f).translate(-5, 0, 0));
		wallW.setMaterial(wallMaterial);

		Plane wallE = new Plane();
		wallE.setTransform(wallE.getTransform().rotateY(1.5708f).rotateZ(1.5708f).translate(5, 0, 0));
		wallE.setMaterial(wallMaterial);
		
		Plane wallN = new Plane();
		wallN.setTransform(wallN.getTransform().rotateX(1.5708f).translate(0, 0, 5));
		wallN.setMaterial(wallMaterial);
		
		Plane wallS = new Plane();
		wallS.setTransform(wallS.getTransform().rotateX(1.5708f).translate(0, 0, -5));
		wallS.setMaterial(wallMaterial);
		
		Sphere bb1 = new Sphere();
		bb1.setTransform(bb1.getTransform().scale(0.4f, 0.4f, 0.4f).translate(4.6f, 0.4f, 1));
		bb1.getMaterial().setColor(new Color(0.8f, 0.5f, 0.3f));
		bb1.getMaterial().setShininess(50);
		
		Sphere bb2 = new Sphere();
		bb2.setTransform(bb2.getTransform().scale(0.3f, 0.3f, 0.3f).translate(4.7f, 0.3f, 0.4f));
		bb2.getMaterial().setColor(new Color(0.9f, 0.4f, 0.5f));
		bb2.getMaterial().setShininess(50);
		
		Sphere bb3 = new Sphere();
		bb3.setTransform(bb3.getTransform().scale(0.5f, 0.5f, 0.5f).translate(-1, 0.5f, 4.5f));
		bb3.getMaterial().setColor(new Color(0.4f, 0.9f, 0.6f));
		bb3.getMaterial().setShininess(50);
		
		Sphere bb4 = new Sphere();
		bb4.setTransform(bb4.getTransform().scale(0.3f, 0.3f, 0.3f).translate(-1.7f, 0.3f, 4.7f));
		bb4.getMaterial().setColor(new Color(0.4f, 0.6f, 0.9f));
		bb4.getMaterial().setShininess(50);
		
		Sphere fb1 = new Sphere();
		fb1.setTransform(fb1.getTransform().translate(-0.6f, 1, 0.6f));
		fb1.getMaterial().setColor(new Color(1, 0.3f, 0.2f));
		fb1.getMaterial().setSpecular(0.4f);
		fb1.getMaterial().setShininess(5);
		
		Sphere fb2 = new Sphere();
		fb2.setTransform(fb2.getTransform().scale(0.7f, 0.7f, 0.7f).translate(0.6f, 0.7f, -0.6f));
		fb2.getMaterial().setColor(new Color(0, 0, 0.2f));
		fb2.getMaterial().setSpecular(0.9f);
		fb2.getMaterial().setShininess(300);
		fb2.getMaterial().setAmbient(0);
		fb2.getMaterial().setDiffuse(0.4f);
		fb2.getMaterial().setReflective(0.9f);
		fb2.getMaterial().setTransparency(0.9f);
		fb2.getMaterial().setRefractiveIndex(1.5f);
		
		Sphere fb3 = new Sphere();
		fb3.setTransform(fb3.getTransform().scale(0.5f, 0.5f, 0.5f).translate(-0.7f, 0.5f, -0.8f));
		fb3.getMaterial().setColor(new Color(0, 0.2f, 0));
		fb3.getMaterial().setSpecular(0.9f);
		fb3.getMaterial().setShininess(300);
		fb3.getMaterial().setAmbient(0);
		fb3.getMaterial().setDiffuse(0.4f);
		fb3.getMaterial().setReflective(0.9f);
		fb3.getMaterial().setTransparency(0.9f);
		fb3.getMaterial().setRefractiveIndex(1.5f);
		
		Light light = new Light(new Point(-4.9f, 4.9f, -1), new Color(1, 1, 1));
		
		World w = new World();
		w.addObjects(fb3);
		w.addObjects(fb2);
		w.addObjects(fb1);
		w.addObjects(bb4);
		w.addObjects(bb3);
		w.addObjects(bb2);
		w.addObjects(bb1);
		w.addObjects(wallS);
		w.addObjects(wallN);
		w.addObjects(wallE);
		w.addObjects(wallW);
		w.addObjects(ceiling);
		w.addObjects(floor);
		
		w.setLight(0, light);

		Camera camera = new Camera(720, 480, 1.152f);
		camera.setTransform(Matrix.multiply(camera.getTransform(), Matrix.getViewTransform(new Point(-2.6f, 1.5f, -3.9f), new Point(-0.6f, 1, -0.8f), new Vector(0, 1, 0))));
		
		Canvas canvas = camera.render(w, 5);
		String ppmData = Canvas.canvasToPpm(canvas);
		
		Save.saveToDiskAsText(absolutePath, ppmData);
		
	}
}
