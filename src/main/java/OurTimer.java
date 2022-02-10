import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Diese Klasse stellt den Timer dar, der, sobald ein Spieler alle Register befüllt hat, 30 Sekunden dauert.
 *
 * @author Luca Weyhofen
 *
 * @version 2.1
 */
public class OurTimer {

    boolean necessary = true;

    /**
     * Dies ist der Konstruktor
     * @param dauer Anzahl der Sekunden, die der Timer laufen soll
     * @param game Das Spiel, bei dem der Timer abläuft
     */
    public OurTimer(int dauer, Game game){
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        Runnable runnable = new Runnable() {
            int countdown = dauer;
            @Override
            public void run() {
                countdown = countdown - 5;
                if (countdown < 0 && necessary){
                    game.timerEnded();
                    scheduler.shutdown();
                } else if (countdown < 0) {
                    scheduler.shutdown();
                }
            }
        };
        scheduler.scheduleAtFixedRate(runnable, 0, 5, TimeUnit.SECONDS);
    }

    public void setNecessary(boolean necessary) {
        this.necessary = necessary;
    }
}
