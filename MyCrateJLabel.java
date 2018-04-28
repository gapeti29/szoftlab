import java.awt.Image;

import javax.swing.*;

public class MyCrateJLabel extends JLabel{
	private Crate crate;
	private Map map;
	private boolean hasBeenDrawn;
	MyCrateJLabel(Crate c,Map m){
		map=m;
		crate=c;
		ImageIcon img = new ImageIcon("Crate.png");
		Image image = img.getImage();
		Image newimg = image.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH);
		img = new ImageIcon(newimg); 
		this.setIcon(img);
		hasBeenDrawn=false;
	}
	public Crate getCrate() {
		return crate;
	}
	public void redraw() {
		this.repaint();
	}
	public boolean getHasBeenDrawn() {
		return hasBeenDrawn;
	}
	public void setHasBeenDrawn(boolean arg) {
		hasBeenDrawn=arg;
	}
}