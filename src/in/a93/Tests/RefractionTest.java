package in.a93.Tests;

import in.a93.*;

public class RefractionTest {
	public static void test1() {
		Material m = new Material();
		
		System.out.println(m);
	}
	
	public static void test2() {
		Sphere a = Sphere.getGlassSphere();
		Sphere b = Sphere.getGlassSphere();
		Sphere c = Sphere.getGlassSphere();
		
		Ray r = new Ray(new Point(0, 0, -4), new Vector(0, 0, 1));
		
		a.setTransform(a.getTransform().scale(2, 2, 2));
		a.getMaterial().setRefractiveIndex(1.5f);
		
		b.setTransform(a.getTransform().translate(0, 0, -0.25f));
		b.getMaterial().setRefractiveIndex(2.0f);
		
		c.setTransform(a.getTransform().translate(0, 0, 0.25f));
		c.getMaterial().setRefractiveIndex(2.5f);
			
		Intersection[] intersections = Intersection.intersections(
				new Intersection(2, a), 
				new Intersection(2.75f, b), 
				new Intersection(3.25f, c), 
				new Intersection(4.75f, b), 
				new Intersection(5.25f, c), 
				new Intersection(6, a)
				);
		
		for (int i = 0; i < intersections.length; i++) {
			IntersectionCompute computed = new IntersectionCompute(intersections[i], r, intersections);
			System.out.println("Index: " + i + " n1: " + computed.getFirstRI() + " n2: " + computed.getSecondRI());
		}
	}
	
	public static void test3() {
		Ray r = new Ray(new Point(0, 0, -5), new Vector(0, 0, 1));
		
		Sphere a = Sphere.getGlassSphere();
		a.setTransform(a.getTransform().translate(0, 0, 1));
		
		Intersection i = new Intersection(5, a);
		Intersection[] xs = Intersection.intersections(i);
		IntersectionCompute c = new IntersectionCompute(i, r, xs);
		System.out.println("Under point: " + c.getUnderPoint());
		System.out.println("Point: " + c.getPoint());
	}
	
	public static void test4() {
		World w = World.getDefaultWorld();
		
		Ray r = new Ray(new Point(0, 0, -5), new Vector(0, 0, 1));
		
		Shape a = w.getObjects().get(0);
		a.setTransform(a.getTransform().translate(0, 0, 1));
		
		Intersection i = new Intersection(4, a);
		Intersection i2 = new Intersection(6, a);
		Intersection[] xs = Intersection.intersections(i, i2);
		IntersectionCompute c = new IntersectionCompute(xs[0], r, xs);
		Color rc = w.getRefractedColor(c, 5);
		System.out.println("Color: " + rc);
	}
	
	public static void test5() {
		World w = World.getDefaultWorld();
		
		Ray r = new Ray(new Point(0, 0, -5), new Vector(0, 0, 1));
		
		Shape a = w.getObjects().get(0);
		a.getMaterial().setTransparency(1.0f);
		a.getMaterial().setRefractiveIndex(1.5f);
		a.setTransform(a.getTransform().translate(0, 0, 1));
		
		Intersection i = new Intersection(4, a);
		Intersection i2 = new Intersection(6, a);
		Intersection[] xs = Intersection.intersections(i, i2);
		IntersectionCompute c = new IntersectionCompute(xs[0], r, xs);
		Color rc = w.getRefractedColor(c, 0);
		System.out.println("Color: " + rc);
	}
	
	public static void test6() {
		World w = World.getDefaultWorld();
		
		Ray r = new Ray(new Point(0, 0, (float) Math.sqrt(2)/2), new Vector(0, 1, 0));
		
		Shape a = w.getObjects().get(0);
		a.getMaterial().setTransparency(1.0f);
		a.getMaterial().setRefractiveIndex(1.5f);
//		a.setTransform(a.getTransform().translate(0, 0, 1));
		
		Intersection i = new Intersection(-(float) Math.sqrt(2)/2, a);
		Intersection i2 = new Intersection((float) Math.sqrt(2)/2, a);
		Intersection[] xs = Intersection.intersections(i, i2);
		IntersectionCompute c = new IntersectionCompute(xs[1], r, xs);
		Color rc = w.getRefractedColor(c, 5);
		System.out.println("Color: " + rc);
	}
	
	public static void test7() {
		World w = World.getDefaultWorld();
		
		Ray r = new Ray(new Point(0, 0, 0.1f), new Vector(0, 1, 0));
		
		Shape a = w.getObjects().get(0);
		a.getMaterial().setAmbient(1);
		a.getMaterial().setPattern(new RingPattern(new Color(1, 0, 0), new Color(0, 1, 1)));
				
		Shape b = w.getObjects().get(1);
		b.getMaterial().setTransparency(1.0f);
		b.getMaterial().setRefractiveIndex(1.5f);
//		a.setTransform(a.getTransform().translate(0, 0, 1));
		
		Intersection i = new Intersection(-0.9899f, a);
		Intersection i2 = new Intersection(-0.4899f, b);
		Intersection i3 = new Intersection(0.4899f, b);
		Intersection i4 = new Intersection(0.9899f, a);
		
		Intersection[] xs = Intersection.intersections(i, i2, i3, i4);
		IntersectionCompute c = new IntersectionCompute(xs[2], r, xs);
		Color rc = w.getRefractedColor(c, 5);
		System.out.println("Color: " + rc);
	}
	
	public static void test8() {
		World w = World.getDefaultWorld();
		
		Plane p = new Plane();
		p.getMaterial().setTransparency(0.5f);;
		p.getMaterial().setRefractiveIndex(1.5f);
		p.setTransform(p.getTransform().translate(0, -1, 0));
		w.addObjects(p);
		
		Sphere s = new Sphere();
		s.getMaterial().setColor(new Color(1, 0, 0));
		s.getMaterial().setAmbient(0.5f);
		s.setTransform(s.getTransform().translate(0, -3.5f, -0.5f));
		w.addObjects(s);
		
		Ray r = new Ray(new Point(0, 0, -3), new Vector(0, -(float) Math.sqrt(2)/2, (float) Math.sqrt(2)/2));
		
//		Shape a = w.getObjects().get(0);
//		a.getMaterial().setAmbient(1);
//		a.getMaterial().setPattern(new RingPattern(new Color(1, 0, 0), new Color(0, 1, 1)));
//		
//		Shape b = w.getObjects().get(1);
//		b.getMaterial().setTransparency(1.0f);
//		b.getMaterial().setRefractiveIndex(1.5f);
//		a.setTransform(a.getTransform().translate(0, 0, 1));
		
		Intersection i = new Intersection((float) Math.sqrt(2), p);
//		Intersection i2 = new Intersection(-0.4899f, b);
//		Intersection i3 = new Intersection(0.4899f, b);
//		Intersection i4 = new Intersection(0.9899f, a);
		
		Intersection[] xs = Intersection.intersections(i);
		IntersectionCompute c = new IntersectionCompute(xs[0], r, xs);
		Color rc = World.getShadeHit(w, c, 5);
		System.out.println("Color: " + rc);
	}
}
