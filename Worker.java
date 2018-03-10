package sokoban;

public class Worker extends MovableThing{
	private int points;
	private String name;
	
	/**
	 * A munkás pontszámának eggyel növelése.
	 */
	public void AddPoint() { ++points; }
	
	/**
	 * A munkás megpróbál az adott irányba lépni (direktben, nem tolják).
	 * @param d Direction típusú, ebbe az irányba haladna.
	 * @return boolean típussal tér vissza, mely akkor true, ha sikerült a mozgás.
	 */
	public boolean DirectMove(Direction d) {
		if(GetField().GetNeighbour(d).DirectAccept(this, d))
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
		//Szkeletonban csak jobbra lép a munkás
		DirectMove(Direction.Right);
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
	public boolean PushedBy(Direction d) {
		if(!Move(d)) {
			Disappear();
		}
		
		return true;
	}
	
	/**
	 * A munkást nem lehet direktben eltolni, ezért mindig false értékkel tér vissza.
	 * @return boolean típussal tér vissza.
	 */
	public boolean DirectPushedBy(Direction d) {
		return false;
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
