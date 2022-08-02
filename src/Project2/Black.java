package Project2;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Black extends Pane {
    private Rectangle rectangle;
    int pixel = 64;
    public Black(){
        this.setMaxHeight(pixel);
        this.setMaxWidth(pixel);
        rectangle = new Rectangle(pixel,pixel);
        rectangle.setFill(Color.BLACK);
        getChildren().add(rectangle);
    }
}
