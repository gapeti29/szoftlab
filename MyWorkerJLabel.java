import java.awt.Image;

import javax.swing.*;

public class MyWorkerJLabel extends JLabel{
	private Worker worker;
	private Map map;
	MyWorkerJLabel(Worker w,Map m){
		map=m;
		worker=w;
		ImageIcon img = new ImageIcon("Worker2.png");
		Image image = img.getImage();
		Image newimg = image.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH);
		img = new ImageIcon(newimg); 
		this.setIcon(img);
	}
	public Worker getWorker() {
		return worker;
	}
	public void redraw() {
		this.repaint();
	}
}

