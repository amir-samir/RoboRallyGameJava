import javafx.stage.Stage;

public class StageSaver {
    private static StageSaver stageSaver = new StageSaver(new Stage());
    private Stage currentStage;

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
}
