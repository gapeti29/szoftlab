package sokoban;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class ScoreBoard{ 
	ArrayList<Worker> data;
	public ScoreBoard() {
		data=null;
	}
	public void setData(ArrayList<Worker> workers) {
		data=workers;
	}
	public void showScore() {
		if(data!=null) {
			JFrame frame =new JFrame();
			frame.setTitle("ScoreBoard");
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			frame.setVisible(true);
			frame.setSize(300, 500);
			String[] column= {"Name","Points"};
			JTable table = new JTable(new DefaultTableModel(column,1));
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			for(int i=0;i<data.size();i++)
				model.addRow(new Object[] {data.get(i).GetName(),data.get(i).GetPoints()});
			table.setSize(300,500);
			table.setVisible(true);
			frame.add(table);
		}
	}
}
