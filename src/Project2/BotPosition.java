package Project2;

public class BotPosition {
    int x;
    int y;

    public BotPosition(int y, int x) {
        this.x = x;
        this.y = y;
    }


    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public String toString() {
        return String.format( "(%d,%d)",x,y);
    }
}
