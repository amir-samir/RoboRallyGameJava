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
                    //scheduler.shutdown();
                }
            }
        };
        scheduler.scheduleAtFixedRate(runnable, 0, 5, TimeUnit.SECONDS);
    }
}
