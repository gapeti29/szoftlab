

public class Pillar extends Field {
	public Pillar(Field f){
		this.SetMaterial(f.GetMaterial());
		this.SetNeighbour(f.GetNeighbour(Direction.Up), Direction.Up);
		this.SetNeighbour(f.GetNeighbour(Direction.Down), Direction.Down);
		this.SetNeighbour(f.GetNeighbour(Direction.Left), Direction.Left);
		this.SetNeighbour(f.GetNeighbour(Direction.Right), Direction.Right);
		this.SetThing(f.GetThing());
		this.SetWarehouse(f.GetWarehouse());
	}
	public Pillar(String name1) {
		name=name1;
	}
	/**
	 * Nem fogad el semmit ez a mez�.
	 */
	public boolean Accept(MovableThing t, Direction d, double s) {
		//Nem csin�lunk semmit, csak visszat�r�nk hamissal.
		return false;
	}
	
	/**
	 * Nem fogad el semmit ez a mez�.
	 */
	public boolean DirectAccept(MovableThing t, Direction d, double s) {
		//Nem csin�lunk semmit, csak visszat�r�nk hamissal.
		return false;
	}
}
