package sokoban;

/**
 *A lyuk mezore kerulo munkasokat, es az ide tolt ladakat megsemmisiti, igy azok eltunnek a jatekbol.
 *Bizonyos lyukakhoz kapcsolo tartozik.
 *Ezek csak akkor viselkednek lyukkent, ha nyitott allapotban vannak, kulonben hagyomanyos mezonek latszanak es ugy is viselkednek.
 */
public class Hole extends Field {
	/**
	 * Tarolja, hogy nyitva van-e a lyuk.
	 */
	private boolean isOpen;
	
	/**
	 * Letrehoz egy lyukat a megadott nevvel, es tarolja, hogy nyitva van-e.
	 * @param name1 String tipusu, a lyuk neve.
	 * @param open_close boolean tipusu, mely akkor true, ha nyitva van a lyuk.
	 */
	public Hole(String name1,boolean open_close) {
		name=name1;
		isOpen=open_close;
	}

	/**
	 * Kinyitja a lyukat.
	 */
	public void Open( ) {
		isOpen = true; 
		//A lyuk kinyitasakor eltunteti a rajta levo dolgot
		if(this.GetThing()!=null) {
			this.GetThing().Disappear();
		}
	}
	
	/**
	 * Bezarja a lyukat.
	 */
	public void Close() { isOpen = false; }
	
	/**
	 * Kiertekeli, hogy a lyukra lephet-e a MovableThing, es ha igen, akkor megsemmisiti, ha a lyuk nyitva van. 
	 * @param t MovableThing tipusu, ez az objektum lep a mezore (es adott esetben semmisul meg).
	 * @param d Direction tipusu, azaz irany, amerre a MovableThing haladna.
	 * @param s double tipusu, a toloero.
	 * @return boolean tipussal ter vissza, amely akkor true, ha elfogadta a MovableThing-et.
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
	 * Kiertekeli, hogy a lyukra lephet-e a MovableThing (ami elvileg egy Worker lesz), es ha igen, akkor megsemmisiti, ha a lyuk nyitva van. 
	 * @param t MovableThing tipusu, ez az objektum lep a mezore (es adott esetben semmisul meg).
	 * @param d Direction tipusu, azaz irany, amerre a MovableThing haladna.
	 * @param s double tipusu, a toloero.
	 * @return boolean tipussal ter vissza, amely akkor true, ha elfogadta a MovableThing-et.
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

	/**
	 * Kiirja a lyuk nevevel egyutt azt, hogy nyitva, vagy csukva van-e.
	 */
	public void List() {
		if(isOpen)	System.out.println(this.getName()+"	Nyitva");
		else		System.out.println(this.getName()+"	Zárva");
	}
}
