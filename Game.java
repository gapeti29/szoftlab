package sokoban;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public final class Game {
	private static int round = 0;
	private static Warehouse map;
	private static Worker[] workers;
	private static int playersNumber = 0; //Az aktuálisan soron lévõ játékos sorszáma.
	
	/**
	 * Új játék futtatása.
	 */
	public static void NewGame(int pieces) {
		//CreateMap(filename);
		
		//Munkások létrehozása
		workers = new Worker[pieces];
		Scanner scanner = new Scanner(System.in);
		for(int i = 0; i < pieces; ++i) {
			System.out.println("Mi legyen a(z) " + i + ". játékos neve?");
			String s;
			s = scanner.nextLine();
			CreateWorker(s, i);
		}
		scanner.close();
		
		//Amíg lehet lépni és nem fogytak el a ládák.
		while(map.HasMoves(workers) && map.GetRemainingCrates() != 0) {
			//Sorban lépnek a játékosok, ha lehet még lépni.
			workers[playersNumber].Step();
			NextRound();
		}
		
		EndGame();
	}
	
	private static void CreateWorker(String s, int i) {
		workers[i] = new Worker(s);
	}
	
	/**
	 * A játékot eggyel tovább lépteti.
	 * Eggyel növeli a round változót, ha minden játékos lépet a körben.
	 */
	public static void NextRound() {
		++playersNumber;
		if(playersNumber == workers.length) {
			playersNumber = 0;
			++round;
		}
	}
	
	/**
	 * Létrehoz egy új raktárépületet és generál hozzá egy pályát a fájl alapján.
	 * @param file String típusú, a pályát tartalmazó fájl neve.
	 */
	public static void CreateMap(String file) {
		map = new Warehouse();
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			//Felj. alatt..
			
			
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		map.SetFieldsNeighbours();
	}
	
	public static void EndGame() {
		int max = 0; //Legtöbb pontot gyûjtött játékos sorszáma lesz a változóban.
		for(int i = 1; i < workers.length; ++i) {
			if(workers[max].GetPoints() < workers[i].GetPoints()) {
				max = i;
			}
		}
		
		System.out.println(workers[max].GetName() + " nyert!");
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
	public static Worker GetCurrentWorker() { return workers[playersNumber]; }
	
	/**
	 * Az aktuális körön lévõ játékosnak ad egy pontot.
	 */
	public static void AddPointToWorker() {
		workers[playersNumber].AddPoint();
	}
	
	private void DrawMap() {
		//Felj. alatt...
	}
 }
