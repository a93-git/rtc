package in.a93.Tests;

import in.a93.*;

public class ReflectiveTest {
	public static void test1() {
		Material m = new Material();
		System.out.println("Reflective: " + m.getReflective());
	}

	public static void test2() {
		Plane p = new Plane();
		Ray r = new Ray(new Point(0, 1, -1), new Vector(0, -(float) Math.sqrt(2)/2, (float) Math.sqrt(2)/2));
		Intersection i = new Intersection((float) Math.sqrt(2), p);
		IntersectionCompute c = new IntersectionCompute(i, r, null);
		
		System.out.println("Precomputed reflect vector: " + c.getReflectVector());
	}
	
	public static void test3() {
		World w = new World();
		Sphere s = new Sphere();
		Sphere s2 = new Sphere();
		
		s2.setTransform(s2.getTransform().scale(0.5f, 0.5f, 0.5f));
		
		w.addObjects(s);
		w.addObjects(s2);
		
		s2.getMaterial().setAmbient(1);
		
		Ray r = new Ray(new Point(0, 0, 0), new Vector(0, 0, 1));
		
		Intersection i = new Intersection(1, s2);
		
		IntersectionCompute c = new IntersectionCompute(i, r, null);
		Color color = w.getReflectedColor(c, 0);
		
		System.out.println("Reflected color is: " + color);
	}
	
	public static void test4() {
		World w = new World();
		Sphere s = new Sphere();
		Sphere s2 = new Sphere();
		Plane p = new Plane();
		
//		World w = World.getDefaultWorld();
		p.getMaterial().setReflective(0.5f);
		p.setTransform(p.getTransform().translate(0, -1, 0));
		s2.setTransform(s2.getTransform().scale(0.5f, 0.5f, 0.5f));
		s.getMaterial().setColor(new Color(0.8f, 1.0f, 0.6f));
		s.getMaterial().setDiffuse(0.7f);
		s.getMaterial().setSpecular(0.2f);
		
		w.addObjects(s);
		w.addObjects(s2);
		w.addObjects(p);

		
		Ray r = new Ray(new Point(0, 0, -3), new Vector(0, -((float) Math.sqrt(2)/2), (float) Math.sqrt(2)/2));
		
		Intersection i = new Intersection((float) Math.sqrt(2), p);
		
		IntersectionCompute c = new IntersectionCompute(i, r, null);
		Color color = w.getReflectedColor(c, 0);
		
		System.out.println("Reflected color is: " + color);
	}
	
	public static void test5() {
		World w = World.getDefaultWorld();
		Plane p = new Plane();
		p.setTransform(p.getTransform().translate(0, -1, 0));
		p.getMaterial().setReflective(0.5f);
		
		w.addObjects(p);

		Ray r = new Ray(new Point(0, 0, -3), new Vector(0, -((float) Math.sqrt(2)/2), (float) Math.sqrt(2)/2));
		
		Intersection i = new Intersection((float) Math.sqrt(2), p);

		IntersectionCompute c = new IntersectionCompute(i, r, null);
		Color color = World.getShadeHit(w, c, 0);
		
		System.out.println("Shade color is: " + color);
	}
	
	public static void test6() {
		World w = new World();
		
		Material pm = new Material();
		pm.setReflective(1);
		
		Matrix t = Matrix.getIdentityMatrix(4, 4).translate(0, -1, 0);
		Matrix t2 = Matrix.getIdentityMatrix(4, 4).translate(0, 1, 0);
		
		Plane p = new Plane();
		p.setMaterial(pm);
		p.setTransform(t);
		
		Plane p2 = new Plane();
		p2.setMaterial(pm);
		p2.setTransform(t2);
		
		Ray ray = new Ray(new Point(0, 0, -3), new Vector(0, 1, 0));
		
		w.addObjects(p);
		w.addObjects(p2);
		w.setLight(0, new Light(new Point(0, 0, 0), new Color(1, 1, 1)));
		
		Color color = World.getColorAt(w, ray, 4);
		
		System.out.println("This terminates");
	}
	
	public static void test7() {
		World w = World.getDefaultWorld();
		Plane p = new Plane();
		p.setTransform(p.getTransform().translate(0, -1, 0));
		p.getMaterial().setReflective(0.5f);
		
		w.addObjects(p);

		Ray r = new Ray(new Point(0, 0, -3), new Vector(0, -((float) Math.sqrt(2)/2), (float) Math.sqrt(2)/2));
		
		Intersection i = new Intersection((float) Math.sqrt(2), p);

		IntersectionCompute c = new IntersectionCompute(i, r, null);
		Color color = w.getReflectedColor(c, 0);
		
		System.out.println("Reflected color is: " + color);
	}
}
