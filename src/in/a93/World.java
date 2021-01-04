package in.a93;

import java.util.ArrayList;

public class World {
	private ArrayList<Light> lights = new ArrayList<Light>();;
	ArrayList<Sphere> objects = new ArrayList<Sphere>();
//	private Sphere sphere1;
//	private Sphere sphere2;
	
	public World() {
		Light light = new Light(new Point(-10, 10, -10), new Color(1, 1, 1));
		this.lights.add(light);
//		this.sphere1 = new Sphere();		
//		this.sphere2 = new Sphere();
//		this.objects.add(sphere1);
//		this.objects.add(sphere2);
//		this.sphere1.getMaterial().setColor(new Color(0.8f, 1f, 0.6f));
//		this.sphere1.getMaterial().setDiffuse(0.7f);
//		this.sphere1.getMaterial().setSpecular(0.2f);
		
//		Matrix sphere2Transform = Matrix.getIdentityMatrix(4, 4).scale(0.5f, 0.5f, 0.5f);
//		this.sphere2.setTransform(sphere2Transform);
	}
	
	public ArrayList<Light> getLight() {
		return this.lights;
	}

	public void setLight(int index, Light light) {
		this.lights.remove(index);
		this.lights.add(index, light);
	}
	
//	public void setObject(int index, Sphere object) {
//		this.objects.remove(index);
//		this.objects.add(index, object);
//	}

	public void addLight(Light light) {
		this.lights.add(light);
	}

	public ArrayList<Sphere> getObjects() {
		return objects;
	}
	
	public void addObjects(Sphere object) {
		this.objects.add(object);
	}
	
//	public Sphere getSphere1() {
//		return sphere1;
//	}
//
//	public void setSphere1(Sphere sphere1) {
//		this.sphere1 = sphere1;
//	}
//
//	public Sphere getSphere2() {
//		return sphere2;
//	}
//
//	public void setSphere2(Sphere sphere2) {
//		this.sphere2 = sphere2;
//	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append(this.getLight());
		
		for (Sphere sphere : this.getObjects()) {
			s.append("\n" + sphere);
			
		}
		
		return s.toString();
	}
	
	public static ArrayList<Intersection> intersectWorld(World world, Ray ray) {
		ArrayList<Intersection> result = new ArrayList<Intersection>();;
		
		for (Sphere object : world.getObjects()) {
			Intersection[] xs = Sphere.intersect(object, ray);
			if (xs == null) {
				continue;
			} else {
				for (Intersection intersection : xs) {
					result.add(intersection);
				}
			}
		}
		
		result.sort(new IntersectionComparator());
		return result;
	}
	
	public static Color getShadeHit(World world, IntersectionPreComputedValue comp) {
		Color result = new Color(0, 0, 0);
		// isShadowed() method traverses all the lights in this world to find if the object is in shadow
		boolean inShade = world.isShadowed(comp.getOverPoint());
		for (Light light : world.getLight()) {
			result = result.add(Material.getLighting(
						comp.getObject().getMaterial(), 
						light, 
						comp.getOverPoint(), 
						comp.getEyeVector(), 
						comp.getNormalVector(), inShade
						)
					);
		}
		return result;
	}
	
	public static Color getColorAt(World world, Ray ray) {
		ArrayList<Intersection> i = World.intersectWorld(world, ray);
		Intersection[] intersections = new Intersection[i.size()];
		
		for (int x = 0; x < i.size(); x++) {
			intersections[x] = i.get(x);
		}
		
		Intersection hit = Intersection.getHit(intersections);
		
		Color result = new Color(0, 0, 0);
		IntersectionPreComputedValue comps = null;
		if (hit == null) {
			return result;
		} else {
			comps = new IntersectionPreComputedValue(hit, ray);
		}
		
		return World.getShadeHit(world, comps);
	}
	
	public boolean isShadowed(Point p) {
		boolean result = false;
		
//		for (Light light : this.getLight()) {
//			Vector v = light.getPosition().subtract(p);
//			float distance = v.magnitude();
//			Vector direction = Vector.normalize(v);
//			
//			Ray ray = new Ray(p, direction);
//			ArrayList<Intersection> i = World.intersectWorld(this, ray);
//			
//			Intersection[] intersections = new Intersection[i.size()];
//			for (int y = 0; y < i.size(); y++) {
//				intersections[y] = i.get(y);
//			}
//
//			Intersection hit = Intersection.getHit(intersections);
//			
//			if (!(hit == null) && (hit.getT() < distance)) {
//				result = true;
//			}
//			
//		}

		Vector v = this.getLight().get(0).getPosition().subtract(p);
		float distance = v.magnitude();
		Vector direction = Vector.normalize(v);
		
		Ray ray = new Ray(p, direction);
		ArrayList<Intersection> i = World.intersectWorld(this, ray);
		
		Intersection[] intersections = new Intersection[i.size()];
		for (int y = 0; y < i.size(); y++) {
			intersections[y] = i.get(y);
		}
		
		Intersection hit = Intersection.getHit(intersections);
		
		if (!(hit == null) && (hit.getT() < distance)) {
			result = true;
		}
		
		return result;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
