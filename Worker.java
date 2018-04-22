package sokoban;


public class Worker extends MovableThing{
	private int points = 0;
	private String name;
	private double strength = 1.1; //Random �rt�k...
	private int materialCount;
	
	public Worker(String s,int material) { name = s; materialCount=material;}
	
	public Worker(String s) { name = s;}
	
	/**
	 * A munk�s pontsz�m�nak eggyel n�vel�se.
	 */
	public void AddPoint() { ++points; }
	
	/**
	 * Visszaadja a j�t�kos pontjainak a sz�m�t.
	 * @return int t�pussal t�r vissza.
	 */
	public int GetPoints() { return points; }
	
	/**
	 * Visszaadja a j�t�kos nev�t.
	 * @return String t�pussal t�r vissza.
	 */
	public String GetName() { return name; }
	
	
	public double GetStrenght() { return strength; }
	
	/**
	 * A munk�s megpr�b�l az adott ir�nyba l�pni (direktben, nem tolj�k).
	 * @param d Direction t�pus�, ebbe az ir�nyba haladna.
	 * @return boolean t�pussal t�r vissza, mely akkor true, ha siker�lt a mozg�s.
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
	 * Egy ir�nyt v�r a felhaszn�l�t�l, majd abba az ir�nyba megpr�b�l l�pni.
	 */
	public void Step() {
		//Fejl. alatt...
		DirectMove(Direction.Right, strength);
	}
	
	/**
	 * T�rli mag�t az aktu�lis mez�r�l.
	 * null �rt�kre �ll�tja a t�rolt mez� �rt�k�t.
	 */
	public void Disappear() {
		GetField().Remove(this);
		SetField(null);
	}
	
	/**
	 * Kikapcsolja a param�ter�l kapott kapcsol�t.
	 */
	public void ControlSwitch(Switch s) {
		s.TurnOff();
	}
	
	/**
	 * A munk�st nem k�zvetlen pr�b�lj�k meg arr�bb tolni, �gy megpr�b�l az adott ir�nyba tol�dni.
	 * Ha nem siker�l arr�bb tol�dnia, akkor meghal.
	 * @param d Direction t�pus�, ebbe az ir�nyba mozogna.
	 * @return boolean t�pussal t�r vissza, mely k�l�nb�z� okokb�l, de mindig true lesz.
	 */
	public boolean PushedBy(Direction d, double s) {
		if(!Move(d, s)) {
			Disappear();
		}
		
		return true;
	}
	
	/**
	 * A munk�st nem lehet direktben eltolni, ez�rt mindig false �rt�kkel t�r vissza.
	 * @return boolean t�pussal t�r vissza.
	 */
	public boolean DirectPushedBy(Direction d, double s) {
		return false;
	}
	
	/**
	 * A j�t�kos �ltal kiv�lasztott anyagot helyezi arra a mez�re, amelyen �ppen �ll.
	 * A lerak�s felt�tele az, hogy m�g nem fogyott ki a munk�s az anyagokb�l.
	 */
	public void PutMaterial() {
		//Beolvas�s...
		
		if(materialCount > 0) {
			//Beolvasott �rt�k vizsg�lata lesz majd az if felt�tel belsej�ben...
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
	 * A munk�snak akkor van �rv�nyes l�p�se, ha b�rmelyik ir�nyba tud l�pni egyet.
	 * @return boolean t�pussal t�r vissza, mely akkor true, ha tud l�pni valamerre a munk�s. 
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
