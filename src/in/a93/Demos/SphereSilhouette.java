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
import in.a93.Light;
import in.a93.Material;

public class SphereSilhouette {
	
	public static void drawSphere() {
		int canvasPixels = 500;
		Canvas canvas = new Canvas(canvasPixels, canvasPixels);
		Color color = null;
		Sphere sphere = new Sphere();
		Point rayOrigin = new Point(0, 0, -10);
		
		float wallZ = 10;
		float wallSize = 7;
		
		float pixelSize = wallSize / canvasPixels;
		
		float half = wallSize / 2;
		float worldY = 0f;
		float worldX = 0f;
	
		sphere.setMaterial(new Material());
		sphere.getMaterial().setColor(new Color(1.0f, 0.2f, 1.0f));
//		sphere.setTransform(Matrix.shearing(0.01f, 0, 0.11f, 0, 0, 0));
//		sphere.getMaterial().setColor(new Color(1.0f, 0, 0));
		
		Light light = new Light(new Point(-10, 10, -10), new Color(1, 1, 1));
//		int count = 0;
		for (int i = 0; i < canvasPixels; i++) {
			for (int j = 0; j < canvasPixels; j++) {
//				count++;
//				System.out.println("Ray count: " + count);
				worldY = half - (pixelSize * i);
				worldX = -half + (pixelSize * j);
				
				Point position = new Point(worldX, worldY, wallZ);
				
				Ray ray = new Ray(rayOrigin, Vector.normalize(position.subtract(rayOrigin)));
				
				Intersection[] xs = Sphere.intersect(sphere, ray);
				
				if (xs == null) {
					continue;
				} else {
					Intersection hit = Intersection.hit(xs); 
					if (!(hit == null)) {
						Point point = Ray.getPosition(ray, hit.getT());
						Vector normalVector = Sphere.normalAt(sphere, point);
						Vector eyeVector = ray.getDirection().scalarMultiply(-1);
						color = Material.getLighting(hit.getObject().getMaterial(), light, point, eyeVector, normalVector);
						canvas.setColorAt(i, j, color);
					}
				}
			}
		}
		
		String ppmData = Canvas.canvasToPpm(canvas);
		Save.saveToDiskAsText("E:\\sphere.ppm", ppmData);
	}
}
