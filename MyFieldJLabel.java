
import java.awt.Color;
import java.awt.Image;

import javax.swing.*;

public class MyFieldJLabel extends JLabel {
	private Field field;
	private boolean hasBeenDrawn;
	private int positionX,positionY;
	MyFieldJLabel(Field f,String image_name){
		field=f;
		hasBeenDrawn=false;
		this.setBackground(Color.blue);
		ImageIcon img = new ImageIcon(image_name);
		Image image = img.getImage();
		Image newimg = image.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH);
		img = new ImageIcon(newimg); 
		this.setIcon(img);

	}
	MyFieldJLabel(){
		hasBeenDrawn=false;
	}
	public Field getField() {
		return field;
	}
	public void setHasBeenDrawn(boolean arg) {
		hasBeenDrawn=arg;
	}
	public boolean getHasBeenDrawn() {
		return hasBeenDrawn;
	}
	public void setPositionX(int x) {
		positionX=x;
	}
	public void setPositionY(int y) {
		positionY=y;
	}
	public int getPositionX() {
		return positionX;
	}
	public int getPositionY() {
		return positionY;
	}
}
