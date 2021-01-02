package in.a93.Demos;

import in.a93.Canvas;
import in.a93.Color;
import in.a93.Intersection;
import in.a93.Matrix;
import in.a93.Sphere;
import in.a93.Point;
import in.a93.Vector;
import in.a93.Ray;
import in.a93.Save;

public class SphereSilhouette {
	private int canvasPixels = 500;
	private Canvas canvas = new Canvas(canvasPixels, canvasPixels);
	private Color color = new Color(1, 0, 0);
	private Sphere sphere = new Sphere();
	private Point rayOrigin = new Point(0, 0, -5);
	
	public void drawSphere() {
		float wallZ = 10;
		float wallSize = 7;
		
		float pixelSize = wallSize / canvasPixels;
		
		float half = wallSize / 2;
		float worldY = 0f;
		float worldX = 0f;
	
		sphere.setTransform(Matrix.shearing(0.01f, 0, 0.11f, 0, 0, 0));
		for (int i = 0; i < canvasPixels; i++) {
			for (int j = 0; j < canvasPixels; j++) {
				worldY = half - (pixelSize * i);
				worldX = -half + (pixelSize * j);
				
				Point position = new Point(worldX, worldY, wallZ);
				
				Ray ray = new Ray(rayOrigin, Vector.normalize(position.subtract(rayOrigin)));
				
				Intersection[] xs = Sphere.intersect(sphere, ray);
				
				if (xs == null) {
					continue;
				} else {
					if (!(Intersection.hit(xs) == null)) {
						canvas.setColorAt(i, j, color);
					}
				}
			}
		}
		
		String ppmData = Canvas.canvasToPpm(canvas);
		Save.saveToDiskAsText("E:\\sphere.ppm", ppmData);
	}
}
