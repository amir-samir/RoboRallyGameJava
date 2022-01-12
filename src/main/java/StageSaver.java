import javafx.stage.Stage;

public class StageSaver {
    private static StageSaver stageSaver = new StageSaver(new Stage());
    private Stage currentStage;
    private Stage chooseCardStage;

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
}
