package sokoban;


public abstract class MovableThing {
	Field field;
	
	public abstract void Disappear();
	public abstract void ControlSwitch(Switch s);
	public abstract boolean PushedBy(Direction d, double s);
	public abstract boolean DirectPushedBy(Direction d, double s);
	public abstract boolean HasMoves();
	
	/**
	 * Az objektum a megadott irányba próbál meg elmozdulni.
	 * @param d Direction típusú, ebbe az irányba mozogna.
	 * @return boolean típusú, mely akkor true, ha sikeres volt a mozgás.
	 */
	public boolean Move(Direction d, double s) {
		return GetField().GetNeighbour(d).Accept(this, d, s);
	}
	
	/**
	 * Ha célmezõre ér a MovableThing, akkor ez a függvénye hívódik. Alapból nem történik semmi.
	 * @param g Erre a célmezõre lépett az objektum.
	 */
	public void AtGoal(Goal g) {}
	
	/**
	 * Visszaadja azt a mezõt, amelyen aktuálisan az objektum áll.
	 * @return	Field típussal tér vissza.
	 */
	public Field GetField() { return field; }
	
	/**
	 * A paraméterül kapott mezõt tárolja, ezen fog az objektum állni.
	 * @param f Field típusú.
	 */
	public void SetField(Field f) { field = f; }
}
