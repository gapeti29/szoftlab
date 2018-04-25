

/**
 * A mezore helyezheto osztaly, amely csokkenti a mezo tapadasat.
 */
public class Honey extends Material {
	/**
	 * A mezre jellemzo surlodasi ero. Ennyivel modositja egy mezo surlodasi erejet, ha rakerul.
	 */
	private static final double cohesion = 2;
	
	/**
	 * A parameterul kapott surlodasi erot noveli az anyagra jellemzo erovel.
	 * @param c double tipusu, ezt az erteket fogja novelni.
	 * @return double tipussal ter vissza, melynek erteke az uj, novelt ertek.
	 */
	public double ModifyCohesion(double c) {
		return c + cohesion;
	}
}
