package sokoban;

public class Honey extends Material {
	public Honey() {
		//A 0.5 csak egy példa érték...
		SetCohesion(0.5);
	}
	
	/**
	 * A paraméterül kapott súrlódási erõt növeli az anyagra jellemzõ erõvel.
	 * @param c double típusú, ezt az értéket fogja növelni.
	 * @return double típussal tér vissza, melynek értéke az új, növelt érték.
	 */
	public double ModifyCohesion(double c) {
		return c + GetCohesion();
	}
}
