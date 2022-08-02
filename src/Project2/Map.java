package Project2;

import java.util.ArrayList;
import java.util.Scanner;


//---------------------------Map------////-----------------------------------------//
class Map {
    char[][] cells;
    int y;
    //int BulX = 0, BulY = 0;
    int x;
    //Scanner scan;
    ArrayList<Integer>listX = new ArrayList<>();
    ArrayList<Integer>listY = new ArrayList<>();
    int length;
    Position position;
    BotPosition botPosition;
    Map(Scanner scan) throws InvalidMapException {
        this.length = scan.nextInt();
        this.y = this.length-1;
        this.x = this.length-1;
        cells = new char[this.length][this.length];

        if (this.length <= 0) {
            throw new InvalidMapException("Map size can not be zero");
        }

        for (int i = 0; i < this.length; i++) {
            for (int j = 0; j < this.length; j++) {
                if (!scan.hasNext()){
                    throw new InvalidMapException("Not enough map elements");
                }
                this.cells[i][j] = scan.next().charAt(0);

                if (this.cells[i][j] == 'P') {
                    this.position = new Position(i, j);
                }
            }
        }
        int i = (int)(Math.random() * 5);
        for(int k =1; k< i; k++) {
            setRandom();
        }
    }
  public void setRandom(){
        int i = (int)(Math.random() * (length ));
        int count =0;
      for(int n = 0; n<length; n++){
          for(int d = 0; d<length; d++){
              if(cells[n][d] == '0'){
                  count++;
                  if(count == i)
                      cells[n][d] = 'E';
//                    botPosition = new BotPosition(d, n);
              }

          }
      }
  }
//    public void setEnemy(int x , int y){cells[y][x] = 'E';}
//    public void

    public void setZero(int x,int  y) {
        cells[y][x] = '0';
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public int getSize() {
        return this.length;
    }

    char getValueAt(int x, int y) {
        return cells[y][x];
    }

    public void print() {
        for (char[] aChar : cells) {
            for (int j = 0; j < this.length; j++) {
                System.out.print(aChar[j] + " ");
            }
            System.out.println();
        }
    }
}

