

public abstract class Material {	
	/**
	 * A paraméterül kapott mezõre kerül az anyag.
	 * @param f Field típusú, erre a mezõre ráhelyezi magát az anyag.
	 */
	public void PutOn(Field f) {
		f.SetMaterial(this);
	}
	
	public abstract double ModifyCohesion(double c);
}
