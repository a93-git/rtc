package in.a93.Tests;

import in.a93.Sphere;
import in.a93.Vector;
import in.a93.Matrix;
import in.a93.Point;

public class LightShadingTest {
	
	public static void sphereNormalTest() {
		Sphere sphere = new Sphere();
		Vector normal = Sphere.normalAt(sphere, new Point(1, 0, 0));
		Vector normal2 = Sphere.normalAt(sphere, new Point(0, 1, 0));
		Vector normal3 = Sphere.normalAt(sphere, new Point(0, 0, 1));
		Vector normal4 = Sphere.normalAt(sphere, new Point((float) Math.sqrt(3)/3, (float) Math.sqrt(3)/3, (float) Math.sqrt(3)/3));
	
		System.out.println(normal);
		System.out.println(normal2);
		System.out.println(normal3);
		System.out.println(normal4);
	
		System.out.println(normal4.equals(Vector.normalize(normal4)));
	
		sphere.setTransform(Matrix.translation(0, 1, 0));
		Vector normal5 = Sphere.normalAt(sphere, new Point(0, 1.70711f, -0.70711f));
		System.out.println(normal5);
		
		Sphere sphere2 = new Sphere();
		
		Matrix scaling = Matrix.scaling(1, 0.5f, 1);
		Matrix rotationZ = Matrix.rotationZ((float) (Math.PI / 5));
		Matrix transform = Matrix.multiply(scaling, rotationZ); 
		
		sphere2.setTransform(transform);
		
		Point normalPoint = new Point(0, (float) Math.sqrt(2)/2, (float) (-1 * Math.sqrt(2)/2));
		
		
		Vector normal6 = Sphere.normalAt(sphere2, normalPoint);
		
		System.out.println(normal6);
		
	}
}
