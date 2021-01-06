package in.a93.Demos;

import in.a93.Camera;
import in.a93.Canvas;
import in.a93.Color;
import in.a93.Light;
import in.a93.Material;
import in.a93.Matrix;
import in.a93.Point;
import in.a93.Save;
import in.a93.Sphere;
import in.a93.Vector;
import in.a93.World;

public class MultipleSpheres {
	public static void drawWorld() {
		World world = new World();
		
		Sphere floor = new Sphere();
		floor.setTransform(floor.getTransform().scale(10, 0.01f, 10));
		floor.setMaterial(new Material());
		floor.getMaterial().setColor(new Color(1, 0.9f, 0.9f));
		floor.getMaterial().setSpecular(0);

		Sphere left_wall = new Sphere();
		left_wall.setTransform(left_wall.getTransform().scale(10, 0.01f, 10).rotateX((float) Math.PI / 2).rotateY((float) (-1 * Math.PI / 4)).translate(0, 0, 5));
		left_wall.setMaterial(floor.getMaterial());
		
		Sphere right_wall = new Sphere();
		right_wall.setTransform(right_wall.getTransform().scale(10, 0.01f, 10).rotateX((float) Math.PI / 2).rotateY((float) Math.PI / 4).translate(0, 0, 5));
		right_wall.setMaterial(floor.getMaterial());
		
		Sphere middle = new Sphere();
		middle.setTransform(middle.getTransform().translate(-0.5f, 1, 0.5f));
		middle.setMaterial(new Material());
		middle.getMaterial().setColor(new Color(0.1f, 1, 0.5f));
		middle.getMaterial().setDiffuse(0.7f);
		middle.getMaterial().setSpecular(0.3f);
		
		Sphere right = new Sphere();
		right.setTransform(right.getTransform().scale(0.5f, 0.5f, 0.5f).translate(1.5f, 0.5f, -0.5f));
		right.setMaterial(new Material());
		right.getMaterial().setColor(new Color(0.5f, 1, 0.1f));
		right.getMaterial().setDiffuse(0.7f);
		right.getMaterial().setSpecular(0.3f);
		
		Sphere left = new Sphere();
		left.setTransform(left.getTransform().scale(0.33f, 0.33f, 0.33f).translate(-1.5f, 0.33f, -0.75f));
		left.setMaterial(new Material());
		left.getMaterial().setColor(new Color(1, 0.8f, 0.1f));
		left.getMaterial().setDiffuse(0.7f);
		left.getMaterial().setSpecular(0.3f);
		
		world.addObjects(floor);
		world.addObjects(left_wall);
		world.addObjects(right_wall);
		world.addObjects(middle);
		world.addObjects(right);
		world.addObjects(left);
		
		world.setLight(0, new Light(new Point(-10, 10, -10), new Color(1, 1, 1)));
		
		Camera camera = new Camera(100, 50, (float) Math.PI / 3);
		camera.setTransform(Matrix.multiply(camera.getTransform(), Matrix.getViewTransform(new Point(0, 1.5f, -5), new Point(0, 1, 0), new Vector(0, 1, 0))));
		
		Canvas canvas = camera.render(world, 0);
		
		String ppmData = Canvas.canvasToPpm(canvas);
		Save.saveToDiskAsText("E:\\sphere2.ppm", ppmData);
	}
}
