

public abstract class Material {	
	/**
	 * A param�ter�l kapott mez�re ker�l az anyag.
	 * @param f Field t�pus�, erre a mez�re r�helyezi mag�t az anyag.
	 */
	public void PutOn(Field f) {
		f.SetMaterial(this);
	}
	
	public abstract double ModifyCohesion(double c);
}
