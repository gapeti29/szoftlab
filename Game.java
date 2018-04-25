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
	private static ArrayList<Worker> dead_worker=new ArrayList<Worker>();
	/**
	 * Az aktualisan soron levo jatekos sorszama.
	 */
	private static int playersNumber = 0; 
	private static Map visualMap;

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
	public void addField(Field f,String image_name) {
		map.AddField(f);
		f.SetWarehouse(map);
		visualMap.addField(f,image_name);
	}

	/**
	 * A parameterul kapott munkast eltarolja.
	 * @param w Worker tipusu.
	 */
	public void addWorker(Worker w,String field) {
		workers.add(w);
		findWorker(w.GetName()).SetField(this.findField(field));
		visualMap.addWorker(w);
	}

	/**
	 * A parameterul kapott ladat hozzaadja a palyahoz.
	 * @param c Crate tipusu.
	 */
	public void addCrate(Crate c,String field) {
		map.AddCrate(c);
		findCrate(c.getName()).SetField(findField(field));
		visualMap.addCrate(c);
	}

	/**
	 * Visszaadja az aktualisan soron levo munkast.
	 * @return Worker tipussal ter vissza.
	 */
	public Worker currentPlayer() {
		if(workers.size()>0) {
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
		visualMap=new Map();
		visualMap.setWarehouse(map);
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
	public static void removeWorker(MovableThing m ){
		visualMap.removeWorker(m);
		System.out.println("Hello");
		for(Worker w1:workers) {
			dead_worker.add(w1);
		}
		workers.remove(m);
		map.removeCrate(m);
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
			try {
				System.out.println("Player: "+w.GetName()+"	Field: "+w.GetField().getName()+" Points: "+w.GetPoints());
			}catch(NullPointerException e) {
				System.out.println("Player: "+w.GetName()+"	Field: none Points: "+w.GetPoints());
			}
		}
	}

	/**
	 * Kilistazza az aktualis kor sorszamat, vagy ha mar nem tudnak lepni a jatekosok, akkor a legtobb pontot elert jatekos nevet plusz pontszamat.
	 */
	public void listRound() {
		boolean zero_moves=true;
		for(Worker w:workers) {
			if(w.GetField() != null) {
				if(w.HasMoves()==true) {
					zero_moves=false;
				}
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
	
	/**
	 * Kilistazza a Field-ek allapotat.
	 */
	public void listFieldState() {
		map.listFieldState();
	}
	public void drawMap() {
		visualMap.buildMap();
	}
	public void locateThings() {
		visualMap.locateWorkers();
		visualMap.locateCrates();
	}
	public void redrawMap() {
		visualMap.redrawMap();
	}
 }