package Project2;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Wall extends Pane {
    private ImageView image;
    private int width,height;
    public Wall(ImageView ivi){
        this.image = ivi;
        this.setMaxHeight(64);
        this.setMaxWidth(64);

        getChildren().add(ivi);
    }
}
