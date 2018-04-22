import java.util.ArrayList;

public final class Game {
	private static int round = 0;
	private static Warehouse map;
	private static ArrayList<Worker> workers=new ArrayList<Worker>();
	private static int playersNumber = 0; //Az aktuálisan soron lévõ játékos sorszáma.

	
	public Worker findWorker(String name) {
		for(Worker w:workers) {
			if(w.GetName().compareTo(name)==0) {return w;}
		}
		return null;
	}
	public Field findField(String name) {
		return map.findField(name);
	}
	public boolean canPush() {
		return map.HasMoves(workers);
	}
	public void addField(Field name) {
		map.AddField(name);
	}
	public void addWorker(Worker w) {
		workers.add(w);
	}
	public void addCrate(Crate c) {
		map.AddCrate(c);
	}
	public Worker currentPlayer() {
		return workers.get(playersNumber);
	}
	
	
	/**
	 * A játékot eggyel tovább lépteti.
	 * Eggyel növeli a round változót, ha minden játékos lépet a körben.
	 */
	public static void NextRound() {
		++playersNumber;
		if(playersNumber == workers.size()) {
			playersNumber = 0;
			++round;
		}
	}
	/**
	 * Létrehoz egy új raktárépületet és generál hozzá egy pályát a fájl alapján.
	 * @param file String típusú, a pályát tartalmazó fájl neve.
	 */
	public static void CreateMap() {
		map = new Warehouse();
	}
	
	
	/**
	 * Visszaadja az aktuális kör sorszámát.
	 * @return	int típussal tér vissza.
	 */
	public static int GetRound() { return round; }
	
	/**
	 * Az aktuális körön lévõ játékosnak ad egy pontot.
	 */
	public static void AddPointToWorker() {
		workers.get(playersNumber).AddPoint();
	}
	
	private void DrawMap() {
		//Felj. alatt...
	}
	public void listBox() {
		map.listBox();
	}
	public void listPoints() {
		for(Worker w: workers) {
			System.out.println("Player: "+w.GetName()+"	Field: "+w.GetField().getName()+" Points: "+w.GetPoints());
		}
	}
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
	public void listCohesion() {
		map.listCohesion();
	}
	public void listFieldState() {
		map.listFieldState();
	}

 }
