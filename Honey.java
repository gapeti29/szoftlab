

public class Honey extends Material {
	private static final double cohesion = 0.5;
	
	/**
	 * A param�ter�l kapott s�rl�d�si er�t n�veli az anyagra jellemz� er�vel.
	 * @param c double t�pus�, ezt az �rt�ket fogja n�velni.
	 * @return double t�pussal t�r vissza, melynek �rt�ke az �j, n�velt �rt�k.
	 */
	public double ModifyCohesion(double c) {
		return c + cohesion;
	}
}
