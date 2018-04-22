
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
	 * A paraméterül kapott lyukat tudja majd állítani.
	 * @param h Hole típusú.
	 */
	public void SetHole(Hole h) { holes = h; }
	
	/**
	 * Megpróbálja elfogadni a mezõre érkezõ MovableThing típusú objektumot, és meghívja annak a kapcsoló kezelõ függvényét, ha elfogadta.
	 * @param t MovableThing típusú, ez az objektum kerülne a mezõre.
	 * @param d Direction típusú, ebbe az irányba halad a paraméterül kapott objektum.
	 * @return boolean típussal tér vissza, mely akkor true, ha elfogadta a MovableThing-et.
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
	 * Megpróbálja elfogadni a mezõre érkezõ MovableThing típusú objektumot, és meghívja annak a kapcsoló kezelõ függvényét, ha elfogadta.
	 * @param t MovableThing típusú, ez az objektum kerülne a mezõre.
	 * @param d Direction típusú, ebbe az irányba halad a paraméterül kapott objektum.
	 * @return boolean típussal tér vissza, mely akkor true, ha elfogadta a MovableThing-et.
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
	 * Kinyitja a kapcsolóhoz tartozó lyukat.
	 */
	public void TurnOn() { holes.Open(); }
	
	/**
	 * Becsukja a kapcsolóhoz tartozó lyukat.
	 */
	public void TurnOff() { holes.Close(); }
	public void List() {
		if(holes==null) {
			System.out.println(this.getName()+"	Nem tartozik hozzá lyuk");
		}
		else {
			if(this.GetThing()==null)	System.out.println(this.getName()+"A hozza tartozo lyuk"+holes.getName()+":	Zárva");
			else						System.out.println(this.getName()+"A hozza tartozo lyuk"+holes.getName()+"	Nyitva");
		}
	}
}
