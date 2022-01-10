import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

import java.awt.*;

public class TryingToMakeAMap {
   public Stage stage;
   private TilePane tilePane;
   private Scene scene;
   private ImageView[] imageView;
   private Image image;

   public TryingToMakeAMap(Stage stage){
      this.stage = stage;
      buildUI();
   }
   private void buildUI() {
      // Create TilePane
      tilePane = new TilePane();
      // add child Nodes to the TilePane
      for (int i = 0; i < 10; i++){
         ImageView imageView1 = new ImageView();
         
      }
   }
}
