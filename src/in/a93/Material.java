package in.a93;

public class Material {
	private Color color;
	private float ambient;
	private float diffuse;
	private float specular;
	private float shininess;
	private RenderPattern pattern = null;
	
	private static final float DELTA = 0.0001f;
	
	public Material() {
		this.color = new Color(1, 1, 1);
		this.ambient = 0.1f;
		this.diffuse = 0.9f;
		this.specular = 0.5f;
		this.shininess = 200f;
	}

	public Material(Color color, float ambient, float diffuse, float specular, float shininess) {
		this.color = color;
		this.ambient = ambient;
		this.diffuse = diffuse;
		this.specular = specular;
		this.shininess = shininess;
	}
	
	public static Color getLighting(Material material, Light light, Point point, Vector eyeVector, Vector normalVector, boolean inShadow, Shape object) {
		Color color = null;
		
		if (material.getPattern() == null) color = material.getColor();			
		else color = material.getPattern().getPatternAtShape(object, point);
		
		Color effectiveColor = Color.schurProduct(color, light.getIntensity());
		Vector lightVector = Vector.normalize(light.getPosition().subtract(point));
		Color ambientColor = effectiveColor.scalarMultiply(material.getAmbient());

		if (inShadow) return ambientColor; // diffuse and specular components are ignored in shadow
		else {
			Color diffuseColor = new Color(0, 0, 0);
			Color specularColor = new Color(0, 0, 0);
			
			float lightDotNormal = Vector.dot(lightVector, normalVector);
			
			if (lightDotNormal < 0) {
				diffuseColor.setBlack();
				specularColor.setBlack();
			} else {
				diffuseColor = effectiveColor.scalarMultiply(material.getDiffuse() * lightDotNormal);
	
				//	Vector reflectVector = Vector.reflect(lightVector, normalVector);
				Vector reflectVector = Vector.reflect(lightVector.scalarMultiply(-1), normalVector);
				float reflectDotEye = Tuple.dot(reflectVector, eyeVector);
				if (reflectDotEye <= 0) {
					specularColor.setBlack();
				} else {
					float factor = (float) Math.pow(reflectDotEye, material.shininess);
					specularColor = light.getIntensity().scalarMultiply(material.getSpecular() * factor);
				}
			}
	
			return ambientColor.add(diffuseColor.add(specularColor));
		}
	}
	
	
	
	@Override
	public boolean equals(Object other) {
		if (other == null) return false;
		if (this == other) return true;
		if (getClass() != other.getClass()) return false;
		
		Material otherMaterial = (Material) other;
		return this.getColor().equals(otherMaterial.getColor())
				&& Math.abs(this.getAmbient() - otherMaterial.getAmbient()) < DELTA
				&& Math.abs(this.getDiffuse() - otherMaterial.getDiffuse()) < DELTA
				&& Math.abs(this.getSpecular()- otherMaterial.getSpecular()) < DELTA
				&& Math.abs(this.getShininess() - otherMaterial.getShininess()) < DELTA;
	}
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		
		s.append("Color: " + this.getColor());
		s.append("\nAmbient: " + this.getAmbient());
		s.append("\nDiffuse: " + this.getDiffuse());
		s.append("\nSpecular: " + this.getSpecular());
		s.append("\nShininess: " + this.getShininess());
		
		return s.toString();
	}

	/*
	 * Getters and setters
	 */
	
	public Color getColor() {
		return color;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	
	public float getAmbient() {
		return ambient;
	}
	
	public void setAmbient(float ambient) {
		this.ambient = ambient;
	}
	
	public float getDiffuse() {
		return diffuse;
	}
	
	public void setDiffuse(float diffuse) {
		this.diffuse = diffuse;
	}
	
	public float getSpecular() {
		return specular;
	}
	
	public void setSpecular(float specular) {
		this.specular = specular;
	}
	
	public float getShininess() {
		return shininess;
	}
	
	public void setShininess(float shininess) {
		this.shininess = shininess;
	}
	
	public void setPattern(RenderPattern pattern) { this.pattern = pattern; }
	public RenderPattern getPattern() { return this.pattern; }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
