package AutomationTestSystem.Util;

import java.util.Timer;
import java.util.TimerTask;

public class TimerTest {
    Timer timer;
//    public TimerTest(int time){
//        timer  = new Timer();
//        timer.schedule(new timerTaskTest(),time*1000);//timer.schedule(执行的方法，延迟多久执行(ms))
//    }
//
//    class timerTaskTest extends TimerTask{
//        @Override
//        public void run() {
//            System.out.println("time's up!!");
//        }
//    }

    public TimerTest(int time){
        timer  = new Timer();
        timer.schedule(new TimerTask(){
        	@Override
            public void run() {
                System.out.println("time's up!!");
            }
        },time*1000);//timer.schedule(执行的方法，延迟多久执行(ms))
    }

    public static void main(String[] args) {
        System.out.println("timer begin...");
        new TimerTest(3);
    }
}
