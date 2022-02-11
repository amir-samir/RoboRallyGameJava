import com.sun.javafx.collections.MappingChange;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class AllInOneView implements Initializable {
    ArrayList<BoardElement>[][] mapGui = SaveClients.client.getMap();
    Boolean[] isFilled = new Boolean[5];
    int[] fromChooseCard = new int[6];
    int chooseCardIsThis;
    List<HashMap<String,Object>> laserList = new ArrayList<HashMap<String,Object>>();
    List wallList = new ArrayList<>();
    Boolean[] upgradeCardsFilled = new Boolean[3];
    HashMap<Integer, Integer[]> checkPointMap = new HashMap<Integer, Integer[]>();
    String bildForRegisterCard =  "";
    private static final Integer BeginTimerSeconds = 30;
    private Integer currentSeconds = BeginTimerSeconds;



    Boolean adminPrivaPressed = false;
    int RegisterPlatz = -1;
    @FXML
    Label timerLabel;
    int time = 30;
    @FXML
    ImageView chooseCard1;
    @FXML
    ImageView chooseCard2;
    @FXML
    ImageView chooseCard3;
    @FXML
    ImageView chooseCard4;
    @FXML
    ImageView chooseCard5;
    @FXML
    ImageView chooseCard6;
    @FXML
    ImageView chooseCard7;
    @FXML
    ImageView chooseCard8;
    @FXML
    ImageView chooseCard9;
    @FXML
    Label passivCardLabel;
    @FXML
    Label temporCardsLabel;
    @FXML
    ImageView UpgradeCard1;
    @FXML
    ImageView UpgradeCard2;
    @FXML
    ImageView UpgradeCard3;
    @FXML
    ImageView UpgradeCard4;
    @FXML
    Label CubesText;
    @FXML
    ImageView DirectionOben;
    @FXML
    ImageView DirectionRechts;
    @FXML
    ImageView DirectionUnten;
    @FXML
    ImageView DirectionLinks;
    @FXML
    ImageView FigureChat;
    @FXML
    TextField privateMsgInput;
    @FXML
    ComboBox PrivateMessage;
    //@FXML
   // private ListView chatBox;
    @FXML
    TextField writeField;
    @FXML
    AnchorPane allInOnePane;
    @FXML
    ScrollPane chatPane;
    @FXML
    SplitPane SplitPaneee;

    @FXML
    private GridPane gridpaneDefault;
    @FXML
    private GridPane gridpane1;
    @FXML
    private ImageView Card1;
    @FXML
    private ImageView Card2;
    @FXML
    private ImageView Card3;
    @FXML
    private ImageView Card4;
    @FXML
    private ImageView Card5;

    Image image0 = new Image("assets/GrünFeld.png");
    Image image1 = new Image("assets/Default51x51.png");
    Image image2 = new Image("assets/1rechts.png");
    Image image3 = new Image("assets/ExtraCrispyMap00.png");
    Image image4 = new Image("assets/2nachUnten.png");
    Image image5 = new Image("assets/GeneratorOff.png");
    Image image6 = new Image("assets/1LaserWandLinks.png");
    Image image7 = new Image("assets/LaserfeldHorizontal.png");
    Image image8 = new Image("assets/1LaserWandRechts.png");
    Image image9 = new Image("assets/Rädchen.png");
    Image image10 = new Image("assets/1AbbiegungLinksUnten.png");
    Image image11 = new Image("assets/1Links.png");
    Image image12 = new Image("assets/WandOben.png");
    Image image13 = new Image("assets/Ziel4.png");
    Image image14 = new Image("assets/Pit.png");
    Image image15 = new Image("assets/Ziel1.png");
    Image image16 = new Image("assets/2rechtsoben.png");
    Image image17 = new Image("assets/laserdurch2feld.png");
    Image image18 = new Image("assets/laserdurch2feldlinks.png");
    Image image19 = new Image("assets/2rechtslinks.png");
    Image image20 = new Image("assets/antenne.png");
    Image image21 = new Image("assets/WandRechts.png");
    Image image22 = new Image("assets/ExtraCrispyMap40.png");
    Image image23 = new Image("assets/LaserfeldVertikal.png");
    Image image24 = new Image("assets/RädchenMitUhrzeiger.png");
    Image image25 = new Image("assets/laserdurch2feldlinks.png");
    Image image26 = new Image("assets/WandUnten.png");
    Image image27 = new Image("assets/WandLinks.png");
    Image image28 = new Image("assets/RädchenGegenUnhrzeiger.png");
    Image image29 = new Image("assets/2rechts.png");
    Image image30 = new Image("assets/2AbbiegunLinks.png");
    Image image31 = new Image("assets/2nachOben.png");
    Image image32 = new Image("assets/Ziel2.png");
    Image image33 = new Image("assets/Ziel3.png");
    Image image34 = new Image("assets/2AbbiegunLinksOben.png");
    Image image35 = new Image("assets/ExtraCrispyMapStartBoardA.png");
    Image image36 = new Image("assets/1Unten.png");
    Image image37 = new Image("assets/1Oben.png");
    Image image38 = new Image("assets/1AbbiegungRechts.png");
    Image image39 = new Image("assets/1AbbiegungRechtsOben.png");
    Image image40 = new Image("assets/generatorOffgedreht.png");
    Image image41 = new Image("assets/1LaserWandLinks.png");
    Image image42 = new Image("assets/1LaserWandRechts.png");
    Image image43 = new Image("assets/1AbbiegungRechtsvonLinks.png");
    Image image44 = new Image("assets/1AbbiegungRechtsRechts.png");
    Image image45 = new Image("assets/ExtraCrispyMapStartBoardA.png");
    Image image46 = new Image("assets/LostBearingMapA1.png");
    Image image47 = new Image("assets/1AbbiegungLinks.png");
    Image image48 = new Image("assets/DeathTrapMapFlag5.png");
    Image image49 = new Image("assets/bumpOben.png");
    Image image50 = new Image("assets/WandUnten.png");
    Image image51 = new Image("assets/bumpLinks.png");
    Image image52 = new Image("assets/GeneratorOff.png");
    Image image53 = new Image("assets/generatorOffgedreht.png");
    Image image54 = new Image("assets/Ziel4.png");
    Image image55 = new Image("assets/GeneratorOff.png");
    Image image56 = new Image("assets/bumpUnten1.png");
    Image image57 = new Image("assets/WandLinks.png");
    Image image58 = new Image("assets/1AbbiegungLinksOben.png");
    Image image59 = new Image("assets/1AbbiegungLinksUnten.png");
    Image image60 = new Image("assets/1AbbiegungRechtsLinks.png");
    Image image61 = new Image("assets/Ziel1.png");
    Image image62 = new Image("assets/Ziel3.png");
    Image image63 = new Image("assets/DeathTrapMap2A.png");
    Image image64 = new Image("assets/1AbbiegungLinksRechts.png");
    Image image65 = new Image("assets/Ziel2.png");
    Image image66 = new Image("assets/bumpRechts.png");
    Image image67 = new Image("assets/2nachUnten.png");
    Image image68 = new Image("assets/GeneratorOff.png");
    Image image69 = new Image("assets/2EinleitungRechts.png");
    Image image70 = new Image("assets/2EinleitungOben.png");
    Image image71 = new Image("assets/2nachLinks.png");
    Image image72 = new Image("assets/2EinleitungUnten.png");
    Image image73 = new Image("assets/2EinleitungRechts.png");
    Image image74 = new Image("assets/1LaserWandOben.png");
    Image image75 = new Image("assets/Runtergreen.png");
    Image image76 = new Image("assets/1LaserWandLinks.png");
    Image image77 = new Image("assets/1LaserWandRechts.png");
    Image image78 = new Image("assets/2nachOben.png");
    Image image79 = new Image("assets/Ziel1.png");
    Image image80 = new Image("assets/1LaserWandUnten.png");
    Image image81 = new Image("assets/1LaserWandOben.png");
    Image image82 = new Image("assets/1LaserWandendeLinks.png");
    Image image83 = new Image("assets/1LaserWandRechts.png");
    Image image84 = new Image("assets/1LaserWandendeUnten.png");
    Image image85 = new Image("assets/2nachrechts.png");
    Image image86 = new Image("assets/2EinleitungLinks.png");
    Image image87 = new Image("assets/2EinleitungOben.png");
    Image image88 = new Image("assets/2EinleitungUnten.png");
    Image image89 = new Image("assets/2EinleitungLinks.png");
    Image image90 = new Image("assets/DizzyHighwayMap99.png");
    Image image91 = new Image("assets/GrünFeldLinks.png");
    Image figureTest = new Image("assets/GelbUnten.png");
    Image image92 = new Image("assets/1LaserEndeWandUnten36.png");
    Image image93 = new Image("assets/1LaserEndeWandRechts38.png");
    Image image94 = new Image("assets/2EinleitungObennachlinks18.png");
    Image image95 = new Image("assets/2EinleitungvonRechtsnachoben211.png");
    Image image96 = new Image("assets/2EinleitungUntennachrechts810.png");
    Image image97 = new Image("assets/2EinleitungLinksnachunten74.png");
    Image image98 = new Image("assets/Runtergreen.png");
    Image image99 = new Image("assets/kommischerechtsunten.png");
    Image image100 = new Image("assets/energyStationWallBottom.png");
    Image image101 = new Image("assets/energyStationWandOben.png");




    // Upgrade Cards
    Image AdminPrivilege = new Image("assets/AdminPrivilege.png");
    Image RearLaser = new Image("assets/RearLaser.png");
    Image MemorySwap = new Image("assets/MemorySwap.png");
    Image SpamBlocker = new Image("assets/SpamBlocker.png");

    //Karten
    Image Karte = new Image("assets/Karte.png");
    @FXML
    Image Move1 = new Image("assets/Move1.png");
    Image Move2 = new Image("assets/move2Blau.png");
    Image Move3 = new Image("assets/Move3Blau.png");
    Image PowerUp = new Image("assets/PowerUpBlau.png");
    Image RightTurn = new Image("assets/RightTurnBlau.png");
    Image UTurn = new Image("assets/UTurnBlau.png");
    Image LeftTurn = new Image ("assets/leftTurnBlau.png");
    Image BackUp = new Image("assets/MoveBack.png");
    Image Again = new Image("assets/Again.png");

    // Figuren
    //Hammer
    Image HammerOben = new Image("assets/HammerOben.png");
    Image HammerRechts = new Image("assets/HammerRechts.png");
    Image HammerLinks = new Image("assets/HammerLinks.png");
    Image HammerUnten = new Image("assets/HammerUnten.png");
    //Twinky
    Image TwinkyOben = new Image("assets/TwinkyOben.png");
    Image TwinkyRechts = new Image("assets/TwinkyRechts.png");
    Image TwinkyLinks = new Image("assets/TwinkyLinks.png");
    Image TwinkyUnten = new Image("assets/TwinkyUnten.png");
    //Zoom
    Image ZoomOben = new Image("assets/ZoomOben.png");
    Image ZoomRechts = new Image("assets/ZoomRechts.png");
    Image ZoomLinks = new Image("assets/ZoomLinks.png");
    Image ZoomUnten = new Image("assets/ZoomUnten.png");
    //Smash
    Image SmashOben = new Image("assets/SmashOben.png");
    Image SmashRechts = new Image("assets/SmadhRechts.png");
    Image SmashLinks = new Image("assets/SmashLinks.png");
    Image SmashUnten = new Image("assets/SmashUnten.png");
    //Spin
    Image SpinOben = new Image("assets/SpinOben.png");
    Image SpinRechts = new Image("assets/SpinRechts.png");
    Image SpinLinks = new Image("assets/SpinLinks.png");
    Image SpinUnten = new Image("assets/SpinUnten.png");
    //Hulk
    Image HulkOben = new Image("assets/HulkOben.png");
    Image HulkRechts = new Image("assets/HulkRechts.png");
    Image HulkLinks = new Image("assets/HulkLinks.png");
    Image HulkUnten = new Image("assets/HulkUnten.png");

    // Spam Cards
    @FXML
    Image spam = new Image("assets/spam.png");
    Image trojanHorse = new Image("assets/trojanHorse.png");
    Image virus = new Image("assets/virus.png");
    Image worm = new Image("assets/worm.png");

    Image[][] saveMap = new Image[10][13];


    public AllInOneView(){
     Client.setAllInOneView(this);
    }
    public void sendMessage() {

        SaveClients.client.printMessage(writeField.textProperty().get());
        writeField.clear();

    }

    public void getLaserOnMaps() {
        for (int x = 0; x < gridpane1.getRowCount(); x++) {
            for (int y = 0; y < gridpane1.getColumnCount(); y++) {
                if (mapGui[x][y].size() == 2){
                if (Objects.equals(mapGui[x][y].get(0).getType(), "Laser") && Objects.equals(mapGui[x][y].get(1).getType(), "Wall")) {
                    HashMap<String, Object> laserPosition = new HashMap();
                    laserPosition.put("x", x);
                    laserPosition.put("y", y);
                    int counter = 0;


                        /*List<Integer> laserPositon = new ArrayList<>();
                        laserPositon.add(x);
                        laserPositon.add(y);
                        List laserOrientation = new ArrayList<>();
                        laserOrientation.add(laserPositon);*/

                    switch (mapGui[x][y].get(0).getOrientations()[0]) {
                        case "bottom":
                            gridpane1.add(new ImageView(image81), y, x);
                            saveMap[x][y] = image81;
                            counter = 0;
                            while (true) {
                                counter++;
                                if (mapGui[x + counter][y].size() == 0) {
                                    gridpane1.add(new ImageView(image23), y, x + counter);
                                    saveMap[x + counter][y] = image23;
                                }
                                if ((mapGui[x + counter][y].size() != 0) && (mapGui[x + counter][y].get(0).getType().equals("Wall") || (mapGui[x + counter][y].get(1).getType().equals("Wall"))) && (mapGui[x + counter][y].get(0).getOrientations()[0].equals("top") || mapGui[x + counter][y].get(0).getOrientations()[0].equals("bottom"))) {
                                    gridpane1.add(new ImageView(image84), y, x + counter);
                                    saveMap[x + counter][y] = image84;
                                    break;
                                }
                            }
                            laserPosition.put("Orientation", "bottom");
                            break;
                        case "top":
                            gridpane1.add(new ImageView(image80), y, x);
                            saveMap[x][y] = image80;
                            counter = 0;
                            while (true) {
                                counter++;
                                if (mapGui[x - counter][y].size() == 0) {
                                    gridpane1.add(new ImageView(image23), y, x - counter);
                                    saveMap[x - counter][y] = image23;
                                }
                                if ((mapGui[x-counter][y].size() != 0) && (mapGui[x - counter][y].get(0).getType().equals("Wall")  || (mapGui[x - counter][y].get(1).getType().equals("Wall"))) && (mapGui[x - 1][y].get(0).getOrientations()[0].equals("top") || mapGui[x - 1][y].get(0).getOrientations()[0].equals("bottom"))) {
                                    gridpane1.add(new ImageView(image92), y, x - counter);
                                    saveMap[x - counter][y] = image92;
                                    break;
                                }
                            }
                            laserPosition.put("Orientation", "top");
                            break;
                        case "right":
                            gridpane1.add(new ImageView(image41), y, x);
                            saveMap[x][y] = image41;
                            counter = 0;
                            while (true) {
                                counter++;
                                if (mapGui[x][y + counter].size() == 0) {
                                    gridpane1.add(new ImageView(image7), y + counter, x);
                                    saveMap[x][y + counter] = image7;
                                }
                                if ((mapGui[x][y + counter].size() != 0) && ((mapGui[x][y + counter].get(0).getType().equals("Wall") || (mapGui[x][y + counter].get(1).getType().equals("Wall"))) || mapGui[x][y + counter].get(1).getType().equals("Wall"))&& (mapGui[x][y + counter].get(0).getOrientations()[0].equals("right") || mapGui[x][y + counter].get(0).getOrientations()[0].equals("left"))) {
                                   gridpane1.add(new ImageView(image93), y + counter, x);
                                    saveMap[x][y + counter] = image93;
                                    if ((mapGui[x][y + counter].size() > 1) && mapGui[x][y + counter].get(1).getType().equals("Wall")) {
                                        //hii
                                        gridpane1.add(new ImageView(image6), y + counter, x);
                                        saveMap[x][y + counter] = image6;
                                    }
                                    break;
                                }
                            }
                            laserPosition.put("Orientation", "right");
                            break;
                        case "left":
                            gridpane1.add(new ImageView(image8), y, x);
                            saveMap[x][y] = image8;
                            counter = 0;
                            while (true) {
                                counter++;
                                if (mapGui[x][y - counter].size() == 0) {
                                    gridpane1.add(new ImageView(image7), y - counter, x);
                                    saveMap[x][y - counter] = image7;
                                }
                                if ((mapGui[x][y - counter].size() != 0) && ((mapGui[x][y - counter].get(0).getType().equals("Wall")) || (mapGui[x][y - counter].get(1).getType().equals("Wall"))) && (mapGui[x][y - counter].get(0).getOrientations()[0].equals("right") || mapGui[x][y - counter].get(0).getOrientations()[0].equals("left"))) {
                                    gridpane1.add(new ImageView(image82), y - counter, x);
                                    saveMap[x][y - counter] = image82;
                                        if ((mapGui[x][y - counter].size() > 1) && mapGui[x][y - counter].get(1).getType().equals("Wall")) {
                                            //hii
                                            gridpane1.add(new ImageView(image6), y - counter, x);
                                            saveMap[x][y - counter] = image6;
                                            break;
                                }
                                    break;
                                }
                            }
                            laserPosition.put("Orientation", "left");
                            break;
                    }
                    laserList.add(laserPosition);
                }

            }
            if (mapGui[x][y].size() == 3) {
                if (Objects.equals(mapGui[x][y].get(0).getType(), "Laser") && Objects.equals(mapGui[x][y].get(1).getType(), "Wall") && Objects.equals(mapGui[x][y].get(2).getType(), "CheckPoint")) {
                switch (mapGui[x][y].get(0).getOrientations()[0]) {
                    case "bottom":
                        int counter = 0;
                        while (true) {
                            counter++;
                            if (mapGui[x + counter][y].size() == 0) {
                                gridpane1.add(new ImageView(image23), y, x + counter);
                                saveMap[x + counter][y] = image23;
                            }
                            //luuu
                            if (mapGui[x + counter][y].size() != 0 && Objects.equals(mapGui[x + counter][y].get(0).getType(), "ConveyorBelt")) {
                                if (Objects.equals(mapGui[x + counter][y].get(0).getOrientations()[0], "left") && Objects.equals(mapGui[x + counter][y].get(0).getOrientations()[1], "right")) {
                                    gridpane1.add(new ImageView(image18), y, x + counter);
                                    saveMap[x + counter][y] = image18;
                                }

                                if (Objects.equals(mapGui[x + counter][y].get(0).getOrientations()[0], "right") && Objects.equals(mapGui[x + counter][y].get(0).getOrientations()[1], "left")) {
                                    gridpane1.add(new ImageView(image17), y, x + counter);
                                    saveMap[x + counter][y] = image17;
                                }
                            }
                        if (mapGui[x + counter][y].size() == 3 && mapGui[x + counter][y].get(2).getCount() == 3) {
                            gridpane1.add(new ImageView(image13), y, x + counter);
                            saveMap[x + counter][y] = image13;
                            break;
                        }
                        if (mapGui[x + counter][y].size() == 3 && mapGui[x + counter][y].get(2).getCount() == 0){
                            gridpane1.add(new ImageView(image15), y, x + counter);
                            saveMap[x + counter][y] = image15;
                            break;
                        }
                            if (mapGui[x + counter][y].size() == 3 && mapGui[x + counter][y].get(2).getCount() == 1) {
                                gridpane1.add(new ImageView(image32), y, x + counter);
                                saveMap[x + counter][y] = image32;
                                break;
                            }
                            if (mapGui[x + counter][y].size() == 3 && mapGui[x + counter][y].get(2).getCount() == 2){
                                gridpane1.add(new ImageView(image33), y, x + counter);
                                saveMap[x + counter][y] = image33;
                                break;
                            }
                        if (mapGui[x + counter][y].size() == 3) {
                            break;
                        }
                        }
                        break;
                    case "top":
                        counter = 0;
                        while (true) {
                            counter++;
                            if (mapGui[x - counter][y].size() == 0) {
                                gridpane1.add(new ImageView(image23), y, x - counter);
                                saveMap[x - counter][y] = image23;
                            }
                            //luuu
                            if (mapGui[x - counter][y].size() != 0 && Objects.equals(mapGui[x - counter][y].get(0).getType(), "ConveyorBelt")) {
                                gridpane1.add(new ImageView(image18), y, x - counter);
                                if (Objects.equals(mapGui[x - counter][y].get(0).getOrientations()[0], "left") && Objects.equals(mapGui[x - counter][y].get(0).getOrientations()[1], "right")) {
                                    gridpane1.add(new ImageView(image18), y, x - counter);
                                    saveMap[x - counter][y] = image18;
                                }
                                if (Objects.equals(mapGui[x - counter][y].get(0).getOrientations()[0], "right") && Objects.equals(mapGui[x - counter][y].get(0).getOrientations()[1], "left")) {
                                    gridpane1.add(new ImageView(image17), y, x - counter);
                                    saveMap[x - counter][y] = image17;
                                }
                            }
                            if (mapGui[x - counter][y].size() == 3 && mapGui[x -counter][y].get(2).getCount() == 1) {
                                gridpane1.add(new ImageView(image32), y, x - counter);
                                saveMap[x - counter][y] = image32;
                                break;
                            }
                            if (mapGui[x - counter][y].size() == 3 && mapGui[x - counter][y].get(2).getCount() == 2){
                                gridpane1.add(new ImageView(image33), y, x - counter);
                                saveMap[x - counter][y] = image33;
                                break;
                            }
                            if (mapGui[x - counter][y].size() == 3 && mapGui[x - counter][y].get(2).getCount() == 3) {
                                gridpane1.add(new ImageView(image13), y, x - counter);
                                saveMap[x - counter][y] = image13;
                                break;
                            }
                            if (mapGui[x - counter][y].size() == 3 && mapGui[x - counter][y].get(2).getCount() == 0){
                                gridpane1.add(new ImageView(image15), y, x - counter);
                                saveMap[x - counter][y] = image15;
                                break;
                            }
                            if (mapGui[x - counter][y].size() == 3) {
                                break;
                            }}
                        break;
                }

            }}
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setChooseCardUnvisible();
        CubesText.setText("Energy cubes: " + SaveClients.client.getCubesZahl());
        DirectionRechts.setVisible(false);
        DirectionLinks.setVisible(false);
        DirectionOben.setVisible(false);
        DirectionUnten.setVisible(false);
        Arrays.fill(isFilled,false);
        Arrays.fill(upgradeCardsFilled,false);
        generateMap();
        gridpaneDefault = gridpane1;
        Card1.setImage(Karte);
        Card2.setImage(Karte);
        Card3.setImage(Karte);
        Card4.setImage(Karte);
        Card5.setImage(Karte);

        //chatBox = SaveClients.client.getChatView().getChatBox();
        PrivateMessage.setItems(SaveClients.client.usernamesGui);
        //chatPane.setMaxHeight(5.0);
        //chatBox.prefHeight(5.0);
        //chatPane.getChildren().add(SaveClients.client.getChatView().getChatBox());
        chatPane.setContent(SaveClients.client.getChatView().getChatBox());
        gridpane1.setHgap(-80);

    }
    //vorne
    public void moveCheckpoints(int id, int x, int y) {
        checkPointMap.replace(id, new Integer[]{x,y});
    }

    public void updateCubes(){
        CubesText.setText("Energy cubes: " + SaveClients.client.getCubesZahl());
    }

    public void sendPrivateMessage(){
        String selectedUser = PrivateMessage.getValue().toString().split(",")[0];
        String msg = privateMsgInput.getText();

        SaveClients.client.singleMessage(SaveClients.client.getID(), msg, Integer.parseInt(selectedUser));
        privateMsgInput.clear();
    }

    public void setReady(){
        SaveClients.client.setReady();
    }

    public void generateMap(){

        for (int x = 0; x < gridpane1.getRowCount(); x++){
            for (int y = 0; y < gridpane1.getColumnCount(); y++){
                if (mapGui[x][y].size() == 0){
                    gridpane1.add(new ImageView(image1),y,x);
                    saveMap[x][y] = image1;

                }
                else if (mapGui[x][y].size() == 2){
                    if (Objects.equals(mapGui[x][y].get(0).getType(), "EnergySpace")) {
                        // Muss evtl. noch weiter angepasst werden
                        gridpane1.add(new ImageView(image52),y,x);
                        saveMap[x][y] = image52;
                        //hiiiiii
                        if (mapGui[x][y].size() == 2 && Objects.equals(mapGui[x][y].get(1).getType(), "Wall")){
                            gridpane1.add(new ImageView(image22),y,x);
                            saveMap[x][y] = image22;
                        }
                    }
                    //laba
                    if (Objects.equals(mapGui[x][y].get(1).getType(), "ConveyorBelt")) {
                        // Muss evtl. noch weiter angepasst werden
                        if (Objects.equals(mapGui[x][y].get(1).getOrientations()[0], "left") && Objects.equals(mapGui[x][y].get(1).getOrientations()[1], "right")) {
                            if (mapGui[x][y].get(0).getSpeed() == 1) {
                                gridpane1.add(new ImageView(image11), y, x);
                                saveMap[x][y] = image11;
                            } else {
                                gridpane1.add(new ImageView(image62), y, x);
                                saveMap[x][y] = image71;
                                //sasa
                                Integer[] check3 = new Integer[]{x,y};
                                checkPointMap.put(2, check3);
                            }
                        }
                        if (Objects.equals(mapGui[x][y].get(1).getOrientations()[0], "top") && Objects.equals(mapGui[x][y].get(1).getOrientations()[1], "bottom")) {
                            if (mapGui[x][y].get(0).getSpeed() == 1) {
                                gridpane1.add(new ImageView(image37), y, x);
                                saveMap[x][y] = image37;
                            } else {
                                gridpane1.add(new ImageView(image54), y, x);
                                saveMap[x][y] = image31;
                                Integer[] check2 = new Integer[]{x,y};
                                checkPointMap.put(1, check2);
                            }
                        }
                        if (Objects.equals(mapGui[x][y].get(1).getOrientations()[0], "right") && Objects.equals(mapGui[x][y].get(1).getOrientations()[1], "left")) {
                            if (mapGui[x][y].get(0).getSpeed() == 1) {
                                gridpane1.add(new ImageView(image2), y, x);
                                saveMap[x][y] = image2;
                            } else {
                                gridpane1.add(new ImageView(image61), y, x);
                                saveMap[x][y] = image85;
                                Integer[] check1 = new Integer[]{x,y};
                                checkPointMap.put(0, check1);
                            }
                        }
                        if (Objects.equals(mapGui[x][y].get(1).getOrientations()[0], "bottom") && Objects.equals(mapGui[x][y].get(1).getOrientations()[1], "top")) {
                            if (mapGui[x][y].get(0).getSpeed() == 1) {
                                gridpane1.add(new ImageView(image36), y, x);
                                saveMap[x][y] = image36;
                            } else {
                                gridpane1.add(new ImageView(image65), y, x);
                                saveMap[x][y] = image4;
                                Integer[] check4 = new Integer[]{x,y};
                                checkPointMap.put(3, check4);
                            }
                        }

                    }
                    if (Objects.equals(mapGui[x][y].get(0).getType(), "PushPanel")){
                       switch (mapGui[x][y].get(0).getOrientations()[0]) {
                           case "bottom":
                               gridpane1.add(new ImageView(image49),y,x);
                               saveMap[x][y] = image49;
                               break;
                           case "top":
                               gridpane1.add(new ImageView(image56),y,x);
                               saveMap[x][y] = image56;
                               break;
                           case "right":
                               gridpane1.add(new ImageView(image51),y,x);
                               saveMap[x][y] = image51;
                               break;
                           case "left":
                               gridpane1.add(new ImageView(image66),y,x);
                               saveMap[x][y] = image66;
                               break;

                       }

                    }
                }
                else{
                    if (Objects.equals(mapGui[x][y].get(0).getType(), "Wall")) {
                        switch (mapGui[x][y].get(0).getOrientations()[0]) {
                            case "bottom":
                                gridpane1.add(new ImageView(image50),y,x);
                                saveMap[x][y] = image50;
                                break;
                            case "top":
                                gridpane1.add(new ImageView(image12),y,x);
                                saveMap[x][y] = image12;
                                break;
                            case "right":
                                gridpane1.add(new ImageView(image21),y,x);
                                saveMap[x][y] = image21;
                                break;
                            case "left":
                                gridpane1.add(new ImageView(image57),y,x);
                                saveMap[x][y] = image57;
                                break;

                        }

                    }
                    if (Objects.equals(mapGui[x][y].get(0).getType(), "Gear")) {
                        if (mapGui[x][y].get(0).getOrientations()[0].equals("clockwise")) {
                            gridpane1.add(new ImageView(image24), y, x);
                            saveMap[x][y] = image24;
                        }
                        else {
                            gridpane1.add(new ImageView(image28), y, x);
                            saveMap[x][y] = image28;
                        }
                    }
                    if (Objects.equals(mapGui[x][y].get(0).getType(), "Antenna")) {
                        // Muss evtl. noch weiter angepasst werden
                        gridpane1.add(new ImageView(image20),y,x);
                        saveMap[x][y] = image20;
                    }
                    if (Objects.equals(mapGui[x][y].get(0).getType(), "EnergySpace")) {
                        // Muss evtl. noch weiter angepasst werden
                        gridpane1.add(new ImageView(image52),y,x);
                        saveMap[x][y] = image52;
                        //hiiiiii
                        if (mapGui[x][y].size() == 2 && Objects.equals(mapGui[x][y].get(1).getType(), "Wall")){
                            gridpane1.add(new ImageView(image22),y,x);
                            saveMap[x][y] = image22;
                        }
                    }
                    if (Objects.equals(mapGui[x][y].get(0).getType(), "StartPoint")) {
                        gridpane1.add(new ImageView(image9),y,x);
                        saveMap[x][y] = image9;
                    }
                    if (Objects.equals(mapGui[x][y].get(0).getType(), "Pit")) {
                        gridpane1.add(new ImageView(image14),y,x);
                        saveMap[x][y] = image14;
                    }
                    if (Objects.equals(mapGui[x][y].get(0).getType(), "RestartPoint")) {
                        if (Objects.equals(mapGui[x][y].get(0).getIsOnBoard(), "DeathTrap")) {
                            gridpane1.add(new ImageView(image91),y,x);
                            saveMap[x][y] = image91;
                        }
                        if (Objects.equals(mapGui[x][y].get(0).getIsOnBoard(), "Twister")) {
                            gridpane1.add(new ImageView(image0),y,x);
                            saveMap[x][y] = image0;
                        }
                        if (Objects.equals(mapGui[x][y].get(0).getIsOnBoard(), "DizzyHighway")) {
                            gridpane1.add(new ImageView(image98),y,x);
                            saveMap[x][y] = image98;
                        }
                        if (Objects.equals(mapGui[x][y].get(0).getIsOnBoard(), "ExtraCrispy")) {
                            gridpane1.add(new ImageView(image0),y,x);
                            saveMap[x][y] = image0;
                        }
                        if (Objects.equals(mapGui[x][y].get(0).getIsOnBoard(), "LostBearings")) {
                            gridpane1.add(new ImageView(image0),y,x);
                            saveMap[x][y] = image0;
                        }
                    }
                    if (Objects.equals(mapGui[x][y].get(0).getType(), "ConveyorBelt")){
                        if (mapGui[x][y].get(0).getOrientations().length == 3){
                            if ((Objects.equals(mapGui[x][y].get(0).getOrientations()[0], "left" )) && (Objects.equals(mapGui[x][y].get(0).getOrientations()[1], "top")) && (Objects.equals(mapGui[x][y].get(0).getOrientations()[2], "right" ))){
                                gridpane1.add(new ImageView(image94),y,x);
                                saveMap[x][y] = image94;
                                //falsches Bild [1][5] (DizzyHighway)
                            }
                            if ((Objects.equals(mapGui[x][y].get(0).getOrientations()[0], "bottom" )) && (Objects.equals(mapGui[x][y].get(0).getOrientations()[1], "top")) && (Objects.equals(mapGui[x][y].get(0).getOrientations()[2], "right" ))){
                                gridpane1.add(new ImageView(image69),y,x);
                                saveMap[x][y] = image69;
                            }
                            if ((Objects.equals(mapGui[x][y].get(0).getOrientations()[0], "left" )) && (Objects.equals(mapGui[x][y].get(0).getOrientations()[1], "right")) && (Objects.equals(mapGui[x][y].get(0).getOrientations()[2], "bottom" ))){
                                gridpane1.add(new ImageView(image72),y,x);
                                saveMap[x][y] = image72;
                            }
                            if ((Objects.equals(mapGui[x][y].get(0).getOrientations()[0], "top" )) && (Objects.equals(mapGui[x][y].get(0).getOrientations()[1], "right")) && (Objects.equals(mapGui[x][y].get(0).getOrientations()[2], "bottom" ))){
                                gridpane1.add(new ImageView(image95),y,x);
                                saveMap[x][y] = image95;
                                //Falsches Bild [2][11] (DizzyHighway)
                            }
                            if ((Objects.equals(mapGui[x][y].get(0).getOrientations()[0], "bottom" )) && (Objects.equals(mapGui[x][y].get(0).getOrientations()[1], "left")) && (Objects.equals(mapGui[x][y].get(0).getOrientations()[2], "top" ))){
                                gridpane1.add(new ImageView(image97),y,x);
                                saveMap[x][y] = image97;
                                //Falsches Bild[7][4] (DizzyHighway)
                            }
                            if ((Objects.equals(mapGui[x][y].get(0).getOrientations()[0], "right" )) && (Objects.equals(mapGui[x][y].get(0).getOrientations()[1], "left")) && (Objects.equals(mapGui[x][y].get(0).getOrientations()[2], "top" ))){
                                gridpane1.add(new ImageView(image87),y,x);
                                saveMap[x][y] = image87;
                            }
                            if ((Objects.equals(mapGui[x][y].get(0).getOrientations()[0], "right" )) && (Objects.equals(mapGui[x][y].get(0).getOrientations()[1], "bottom")) && (Objects.equals(mapGui[x][y].get(0).getOrientations()[2], "left" ))){
                                gridpane1.add(new ImageView(image96),y,x);
                                saveMap[x][y] = image96;
                                //Falsches Bild [8][10] (DizzyHighway)
                            }
                            if ((Objects.equals(mapGui[x][y].get(0).getOrientations()[0], "top" )) && (Objects.equals(mapGui[x][y].get(0).getOrientations()[1], "bottom")) && (Objects.equals(mapGui[x][y].get(0).getOrientations()[2], "left" ))){
                                gridpane1.add(new ImageView(image89),y,x);
                                saveMap[x][y] = image89;
                            }
                        }
                        else {
                            if (Objects.equals(mapGui[x][y].get(0).getOrientations()[0], "top") && Objects.equals(mapGui[x][y].get(0).getOrientations()[1], "right")) {
                                if (mapGui[x][y].get(0).getSpeed() == 1) {
                                    gridpane1.add(new ImageView(image39), y, x);
                                    saveMap[x][y] = image39;
                                } else {
                                    gridpane1.add(new ImageView(image16), y, x);
                                    saveMap[x][y] = image16;
                                }
                            }
                            if (Objects.equals(mapGui[x][y].get(0).getOrientations()[0], "right") && Objects.equals(mapGui[x][y].get(0).getOrientations()[1], "bottom")) {
                                if (mapGui[x][y].get(0).getSpeed() == 1) {
                                    gridpane1.add(new ImageView(image44), y, x);
                                    saveMap[x][y] = image44;
                                } else {
                                    gridpane1.add(new ImageView(image29), y, x);
                                    saveMap[x][y] = image29;
                                }
                            }
                            if (Objects.equals(mapGui[x][y].get(0).getOrientations()[0], "right") && Objects.equals(mapGui[x][y].get(0).getOrientations()[1], "left")) {
                                if (mapGui[x][y].get(0).getSpeed() == 1) {
                                    gridpane1.add(new ImageView(image2), y, x);
                                    saveMap[x][y] = image2;
                                } else {
                                    gridpane1.add(new ImageView(image85), y, x);
                                    saveMap[x][y] = image85;
                                }
                            }
                            if (Objects.equals(mapGui[x][y].get(0).getOrientations()[0], "right") && Objects.equals(mapGui[x][y].get(0).getOrientations()[1], "top")) {
                                if (mapGui[x][y].get(0).getSpeed() == 1) {
                                    gridpane1.add(new ImageView(image64), y, x);
                                    saveMap[x][y] = image64;
                                } else {
                                    gridpane1.add(new ImageView(image16), y, x);
                                    saveMap[x][y] = image16;
                                    //momo
                                }
                            }
                            if (Objects.equals(mapGui[x][y].get(0).getOrientations()[0], "bottom") && Objects.equals(mapGui[x][y].get(0).getOrientations()[1], "left")) {
                                if (mapGui[x][y].get(0).getSpeed() == 1) {
                                    gridpane1.add(new ImageView(image43), y, x);
                                    saveMap[x][y] = image43;
                                } else {
                                    // kaaa
                                    gridpane1.add(new ImageView(image30), y, x);
                                    saveMap[x][y] = image30;
                                }
                            }
                            if (Objects.equals(mapGui[x][y].get(0).getOrientations()[0], "bottom") && Objects.equals(mapGui[x][y].get(0).getOrientations()[1], "top")) {
                                if (mapGui[x][y].get(0).getSpeed() == 1) {
                                    gridpane1.add(new ImageView(image36), y, x);
                                    saveMap[x][y] = image36;
                                } else {
                                    gridpane1.add(new ImageView(image4), y, x);
                                    saveMap[x][y] = image4;
                                }
                            }
                            if (Objects.equals(mapGui[x][y].get(0).getOrientations()[0], "bottom") && Objects.equals(mapGui[x][y].get(0).getOrientations()[1], "right")) {
                                if (mapGui[x][y].get(0).getSpeed() == 1) {
                                    gridpane1.add(new ImageView(image59), y, x);
                                    saveMap[x][y] = image59;
                                } else {
                                    // wurde noch nicht bearbeitet, wird wahrscheinlich auch nicht benutzt
                                    gridpane1.add(new ImageView(image4), y, x);
                                    saveMap[x][y] = image4;
                                }
                            }
                            if (Objects.equals(mapGui[x][y].get(0).getOrientations()[0], "top") && Objects.equals(mapGui[x][y].get(0).getOrientations()[1], "bottom")) {
                                if (mapGui[x][y].get(0).getSpeed() == 1) {
                                    gridpane1.add(new ImageView(image37), y, x);
                                    saveMap[x][y] = image37;
                                } else {
                                    gridpane1.add(new ImageView(image31), y, x);
                                    saveMap[x][y] = image31;
                                }
                            }
                            if (Objects.equals(mapGui[x][y].get(0).getOrientations()[0], "top") && Objects.equals(mapGui[x][y].get(0).getOrientations()[1], "left")) {
                                if (mapGui[x][y].get(0).getSpeed() == 1) {
                                    gridpane1.add(new ImageView(image58), y, x);
                                    saveMap[x][y] = image58;
                                } else {
                                    // wurde noch nicht bearbeitet, wird wahrscheinlich auch nicht benutzt
                                    gridpane1.add(new ImageView(image31), y, x);
                                    saveMap[x][y] = image31;
                                }
                            }
                            if (Objects.equals(mapGui[x][y].get(0).getOrientations()[0], "left") && Objects.equals(mapGui[x][y].get(0).getOrientations()[1], "right")) {
                                if (mapGui[x][y].get(0).getSpeed() == 1) {
                                    gridpane1.add(new ImageView(image11), y, x);
                                    saveMap[x][y] = image11;
                                } else {
                                    gridpane1.add(new ImageView(image71), y, x);
                                    saveMap[x][y] = image71;
                                }
                            }
                            if (Objects.equals(mapGui[x][y].get(0).getOrientations()[0], "left") && Objects.equals(mapGui[x][y].get(0).getOrientations()[1], "bottom")) {
                                if (mapGui[x][y].get(0).getSpeed() == 1) {
                                    gridpane1.add(new ImageView(image47), y, x);
                                    saveMap[x][y] = image47;
                                } /*else {
                                //was wenn es blau ist!!!!
                                gridpane1.add(new ImageView(image0),y,x);
                            }*/
                            }
                            if (Objects.equals(mapGui[x][y].get(0).getOrientations()[0], "left") && Objects.equals(mapGui[x][y].get(0).getOrientations()[1], "top")) {
                                if (mapGui[x][y].get(0).getSpeed() == 1) {
                                    gridpane1.add(new ImageView(image60), y, x);
                                    saveMap[x][y] = image60;
                                } else {
                                    gridpane1.add(new ImageView(image19), y, x);
                                    saveMap[x][y] = image19;
                                }
                            }
                        }
                        /*else {
                            gridpane1.add(new ImageView(image0),y,x);
                        }*/

                    }
                    //dudu
                    if (Objects.equals(mapGui[x][y].get(0).getType(), "CheckPoint")){
                        switch (mapGui[x][y].get(0).getCount()){
                            case 0:
                                gridpane1.add(new ImageView(image61),y,x);
                                saveMap[x][y] = image61;
                                Integer[] check1 = new Integer[]{x,y};
                                checkPointMap.put(0, check1);

                                break;
                            case 1:
                                gridpane1.add(new ImageView(image65),y,x);
                                saveMap[x][y] = image65;
                                Integer[] check2 = new Integer[]{x,y};
                                checkPointMap.put(1, check2);
                                break;
                            case 2:
                                gridpane1.add(new ImageView(image62),y,x);
                                saveMap[x][y] = image62;
                                Integer[] check3 = new Integer[]{x,y};
                                checkPointMap.put(2, check3);
                                break;
                            case 3:
                                gridpane1.add(new ImageView(image54),y,x);
                                saveMap[x][y] = image54;
                                Integer[] check4 = new Integer[]{x,y};
                                checkPointMap.put(3, check4);
                                break;
                            case 4:
                                gridpane1.add(new ImageView(image48),y,x);
                                saveMap[x][y] = image48;
                                Integer[] check5 = new Integer[]{x,y};
                                checkPointMap.put(4, check5);
                                break;
                        }
                    }
                }
            }
           getLaserOnMaps();
        }
    }


    public void clickGrid(javafx.scene.input.MouseEvent event) {
        Node clickedNode = event.getPickResult().getIntersectedNode();
        if (clickedNode != gridpane1) {
            // click on descendant node
            Integer colIndex = GridPane.getColumnIndex(clickedNode);
            Integer rowIndex = GridPane.getRowIndex(clickedNode);
            //gridpane1.add(new ImageView(figureTest),colIndex,rowIndex);
            SaveClients.client.setStartingPoint(rowIndex,colIndex);
        }
    }

    public void ChangeImageTest(){
        Card1.setImage(image4);
    }

    public void setFigureOnMapNew(int figure,String direction, int x, int y){
        //gaga
        // ID 0
        if (checkPointMap.get(0) != null) {
            gridpane1.add(new ImageView(image61), checkPointMap.get(0)[1], checkPointMap.get(0)[0]);
        }
        //ID 1
        if (checkPointMap.get(1) != null) {
            gridpane1.add(new ImageView(image65), checkPointMap.get(1)[1], checkPointMap.get(1)[0]);
        }
        //ID 2
        if (checkPointMap.get(2) != null) {
            gridpane1.add(new ImageView(image62), checkPointMap.get(2)[1], checkPointMap.get(2)[0]);
        }
        //ID 3
        if (checkPointMap.get(3) != null) {
            gridpane1.add(new ImageView(image54), checkPointMap.get(3)[1], checkPointMap.get(3)[0]);
        }
        //ID 4
        if (checkPointMap.get(4) != null) {
            gridpane1.add(new ImageView(image48), checkPointMap.get(4)[1], checkPointMap.get(4)[0]);
        }

        if (figure == 0){
            if (direction == "top"){
                gridpane1.add(new ImageView(TwinkyOben),y,x);
            }
            if (direction == "right"){
                gridpane1.add(new ImageView(TwinkyRechts),y,x);
            }
            if (direction == "left") {
                gridpane1.add(new ImageView(TwinkyLinks),y,x);
            }
            if (direction == "bottom") {
                gridpane1.add(new ImageView(TwinkyUnten),y,x);
            }
        }
        if (figure == 1){
            if (direction == "top"){
                gridpane1.add(new ImageView(SmashOben),y,x);
            }
            if (direction == "right"){
                gridpane1.add(new ImageView(SmashRechts),y,x);
            }
            if (direction == "left") {
                gridpane1.add(new ImageView(SmashLinks),y,x);
            }
            if (direction == "bottom") {
                gridpane1.add(new ImageView(SmashUnten),y,x);
            }
        }
        if (figure == 2){
            if (direction == "top"){
                gridpane1.add(new ImageView(HulkOben),y,x);
            }
            if (direction == "right"){
                gridpane1.add(new ImageView(HulkRechts),y,x);
            }
            if (direction == "left") {
                gridpane1.add(new ImageView(HulkLinks),y,x);
            }
            if (direction == "bottom") {
                gridpane1.add(new ImageView(HulkUnten),y,x);
            }
        }
        if (figure == 3){
            if (direction == "top"){
                gridpane1.add(new ImageView(ZoomOben),y,x);
            }
            if (direction == "right"){
                gridpane1.add(new ImageView(ZoomRechts),y,x);
            }
            if (direction == "left") {
                gridpane1.add(new ImageView(ZoomLinks),y,x);
            }
            if (direction == "bottom") {
                gridpane1.add(new ImageView(ZoomUnten),y,x);
            }
        }
        if (figure == 4){
            if (direction == "top"){
                gridpane1.add(new ImageView(SpinOben),y,x);
            }
            if (direction == "right"){
                gridpane1.add(new ImageView(SpinRechts),y,x);
            }
            if (direction == "left") {
                gridpane1.add(new ImageView(SpinLinks),y,x);
            }
            if (direction == "bottom") {
                gridpane1.add(new ImageView(SpinUnten),y,x);
            }
        }
        if (figure == 5){
            if (direction == "top"){
                gridpane1.add(new ImageView(HammerOben),y,x);
            }
            if (direction == "right"){
                gridpane1.add(new ImageView(HammerRechts),y,x);
            }
            if (direction == "left") {
                gridpane1.add(new ImageView(HammerLinks),y,x);
            }
            if (direction == "bottom") {
                gridpane1.add(new ImageView(HammerUnten),y,x);
            }
        }
    }

    public Image getImageForRegisterCard(String cardName) {
        switch (cardName) {
            case "MoveI":
                return Move1;
            case "MoveII":
                return Move2;
            case "MoveIII":
                return Move3;

            case "TurnLeft":
                return LeftTurn;

            case "TurnRight":
                return RightTurn;

            case "UTurn":
                return UTurn;

            case "BackUp":
                return BackUp;
            case "PowerUp":
                return PowerUp;

            case "Again":
                return Again;

            case "Spam":
                return spam;

            case "Virus":
                return virus;

            case "Worm":
                return worm;

            case "Trojan":
                return trojanHorse;

            default:
                return Karte;
        }
    }

    public void ChooseCard1(){
        if (!adminPrivaPressed && !isFilled[0]) {
            SaveClients.client.sendCardToRegister(SaveClients.client.getCardOfGui(),0);
            Card1.setImage(getImageForRegisterCard(bildForRegisterCard));
            bildForRegisterCard = "";
            setChoosecardDisabledFalseTempo();
            isFilled[0] = true;
            fromChooseCard[0] = getChooseCardIsThis();

        }
        else {
            if (isFilled[0] && !adminPrivaPressed) {
                resetChooseCard(fromChooseCard[0]);
                SaveClients.client.sendCardToRegister(null, 0);
                Card1.setImage(getImageForRegisterCard(""));
                RegisterPlatz--;
                setChoosecardDisabledFalseTempo();
                isFilled[0] = false;
            }
            if (adminPrivaPressed) {
                SaveClients.client.chooseRegister(0);
                adminPrivaPressed = false;
            }
        }
    }
    public void ChooseCard2(){
        if (!adminPrivaPressed && !isFilled[1]){
            SaveClients.client.sendCardToRegister(SaveClients.client.getCardOfGui(),1);
            Card2.setImage(getImageForRegisterCard(bildForRegisterCard));
            bildForRegisterCard = "";
            setChoosecardDisabledFalseTempo();
            isFilled[1] = true;
            fromChooseCard[1] = getChooseCardIsThis();
        }
        else {
            if (isFilled[1] && !adminPrivaPressed) {
                resetChooseCard(fromChooseCard[1]);
                SaveClients.client.sendCardToRegister(null, 1);
                Card2.setImage(getImageForRegisterCard(""));
                setChoosecardDisabledFalseTempo();
                RegisterPlatz--;
                isFilled[1] = false;
            }
            if (adminPrivaPressed) {
                SaveClients.client.chooseRegister(1);
                adminPrivaPressed = false;
            }
        }

    }
    public void ChooseCard3(){
        if (!adminPrivaPressed && !isFilled[2]) {
            SaveClients.client.sendCardToRegister(SaveClients.client.getCardOfGui(),2);
            Card3.setImage(getImageForRegisterCard(bildForRegisterCard));
            bildForRegisterCard = "";
            setChoosecardDisabledFalseTempo();
            isFilled[2] = true;
            fromChooseCard[2] = getChooseCardIsThis();
        }

        else {
            if (isFilled[2] && !adminPrivaPressed) {
                resetChooseCard(fromChooseCard[2]);
                SaveClients.client.sendCardToRegister(null, 2);
                Card3.setImage(getImageForRegisterCard(""));
                setChoosecardDisabledFalseTempo();
                RegisterPlatz--;
                isFilled[2] = false;
            }
            if (adminPrivaPressed) {
                SaveClients.client.chooseRegister(2);
                adminPrivaPressed = false;
            }
        }
    }
    public void ChooseCard4(){
        if (!isFilled[3] && !adminPrivaPressed) {
            SaveClients.client.sendCardToRegister(SaveClients.client.getCardOfGui(),3);
            Card4.setImage(getImageForRegisterCard(bildForRegisterCard));
            bildForRegisterCard = "";
            setChoosecardDisabledFalseTempo();
            isFilled[3] = true;
            fromChooseCard[3] = getChooseCardIsThis();
        }
        else {
            if (isFilled[3] && !adminPrivaPressed) {
                resetChooseCard(fromChooseCard[3]);
                SaveClients.client.sendCardToRegister(null, 3);
                Card4.setImage(getImageForRegisterCard(""));
                setChoosecardDisabledFalseTempo();
                RegisterPlatz--;
                isFilled[3] = false;
            }

            if (adminPrivaPressed) {
                SaveClients.client.chooseRegister(3);
                adminPrivaPressed = false;
            }
        }
    }
    public void ChooseCard5(){
        if (!isFilled[4] && !adminPrivaPressed) {
            SaveClients.client.sendCardToRegister(SaveClients.client.getCardOfGui(),4);
            Card5.setImage(getImageForRegisterCard(bildForRegisterCard));
            bildForRegisterCard = "";
            setChoosecardDisabledFalseTempo();
            isFilled[4] = true;
            fromChooseCard[4] = getChooseCardIsThis();
        }
        else {
            if (isFilled[4] && !adminPrivaPressed) {
                resetChooseCard(fromChooseCard[4]);
                SaveClients.client.sendCardToRegister(null, 4);
                Card5.setImage(getImageForRegisterCard(""));
                setChoosecardDisabledFalseTempo();
                RegisterPlatz--;
                isFilled[4] = false;
            }
            if (adminPrivaPressed) {
                SaveClients.client.chooseRegister(4);
                adminPrivaPressed = false;
            }
        }
    }

    public void resetRegisterCard(){
        Card1.setImage(Karte);
        Card2.setImage(Karte);
        Card3.setImage(Karte);
        Card4.setImage(Karte);
        Card5.setImage(Karte);
        Arrays.fill(isFilled,false);
    }

    public void ChooseDirectionSetvisible(){
        DirectionUnten.setVisible(true);
        DirectionRechts.setVisible(true);
        DirectionLinks.setVisible(true);
        DirectionOben.setVisible(true);
    }

    public void pressRightDirection(){
        SaveClients.client.setNewDirection("right");
    }

    public void pressDownDirecton(){
        SaveClients.client.setNewDirection("bottom");
    }

    public void pressUpDirection(){
        SaveClients.client.setNewDirection("top");
    }

    public void pressLeftDirection(){
        SaveClients.client.setNewDirection("left");
    }

    public void setDirectionUnvisible(){
        DirectionOben.setVisible(false);
        DirectionLinks.setVisible(false);
        DirectionRechts.setVisible(false);
        DirectionUnten.setVisible(false);
    }

    public void runUpgradeCards() throws IOException {
        Stage stage = new Stage();
        stage.setTitle(SaveClients.client.getTitleUserName());
        Parent signIn = FXMLLoader.load(getClass().getResource("fxml/UpgradeCards.fxml"));
        Scene signInScene = new Scene(signIn);
        stage.setScene(signInScene);
        stage.show();
        StageSaver.getStageSaver().setUpgradeCardsStage(stage);
        stage.setOnCloseRequest(e -> Platform.exit());
    }

    public void runChooseCardsForSwap() throws IOException {
        Stage stage = new Stage();
        stage.setTitle(SaveClients.client.getTitleUserName());
        Parent signIn = FXMLLoader.load(getClass().getResource("fxml/ChooseCardsForSwap.fxml"));
        Scene signInScene = new Scene(signIn);
        stage.setScene(signInScene);
        stage.show();
        StageSaver.getStageSaver().setUpgradeCardsStageForSwap(stage);
        stage.setOnCloseRequest(e -> Platform.exit());
    }

    public void setImageForUpgradeCard(String cardname){
        switch (cardname){
            case "AdminPrivilege" :
                if (!upgradeCardsFilled[0]) {
                    UpgradeCard1.setVisible(true);
                    UpgradeCard1.setImage(AdminPrivilege);
                    upgradeCardsFilled[0] = true;
                }
                else {
                    UpgradeCard2.setVisible(true);
                    UpgradeCard2.setImage(AdminPrivilege);
                    upgradeCardsFilled[1] = true;
                }
               break;
            case "RearLaser" :
                if (!upgradeCardsFilled[0]){
                    UpgradeCard1.setVisible(true);
                    UpgradeCard1.setImage(RearLaser);
                    upgradeCardsFilled[0] = true;
                }
                else {
                    UpgradeCard2.setVisible(true);
                    UpgradeCard2.setImage(RearLaser);
                    upgradeCardsFilled[1] = true;
                }

                break;
            case "MemorySwap" :
                if (!upgradeCardsFilled[2]) {
                    UpgradeCard3.setVisible(true);
                    UpgradeCard3.setImage(MemorySwap);
                    upgradeCardsFilled[2] = true;
                }
                else {
                    UpgradeCard4.setVisible(true);
                    UpgradeCard4.setImage(MemorySwap);
                    upgradeCardsFilled[3] = true;
                }
                break;
            case  "SpamBlocker" :
                if (!upgradeCardsFilled[2]){
                    UpgradeCard3.setVisible(true);
                    UpgradeCard3.setImage(SpamBlocker);
                    upgradeCardsFilled[2] = true;
                }
                else {
                    UpgradeCard4.setVisible(true);
                    UpgradeCard4.setImage(SpamBlocker);
                    upgradeCardsFilled[3] = true;
                }
                break;
            default:
                UpgradeCard1.setImage(SpamBlocker);
        }
    }

    public void upgradeCardPressed(){
        if (SaveClients.client.getUpgradeCardName()[0].equals("AdminPrivilege")){
            adminPrivaPressed = true;
        }
    }

    public void upgradeCard1Pressed(){
        if (SaveClients.client.getUpgradeCardName()[1].equals("AdminPrivilege")){
            adminPrivaPressed = true;
        }
    }

    public void setDefaultMap(){
        for (int i = 0; i < 10; i++){
            for (int j = 0; j < 13; j++){
                gridpane1.add(new ImageView(saveMap[i][j]), j, i);
            }
        }
    }

    public void setUnvisibleUpGradeCards(){
        UpgradeCard3.setDisable(true);
        UpgradeCard3.setVisible(false);
        UpgradeCard4.setDisable(true);
        UpgradeCard4.setVisible(false);

    }

    public void runGewonnen() throws IOException {
        Stage stage = new Stage();
        stage.setTitle("You won");
        Parent signIn = FXMLLoader.load(getClass().getResource("fxml/GameFinished.fxml"));
        Scene signInScene = new Scene(signIn);
        stage.setScene(signInScene);
        stage.show();
        StageSaver.getStageSaver().setUpgradeCardsStageForSwap(stage);
        stage.setOnCloseRequest(e -> Platform.exit());
    }

    public void setChooseCardUnvisible(){
        chooseCard1.setVisible(false);
        chooseCard2.setVisible(false);
        chooseCard3.setVisible(false);
        chooseCard4.setVisible(false);
        chooseCard5.setVisible(false);
        chooseCard6.setVisible(false);
        chooseCard7.setVisible(false);
        chooseCard8.setVisible(false);
        chooseCard9.setVisible(false);
        chooseCard1.setDisable(true);
        chooseCard2.setDisable(true);
        chooseCard3.setDisable(true);
        chooseCard4.setDisable(true);
        chooseCard5.setDisable(true);
        chooseCard6.setDisable(true);
        chooseCard7.setDisable(true);
        chooseCard8.setDisable(true);
        chooseCard9.setDisable(true);


    }

    public void setChooseCardDisabledTempo(){
        chooseCard1.setDisable(true);
        chooseCard2.setDisable(true);
        chooseCard3.setDisable(true);
        chooseCard4.setDisable(true);
        chooseCard5.setDisable(true);
        chooseCard6.setDisable(true);
        chooseCard7.setDisable(true);
        chooseCard8.setDisable(true);
        chooseCard9.setDisable(true);
    }

    public void setChoosecardDisabledFalseTempo(){
        chooseCard1.setDisable(false);
        chooseCard2.setDisable(false);
        chooseCard3.setDisable(false);
        chooseCard4.setDisable(false);
        chooseCard5.setDisable(false);
        chooseCard6.setDisable(false);
        chooseCard7.setDisable(false);
        chooseCard8.setDisable(false);
        chooseCard9.setDisable(false);
    }

    public void fillChooseCard(){
        RegisterPlatz = -1;
        chooseCard1.setVisible(true);
        chooseCard2.setVisible(true);
        chooseCard3.setVisible(true);
        chooseCard4.setVisible(true);
        chooseCard5.setVisible(true);
        chooseCard6.setVisible(true);
        chooseCard7.setVisible(true);
        chooseCard8.setVisible(true);
        chooseCard9.setVisible(true);
        chooseCard1.setDisable(false);
        chooseCard2.setDisable(false);
        chooseCard3.setDisable(false);
        chooseCard4.setDisable(false);
        chooseCard5.setDisable(false);
        chooseCard6.setDisable(false);
        chooseCard7.setDisable(false);
        chooseCard8.setDisable(false);
        chooseCard9.setDisable(false);
        chooseCard1.setImage(getImageForRegisterCard(SaveClients.client.getHandcards().get(0).getName()));
        chooseCard2.setImage(getImageForRegisterCard(SaveClients.client.getHandcards().get(1).getName()));
        chooseCard3.setImage(getImageForRegisterCard(SaveClients.client.getHandcards().get(2).getName()));
        chooseCard4.setImage(getImageForRegisterCard(SaveClients.client.getHandcards().get(3).getName()));
        chooseCard5.setImage(getImageForRegisterCard(SaveClients.client.getHandcards().get(4).getName()));
        chooseCard6.setImage(getImageForRegisterCard(SaveClients.client.getHandcards().get(5).getName()));
        chooseCard7.setImage(getImageForRegisterCard(SaveClients.client.getHandcards().get(6).getName()));
        chooseCard8.setImage(getImageForRegisterCard(SaveClients.client.getHandcards().get(7).getName()));
        chooseCard9.setImage(getImageForRegisterCard(SaveClients.client.getHandcards().get(8).getName()));
    }

    public void takeCard1(){
        if (RegisterPlatz < 4) {
            RegisterPlatz++;
            chooseCard1.setDisable(true);
            chooseCard1.setVisible(false);
            setChooseCardDisabledTempo();
            chooseCardIsThis = 0;
            bildForRegisterCard = SaveClients.client.getHandcards().get(0).getName();
            SaveClients.client.setCardOfGui(SaveClients.client.getHandcards().get(0).getName());
        }
        else {
            SaveClients.client.printMessage("Dein Register ist voll!");
        }
    }
    public void takeCard2(){
        if (RegisterPlatz < 4) {
            RegisterPlatz++;
            chooseCard2.setDisable(true);
            chooseCard2.setVisible(false);
            setChooseCardDisabledTempo();
            bildForRegisterCard = SaveClients.client.getHandcards().get(1).getName();
            SaveClients.client.setCardOfGui(SaveClients.client.getHandcards().get(1).getName());
            chooseCardIsThis = 1;
        }
        else {
            SaveClients.client.printMessage("Dein Register ist voll!");
        }
    }
    public void takeCard3(){
        if (RegisterPlatz < 4) {
            RegisterPlatz++;
            chooseCard3.setDisable(true);
            chooseCard3.setVisible(false);
            setChooseCardDisabledTempo();
            bildForRegisterCard = SaveClients.client.getHandcards().get(2).getName();
            SaveClients.client.setCardOfGui(SaveClients.client.getHandcards().get(2).getName());
            chooseCardIsThis = 2;
        }
        else {
            SaveClients.client.printMessage("Dein Register ist voll!");
        }
    }
    public void takeCard4(){
        if (RegisterPlatz < 4) {
            RegisterPlatz++;
            //SaveClients.client.sendCardToRegister(SaveClients.client.getHandcards().get(3).getName(), RegisterPlatz);
            chooseCard4.setDisable(true);
            chooseCard4.setVisible(false);
            setChooseCardDisabledTempo();
            bildForRegisterCard = SaveClients.client.getHandcards().get(3).getName();
            SaveClients.client.setCardOfGui(SaveClients.client.getHandcards().get(3).getName());
            chooseCardIsThis = 3;
        }
        else {
            SaveClients.client.printMessage("Dein Register ist voll!");
        }
    }
    public void takeCard5(){
        if (RegisterPlatz < 4) {
            RegisterPlatz++;
            chooseCard5.setDisable(true);
            chooseCard5.setVisible(false);
            setChooseCardDisabledTempo();
            bildForRegisterCard = SaveClients.client.getHandcards().get(4).getName();
            SaveClients.client.setCardOfGui(SaveClients.client.getHandcards().get(4).getName());
            chooseCardIsThis = 4;
        }
        else {
            SaveClients.client.printMessage("Dein Register ist voll!");
        }
    }
    public void takeCard6(){
        if (RegisterPlatz < 4) {
            RegisterPlatz++;
            chooseCard6.setDisable(true);
            chooseCard6.setVisible(false);
            setChooseCardDisabledTempo();
            bildForRegisterCard = SaveClients.client.getHandcards().get(5).getName();
            SaveClients.client.setCardOfGui(SaveClients.client.getHandcards().get(5).getName());
            chooseCardIsThis = 5;
        }
        else {
            SaveClients.client.printMessage("Dein Register ist voll!");
        }
    }
    public void takeCard7(){
        if (RegisterPlatz < 4) {
            RegisterPlatz++;
            chooseCard7.setDisable(true);
            chooseCard7.setVisible(false);
            setChooseCardDisabledTempo();
            bildForRegisterCard = SaveClients.client.getHandcards().get(6).getName();
            SaveClients.client.setCardOfGui(SaveClients.client.getHandcards().get(6).getName());
            chooseCardIsThis = 6;
        }
        else {
            SaveClients.client.printMessage("Dein Register ist voll!");
        }
    }
    public void takeCard8(){
        if (RegisterPlatz < 4) {
            RegisterPlatz++;
            chooseCard8.setDisable(true);
            chooseCard8.setVisible(false);
            setChooseCardDisabledTempo();
            bildForRegisterCard = SaveClients.client.getHandcards().get(7).getName();
            SaveClients.client.setCardOfGui(SaveClients.client.getHandcards().get(7).getName());
            chooseCardIsThis = 7;
        }
        else {
            SaveClients.client.printMessage("Dein Register ist voll!");
        }
    }
    public void takeCard9(){
        if (RegisterPlatz < 4) {
            RegisterPlatz++;
            chooseCard9.setDisable(true);
            chooseCard9.setVisible(false);
            setChooseCardDisabledTempo();
            bildForRegisterCard = SaveClients.client.getHandcards().get(8).getName();
            SaveClients.client.setCardOfGui(SaveClients.client.getHandcards().get(8).getName());
            chooseCardIsThis = 8;
        }
        else {
            SaveClients.client.printMessage("Dein Register ist voll!");
        }
    }

    public int getChooseCardIsThis() {
        return chooseCardIsThis;
    }

    public void resetChooseCard(int i){
        switch (i){
            case 0:
                chooseCard1.setVisible(true);
                chooseCard1.setDisable(false);
                break;
            case 1:
                chooseCard2.setVisible(true);
                chooseCard2.setDisable(false);
                break;
            case 2:
                chooseCard3.setVisible(true);
                chooseCard3.setDisable(false);
                break;
            case 3:
                chooseCard4.setVisible(true);
                chooseCard4.setDisable(false);
                break;
            case 4:
                chooseCard5.setVisible(true);
                chooseCard5.setDisable(false);
                break;
            case 5:
                chooseCard6.setVisible(true);
                chooseCard6.setDisable(false);
                break;
            case 6:
                chooseCard7.setVisible(true);
                chooseCard7.setDisable(false);
                break;
            case 7:
                chooseCard8.setVisible(true);
                chooseCard8.setDisable(false);
                break;
            case 8:
                chooseCard9.setVisible(true);
                chooseCard9.setDisable(false);
                break;
            default:
                break;

        }
    }

    public void startTimer() {

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {

                if(time > 0)
                {
                    Platform.runLater(() -> timerLabel.setText("00" + ":" + Integer.toString(time)));
                    time--;
                    if(time < 6)
                    {
                        timerLabel.setTextFill(Color.web("red"));
                    }
                }
                else {
                    timerLabel.setVisible(false);
                    timer.cancel();
                }
            }
        }, 1000,1000);
        time = 30;
        timerLabel.setVisible(true);
        timerLabel.setText("");
        timerLabel.setTextFill(Color.web("black"));
    }

    public void hideTimer(){
        timerLabel.setVisible(false);
    }

    public void runDamageCardExtra() throws IOException {
        Stage stage = new Stage();
        stage.setTitle(SaveClients.client.getTitleUserName());
        Parent signIn = FXMLLoader.load(getClass().getResource("fxml/SpamExtraView.fxml"));
        Scene signInScene = new Scene(signIn);
        stage.setScene(signInScene);
        stage.show();
        stage.setOnCloseRequest(e -> Platform.exit());
    }
}
