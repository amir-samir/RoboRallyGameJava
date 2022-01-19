import com.sun.javafx.collections.MappingChange;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.*;

public class AllInOneView implements Initializable {
    ArrayList<BoardElement>[][] mapGui = SaveClients.client.getMap();
    Boolean[] isFilled = new Boolean[5];
    List<HashMap<String,Object>> laserList = new ArrayList<HashMap<String,Object>>();
    List wallList = new ArrayList<>();
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
    @FXML
    int[][] crispyMapArray = new int[][]{
            { 0, 1, 2 ,3 ,4 ,1 ,1, 1, 5, 6, 7, 8, 1},
            { 1, 9, 1, 1, 4, 1, 10, 11, 1, 1, 1, 4, 1},
            { 1, 12, 1, 1, 4, 13, 14, 1, 1, 14, 15, 4, 1},
            { 9, 1, 1, 1, 16, 17, 14, 6, 8, 14, 18, 19, 1},
            { 20, 9, 21, 22, 1, 23, 24, 1, 1, 21, 23, 5, 12},
            { 1, 9, 21, 26, 1, 23, 27, 5, 1, 28, 23, 1, 26},
            { 9, 1, 1, 1, 29, 17, 14, 6, 8, 14, 18, 30, 1},
            { 1, 26, 1, 1, 31, 32, 14, 1, 1, 14, 33, 31, 1},
            { 1, 9, 1, 1, 31, 1, 1, 1, 2, 34, 1, 31, 1},
            { 35, 1, 2, 5, 6, 7, 8, 1, 1, 1, 1, 31, 1}

    };
    int[][] LostBearingArray = new int[][]{
            { 0, 1, 2, 1, 36, 1, 1, 1, 1, 1, 1, 37, 1},
            { 1, 9, 1, 11, 38, 1, 11, 11, 2, 2, 1, 39, 11},
            { 1, 12, 1, 1, 1, 40, 14, 1, 33, 14, 40, 1, 1},
            { 9, 1, 1, 1, 1, 4, 41, 7, 7, 8, 4, 1, 1},
            { 20, 9, 21, 1, 1, 28, 1, 40, 28, 1, 24, 15, 1},
            { 1, 9, 21, 1, 32, 24, 1, 24, 40, 1, 28, 1, 1},
            { 9, 1, 1, 1, 1, 31, 6, 7, 7, 42, 31, 1, 1},
            { 1, 26, 1, 1, 1, 40, 14, 1, 13, 14, 40, 1, 1},
            { 1, 9, 1, 2, 43, 1, 1, 1, 1, 1, 1, 44, 2},
            { 45, 1, 2, 46, 36, 1, 1, 1, 1, 1, 1, 37, 1}

    };
    //daaaaaa
    int[][] DeathTrapMap = new int[][]{
            { 1, 1, 11, 11, 47, 1, 1, 1, 1, 1, 11, 1, 45},
            { 48, 49, 1, 50, 39, 11, 1, 14, 66, 37, 1, 9, 1},
            { 1, 14, 51, 14, 52, 1, 53, 49, 54, 37, 1, 12, 1},
            { 1, 1, 55, 1, 56, 1, 1, 14, 57, 37, 1, 1, 9},
            { 1, 36, 1, 14, 65, 1, 49, 1, 44, 58, 57, 9, 1},
            { 59, 60, 1, 56, 49, 26, 14, 1, 37, 1, 57, 9, 20},
            { 36, 21, 14, 1, 52, 1, 1, 52, 1, 1, 1, 1, 9},
            { 36, 61, 56, 53, 1, 1, 14, 66, 14, 1, 1, 26, 1},
            { 36, 51, 14, 1, 2, 43, 12, 62, 56, 1, 1, 9, 1},
            { 63, 1, 1, 1, 1, 64, 2, 2, 2, 1, 11, 1, 91}

    };
    int[][] DizzyHighwayMap = new int[][]{
            { 1, 1, 2, 1, 67, 67, 1, 1, 1, 1, 1, 1, 68},
            { 1, 9, 1, 1, 69, 70, 71, 71, 71, 71, 71, 72, 71},
            { 1, 12, 1, 1, 67, 68, 1, 1, 1, 1, 1, 73, 71},
            { 9, 1, 1, 1, 67, 1, 74, 75, 76, 77, 1, 78, 79},
            { 20, 9, 21, 1, 67, 1, 80, 1, 68, 1, 1, 78, 1},
            { 1, 9, 21, 1, 67, 1, 1, 68, 1, 81, 1, 78, 1},
            { 9, 1, 1, 1, 67, 1, 82, 83, 1, 84, 1, 78, 1},
            { 1, 26, 1, 85, 86, 1, 1, 1, 1, 1, 68, 78, 1},
            { 1, 9, 1, 85, 87, 85, 85, 85, 85, 85, 88, 89, 1},
            { 35, 1, 2, 68, 1, 1, 1, 1, 1, 1, 78, 78, 90}

    };
    @FXML
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
    Image image75 = new Image("assets/GruenFeldUnten.png");
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



