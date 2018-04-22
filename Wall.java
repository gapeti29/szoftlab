package sokoban;

public class Wall {
	private Pillar[] components;
	
	/**
	 * A components t�mb v�g�re sz�rja a kapott oszlopot.
	 * @param p Pillar t�pus�.
	 */
	public void AddComponent(Pillar p) {
		if(components == null) {
			components = new Pillar[1];
			components[0] = p;
		}
		else {
			Pillar[] temp = new Pillar[components.length+1];
			for(int i = 0; i < components.length; ++i) {
				temp[i] = components[i];
			}
			temp[components.length] = p;
			components = temp;
		}
	}
}
