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
		
		Shape shape = w.getObjects().get(0);
		Intersection i = new Intersection(4, shape);
		IntersectionPreComputedValue comp = new IntersectionPreComputedValue(i, ray);
		
		Color c = World.getShadeHit(w, comp, 0);
		System.out.println(c);
		
	}

	public static void shadeHitInside() {
		World w = new World();
		w.setLight(0, new Light(new Point(0, 0.25f, 0), new Color(1, 1, 1)));
		
		Ray ray = new Ray(new Point(0, 0, 0), new Vector(0, 0, 1));
		
		Sphere shape = (Sphere) w.getObjects().get(1);
		Intersection i = new Intersection(0.5f, shape);
		IntersectionPreComputedValue comp = new IntersectionPreComputedValue(i, ray);
		
		Color c = World.getShadeHit(w, comp, 0);
		System.out.println(c);
		
	}

	public static void colorAtWorld() {
		World world = new World();
		Ray ray = new Ray(new Point(0, 0, -5), new Vector(0, 1, 0));
		Color c = World.getColorAt(world, ray, 4);
		
		System.out.println(c);

		Ray ray2 = new Ray(new Point(0, 0, -5), new Vector(0, 0, 1));

		Color c2 = World.getColorAt(world, ray2, 4);
		
		System.out.println(c2);

		world.getObjects().get(0).getMaterial().setAmbient(1);
		world.getObjects().get(1).getMaterial().setAmbient(1);
		
		Ray ray3 = new Ray(new Point(0, 0, 0.75f), new Vector(0, 0, -1));
		Color c3 = World.getColorAt(world, ray3, 4);
		
		System.out.println("Computed color is: " + c3);
		System.out.println("Color of inner sphere is: " + world.getObjects().get(1).getMaterial().getColor());
		System.out.println("Color of outer sphere is: " + world.getObjects().get(0).getMaterial().getColor());
		
	}
	
	public static void test1() {
		World w = new World();
		
		Point p = new Point(0, 10, 0);
		
		System.out.println(w.isShadowed(p));
	}
	
	public static void test2() {
		World w = new World();
		
		Point p = new Point(10, -10, 10);
		
		System.out.println(w.isShadowed(p));
	}
	
	public static void test3() {
		World w = new World();
		
		Point p = new Point(-20, 20, -20);
		
		System.out.println(w.isShadowed(p));
	}
	
	public static void test4() {
		World w = new World();
		
		Point p = new Point(-2, 2, -2);
		
		System.out.println(w.isShadowed(p));
	}
	
	public static void test5() {
		World w = new World();
		
		w.setLight(0, new Light(new Point(0, 0, -10), new Color(1, 1, 1)));
		w.getObjects().get(1).setTransform(w.getObjects().get(1).getTransform().translate(0, 0, 10));
		
		Ray ray = new Ray(new Point(0, 0, 5), new Vector(0, 0, 1));
		Intersection i = new Intersection(4, w.getObjects().get(1));
		
		IntersectionPreComputedValue comps = new IntersectionPreComputedValue(i, ray);
		Color c = World.getShadeHit(w, comps, 0);
		System.out.println(c);
	}
	
	public static void test6() {
		World w = new World();
		
		Ray ray = new Ray(new Point(0, 0, -5), new Vector(0, 0, 1));
		Sphere sphere = new Sphere();
		sphere.setTransform(sphere.getTransform().translate(0, 0, 1));

		w.addObjects(sphere);
		
		Light light = new Light(new Point(0, 0, -10), new Color(1, 1, 1));
		w.addLight(light);

		Intersection i = new Intersection(5, sphere);
		
		IntersectionPreComputedValue computed = new IntersectionPreComputedValue(i, ray);
		
		System.out.println(computed.getOverPoint().getZ() < IntersectionPreComputedValue.getDELTA());
		System.out.println(computed.getPoint().getZ() > computed.getOverPoint().getZ());
		
	}
}
