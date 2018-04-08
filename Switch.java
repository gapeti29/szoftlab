package sokoban;

public class Switch extends Field{
	private Hole holes;
	
	/**
	 * A paraméterül kapott lyukat tudja majd állítani.
	 * @param h Hole típusú.
	 */
	public void SetHole(Hole h) { holes = h; }
	
	/**
	 * Megpróbálja elfogadni a mezõre érkezõ MovableThing típusú objektumot, és meghívja annak a kapcsoló kezelõ függvényét, ha elfogadta.
	 * @param t MovableThing típusú, ez az objektum kerülne a mezõre.
	 * @param d Direction típusú, ebbe az irányba halad a paraméterül kapott objektum.
	 * @return boolean típussal tér vissza, mely akkor true, ha elfogadta a MovableThing-et.
	 */
	public boolean Accept(MovableThing t, Direction d, double s) {
		if(super.Accept(t, d, s)){
			t.ControlSwitch(this);
			return true;
		}
		else
			return false;
	}
	
	/**
	 * Megpróbálja elfogadni a mezõre érkezõ MovableThing típusú objektumot, és meghívja annak a kapcsoló kezelõ függvényét, ha elfogadta.
	 * @param t MovableThing típusú, ez az objektum kerülne a mezõre.
	 * @param d Direction típusú, ebbe az irányba halad a paraméterül kapott objektum.
	 * @return boolean típussal tér vissza, mely akkor true, ha elfogadta a MovableThing-et.
	 */
	public boolean DirectAccept(MovableThing t, Direction d, double s) {
		if(super.DirectAccept(t, d, s)){
			t.ControlSwitch(this);
			return true;
		}
		else
			return false;
	}
	
	/**
	 * Kinyitja a kapcsolóhoz tartozó lyukat.
	 */
	public void TurnOn() { holes.Open(); }
	
	/**
	 * Becsukja a kapcsolóhoz tartozó lyukat.
	 */
	public void TurnOff() { holes.Close(); }
}
