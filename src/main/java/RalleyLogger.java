import java.util.logging.*;

/**
 * Diese Klasse stellt den Logger speziell für das Spiel RoboRally dar.
 *
 * @author Yilu Ye
 * @author Luca Weyhofen
 *
 * @version 2.1
 */
public class RalleyLogger {

    private static Logger logger = Logger.getLogger("");;

    public RalleyLogger(){
        logger.setLevel(Level.INFO);
        FileHandler txt = null;
        try {
            txt = new FileHandler("log.txt");
        } catch (Exception e){
            e.printStackTrace();
        }
        txt.setFormatter(new Formatter() {
            @Override
            public String format(LogRecord record) {
                String ret = "";
                ret += record.getLevel() + ": ";
                ret += this.formatMessage(record);
                ret += "\r\n";
                return ret;
            }
        });
        logger.addHandler(txt);
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void main(String[] args) {
        RalleyLogger ralleyLogger = new RalleyLogger();
        ralleyLogger.getLogger().info("Test Info");
    }
}
