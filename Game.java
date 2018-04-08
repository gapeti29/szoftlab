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
		
		//CreateMap(filename);
		
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
			String line;
			try {
				int i = 0; //beolvasott sorok
				while(i < map.GetRowSize()) {
					line = br.readLine();
					//Szóköz alapján darabol
					String[] t = line.split(" ");
					for(int j = 0; j < map.GetColumnSize(); ++j) {
						switch(t[j]) {
						//Sima mezõ
						case "F":
							map.AddField(new Field(), i, j);
							break;
						//Célmezõ
						case "G":
							map.AddField(new Goal(), i, j);
							break;
						//Nyitott lyuk
						case "H":
							Hole h = new Hole();
							h.Open();
							map.AddField(h, i, j);
							break;
						//Bezárt lyuk
						case "C":
							Hole c = new Hole();
							c.Close();
							map.AddField(c, i, j);
							break;
						//Kapcsoló
						case "S":
							map.AddField(new Switch(), i, j);
							break;
						//Oszlop
						case "P":
							map.AddField(new Pillar(), i, j);
							break;
						}
					}
					++i;
				}
				line = br.readLine();
				//Lyukak-kapcsolók összerendelése
				if(line.equals("holes")) {
					line = br.readLine();
					while(!line.equals("crates") || !line.equals("workers")) {
						//2 koordináta szétválasztása
						String[] t = line.split(" ");
						String[] scoord = t[0].split(","); //kapcsoló koordinátái
						String[] hcoord = t[1].split(","); //lyuk koordinátái
						//Az új kapcsoló és hozzárendelt lyuk hozzáadása a pályához
						Switch s = new Switch();
						Hole h = new Hole();
						s.SetHole(h);
						map.AddField(s, Integer.parseInt(scoord[0]), Integer.parseInt(scoord[1]));
						map.AddField(h, Integer.parseInt(hcoord[0]), Integer.parseInt(hcoord[1]));
						if(t[2].equals("O")) {
							h.Open();
						}
						if(t[2].equals("C")) {
							h.Close();
						}
						
						line = br.readLine();
					}
				}
				//Ládák pályára helyezése
				if(line.equals("crates")) {
					line = br.readLine();
					while(!line.equals("workers")) {
						String[] coord = line.split(",");
						Crate c = new Crate();
						map.GetField(Integer.parseInt(coord[0]), Integer.parseInt(coord[1])).SetThing(c);
						map.AddCrate(c);
						
						line = br.readLine();
					}
				}
				//Munkások pályára helyezése
				if(line.equals("workers")) {
					line = br.readLine();
					for(int n = 0; n < workers.length; ++n) {
						String[] coord = line.split(",");
						map.GetField(Integer.parseInt(coord[0]), Integer.parseInt(coord[1])).SetThing(workers[n]);
						
						line = br.readLine();
					}
				}
			} catch (IOException e1) {
				//Nem sikerült sort beolvasni.
				e1.printStackTrace();
			}
			
			try {
				br.close();
			} catch (IOException e) {
				//Nem sikerült bezárni.
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			//Rossz a fájl neve.
			e.printStackTrace();
		}
		
		//Szomszédok beállítása a pályában.
		map.SetFieldsNeighbours();
	}
	
	/**
	 * Játék befejezése, nyertes nevének kiírása.
	 */
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
	 * Az aktuális körön lévõ játékosnak ad egy pontot.
	 */
	public static void AddPointToWorker() {
		workers[playersNumber].AddPoint();
	}
	
	private void DrawMap() {
		//Felj. alatt...
	}
 }
