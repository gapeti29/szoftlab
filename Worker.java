package sokoban;

/**
 * A jatekosok altal iranyitott munkas.
 * A jatekos koreben a munkasa egy mezonyit lephet es kozben ladakat tolhat el.
 * A munkas tartja nyilvan a jatekos altal szerzett pontokat.
 * A munkasokat egy mozgo lada arrebb tudja lokni, es ha nyitott lyuk mezore lep, akkor a munkas meghal.
 * A soron levo munkas kozvetlenul nem kepes egy masik munkast eltolni.
 * A munkas rendelkezik egy toloerovel, ami azt mutatja, hogy milyen erovel kepes ladakat tolni.
 */
public class Worker extends MovableThing{
	/**
	 * A munkas pontszama.
	 */
	private int points = 0;
	/**
	 * A munkas ereje, ekkora erovel tud tolni dolgokat.
	 */
	private double strength = 1.1;
	/**
	 * A kenoanyagjai szama.
	 */
	private int materialCount = 3;
	
	/**
	 * Letrehoz egy munkast a parameterul megadott nevvel es kenoanyagok szamaval.
	 * @param s String tipusu, a munkas neve.
	 * @param material int tipusu, a kenoanyagok szama.
	 */
	public Worker(String s,int material) { name = s; materialCount=material;}
	
	/**
	 * Letrehoz egy munkast a megadott nevvel.
	 * @param s String tipusu.
	 */
	public Worker(String s) { name = s;}
	
	/**
	 * A munkas pontszamanak eggyel novelese.
	 */
	public void AddPoint() { points++; }
	
	/**
	 * Visszaadja a jatekos pontjainak a szamat.
	 * @return int tipussal ter vissza.
	 */
	public int GetPoints() { return points; }
	
	/**
	 * Visszaadja a jatekos nevet.
	 * @return String tipussal ter vissza.
	 */
	public String GetName() { return name; }
	
	/**
	 * Visszaadja a munkas erejet.
	 * @return double tipussal ter vissza.
	 */
	public double GetStrenght() { return strength; }
	
	/**
	 * A munkas megprobal az adott iranyba lepni (direktben, nem toljak).
	 * @param d Direction tipusu, ebbe az iranyba haladna.
	 * @return boolean tipussal ter vissza, mely akkor true, ha sikerult a mozgas.
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
	 * Torli magat az aktualis mezorol.
	 * null ertekre allitja a tarolt mezo erteket.
	 */
	public void Disappear() {
		Game.removeWorker(this);
		GetField().Remove(this);
		SetField(null);
		Game.reducePlayerNumber();
	}
	
	/**
	 * Kikapcsolja a parameterul kapott kapcsolot.
	 */
	public void ControlSwitch(Switch s) {
		s.TurnOff();
	}
	
	/**
	 * A munkast nem kozvetlen probaljak meg arrebb tolni, igy megprobal az adott iranyba tolodni.
	 * Ha nem sikerul arrebb tolodnia, akkor meghal.
	 * @param d Direction tipusu, ebbe az iranyba mozogna.
	 * @return boolean tipussal ter vissza, mely kulonbozo okokbol, de mindig true lesz.
	 */
	public boolean PushedBy(Direction d, double s) {
		if(!Move(d, s)) {
			Disappear();
		}
		
		return true;
	}
	
	/**
	 * A munkast nem lehet direktben eltolni, ezert mindig false ertekkel ter vissza.
	 * @return boolean tipussal ter vissza.
	 */
	public boolean DirectPushedBy(Direction d, double s) {
		return false;
	}
	
	/**
	 * A jatekos altal kivalasztott anyagot helyezi arra a mezore, amelyen eppen all.
	 * A lerakas feltetele az, hogy meg nem fogyott ki a munkas az anyagokbol.
	 * @param m String tipusu, a kivalasztott anyag neve.
	 */
	public void PutMaterial(Material m) {
		if(materialCount > 0) {
			m.PutOn(this.GetField());
			--materialCount;
		}
	}
	
	/**
	 * A munkasnak akkor van ervenyes lepese, ha barmelyik iranyba tud lepni egyet.
	 * @return boolean tipussal ter vissza, mely akkor true, ha tud lepni valamerre a munkas. 
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
