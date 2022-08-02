package Project2;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.io.FileNotFoundException;
/**
 *
createing bullet
 * **/
public class Bullet extends Pane {
    Position position;
    char dircetion;
    Map map;
    private Circle c;
    public Bullet(){
        this.setMaxHeight(4);
        this.setMaxWidth(4);
        c = new Circle(4);
        c.setFill(Color.YELLOW);
        this.getChildren().add(c);

    }
    public Bullet(Position position){
        this.position = position;
        this.setMaxHeight(6);
        this.setMaxWidth(6);
        c = new Circle(4);
        c.setFill(Color.RED);
        getChildren().add(c);
    }
    public void setMap(Map map){
        this.map = map;
    }
    public void posBullet() throws FileNotFoundException {
        switch (new Main().getDirection()){
            case 'L':
                break;
            case 'U':
                break;
            case 'D':
                break;
            case 'R':
                break;
        }
    }
    public void shootDown(){

    }
    public void shootLeft() {
    }
    public void shootRight() {
    }
    public void shootUp() {
    }
    }

