

public class Crate extends MovableThing{
	
	/**
	 * �rtes�ti a rakt�r�p�letet, hogy megsemmis�lt.
	 * T�rli mag�t az aktu�lis mez�r�l.
	 * null �rt�kre �ll�tja a t�rolt mez� �rt�k�t.
	 */
	public void Disappear() {
		GetField().GetWarehouse().CrateRemoved(this);
		GetField().Remove(this);
		SetField(null);
	}
	
	/**
	 * Bekapcsolja a param�ter�l kapott kapcsol�t.
	 * @param s Switch t�pus�, ezt a kapcsol�t fogja bekapcsolni.
	 */
	public void ControlSwitch(Switch s) {
		s.TurnOn();
	}
	
	/**
	 * A l�d�t arr�bb akarj�k tolni, ez�rt megpr�b�l az adott ir�nyban arr�bb tol�dni.
	 * @param d Direction t�pus�, ebbe az ir�nyba tol�dna a l�da.
	 * @return boolean t�pus�, true �rt�kkel t�r vissza, ha sikeres volt a mozg�s.
	 */
	public boolean PushedBy(Direction d, double s)  {
		s = GetField().ApplyCohesion(s);
		//Csak akkor tol�dik arr�bb a l�da, ha megfelel� er�vel tolj�k, teh�t m�g pozit�v az s er�.
		if(s > 0) {
			return Move(d, s);
		}
		else {
			return false;
		}
	}
	
	/**
	 * A l�d�t lehet direktben tolni, ez�rt megpr�b�l az adott ir�nyban arr�bb tol�dni.
	 * @param d Direction t�pus�, ebbe az ir�nyba tol�dna a l�da.
	 * @return boolean t�pus�, true �rt�kkel t�r vissza, ha sikeres volt a mozg�s.
	 */
	public boolean DirectPushedBy(Direction d, double s) {
		s = GetField().ApplyCohesion(s);
		//Csak akkor tol�dik arr�bb a l�da, ha megfelel� er�vel tolj�k, teh�t m�g pozit�v az s er�.
		if(s > 0) {
			return Move(d, s);
		}
		else {
			return false;
		}
	}
	
	/**
	 * A l�da c�lmez�re �rt, err�l �rtes�ti a param�ter�l kapott c�lmez�t, majd t�rli mag�t.
	 * @param g Goal t�pus�, erre a c�lmez�re �rt a l�da.
	 */
	public void AtGoal(Goal g) {
		g.CrateDelivered();
		Disappear();
	}
	
	/**
	 * A l�danak akkor van �rv�nyes l�p�se, ha f�ggelegesen, vagy v�zszintesen el tud mozdulni.
	 * @return boolean t�pussal t�r vissza, melynek akkor true az �rt�ke, ha el tud mozdulni a l�da.
	 */
	public boolean HasMoves() {
		if( ( GetField().CheckMove(Direction.Up) && GetField().CheckMove(Direction.Down) ) ||
			( GetField().CheckMove(Direction.Left) && GetField().CheckMove(Direction.Right) ) ) {
				return true;
		}
		else
			return false;
	}
}
