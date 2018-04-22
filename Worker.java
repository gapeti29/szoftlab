package sokoban;


public class Worker extends MovableThing{
	private int points = 0;
	private String name;
	private double strength = 1.1; //Random érték...
	private int materialCount;
	
	public Worker(String s,int material) { name = s; materialCount=material;}
	
	public Worker(String s) { name = s;}
	
	/**
	 * A munkás pontszámának eggyel növelése.
	 */
	public void AddPoint() { ++points; }
	
	/**
	 * Visszaadja a játékos pontjainak a számát.
	 * @return int típussal tér vissza.
	 */
	public int GetPoints() { return points; }
	
	/**
	 * Visszaadja a játékos nevét.
	 * @return String típussal tér vissza.
	 */
	public String GetName() { return name; }
	
	
	public double GetStrenght() { return strength; }
	
	/**
	 * A munkás megpróbál az adott irányba lépni (direktben, nem tolják).
	 * @param d Direction típusú, ebbe az irányba haladna.
	 * @return boolean típussal tér vissza, mely akkor true, ha sikerült a mozgás.
	 */
	public boolean DirectMove(Direction d, double s) {
		if(GetField().GetNeighbour(d).DirectAccept(this, d, s))
		{
			return true;
		}
		else
			return false;
	}
	
	/**
	 * Egy irányt vár a felhasználótól, majd abba az irányba megpróbál lépni.
	 */
	public void Step() {
		//Fejl. alatt...
		DirectMove(Direction.Right, strength);
	}
	
	/**
	 * Törli magát az aktuális mezõrõl.
	 * null értékre állítja a tárolt mezõ értékét.
	 */
	public void Disappear() {
		GetField().Remove(this);
		SetField(null);
	}
	
	/**
	 * Kikapcsolja a paraméterül kapott kapcsolót.
	 */
	public void ControlSwitch(Switch s) {
		s.TurnOff();
	}
	
	/**
	 * A munkást nem közvetlen próbálják meg arrébb tolni, így megpróbál az adott irányba tolódni.
	 * Ha nem sikerül arrébb tolódnia, akkor meghal.
	 * @param d Direction típusú, ebbe az irányba mozogna.
	 * @return boolean típussal tér vissza, mely különbözõ okokból, de mindig true lesz.
	 */
	public boolean PushedBy(Direction d, double s) {
		if(!Move(d, s)) {
			Disappear();
		}
		
		return true;
	}
	
	/**
	 * A munkást nem lehet direktben eltolni, ezért mindig false értékkel tér vissza.
	 * @return boolean típussal tér vissza.
	 */
	public boolean DirectPushedBy(Direction d, double s) {
		return false;
	}
	
	/**
	 * A játékos által kiválasztott anyagot helyezi arra a mezõre, amelyen éppen áll.
	 * A lerakás feltétele az, hogy még nem fogyott ki a munkás az anyagokból.
	 */
	public void PutMaterial() {
		//Beolvasás...
		
		if(materialCount > 0) {
			//Beolvasott érték vizsgálata lesz majd az if feltétel belsejében...
			if("1" == "Oil") {
				Oil oil = new Oil();
				oil.PutOn(GetField());
			}
			else {
				Honey honey = new Honey();
				honey.PutOn(GetField());
			}
			--materialCount;
		}
	}
	
	/**
	 * A munkásnak akkor van érvényes lépése, ha bármelyik irányba tud lépni egyet.
	 * @return boolean típussal tér vissza, mely akkor true, ha tud lépni valamerre a munkás. 
	 */
	public boolean HasMoves() {
		if( GetField().CheckMove(Direction.Up) ||
			GetField().CheckMove(Direction.Down) ||
			GetField().CheckMove(Direction.Left) ||
			GetField().CheckMove(Direction.Right) ) {
				return true;
		}
		else
			return false;
	}
}
