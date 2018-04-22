
public class Goal extends Field {
	public Goal(Field f){
		this.SetMaterial(f.GetMaterial());
		this.SetNeighbour(f.GetNeighbour(Direction.Up), Direction.Up);
		this.SetNeighbour(f.GetNeighbour(Direction.Down), Direction.Down);
		this.SetNeighbour(f.GetNeighbour(Direction.Left), Direction.Left);
		this.SetNeighbour(f.GetNeighbour(Direction.Right), Direction.Right);
		this.SetThing(f.GetThing());
		this.SetWarehouse(f.GetWarehouse());
	}
	
	public Goal(String name1) {
		name=name1;
	}

	/**
	 * Megpróbálja elfogadni a mezõre érkezõ MovableThing típusú objektumot.
	 * Ha sikerült, akkor meghívja annak a célmezõre érkezést kezelõ függvényét.
	 * @param t MovableThing típusú, ez az objektum kerülne a mezõre.
	 * @param d Direction típusú, ebbe az irányba halad a paraméterül kapott objektum.
	 * @return boolean típussal tér vissza, amely akkor true, ha elfogadta a MovableThing-et.
	 */
	public boolean Accept(MovableThing t, Direction d, double s) {
		if(super.Accept(t, d, s)) {
			//Meghívja a célmezõre érést kezelõ függvényét.
			t.AtGoal(this);
			return true;
		}
		else
			return false;
	}
	
	/**
	 * Az aktuálisan soron lévõ játékos betolt egy ládát a célmezõre, ezért õ kap egy plusz pontot.
	 */
	public void CrateDelivered() {
		Game.AddPointToWorker();
	}
}
