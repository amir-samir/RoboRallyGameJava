import javafx.beans.property.BooleanProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.stage.Stage;

public class ChooseRobotViewModel {

    private BooleanProperty hulkBot = new SimpleBooleanProperty();
    private BooleanProperty smashBot = new SimpleBooleanProperty();
    private BooleanProperty spinBot = new SimpleBooleanProperty();
    private BooleanProperty zoomBot = new SimpleBooleanProperty();
    private BooleanProperty twinkyBot = new SimpleBooleanProperty();
    private BooleanProperty hammerBot = new SimpleBooleanProperty();

    public BooleanProperty getHulkBot(){
        return hulkBot;
    }
    public BooleanProperty getSmashBot(){
        return smashBot;
    }
    public BooleanProperty getSpinBot(){
        return spinBot;
    }
    public BooleanProperty getZoomBot(){
        return zoomBot;
    }
    public BooleanProperty getTwinkyBot(){
        return twinkyBot;
    }
    public Property<Boolean> getHammerBot(){
        return hammerBot;
    }

    private Client client;
    private Stage stage;


    public void setClientandStage(Client client, Stage stage) {
        this.client = client;
        this.stage = stage;
        //this.clientThread = new Thread(client);
        //clientThread.start();
    }

    public void sendChooesenBot(String bot) {
        client.printMessage(bot);
    }

    public void closeStage(){
        stage.close();
    }

    public Client getClient() {

        return client;
    }
}


