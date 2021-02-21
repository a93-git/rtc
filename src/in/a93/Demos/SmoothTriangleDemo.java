package in.a93.Demos;

import java.nio.file.Path;
import in.a93.*;

public class SmoothTriangleDemo {
	public static void drawWorld(String absolutePath) {
		Path filePath = Path.of("C:\\Users\\win10\\Desktop\\teapot-low.obj");
		ObjFile objFile = new ObjFile(filePath);
		objFile.parseObjFile();
		
		Group g = objFile.objectToGroup();
//		g.setTransform(g.getTransform());
		g.setTransform(g.getTransform().rotateX((float) Math.PI / -2).rotateY((float) Math.PI / 18).translate(0, 1, 0).scale(0.33f, 0.33f, 0.33f));
//		g.getMaterial().setColor(new Color(0.75f, 0.6f, 0.2f));
		
		World w = new World();
		w.addObjects(g);
//		Bounds boundingBox = w.getObjects().get(0).parentSpaceBounds(); 
//		float x = Math.abs(boundingBox.getMaxExtent().getX() - boundingBox.getMinExtent().getX());
//		float y = Math.abs(boundingBox.getMaxExtent().getY() - boundingBox.getMinExtent().getY());
//		float z = Math.abs(boundingBox.getMaxExtent().getZ() - boundingBox.getMinExtent().getZ());
//		
//		System.out.println(x + ", " + y + ", " + z);
		
		Plane p = new Plane();
		p.getMaterial().setColor(new Color(1, 0, 0));
		
		w.addObjects(p);
		
		Camera camera = new Camera(100, 50, (float) Math.PI / 3);
		camera.setTransform(Matrix.multiply(camera.getTransform(), Matrix.getViewTransform(new Point(0, 10, -25), new Point(0, 1, 0), new Vector(0, 1, 0))));
		
		
		System.out.println("Rendering world");
		Canvas canvas = camera.render(w, 4);
		
		String ppmData = Canvas.canvasToPpm(canvas);
		Save.saveToDiskAsText(absolutePath, ppmData);
		
	}
}
