

import java.util.HashMap;
import java.util.Map;

public class Field {
	private MovableThing thing;
	private Map<Direction, Field> neighbours = new HashMap<Direction, Field>();
	private Warehouse warehouse;
	private Material material;
	private double cohesion;
	protected String name;
	
	/**
	 * A param�ter�l kapott objektumot elfogadja, ha tudja.
	 * @param t MovableThing t�pus�, ez az objektum ker�lne a mez�re.
	 * @param d Direction t�pus�, ebbe az ir�nyba halad a param�ter�l kapott objektum.
	 * @return boolean t�pussal t�r vissza, mely akkor true, ha elfogadta a mez� az objektumot.
	 */
	public Field() {
		neighbours.put(Direction.Up, null);
		neighbours.put(Direction.Down, null);
		neighbours.put(Direction.Left, null);
		neighbours.put(Direction.Right, null);
	}
	public Field(String azzonisito) {name=azzonisito;}
	public boolean Accept(MovableThing t, Direction d, double s) {
		if(thing != null) {
			//Ha siker�lt arr�bb tolni a mez�n l�v� dolgot.
			if(thing.PushedBy(d, s)){
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
	 * A param�ter�l kapott objektumot elfogadja, ha tudja.
	 * @param t MovableThing t�pus�, ez az objektum ker�lne a mez�re.
	 * @param d Direction t�pus�, ebbe az ir�nyba halad a param�ter�l kapott objektum.
	 * @return boolean t�pussal t�r vissza, mely akkor true, ha elfogadta a mez� az objektumot.
	 */
	public boolean DirectAccept(MovableThing t, Direction d, double s) {
		if(thing != null) {
			//Ha siker�lt arr�bb tolni a mez�n l�v� dolgot.
			if(thing.DirectPushedBy(d, s)){
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
	 * A param�ter�l kapott dolog r�ker�lhet a mez�re.
	 * @param t MovableThing t�pus�, ez az objektum ker�l a mez�re.
	 */
	private void Accepted(MovableThing t) {
		//R�gi mez�r�l t�rli az objektumot.
		try {
			t.GetField().Remove(t);
		} catch(NullPointerException e) {
			/*
			 * Nem csin�lunk semmit.
			 * A kiv�tel azt jelzi, hogy a MovableThing t nem volt m�g egy mez�n se, most ker�l a p�ly�ra.
			 */
		}
		//K�lcs�n�sen elt�rolj�k egym�st.
		thing = t;
		thing.SetField(this);
	}
	
	/**
	 * A param�ter�l kapott dolog t�rl�se a mez�r�l.
	 * @param t MovableThing t�pus�, t�rl�dik a things t�mbb�l.
	 */
	public void Remove(MovableThing t) {
		if(thing == t)
			thing = null;
	}
	
	/**
	 * Visszaadja a param�ter�l kapott ir�nyban l�v� szomsz�dos mez�t.
	 * @param d Ebben az ir�nyban helyezkedik el a szomsz�dos mez�.
	 * @return	Field t�pust ad vissza.
	 */
	public Field GetNeighbour(Direction d) {
		return neighbours.get(d);
	}
	
	/**
	 * Elt�rolja a mez� a param�ter�l kapott m�sik mez�t, a param�ter�l kapott ir�nyban l�v� szomsz�djak�nt.
	 * @param f Field t�pus�, ezt a mez�t t�rolja szomsz�dk�nt.
	 * @param d Direction t�pus�, ebben az ir�nyban lesz a szomsz�d.
	 */
	public void SetNeighbour(Field f, Direction d) {
		neighbours.put(d, f);
	}
	
	/**
	 * Visszaadja azt a rakt�r�p�letet, amelyben a mez� van.
	 * @return Warehouse t�pussal t�r vissza.
	 */
	public Warehouse GetWarehouse() { return warehouse; }
	
	/**
	 * Elt�rolja a param�ter�l kapott rakt�r�p�letet.
	 * @param w Warehouse t�pus�, ebben szerepel a mez�.
	 */
	public void SetWarehouse(Warehouse w) { warehouse = w; }
	
	/**
	 * Visszaadja a mez�n l�v� dolgot.
	 * @return MovableThing t�pussal t�r vissza.
	 */
	public MovableThing GetThing() { return thing; }
	
	/**
	 * A param�ter�l kapott dolgot elt�rolja a mez�.
	 * @param t MovableThing t�pus�.
	 */
	public void SetThing(MovableThing t) { thing = t; }
	
	/**
	 * A munk�s tol�erej�t cs�kkenti a mez� s�rl�d�si erej�vel.
	 * @param s double t�pus�, ez a tol�er�
	 * @return double t�pussal t�r vissza, melynek �rt�ke a fenn marad� tol�er�.
	 */	
	public double ApplyCohesion(double s) {
		return s - cohesion;
	}
	
	/**
	 * Elt�rolja a param�ter�l kapott anyagot.
	 * @param m Material t�pus�, ez az anyag lesz a mez�n.
	 */
	public void SetMaterial(Material m) { material = m; }
	public Material GetMaterial() {return material;}
	public double getCohesion() {return cohesion;}
	
	/**
	 * Ellen�rzi, hogy van-e �rv�nyes l�p�s a param�ter�l kapott ir�nyba.
	 * @param d Direction t�pus�, ebbe az ir�nyba haladna egy MovableThing.
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
	public String getName() {return name;}
	public void List() {}
}
