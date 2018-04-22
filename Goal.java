
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
	 * Megpr�b�lja elfogadni a mez�re �rkez� MovableThing t�pus� objektumot.
	 * Ha siker�lt, akkor megh�vja annak a c�lmez�re �rkez�st kezel� f�ggv�ny�t.
	 * @param t MovableThing t�pus�, ez az objektum ker�lne a mez�re.
	 * @param d Direction t�pus�, ebbe az ir�nyba halad a param�ter�l kapott objektum.
	 * @return boolean t�pussal t�r vissza, amely akkor true, ha elfogadta a MovableThing-et.
	 */
	public boolean Accept(MovableThing t, Direction d, double s) {
		if(super.Accept(t, d, s)) {
			//Megh�vja a c�lmez�re �r�st kezel� f�ggv�ny�t.
			t.AtGoal(this);
			return true;
		}
		else
			return false;
	}
	
	/**
	 * Az aktu�lisan soron l�v� j�t�kos betolt egy l�d�t a c�lmez�re, ez�rt � kap egy plusz pontot.
	 */
	public void CrateDelivered() {
		Game.AddPointToWorker();
	}
}