    //Karten
    Image Karte = new Image("assets/Karte.png");
    @FXML
    Image Move1 = new Image("assets/Move1.png");
    Image Move2 = new Image("assets/Move2Blau.png");
    Image Move3 = new Image("assets/Move3Blau.png");
    Image PowerUp = new Image("assets/PowerUpBlau.png");
    Image RightTurn = new Image("assets/RightTurnBlau.png");
    Image UTurn = new Image("assets/UTurnBlau.png");
    Image LeftTurn = new Image ("assets/LeftTurnBlau.png");
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


    public AllInOneView(){
     Client.setAllInOneView(this);
    }
    public void sendMessage() {

        SaveClients.client.printMessage(writeField.textProperty().get());
        writeField.clear();

    }

    public void getLaserOnMaps() {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Arrays.fill(isFilled,false);
        setDefaultMap();
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

    }

    public void sendPrivateMessage(){
        String selectedUser = PrivateMessage.getValue().toString().split(",")[1];
        String msg = privateMsgInput.getText();

        SaveClients.client.singleMessage(SaveClients.client.getID(), msg, selectedUser);
        privateMsgInput.clear();
    }

    public void setReady(){
        SaveClients.client.setReady();
    }

    public void setDefaultMap(){

        for (int x = 0; x < gridpane1.getRowCount(); x++){
            for (int y = 0; y < gridpane1.getColumnCount(); y++){
                if (mapGui[x][y].size() == 0){
                    gridpane1.add(new ImageView(image1),y,x);
                }
                else if (mapGui[x][y].size() == 2){
                    if (Objects.equals(mapGui[x][y].get(0).getType(), "PushPanel")){
                       switch (mapGui[x][y].get(0).getOrientations()[0]) {
                           case "bottom":
                               gridpane1.add(new ImageView(image49),y,x);
                               break;
                           case "top":
                               gridpane1.add(new ImageView(image56),y,x);
                               break;
                           case "right":
                               gridpane1.add(new ImageView(image51),y,x);
                               break;
                           case "left":
                               gridpane1.add(new ImageView(image66),y,x);
                               break;

                       }

                    }
                    //hiiii
                    if (Objects.equals(mapGui[x][y].get(0).getType(), "Laser") && Objects.equals(mapGui[x][y].get(1).getType(), "Wall")) {
                        HashMap<String,Object> laserPosition = new HashMap();
                        laserPosition.put("x",x);
                        laserPosition.put("y",y);


                        /*List<Integer> laserPositon = new ArrayList<>();
                        laserPositon.add(x);
                        laserPositon.add(y);
                        List laserOrientation = new ArrayList<>();
                        laserOrientation.add(laserPositon);*/

                        switch (mapGui[x][y].get(0).getOrientations()[0]) {
                            case "bottom":
                                gridpane1.add(new ImageView(image81),y,x);
                                laserPosition.put("Orientation","bottom");
                                break;
                            case "top":
                                gridpane1.add(new ImageView(image80),y,x);
                                while (true) {
                                    gridpane1.add(new ImageView(image74), y, x - 1);
                                    if(mapGui[x-1][y].get(0).getType().equals("Wall") && (mapGui[x-1][y].get(0).getOrientations()[0].equals("top") || mapGui[x-1][y].get(0).getOrientations()[0].equals("bottom"))){
                                        break;
                                    }
                                }

                                laserPosition.put("Orientation","top");
                                break;
                            case "right":
                                gridpane1.add(new ImageView(image41),y,x);
                                laserPosition.put("Orientation","right");
                                break;
                            case "left":
                                gridpane1.add(new ImageView(image8),y,x);
                                laserPosition.put("Orientation","left");
                                break;
                        }
                        laserList.add(laserPosition);
                    }
                }
                else{
                    if (Objects.equals(mapGui[x][y].get(0).getType(), "Wall")) {
                        switch (mapGui[x][y].get(0).getOrientations()[0]) {
                            case "bottom":
                                gridpane1.add(new ImageView(image50),y,x);
                                break;
                            case "top":
                                gridpane1.add(new ImageView(image12),y,x);
                                break;
                            case "right":
                                gridpane1.add(new ImageView(image21),y,x);
                                break;
                            case "left":
                                gridpane1.add(new ImageView(image57),y,x);
                                break;

                        }

                    }
                    if (Objects.equals(mapGui[x][y].get(0).getType(), "Antenna")) {
                        // Muss evtl. noch weiter angepasst werden
                        gridpane1.add(new ImageView(image20),y,x);
                    }
                    if (Objects.equals(mapGui[x][y].get(0).getType(), "EnergySpace")) {
                        // Muss evtl. noch weiter angepasst werden
                        gridpane1.add(new ImageView(image52),y,x);
                    }
                    if (Objects.equals(mapGui[x][y].get(0).getType(), "StartPoint")) {
                        gridpane1.add(new ImageView(image9),y,x);
                    }
                    if (Objects.equals(mapGui[x][y].get(0).getType(), "Pit")) {
                        gridpane1.add(new ImageView(image14),y,x);
                    }
                    if (Objects.equals(mapGui[x][y].get(0).getType(), "RestartPoint")) {
                        if (Objects.equals(mapGui[x][y].get(0).getIsOnBoard(), "DeathTrap")) {
                            gridpane1.add(new ImageView(image91),y,x);
                        }
                        if (Objects.equals(mapGui[x][y].get(0).getIsOnBoard(), "DizzyHighway")) {
                            gridpane1.add(new ImageView(image0),y,x);
                        }
                        if (Objects.equals(mapGui[x][y].get(0).getIsOnBoard(), "ExtraCrispy")) {
                            gridpane1.add(new ImageView(image0),y,x);
                        }
                        if (Objects.equals(mapGui[x][y].get(0).getIsOnBoard(), "LostBearings")) {
                            gridpane1.add(new ImageView(image0),y,x);
                        }
                    }
                    if (Objects.equals(mapGui[x][y].get(0).getType(), "ConveyorBelt")){
                        if (Objects.equals(mapGui[x][y].get(0).getOrientations()[0], "top") && Objects.equals(mapGui[x][y].get(0).getOrientations()[1],"right")) {
                            if (mapGui[x][y].get(0).getSpeed() == 1) {
                                gridpane1.add(new ImageView(image39), y, x);
                            } else {
                                gridpane1.add(new ImageView(image16), y, x);
                            }
                        }
                        if (Objects.equals(mapGui[x][y].get(0).getOrientations()[0], "right") && Objects.equals(mapGui[x][y].get(0).getOrientations()[1],"bottom")) {
                            if (mapGui[x][y].get(0).getSpeed() == 1) {
                                gridpane1.add(new ImageView(image44), y, x);
                            } else {
                                gridpane1.add(new ImageView(image29), y, x);
                            }
                        }
                        if (Objects.equals(mapGui[x][y].get(0).getOrientations()[0], "right") && Objects.equals(mapGui[x][y].get(0).getOrientations()[1],"left")) {
                            if (mapGui[x][y].get(0).getSpeed() == 1) {
                                gridpane1.add(new ImageView(image2), y, x);
                            } else {
                                gridpane1.add(new ImageView(image85), y, x);
                            }
                        }
                        if (Objects.equals(mapGui[x][y].get(0).getOrientations()[0], "right") && Objects.equals(mapGui[x][y].get(0).getOrientations()[1],"top")) {
                            if (mapGui[x][y].get(0).getSpeed() == 1) {
                                gridpane1.add(new ImageView(image64), y, x);
                            } else {
                                // wurde noch nicht bearbeitet, wird wahrscheinlich auch nicht benutzt
                                gridpane1.add(new ImageView(image85), y, x);
                            }
                        }
                        if (Objects.equals(mapGui[x][y].get(0).getOrientations()[0], "bottom") && Objects.equals(mapGui[x][y].get(0).getOrientations()[1],"left")) {
                            if (mapGui[x][y].get(0).getSpeed() == 1) {
                                gridpane1.add(new ImageView(image43), y, x);
                            } else {
                                // wurde noch nicht bearbeitet, wird wahrscheinlich auch nicht benutzt
                                gridpane1.add(new ImageView(image4), y, x);
                            }
                        }
                        if (Objects.equals(mapGui[x][y].get(0).getOrientations()[0], "bottom") && Objects.equals(mapGui[x][y].get(0).getOrientations()[1],"top")) {
                            if (mapGui[x][y].get(0).getSpeed() == 1) {
                                gridpane1.add(new ImageView(image36), y, x);
                            } else {
                                gridpane1.add(new ImageView(image4), y, x);
                            }
                        }
                        if (Objects.equals(mapGui[x][y].get(0).getOrientations()[0], "bottom") && Objects.equals(mapGui[x][y].get(0).getOrientations()[1],"right")) {
                            if (mapGui[x][y].get(0).getSpeed() == 1) {
                                gridpane1.add(new ImageView(image59), y, x);
                            } else {
                                // wurde noch nicht bearbeitet, wird wahrscheinlich auch nicht benutzt
                                gridpane1.add(new ImageView(image4), y, x);
                            }
                        }
                        if (Objects.equals(mapGui[x][y].get(0).getOrientations()[0], "top") && Objects.equals(mapGui[x][y].get(0).getOrientations()[1],"bottom")) {
                            if (mapGui[x][y].get(0).getSpeed() == 1) {
                                gridpane1.add(new ImageView(image37), y, x);
                            } else {
                                gridpane1.add(new ImageView(image31), y, x);
                            }
                        }
                        if (Objects.equals(mapGui[x][y].get(0).getOrientations()[0], "top") && Objects.equals(mapGui[x][y].get(0).getOrientations()[1],"left")) {
                            if (mapGui[x][y].get(0).getSpeed() == 1) {
                                gridpane1.add(new ImageView(image58), y, x);
                            } else {
                                // wurde noch nicht bearbeitet, wird wahrscheinlich auch nicht benutzt
                                gridpane1.add(new ImageView(image31), y, x);
                            }
                        }
                        if (Objects.equals(mapGui[x][y].get(0).getOrientations()[0], "left") && Objects.equals(mapGui[x][y].get(0).getOrientations()[1],"right")){
                            if (mapGui[x][y].get(0).getSpeed() == 1) {
                                gridpane1.add(new ImageView(image11), y, x);
                            } else {
                                gridpane1.add(new ImageView(image71),y,x);
                            }
                        } if (Objects.equals(mapGui[x][y].get(0).getOrientations()[0], "left") && Objects.equals(mapGui[x][y].get(0).getOrientations()[1],"bottom")) {
                            if (mapGui[x][y].get(0).getSpeed() == 1) {
                                gridpane1.add(new ImageView(image47), y, x);
                            } /*else {
                                //was wenn es blau ist!!!!
                                gridpane1.add(new ImageView(image0),y,x);
                            }*/
                        } if (Objects.equals(mapGui[x][y].get(0).getOrientations()[0], "left") && Objects.equals(mapGui[x][y].get(0).getOrientations()[1],"top")) {
                            if (mapGui[x][y].get(0).getSpeed() == 1) {
                                gridpane1.add(new ImageView(image60), y, x);
                            } else {
                                gridpane1.add(new ImageView(image19),y,x);
                            }
                        }
                        /*else {
                            gridpane1.add(new ImageView(image0),y,x);
                        }*/

                    }
                    if (Objects.equals(mapGui[x][y].get(0).getType(), "CheckPoint")){
                        switch (mapGui[x][y].get(0).getCount()){
                            case 0:
                                gridpane1.add(new ImageView(image61),y,x);
                                break;
                            case 1:
                                gridpane1.add(new ImageView(image65),y,x);
                                break;
                            case 2:
                                gridpane1.add(new ImageView(image62),y,x);
                                break;
                            case 3:
                                gridpane1.add(new ImageView(image54),y,x);
                                break;
                            case 4:
                                gridpane1.add(new ImageView(image48),y,x);
                                break;
                        }
                    }
                }

            }
        }
       /* if (SaveClients.client.getSelectedMap().equals("DizzyHighway")){
            for (int i = 0; i < gridpane1.getRowCount(); i++){
                for (int j = 0; j < gridpane1.getColumnCount(); j++){
                    gridpane1.setHgap(-80);
                    //gridpane1.setVgap(10);
                    gridpane1.add(new ImageView(getImageForMap(DizzyHighwayMap[i][j])),j,i);
                    //gridpane1.add(new ImageView(testImage),j,i);
                }
            }
        }
        if (SaveClients.client.getSelectedMap().equals("ExtraCrispy")){
            for (int i = 0; i < gridpane1.getRowCount(); i++){
                for (int j = 0; j < gridpane1.getColumnCount(); j++){
                    gridpane1.setHgap(-80);
                    //gridpane1.setVgap(10);
                    gridpane1.add(new ImageView(getImageForMap(crispyMapArray[i][j])),j,i);
                    //gridpane1.add(new ImageView(testImage),j,i);
                }
            }
        }
        if (SaveClients.client.getSelectedMap().equals("DeathTrap")){
            for (int i = 0; i < gridpane1.getRowCount(); i++){
                for (int j = 0; j < gridpane1.getColumnCount(); j++){
                    gridpane1.setHgap(-80);
                    //gridpane1.setVgap(10);
                    gridpane1.add(new ImageView(getImageForMap(DeathTrapMap[i][j])),j,i);
                    //gridpane1.add(new ImageView(testImage),j,i);
                }
            }
        }
        if (SaveClients.client.getSelectedMap().equals("LostBearings")){
            for (int i = 0; i < gridpane1.getRowCount(); i++){
                for (int j = 0; j < gridpane1.getColumnCount(); j++){
                    gridpane1.setHgap(-80);
                    //gridpane1.setVgap(10);
                    gridpane1.add(new ImageView(getImageForMap(LostBearingArray[i][j])),j,i);
                    //gridpane1.add(new ImageView(testImage),j,i);
                }
            }
        }*/
    }

