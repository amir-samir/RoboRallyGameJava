import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.image.Image ;
import javafx.scene.layout.RowConstraints;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class MaybeMapsController implements Initializable {
    @FXML
    private GridPane gridpane1;
    @FXML
    private ImageView Card1;
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
            { 63, 1, 1, 1, 1, 64, 2, 2, 2, 1, 11, 1, 0}

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
    Image image0 = new Image("assets/ExtraCrispyMapStartBoard00.png");
    Image image1 = new Image("assets/ExtraCrispyMap10.png");
    Image image2 = new Image("assets/ExtraCrispyMapStartBorad0last.png");
    Image image3 = new Image("assets/ExtraCrispyMap00.png");
    Image image4 = new Image("assets/ExtraCrispyMap01.png");
    Image image5 = new Image("assets/ExtraCrispyMap100.png");
    Image image6 = new Image("assets/ExtraCrispyMap91.png");
    Image image7 = new Image("assets/ExtraCrispyMap92.png");
    Image image8 = new Image("assets/ExtraCrispyMap011.png");
    Image image9 = new Image("assets/ExtraCrispyMapStartBoardStartPoint.png");
    Image image10 = new Image("assets/ExtraCrispyMap13.png");
    Image image11 = new Image("assets/ExtraCrispyMap14.png");
    Image image12 = new Image("assets/ExtraCrispyMapStartBoard21.png");
    Image image13 = new Image("assets/ExtraCrispyMap32.png");
    Image image14 = new Image("assets/ExtraCrispyMap23.png");
    Image image15 = new Image("assets/ExtraCrispyMap27.png");
    Image image16 = new Image("assets/ExtraCrispyMap41.png");
    Image image17 = new Image("assets/ExtraCrispyMap42.png");
    Image image18 = new Image("assets/ExtraCrispyMapNumber18.png");
    Image image19 = new Image("assets/ExtraCrispyMap38.png");
    Image image20 = new Image("assets/ExtraCrispyMapStartBoardAntenne.png");
    Image image21 = new Image("assets/ExtraCrispyMap46.png");
    Image image22 = new Image("assets/ExtraCrispyMap40.png");
    Image image23 = new Image("assets/ExtraCrispyMap52.png");
    Image image24 = new Image("assets/ExtraCrispyMap43.png");
    Image image25 = new Image("assets/ExtraCrispyMap37.png");
    Image image26 = new Image("assets/ExtraCrispyMap50.png");
    Image image27 = new Image("assets/ExtraCrispyMap53.png");
    Image image28 = new Image("assets/ExtraCrispyMap56.png");
    Image image29 = new Image("assets/ExtraCrispyMap71.png");
    Image image30 = new Image("assets/ExtraCrispyMap68.png");
    Image image31 = new Image("assets/ExtraCrispyMap81.png");
    Image image32 = new Image("assets/ExtraCrispyMap72.png");
    Image image33 = new Image("assets/ExtraCrispyMap77.png");
    Image image34 = new Image("assets/ExtraCrispyMap86.png");
    Image image35 = new Image("assets/ExtraCrispyMapStartBoardA.png");
    Image image36 = new Image("assets/LostBearingMapGreenPfileNachUnten.png");
    Image image37 = new Image("assets/LostBearingMapGreenPfileNachOben.png");
    Image image38 = new Image("assets/LostBearingMapGreenPfileTurnRight.png");
    Image image39 = new Image("assets/LostBearingMapGreenPfeilTurnRightNachOben.png");
    Image image40 = new Image("assets/LostBearingMapDoubleEnergy.png");
    Image image41 = new Image("assets/LostBearingMapLaserWall.png");
    Image image42 = new Image("assets/LostBearingMapLaserWall42.png");
    Image image43 = new Image("assets/LostBearingMapGreenPfileTurnRightNachUnten.png");
    Image image44 = new Image("assets/LostBearingMapGreenTurnRightLast.png");
    Image image45 = new Image("assets/ExtraCrispyMapStartBoardA.png");
    Image image46 = new Image("assets/LostBearingMapA1.png");
    Image image47 = new Image("assets/DeathTrapMap04.png");
    Image image48 = new Image("assets/DeathTrapMapFlag5.png");
    Image image49 = new Image("assets/DeathTrapMap11.png");
    Image image50 = new Image("assets/ExtraCrispyMap50.png");
    Image image51 = new Image("assets/DeathTrapMap22.png");
    Image image52 = new Image("assets/DeathTrapMap24.png");
    Image image53 = new Image("assets/DeathTrapMap26.png");
    Image image54 = new Image("assets/DeathTrapMapFlag4.png");
    Image image55 = new Image("assets/DeathTrapMap32.png");
    Image image56 = new Image("assets/DeathTrapMap34.png");
    Image image57 = new Image("assets/DeathTrapMap38.png");
    Image image58 = new Image("assets/DeathTrapMap49.png");
    Image image59 = new Image("assets/DeathTrapMap50.png");
    Image image60 = new Image("assets/DeathTrapMap51.png");
    Image image61 = new Image("assets/DeathTrapMapFlag1.png");
    Image image62 = new Image("assets/DeathTrapMapFlag3.png");
    Image image63 = new Image("assets/DeathTrapMap2A.png");
    Image image64 = new Image("assets/DeathTrapMap95.png");
    Image image65 = new Image("assets/DeathTrapMapFlag2.png");
    Image image66 = new Image("assets/DeathTrapMap18.png");
    Image image67 = new Image("assets/DizzyHighwayMap01.png");
    Image image68 = new Image("assets/DizzyHighwayMap09.png");
    Image image69 = new Image("assets/DizzyHighwayMap11.png");
    Image image70 = new Image("assets/DizzyHighwayMap12.png");
    Image image71 = new Image("assets/DizzyHighwayMap13.png");
    Image image72 = new Image("assets/DizzyHighwayMap18.png");
    Image image73 = new Image("assets/DizzyHighwayMap28.png");
    Image image74 = new Image("assets/DizzyHighwayMap33.png");
    Image image75 = new Image("assets/DizzyHighwayMap34.png");
    Image image76 = new Image("assets/DizzyHighwayMap35.png");
    Image image77 = new Image("assets/DizzyHighwayMap36.png");
    Image image78 = new Image("assets/DizzyHighwayMap38.png");
    Image image79 = new Image("assets/DeathTrapMapFlag1.png");
    Image image80 = new Image("assets/DizzyHighwayMap43.png");
    Image image81 = new Image("assets/DizzyHighwayMap56.png");
    Image image82 = new Image("assets/DizzyHighwayMap63.png");
    Image image83 = new Image("assets/DizzyHighwayMap64.png");
    Image image84 = new Image("assets/DizzyHighwayMap66.png");
    Image image85 = new Image("assets/DizzyHighwayMap70.png");
    Image image86 = new Image("assets/DizzyHighwayMap71.png");
    Image image87 = new Image("assets/DizzyHighwayMap81.png");
    Image image88 = new Image("assets/DizzyHighwayMap87.png");
    Image image89 = new Image("assets/DizzyHighwayMap88.png");
    Image image90 = new Image("assets/DizzyHighwayMap99.png");
    Image figureTest = new Image("assets/figureTest2.png");
    //Image testImage = new Image("assets/MateoTry.jpeg");

    // Array
    //int[][] crispyMapArray = new int[10][10];


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image image = new Image("assets/ExtraCrispyMap27.png");
        ImageView imageViewForAll = new ImageView();
        setDefaultMap();
        /*for (int i = 0; i < gridpane1.getRowCount(); i++){
            for (int j = 0; j < gridpane1.getColumnCount(); j++){
                gridpane1.setHgap(-80);
                //gridpane1.setVgap(10);
                gridpane1.add(new ImageView(getImageForMap(DizzyHighwayMap[i][j])),j,i);
                //gridpane1.add(new ImageView(testImage),j,i);
            }
        }*/
      Card1.setImage(image);

    }

    public void setDefaultMap(){
        if (SaveClients.client.getSelectedMap().equals("DizzyHighway")){
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
        if (SaveClients.client.getSelectedMap().equals("LostBearing")){
            for (int i = 0; i < gridpane1.getRowCount(); i++){
                for (int j = 0; j < gridpane1.getColumnCount(); j++){
                    gridpane1.setHgap(-80);
                    //gridpane1.setVgap(10);
                    gridpane1.add(new ImageView(getImageForMap(LostBearingArray[i][j])),j,i);
                    //gridpane1.add(new ImageView(testImage),j,i);
                }
            }
        }
    }

    public void moveFigureTest(){
        gridpane1.add(new ImageView(image0),0,0);
        gridpane1.add(new ImageView(figureTest),1,0);
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
            gridpane1.add(new ImageView(figureTest),colIndex,rowIndex);
           SaveClients.client.setStartingPoint(rowIndex,colIndex);
        }
    }

    public void ChangeImageTest(){
        Card1.setImage(image4);
    }
    //TODO: Method for inserting ein Figure auf dem Map, wird von Client gerufen, und nimmt 4 Parametern(Figure, Direction, X, Y)





}
