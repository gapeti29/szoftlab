import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;

public class MyTimer {
	int secondPassed=0;
	Timer myTimer=new Timer();
	JLabel jl;
	public MyTimer(JLabel arg) {
		jl=arg;
	}
	public MyTimer() {
		
	}
	
	TimerTask task=new TimerTask(){
		public void run() {
			secondPassed++;
			jl.setText(String.valueOf(secondPassed));
		}
	};
	public int getTime() {
		return secondPassed;
	}
	public void start() {
		myTimer.scheduleAtFixedRate(task, 1000, 1000);
	}
}
