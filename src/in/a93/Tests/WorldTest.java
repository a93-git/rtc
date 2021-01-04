package in.a93.Tests;

import java.util.ArrayList;

import in.a93.*;

public class WorldTest {
	public static void worldTest() {
		World w = new World();
		Ray ray = new Ray(new Point(0, 0, -5), new Vector(0, 0, 1));
		ArrayList<Intersection> xs = World.intersectWorld(w, ray);
		System.out.println(xs.size());
		for (Intersection i : xs) { System.out.println(i); }
	}
	
	public static void preComputeTest() {
		Ray ray = new Ray(new Point(0, 0, -5), new Vector(0, 0, 1));
		Sphere sphere = new Sphere();
		Intersection intersection = new Intersection(4, sphere);
		IntersectionPreComputedValue comp = new IntersectionPreComputedValue(intersection, ray);
		
		System.out.println(comp.getIntersectionT());
		System.out.println(comp.getObject());
		System.out.println(comp.getPoint());
		System.out.println(comp.getEyeVector());
		System.out.println(comp.getNormalVector());
	}
	
	public static void preComputeRayIntersectsInsideTest() {
		Ray ray = new Ray(new Point(0, 0, 0), new Vector(0, 0, 1));
		Sphere shape = new Sphere();
		
		Intersection intersection = new Intersection(1, shape);
		IntersectionPreComputedValue comp = new IntersectionPreComputedValue(intersection, ray);
		
		System.out.println(comp.getIntersectionT());
		System.out.println(comp.getObject());
		System.out.println(comp.getPoint());
		System.out.println(comp.getEyeVector());
		System.out.println(comp.getInside());
		System.out.println(comp.getNormalVector());
	}
	
	public static void shadeHit() {
		World w = new World();
		Ray ray = new Ray(new Point(0, 0, -5), new Vector(0, 0, 1));
		
		Sphere shape = w.getObjects().get(0);
		Intersection i = new Intersection(4, shape);
		IntersectionPreComputedValue comp = new IntersectionPreComputedValue(i, ray);
		
		Color c = World.getShadeHit(w, comp);
		System.out.println(c);
		
	}

	public static void shadeHitInside() {
		World w = new World();
		w.setLight(0, new Light(new Point(0, 0.25f, 0), new Color(1, 1, 1)));
		
		Ray ray = new Ray(new Point(0, 0, 0), new Vector(0, 0, 1));
		
		Sphere shape = w.getObjects().get(1);
		Intersection i = new Intersection(0.5f, shape);
		IntersectionPreComputedValue comp = new IntersectionPreComputedValue(i, ray);
		
		Color c = World.getShadeHit(w, comp);
		System.out.println(c);
		
	}

	public static void colorAtWorld() {
		World world = new World();
		Ray ray = new Ray(new Point(0, 0, -5), new Vector(0, 1, 0));
		Color c = World.getColorAt(world, ray);
		
		System.out.println(c);

		Ray ray2 = new Ray(new Point(0, 0, -5), new Vector(0, 0, 1));

		Color c2 = World.getColorAt(world, ray2);
		
		System.out.println(c2);

		world.getObjects().get(0).getMaterial().setAmbient(1);
		world.getObjects().get(1).getMaterial().setAmbient(1);
		
		Ray ray3 = new Ray(new Point(0, 0, 0.75f), new Vector(0, 0, -1));
		Color c3 = World.getColorAt(world, ray3);
		
		System.out.println("Computed color is: " + c3);
		System.out.println("Color of inner sphere is: " + world.getObjects().get(1).getMaterial().getColor());
		System.out.println("Color of outer sphere is: " + world.getObjects().get(0).getMaterial().getColor());
		
	}
}
