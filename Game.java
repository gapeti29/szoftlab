package sokoban;

import java.util.ArrayList;

/**
 * Nyilvantartja az aktualis jatek koreinek szamat.
 * Sorban lepesre szolitja fel a jatekosokat, majd a megfelelo iranyba mozgatja a munkasaikat.
 * uj jatekot tud kezdeni, ennek soran letrehozza a raktar terkepet, es az eppen futo jatekot be is tudja fejezni.
 * A jatektol lekerdezheto az eppen soron levo jatekos.
 */
public final class Game {
	/**
	 * A korok szama.
	 */
	private static int round = 0;
	/**
	 * A palya, ahol a jatek folyik.
	 */
	private static Warehouse map;
	/**
	 * Munkasok referenciaja.
	 */
	private static ArrayList<Worker> workers=new ArrayList<Worker>();
	/**
	 * Az aktualisan soron levo jatekos sorszama.
	 */
	private static int playersNumber = -1; 

	/**
	 * Megkeresi a munkast a parameterul kapott neve alapjan, es azt vissza is adja.
	 * @param name String tipusu, ezt a munkast keressuk.
	 * @return Worker tipussal ter vissza.
	 */	
	public Worker findWorker(String name) {
		for(Worker w:workers) {
			if(w.GetName().compareTo(name)==0) {return w;}
		}
		return null;
	}

	/**
	 * Megkeresi a mezot a parameterul kapott neve alapjan, es azt vissza is adja.
	 * @param name String tipusu, ezt a mezot keressuk.
	 * @return Field tipussal ter vissza.
	 */
	public Field findField(String name) {
		return map.findField(name);
	}

	/**
	 * Ellenorzi, hogy tud-e meg lepni valamelyik munkas.
	 * @return boolean tipussal ter vissza, mely akkor true, ha van meg ervenyes lepes.
	 */
	public boolean canPush() {
		return map.HasMoves(workers);
	}

	/**
	 * A parameterul kapott mezot hozzaadja a palyahoz.
	 * @param f Field tipusu.
	 */
	public void addField(Field f) {
		map.AddField(f);
	}

	/**
	 * A parameterul kapott munkast eltarolja.
	 * @param w Worker tipusu.
	 */
	public void addWorker(Worker w) {
		workers.add(w);
		playersNumber++;
	}

	/**
	 * A parameterul kapott ladat hozzaadja a palyahoz.
	 * @param c Crate tipusu.
	 */
	public void addCrate(Crate c) {
		map.AddCrate(c);
	}

	/**
	 * Visszaadja az aktualisan soron levo munkast.
	 * @return Worker tipussal ter vissza.
	 */
	public Worker currentPlayer() {
		if(playersNumber>=0) {
			return workers.get(playersNumber);
		}
		return null;
	}

	/**
	 * Megkeres egy ladat a parameterul kapott nev alapjan. 
	 * @param name String tipusu.
	 * @return Crate tipussal ter vissza.
	 */
	public Crate findCrate(String name) {
		return map.findCrate(name);
	}
	
	
	/**
	 * A jatekot eggyel tovabb lepteti.
	 * Eggyel noveli a round valtozot, ha minden jatekos lepet a korben.
	 */
	public static void NextRound() {
		playersNumber++;
		if(playersNumber == workers.size()) {
			playersNumber = 0;
			round++;
		}
	}

	/**
	 * Letrehoz egy uj raktarepuletet es general hozza egy palyat a fajl alapjan.
	 * @param file String tipusu, a palyat tartalmazo fajl neve.
	 */
	public static void CreateMap() {
		map = new Warehouse();
	}
	
	/**
	 * Visszaadja az aktualis kor sorszamat.
	 * @return	int tipussal ter vissza.
	 */
	public static int GetRound() { return round; }
	
	/**
	 * Az aktualis koron levo jatekosnak ad egy pontot.
	 */
	public static void AddPointToWorker() {
		workers.get(playersNumber).AddPoint();
	}

	/**
	 * Torli a parameterul kapott munkast.
	 * @param w Worker tipusu.
	 */
	public static void removeWorker(Worker w) {
		workers.remove(w);
		playersNumber--;
	}

	/**
	 * Kilistazza azokat a mezoket, melyeken dobozok allnak.
	 */
	public void listBox() {
		map.listBox();
	}

	/**
	 * Kilistazza a jatekosok pontjait.
	 */
	public void listPoints() {
		for(Worker w: workers) {
			System.out.println("Player: "+w.GetName()+"	Field: "+w.GetField().getName()+" Points: "+w.GetPoints());
		}
	}

	/**
	 * Kilistazza az aktualis kor sorszamat, vagy ha mar nem tudnak lepni a jatekosok, akkor a legtobb pontot elert jatekos nevet plusz pontszamat.
	 */
	public void listRound() {
		boolean zero_moves=true;
		for(Worker w:workers) {
			if(w.HasMoves()==true) {
				zero_moves=false;
			}
		}
		if(zero_moves==true) {
			Worker highest_point=workers.get(0);
			for(Worker w:workers) {
				if(w.GetPoints()>highest_point.GetPoints())highest_point=w;
			}
			System.out.println(highest_point.GetName()+": "+highest_point.GetPoints());
		}
		else{
			System.out.println("Current round: "+round);
		}
	}

	/**
	 * Kilistazza a palya mezoinek a surlodasi erejet.
	 */
	public void listCohesion() {
		map.listCohesion();
	}
	public void listFieldState() {
		map.listFieldState();
	}

 }
