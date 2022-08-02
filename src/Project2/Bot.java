package Project2;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.util.Duration;

import java.io.File;
import java.util.Random;

public class Bot implements Player,Runnable{
    Map map;
    int time = 1 , pixel = 64;
    Pane pane;
    private boolean isAlive;
    BotPosition botPosition;
    Position pos;
    ImageView imageView = new ImageView(new Image(new File("src/images/bot.gif").toURI().toString()));
    private int health;
    char move = 'u';
    public Bot(Map map) {
        this.map = map;
        this.imageView = imageView;

        imageView.setFitWidth(pixel);
        imageView.setFitHeight(pixel);
    }
    public ImageView getImage(){
        return this.imageView;
    }

    public void setDirect() {
        Random random = new Random();
        char[] ch = {'u', 'd', 'r', 'l'};
        int[] ints = {100 , 117 , 114 , 108 };
        char direction = (char)(ints[random.nextInt(4)]);
        switch (direction){
            case 'u': this.move = 'u'; break;
            case 'd':  this.move = 'd'; break;
            case 'r':  this.move = 'r'; break;
            case 'l':   this.move = 'l'; break;
        }
    }

    public void setAlive() {
        if(getHealth()>0){
            isAlive = true;
        }else
            isAlive = false;
    }

    public boolean isAlive() {
        return isAlive;
    }
    //
    public boolean isDead(){
        if(getHealth()==0){
            return true;
        }
        else return false;
    }
    public void setMap(Map map) {
        this.map = map;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                if (map.getValueAt(i, j) == 'E') {
                    botPosition = new BotPosition(i, j);
                }
            }
        }
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
    public void changehealth(){
/**
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 * */
    this.health = health;
    }
    @Override
    public void moveRight(){
        imageView.setRotate(90);
        nextRight();


    }
    public void nextRight(){
        if(botPosition.x+1 <= map.getX()) {
            if(map.getValueAt(botPosition.x + 1, botPosition.y) != 'T'){
                imageView.setVisible(true);
            }
            if(map.getValueAt(botPosition.x + 1, botPosition.y) == 'T'){
                imageView.setVisible(false);
            }
            if(map.getValueAt(botPosition.x+1, botPosition.y) != 'S' && map.getValueAt(botPosition.x+1, botPosition.y) != 'B'
                    && map.getValueAt(botPosition.x+1, botPosition.y) != 'E'  && map.getValueAt(botPosition.x+1, botPosition.y) != 'W' && map.getValueAt(botPosition.x+1, botPosition.y) != 'P') {
                botPosition = new BotPosition(botPosition.y, botPosition.x + 1);
                imageView.setX(botPosition.getX() * pixel);
            }    }
    }
    @Override
    public void moveLeft(){
        imageView.setRotate(-90);
        nextLeft();
    }
    public void nextLeft(){
        if(botPosition.x - 1 >= 0) {
            if(map.getValueAt(botPosition.x - 1, botPosition.y) != 'T'){
            setInvisible();
            }
            if(map.getValueAt(botPosition.x - 1, botPosition.y) == 'T'){
                setVisible();
            }
            if(map.getValueAt(botPosition.x - 1, botPosition.y) != 'S' && map.getValueAt(botPosition.x - 1, botPosition.y) != 'B'
                    && map.getValueAt(botPosition.x - 1, botPosition.y) != 'E' && map.getValueAt(botPosition.x - 1, botPosition.y) != 'W' && map.getValueAt(botPosition.x - 1, botPosition.y) != 'P'){
                botPosition = new BotPosition(botPosition.y, botPosition.x - 1);
                imageView.setX(botPosition.getX() * 64);
            }
        }
    }
    @Override
    public void moveDown(){
        imageView.setRotate(180);
        nextDown();
    }
    public void nextDown(){
        if(botPosition.y+1 <= map.getY()) {
            if(map.getValueAt(botPosition.x, botPosition.y+1) == 'T'){
                setInvisible();
            }
            if(map.getValueAt(botPosition.x, botPosition.y+1) != 'T'){
                setVisible();
            }
            if(map.getValueAt(botPosition.x, botPosition.y+1) != 'S' && map.getValueAt(botPosition.x, botPosition.y+1) != 'B' &&
                    map.getValueAt(botPosition.x, botPosition.y+1) != 'W' && map.getValueAt(botPosition.x, botPosition.y+1) != 'P'
                    && map.getValueAt(botPosition.x, botPosition.y+1) != 'E'){
                botPosition = new BotPosition(botPosition.y + 1, botPosition.x);
                imageView.setY(botPosition.getY() * 64);
            }
        }
    }
    @Override
    public Position getPosition() {
        return null;
    }

    public BotPosition getEnemyPosition() {
        return botPosition;
    }

    @Override
    public void moveUp(){
        imageView.setRotate(0);
        nextUp();
    }
    public void nextUp(){
        if(botPosition.y - 1 >= 0) {
            if(map.getValueAt(botPosition.x, botPosition.y - 1) == 'T'){
                setInvisible();
            }
            if(map.getValueAt(botPosition.x, botPosition.y - 1) != 'T'){
                setVisible();
            }
            if(map.getValueAt(botPosition.x, botPosition.y - 1) != 'S' && map.getValueAt(botPosition.x, botPosition.y - 1) != 'B' && map.getValueAt(botPosition.x, botPosition.y - 1) != 'W'){
                botPosition = new BotPosition(botPosition.y - 1, botPosition.x);
                imageView.setY(botPosition.getY() * 64);
            }
        }
    }
    public void setVisible(){
        imageView.setVisible(true);
    }
    public void setInvisible(){
        imageView.setVisible(false);
    }
    @Override
    public void shoot(){
        Bullet bullet = new Bullet();
        PathTransition pt = new PathTransition();
        pane.getChildren().add(bullet);
        Line line = new Line();

        switch(move){
            case 'u':

                break;
            case 'd':
                break;
            case 'r':
                break;
            case 'l':
                break;
        }

    }
    public void setPos(Position pos){
        this.pos = pos;
    }
    public char getDirect(){
        return this.move;
}
    @Override
    public void run() {
        Timeline tt = new Timeline();
        tt.getKeyFrames().add(new KeyFrame(Duration.seconds(1),event -> {
//            do tt.stop();while (!true);
            setDirect();
            switch (getDirect()){
              case 'u': moveUp(); break;
              case 'd': moveDown(); break;
              case 'r': moveRight(); break;
              case 'l': moveLeft(); break;
          }
//          if(botPosition.getY()>pos.y && botPosition.x>pos.x){
//              moveLeft();
//              moveDown();
//          }else if(botPosition.getY()<pos.y && botPosition.x<pos.x){
//              moveRight();
//              moveUp();
//          }else if(botPosition.getY()<pos.y && botPosition.x>pos.x){
//                moveLeft();
//                moveUp();
//          }else if(botPosition.getY()>pos.y && botPosition.x<pos.x){
//              moveRight();
//              moveDown();
//          }

        }));
        tt.setCycleCount(Animation.INDEFINITE);
        tt.play();
    }

}
