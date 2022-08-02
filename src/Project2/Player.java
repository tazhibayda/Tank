package Project2;

//import javax.swing.text.html.ImageView;


//---------------------------Player------////-----------------------------------------//
interface Player {
    void setMap(Map map);

    void moveRight();

    void moveLeft();

    void moveUp();

    void moveDown();

    void shoot();
    Position getPosition();
}

