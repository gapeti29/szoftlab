package sokoban;

public class Warehouse {
	private int remainingCrates;
	private int rowSize = 4; //Példa érték...
	private int columnSize = 4; //Példa érték...
	private Field[][] fields = new Field[rowSize][columnSize];
	private Crate[] crates;
	
	/**
	 * Visszaadja, hogy hány sorból áll a pálya.
	 * @return int típussal tér vissza.
	 */
	public int GetRowSize() { return rowSize; }
	
	/**
	 * Visszaadja, hogy hány oszlopból áll a pálya.
	 * @return int típussal tér vissza.
	 */
	public int GetColumnSize() { return columnSize; }
	
	/**
	 * Visszaadja a paraméterek által meghatározott helyen lévõ mezõt.
	 * @param i int típusú, ebben a sorban van a mezõ.
	 * @param j int típusú, ebben az oszlopban van a mezõ.
	 * @return Field típussal tér vissza.
	 */
	public Field GetField(int i, int j) { return fields[i][j]; }
	
	/**
	 * A paraméterül kapott ládát a ládákat tartalmazó tömb végére szúrja.
	 * @param c Crate típusú.
	 */
	public void AddCrate(Crate c) {
		if(crates == null) {
			crates = new Crate[1];
			crates[0] = c;
		}
		else {
			Crate[] temp = new Crate[crates.length + 1];
			for(int i = 0; i < crates.length; ++i) {
				temp[i] = crates[i];
			}
			temp[crates.length] = c;
			crates = temp;
		}
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
				try {
					fields[i][j].SetNeighbour(fields[i-1][j], Direction.Up);
					fields[i-1][j].SetNeighbour(fields[i][j], Direction.Down);
				} catch(NullPointerException e) {
					//Nem csinál semmit.
				} catch(ArrayIndexOutOfBoundsException out) {
					//Nem csinál semmit.
				}
				try {
					fields[i][j].SetNeighbour(fields[i+1][j], Direction.Down);
					fields[i+1][j].SetNeighbour(fields[i][j], Direction.Up);
				} catch(NullPointerException e) {
					//Nem csinál semmit.
				} catch(ArrayIndexOutOfBoundsException out) {
					//Nem csinál semmit.
				}
				try {
					fields[i][j].SetNeighbour(fields[i][j-1], Direction.Left);
					fields[i][j-1].SetNeighbour(fields[i][j], Direction.Right);
				} catch(NullPointerException e) {
					//Nem csinál semmit.
				} catch(ArrayIndexOutOfBoundsException out) {
					//Nem csinál semmit.
				}
				try {
					fields[i][j].SetNeighbour(fields[i][j+1], Direction.Right);
					fields[i][j+1].SetNeighbour(fields[i][j], Direction.Left);
				} catch(NullPointerException e) {
					//Nem csinál semmit.
				} catch(ArrayIndexOutOfBoundsException out) {
					//Nem csinál semmit.
				}
			}
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
