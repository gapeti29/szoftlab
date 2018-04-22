import java.io.*;
import java.util.ArrayList;

public class Menu {
	static Game game;
	static ArrayList<String> commands;
	public static void main(String[] args) throws IOException {
		game=new Game();
		commands = new ArrayList<String>();
		Game.CreateMap();
		BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
		while(true) {
			String input=reader.readLine();
			String[] line=input.split(",");
			if(line[0].compareTo("loadMap")==0||line[0].compareTo("saveMap")==0) {}
			else {
				commands.add(input);
			}
			menu(line);
		}
	}
	private static void menu(String[] split) throws IOException {
		if(split[0].compareTo("loadMap")==0&&split.length==2){loadMap(split[1]);}
		if(split[0].compareTo("saveMap")==0&&split.length==2){saveMap(split[1]);}
		if(split[0].compareTo("moveWorker")==0&&split.length==3){moveWorker(split[2]);}
		if(split[0].compareTo("canPush")==0&&split.length==1){canPush();}
		if(split[0].compareTo("setNeighbour")==0&&split.length==4){setNeighbour(split[1],split[2],split[3]);}
		if(split[0].compareTo("setWorker")==0&&split.length==4){setWorker(split[1],split[2],split[3]);}
		if(split[0].compareTo("setCrate")==0&&split.length==3){setCrate(split[1],split[2]);}
		if(split[0].compareTo("setOil")==0&&split.length==1){setOil();}
		if(split[0].compareTo("setHoney")==0&&split.length==1){setHoney();}
		if(split[0].compareTo("setField")==0&&split.length==2){setField(split[1]);}
		if(split[0].compareTo("setPillar")==0&&split.length==2){setPillar(split[1]);}
		if(split[0].compareTo("setHole")==0&&split.length==3){setHole(split[1],split[2]);}
		if(split[0].compareTo("setSwitch")==0&&split.length==3){setSwitch(split[1],split[2],split[3]);}
		if(split[0].compareTo("listFieldStates")==0&&split.length==1){listFieldStates();}
		if(split[0].compareTo("listBox")==0&&split.length==1){listBox();}
		if(split[0].compareTo("listRound")==0&&split.length==1){listRound();}
		if(split[0].compareTo("listCohesion")==0&&split.length==1){listCohesion();}
		if(split[0].compareTo("listPoints")==0&&split.length==1){listPoints();}
		if(split[0].compareTo("pass")==0&&split.length==1){pass();}
	}
	public static void loadMap(String file) throws IOException,NullPointerException {
		System.out.println(System.getProperty("user.dir"));
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
			String line=br.readLine();
			String[] split = line.split(",");
		    while (line != null) {
				System.out.println(line);
		    	menu(split);
				commands.add(line);
		    	line=br.readLine();
			    split = line.split(",");
		    }
			br.close();
			} catch (FileNotFoundException e) {
			System.out.print("Nem létezik ilyen file!!!!");
			}catch(NullPointerException e) {}
	}
	public static void drawMap() {}
	public static void saveMap(String file) throws FileNotFoundException {
		PrintWriter pw = new PrintWriter(new FileOutputStream(file+".txt"));
		int file_size=commands.size();
		for(int i=0;i<file_size;i++)
		{
			pw.println(commands.remove(0));
		}
	    pw.close();
	}
	public static void moveWorker(String direction) {
		if(direction.compareTo("up")==0)		game.currentPlayer().DirectMove(Direction.Up, game.currentPlayer().GetStrenght());
		if(direction.compareTo("down")==0)		game.currentPlayer().DirectMove(Direction.Down, game.currentPlayer().GetStrenght());
		if(direction.compareTo("left")==0)		game.currentPlayer().DirectMove(Direction.Left, game.currentPlayer().GetStrenght());
		if(direction.compareTo("right")==0)		game.currentPlayer().DirectMove(Direction.Right, game.currentPlayer().GetStrenght());
		Game.NextRound();
	}
	public static void canPush() {
		if(game.canPush()==true) {System.out.println("Yes");}
		else
		{System.out.println("No");}
	}
	public static void setNeighbour(String name, String neighbour_name,String direction) {
		if(direction.compareTo("up")==0)	game.findField(name).SetNeighbour(game.findField(neighbour_name), Direction.Up);
		if(direction.compareTo("down")==0)	game.findField(name).SetNeighbour(game.findField(neighbour_name), Direction.Down);
		if(direction.compareTo("left")==0)	game.findField(name).SetNeighbour(game.findField(neighbour_name), Direction.Left);
		if(direction.compareTo("right")==0)	game.findField(name).SetNeighbour(game.findField(neighbour_name), Direction.Right);
	}
	public static void setWorker(String name,String field,String material) {
		int converted_material=Integer.parseInt(material);
		Worker w=new Worker(name,converted_material);
		w.SetField(game.findField(field));
		game.addWorker(w);
		game.findField(field).SetThing(w);
	}
	public static void setCrate(String name,String field) {
		Crate c=new Crate();
		c.SetField(game.findField(name));
		game.addCrate(c);
		game.findField(field).SetThing(c);
	}
	public static void setOil() {
		game.currentPlayer().GetField().SetMaterial(new Oil());
	}
	public static void setHoney() {
		game.currentPlayer().GetField().SetMaterial(new Honey());
	}
	public static void setField(String name) {
		game.addField(new Field(name));
	}
	public static void setPillar(String name) {
		game.addField(new Pillar(name));
	}
	public static void setGoal(String name) {
		game.addField(new Goal(name));
	}
	public static void setHole(String name,String state) {
		if(state.compareTo("closed")==0)
			game.addField(new Hole(name,false));
		else
		if(state.compareTo("open")==0)
			game.addField(new Hole(name,true));
		else
			game.addField(new Hole(name,false));
	}
	public static void setSwitch(String name,String state,String hole) {
		if(state.compareTo("off")==0)
			game.addField(new Switch(name,false,hole));
		else
		if(state.compareTo("on")==0)
			game.addField(new Switch(name,true,hole));
		else
			game.addField(new Switch(name,false,hole));
	}
	public static void listFieldStates() {
		game.listFieldState();
	}
	public static void listBox() {
		game.listBox();
	}
	public static void listPoints() {
		game.listPoints();
	}
	public static void listRound() {
		game.listRound();
	}
	public static void listCohesion() {
		game.listCohesion();
	}
	public static void pass() {
		Game.NextRound();
	}
	

}
