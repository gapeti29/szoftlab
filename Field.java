package sokoban;

import java.util.HashMap;
import java.util.Map;

/**
 * A mezo nyilvantartja a rajta allo munkast vagy ladat, es ismeri a szomszedait, akik iranyonkent lekerdezhetoek tole.
 * A mezo elfogadhat munkasokat vagy ladakat es el is tavolithatja oket magarol.
 * A mezo felelossege, hogy egyszerre csak egy lada vagy munkas lehessen rajta.
 * A mezo rendelkezik egy tapadassal, melyet a ra kerulo anyagok modosithatnak.
 */
public class Field {
	/**
	 * A mezon levo Movablething (mar ha van).
	 */
	private MovableThing thing;
	/**
	 * A mezo szomszedai.
	 */
	private Map<Direction, Field> neighbours = new HashMap<Direction, Field>();
	/**
	 * A mezot tartalmazo Warehouse referenciaja.
	 */
	private Warehouse warehouse;
	/**
	 * A mezon levo Material (mar ha van).
	 */
	private Material material;
	/**
	 * A mezo surlodasi ereje.
	 */
	private double cohesion=0;
	/**
	 * A mezo neve (azonositoja).
	 */
	protected String name;
	
	/**
	 * Parameter nelkuli konstruktor.
	 * A szomszedokat null ertekkel allitja be.
	 */
	public Field() {
		thing=null;
		neighbours.put(Direction.Up, null);
		neighbours.put(Direction.Down, null);
		neighbours.put(Direction.Left, null);
		neighbours.put(Direction.Right, null);
	}

	/**
	 * A konstruktor kap egy azonosito nevet.
	 * @param ID String tipusu.
	 */
	public Field(String ID) {name=ID;}

	/**
	 * A parameterul kapott objektumot elfogadja, ha tudja.
	 * @param t MovableThing tipusu, ez az objektum kerulne a mezore.
	 * @param d Direction tipusu, ebbe az iranyba halad a parameterul kapott objektum.
	 * @param s double tipusu, a toloero.
	 * @return boolean tipussal ter vissza, mely akkor true, ha elfogadta a mezo az objektumot.
	 */
	public boolean Accept(MovableThing t, Direction d, double s) {
		if(thing == null) {
			thing = t;
			thing.SetField(this);
			return true;
		}
		else {
			if(this.GetNeighbour(d).Accept(thing, d, s-cohesion)) {
				thing=t;
				thing.SetField(this);
				return true;
			}else
			{
				return false;
			}
		}
	}
	
	/**
	 * A parameterul kapott objektumot elfogadja, ha tudja.
	 * @param t MovableThing tipusu, ez az objektum kerulne a mezore.
	 * @param d Direction tipusu, ebbe az iranyba halad a parameterul kapott objektum.
	 * @param s double tipusu, a toloero.
	 * @return boolean tipussal ter vissza, mely akkor true, ha elfogadta a mezo az objektumot.
	 */
	public boolean DirectAccept(MovableThing t, Direction d, double s) {
		if(thing == null) {
			thing = t;
			thing.SetField(this);
			return true;
		}
		else {
			if(thing.DirectPushedBy(d, s)) {
				if(this.GetNeighbour(d).Accept(thing, d, s-cohesion)) {
					thing=t;
					thing.SetField(this);
					return true;
				}else
				{
					return false;
				}
			}
			else
			{
				return false;
			}
		}
	}
	
	/**
	 * A parameterul kapott dolog rakerulhet a mezore.
	 * @param t MovableThing tipusu, ez az objektum kerul a mezore.
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
	 * A parameterul kapott dolog torlese a mezorol.
	 * @param t MovableThing tipusu, torlodik a things tombbol.
	 */
	public void Remove(MovableThing t) {
		if(thing == t)
			thing = null;
	}
	
	/**
	 * Visszaadja a parameterul kapott iranyban levo szomszedos mezot.
	 * @param d Ebben az iranyban helyezkedik el a szomszedos mezo.
	 * @return	Field tipust ad vissza.
	 */
	public Field GetNeighbour(Direction d) {
		return neighbours.get(d);
	}
	
	/**
	 * Eltarolja a mezo a parameterul kapott masik mezot, a parameterul kapott iranyban levo szomszedjakent.
	 * @param f Field tipusu, ezt a mezot tarolja szomszedkent.
	 * @param d Direction tipusu, ebben az iranyban lesz a szomszed.
	 */
	public void SetNeighbour(Field f, Direction d) {
		neighbours.put(d, f);
	}
	
	/**
	 * Visszaadja azt a raktarepuletet, amelyben a mezo van.
	 * @return Warehouse tipussal ter vissza.
	 */
	public Warehouse GetWarehouse() { return warehouse; }
	
	/**
	 * Eltarolja a parameterul kapott raktarepuletet.
	 * @param w Warehouse tipusu, ebben szerepel a mezo.
	 */
	public void SetWarehouse(Warehouse w) { warehouse = w; }
	
	/**
	 * Visszaadja a mezon levo dolgot.
	 * @return MovableThing tipussal ter vissza.
	 */
	public MovableThing GetThing() { return thing; }
	
	/**
	 * A parameterul kapott dolgot eltarolja a mezo.
	 * @param t MovableThing tipusu.
	 */
	public void SetThing(MovableThing t) { thing = t; }
	
	/**
	 * A munkas toloerejet csokkenti a mezo surlodasi erejevel.
	 * @param s double tipusu, ez a toloero
	 * @return double tipussal ter vissza, melynek erteke a fenn marado toloero.
	 */		
	public double ApplyCohesion(double s) {
		return s - cohesion;
	}
	
	/**
	 * Eltarolja a parameterul kapott anyagot.
	 * @param m Material tipusu, ez az anyag lesz a mezon.
	 */
	public void SetMaterial(Material m) { 
		material = m;
		cohesion=m.ModifyCohesion(cohesion);
	}

	/**
	 * Visszaadja a mezon levo anyagot.
	 * @return Material tipussal ter vissza.
	 */
	public Material GetMaterial() {return material;}

	/**
	 * Visszaadja a mezo surlodasi erejet.
	 * @return double tipussal ter vissza.
	 */
	public double getCohesion() {return cohesion;}
	
	/**
	 * Ellenorzi, hogy van-e ervenyes lepes a parameterul kapott iranyba.
	 * @param d Direction tipusu, ebbe az iranyba haladna egy MovableThing.
	 * @return
	 */
	public boolean CheckMove(Direction d) {
		if(GetNeighbour(d)!=null) {
			if(GetNeighbour(d).CheckMove(d)) {
				return true;
			}
			return false;
		}
		else {
			return true;
		}
	}

	/**
	 * Visszaadja a mezo nevet.
	 * @return String tipussal ter vissza.
	 */
	public String getName() {return name;}
	public void List() {}
}
