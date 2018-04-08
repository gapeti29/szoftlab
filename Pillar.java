package sokoban;

public class Pillar extends Field {
	/**
	 * Nem fogad el semmit ez a mezõ.
	 */
	public boolean Accept(MovableThing t, Direction d, double s) {
		//Nem csinálunk semmit, csak visszatérünk hamissal.
		return false;
	}
	
	/**
	 * Nem fogad el semmit ez a mezõ.
	 */
	public boolean DirectAccept(MovableThing t, Direction d, double s) {
		//Nem csinálunk semmit, csak visszatérünk hamissal.
		return false;
	}
}
