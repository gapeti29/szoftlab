package sokoban;

import java.util.Random;

public class Warehouse {
	private int remainingCrates;
	private Field[][] fields;
	private Crate[] crates;
	
	private final int size = 7;	//Random méret a pályának...
	private final int pieces = 3; //Random szám, majd megbeszéljük, mibõl mennyi legyen...
	
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
	 * A pálya alapjának létrehozása.
	 */
	public void InitFields() {		
		//Egy új, négyzet alakú pálya létrehozása.
		fields = new Field[size][size];
		
		/*A mezõk létrehozása és összekötése.
		 *A mezõk eltárolják egymást szomszédként.
		 */
		for(int i = 0; i < size; ++i) {
			for(int j = 0; j < size; ++j) {
				fields[i][j] = new Field();
				fields[i][j].SetWarehouse(this);
				ChangeField(fields[i][j], i, j);
			}
		}
	}
	
	/**
	 * A paraméterül kapott mezõ beillesztése a pályába. Eltárolja mind a 4 irányba a szomszédait, valamint õk is eltárolják a kapott mezõt.
	 * @param f	Az új mezõ, mely bekerül a pályába.
	 * @param i Ebben a sorban fog elhelyezkedni.
	 * @param j	Ebben az oszlopban fog elhelyezkedni.
	 */
	private void ChangeField(Field f, int i, int j) {
		ChangeFieldUp(f, i, j);
		ChangeFieldDown(f, i, j);
		ChangeFieldLeft(f, i, j);
		ChangeFieldRight(f, i, j);
	}
	
	/**
	 * A megadott helyû mezõ eltárolja felsõ szomszédként a felette lévõ mezõt, míg az eltárolja õt alsó szomszédként.
	 * @param f Ehhez a mezõhöz viszonyítunk. Õ lesz az alsó szomszéd.
	 * @param i Ebben a sorban van az f.
	 * @param j Ebben az oszlopban van az f.
	 */
	private void ChangeFieldUp(Field f, int i, int j) {
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
	 * @param f Ehhez a mezõhöz viszonyítunk. Õ lesz a felsõ szomszéd.
	 * @param i Ebben a sorban van az f.
	 * @param j Ebben az oszlopban van az f.
	 */
	private void ChangeFieldDown(Field f, int i, int j) {
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
	 * @param f Ehhez a mezõhöz viszonyítunk. Õ lesz a jobboldali szomszéd.
	 * @param i Ebben a sorban van az f.
	 * @param j Ebben az oszlopban van az f.
	 */
	private void ChangeFieldLeft(Field f, int i, int j) {
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
	 * @param f Ehhez a mezõhöz viszonyítunk. Õ lesz a baololdali szomszéd.
	 * @param i Ebben a sorban van az f.
	 * @param j Ebben az oszlopban van az f.
	 */
	private void ChangeFieldRight(Field f, int i, int j) {
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
	 * A fal létrehozása, körben a pálya szélén.
	 */
	public void InitWall() {
		Wall w = new Wall();
		
		for(int i = 0; i < size; ++i) {
			//Balszél
			Pillar p1 = new Pillar();
			ChangeField(p1, i, 0);
			fields[i][0] = p1;
			p1.SetWarehouse(this);
			w.AddComponent(p1);
			
			//Jobbszél
			Pillar p2 = new Pillar();
			ChangeField(p2, i, size-1);
			fields[i][size-1] = p2;
			p2.SetWarehouse(this);
			w.AddComponent(p2);
			
			//Teteje
			Pillar p3 = new Pillar();
			ChangeField(p3, 0, i);
			fields[0][i] = p3;
			p3.SetWarehouse(this);
			w.AddComponent(p3);
			
			//Alja
			Pillar p4 = new Pillar();
			ChangeField(p4, size-1, i);
			fields[size-1][i] = p4;
			p4.SetWarehouse(this);
			w.AddComponent(p4);
		}
	}
	
	/**
	 * Oszlopok létrehozása és hozzáadása a pályához.
	 */
	public void InitPillar() {
		for(int i = 0; i < pieces; ++i) {
			AddField(new Pillar());
		}
	}
	
	/**
	 * Kapcsolók és hozzájuk lyukak létrehozása.
	 */
	public void InitSwitch() {
		for(int i = 0; i < pieces; ++i) {
			Hole h = new Hole();
			Switch s = new Switch(h);
			AddField(h);
			AddField(s);
		}
	}
	
	/**
	 * A lyukak létrehozása és hozzáadása a pályához.
	 */
	public void InitHole() {
		for(int i = 0; i < pieces; ++i) {
			AddField(new Hole());
		}
	}
	
	/**
	 * A célmezõk létrehozása és hozzáadása a pályához.
	 */
	public void InitGoal() {
		for(int i = 0; i < pieces; ++i) {
			AddField(new Goal());
		}
	}
	
	/**
	 * A ládák létrehozása és hozzáadása a pályához.
	 */
	public void InitCrate() {
		crates = new Crate[pieces];
		
		for(int i = 0; i < pieces; ++i) {
			Crate c = new Crate();
			AddMovableThing(c);
			crates[i] = c;
		}
		remainingCrates = pieces;
	}
	
	/**
	 * A munkások létrehozása és hozzáadása a pályához.
	 */
	public void InitWorker(int members) {
		for(int i = 0; i < members; ++i) {
			Worker w = new Worker();
			Game.AddWorker(w, i);
			AddMovableThing(w);
		}
	}
	
	/**
	 * A paraméterül kapott dolgot elhelyezi egy random mezõre.
	 * @param t MovableThing típusú, ezt helyezi el a pályán valahol.
	 */
	void AddMovableThing(MovableThing t) {
		Random r = new Random();
		boolean done = false;
		
		while(!done) {
			//Két szám generálása 1 és (size-1) között
			int i = r.nextInt((size-1) - 1) + 1;
			int j = r.nextInt((size-1) - 1) + 1;
		
			//Ha nem egy extra mezõ a sorsolt, akkor a paraméterül kapott mezõ lesz a helyén.
			if( !(fields[i][j].IsExtra()) ) {
				//Az irány jelen esetben teljesen mindegy, mert nincs semmi a mezõn, úgyhogy el lesz fogadva.
				fields[i][j].Accept(t, Direction.Right);
				fields[i][j].MakeExtra();
				done = true;
			}
		}
	}	
	
	/**
	 * A paraméterül kapott mezõt egy random helyre beilleszti a pályába.
	 * @param f Field típusú, ez a mezõ kerül a pályába.
	 */
	void AddField(Field f) {
		Random r = new Random();
		boolean done = false;
		
		while(!done) {
			//Két szám generálása 1 és (size-1) között.
			int i = r.nextInt((size-1) - 1) + 1;
			int j = r.nextInt((size-1) - 1) + 1;
		
			//Ha nem egy extra mezõ a sorsolt, akkor a paraméterül kapott mezõ lesz a helyén.
			if( !(fields[i][j].IsExtra()) ) {
				//A szomszédsági viszony létrehozása.
				ChangeField(f, i, j);
				fields[i][j] = f;
				fields[i][j].SetWarehouse(this);
				//Ezentúl ez egy extra mezõ lesz.
				fields[i][j].MakeExtra();
				done = true;
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
