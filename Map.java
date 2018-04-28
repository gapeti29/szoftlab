import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class Map extends JFrame{
	ArrayList<MyFieldJLabel> fields=new ArrayList<MyFieldJLabel>();
	ArrayList<MyWorkerJLabel> workers=new ArrayList<MyWorkerJLabel>();
	ArrayList<MyCrateJLabel> crates=new ArrayList<MyCrateJLabel>();
	ScoreBoard score=new ScoreBoard();
	JFrame frame;
	JPanel bottom;
	JLabel round;
	JLabel currentPlayer;
	JButton pass;
	JButton showScore;
	Warehouse warehouse;
	Map(){
		frame= new JFrame();
		bottom=new JPanel(new FlowLayout());
		frame.setSize(600, 600);
		frame.setLocation(200, 200);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Sokoban");
		frame.setEnabled(true);
		frame.setLayout(null);
		frame.setFocusTraversalKeysEnabled(true);
		frame.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				if(key== KeyEvent.VK_UP) {
					Menu.moveWorker("Down");
				}
				if(key== KeyEvent.VK_RIGHT) {
					Menu.moveWorker("Right");
				}
				if(key== KeyEvent.VK_DOWN) {
					Menu.moveWorker("Up");
				}
				if(key== KeyEvent.VK_LEFT) {
					Menu.moveWorker("Left");
				}
				if(key==KeyEvent.VK_X) {
					Menu.setOil();
				}
				if(key==KeyEvent.VK_Y) {
					Menu.setHoney();
				}
				for(MyFieldJLabel jl: fields) {
					jl.reDraw();
				}
				locateWorkers();
				locateCrates();
			}
			public void keyReleased(KeyEvent e) {}
			public void keyTyped(KeyEvent e) {}
		});
		bottom.setBounds(0, 470, 600, 100);
		bottom.setBackground(Color.lightGray);
		round =new JLabel();
		round.setText("Round: 0");
		currentPlayer=new JLabel();
		pass=new JButton("Pass");
		pass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Game.NextRound();
				frame.requestFocus();
			}
		});
		showScore=new JButton("Score");
		showScore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				score.showScore();
				frame.requestFocus();
			}
		});
		bottom.add(round);
		bottom.add(currentPlayer);
		bottom.add(pass);
		bottom.add(showScore);
		frame.add(bottom);
		frame.requestFocus();
	}
	public void setWarehouse(Warehouse w) {
		warehouse=w;
	}
	public void setRound(int x) {
		round.setText("Round: "+x);
	}
	public void setCurrentPlayer(String name) {
		currentPlayer.setText("Current player:	"+name);
	}
	public void setScoreBoard(ArrayList<Worker> workers) {
		score.setData(workers);
	}
	public void addField(Field f,String image_name) {
		if(image_name.compareTo("Hole_closed.png")==0||image_name.compareTo("Hole_open.png")==0) {
			fields.add(new MyHoleJLabel(f,image_name));
		}else {
			fields.add(new MyFieldJLabel(f,image_name));
		}	
	}
	public void addWorker(Worker w) {
		workers.add(new MyWorkerJLabel(w,this));
	}
	public void addCrate(Crate c) {
		crates.add(new MyCrateJLabel(c,this));
	}
	public void locateWorkers() {
		int x=0,y=0;
		for(MyWorkerJLabel worker:workers) {
			if(worker.getHasBeenDrawn()==false) {
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
				worker.setHasBeenDrawn(true);
			}else {
				for(MyFieldJLabel jl:fields) {
					if(jl.getField()==worker.getWorker().GetField()) {
						x=jl.getPositionX();
						y=jl.getPositionY();
						System.out.println(x+" , "+y);
					}
				}
				worker.setBounds(x, y, 50, 50);
				worker.repaint();
			}
		}
	}
	public void locateCrates() {
		int x=0,y=0;
		for(MyCrateJLabel crate:crates) {
			if(crate.getHasBeenDrawn()==false) {
				for(MyFieldJLabel jl:fields) {
					if(jl.getField()==crate.getCrate().GetField()) {
						x=jl.getPositionX();
						y=jl.getPositionY();
						System.out.println(x+" , "+y);
					}
				}
				crate.setVisible(true);
				crate.setEnabled(true);
				crate.setBounds(x, y, 50, 50);
				frame.add(crate);
				frame.setComponentZOrder(crate, 0);
				crate.repaint();
				crate.setHasBeenDrawn(true);
			}else {
				for(MyFieldJLabel jl:fields) {
					if(jl.getField()==crate.getCrate().GetField()) {
						x=jl.getPositionX();
						y=jl.getPositionY();
						System.out.println(x+" , "+y);
					}
				}
				crate.setBounds(x, y, 50, 50);
				crate.repaint();
			}
		}
	}
	public void redrawMap() {
		for(MyWorkerJLabel worker:workers) {
			worker.redraw();
		}
		for(MyCrateJLabel crate:crates) {
			crate.redraw();
		}
	}
	public void buildMap() {
		if(fields.size()>0) {
			drawField(fields.get(0).getField(),150,150);
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
	public void removeWorker(Worker w) {
		for(int i=0;i<workers.size();i++) {
			if(workers.get(i).getWorker().GetName()==w.getName()) {
				workers.get(i).setVisible(false);
				workers.remove(workers.get(i));
			}
		}
	}
	public void removeCrate(Crate c) {
		for(int i=0;i<crates.size();i++) {
			if(crates.get(i).getCrate().getName()==c.getName()) {
				crates.get(i).setVisible(false);
				crates.remove(crates.get(i));
			}
		}
	}

}
