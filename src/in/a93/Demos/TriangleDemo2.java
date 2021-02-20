package in.a93.Demos;

import in.a93.*;

import java.nio.file.Path;

public class TriangleDemo2 {
	public static void drawWorld(String absolutePath) {
		Path filepath = Path.of("C:\\Users\\win10\\Desktop\\teapot-low.obj");
		World w = new World();
		
		
		ObjFile o = new ObjFile(filepath);
		System.out.println("Parsing obj file");
		o.parseObjFile();
		
		System.out.println("Done parsing obj file");
		
		Group g = o.objectToGroup();
		
		System.out.println("Total count of groups is: " + g.getChildren().size());
		for (Group i : o.getGroups()) {
			System.out.println("Total count of triangles for this group is: " + i.getChildren().size());
		}
//		
		g.setTransform(g.getTransform().rotateX((float) Math.PI / -2).translate(0, -0.3f, 0));
		g.getMaterial().setColor(new Color(0.75f, 0.6f, 0.2f));
		
		w.addObjects(g);
		

		Plane floor = new Plane();
		floor.getMaterial().setPattern(new CheckerPattern(new Color(0, 1, 1), new Color(1, 0.6f, 0.6f)));
		w.addObjects(floor);
		
		Plane backWall = new Plane();
		backWall.getMaterial().setPattern(new CheckerPattern(new Color(1, 0, 1), new Color(0.6f, 0.6f, 1)));
		backWall.setTransform(backWall.getTransform().rotateX((float) Math.PI / 2).rotateZ((float) Math.PI / 15).translate(0, 0, 15));
		w.addObjects(backWall);
		
		w.getLight().get(0).setPosition(new Point(-32, 21, -18));
//		Cube cube = new Cube();
//		Bounds boundingBox = w.getObjects().get(0).parentSpaceBounds(); 
//		float x = Math.abs(boundingBox.getMaxExtent().getX() - boundingBox.getMinExtent().getX());
//		float y = Math.abs(boundingBox.getMaxExtent().getY() - boundingBox.getMinExtent().getY());
//		float z = Math.abs(boundingBox.getMaxExtent().getZ() - boundingBox.getMinExtent().getZ());
//
//		System.out.println(x + ", " + y + ", " + z);
//		
//		cube.setTransform(cube.getTransform().scale(x, y, z));
//		cube.getMaterial().setColor(new Color(1, 1, 0));
//		cube.getMaterial().setAmbient(0.1f);
//		cube.getMaterial().setDiffuse(0.2f);
//		cube.getMaterial().setSpecular(0);
//		cube.getMaterial().setShininess(0);
//		cube.getMaterial().setReflective(0);
//		cube.getMaterial().setTransparency(0.8f);
//		cube.getMaterial().setRefractiveIndex(1);
	    
//		w.addObjects(cube);
		
		Camera camera = new Camera(360, 240, (float) Math.PI / 1.4f);
		camera.setTransform(Matrix.multiply(camera.getTransform(), Matrix.getViewTransform(new Point(0, 20, -20), new Point(0, 1, 0), new Vector(0, 1, 0))));
		
		
		System.out.println("Rendering world");
		Canvas canvas = camera.render(w, 0);
		
		String ppmData = Canvas.canvasToPpm(canvas);
		Save.saveToDiskAsText(absolutePath, ppmData);
	}
}
