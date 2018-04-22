package sokoban;


public abstract class MovableThing {
	Field field;
	
	public abstract void Disappear();
	public abstract void ControlSwitch(Switch s);
	public abstract boolean PushedBy(Direction d, double s);
	public abstract boolean DirectPushedBy(Direction d, double s);
	public abstract boolean HasMoves();
	
	/**
	 * Az objektum a megadott ir�nyba pr�b�l meg elmozdulni.
	 * @param d Direction t�pus�, ebbe az ir�nyba mozogna.
	 * @return boolean t�pus�, mely akkor true, ha sikeres volt a mozg�s.
	 */
	public boolean Move(Direction d, double s) {
		return GetField().GetNeighbour(d).Accept(this, d, s);
	}
	
	/**
	 * Ha c�lmez�re �r a MovableThing, akkor ez a f�ggv�nye h�v�dik. Alapb�l nem t�rt�nik semmi.
	 * @param g Erre a c�lmez�re l�pett az objektum.
	 */
	public void AtGoal(Goal g) {}
	
	/**
	 * Visszaadja azt a mez�t, amelyen aktu�lisan az objektum �ll.
	 * @return	Field t�pussal t�r vissza.
	 */
	public Field GetField() { return field; }
	
	/**
	 * A param�ter�l kapott mez�t t�rolja, ezen fog az objektum �llni.
	 * @param f Field t�pus�.
	 */
	public void SetField(Field f) { field = f; }
}
