package in.a93;

import java.util.ArrayList;

public class World {
	private ArrayList<Light> lights = new ArrayList<Light>();;
	private ArrayList<Shape> objects = new ArrayList<Shape>();
	private static final float DELTA = 0.01f;
	
	public World() {
		Light light = new Light(new Point(-10, 10, -10), new Color(1, 1, 1));
		this.lights.add(light);
	}
	
	public static World getDefaultWorld() {
		World w = new World();
		Sphere s = new Sphere();
		Sphere s2 = new Sphere();
		
		s.getMaterial().setColor(new Color(0.8f, 1.0f, 0.6f));
		s.getMaterial().setDiffuse(0.7f);
		s.getMaterial().setSpecular(0.2f);		

		s2.setTransform(s2.getTransform().scale(0.5f, 0.5f, 0.5f));
		
		w.addObjects(s);
		w.addObjects(s2);
		
		return w;
	}
	
	public ArrayList<Light> getLight() {
		return this.lights;
	}

	public void setLight(int index, Light light) {
		this.lights.remove(index);
		this.lights.add(index, light);
	}
	
	public void setObject(int index, Shape object) {
		this.objects.remove(index);
		this.objects.add(index, object);
	}

	public void addLight(Light light) {
		this.lights.add(light);
	}

	public ArrayList<Shape> getObjects() {
		return objects;
	}
	
	public void addObjects(Shape object) {
		this.objects.add(object);
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append(this.getLight());
		
		for (Shape sphere : this.getObjects()) {
			s.append("\n" + sphere);
			
		}
		
		return s.toString();
	}
	
	public static ArrayList<Intersection> intersectWorld(World world, Ray ray) {
		ArrayList<Intersection> result = new ArrayList<Intersection>();;
		
		for (Shape object : world.getObjects()) {
			Intersection[] xs = object.intersect(ray);
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
	
	public static Color getShadeHit(World world, IntersectionPreComputedValue comp, int recursionDepth) {
		Color result = new Color(0, 0, 0);
		// isShadowed() method traverses all the lights in this world to find if the object is in shadow
		boolean inShade = world.isShadowed(comp.getOverPoint());
		for (Light light : world.getLight()) {
			result = result.add(Material.getLighting(
						comp.getObject().getMaterial(), 
						light, 
						comp.getOverPoint(), 
						comp.getEyeVector(), 
						comp.getNormalVector(), 
						inShade, 
						comp.getObject()
						)
					);
		}
		
		Color reflectedColor = world.getReflectedColor(comp, recursionDepth);
		
		return result.add(reflectedColor);
	}
	
	public static Color getColorAt(World world, Ray ray, int recursionDepth) {
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
		
		return World.getShadeHit(world, comps, recursionDepth);
	}
	
	public boolean isShadowed(Point p) {
		boolean result = false;


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
	
	public Color getReflectedColor(IntersectionPreComputedValue computed, int recursionDepth) {
		System.out.println("Recursion depth is: " + recursionDepth);
		if (recursionDepth <= 0) return new Color(0, 0, 0);
		
		recursionDepth -= 1;
		
		if (computed.getObject().getMaterial().getReflective() - 0 < World.DELTA) return new Color(0, 0, 0);
		else {
			Ray reflectRay = new Ray(computed.getOverPoint(), computed.getReflectVector());
			Color color = World.getColorAt(this, reflectRay, recursionDepth);
			
			return color.scalarMultiply(computed.getObject().getMaterial().getReflective());
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
