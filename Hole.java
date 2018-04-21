

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
	/**
	 * Kinyitja a lyukat.
	 */
	public void Open( ) {
		isOpen = true; 
		//A lyuk kinyitásakor eltünteti a rajta lévõ dolgot
		if(this.GetThing()!=null) {
			this.GetThing().Disappear();
		}
	}
	
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
	 * Kiértékeli, hogy a lyukra léphet-e a MovableThing (ami elvileg egy Worker lesz), és ha igen, akkor megsemmisíti, ha a lyuk nyitva van. 
	 * @param t MovableThing típusú, ez az objektum lép a mezõre (és adott esetben semmisül meg).
	 * @param d Direction típusú, azaz irány, amerre a MovableThing haladna.
	 * @return boolean típussal tér vissza, amely akkor true, ha elfogadta a MovableThing-et.
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
}
