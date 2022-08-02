package Project2;

import javafx.scene.image.ImageView;

public class Brick extends Wall{
private int health ;
    public Brick(ImageView image) {
        super(image);
        this.health = 4;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
