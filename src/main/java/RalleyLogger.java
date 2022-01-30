import java.util.logging.*;

public class RalleyLogger {

    private static Logger logger;

    public RalleyLogger(){
        logger = Logger.getLogger("");
        logger.setLevel(Level.ALL);
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
        ralleyLogger.getLogger().warning("Test Warnung");
    }
}
