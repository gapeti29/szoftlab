import java.io.*;
import java.util.ArrayList;

/**
 * A tesztesetekhez tartozo bemeneti nyelvet megvalosito osztaly
 * A kulönbözõ beallito és lekerdezo fuggvenyeket tartalmazza
 * Az osztaly felel a szoveges fajlok kezeleseert is
 * 
 */

public class Menu{
	static Game game;
	public static void main(String[] args) throws IOException {
		game=new Game();
		Game.CreateMap();
		loadMap("bemenet_01.txt");
		game.drawMap();
		game.locateThings();
	}
	/**
	 * A kulonbozo bemeneti parancsokat kezelo menu fuggveny. Feldolgozast vegez a bemeneti nyelv szerint
	 * @param split String[] tipusu, ez a bemeneti utasitas
	 */		
	private static void menu(String[] split) throws IOException {
		if(split[0].compareTo("loadMap")==0&&split.length==2){loadMap(split[1]);}
		if(split[0].compareTo("saveMap")==0&&split.length==2){saveMap(split[1]);}
		if(split[0].compareTo("moveWorker")==0&&split.length==2){moveWorker(split[1]);}
		if(split[0].compareTo("canPush")==0&&split.length==1){canPush();}
		if(split[0].compareTo("setNeighbour")==0&&split.length==4){setNeighbour(split[1],split[2],split[3]);}
		if(split[0].compareTo("setWorker")==0&&split.length==3){setWorker(split[1],split[2]);}
		if(split[0].compareTo("setBox")==0&&split.length==3){setBox(split[1],split[2]);}
		if(split[0].compareTo("setOil")==0&&split.length==1){setOil();}
		if(split[0].compareTo("setHoney")==0&&split.length==1){setHoney();}
		if(split[0].compareTo("setField")==0&&split.length==2){setField(split[1]);}
		if(split[0].compareTo("setGoal")==0&&split.length==2){setGoal(split[1]);}
		if(split[0].compareTo("setPillar")==0&&split.length==2){setPillar(split[1]);}
		if(split[0].compareTo("setHole")==0&&split.length==3){setHole(split[1],split[2]);}
		if(split[0].compareTo("setSwitch")==0&&split.length==4){setSwitch(split[1],split[2],split[3]);}
		if(split[0].compareTo("listFieldStates")==0&&split.length==1){listFieldStates();}
		if(split[0].compareTo("listBox")==0&&split.length==1){listBox();}
		if(split[0].compareTo("listRound")==0&&split.length==1){listRound();}
		if(split[0].compareTo("listCohesion")==0&&split.length==1){listCohesion();}
		if(split[0].compareTo("listPoints")==0&&split.length==1){listPoints();}
		if(split[0].compareTo("pass")==0&&split.length==1){pass();}
	}
/**
 * loadMap bemeneti utasitas, a fajlbol torteno bemenet beolvasasat vegzi, majd futtatja az ott talalhato utasitasokat
 * @param file String[] tipusu, a bemeneti fajl eleresi utvonala
 */	
	
	public static void loadMap(String file) throws IOException,NullPointerException {
		System.out.println(System.getProperty("user.dir"));
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
			String line=br.readLine();
			String[] split = line.split(",");
		    while (line!=null) {
				System.out.println(line);
		    	menu(split);
		    	line=br.readLine();
			    split = line.split(",");
		    }
			br.close();
			} catch (FileNotFoundException e) {
				System.out.print("Nem létezik ilyen file!!!!");
			}catch(NullPointerException e) {
				//...
			}
	}
/**
 * A terkep elmentese szoveges fajlba
 * @param file String[] tipusu, ebbe a fajlba menti el a terkepet/palyat
 */		
	public static void saveMap(String file) throws FileNotFoundException {
		
	}
	
/**
 * Egy munkas mozgatasa egy adott iranyba
 * 
 * @param direction String tipusu, ez adja meg az iranyt (fel-le-jobbra-balra)
 * 
 */	
	public static void moveWorker(String direction) {
		if(direction.compareTo("Up")==0)		game.currentPlayer().DirectMove(Direction.Up, game.currentPlayer().GetStrenght());
		if(direction.compareTo("Down")==0)		game.currentPlayer().DirectMove(Direction.Down, game.currentPlayer().GetStrenght());
		if(direction.compareTo("Left")==0)		game.currentPlayer().DirectMove(Direction.Left, game.currentPlayer().GetStrenght());
		if(direction.compareTo("Right")==0)		game.currentPlayer().DirectMove(Direction.Right, game.currentPlayer().GetStrenght());
		Game.NextRound();
	}

/**
 * Megadja, hogy van-e mozdithato lada a jatekban. “yes”-t ir ki, ha van s “no”-t ha nincs
 */	
	public static void canPush() {
		if(game.canPush()==true) {System.out.println("Yes");}
		else
		{System.out.println("No");}
	}
