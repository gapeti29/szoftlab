import java.util.ArrayList;

public class Warehouse {
	private int remainingCrates;
	private ArrayList<Field> fields=new ArrayList<Field>();
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
	public void AddField(Field name) {
		fields.add(name);
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
	public Field findField(String name) {
		for(Field f:fields) {
			if(f.getName().compareTo(name)==0)return f;
		}
		return null;
	}
	public void listBox() {
		for(Crate c: crates)
			System.out.println(c.GetField().getName());
	}
	public void listCohesion() {
		for(Field f:fields) {
			System.out.println(f.getName()+": "+f.getCohesion());
		}
	}
	public void listFieldState() {
		for(Field f:fields) {
			f.List();
		}
	}
}
