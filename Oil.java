package sokoban;

public class Oil extends Material {
	private static final double cohesion = 0.5;
		
	/**
	 * A param�ter�l kapott s�rl�d�si er�t cs�kkenti az anyagra jellemz� er�vel.
	 * @param c double t�pus�, ezt az �rt�ket fogja cs�kkenteni.
	 * @return double t�pussal t�r vissza, melynek �rt�ke az �j, cs�kkentett �rt�k.
	 */
	public double ModifyCohesion(double c) {
		return c - cohesion;
	}
}
