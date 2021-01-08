package in.a93.Tests;

import in.a93.Sphere;
import in.a93.*;

public class FresnelTest {
	public static void test1() {
		Sphere s = Sphere.getGlassSphere();
		Ray r = new Ray(new Point(0, 0, (float) Math.sqrt(2) / 2), new Vector(0, 1, 0));
		Intersection[] xs = Intersection.intersections(new Intersection(-(float) Math.sqrt(2)/2, s), new Intersection((float) Math.sqrt(2)/2, s));
		IntersectionCompute c = new IntersectionCompute(xs[1], r, xs);
		float reflectance = Intersection.getSchlick(c);
		System.out.println("Reflectance: " + reflectance);
	}

	public static void test2() {
		Sphere s = Sphere.getGlassSphere();
		Ray r = new Ray(new Point(0, 0, 0), new Vector(0, 1, 0));
		Intersection[] xs = Intersection.intersections(new Intersection(-1, s), new Intersection(1, s));
		IntersectionCompute c = new IntersectionCompute(xs[1], r, xs);
		float reflectance = Intersection.getSchlick(c);
		System.out.println("Reflectance: " + reflectance);
	}
	
	public static void test3() {
		Sphere s = Sphere.getGlassSphere();
		Ray r = new Ray(new Point(0, 0.99f, -2), new Vector(0, 0, 1));
		Intersection[] xs = Intersection.intersections(new Intersection(1.8589f, s));
		IntersectionCompute c = new IntersectionCompute(xs[0], r, xs);
		float reflectance = Intersection.getSchlick(c);
		System.out.println("Reflectance: " + reflectance);
	}
	
	public static void test4() {
		World w = World.getDefaultWorld();
		Ray r = new Ray(new Point(0, 0, -3), new Vector(0, -(float) Math.sqrt(2)/2, (float) Math.sqrt(2)/2));
		Plane floor = new Plane();
		floor.setTransform(floor.getTransform().translate(0, -1, 0));
		floor.getMaterial().setReflective(0.5f);
		floor.getMaterial().setTransparency(0.5f);
		floor.getMaterial().setRefractiveIndex(1.5f);
		w.addObjects(floor);
		
		Sphere ball = new Sphere();
		ball.getMaterial().setColor(new Color(1, 0, 0));
		ball.getMaterial().setAmbient(0.5f);
		ball.setTransform(ball.getTransform().translate(0, -3.5f, -0.5f));
		w.addObjects(ball);
		
		Intersection[] xs = Intersection.intersections(new Intersection((float) Math.sqrt(2), floor));
		IntersectionCompute c = new IntersectionCompute(xs[0], r, xs);
		Color color = World.getShadeHit(w, c, 5);
		System.out.println("Color: " + color);
	}
}
