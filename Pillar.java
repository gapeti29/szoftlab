package sokoban;

/**
 * A palyan elhelyezkedo oszlopot reprezentalo osztaly.
 * Megakadalyozza a ladak es munkasok mozgasat.
 * Ha egy lada egy munkast az oszlopra tolna, akkor az oszlop megsemmisiti az adott munkast. 
 */
public class Pillar extends Field {
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
	 *  Nem fogad el semmit ez a mezo.
	 */
	public boolean DirectAccept(MovableThing t, Direction d, double s) {
		//Nem csinalunk semmit, csak visszaterunk hamissal.
		return false;
	}
	
	/**
	 * Ellenorzi, hogy van-e ervenyes lepes a parameterul kapott iranyba.
	 * @param d Direction tipusu, ebbe az iranyba haladna egy MovableThing.
	 * @return boolean tipussal ter vissza, mely mindig hamis.
	 */
	public boolean CheckMove(Direction d) {
		return false;
	}
}
