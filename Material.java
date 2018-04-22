package sokoban;

/**
 * Absztrakt ososztaly, a mezokon elhelyezheto kenoanyagokat foglalja magaban.
 * Az egyes anyagokat a munkasok helyezhetik el azokra a mezokre, amelyeken eppen allnak.
 * Egy mezon egyszerre csak egy darab anyag lehet.
 * Ha ugy akarunk kenoanyagot helyezni egy mezore, hogy azon mar van egy masik fajta, akkor kicserelodik, ha ugyanaz, akkor a hatasa nem sokszorozodik.
 */
public abstract class Material {	
	/**
	 * A parameterul kapott mezore kerul az anyag.
	 * @param f Field tipusu, erre a mezore rahelyezi magat az anyag.
	 */
	public void PutOn(Field f) {
		f.SetMaterial(this);
	}
	
	public abstract double ModifyCohesion(double c);
}
