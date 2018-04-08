package sokoban;

public class Oil extends Material {
	/**
	 * A konstruktor beállítja az anyagra jellemzõ súrlódási erõ értékét.
	 */
	public Oil() {
		//A 0.5 csak egy példa érték...
		SetCohesion(0.5);
	}
	
	/**
	 * A paraméterül kapott súrlódási erõt csökkenti az anyagra jellemzõ erõvel.
	 * @param c double típusú, ezt az értéket fogja csökkenteni.
	 * @return double típussal tér vissza, melynek értéke az új, csökkentett érték.
	 */
	public double ModifyCohesion(double c) {
		return c - GetCohesion();
	}
}
