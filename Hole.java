package sokoban;

public class Hole extends Field {
	private boolean isOpen;
	
	/**
	 * Kinyitja a lyukat.
	 */
	public void Open( ) { isOpen = true; }
	
	/**
	 * Bezárja a lyukat.
	 */
	public void Close() { isOpen = false; }
	
	/**
	 * Kiértékeli, hogy a lyukra léphet-e a MovableThing, és ha igen, akkor megsemmisíti, ha a lyuk nyitva van. 
	 * @param t MovableThing típusú, ez az objektum lép a mezõre (és adott esetben semmisül meg).
	 * @param d Direction típusú, azaz irány, amerre a MovableThing haladna.
	 * @return boolean típussal tér vissza, amely akkor true, ha elfogadta a MovableThing-et.
	 */
	public boolean Accept(MovableThing t, Direction d) {
		if(super.Accept(t, d)) {
			if(isOpen) {
				t.Disappear();
			}
			return true;
		}
		else
			return false;
	}
	
	/**
	 * Kiértékeli, hogy a lyukra léphet-e a MovableThing (ami elvileg egy Worker lesz), és ha igen, akkor megsemmisíti, ha a lyuk nyitva van. 
	 * @param t MovableThing típusú, ez az objektum lép a mezõre (és adott esetben semmisül meg).
	 * @param d Direction típusú, azaz irány, amerre a MovableThing haladna.
	 * @return boolean típussal tér vissza, amely akkor true, ha elfogadta a MovableThing-et.
	 */
	public boolean DirectAccept(MovableThing t, Direction d) {
		if(super.DirectAccept(t, d)) {
			if(isOpen) {
				t.Disappear();
			}
			return true;
		}
		else
			return false;
	}
}