    public Image getImageForMap(int image){
        switch (image){
            case 0:
                return image0;
            case 1:
                return image1;
            case 2:
                return image2;
            case 3:
                return image3;
            case 4:
                return image4;
            case 5:
                return image5;
            case 6:
                return image6;
            case 7:
                return image7;
            case 8:
                return image8;
            case 9:
                return image9;
            case 10:
                return image10;
            case 11:
                return image11;
            case 12:
                return image12;
            case 13:
                return image13;
            case 14:
                return image14;
            case 15:
                return image15;
            case 16:
                return image16;
            case 17:
                return image17;
            case 18:
                return image18;
            case 19:
                return image19;
            case 20:
                return image20;
            case 21:
                return image21;
            case 22:
                return image22;
            case 23:
                return image23;
            case 24:
                return image24;
            case 25:
                return image25;
            case 26:
                return image26;
            case 27:
                return image27;
            case 28:
                return image28;
            case 29:
                return image29;
            case 30:
                return image30;
            case 31:
                return image31;
            case 32:
                return image32;
            case 33:
                return image33;
            case 34:
                return image34;
            case 35:
                return image35;
            case 36:
                return image36;
            case 37:
                return image37;
            case 38:
                return image38;
            case 39:
                return image39;
            case 40:
                return image40;
            case 41:
                return image41;
            case 42:
                return image42;
            case 43:
                return image43;
            case 44:
                return image44;
            case 45:
                return image45;
            case 46:
                return image46;
            case 47:
                return image47;
            case 48:
                return image48;
            case 49:
                return image49;
            case 50:
                return image50;
            case 51:
                return image51;
            case 52:
                return image52;
            case 53:
                return image53;
            case 54:
                return image54;
            case 55:
                return image55;
            case 56:
                return image56;
            case 57:
                return image57;
            case 58:
                return image58;
            case 59:
                return image59;
            case 60:
                return image60;
            case 61:
                return image61;
            case 62:
                return image62;
            case 63:
                return image63;
            case 64:
                return image64;
            case 65:
                return image65;
            case 66:
                return image66;
            case 67:
                return image67;
            case 68:
                return image68;
            case 69:
                return image69;
            case 70:
                return image70;
            case 71:
                return image71;
            case 72:
                return image72;
            case 73:
                return image73;
            case 74:
                return image74;
            case 75:
                return image75;
            case 76:
                return image76;
            case 77:
                return image77;
            case 78:
                return image78;
            case 79:
                return image79;
            case 80:
                return image80;
            case 81:
                return image81;
            case 82:
                return image82;
            case 83:
                return image83;
            case 84:
                return image84;
            case 85:
                return image85;
            case 86:
                return image86;
            case 87:
                return image87;
            case 88:
                return image88;
            case 89:
                return image89;
            case 90:
                return image90;
            case 91:
                return image91;

            default:
                return image4;
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
    //TODO: Method for inserting ein Figure auf dem Map, wird von Client gerufen, und nimmt 4 Parametern(Figure, Direction, X, Y)

    public void setFigureOnMap(int x, int y){
        gridpane1.add(new ImageView(figureTest),y,x);
    }
    public void setFigureOnMapNew(int figure,String direction, int x, int y){
        if (figure == 0){
            if (direction == "top"){
                gridpane1.add(new ImageView(TwinkyOben),y,x);
            }
            if (direction == "right"){
                setDefaultMap();
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
            default:
                return Karte;
        }
    }

    public void ChooseCard1(){
        Card1.setImage(getImageForRegisterCard(SaveClients.client.getCardOfGui()));
        isFilled[0] = true;
        SaveClients.client.setCardOfGui("");
    }
    public void ChooseCard2(){
        Card2.setImage(getImageForRegisterCard(SaveClients.client.getCardOfGui()));
        isFilled[1] = true;
        SaveClients.client.setCardOfGui("");
    }
    public void ChooseCard3(){
        Card3.setImage(getImageForRegisterCard(SaveClients.client.getCardOfGui()));
        isFilled[2] = true;
        SaveClients.client.setCardOfGui("");
    }
    public void ChooseCard4(){
        Card4.setImage(getImageForRegisterCard(SaveClients.client.getCardOfGui()));
        isFilled[3] = true;
        SaveClients.client.setCardOfGui("");
    }
    public void ChooseCard5(){
        Card5.setImage(getImageForRegisterCard(SaveClients.client.getCardOfGui()));
        isFilled[4] = true;
        SaveClients.client.setCardOfGui("");
    }
    public void RunMapNew() throws Exception{
        Stage stage = new Stage();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/MaybeMap.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();

        //Close if Bye
        stage.setOnCloseRequest(e -> {
            Platform.exit();
            //clientHandler.writer.equals("bye");
            stage.close();
            System.exit(0);
        });
    }

    public void resetRegisterCard(){
        Card1.setImage(Karte);
        Card2.setImage(Karte);
        Card3.setImage(Karte);
        Card4.setImage(Karte);
        Card5.setImage(Karte);
    }

}
