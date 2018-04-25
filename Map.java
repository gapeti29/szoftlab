import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class Map extends JFrame{
	ArrayList<MyFieldJLabel> fields=new ArrayList<MyFieldJLabel>();
	ArrayList<MyWorkerJLabel> workers=new ArrayList<MyWorkerJLabel>();
	JFrame frame;
	Warehouse warehouse;
	Map(){
		frame= new JFrame();
		frame.setSize(600, 600);
		frame.setLocation(200, 200);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Sokoban");
		frame.setEnabled(true);
		frame.setLayout(null);
		frame.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				if(key== KeyEvent.VK_UP) {
					Menu.moveWorker("Up");
					//locateWorkers();
				}
				if(key== KeyEvent.VK_RIGHT) {
					Menu.moveWorker("Right");
					locateWorkers();
				}
				if(key== KeyEvent.VK_DOWN) {
					Menu.moveWorker("Down");
					//locateWorkers();
				}
				if(key== KeyEvent.VK_LEFT) {
					Menu.moveWorker("Left");
					locateWorkers();
				}
			}
			public void keyReleased(KeyEvent e) {}
			public void keyTyped(KeyEvent e) {}
		});
	}
	public void setWarehouse(Warehouse w) {
		warehouse=w;
	}
	public void addField(Field f) {
		fields.add(new MyFieldJLabel(f));
	}
	public void addWorker(Worker w) {
		MyWorkerJLabel worker=new MyWorkerJLabel(w,this);
		workers.add(worker);
	}
	public void addCrate() {
		
	}
	public void locateWorkers() {
		int x=0,y=0;
		for(MyWorkerJLabel worker:workers) {
			for(MyFieldJLabel jl:fields) {
				if(jl.getField()==worker.getWorker().GetField()) {
					x=jl.getPositionX();
					y=jl.getPositionY();
					System.out.println(x+" , "+y);
				}
			}
			worker.setVisible(true);
			worker.setEnabled(true);
			worker.setBounds(x, y, 50, 50);
			frame.add(worker);
			frame.setComponentZOrder(worker, 0);
			worker.repaint();
		}
	}
	public void redrawMap() {
		for(MyWorkerJLabel worker:workers) {
			worker.redraw();
		}
	}
	public void buildMap() {
		if(fields.size()>0) {
			drawField(fields.get(0).getField(),300,300);
		}
	}
	public void drawField(Field f,int x,int y) {
		for(MyFieldJLabel jl:fields) {
			if(jl.getField()==f) {
				jl.setHasBeenDrawn(true);
				jl.setVisible(true);
				jl.setEnabled(true);
				jl.setBounds(x, y, 50, 50);
				jl.setPositionX(x);
				jl.setPositionY(y);
				frame.add(jl);
				frame.setComponentZOrder(jl, 0);
				jl.repaint();
			}
		}
		if(f.GetNeighbour(Direction.Up)!=null) {
			for(MyFieldJLabel jl:fields) {
				if(jl.getField()==f.GetNeighbour(Direction.Up)&&!jl.getHasBeenDrawn()) {
					drawField(f.GetNeighbour(Direction.Up),x+0,y+50);
				}
			}
		}
		if(f.GetNeighbour(Direction.Right)!=null) {
			for(MyFieldJLabel jl:fields) {
				if(jl.getField()==f.GetNeighbour(Direction.Right)&&!jl.getHasBeenDrawn()) {
					drawField(f.GetNeighbour(Direction.Right),x+50,y);
				}
			}
		}
		if(f.GetNeighbour(Direction.Down)!=null) {
			for(MyFieldJLabel jl:fields) {
				if(jl.getField()==f.GetNeighbour(Direction.Down)&&!jl.getHasBeenDrawn()) {
					drawField(f.GetNeighbour(Direction.Down),x+0,y-50);
				}
			}
		}
		if(f.GetNeighbour(Direction.Left)!=null) {
			for(MyFieldJLabel jl:fields) {
				if(jl.getField()==f.GetNeighbour(Direction.Left)&&!jl.getHasBeenDrawn()) {
					drawField(f.GetNeighbour(Direction.Left),x-50,y+0);
				}
			}
		}
	}

}
