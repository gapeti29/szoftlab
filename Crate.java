package sokoban;

public class Crate extends MovableThing{
	
	/**
	 * Értesíti a raktárépületet, hogy megsemmisült.
	 * Törli magát az aktuális mezõrõl.
	 * null értékre állítja a tárolt mezõ értékét.
	 */
	public void Disappear() {
		GetField().GetWarehouse().CrateRemoved(this);
		GetField().Remove(this);
		SetField(null);
	}
	
	/**
	 * Bekapcsolja a paraméterül kapott kapcsolót.
	 * @param s Switch típusú, ezt a kapcsolót fogja bekapcsolni.
	 */
	public void ControlSwitch(Switch s) {
		s.TurnOn();
	}
	
	/**
	 * A ládát arrébb akarják tolni, ezért megpróbál az adott irányban arrébb tolódni.
	 * @param d Direction típusú, ebbe az irányba tolódna a láda.
	 * @return boolean típusú, true értékkel tér vissza, ha sikeres volt a mozgás.
	 */
	public boolean PushedBy(Direction d)  {
		return Move(d);
	}
	
	/**
	 * A ládát lehet direktben tolni, ezért megpróbál az adott irányban arrébb tolódni.
	 * @param d Direction típusú, ebbe az irányba tolódna a láda.
	 * @return boolean típusú, true értékkel tér vissza, ha sikeres volt a mozgás.
	 */
	public boolean DirectPushedBy(Direction d) {
		return Move(d);
	}
	
	/**
	 * A láda célmezõre ért, errõl értesíti a paraméterül kapott célmezõt, majd törli magát.
	 * @param g Goal típusú, erre a célmezõre ért a láda.
	 */
	public void AtGoal(Goal g) {
		g.CrateDelivered();
		Disappear();
	}
	
	/**
	 * A ládanak akkor van érvényes lépése, ha függelegesen, vagy vízszintesen el tud mozdulni.
	 * @return boolean típussal tér vissza, melynek akkor true az értéke, ha el tud mozdulni a láda.
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
