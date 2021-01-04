package in.a93.Tests;

import in.a93.Intersection;
import in.a93.Point;
import in.a93.Ray;
import in.a93.Sphere;
import in.a93.Vector;

public class IntersectionTest {
	public static void createIntersectionTest() {
		Intersection i = new Intersection(3.5f, new Sphere());
		
		System.out.println("T is:" + i.getT());
		System.out.println("Sphere is: " + i.getObject());
	}
	
	public static void intersectionCollectionTest() {
		Intersection i = new Intersection(1, new Sphere());
		Intersection i2 = new Intersection(2, new Sphere());
		
		Intersection[] i3 = new Intersection[2];
		i3[0] = i;
		i3[1] = i2;
		
		Intersection[] i4 = Intersection.intersections(i, i2);
		Intersection[] i5 = Intersection.intersections(i3);
		
		for (Intersection j : i4) {
			System.out.printf("T: %s\tSphere: %s\n", j.getT(), j.getObject() );
		}
		
		for (Intersection j : i5) {
			System.out.printf("T: %s\tSphere: %s\n", j.getT(), j.getObject() );
		}
	}
	
	public static void intersectObjectPropertyTest() {
		Ray ray = new Ray(new Point(0, 0, -5), new Vector(0, 0, 1));
		Sphere s = new Sphere();
		
		System.out.println("Sphere UUID is: " + s.getUuid());
		Intersection[] xs = Sphere.intersect(s, ray);
		for (Intersection i : xs) {
			System.out.println(i.getT() + ", " + i.getObject().getUuid());
		}
	}
	
	public static void hitTest() {
		Sphere s = new Sphere();
		Intersection i1 = new Intersection(1, s);
		Intersection i2 = new Intersection(2, s);
		Intersection i3 = new Intersection(-1, s);
		Intersection i4 = new Intersection(-2, s);
		Intersection i5 = new Intersection(5, s);
		Intersection i6 = new Intersection(7, s);
		Intersection i7 = new Intersection(-3, s);
		
		Intersection[] il1 = Intersection.intersections(i1, i2);
		Intersection[] il2 = Intersection.intersections(i1, i3);
		Intersection[] il3 = Intersection.intersections(i4, i3);
		Intersection[] il4 = Intersection.intersections(i5, i6, i7, i2);
		
		System.out.println(Intersection.getHit(il1));
		System.out.println(Intersection.getHit(il2));
		System.out.println(Intersection.getHit(il3));
		System.out.println(Intersection.getHit(il4));
	}
}
