package sudoku;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class cikisZamanlayicisi 
//eðer programa hatalý veri girilirse program sonsuz döngüye giriyor 
//bu fonksiyonda kurtarýcý
{
	public cikisZamanlayicisi()
	{
		//baslat();
	}
	
	public void baslat()
	{
		int saniye = 6;
		timer.schedule(timertask,new Date(System.currentTimeMillis()+saniye*1000)); // 6 saniye sonra bum
	}
	
		Timer timer = new Timer();
		TimerTask timertask = new TimerTask() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		};
}
