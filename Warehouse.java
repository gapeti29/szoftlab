package sokoban;

public class Warehouse {
	private int remainingCrates;
	private int rowSize = 4; //Példa érték...
	private int columnSize = 4; //Példa érték...
	private Field[][] fields = new Field[rowSize][columnSize];
	private Crate[] crates;
	
	
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
		for(int i = 0; i < remainingCrates; ++i) {
			if(crates[i] == c) {
				Crate[] temp = new Crate[remainingCrates-1];
				for(int j = 0; j < remainingCrates-1; ++j) {
					if(i != j) {
						temp[j] = crates[j];
					}
				}
				crates = temp;
			}
		}
		
		--remainingCrates;
	}
	
	/**
	 * A paraméterül kapott mezõt beszúrja a pályába, a paraméterül megadott helyre.
	 * @param f Field típusú, ez a mezõ kerül beszúrásra.
	 * @param i int típusú, a sor száma.
	 * @param j int típusú, az oszlop száma.
	 */
	public void AddField(Field f, int i, int j) {
		fields[i][j] = f;
	}
	
	/**
	 * Létrehozza a szomszédsági viszonyt a mezõk között.
	 */
	public void SetFieldsNeighbours() {
		for(int i = 0; i < rowSize; ++i) {
			for(int j = 0; j < columnSize; ++j) {
				//A mezõ szomszédainak beállítása
				SetUpperNeighbour(fields[i][j], i, j);
				SetLowerNeighbour(fields[i][j], i, j);
				SetLeftNeighbour(fields[i][j], i, j);
				SetRightNeighbour(fields[i][j], i, j);
			}
		}
	}
	
	/**
	 * A megadott helyû mezõ eltárolja felsõ szomszédként a felette lévõ mezõt, míg az eltárolja õt alsó szomszédként.
	 * @param f Field típusú, ehhez a mezõhöz viszonyítunk. Õ lesz az alsó szomszéd.
	 * @param i int típusú, ebben a sorban van az f.
	 * @param j int típusú, ebben az oszlopban van az f.
	 */
	private void SetUpperNeighbour(Field f, int i, int j) {
		try {
			f.SetNeighbour(fields[i-1][j], Direction.Up);
			fields[i-1][j].SetNeighbour(f, Direction.Down);
		} catch(NullPointerException e) {
			//Nem csinál semmit.
		} catch(ArrayIndexOutOfBoundsException out) {
			//Nem csinál semmit.
		}
	}
	
	/**
	 * A megadott helyû mezõ eltárolja alsó szomszédként az alatta lévõ mezõt, míg az eltárolja õt felsõ szomszédként.
	 * @param f Field típusú, ehhez a mezõhöz viszonyítunk. Õ lesz a felsõ szomszéd.
	 * @param i int típusú, ebben a sorban van az f.
	 * @param j int típusú, ebben az oszlopban van az f.
	 */
	private void SetLowerNeighbour(Field f, int i, int j) {
		try {
			f.SetNeighbour(fields[i+1][j], Direction.Down);
			fields[i+1][j].SetNeighbour(f, Direction.Up);
		} catch(NullPointerException e) {
			//Nem csinál semmit.
		} catch(ArrayIndexOutOfBoundsException out) {
			//Nem csinál semmit.
		}
	}
	
	/**
	 * A megadott helyû mezõ eltárolja baloldali szomszédként a tõle balra lévõ mezõt, míg az eltárolja õt jobboldali szomszédként.
	 * @param f Field típusú, ehhez a mezõhöz viszonyítunk. Õ lesz a jobboldali szomszéd.
	 * @param i int típusú, ebben a sorban van az f.
	 * @param j int típusú, ebben az oszlopban van az f.
	 */
	private void SetLeftNeighbour(Field f, int i, int j) {
		try {
			f.SetNeighbour(fields[i][j-1], Direction.Left);
			fields[i][j-1].SetNeighbour(f, Direction.Right);
		} catch(NullPointerException e) {
			//Nem csinál semmit.
		} catch(ArrayIndexOutOfBoundsException out) {
			//Nem csinál semmit.
		}
	}
	
	/**
	 * A megadott helyû mezõ eltárolja jobboldali szomszédként a tõle jobbra lévõ mezõt, míg az eltárolja õt baloldali szomszédként.
	 * @param f Field típusú, ehhez a mezõhöz viszonyítunk. Õ lesz a baololdali szomszéd.
	 * @param i int típusú, ebben a sorban van az f.
	 * @param j int típusú, ebben az oszlopban van az f.
	 */
	private void SetRightNeighbour(Field f, int i, int j) {
		try {
			f.SetNeighbour(fields[i][j+1], Direction.Right);
			fields[i][j+1].SetNeighbour(f, Direction.Left);
		} catch(NullPointerException e) {
			//Nem csinál semmit.
		} catch(ArrayIndexOutOfBoundsException out) {
			//Nem csinál semmit.
		}
	}
	
	/**
	 * Ellenõrzi, hogy van-e még érvényes lépés a játékban.
	 * @param workers Worker[] típusú, mely a játékban lévõ munkások referenciáját adja meg.
	 * @return boolean típussal tér vissza, mely akkor true, ha van olyan munkás és olyan láda, mely tud lépni.
	 */
	public boolean HasMoves(Worker[] workers) {
		//Munkások
		boolean workersCanMove = false;
		for(int i = 0; i < workers.length; ++i) {
			if(workers[i].HasMoves())
				workersCanMove = true;
		}
		
		//Ládák
		boolean cratesCanMove = false;
		for(int i = 0; i < crates.length; ++i) {
			if(crates[i].HasMoves())
				cratesCanMove = true;
		}
	
		//Csak akkor igaz, ha a munkások és a ládák közül is tud valamelyik lépni. 
		if(workersCanMove && cratesCanMove)
			return true;
		else
			return false;
	}	
}
