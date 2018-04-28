package sokoban;

/**
 * A mezore helyezheto osztaly, amely csokkenti a mezo tapadasat.
 */
public class Oil extends Material {
	/**
	 * Az olajra jellemzo surlodasi ero. Ennyivel modositja egy mezo surlodasi erejet, ha rakerul.
	 */
	private static final double cohesion = 0.5;
		
	/**
	 * A parameterul kapott surlodasi erot csokkenti az anyagra jellemzo erovel.
	 * @param c double tipusu, ezt az erteket fogja csokkenteni.
	 * @return double tipussal ter vissza, melynek erteke az uj, csokkentett ertek.
	 */
	public double ModifyCohesion(double c) {
		return c - cohesion;
	}
}
