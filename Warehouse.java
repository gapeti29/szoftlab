package sokoban;

import java.util.ArrayList;

/**
 * A jatek helyszinet, a raktarat jeleniti meg.
 * A raktar mezokbol epul fel, amelyek egy negyzetracsot alkotnak.
 * A raktar szelet falak hataroljak.
 * A raktar felelossege nyilvantartani a palyan levo ladak szamat, es lekerdezheto tole, hogy van-e elerheto lepes a jatekban.
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
	 * A parameterul kapott ladat a ladakat tartalmazo tomb vegere szurja.
	 * @param c Crate tipusu.
	 */
	public void AddCrate(Crate c) {
		crates.add(c);
	}
	
	/**
	 * Megadja a raktarban levo ladak szamat.
	 * @return int tipust ad vissza.
	 */
	public int GetRemainingCrates() { return remainingCrates; }
	
	/**
	 * Egy lada megszunt valamilyen okbol.
	 * @param c Crate tipusu, ez a lada szunt meg.
	 */
	public void CrateRemoved(Crate c) {
		crates.remove(c);
	}
	
	/**
	 * A parameterul kapott mezot beszurja a palyaba, a parameterul megadott helyre.
	 * @param f Field tipusu, ez a mezo kerul beszurasra.
	 * @param i int tipusu, a sor szama.
	 * @param j int tipusu, az oszlop szama.
	 */
	public void AddField(Field f) {
		fields.add(f);
		f.SetWarehouse(this);
	}

	/**
	 * Torli a parameterul kapott munkast.
	 * @param w Worker tipusu.
	 */
	public void removeWorker(Worker w) {
		Game.removeWorker(w);
	}
	
	
	/**
	 * Ellenorzi, hogy van-e meg ervenyes lepes a jatekban.
	 * @param workers Worker[] tipusu, mely a jatekban levo munkasok referenciajat adja meg.
	 * @return boolean tipussal ter vissza, mely akkor true, ha van olyan munkas es olyan lada, mely tud lepni.
	 */
	public boolean HasMoves(ArrayList<Worker> workers) {
		//Munkasok
		boolean workersCanMove = false;
		for(Worker w:workers) {
			if(w.HasMoves())
				workersCanMove = true;
		}
		
		//Ladak
		boolean cratesCanMove = false;
		for(Crate c:crates) {
			if(c.HasMoves())
				cratesCanMove = true;
		}
	
		//Csak akkor igaz, ha a munkasok es a ladak kozul is tud valamelyik lepni.
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
	 * Megkeresi a nev alapjan a ladat.
	 * @param name String tipusu.
	 * @return Crate tipussal ter vissza.
	 */
	public Crate findCrate(String name) {
		for(Crate c:crates) {
			if(c.getName().compareTo(name)==0)return c;
		}
		return null;
	}

	/**
	 * Kilistazza a ladak neveit.
	 */
	public void listBox() {
		if(crates.size() != 0) {
			for(Crate c: crates)
				System.out.println(c.GetField().getName());
		}
		else {
			System.out.println("0 crate left.");
		}
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