/**
 * Egy mezo szomszedjainak beallitasara szolgalo metodus
 * 
 * @param name String tipusu, megadja, hogy melyik mezonek a szomszedjat allitjuk be
 * @param neighbour_name megadja a szomszed nevet
 * @param direction String tipusu megadja a szomszed iranyat
 */	
	public static void setNeighbour(String name, String neighbour_name,String direction) {
		if(direction.compareTo("Up")==0)	game.findField(name).SetNeighbour(game.findField(neighbour_name), Direction.Up);
		if(direction.compareTo("Down")==0)	game.findField(name).SetNeighbour(game.findField(neighbour_name), Direction.Down);
		if(direction.compareTo("Left")==0)	game.findField(name).SetNeighbour(game.findField(neighbour_name), Direction.Left);
		if(direction.compareTo("Right")==0)	game.findField(name).SetNeighbour(game.findField(neighbour_name), Direction.Right);
	}
/**
 * Egy munkas beallitasa egy mezore 
 * 
 * @param name String tipusu, megadja, hogy melyik munkasra allitjuk be
 * @param field String tipusu, megadja a mezo nevet amelyikre a munkast rakjuk
 * 
 */		
	public static void setWorker(String name,String field) {
		game.addWorker(new Worker(name),field);
		game.findField(field).SetThing(game.findWorker(name));
	}
	
/**
 * Egy doboz beallitasa egy mezore 
 * 
 * @param name String tipusu, megadja, hogy melyik dobozt allitjuk be
 * @param field String tipusu, megadja a mezo nevet amelyikre a dobozt rakjuk
 * 
 */		
	public static void setBox(String name,String field) {
		game.addCrate(new Crate(name),field);
		game.findField(field).SetThing(game.findCrate(name));
	}

/**
 * Olaj kenoanyag beallitasa egy mezore 
 * 
 * @param name String tipusu, megadja, hogy hova tegyuk le a kenoanyagot
 * 
 */		
	public static void setOil() {
		game.currentPlayer().PutMaterial(new Oil());
	}
/**
 * Mez kenoanyag beallitasa egy mezore 
 * 
 * @param name String tipusu, megadja, hogy hova tegyuk le a kenoanyagot
 * 
 */	
	public static void setHoney() {
		game.currentPlayer().PutMaterial(new Honey());
	}
/**
 * Mezo letrehozasa 
 * 
 * @param name String tipusu, megadja, hogy milyen elnevezesu mezot akarunk letrehozni
 * 
 */	
	public static void setField(String name) {
		game.addField(new Field(name),"Field.png");
	}
/**
 * Oszlop letrehozasa 
 * 
 * @param name String tipusu, megadja az oszlop megnevezeset
 * 
 */	
	public static void setPillar(String name) {
		game.addField(new Pillar(name),"Pillar.jpg");
	}
/**
 * Celmezo letrehozasa 
 * 
 * @param name String tipusu, megadja a celmezo megnevezeset
 * 
 */	
	public static void setGoal(String name) {
		game.addField(new Goal(name),"Goal.png");
	}
/**
 * Lyuk beallitasa 
 * @param name String tipusu, megadja a mezo adatait ahol a lyuk elhelyezkedik
 * @param state String tipusu, megadja a lyuk allapotat, lehet nyitott vagy zart
 * 
 */		
	public static void setHole(String name,String state) {
		if(state.compareTo("closed")==0)
			game.addField(new Hole(name,false),"Hole_closed.png");
		else
		if(state.compareTo("open")==0)
			game.addField(new Hole(name,true),"Hole_open.png");
		else
			game.addField(new Hole(name,false),"Hole_closed.png");
	}
/**
 * Kapcsolo beallitasa 
 * @param name String tipusu, megadja a mezo adatait ahol a kapcsolo elhelyezkedik
 * @param state String tipusu, megadja a kapcsolo allapotat
 * @param hole String tipusu, megadja a lyuk elheyezkedeset
 */		
	public static void setSwitch(String name,String state,String hole) {
		if(state.compareTo("off")==0)
			game.addField(new Switch(name,false,hole),"Switch.png");
		else
		if(state.compareTo("on")==0)
			game.addField(new Switch(name,true,hole),"Switch.png");
		else
			game.addField(new Switch(name,false,hole),"Switch.png");
	}
/**
 * Kilistazza a jatekban levo mezok allapotait (tipusait)
 */		
	public static void listFieldStates() {
		game.listFieldState();
	}
/**
 * Kilistazza a jatekban levo dobozokat
 */		
	public static void listBox() {
		game.listBox();
	}
/**
 * Kilistazza a jatekban levo munkasok aktualis pontszamat
 */	
	public static void listPoints() {
		game.listPoints();
	}
/**
 * A jatek inditasa ota eltelt korok szamat adja meg
 */		
	public static void listRound() {
		game.listRound();
	}
/**
 * A tapadast - melyeket a kenoanyagok valtoztathatnak - listazza ki
 */	
	public static void listCohesion() {
		game.listCohesion();
	}
/**
 * Passzolas, ilyenkor a kovetkezo korre ugrunk
 */	
	public static void pass() {
		Game.NextRound();
	}
}