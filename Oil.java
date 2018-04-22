package sokoban;

public class Oil extends Material {
	private static final double cohesion = 0.5;
		
	/**
	 * A paraméterül kapott súrlódási erõt csökkenti az anyagra jellemzõ erõvel.
	 * @param c double típusú, ezt az értéket fogja csökkenteni.
	 * @return double típussal tér vissza, melynek értéke az új, csökkentett érték.
	 */
	public double ModifyCohesion(double c) {
		return c - cohesion;
	}
}
