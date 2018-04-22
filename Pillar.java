package sokoban;

/**
 * A palyan elhelyezkedo oszlopot reprezentalo osztaly.
 * Megakadalyozza a ladak es munkasok mozgasat.
 * Ha egy lada egy munkast az oszlopra tolna, akkor az oszlop megsemmisiti az adott munkast. 
 */
public class Pillar extends Field {
	/**
	 * A parameterul kapott mezovel egyenerteku oszlopot hoz letre.
	 * @param f Field tipusu.
	 */
	public Pillar(Field f){
		this.SetMaterial(f.GetMaterial());
		this.SetNeighbour(f.GetNeighbour(Direction.Up), Direction.Up);
		this.SetNeighbour(f.GetNeighbour(Direction.Down), Direction.Down);
		this.SetNeighbour(f.GetNeighbour(Direction.Left), Direction.Left);
		this.SetNeighbour(f.GetNeighbour(Direction.Right), Direction.Right);
		this.SetThing(f.GetThing());
		this.SetWarehouse(f.GetWarehouse());
	}
	
	/**
	 * A parameterul megadott nevvel hoz letre egy oszlopot.
	 * @param name1
	 */
	public Pillar(String name1) {
		name=name1;
	}
	
	/**
	 * Nem fogad el semmit ez a mezo.
	 */
	public boolean Accept(MovableThing t, Direction d, double s) {
		//Nem csinalunk semmit, csak visszaterunk hamissal.
		return false;
	}
	
	/**
	 * Nem fogad el semmit ez a mezo.
	 */
	public boolean DirectAccept(MovableThing t, Direction d, double s) {
		//Nem csinalunk semmit, csak visszaterunk hamissal.
		return false;
	}
}
