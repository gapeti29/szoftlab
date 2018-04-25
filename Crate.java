

/**
 * A lada nyilvantartja, hogy eppen melyik mezon all.
 * A ladakat munkasok el tudjak tolni, ekkor a lada szomszedos mezore mozog.
 * A mozgasa kozben eltolhat mas ladakat vagy munkasokat.
 * Ha lyukra vagy celmezore er, akkor a lada el tud tunni.
 * A lada felelossege, hogy eltuneserol a raktart ertesitse.
 * Ha egy lada kapcsolora er, akkor vezerelni tudja azt.
 * A lada tolasa kozben a tapadas mertekevel csokkenti a munkas toloerejet, ha ez nulla vagy negativ lesz, akkor a tolas sikertelen.
 */
public class Crate extends MovableThing{
	/**
	 * Letrehoz egy ladat a megadott nevvel.
	 * @param name1
	 */
	public Crate(String name1) {
		name=name1;
	}

	/**
	 * Ertesiti a raktarepuletet, hogy megsemmisult.
	 * Torli magat az aktualis mezorol.
	 * null ertekre allitja a tarolt mezo erteket.
	 */
	public void Disappear() {
		GetField().GetWarehouse().CrateRemoved(this);
		GetField().Remove(this);
		SetField(null);
	}
	
	/**
	 * Bekapcsolja a parameterul kapott kapcsolot.
	 * @param s Switch tipusu, ezt a kapcsolot fogja bekapcsolni.
	 */
	public void ControlSwitch(Switch s) {
		s.TurnOn();
	}
	
	/**
	 * A ladat arrebb akarjak tolni, ezert megprobal az adott iranyban arrebb tolodni.
	 * @param d Direction tipusu, ebbe az iranyba tolodna a lada.
	 * @return boolean tipusu, true ertekkel ter vissza, ha sikeres volt a mozgas.
	 */
	public boolean PushedBy(Direction d, double s)  {
		s = GetField().ApplyCohesion(s);
		//Csak akkor tolódik arrébb a láda, ha megfelelõ erõvel tolják, tehát még pozitív az s erõ.
		if(s > 0) {
			return Move(d, s);
		}
		else {
			return false;
		}
	}
	
	/**
	 * A ladat lehet direktben tolni, ezert megprobal az adott iranyban arrebb tolodni.
	 * @param d Direction tipusu, ebbe az iranyba tolodna a lada.
	 * @return boolean tipusu, true ertekkel ter vissza, ha sikeres volt a mozgas.
	 */
	public boolean DirectPushedBy(Direction d, double s) {
		s = GetField().ApplyCohesion(s);
		//Csak akkor tolódik arrébb a láda, ha megfelelõ erõvel tolják, tehát még pozitív az s erõ.
		if(s > 0) {
			return Move(d, s);
		}
		else {
			return false;
		}
	}
	
	/**
	 * A lada celmezore ert, errol ertesiti a parameterul kapott celmezot, majd torli magat.
	 * @param g Goal tipusu, erre a celmezore ert a lada.
	 */
	public void AtGoal(Goal g) {
		g.CrateDelivered();
		Disappear();
	}
	
	/**
	 * A ladanak akkor van ervenyes lepese, ha fuggolegesen, vagy vizszintesen el tud mozdulni.
	 * @return boolean tipussal ter vissza, melynek akkor true az erteke, ha el tud mozdulni a lada.
	 */
	public boolean HasMoves() {
		if( ( GetField().CheckMove(Direction.Up) && GetField().CheckMove(Direction.Down) ) ||
			( GetField().CheckMove(Direction.Left) && GetField().CheckMove(Direction.Right) ) ) {
				return true;
		}
		else
			return false;
	}
}
