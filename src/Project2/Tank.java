package Project2;

import javafx.scene.image.ImageView;
import javafx.scene.shape.Line;

public class Tank extends MyPlayer{
    private int health;
    private boolean isAlive;
    public Tank(ImageView ivi, Map map) {
        super(ivi, map);
    }
    public void setHealth(int x){
        this.health = x;
    }

    public int getHealth() {
        return health;
    }
    public boolean isAlive(){
        return isAlive;
    }
    public void setAlive(int hp){
        if(hp>0){
            isAlive = true;
        }else
            isAlive = false;
    }

}
