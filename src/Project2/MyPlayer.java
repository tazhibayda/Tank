package Project2;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

class MyPlayer extends Pane implements Player {
    Position position;
    Map map;
    ImageView imageView;


    public MyPlayer(){}


    public MyPlayer(ImageView imageView, Map map){
        this.imageView = imageView;
        this.position = map.position;
        this.map = map;
    }

    @Override
    public void setMap(Map map) {
        this.map = map;
        this.position = map.position;
    }

    @Override
    public void moveRight() {
        imageView.setRotate(90);
        if(position.x+1 <= map.getX()) {
            if(map.getValueAt(position.x + 1, position.y) == 'T'){
                imageView.setX(position.getX()*64);
                imageView.setVisible(false);
            }
            if(map.getValueAt(position.x + 1, position.y) != 'T'){
                imageView.setVisible(true);
            }
           if(map.getValueAt(position.x+1, position.y) != 'S' && map.getValueAt(position.x+1, position.y) != 'B' && map.getValueAt(position.x+1, position.y) != 'W'){
               position = new Position(position.y, position.x + 1);
               imageView.setX(position.getX() * 64);
            }
        }
    }

    @Override
    public void moveLeft() {
        imageView.setRotate(-90);
        if(position.x-1 >= 0) {
            if(map.getValueAt(position.x - 1, position.y) == 'T'){
                imageView.setX(position.getX()*64);
                imageView.setVisible(false);
            }
            if(map.getValueAt(position.x - 1, position.y) != 'T'){
                imageView.setVisible(true);
            }
            if(map.getValueAt(position.x-1, position.y) != 'S' && map.getValueAt(position.x-1, position.y) != 'B' && map.getValueAt(position.x-1, position.y) != 'W') {
                position = new Position(position.y, position.x - 1);
                imageView.setX(position.getX() * 64);
            }
        }
    }

    @Override
    public void moveUp() {
        imageView.setRotate(0);
        if(position.y-1 >= 0) {
            if(map.getValueAt(position.x, position.y-1) == 'T'){
                imageView.setY(position.getY()*64);
                this.imageView.setVisible(false);
            }
            if(map.getValueAt(position.x, position.y-1) != 'T'){
                this.imageView.setVisible(true);
            }
            if(map.getValueAt(position.x, position.y-1) != 'S' &&  map.getValueAt(position.x, position.y-1) != 'B' && map.getValueAt(position.x, position.y-1) != 'W') {
                position = new Position(position.y - 1, position.x);
                imageView.setY(position.getY() * 64);
            }
        }
    }
    @Override
    public void moveDown() {
        imageView.setRotate(180);

        if(position.y+1 <= map.getY()) {
            if(map.getValueAt(position.x, position.y+1) == 'T'){
                imageView.setY(position.getY()*64);
                this.imageView.setVisible(false);
            }
            if(map.getValueAt(position.x, position.y+1) != 'T'){
                this.imageView.setVisible(true);
            }
            if(map.getValueAt(position.x, position.y+1) != 'S' && map.getValueAt(position.x, position.y+1) != 'B' && map.getValueAt(position.x, position.y+1) != 'W'){
               // this.imageView.setVisible(true);
                position = new Position(position.y + 1, position.x);
                imageView.setY(position.getY() * 64);
            }
        }
    }

    @Override
    public void shoot(){

    }

    public void setMoveUp(ImageView imageView){
        moveUp();
    }

    @Override
    public Position getPosition() {
        return this.position;
    }
}

