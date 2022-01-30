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
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class AllInOneView implements Initializable {
    ArrayList<BoardElement>[][] mapGui = SaveClients.client.getMap();
    Boolean[] isFilled = new Boolean[5];
    List<HashMap<String,Object>> laserList = new ArrayList<HashMap<String,Object>>();
    List wallList = new ArrayList<>();

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
    Image image0 = new Image(getClass().getClassLoader().getResource("assets/GrünFeld.png").toString());
    Image image1 = new Image(getClass().getClassLoader().getResource("assets/Default51x51.png").toString());
    Image image2 = new Image(getClass().getClassLoader().getResource("assets/1rechts.png").toString());
    Image image3 = new Image(getClass().getClassLoader().getResource("assets/ExtraCrispyMap00.png").toString());
    Image image4 = new Image(getClass().getClassLoader().getResource("assets/2nachUnten.png").toString());
    Image image5 = new Image(getClass().getClassLoader().getResource("assets/GeneratorOff.png").toString());
    Image image6 = new Image(getClass().getClassLoader().getResource("assets/1LaserWandLinks.png").toString());
    Image image7 = new Image(getClass().getClassLoader().getResource("assets/LaserfeldHorizontal.png").toString());
    Image image8 = new Image(getClass().getClassLoader().getResource("assets/1LaserWandRechts.png").toString());
    Image image9 = new Image(getClass().getClassLoader().getResource("assets/Rädchen.png").toString());
    Image image10 = new Image(getClass().getClassLoader().getResource("assets/1AbbiegungLinksUnten.png").toString());
    Image image11 = new Image(getClass().getClassLoader().getResource("assets/1Links.png").toString());
    Image image12 = new Image(getClass().getClassLoader().getResource("assets/WandOben.png").toString());
    Image image13 = new Image(getClass().getClassLoader().getResource("assets/Ziel4.png").toString());
    Image image14 = new Image(getClass().getClassLoader().getResource("assets/Pit.png").toString());
    Image image15 = new Image(getClass().getClassLoader().getResource("assets/Ziel1.png").toString());
    Image image16 = new Image(getClass().getClassLoader().getResource("assets/2rechtsoben.png").toString());
    Image image17 = new Image(getClass().getClassLoader().getResource("assets/laserdurch2feld.png").toString());
    Image image18 = new Image(getClass().getClassLoader().getResource("assets/laserdurch2feldlinks.png").toString());
    Image image19 = new Image(getClass().getClassLoader().getResource("assets/2rechtslinks.png").toString());
    Image image20 = new Image(getClass().getClassLoader().getResource("assets/antenne.png").toString());
    Image image21 = new Image(getClass().getClassLoader().getResource("assets/WandRechts.png").toString());
    Image image22 = new Image(getClass().getClassLoader().getResource("assets/ExtraCrispyMap40.png").toString());
    Image image23 = new Image(getClass().getClassLoader().getResource("assets/LaserfeldVertikal.png").toString());
    Image image24 = new Image(getClass().getClassLoader().getResource("assets/RädchenMitUhrzeiger.png").toString());
    Image image25 = new Image(getClass().getClassLoader().getResource("assets/laserdurch2feldlinks.png").toString());
    Image image26 = new Image(getClass().getClassLoader().getResource("assets/WandUnten.png").toString());
    Image image27 = new Image(getClass().getClassLoader().getResource("assets/WandLinks.png").toString());
    Image image28 = new Image(getClass().getClassLoader().getResource("assets/RädchenGegenUnhrzeiger.png").toString());
    Image image29 = new Image(getClass().getClassLoader().getResource("assets/2rechts.png").toString());
    Image image30 = new Image(getClass().getClassLoader().getResource("assets/2AbbiegunLinks.png").toString());
    Image image31 = new Image(getClass().getClassLoader().getResource("assets/2nachOben.png").toString());
    Image image32 = new Image(getClass().getClassLoader().getResource("assets/Ziel2.png").toString());
    Image image33 = new Image(getClass().getClassLoader().getResource("assets/Ziel3.png").toString());
    Image image34 = new Image(getClass().getClassLoader().getResource("assets/2AbbiegunLinksOben.png").toString());
    Image image35 = new Image(getClass().getClassLoader().getResource("assets/ExtraCrispyMapStartBoardA.png").toString());
    Image image36 = new Image(getClass().getClassLoader().getResource("assets/1Unten.png").toString());
    Image image37 = new Image(getClass().getClassLoader().getResource("assets/1Oben.png").toString());
    Image image38 = new Image(getClass().getClassLoader().getResource("assets/1AbbiegungRechts.png").toString());
    Image image39 = new Image(getClass().getClassLoader().getResource("assets/1AbbiegungRechtsOben.png").toString());
    Image image40 = new Image(getClass().getClassLoader().getResource("assets/generatorOffgedreht.png").toString());
    Image image41 = new Image(getClass().getClassLoader().getResource("assets/1LaserWandLinks.png").toString());
    Image image42 = new Image(getClass().getClassLoader().getResource("assets/1LaserWandRechts.png").toString());
    Image image43 = new Image(getClass().getClassLoader().getResource("assets/1AbbiegungRechtsvonLinks.png").toString());
    Image image44 = new Image(getClass().getClassLoader().getResource("assets/1AbbiegungRechtsRechts.png").toString());
    Image image45 = new Image(getClass().getClassLoader().getResource("assets/ExtraCrispyMapStartBoardA.png").toString());
    Image image46 = new Image(getClass().getClassLoader().getResource("assets/LostBearingMapA1.png").toString());
    Image image47 = new Image(getClass().getClassLoader().getResource("assets/1AbbiegungLinks.png").toString());
    Image image48 = new Image(getClass().getClassLoader().getResource("assets/DeathTrapMapFlag5.png").toString());
    Image image49 = new Image(getClass().getClassLoader().getResource("assets/bumpOben.png").toString());
    Image image50 = new Image(getClass().getClassLoader().getResource("assets/WandUnten.png").toString());
    Image image51 = new Image(getClass().getClassLoader().getResource("assets/bumpLinks.png").toString());
    Image image52 = new Image(getClass().getClassLoader().getResource("assets/GeneratorOff.png").toString());
    Image image53 = new Image(getClass().getClassLoader().getResource("assets/generatorOffgedreht.png").toString());
    Image image54 = new Image(getClass().getClassLoader().getResource("assets/Ziel4.png").toString());
    Image image55 = new Image(getClass().getClassLoader().getResource("assets/GeneratorOff.png").toString());
    Image image56 = new Image(getClass().getClassLoader().getResource("assets/bumpUnten1.png").toString());
    Image image57 = new Image(getClass().getClassLoader().getResource("assets/WandLinks.png").toString());
    Image image58 = new Image(getClass().getClassLoader().getResource("assets/1AbbiegungLinksOben.png").toString());
    Image image59 = new Image(getClass().getClassLoader().getResource("assets/1AbbiegungLinksUnten.png").toString());
    Image image60 = new Image(getClass().getClassLoader().getResource("assets/1AbbiegungRechtsLinks.png").toString());
    Image image61 = new Image(getClass().getClassLoader().getResource("assets/Ziel1.png").toString());
    Image image62 = new Image(getClass().getClassLoader().getResource("assets/Ziel3.png").toString());
    Image image63 = new Image(getClass().getClassLoader().getResource("assets/DeathTrapMap2A.png").toString());
    Image image64 = new Image(getClass().getClassLoader().getResource("assets/1AbbiegungLinksRechts.png").toString());
    Image image65 = new Image(getClass().getClassLoader().getResource("assets/Ziel2.png").toString());
    Image image66 = new Image(getClass().getClassLoader().getResource("assets/bumpRechts.png").toString());
    Image image67 = new Image(getClass().getClassLoader().getResource("assets/2nachUnten.png").toString());
    Image image68 = new Image(getClass().getClassLoader().getResource("assets/GeneratorOff.png").toString());
    Image image69 = new Image(getClass().getClassLoader().getResource("assets/2EinleitungRechts.png").toString());
    Image image70 = new Image(getClass().getClassLoader().getResource("assets/2EinleitungOben.png").toString());
    Image image71 = new Image(getClass().getClassLoader().getResource("assets/2nachLinks.png").toString());
    Image image72 = new Image(getClass().getClassLoader().getResource("assets/2EinleitungUnten.png").toString());
    Image image73 = new Image(getClass().getClassLoader().getResource("assets/2EinleitungRechts.png").toString());
    Image image74 = new Image(getClass().getClassLoader().getResource("assets/1LaserWandOben.png").toString());
    Image image75 = new Image(getClass().getClassLoader().getResource("assets/Runtergreen.png").toString());
    Image image76 = new Image(getClass().getClassLoader().getResource("assets/1LaserWandLinks.png").toString());
    Image image77 = new Image(getClass().getClassLoader().getResource("assets/1LaserWandRechts.png").toString());
    Image image78 = new Image(getClass().getClassLoader().getResource("assets/2nachOben.png").toString());
    Image image79 = new Image(getClass().getClassLoader().getResource("assets/Ziel1.png").toString());
    Image image80 = new Image(getClass().getClassLoader().getResource("assets/1LaserWandUnten.png").toString());
    Image image81 = new Image(getClass().getClassLoader().getResource("assets/1LaserWandOben.png").toString());
    Image image82 = new Image(getClass().getClassLoader().getResource("assets/1LaserWandendeLinks.png").toString());
    Image image83 = new Image(getClass().getClassLoader().getResource("assets/1LaserWandRechts.png").toString());
    Image image84 = new Image(getClass().getClassLoader().getResource("assets/1LaserWandendeUnten.png").toString());
    Image image85 = new Image(getClass().getClassLoader().getResource("assets/2nachrechts.png").toString());
    Image image86 = new Image(getClass().getClassLoader().getResource("assets/2EinleitungLinks.png").toString());
    Image image87 = new Image(getClass().getClassLoader().getResource("assets/2EinleitungOben.png").toString());
    Image image88 = new Image(getClass().getClassLoader().getResource("assets/2EinleitungUnten.png").toString());
    Image image89 = new Image(getClass().getClassLoader().getResource("assets/2EinleitungLinks.png").toString());
    Image image90 = new Image(getClass().getClassLoader().getResource("assets/DizzyHighwayMap99.png").toString());
    Image image91 = new Image(getClass().getClassLoader().getResource("assets/GrünFeldLinks.png").toString());
    Image figureTest = new Image(getClass().getClassLoader().getResource("assets/GelbUnten.png").toString());
    Image image92 = new Image(getClass().getClassLoader().getResource("assets/1LaserEndeWandUnten36.png").toString());
    Image image93 = new Image(getClass().getClassLoader().getResource("assets/1LaserEndeWandRechts38.png").toString());
    Image image94 = new Image(getClass().getClassLoader().getResource("assets/2EinleitungObennachlinks18.png").toString());
    Image image95 = new Image(getClass().getClassLoader().getResource("assets/2EinleitungvonRechtsnachoben211.png").toString());
    Image image96 = new Image(getClass().getClassLoader().getResource("assets/2EinleitungUntennachrechts810.png").toString());
    Image image97 = new Image(getClass().getClassLoader().getResource("assets/2EinleitungLinksnachunten74.png").toString());
    Image image98 = new Image(getClass().getClassLoader().getResource("assets/Runtergreen.png").toString());



    //Karten
    Image Karte = new Image(getClass().getClassLoader().getResource("assets/Karte.png").toString());
    @FXML
    Image Move1 = new Image(getClass().getClassLoader().getResource("assets/Move1.png").toString());
    Image Move2 = new Image(getClass().getClassLoader().getResource("assets/Move2Blau.png").toString());
    Image Move3 = new Image(getClass().getClassLoader().getResource("assets/Move3Blau.png").toString());
    Image PowerUp = new Image(getClass().getClassLoader().getResource("assets/PowerUpBlau.png").toString());
    Image RightTurn = new Image(getClass().getClassLoader().getResource("assets/RightTurnBlau.png").toString());
    Image UTurn = new Image(getClass().getClassLoader().getResource("assets/UTurnBlau.png").toString());
    Image LeftTurn = new Image (getClass().getClassLoader().getResource("assets/LeftTurnBlau.png").toString());
    Image BackUp = new Image(getClass().getClassLoader().getResource("assets/MoveBack.png").toString());
    Image Again = new Image(getClass().getClassLoader().getResource("assets/Again.png").toString());

    // Figuren
    //Hammer
    Image HammerOben = new Image(getClass().getClassLoader().getResource("assets/HammerOben.png").toString());
    Image HammerRechts = new Image(getClass().getClassLoader().getResource("assets/HammerRechts.png").toString());
    Image HammerLinks = new Image(getClass().getClassLoader().getResource("assets/HammerLinks.png").toString());
    Image HammerUnten = new Image(getClass().getClassLoader().getResource("assets/HammerUnten.png").toString());
    //Twinky
    Image TwinkyOben = new Image(getClass().getClassLoader().getResource("assets/TwinkyOben.png").toString());
    Image TwinkyRechts = new Image(getClass().getClassLoader().getResource("assets/TwinkyRechts.png").toString());
    Image TwinkyLinks = new Image(getClass().getClassLoader().getResource("assets/TwinkyLinks.png").toString());
    Image TwinkyUnten = new Image(getClass().getClassLoader().getResource("assets/TwinkyUnten.png").toString());
    //Zoom
    Image ZoomOben = new Image(getClass().getClassLoader().getResource("assets/ZoomOben.png").toString());
    Image ZoomRechts = new Image(getClass().getClassLoader().getResource("assets/ZoomRechts.png").toString());
    Image ZoomLinks = new Image(getClass().getClassLoader().getResource("assets/ZoomLinks.png").toString());
    Image ZoomUnten = new Image(getClass().getClassLoader().getResource("assets/ZoomUnten.png").toString());
    //Smash
    Image SmashOben = new Image(getClass().getClassLoader().getResource("assets/SmashOben.png").toString());
    Image SmashRechts = new Image(getClass().getClassLoader().getResource("assets/SmadhRechts.png").toString());
    Image SmashLinks = new Image(getClass().getClassLoader().getResource("assets/SmashLinks.png").toString());
    Image SmashUnten = new Image(getClass().getClassLoader().getResource("assets/SmashUnten.png").toString());
    //Spin
    Image SpinOben = new Image(getClass().getClassLoader().getResource("assets/SpinOben.png").toString());
    Image SpinRechts = new Image(getClass().getClassLoader().getResource("assets/SpinRechts.png").toString());
    Image SpinLinks = new Image(getClass().getClassLoader().getResource("assets/SpinLinks.png").toString());
    Image SpinUnten = new Image(getClass().getClassLoader().getResource("assets/SpinUnten.png").toString());
    //Hulk
    Image HulkOben = new Image(getClass().getClassLoader().getResource("assets/HulkOben.png").toString());
    Image HulkRechts = new Image(getClass().getClassLoader().getResource("assets/HulkRechts.png").toString());
    Image HulkLinks = new Image(getClass().getClassLoader().getResource("assets/HulkLinks.png").toString());
    Image HulkUnten = new Image(getClass().getClassLoader().getResource("assets/HulkUnten.png").toString());

    // Spam Cards
    @FXML
    Image spam = new Image(getClass().getClassLoader().getResource("assets/spam.png").toString());
    Image trojanHorse = new Image(getClass().getClassLoader().getResource("assets/trojanHorse.png").toString());
    Image virus = new Image(getClass().getClassLoader().getResource("assets/virus.png").toString());
    Image worm = new Image(getClass().getClassLoader().getResource("assets/worm.png").toString());


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
                            counter = 0;
                            while (true) {
                                counter++;
                                if (mapGui[x + counter][y].size() == 0) {
                                    gridpane1.add(new ImageView(image23), y, x + counter);
                                }
                                if ((mapGui[x + counter][y].size() != 0) && (mapGui[x + counter][y].get(0).getType().equals("Wall") || (mapGui[x + counter][y].get(1).getType().equals("Wall"))) && (mapGui[x + counter][y].get(0).getOrientations()[0].equals("top") || mapGui[x + counter][y].get(0).getOrientations()[0].equals("bottom"))) {
                                    gridpane1.add(new ImageView(image84), y, x + counter);
                                    break;
                                }
                            }
                            laserPosition.put("Orientation", "bottom");
                            break;
                        case "top":
                            gridpane1.add(new ImageView(image80), y, x);
                            counter = 0;
                            while (true) {
                                counter++;
                                if (mapGui[x - counter][y].size() == 0) {
                                    gridpane1.add(new ImageView(image23), y, x - counter);
                                }
                                if ((mapGui[x-counter][y].size() != 0) && (mapGui[x - counter][y].get(0).getType().equals("Wall")  || (mapGui[x - counter][y].get(1).getType().equals("Wall"))) && (mapGui[x - 1][y].get(0).getOrientations()[0].equals("top") || mapGui[x - 1][y].get(0).getOrientations()[0].equals("bottom"))) {
                                    gridpane1.add(new ImageView(image92), y, x - counter);
                                    break;
                                }
                            }
                            laserPosition.put("Orientation", "top");
                            break;
                        case "right":
                            gridpane1.add(new ImageView(image41), y, x);
                            counter = 0;
                            while (true) {
                                counter++;
                                if (mapGui[x][y + counter].size() == 0) {
                                    gridpane1.add(new ImageView(image7), y + counter, x);
                                }
                                if ((mapGui[x][y + counter].size() != 0) && ((mapGui[x][y + counter].get(0).getType().equals("Wall") || (mapGui[x][y + counter].get(1).getType().equals("Wall"))) || mapGui[x][y + counter].get(1).getType().equals("Wall"))&& (mapGui[x][y + counter].get(0).getOrientations()[0].equals("right") || mapGui[x][y + counter].get(0).getOrientations()[0].equals("left"))) {
                                   gridpane1.add(new ImageView(image93), y + counter, x);
                                    if ((mapGui[x][y + counter].size() > 1) && mapGui[x][y + counter].get(1).getType().equals("Wall")) {
                                        //hii
                                        gridpane1.add(new ImageView(image6), y + counter, x);
                                    }
                                    break;
                                }
                            }
                            laserPosition.put("Orientation", "right");
                            break;
                        case "left":
                            gridpane1.add(new ImageView(image8), y, x);
                            counter = 0;
                            while (true) {
                                counter++;
                                if (mapGui[x][y - counter].size() == 0) {
                                    gridpane1.add(new ImageView(image7), y - counter, x);
                                }
                                if ((mapGui[x][y - counter].size() != 0) && ((mapGui[x][y - counter].get(0).getType().equals("Wall")) || (mapGui[x][y - counter].get(1).getType().equals("Wall"))) && (mapGui[x][y - counter].get(0).getOrientations()[0].equals("right") || mapGui[x][y - counter].get(0).getOrientations()[0].equals("left"))) {
                                    gridpane1.add(new ImageView(image82), y - counter, x);
                                        if ((mapGui[x][y - counter].size() > 1) && mapGui[x][y - counter].get(1).getType().equals("Wall")) {
                                            //hii
                                            gridpane1.add(new ImageView(image6), y - counter, x);
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
                            }
                            //luuu
                            if (mapGui[x + counter][y].size() != 0 && Objects.equals(mapGui[x + counter][y].get(0).getType(), "ConveyorBelt")) {
                                if (Objects.equals(mapGui[x + counter][y].get(0).getOrientations()[0], "left") && Objects.equals(mapGui[x + counter][y].get(0).getOrientations()[1], "right")) {
                                    gridpane1.add(new ImageView(image18), y, x + counter);
                                }

                                if (Objects.equals(mapGui[x + counter][y].get(0).getOrientations()[0], "right") && Objects.equals(mapGui[x + counter][y].get(0).getOrientations()[1], "left")) {
                                    gridpane1.add(new ImageView(image17), y, x + counter);
                                }
                            }
                        if (mapGui[x + counter][y].size() == 3 && mapGui[x + counter][y].get(2).getCount() == 3) {
                            gridpane1.add(new ImageView(image13), y, x + counter);
                            break;
                        }
                        if (mapGui[x + counter][y].size() == 3 && mapGui[x + counter][y].get(2).getCount() == 0){
                            gridpane1.add(new ImageView(image15), y, x + counter);
                            break;
                        }
                            if (mapGui[x + counter][y].size() == 3 && mapGui[x + counter][y].get(2).getCount() == 1) {
                                gridpane1.add(new ImageView(image32), y, x + counter);
                                break;
                            }
                            if (mapGui[x + counter][y].size() == 3 && mapGui[x + counter][y].get(2).getCount() == 2){
                                gridpane1.add(new ImageView(image33), y, x + counter);
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
                            }
                            //luuu
                            if (mapGui[x - counter][y].size() != 0 && Objects.equals(mapGui[x - counter][y].get(0).getType(), "ConveyorBelt")) {
                                gridpane1.add(new ImageView(image18), y, x - counter);
                                if (Objects.equals(mapGui[x - counter][y].get(0).getOrientations()[0], "left") && Objects.equals(mapGui[x - counter][y].get(0).getOrientations()[1], "right")) {
                                    gridpane1.add(new ImageView(image18), y, x - counter);
                                }
                                if (Objects.equals(mapGui[x - counter][y].get(0).getOrientations()[0], "right") && Objects.equals(mapGui[x - counter][y].get(0).getOrientations()[1], "left")) {
                                    gridpane1.add(new ImageView(image17), y, x - counter);
                                }
                            }
                            if (mapGui[x - counter][y].size() == 3 && mapGui[x -counter][y].get(2).getCount() == 1) {
                                gridpane1.add(new ImageView(image32), y, x - counter);
                                break;
                            }
                            if (mapGui[x - counter][y].size() == 3 && mapGui[x - counter][y].get(2).getCount() == 2){
                                gridpane1.add(new ImageView(image33), y, x - counter);
                                break;
                            }
                            if (mapGui[x - counter][y].size() == 3 && mapGui[x - counter][y].get(2).getCount() == 3) {
                                gridpane1.add(new ImageView(image13), y, x - counter);
                                break;
                            }
                            if (mapGui[x - counter][y].size() == 3 && mapGui[x - counter][y].get(2).getCount() == 0){
                                gridpane1.add(new ImageView(image15), y, x - counter);
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
        CubesText.setText("Energy cubes: " + SaveClients.client.getCubesZahl());
        DirectionRechts.setVisible(false);
        DirectionLinks.setVisible(false);
        DirectionOben.setVisible(false);
        DirectionUnten.setVisible(false);
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
        gridpane1.setHgap(-80);

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
                    if (Objects.equals(mapGui[x][y].get(0).getType(), "EnergySpace")) {
                        // Muss evtl. noch weiter angepasst werden
                        gridpane1.add(new ImageView(image52),y,x);
                        //hiiiiii
                        if (mapGui[x][y].size() == 2 && Objects.equals(mapGui[x][y].get(1).getType(), "Wall")){
                            gridpane1.add(new ImageView(image22),y,x);
                        }
                    }
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
                    if (Objects.equals(mapGui[x][y].get(0).getType(), "Gear")) {
                        if (mapGui[x][y].get(0).getOrientations()[0].equals("clockwise")) {
                            gridpane1.add(new ImageView(image24), y, x);
                        }
                        else {
                            gridpane1.add(new ImageView(image28), y, x);
                        }
                    }
                    if (Objects.equals(mapGui[x][y].get(0).getType(), "Antenna")) {
                        // Muss evtl. noch weiter angepasst werden
                        gridpane1.add(new ImageView(image20),y,x);
                    }
                    if (Objects.equals(mapGui[x][y].get(0).getType(), "EnergySpace")) {
                        // Muss evtl. noch weiter angepasst werden
                        gridpane1.add(new ImageView(image52),y,x);
                        //hiiiiii
                        if (mapGui[x][y].size() == 2 && Objects.equals(mapGui[x][y].get(1).getType(), "Wall")){
                            gridpane1.add(new ImageView(image22),y,x);
                        }
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
                            gridpane1.add(new ImageView(image98),y,x);
                        }
                        if (Objects.equals(mapGui[x][y].get(0).getIsOnBoard(), "ExtraCrispy")) {
                            gridpane1.add(new ImageView(image0),y,x);
                        }
                        if (Objects.equals(mapGui[x][y].get(0).getIsOnBoard(), "LostBearings")) {
                            gridpane1.add(new ImageView(image0),y,x);
                        }
                    }
                    if (Objects.equals(mapGui[x][y].get(0).getType(), "ConveyorBelt")){
                        if (mapGui[x][y].get(0).getOrientations().length == 3){
                            if ((Objects.equals(mapGui[x][y].get(0).getOrientations()[0], "left" )) && (Objects.equals(mapGui[x][y].get(0).getOrientations()[1], "top")) && (Objects.equals(mapGui[x][y].get(0).getOrientations()[2], "right" ))){
                                gridpane1.add(new ImageView(image94),y,x);
                                //falsches Bild [1][5] (DizzyHighway)
                            }
                            if ((Objects.equals(mapGui[x][y].get(0).getOrientations()[0], "bottom" )) && (Objects.equals(mapGui[x][y].get(0).getOrientations()[1], "top")) && (Objects.equals(mapGui[x][y].get(0).getOrientations()[2], "right" ))){
                                gridpane1.add(new ImageView(image69),y,x);
                            }
                            if ((Objects.equals(mapGui[x][y].get(0).getOrientations()[0], "left" )) && (Objects.equals(mapGui[x][y].get(0).getOrientations()[1], "right")) && (Objects.equals(mapGui[x][y].get(0).getOrientations()[2], "bottom" ))){
                                gridpane1.add(new ImageView(image72),y,x);
                            }
                            if ((Objects.equals(mapGui[x][y].get(0).getOrientations()[0], "top" )) && (Objects.equals(mapGui[x][y].get(0).getOrientations()[1], "right")) && (Objects.equals(mapGui[x][y].get(0).getOrientations()[2], "bottom" ))){
                                gridpane1.add(new ImageView(image95),y,x);
                                //Falsches Bild [2][11] (DizzyHighway)
                            }
                            if ((Objects.equals(mapGui[x][y].get(0).getOrientations()[0], "bottom" )) && (Objects.equals(mapGui[x][y].get(0).getOrientations()[1], "left")) && (Objects.equals(mapGui[x][y].get(0).getOrientations()[2], "top" ))){
                                gridpane1.add(new ImageView(image97),y,x);
                                //Falsches Bild[7][4] (DizzyHighway)
                            }
                            if ((Objects.equals(mapGui[x][y].get(0).getOrientations()[0], "right" )) && (Objects.equals(mapGui[x][y].get(0).getOrientations()[1], "left")) && (Objects.equals(mapGui[x][y].get(0).getOrientations()[2], "top" ))){
                                gridpane1.add(new ImageView(image87),y,x);
                            }
                            if ((Objects.equals(mapGui[x][y].get(0).getOrientations()[0], "right" )) && (Objects.equals(mapGui[x][y].get(0).getOrientations()[1], "bottom")) && (Objects.equals(mapGui[x][y].get(0).getOrientations()[2], "left" ))){
                                gridpane1.add(new ImageView(image96),y,x);
                                //Falsches Bild [8][10] (DizzyHighway)
                            }
                            if ((Objects.equals(mapGui[x][y].get(0).getOrientations()[0], "top" )) && (Objects.equals(mapGui[x][y].get(0).getOrientations()[1], "bottom")) && (Objects.equals(mapGui[x][y].get(0).getOrientations()[2], "left" ))){
                                gridpane1.add(new ImageView(image89),y,x);
                            }
                        }
                        else {
                            if (Objects.equals(mapGui[x][y].get(0).getOrientations()[0], "top") && Objects.equals(mapGui[x][y].get(0).getOrientations()[1], "right")) {
                                if (mapGui[x][y].get(0).getSpeed() == 1) {
                                    gridpane1.add(new ImageView(image39), y, x);
                                } else {
                                    gridpane1.add(new ImageView(image16), y, x);
                                }
                            }
                            if (Objects.equals(mapGui[x][y].get(0).getOrientations()[0], "right") && Objects.equals(mapGui[x][y].get(0).getOrientations()[1], "bottom")) {
                                if (mapGui[x][y].get(0).getSpeed() == 1) {
                                    gridpane1.add(new ImageView(image44), y, x);
                                } else {
                                    gridpane1.add(new ImageView(image29), y, x);
                                }
                            }
                            if (Objects.equals(mapGui[x][y].get(0).getOrientations()[0], "right") && Objects.equals(mapGui[x][y].get(0).getOrientations()[1], "left")) {
                                if (mapGui[x][y].get(0).getSpeed() == 1) {
                                    gridpane1.add(new ImageView(image2), y, x);
                                } else {
                                    gridpane1.add(new ImageView(image85), y, x);
                                }
                            }
                            if (Objects.equals(mapGui[x][y].get(0).getOrientations()[0], "right") && Objects.equals(mapGui[x][y].get(0).getOrientations()[1], "top")) {
                                if (mapGui[x][y].get(0).getSpeed() == 1) {
                                    gridpane1.add(new ImageView(image64), y, x);
                                } else {
                                    gridpane1.add(new ImageView(image16), y, x);
                                }
                            }
                            if (Objects.equals(mapGui[x][y].get(0).getOrientations()[0], "bottom") && Objects.equals(mapGui[x][y].get(0).getOrientations()[1], "left")) {
                                if (mapGui[x][y].get(0).getSpeed() == 1) {
                                    gridpane1.add(new ImageView(image43), y, x);
                                } else {
                                    // kaaa
                                    gridpane1.add(new ImageView(image30), y, x);
                                }
                            }
                            if (Objects.equals(mapGui[x][y].get(0).getOrientations()[0], "bottom") && Objects.equals(mapGui[x][y].get(0).getOrientations()[1], "top")) {
                                if (mapGui[x][y].get(0).getSpeed() == 1) {
                                    gridpane1.add(new ImageView(image36), y, x);
                                } else {
                                    gridpane1.add(new ImageView(image4), y, x);
                                }
                            }
                            if (Objects.equals(mapGui[x][y].get(0).getOrientations()[0], "bottom") && Objects.equals(mapGui[x][y].get(0).getOrientations()[1], "right")) {
                                if (mapGui[x][y].get(0).getSpeed() == 1) {
                                    gridpane1.add(new ImageView(image59), y, x);
                                } else {
                                    // wurde noch nicht bearbeitet, wird wahrscheinlich auch nicht benutzt
                                    gridpane1.add(new ImageView(image4), y, x);
                                }
                            }
                            if (Objects.equals(mapGui[x][y].get(0).getOrientations()[0], "top") && Objects.equals(mapGui[x][y].get(0).getOrientations()[1], "bottom")) {
                                if (mapGui[x][y].get(0).getSpeed() == 1) {
                                    gridpane1.add(new ImageView(image37), y, x);
                                } else {
                                    gridpane1.add(new ImageView(image31), y, x);
                                }
                            }
                            if (Objects.equals(mapGui[x][y].get(0).getOrientations()[0], "top") && Objects.equals(mapGui[x][y].get(0).getOrientations()[1], "left")) {
                                if (mapGui[x][y].get(0).getSpeed() == 1) {
                                    gridpane1.add(new ImageView(image58), y, x);
                                } else {
                                    // wurde noch nicht bearbeitet, wird wahrscheinlich auch nicht benutzt
                                    gridpane1.add(new ImageView(image31), y, x);
                                }
                            }
                            if (Objects.equals(mapGui[x][y].get(0).getOrientations()[0], "left") && Objects.equals(mapGui[x][y].get(0).getOrientations()[1], "right")) {
                                if (mapGui[x][y].get(0).getSpeed() == 1) {
                                    gridpane1.add(new ImageView(image11), y, x);
                                } else {
                                    gridpane1.add(new ImageView(image71), y, x);
                                }
                            }
                            if (Objects.equals(mapGui[x][y].get(0).getOrientations()[0], "left") && Objects.equals(mapGui[x][y].get(0).getOrientations()[1], "bottom")) {
                                if (mapGui[x][y].get(0).getSpeed() == 1) {
                                    gridpane1.add(new ImageView(image47), y, x);
                                } /*else {
                                //was wenn es blau ist!!!!
                                gridpane1.add(new ImageView(image0),y,x);
                            }*/
                            }
                            if (Objects.equals(mapGui[x][y].get(0).getOrientations()[0], "left") && Objects.equals(mapGui[x][y].get(0).getOrientations()[1], "top")) {
                                if (mapGui[x][y].get(0).getSpeed() == 1) {
                                    gridpane1.add(new ImageView(image60), y, x);
                                } else {
                                    gridpane1.add(new ImageView(image19), y, x);
                                }
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

    public void runUpgradeCards() throws IOException {
        Stage stage = new Stage();
        stage.setTitle("Upgrade cards");
        Parent signIn = FXMLLoader.load(getClass().getResource("fxml/UpgradeCards.fxml"));
        Scene signInScene = new Scene(signIn);
        stage.setScene(signInScene);
        stage.show();
        stage.setOnCloseRequest(e -> Platform.exit());
    }

}
