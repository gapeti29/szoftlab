package sokoban;

import java.util.Scanner;

public final class Game {
	private static int round = 0;
	private static Warehouse map;
	private static Worker[] workers;
	
	/**
	 * Új játék futtatása.
	 */
	public static void NewGame() {
		CreateMap();
		
		//Amíg lehet lépni és nem fogytak el a ládák.
		while(map.HasMoves(workers) && map.GetRemainingCrates() != 0) {
			//Sorban lépnek a játékosok, ha lehet még lépni.
			workers[round].Step();
			NextRound();
		}
		
		EndGame();
	}
	
	/**
	 * A játék következõ körbe lépteti.
	 * Eggyel növeli a round változót (vagy nullázza, ha körbeértek a játékosok).
	 */
	public static void NextRound() {
		++round;
		if(round == workers.length)
			round = 0;
	}
	
	/**
	 * Létrehoz egy új raktárépületet és generál hozzá egy pályát.
	 */
	public static void CreateMap() {
		map = new Warehouse();
		map.InitFields();
		map.InitWall();
		map.InitPillar();
		map.InitSwitch();
		map.InitHole();
		map.InitGoal();
		map.InitCrate();
		
		//Egyelõre ez csak ilyen...
		System.out.println("Hány munkás legyen?");
		Scanner Cin = new Scanner(System.in);
		int members;
		members = Cin.nextInt();
		Cin.close();
		workers = new Worker[members];
		
		map.InitWorker(members);
	}
	
	public static void EndGame() {
		//Felj. alatt...
	}
	
	/**
	 * Visszaadja az aktuális kör sorszámát.
	 * @return	int típussal tér vissza.
	 */
	public static int GetRound() { return round; }
	
	/**
	 * Visszaadja az aktuális körben mozgó munkást.
	 * @return Worker típussal tér vissza.
	 */
	public static Worker GetCurrentWorker() { return workers[round]; }
	
	/**
	 * A paraméterül kapott munkást eltárolja a workers tömbben.
	 * @param w Worker típusú, ez az objektum kerül a tömbbe.
	 * @param i int típusú, a munkás sorszámát adja meg.
	 */
	public static void AddWorker(Worker w, int i) {
		workers[i] = w;
	}
 }
