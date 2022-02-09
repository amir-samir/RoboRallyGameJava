import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class OurTimer {

    boolean necessary = true;

    public OurTimer(int dauer, Game game){
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        Runnable runnable = new Runnable() {
            int countdown = dauer;
            @Override
            public void run() {
                countdown = countdown - 5;
                if (countdown < 0 && necessary){
                    game.timerEnded();
                }
                scheduler.shutdown();
            }
        };
        scheduler.scheduleAtFixedRate(runnable, 0, 5, TimeUnit.SECONDS);
    }

    public void setNecessary(boolean necessary) {
        this.necessary = necessary;
    }

    public boolean isNecessary() {
        return necessary;
    }
}
