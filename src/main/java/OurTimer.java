import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class OurTimer {
    public OurTimer(int dauer, Game game){
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        Runnable runnable = new Runnable() {
            int countdown = dauer;
            @Override
            public void run() {
                System.out.println(countdown);
                countdown = countdown - 5;
                if (countdown < 0){
                    game.timerEnded();
                    scheduler.shutdown();
                }
            }
        };
        scheduler.scheduleAtFixedRate(runnable, 0, 5, TimeUnit.SECONDS);
    }

    public OurTimer(int dauer){
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        Runnable runnable = new Runnable() {
            int countdown = dauer;
            @Override
            public void run() {
                System.out.println(countdown);
                countdown = countdown - 5;
                if (countdown < 0){
                    System.out.println("Timer Ended");
                    scheduler.shutdown();
                }
            }
        };
        scheduler.scheduleAtFixedRate(runnable, 0, 5, TimeUnit.SECONDS);
    }

    public static void main(String[] args) {
        OurTimer ourTimer = new OurTimer(30);
    }

}
