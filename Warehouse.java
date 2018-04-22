import java.util.ArrayList;

public class Warehouse {
	private int remainingCrates;
	private ArrayList<Field> fields=new ArrayList<Field>();
	private ArrayList<Crate> crates=new ArrayList<Crate>();
	
	
	/**
	 * A param�ter�l kapott l�d�t a l�d�kat tartalmaz� t�mb v�g�re sz�rja.
	 * @param c Crate t�pus�.
	 */
	public void AddCrate(Crate c) {
		crates.add(c);
	}
	
	/**
	 * Megadja a rakt�rban l�v� l�d�k sz�m�t.
	 * @return int t�pust ad vissza.
	 */
	public int GetRemainingCrates() { return remainingCrates; }
	
	/**
	 * Egy l�da megsz�nt valamilyen okb�l.
	 * @param c Crate t�pus�, ez a l�da sz�nt meg.
	 */
	public void CrateRemoved(Crate c) {
		crates.remove(c);
	}
	
	/**
	 * A param�ter�l kapott mez�t besz�rja a p�ly�ba, a param�ter�l megadott helyre.
	 * @param f Field t�pus�, ez a mez� ker�l besz�r�sra.
	 * @param i int t�pus�, a sor sz�ma.
	 * @param j int t�pus�, az oszlop sz�ma.
	 */
	public void AddField(Field name) {
		fields.add(name);
	}
	
	
	/**
	 * Ellen�rzi, hogy van-e m�g �rv�nyes l�p�s a j�t�kban.
	 * @param workers Worker[] t�pus�, mely a j�t�kban l�v� munk�sok referenci�j�t adja meg.
	 * @return boolean t�pussal t�r vissza, mely akkor true, ha van olyan munk�s �s olyan l�da, mely tud l�pni.
	 */
	public boolean HasMoves(ArrayList<Worker> workers) {
		//Munk�sok
		boolean workersCanMove = false;
		for(Worker w:workers) {
			if(w.HasMoves())
				workersCanMove = true;
		}
		
		//L�d�k
		boolean cratesCanMove = false;
		for(Crate c:crates) {
			if(c.HasMoves())
				cratesCanMove = true;
		}
	
		//Csak akkor igaz, ha a munk�sok �s a l�d�k k�z�l is tud valamelyik l�pni. 
		if(workersCanMove && cratesCanMove)
			return true;
		else
			return false;
	}	
	public Field findField(String name) {
		for(Field f:fields) {
			if(f.getName().compareTo(name)==0)return f;
		}
		return null;
	}
	public void listBox() {
		for(Crate c: crates)
			System.out.println(c.GetField().getName());
	}
	public void listCohesion() {
		for(Field f:fields) {
			System.out.println(f.getName()+": "+f.getCohesion());
		}
	}
	public void listFieldState() {
		for(Field f:fields) {
			f.List();
		}
	}
}
