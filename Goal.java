package sokoban;

/**
 * A jatek celmezoje, ide kell eljuttatni a ladakat.
 * A celmezore erkezo ladakat eltunteti a jatekbol, es az azt oda juttato jatekosnak egy pontot ad.
 * Ha munkas lep ra, akkor sima mezokent viselkedik. 
 */
public class Goal extends Field {
	/**
	 * Letrehoz egy celmezot a megadott nevvel.
	 * @param name1 String tipusu.
	 */
	public Goal(String name1) {
		name=name1;
	}

	/**
	 * Megprobalja elfogadni a mezore erkezo MovableThing tipusu objektumot.
	 * Ha sikerult, akkor meghivja annak a celmezore erkezest kezelo fuggvenyet.
	 * @param t MovableThing tipusu, ez az objektum kerulne a mezore.
	 * @param d Direction tipusu, ebbe az iranyba halad a parameterul kapott objektum.
	 * @param s double tipusu, a toloero.
	 * @return boolean tipussal ter vissza, amely akkor true, ha elfogadta a MovableThing-et.
	 */
	public boolean DirectAccept(MovableThing t, Direction d, double s) {
		if(t.DirectPushedBy(d, s)) {
			this.CrateDelivered();
			this.GetWarehouse().removeBox((Crate) t);
			t.SetField(null);
		}
		else {
			SetThing(t);
			t.SetField(this);
		}
		return true;
	}

	/**
	 * Megprobalja elfogadni a mezore erkezo MovableThing tipusu objektumot.
	 * Ha sikerult, akkor meghivja annak a celmezore erkezest kezelo fuggvenyet.
	 * @param t MovableThing tipusu, ez az objektum kerulne a mezore.
	 * @param d Direction tipusu, ebbe az iranyba halad a parameterul kapott objektum.
	 * @param s double tipusu, a toloero.
	 * @return boolean tipussal ter vissza, amely akkor true, ha elfogadta a MovableThing-et.
	 */
	public boolean Accept(MovableThing t, Direction d, double s) {
		if(t.DirectPushedBy(d, s)) {
			this.CrateDelivered();
			this.GetWarehouse().removeBox((Crate) t);
			t.SetField(null);
		}
		else {
			SetThing(t);
			t.SetField(this);
		}
		return true;
	}
	
	/**
	 * Az aktualisan soron levo jatekos betolt egy ladat a celmezore, ezert o kap egy plusz pontot.
	 */
	public void CrateDelivered() {
		Game.AddPointToWorker();
	}
}
