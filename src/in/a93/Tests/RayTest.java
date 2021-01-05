package in.a93.Tests;

import in.a93.Ray;
import in.a93.Sphere;
import in.a93.Intersection;
import in.a93.Matrix;
import in.a93.Point;
import in.a93.Vector;

public class RayTest {
	public static void testAll() {
		RayTest.rayTest();
		RayTest.rayPosition();
		RayTest.raySphereIntersection();
		RayTest.raySphereBehindRayIntersection();
		RayTest.rayOriginatesInsideSphereIntersection();
		RayTest.raySphereZeroIntersection();
		RayTest.raySphereTangentIntersection();
	}
	
	public static void rayTest() {
		Ray ray = null;
		Point origin = new Point(1, 2, 3);
		Vector direction = new Vector(4, 5, 6);
		
		ray = new Ray(origin, direction);
		
		System.out.println("Ray origin is: " + ray.getOrigin());
		System.out.println("Ray direction is: " + ray.getDirection());
	}
	
	public static void rayPosition() {
		Ray ray = null;
		Point origin = new Point(2, 3, 4);
		Vector direction = new Vector(1, 0, 0);
		ray = new Ray(origin, direction);
		
		System.out.println("t = 0: " + Ray.getPosition(ray, 0));
		System.out.println("t = 1: " + Ray.getPosition(ray, 1));
		System.out.println("t = -1: " + Ray.getPosition(ray, -1));
		System.out.println("t = 2.5: " + Ray.getPosition(ray, 2.5f));
		
	}
	
	public static void raySphereIntersection() {
		Ray ray = new Ray(new Point(0, 0, -5), new Vector(0, 0, 1));
		Sphere s = new Sphere();
		
		Intersection[] xs = s.intersect(ray);
		
		if (xs == null) {
			System.out.println("No intersection");
		} else {
			for (Intersection i : xs) {
				System.out.println(i.getT());
			}
		}
	}
	
	public static void raySphereTangentIntersection() {
		Ray ray = new Ray(new Point(0, 1, -5), new Vector(0, 0, 1));
		Sphere s = new Sphere();
		
		Intersection[] xs = s.intersect(ray);
		
		if (xs == null) {
			System.out.println("No intersection");
		} else {
			for (Intersection i : xs) {
				System.out.println(i.getT());
			}
		}
	}
	
	public static void raySphereZeroIntersection() {
		Ray ray = new Ray(new Point(0, 2, -5), new Vector(0, 0, 1));
		Sphere s = new Sphere();
		
		Intersection[] xs = s.intersect(ray);
		
		if (xs == null) {
			System.out.println("No intersection");
		} else {
			for (Intersection i : xs) {
				System.out.println(i.getT());
			}
		}
	}
	
	public static void rayOriginatesInsideSphereIntersection() {
		Ray ray = new Ray(new Point(0, 0, 0), new Vector(0, 0, 1));
		Sphere s = new Sphere();
		
		Intersection[] xs = s.intersect(ray);
		
		if (xs == null) {
			System.out.println("No intersection");
		} else {
			for (Intersection i : xs) {
				System.out.println(i.getT());
			}
		}
	}
	
	public static void raySphereBehindRayIntersection() {
		Ray ray = new Ray(new Point(0, 0, 5), new Vector(0, 0, 1));
		Sphere s = new Sphere();
		
		Intersection[] xs = s.intersect(ray);
		
		if (xs == null) {
			System.out.println("No intersection");
		} else {
			for (Intersection i : xs) {
				System.out.println(i.getT());
			}
		}
	}
	
	public static void rayTransformation() {
		Ray ray = new Ray(new Point(1, 2, 3), new Vector(0, 1, 0));
		
		Matrix m = Matrix.translation(3, 4, 5);
		
		Ray ray2 = Ray.transformRay(ray,  m);
		
		System.out.println(ray2);
		
		Matrix m2 = Matrix.scaling(2, 3, 4);
		
		Ray ray3 = Ray.transformRay(ray, m2);
		System.out.println(ray3);
		
	}

	public static void sphereTransformation() {
		Sphere s = new Sphere();
		
		System.out.println("Default transform of the sphere is:\n" + s.getTransform());
		
		Matrix t = Matrix.translation(2, 3, 4);
		
		System.out.println("Translation matrix is:\n" + t);
		s.setTransform(t);
		System.out.println("Transform of sphere after translation is:\n" + s.getTransform());
	}

	public static void transformedSphereIntersection() {
		Ray ray = new Ray(new Point(0, 0, -5), new Vector(0, 0, 1));
		Sphere s = new Sphere();
		s.setTransform(Matrix.scaling(2, 2, 2));
		
		Intersection[] xs = s.intersect(ray);
		
		System.out.println("Result count is: " + xs.length);
		
		if (!(xs == null)) {
			for (Intersection i : xs) {
				System.out.println(i.getT());
			}
		}
		
		Sphere s2 = new Sphere();
		s2.setTransform(Matrix.scaling(5,  0,  0));
		Intersection[] xs2 = s2.intersect(ray);
		
		if (!(xs2 == null)) {
			for (Intersection i : xs) {
				System.out.println(i.getT());
			}
		} else System.out.println(0);
	}







}
