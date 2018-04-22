import java.util.ArrayList;

public final class Game {
	private static int round = 0;
	private static Warehouse map;
	private static ArrayList<Worker> workers=new ArrayList<Worker>();
	private static int playersNumber = 0; //Az aktu�lisan soron l�v� j�t�kos sorsz�ma.

	
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
	 * A j�t�kot eggyel tov�bb l�pteti.
	 * Eggyel n�veli a round v�ltoz�t, ha minden j�t�kos l�pet a k�rben.
	 */
	public static void NextRound() {
		++playersNumber;
		if(playersNumber == workers.size()) {
			playersNumber = 0;
			++round;
		}
	}
	/**
	 * L�trehoz egy �j rakt�r�p�letet �s gener�l hozz� egy p�ly�t a f�jl alapj�n.
	 * @param file String t�pus�, a p�ly�t tartalmaz� f�jl neve.
	 */
	public static void CreateMap() {
		map = new Warehouse();
	}
	
	
	/**
	 * Visszaadja az aktu�lis k�r sorsz�m�t.
	 * @return	int t�pussal t�r vissza.
	 */
	public static int GetRound() { return round; }
	
	/**
	 * Az aktu�lis k�r�n l�v� j�t�kosnak ad egy pontot.
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
