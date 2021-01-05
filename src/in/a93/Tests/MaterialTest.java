package in.a93.Tests;

import in.a93.Material;
import in.a93.Sphere;
import in.a93.Point;
import in.a93.Vector;
import in.a93.Light;
import in.a93.Color;

public class MaterialTest {
	public static void materialTest() {
		Material m = new Material();
		
		System.out.println("Color: " + m.getColor());
		System.out.println("Ambient: " + m.getAmbient());
		System.out.println("Diffuse: " + m.getDiffuse());
		System.out.println("Specular: " + m.getSpecular());
		System.out.println("Shininess: " + m.getShininess());
	}
	
	public static void sphereDefaultMaterialTest() {
		Sphere s = new Sphere();
		
		System.out.println("Default material of sphere with " + s + " is:\n" + s.getMaterial());
	}

	public static void sphereMaterialAssignmentTest() {
		Sphere s = new Sphere();
		Material m = new Material();
		
		m.setAmbient(1);
		s.setMaterial(m);
		System.out.println("Updated material of sphere with " + s + " is:\n" + s.getMaterial());
	}
	
	public static void phongReflectionTest() {
		Material material = new Material();
		Point position = new Point(0, 0, 0);
		
		Vector ev = new Vector(0, 0, -1);
		Vector nv = new Vector(0, 0, -1);
		Light light = new Light(new Point(0, 0, -10), new Color(1, 1, 1));
		
		Color result = Material.getLighting(material, light, position, ev, nv, false, null);
		System.out.println(result);
		
		Vector ev2 = new Vector(0, (float) Math.sqrt(2) / 2, -1 * (float) Math.sqrt(2) / 2);
		Vector nv2 = new Vector(0, 0, -1);
		Light light2 = new Light(new Point(0, 0, -10), new Color(1, 1, 1));
		
		Color result2 = Material.getLighting(material, light2, position, ev2, nv2, false, null);
		System.out.println(result2);

		Vector ev3 = new Vector(0, 0, -1);
		Vector nv3 = new Vector(0, 0, -1);
		Light light3 = new Light(new Point(0, 10, -10), new Color(1, 1, 1));
		
		Color result3 = Material.getLighting(material, light3, position, ev3, nv3, false, null);
		System.out.println(result3);

		Vector ev4 = new Vector(0, -1 * (float) Math.sqrt(2) / 2, -1 * (float) Math.sqrt(2) / 2);
		Vector nv4 = new Vector(0, 0, -1);
		Light light4 = new Light(new Point(0, 10, -10), new Color(1, 1, 1));
		
		Color result4 = Material.getLighting(material, light4, position, ev4, nv4, false, null);
		System.out.println(result4);

		Vector ev5 = new Vector(0, 0, -1);
		Vector nv5 = new Vector(0, 0, -1);
		Light light5 = new Light(new Point(0, 0, 10), new Color(1, 1, 1));
		
		Color result5 = Material.getLighting(material, light5, position, ev5, nv5, false, null);
		System.out.println(result5);
	}
	
	public static void test1() {
		Material material = new Material();
		Point position = new Point(0, 0, 0);
		
		Vector ev = new Vector(0, 0, -1);
		Vector nv = new Vector(0, 0, -1);
		Light light = new Light(new Point(0, 0, -10), new Color(1, 1, 1));
		
		Color result = Material.getLighting(material, light, position, ev, nv, true, null);
		System.out.println(result);
		
	}

}
