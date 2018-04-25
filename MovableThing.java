

/**
 * Absztrakt ososztaly, amely a jatekban talalhato mozgo dolgokat foglalja magaba.
 * Minden mozgo dolog egy idoben egy mezon all, es kepes a szomszedos mezokre mozogni, a kapcsolot allitani.
 * A kulonbozo tipusu mozgo dolgok a celnal kulonbozoen viselkednek.
 */
public abstract class MovableThing {
	/**
	 * A mezo, amelyen a MovableThing all.
	 */
	Field field;
	/**
	 * A MovableThing neve.
	 */
	protected String name;
	
	public abstract void Disappear();
	public abstract void ControlSwitch(Switch s);
	public abstract boolean PushedBy(Direction d, double s);
	public abstract boolean DirectPushedBy(Direction d, double s);
	public abstract boolean HasMoves();
	
	/**
	 * Az objektum a megadott iranyba probal meg elmozdulni.
	 * @param d Direction tipusu, ebbe az iranyba mozogna.
	 * @return boolean tipusu, mely akkor true, ha sikeres volt a mozgas.
	 */
	public boolean Move(Direction d, double s) {
		return GetField().GetNeighbour(d).Accept(this, d, s);
	}
	
	/**
	 * Ha celmezore er a MovableThing, akkor ez a fuggvenye hivodik. Alapbol nem tortenik semmi.
	 * @param g Erre a celmezore lepett az objektum.
	 */
	public void AtGoal(Goal g) {}
	
	/**
	 * Visszaadja azt a mezot, amelyen aktualisan az objektum all.
	 * @return	Field tipussal ter vissza.
	 */
	public Field GetField() { return field; }
	
	/**
	 * A parameterul kapott mezot tarolja, ezen fog az objektum allni.
	 * @param f Field tipusu.
	 */
	public void SetField(Field f) { field = f; }

	/**
	 * Visszaadja a MovableThing nevet.
	 * @return String tipussal ter vissza.
	 */
	public String getName() {
		return name;
	}
}
