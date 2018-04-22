package sokoban;

import java.util.ArrayList;

/**
 * A játék helyszínét, a raktárat jeleníti meg.
 * A raktár mezõkbõl épül fel, amelyek egy négyzetrácsot alkotnak.
 * A raktár szélét falak határolják.
 * A raktár felelõssége nyilvántartani a pályán lévõ ládák számát, és lekérdezhetõ tõle, hogy van-e elérhetõ lépés a játékban.
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
	 * A paraméterül kapott ládát a ládákat tartalmazó tömb végére szúrja.
	 * @param c Crate típusú.
	 */
	public void AddCrate(Crate c) {
		crates.add(c);
	}
	
	/**
	 * Megadja a raktárban lévõ ládák számát.
	 * @return int típust ad vissza.
	 */
	public int GetRemainingCrates() { return remainingCrates; }
	
	/**
	 * Egy láda megszûnt valamilyen okból.
	 * @param c Crate típusú, ez a láda szûnt meg.
	 */
	public void CrateRemoved(Crate c) {
		crates.remove(c);
	}
	
	/**
	 * A paraméterül kapott mezõt beszúrja a pályába, a paraméterül megadott helyre.
	 * @param f Field típusú, ez a mezõ kerül beszúrásra.
	 * @param i int típusú, a sor száma.
	 * @param j int típusú, az oszlop száma.
	 */
	public void AddField(Field f) {
		fields.add(f);
	}
	
	
	/**
	 * Ellenõrzi, hogy van-e még érvényes lépés a játékban.
	 * @param workers Worker[] típusú, mely a játékban lévõ munkások referenciáját adja meg.
	 * @return boolean típussal tér vissza, mely akkor true, ha van olyan munkás és olyan láda, mely tud lépni.
	 */
	public boolean HasMoves(ArrayList<Worker> workers) {
		//Munkások
		boolean workersCanMove = false;
		for(Worker w:workers) {
			if(w.HasMoves())
				workersCanMove = true;
		}
		
		//Ládák
		boolean cratesCanMove = false;
		for(Crate c:crates) {
			if(c.HasMoves())
				cratesCanMove = true;
		}
	
		//Csak akkor igaz, ha a munkások és a ládák közül is tud valamelyik lépni. 
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
