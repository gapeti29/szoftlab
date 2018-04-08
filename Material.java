package sokoban;

public abstract class Material {
	private double cohesion;
	
	/**
	 * Visszaadja az anyagra jellemzõ súrlódási erõt.
	 * @return double típussal tér vissza, melynek értéke a súrlódási erõ.
	 */
	public double GetCohesion() { return cohesion; }
	
	/**
	 * Eltárolja az új súrlódási erõ értékét.
	 * @param c double típusú, mely az új súrlódási erõ értékét adja meg.
	 */
	public void SetCohesion(double c) { cohesion = c; } 
	
	/**
	 * A paraméterül kapott mezõre kerül az anyag.
	 * @param f Field típusú, erre a mezõre ráhelyezi magát az anyag.
	 */
	public void PutOn(Field f) {
		f.SetMaterial(this);
	}
	
	public abstract double ModifyCohesion(double c);
}
