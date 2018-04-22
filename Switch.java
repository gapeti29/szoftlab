
public class Switch extends Field{
	private Hole holes;
	public Switch(Field f){
		this.SetMaterial(f.GetMaterial());
		this.SetNeighbour(f.GetNeighbour(Direction.Up), Direction.Up);
		this.SetNeighbour(f.GetNeighbour(Direction.Down), Direction.Down);
		this.SetNeighbour(f.GetNeighbour(Direction.Left), Direction.Left);
		this.SetNeighbour(f.GetNeighbour(Direction.Right), Direction.Right);
		this.SetThing(f.GetThing());
		this.SetWarehouse(f.GetWarehouse());
	}
	
	public Switch(String name1,boolean on_off,String holes1) {
		name=name1;
		if(on_off)this.TurnOn();
		else this.TurnOff();
		holes=(Hole) this.GetWarehouse().findField(holes1);
	}

	/**
	 * A param�ter�l kapott lyukat tudja majd �ll�tani.
	 * @param h Hole t�pus�.
	 */
	public void SetHole(Hole h) { holes = h; }
	
	/**
	 * Megpr�b�lja elfogadni a mez�re �rkez� MovableThing t�pus� objektumot, �s megh�vja annak a kapcsol� kezel� f�ggv�ny�t, ha elfogadta.
	 * @param t MovableThing t�pus�, ez az objektum ker�lne a mez�re.
	 * @param d Direction t�pus�, ebbe az ir�nyba halad a param�ter�l kapott objektum.
	 * @return boolean t�pussal t�r vissza, mely akkor true, ha elfogadta a MovableThing-et.
	 */
	public boolean Accept(MovableThing t, Direction d, double s) {
		if(super.Accept(t, d, s)){
			t.ControlSwitch(this);
			return true;
		}
		else
			return false;
	}
	
	/**
	 * Megpr�b�lja elfogadni a mez�re �rkez� MovableThing t�pus� objektumot, �s megh�vja annak a kapcsol� kezel� f�ggv�ny�t, ha elfogadta.
	 * @param t MovableThing t�pus�, ez az objektum ker�lne a mez�re.
	 * @param d Direction t�pus�, ebbe az ir�nyba halad a param�ter�l kapott objektum.
	 * @return boolean t�pussal t�r vissza, mely akkor true, ha elfogadta a MovableThing-et.
	 */
	public boolean DirectAccept(MovableThing t, Direction d, double s) {
		if(super.DirectAccept(t, d, s)){
			t.ControlSwitch(this);
			return true;
		}
		else
			return false;
	}
	
	/**
	 * Kinyitja a kapcsol�hoz tartoz� lyukat.
	 */
	public void TurnOn() { holes.Open(); }
	
	/**
	 * Becsukja a kapcsol�hoz tartoz� lyukat.
	 */
	public void TurnOff() { holes.Close(); }
	public void List() {
		if(holes==null) {
			System.out.println(this.getName()+"	Nem tartozik hozz� lyuk");
		}
		else {
			if(this.GetThing()==null)	System.out.println(this.getName()+"A hozza tartozo lyuk"+holes.getName()+":	Z�rva");
			else						System.out.println(this.getName()+"A hozza tartozo lyuk"+holes.getName()+"	Nyitva");
		}
	}
}
