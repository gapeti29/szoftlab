package sokoban;

/**
 * A lyukak allapotat vezerlo elem.
 * Ki tudja nyitni vagy be tudja csukni a hozza tartozo lyukat.
 * A tobbi mezohoz hasonloan kepes mozgo dolgokat (munkasokat vagy ladakat) tarolni.
 * Egy lada csak ugy kerulhet le egy kapcsolorol, ha egy masik lada vagy egy munkas letolja onnan.
 * Mivel az elso esetben a kapcsolo allapota nem valtozik, ezert a kapcsolo csak akkor kapcsol ki, ha munkas lep ra.
 */
public class Switch extends Field{
	/**
	 * A kapcsolohoz tartozo lyuk, amelyet iranyitani tud.
	 */
	private Hole holes;

	/**
	 * A parameterul megadott nevvel letrehoz egy kapcsolot, beallitja hozza a lyukat, amit kinyit, vagy bezar.
	 * @param name1 String tipusu, a kapcsolo neve.
	 * @param on_off boolean tipusu, mely akkor true, ha nyitva van a lyuk.
	 * @param holes1 String tipusu, a kapcsolohoz tartozo lyuk neve.
	 */
	public Switch(String name1,boolean on_off,String holes1) {
		name=name1;
		holes=(Hole) this.GetWarehouse().findField(holes1);
		if(on_off)this.TurnOn();
		else this.TurnOff();
	}

	/**
	 * A parameterul kapott lyukat tudja majd allitani.
	 * @param h Hole tipusu.
	 */
	public void SetHole(Hole h) { holes = h; }
	
	/**
	 * Megprobalja elfogadni a mezore erkezo MovableThing tipusu objektumot, es meghivja annak a kapcsolo kezelo fuggvenyet, ha elfogadta.
	 * @param t MovableThing tipusu, ez az objektum kerulne a mezore.
	 * @param d Direction tipusu, ebbe az iranyba halad a parameterul kapott objektum.
	 * @return boolean tipussal ter vissza, mely akkor true, ha elfogadta a MovableThing-et.
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
	 * Megprobalja elfogadni a mezore erkezo MovableThing tipusu objektumot, es meghivja annak a kapcsolo kezelo fuggvenyet, ha elfogadta.
	 * @param t MovableThing tipusu, ez az objektum kerulne a mezore.
	 * @param d Direction tipusu, ebbe az iranyba halad a parameterul kapott objektum.
	 * @return boolean tipussal ter vissza, mely akkor true, ha elfogadta a MovableThing-et.
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
	 * Kinyitja a kapcsolohoz tartozo lyukat.
	 */
	public void TurnOn() { holes.Open(); }
	
	/**
	 * Becsukja a kapcsolohoz tartozo lyukat.
	 */
	public void TurnOff() { holes.Close(); }

	/**
	 * Kiirja, hogy tartozik-e hozza lyuk, es ha igen, akkor az nyitva van-e.
	 */
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
