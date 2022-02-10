import javafx.stage.Stage;

public class StageSaver {
    private static StageSaver stageSaver = new StageSaver(new Stage());
    private Stage currentStage;
    private Stage chooseCardStage;
    private Stage chatViewStage;
    private Stage upgradeCardsStage;
    private Stage upgradeCardsForSwap;
    private Stage allInOneStage;

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
    public void setUpgradeCardsStage(Stage upgradeCardsStage1){
        this.upgradeCardsStage = upgradeCardsStage1;
    }
    public void setUpgradeCardsStageForSwap(Stage upgradeCardsStage1){
        this.upgradeCardsForSwap = upgradeCardsStage1;
    }
    public Stage getUpgradeCardsForSwap(){
        return this.upgradeCardsForSwap;
    }
    public Stage getChatViewStage(){
        return chatViewStage;
    }

    public Stage getUpgradeCardsStage(){
        return upgradeCardsStage;
    }

    public void setAllInOneStage(Stage allInOneStage1) {
        this.allInOneStage = allInOneStage1;
    }

    public Stage getAllInOneStage() {
        return allInOneStage;
    }
}
