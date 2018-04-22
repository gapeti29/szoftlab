package sokoban;

import java.util.ArrayList;

/**
 * A j�t�k helysz�n�t, a rakt�rat jelen�ti meg.
 * A rakt�r mez�kb�l �p�l fel, amelyek egy n�gyzetr�csot alkotnak.
 * A rakt�r sz�l�t falak hat�rolj�k.
 * A rakt�r felel�ss�ge nyilv�ntartani a p�ly�n l�v� l�d�k sz�m�t, �s lek�rdezhet� t�le, hogy van-e el�rhet� l�p�s a j�t�kban.
 */
public class Warehouse {
	/**
	 * A meg jatekban levo ladak szama.
	 */
	private int remainingCrates;
	/**
	 * A mezok, amelyekbol a palya all.
	 */
	private ArrayList<Field> fields=new ArrayList<Field>();
	/**
	 * A palyan levo ladak referenciaja.
	 */
	private ArrayList<Crate> crates=new ArrayList<Crate>();
	
	
	/**
	 * A param�ter�l kapott l�d�t a l�d�kat tartalmaz� t�mb v�g�re sz�rja.
	 * @param c Crate t�pus�.
	 */
	public void AddCrate(Crate c) {
		crates.add(c);
	}
	
	/**
	 * Megadja a rakt�rban l�v� l�d�k sz�m�t.
	 * @return int t�pust ad vissza.
	 */
	public int GetRemainingCrates() { return remainingCrates; }
	
	/**
	 * Egy l�da megsz�nt valamilyen okb�l.
	 * @param c Crate t�pus�, ez a l�da sz�nt meg.
	 */
	public void CrateRemoved(Crate c) {
		crates.remove(c);
	}
	
	/**
	 * A param�ter�l kapott mez�t besz�rja a p�ly�ba, a param�ter�l megadott helyre.
	 * @param f Field t�pus�, ez a mez� ker�l besz�r�sra.
	 * @param i int t�pus�, a sor sz�ma.
	 * @param j int t�pus�, az oszlop sz�ma.
	 */
	public void AddField(Field f) {
		fields.add(f);
	}
	
	
	/**
	 * Ellen�rzi, hogy van-e m�g �rv�nyes l�p�s a j�t�kban.
	 * @param workers Worker[] t�pus�, mely a j�t�kban l�v� munk�sok referenci�j�t adja meg.
	 * @return boolean t�pussal t�r vissza, mely akkor true, ha van olyan munk�s �s olyan l�da, mely tud l�pni.
	 */
	public boolean HasMoves(ArrayList<Worker> workers) {
		//Munk�sok
		boolean workersCanMove = false;
		for(Worker w:workers) {
			if(w.HasMoves())
				workersCanMove = true;
		}
		
		//L�d�k
		boolean cratesCanMove = false;
		for(Crate c:crates) {
			if(c.HasMoves())
				cratesCanMove = true;
		}
	
		//Csak akkor igaz, ha a munk�sok �s a l�d�k k�z�l is tud valamelyik l�pni. 
		if(workersCanMove && cratesCanMove)
			return true;
		else
			return false;
	}	
	
	/**
	 * Megkeresi azt a mezot, amelynek a nevet parameterul kapta.
	 * @param name String tipusu, a keresett mezo neve.
	 * @return Field tipussal ter vissza.
	 */
	public Field findField(String name) {
		for(Field f:fields) {
			if(f.getName().compareTo(name)==0)return f;
		}
		return null;
	}
	
	/**
	 * Kilistazza a ladak neveit.
	 */
	public void listBox() {
		for(Crate c: crates)
			System.out.println(c.GetField().getName());
	}
	
	/**
	 * Kilistazza a mezok neveit a hozzajuk tartozo surlodasi erovel egyutt.
	 */
	public void listCohesion() {
		for(Field f:fields) {
			System.out.println(f.getName()+": "+f.getCohesion());
		}
	}
	
	/**
	 * Kilistazza a mezok allapotat.
	 */
	public void listFieldState() {
		for(Field f:fields) {
			f.List();
		}
	}
}
