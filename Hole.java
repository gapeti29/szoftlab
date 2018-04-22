

public class Hole extends Field {
	private boolean isOpen;
	
	public Hole(Field f){
		this.SetMaterial(f.GetMaterial());
		this.SetNeighbour(f.GetNeighbour(Direction.Up), Direction.Up);
		this.SetNeighbour(f.GetNeighbour(Direction.Down), Direction.Down);
		this.SetNeighbour(f.GetNeighbour(Direction.Left), Direction.Left);
		this.SetNeighbour(f.GetNeighbour(Direction.Right), Direction.Right);
		this.SetThing(f.GetThing());
		this.SetWarehouse(f.GetWarehouse());
	}
	public Hole(String name1,boolean open_close) {
		name=name1;
		isOpen=open_close;
	}
	/**
	 * Kinyitja a lyukat.
	 */
	public void Open( ) {
		isOpen = true; 
		//A lyuk kinyit�sakor elt�nteti a rajta l�v� dolgot
		if(this.GetThing()!=null) {
			this.GetThing().Disappear();
		}
	}
	
	/**
	 * Bez�rja a lyukat.
	 */
	public void Close() { isOpen = false; }
	
	/**
	 * Ki�rt�keli, hogy a lyukra l�phet-e a MovableThing, �s ha igen, akkor megsemmis�ti, ha a lyuk nyitva van. 
	 * @param t MovableThing t�pus�, ez az objektum l�p a mez�re (�s adott esetben semmis�l meg).
	 * @param d Direction t�pus�, azaz ir�ny, amerre a MovableThing haladna.
	 * @return boolean t�pussal t�r vissza, amely akkor true, ha elfogadta a MovableThing-et.
	 */
	public boolean Accept(MovableThing t, Direction d, double s) {
		if(super.Accept(t, d, s)) {
			if(isOpen) {
				t.Disappear();
			}
			return true;
		}
		else
			return false;
	}
	
	/**
	 * Ki�rt�keli, hogy a lyukra l�phet-e a MovableThing (ami elvileg egy Worker lesz), �s ha igen, akkor megsemmis�ti, ha a lyuk nyitva van. 
	 * @param t MovableThing t�pus�, ez az objektum l�p a mez�re (�s adott esetben semmis�l meg).
	 * @param d Direction t�pus�, azaz ir�ny, amerre a MovableThing haladna.
	 * @return boolean t�pussal t�r vissza, amely akkor true, ha elfogadta a MovableThing-et.
	 */
	public boolean DirectAccept(MovableThing t, Direction d, double s) {
		if(super.DirectAccept(t, d, s)) {
			if(isOpen) {
				t.Disappear();
			}
			return true;
		}
		else
			return false;
	}
	public void List() {
		if(isOpen)	System.out.println(this.getName()+"	Nyitva");
		else		System.out.println(this.getName()+"	Z�rva");
	}
}
