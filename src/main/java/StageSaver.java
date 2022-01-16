import javafx.stage.Stage;

public class StageSaver {
    private static StageSaver stageSaver = new StageSaver(new Stage());
    private Stage currentStage;
    private Stage chooseCardStage;
    private Stage chatViewStage;

    private StageSaver(Stage firstStage) {
        this.currentStage = firstStage;
    }

    public static StageSaver getStageSaver(){
        return stageSaver;
    }

    public void setStageSaver(Stage firstStage){
        this.currentStage = firstStage;
    }
    public Stage getCurrentStage(){
        return currentStage;
    }
    public Stage getChooseCardStage(){
        return chooseCardStage;
    }
    public void setChooseCardStage(Stage chooseCardStage1){
        this.chooseCardStage = chooseCardStage1;
    }
    public void setChatViewStage(Stage chatViewStage1){
        this.chatViewStage = chatViewStage1;
    }
    public Stage getChatViewStage(){
        return chatViewStage;
    }
}
