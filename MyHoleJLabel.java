package sokoban;

import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;

public class MyHoleJLabel extends MyFieldJLabel {
	private boolean isOpen;
	private ImageIcon img_open;
	private ImageIcon img_closed;
	MyHoleJLabel(Field f,String image_name){
		field=(Hole)f;
		hasBeenDrawn=false;
		isOpen=((Hole)field).IsOpen();
		this.setOpaque(true);
		
		ImageIcon img = new ImageIcon("Hole_open.png");
		Image image = img.getImage();
		Image newimg = image.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH);
		img_open = new ImageIcon(newimg); 
		
		img = new ImageIcon("Hole_closed.png");
		image = img.getImage();
		newimg = image.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH);
		img_closed = new ImageIcon(newimg); 
		if(isOpen) {
			this.setIcon(img_open);
		}
		else {
			this.setIcon(img_closed);
		}
	}
	private void setOpen(boolean arg) {
		isOpen=arg;
	}
	public void reDraw() {
		if(((Hole)field).IsOpen()!=isOpen) {
			if(((Hole)field).IsOpen()==true) {
				this.setIcon(img_open);
				this.setOpen(true);
			}
			if(((Hole)field).IsOpen()==false) {
				this.setIcon(img_closed);
				this.setOpen(false);
			}
		}
		if(field.getCohesion()==0) {
		}
		else{
			if(field.getCohesion()>0)
				this.setBackground(Color.yellow);
			if(field.getCohesion()<0)
				this.setBackground(Color.lightGray);
		}
	}
}
