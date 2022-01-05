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
    public ObservableList<Object> usernamesGui = FXCollections.observableArrayList();
    @FXML
    private GridPane gridpane1;
    @FXML
    int[][] crispyMapArray = new int[][]{
            { 0, 1, 2 ,3 ,4 ,1 ,1, 1, 5, 6, 7, 8, 1},
            { 1, 9, 1, 1, 4, 1, 10, 11, 1, 1, 1, 4, 1},
            { 1, 12, 1, 1, 4, 13, 14, 1, 1, 14, 15, 4, 1},
            { 9, 1, 1, 1, 16, 17, 14, 6, 8, 14, 18, 19, 1},
            { },
            { 3, 1, 12, 19, 4, 1, 22, 12, 1, 3 },
            { 1, 7, 11, 17, 9, 18, 17, 25, 29, 1 },
            { 1, 8, 14, 17, 1, 1, 17, 26, 30, 1 },
            { 1, 8, 1, 1, 1, 20, 23, 1, 30, 1 },
            { 4, 9, 15, 18, 1, 1, 1, 1, 30, 1 }

    };
    @FXML
    Image image0 = new Image("assets/ExtraCrispyMap00.png");
    Image image1 = new Image("assets/ExtraCrispyMap10.png");
    Image image2 = new Image("assets/ExtraCrispyMap40.png");
    Image image3 = new Image("assets/ExtraCrispyMap50.png");
    Image image4 = new Image("assets/ExtraCrispyMap100.png");
    Image image5 = new Image("assets/ExtraCrispyMap01.png");
    Image image6 = new Image("assets/ExtraCrispyMap41.png");
    Image image7 = new Image("assets/ExtraCrispyMap71.png");
    Image image8 = new Image("assets/ExtraCrispyMap81.png");
    Image image9 = new Image("assets/ExtraCrispyMap91.png");
    Image image10 = new Image("assets/ExtraCrispyMap32.png");
    Image image11 = new Image("assets/ExtraCrispyMap42.png");
    Image image12 = new Image("assets/ExtraCrispyMap52.png");
    Image image14 = new Image("assets/ExtraCrispyMap72.png");
    Image image15 = new Image("assets/ExtraCrispyMap92.png");
    Image image16 = new Image("assets/ExtraCrispyMap13.png");
    Image image17 = new Image("assets/ExtraCrispyMap23.png");
    Image image18 = new Image("assets/ExtraCrispyMap43.png");
    Image image19 = new Image("assets/ExtraCrispyMap53.png");
    Image image20 = new Image("assets/ExtraCrispyMap85.png");
    Image image21 = new Image("assets/ExtraCrispyMap46.png");
    Image image22 = new Image("assets/ExtraCrispyMap56.png");
    Image image23 = new Image("assets/ExtraCrispyMap86.png");
    Image image24 = new Image("assets/ExtraCrispyMap27.png");
    Image image25 = new Image("assets/ExtraCrispyMap37.png");
    Image image26 = new Image("assets/ExtraCrispyMap77.png");
    Image image27 = new Image("assets/ExtraCrispyMap18.png");
    Image image28 = new Image("assets/ExtraCrispyMap38.png");
    Image image29 = new Image("assets/ExtraCrispyMap68.png");
    Image image30 = new Image("assets/ExtraCrispyMap78.png");
    Image image31 = new Image("assets/ExtraCrispyMap49.png");
    Image figureTest = new Image("assets/figureTest2.png");
    //Image testImage = new Image("assets/MateoTry.jpeg");

    // Array
    //int[][] crispyMapArray = new int[10][10];


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        usernamesGui.add(crispyMapArray);
        Image image = new Image("assets/ExtraCrispyMap27.png");
        ImageView imageViewForAll = new ImageView();

        for (int i = 0; i < crispyMapArray.length; i++){
            for (int j = 0; j < crispyMapArray.length; j++){
                gridpane1.setHgap(-80);
                //gridpane1.setVgap(10);
                gridpane1.add(new ImageView(getImageForMap(crispyMapArray[i][j])),j,i);
                //gridpane1.add(new ImageView(testImage),j,i);
            }
        }
        gridpane1.add(new ImageView(figureTest),0,0);

    }

    public void moveFigureTest(){
       gridpane1.add(new ImageView(image0),0,0);
       gridpane1.add(new ImageView(figureTest),1,0);
       /* besmellaUpdatingForAllView.changeMap();
        crispyMapArray = besmellaUpdatingForAllView.getCrispyMapArray();
        for (int i = 0; i < crispyMapArray.length; i++){
            for (int j = 0; j < crispyMapArray.length; j++){
                gridpane1.setHgap(-80);
                //gridpane1.setVgap(10);
                gridpane1.add(new ImageView(getImageForMap(crispyMapArray[i][j])),j,i);
                //gridpane1.add(new ImageView(testImage),j,i);
            }
        } */

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
            System.out.println("Mouse clicked cell: " + colIndex + " And: " + rowIndex);
        }
    }

    public void takeStartingPointZero(){
        SaveClients.client.printMessage("ya rab ya habibi");
    }


}
