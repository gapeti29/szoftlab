package sokoban;

import java.util.HashMap;
import java.util.Map;

public class Field {
	private MovableThing thing;
	private Map<Direction, Field> neighbours = new HashMap<Direction, Field>();
	private Warehouse warehouse;
	
	/*Pálya létrehozására.
	 *Extra mezõ az, amelyen már van MovableThing, vagy pedig a Field leszármazottja. 
	 */
	private boolean extra = false;
	
	/**
	 * A paraméterül kapott objektumot elfogadja, ha tudja.
	 * @param t MovableThing típusú, ez az objektum kerülne a mezõre.
	 * @param d Direction típusú, ebbe az irányba halad a paraméterül kapott objektum.
	 * @return boolean típussal tér vissza, mely akkor true, ha elfogadta a mezõ az objektumot.
	 */
	public boolean Accept(MovableThing t, Direction d) {
		if(thing != null) {
			//Ha sikerült arrébb tolni a mezõn lévõ dolgot.
			if(thing.PushedBy(d)){
				Accepted(t);
				return true;
			}
			else
				return false;
		}
		else {
			Accepted(t);
			return true;
		}
	}
	
	/**
	 * A paraméterül kapott objektumot elfogadja, ha tudja.
	 * @param t MovableThing típusú, ez az objektum kerülne a mezõre.
	 * @param d Direction típusú, ebbe az irányba halad a paraméterül kapott objektum.
	 * @return boolean típussal tér vissza, mely akkor true, ha elfogadta a mezõ az objektumot.
	 */
	public boolean DirectAccept(MovableThing t, Direction d) {
		if(thing != null) {
			//Ha sikerült arrébb tolni a mezõn lévõ dolgot.
			if(thing.DirectPushedBy(d)){
				Accepted(t);
				return true;
			}
			else
				return false;
		}
		else {
			Accepted(t);
			return true;
		}
	}
	
	/**
	 * A paraméterül kapott dolog rákerülhet a mezõre.
	 * @param t MovableThing típusú, ez az objektum kerül a mezõre.
	 */
	private void Accepted(MovableThing t) {
		//Régi mezõrõl törli az objektumot.
		try {
			t.GetField().Remove(t);
		} catch(NullPointerException e) {
			/*
			 * Nem csinálunk semmit.
			 * A kivétel azt jelzi, hogy a MovableThing t nem volt még egy mezõn se, most kerül a pályára.
			 */
		}
		//Kölcsönösen eltárolják egymást.
		thing = t;
		thing.SetField(this);
	}
	
	/**
	 * A paraméterül kapott dolog törlése a mezõrõl.
	 * @param t MovableThing típusú, törlõdik a things tömbbõl.
	 */
	public void Remove(MovableThing t) {
		if(thing == t)
			thing = null;
	}
	
	/**
	 * Visszaadja a paraméterül kapott irányban lévõ szomszédos mezõt.
	 * @param d Ebben az irányban helyezkedik el a szomszédos mezõ.
	 * @return	Field típust ad vissza.
	 */
	public Field GetNeighbour(Direction d) {
		return neighbours.get(d);
	}
	
	/**
	 * Eltárolja a mezõ a paraméterül kapott másik mezõt, a paraméterül kapott irányban lévõ szomszédjaként.
	 * @param f Field típusú, ezt a mezõt tárolja szomszédként.
	 * @param d Direction típusú, ebben az irányban lesz a szomszéd.
	 */
	public void SetNeighbour(Field f, Direction d) {
		neighbours.put(d, f);
	}
	
	/**
	 * Visszaadja azt a raktárépületet, amelyben a mezõ van.
	 * @return Warehouse típussal tér vissza.
	 */
	public Warehouse GetWarehouse() { return warehouse; }
	
	/**
	 * Eltárolja a paraméterül kapott raktárépületet.
	 * @param w Warehouse típusú, ebben szerepel a mezõ.
	 */
	public void SetWarehouse(Warehouse w) { warehouse = w; }
	
	/**
	 * Megadja, hogy az adott mezõ extra-e.
	 * Extra mezõnek számít az a mezõ, melyen már van MovableThing, vagy pedig a Field leszármazottja.
	 * @return boolean típussal tér vissza, mely akkor true, ha extra a mezõ.
	 */
	public boolean IsExtra() {
		if(extra)
			return true;
		else
			return false;
	}
	
	/**
	 * Extra mezõvé állítja be a mezõt.
	 */
	public void MakeExtra() {
		extra = true;
	}
	
	/**
	 * Ellenõrzi, hogy van-e érvényes lépés a paraméterül kapott irányba.
	 * @param d Direction típusú, ebbe az irányba haladna egy MovableThing.
	 * @return
	 */
	public boolean CheckMove(Direction d) {
		if(thing != null) {
			if(GetNeighbour(d).CheckMove(d)) {
				return true;
			}
			
			return false;
		}
		else {
			return true;
		}
	}
}
