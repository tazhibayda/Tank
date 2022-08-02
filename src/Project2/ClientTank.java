package Project2;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.File;

public class ClientTank extends Pane  {
    private ImageView ivi;
    Position position ;
    Map map;
    private int health;
    public ClientTank(){
        ivi = new ImageView(new Image(new File("src/images/cli.gif").toURI().toString()));
        ivi.setFitHeight(64);
        ivi.setFitWidth(64);
        getChildren().addAll(ivi);
    }

    public void setMap(Map map) {
    this.map = map;
    boolean check = false;
    while (!check){
        int l = (int)(Math.random() * map.length);
        int d = (int)(Math.random() * map.length);
        if(map.getValueAt(l,d) == '0'){
            this.position = new Position(l,d);
            check = true;
        }
    }
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void moveUp(){
        setRotate(0);
        if (this.position.y - 1 >= 0) {
            if (map.getValueAt(this.position.x, this.position.y - 1) == 'T') {
                setVisible(false);
            }
            if (map.getValueAt(this.position.x, this.position.y - 1) != 'T') {
                setVisible(true);
            }
            if (map.getValueAt(this.position.x, this.position.y - 1) != 'S' && map.getValueAt(this.position.x,this. position.y - 1) != 'E' && map.getValueAt(this.position.x,this. position.y - 1) != 'B' && map.getValueAt(this.position.x, this.position.y - 1) != 'W') {
                this.position = new Position(this.position.y - 1, this.position.x);
                setLayoutY(this.position.getY() * 64);
            }
            System.out.println("up " + this.position.getX() + " " +this.position.getY());
        }
    }
    public void moveLeft(){
        this.setRotate(-90);
        if (this.position.x - 1 >= 0) {
            if (map.getValueAt(this.position.x - 1, this.position.y) == 'T') {
                this.setVisible(false);
            }
            if (map.getValueAt(this.position.x - 1, this.position.y) != 'T') {
                this.setVisible(true);
            }
            if (map.getValueAt(this.position.x - 1, this.position.y) != 'S' && map.getValueAt(this.position.x - 1,
                    this.position.y) != 'E' && map.getValueAt(this.position.x - 1, this.position.y) != 'B' && map.getValueAt(this.position.x - 1, this.position.y) != 'W') {
                this.position = new Position(this.position.y, this.position.x - 1);
                this.setLayoutX(this.getPosition().x * 64);
            }
        }
        System.out.println(this.getPosition().x + " " + this.position.getY());
    }
    public void moveRight(){
        this.setRotate(90);
        if (this.position.x + 1 < map.length) {
            if (map.getValueAt(this.position.x + 1, this.position.y) == 'T') {
                this.setVisible(false);
            }
            if (map.getValueAt(this.position.x + 1, this.position.y) != 'T') {
                this.setVisible(true);
            }
            if (map.getValueAt(this.position.x + 1, this.position.y) != 'S' && map.getValueAt(this.position.x + 1, this.position.y)
                    != 'E' && map.getValueAt(this.position.x + 1, this.position.y) != 'B' && map.getValueAt(this.position.x + 1, this.position.y) != 'W') {
                this.position = new Position(this.position.y, this.position.x + 1);
                this.setLayoutX(this.getPosition().x * 64);
            }
        }
        System.out.println(this.getPosition().x + " " + this.position.getY());
    }
    public void moveDown(){
        this.setRotate(180);
        if (this.position.y + 1 < map.length) {
            if (map.getValueAt(this.position.x, this.position.y + 1) == 'T') {
                this.setVisible(false);
            }
            if (map.getValueAt(this.position.x, this.position.y + 1) != 'T') {
                this.setVisible(true);
            }
            if (map.getValueAt(this.position.x, this.position.y + 1) != 'S' && map.getValueAt(this.position.x, this.position.y + 1) != 'E'
                    && map.getValueAt(this.position.x, this.position.y + 1) != 'B' && map.getValueAt(this.position.x, this.position.y + 1) != 'W') {
                this.position = new Position(this.position.y + 1, this.position.x);
                this.setLayoutY(this.position.getY() * 64);
            }
            System.out.println("down " + this.position.getX() + " " + this.position.getY());
        }
    }
    public Position getPosition() {
        return position;
    }
}