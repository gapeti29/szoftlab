package sokoban;

import java.awt.Color;
import java.awt.Image;

import javax.swing.*;

public class MyFieldJLabel extends JLabel {
	protected Field field;
	protected boolean hasBeenDrawn;
	private int positionX,positionY;
	MyFieldJLabel(Field f,String image_name){
		field=f;
		hasBeenDrawn=false;
		this.setOpaque(true);
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
	public void reDraw() {
		if(field.getCohesion()==0) {
		}
		else{
			if(field.getCohesion()>0) {
				this.setBackground(Color.darkGray);
			}	
			if(field.getCohesion()<0) {
				this.setBackground(Color.yellow);
			}
		}
	}
}
