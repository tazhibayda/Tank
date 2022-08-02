package Project2;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;

import java.util.Scanner;

public class Main extends Application {
    Image icon = new Image("images/icon.jfif");
    ImageView image;
    BotPosition botPosition;
    Map map;
    boolean isAlive;
    private ArrayList<Bot> list;
    char direction = 'R';



    public Main() throws FileNotFoundException {
    }



     public static void main(String[] args) {
/**         path_file = args[0];   хотел запусткать через cmd но не получилось по кд ловит FileNotFoundException       */
            launch(args);
    }
    int bots = 0;
    int thtf = 35 , sn = 69;
    final int pixel = 64;
     Brick[][] objects;
    public char cli_direction;
    Bullet secondTank;
     DataInputStream input;
    Pane pane;
    public char getDirection(){
        return this.direction;
    }
    @Override
    public void start(Stage primaryStage) throws  InvalidMapException , FileNotFoundException {//начальная сцена
        BorderPane bp = new BorderPane(); // бордер пейн чтоб расставить все по местам но не получилось
        Button button = new Button("Start");
        Button button1 = new Button("Instruction");
        Button button2 = new Button("Exit");
        Circle c = new Circle(); // чтобы кнопи были по середине
        c.setRadius(50);
        c.setVisible(false);
        HBox hBox = new HBox();//создать боксы чтобы красиво выглядел
        VBox vBox = new VBox();//создать боксы чтобы красиво выглядел
        vBox.setSpacing(40);
        hBox.setSpacing(70);
        c.setCenterX(300);
        c.setCenterY(175);
        hBox.getChildren().addAll(c,vBox);
        vBox.getChildren().addAll(c, button,button1,button2);
        Image image = new Image("images/main.jpg");
        hBox.setAlignment(Pos.CENTER);
        ImageView iv = new ImageView(image);
        Group root = new Group();//рут для заднего фона
        root.getChildren().addAll(iv);
        button.setOnAction(e->{
            try {
                play(primaryStage);//перейти на сцену игры
            } catch (InvalidMapException invalidMapException) {
                invalidMapException.printStackTrace();
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        });
        button1.setOnAction(e->{
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Instruction");//инструкция
            alert.setHeaderText("How to Play?");
            alert.setContentText("Move by WASD and Shoot by SPACE\nPress ESC to go Main Menu and F to FullScreen");
            alert.showAndWait();
        });
        button2.setOnAction(e->{
            System.exit(0);//выход
        });
        BorderPane borderPane = new BorderPane();//
        borderPane.setCenter(iv);//фото для заднего фона
//        borderPane.setTop(vBox);
        bp.setTop( vBox);
        primaryStage.getIcons().add(icon);//иконка
        StackPane stackPane = new StackPane();
        bp.setStyle("-fx-background-image: "+iv);
        stackPane.getChildren().addAll(bp,borderPane);
        primaryStage.setScene(new Scene(stackPane , 800, 400));
        primaryStage.setIconified(false);//что то сделал не так и не могу поставить кнопки
        primaryStage.setResizable(false);
        bp.setStyle("-fx-background-color: #050202");
        primaryStage.setTitle("Menu");
        primaryStage.show();
    }
    public void bulletIsAlive(){
        this.isAlive = true;
    }
    public void bulletIsDied(){
//        this.is
    }
    public void set(Brick brick){
        brick.setLayoutX(pixel);
        brick.setLayoutY(pixel);
    }
    public void set(int i , int j, ImageView image , Pane pane){
        image.setX(j*pixel);
        image.setY(i*pixel);
        pane.getChildren().add(image);
    }
    public void play(Stage primaryStage) throws InvalidMapException, FileNotFoundException {
        BorderPane bp = new BorderPane();
        objects = new Brick[13][13];//максимальный размер чтоб большие карты не отображались()
        int milli = 1_0_0_0;//скорость на миллисекуедах
        pane = new Pane();//главный пейн
        pane.setStyle("-fx-background-color: black;");//задний план
        list = new ArrayList<>();//лист для ботов

        File file = new File("src/images/Map.txt");//файл


        Scanner scan = new Scanner(
file                            //reading map
        );
        map = new Map(scan);//карта через файл




        for(int j = 0; j < map.getSize(); j++){

            for(int i = 0; i < map.getSize(); i++){
                if(map.getValueAt(i, j) == 'P'){
                    tankView.setX(i*pixel);
                    tankView.setY(j*pixel);
                    pane.getChildren().add(tankView);
                }else
                if(map.getValueAt(j, i) == 'W'){
                    ImageView waterView = new ImageView(water);
                    set(i,j,waterView,pane);
                }else
                if(map.getValueAt(j, i) == 'S'){
                    ImageView steelView = new ImageView(steel);
                    set(i,j,steelView,pane);
                }else
                if(map.getValueAt(j, i) == 'T'){
                    ImageView treeView = new ImageView(tree);
                    set(i,j,treeView,pane);
                }else
                if(map.getValueAt(j, i) == 'B'){
                    ImageView brickView = new ImageView(brick);
                    Brick brick = new Brick(brickView);
                    brick.setLayoutX(j*pixel);
                    brick.setLayoutY(i*pixel);
                    objects[j][i] = brick;
                    pane.getChildren().add(brick);
                }
                else if(map.getValueAt(i, j) == 'E'){
/**
 *
 * Adding bots randomly and put it to Array list
 *
 *
 * */
                    bots++;

                    Bot bot = new Bot(map);
                    image = bot.getImage();
                    bot.setMap(map);
                    for (int f=0; f<bots;f++){
                        list.add(bot);
                    }/**
                     Setting position

                     */
                    set(i,j,image,pane);
                    botPosition = new BotPosition(i, j);
                }
            }
        }


            Tank tank = new Tank(tankView, map);
            bp.setCenter(pane);
            Scene scene = new Scene(bp, map.getSize() * pixel, map.getSize() * pixel);
            primaryStage.setScene(scene);

        primaryStage.getScene().setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.W)) {
                tank.moveUp();/**change direction*/
                direction = 'U';
            }
            if (event.getCode().equals(KeyCode.S)){
                tank.moveDown();
                direction = 'D';
            }
            if (event.getCode().equals( KeyCode.A)){
                tank.moveLeft();
                direction = 'L';
            }
            if (event.getCode().equals( KeyCode.D)){
                tank.moveRight();
                direction = 'R';
            }
            if(event.getCode().equals( KeyCode.ESCAPE)){
                try {
                    start(primaryStage);
                } catch (InvalidMapException e) {
                    e.printStackTrace();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }if(event.getCode().equals( KeyCode.N)){
                try {
                    changeMap();
                } catch (InvalidMapException e) {
                    e.printStackTrace();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
            if(event.getCode().equals(KeyCode.SPACE)) {
                Bullet bullet = new Bullet(tank.getPosition());
                bullet.setMap(map);
                pane.getChildren().add(bullet);
                PathTransition path = new PathTransition();
                Line line = new Line();
                for (int i = 0; i < list.size(); i++) {

                    switch (direction) {
                        case 'U':
                            for (int j = tank.position.getY() - 1; j >= 0; --j) {
                                if (map.getValueAt(tank.position.getX(), j) == 'S') {
                                    int x = tank.position.getX() * pixel + thtf;
                                    int y = tank.position.getY() * pixel;
                                    line = new Line(x, y, x, j * pixel + sn);
                                    break;
                                } else if (map.getValueAt(tank.position.getX(), j) == 'B') {

                                    if (objects[tank.position.getX()][j] != null) {
                                        int health = objects[tank.position.getX()][j].getHealth();
                                        health = health - 1;
                                        int x = tank.position.getX() * pixel + thtf;
                                        int y = tank.position.getY() * pixel;
                                        line = new Line(x, y, x, j * pixel + sn);

                                        objects[tank.position.getX()][j].setHealth(health);
                                        if (objects[tank.position.getX()][j].getHealth() == 0) {
                                            map.setZero(tank.position.getX(), j);
                                            pane.getChildren().remove(objects[tank.position.getX()][j]);
                                            objects[tank.position.getX()][j] = null;

                                        } else {
                                            System.out.println(objects[tank.position.getX()][j].getHealth());

                                        }
                                    }
                                    break;
                                } else if (tank.getPosition().x == botPosition.getX() && j == botPosition.getY()) {
                                } else {
                                    line = new Line(tank.position.getX() * pixel + 35, tank.position.getY() * pixel, tank.position.getX() * pixel + 35, -7);
                                }
                            }
                            break;
                        case 'L':
                            for (int j = tank.position.getX() - 1; j >= 0; --j) {
                                if (map.getValueAt(j, tank.position.getY()) == 'S') {
                                    int x = tank.position.getX() * pixel;
                                    int s = tank.position.getY() * pixel + 35;
                                    line = new Line(x, s, j * pixel + 69, s);
                                    break;
                                } else if (map.getValueAt(j, tank.position.getY()) == 'B') {
                                    if (objects[j][tank.position.getY()] != null) {
                                        int health = objects[j][tank.position.getY()].getHealth();
                                        health--;
                                        objects[j][tank.position.getY()].setHealth(health);
                                        int x = tank.position.getX() * pixel;
                                        int s = tank.position.getY() * pixel + 35;
                                        line = new Line(x, s, j * pixel + 69, s);
                                        if (objects[j][tank.position.getY()].getHealth() == 0) {
                                            map.setZero(j, tank.position.getY());
                                            pane.getChildren().remove(objects[j][tank.position.getY()]);
                                            objects[j][tank.position.getY()] = null;

                                        }
                                    }
                                    break;
                                } else {
                                    line = new Line(tank.position.getX() * pixel, tank.position.getY() * pixel + 35, -7, tank.position.getY() * pixel + 35);
                                }
                            }
                            break;
                        case 'D':
                            for (int j = tank.position.getY() + 1; j <= map.getY(); ++j) {
                                if (map.getValueAt(tank.position.getX(), j) == 'S') {
                                    int x = tank.position.getX() * pixel + 35;
                                    int s = tank.position.getY() * pixel + 64;
                                    line = new Line(x, s, x, j * pixel);
                                    break;
                                } else if (map.getValueAt(tank.position.getX(), j) == 'B') {
                                    if (objects[tank.position.getX()][j] != null) {
                                        int health = objects[tank.position.getX()][j].getHealth();
                                        health = health - 1;
                                        int x = tank.position.getX() * pixel + 35;
                                        int s = tank.position.getY() * pixel + 64;
                                        line = new Line(x, s, x, j * pixel);

                                        objects[tank.position.getX()][j].setHealth(health);
                                        if (objects[tank.position.getX()][j].getHealth() == 0) {
                                            map.setZero(tank.position.getX(), j);
                                            removePane(pane, objects[tank.position.getX()][j]);
                                            objects[tank.position.getX()][j] = null;

                                        } else {
                                            System.out.println(objects[tank.position.getX()][j].getHealth());

                                        }
                                    } else if (map.getValueAt(tank.position.getX(), j) == 'E') {
                                    }
                                    break;
                                } else {
                                    line = new Line(tank.position.getX() * pixel + 35, tank.position.getY() * pixel + 69, tank.position.getX() * pixel + 35, map.getSize() * pixel);
                                }
                            }
                            break;
                        case 'R':/**
                         check cases
                         if go r */
                            for (int j = tank.position.getX() + 1; j < map.getSize(); ++j) {
                                if (map.getValueAt(j, tank.position.getY()) == 'S') {
                                    int x = tank.position.getX() * pixel + 64;
                                    int y = tank.position.getY() * pixel + 35;
                                    line = new Line(x, y, j * pixel, y);
                                    break;
                                }
                                if (objects[j][tank.position.getY()] != null) {
                                    int health = objects[j][tank.position.getY()].getHealth();
                                    health--;
                                    objects[j][tank.position.getY()].setHealth(health);
                                    int x = tank.position.getX() * pixel + 64;
                                    int s = tank.position.getY() * pixel + 35;
                                    line = new Line(x, s, j * pixel, s);
                                    if (objects[j][tank.position.getY()].getHealth() == 0) {
                                        map.setZero(j, tank.position.getY());
                                        pane.getChildren().remove(objects[j][tank.position.getY()]);
                                        objects[j][tank.position.getY()] = null;

                                    }
                                    break;
                                } else {
                                    line = new Line(tank.position.getX() * pixel + 69, tank.position.getY() * pixel + 35, map.getSize() * pixel, tank.position.getY() * pixel + 35);
                                }
                            }
                            break;
                    }
                }
                path.setPath(line);
                path.setNode(bullet);
                path.setDuration(Duration.millis(1000));
                path.play();
                path.setOnFinished(event1 ->
                        pane.getChildren().remove(bullet)
                );
            }
        });


        for(int i=0;i<bots;i++){  // вроде работает нормально
            Thread tanks = new Thread(list.get(i));// но иногда не сразу запускается
            tanks.start();
        }



      /**    Тут дальше я не сделал
       *     не могу доабвить объекта в пейн не знаю из за чего
       *     и часто выводит ошибок
       *     тред работает не правильно
       *
       * */
                new Thread(()->{//тут слошные ошибки
            try {
                ServerSocket server = new ServerSocket(111);
                System.out.println("Server is on");
                while (true) {
                    Socket client = server.accept();
                    System.out.println("connected");
                    new Thread(() -> {
                        ClientTank clientTank = new ClientTank();
                        clientTank.setMap(map);
                        clientTank.setLayoutX(clientTank.getPosition().x * pixel);
                        clientTank.setLayoutY(clientTank.getPosition().y * pixel);

                        Platform.runLater(() -> {
                            pane.getChildren().add(clientTank);
                        });
                        try {
                            input = new DataInputStream(client.getInputStream());
                            System.out.println(input.readUTF() + " joined...");
                            while (true) {
                                String from_client = input.readUTF();


                                switch (from_client) {
                                    case "U":
                                        clientTank.moveUp();
                                        break;
                                    case "L":
                                        clientTank.moveLeft();
                                        break;
                                    case "D":
                                        clientTank.moveDown();
                                        break;
                                    case "R":
                                        clientTank.moveRight();
                                        break;
                                    case "S":
                                        /** I couldnt create bullet for tank */
                                        break;
                                }
                            }


                        } catch (IOException e) {
                        }
                    }).start();
                }
            }
            catch (IOException e){
            }
        }).start();
        primaryStage.show();
        primaryStage.getIcons().add(icon);
        primaryStage.setTitle("World of Tanks");
        pane.requestFocus();
    }

    public void addPane(Pane pane, Node node){
        pane.getChildren().add(node);
    }
    public void removePane(Pane pane, Node node){
        pane.getChildren().remove(node);
    }
    public void changeMap() throws InvalidMapException, FileNotFoundException {
        File map2 = new File("src/images/Map2.txt");
        Scanner mapScan = new Scanner(map2);
        map = new Map(mapScan);
    }

    Image brick = new Image(new FileInputStream("src/images/Brick.png"));

    Image steel = new Image(new FileInputStream("src/images/SteelWall.png"));

    Image tree = new Image(new FileInputStream("src/images/Tree.png"));


    Image water = new Image(new FileInputStream("src/images/Water.png"));

    Image tank = new Image(new FileInputStream("src/images/Tank.png"));

    ImageView tankView = new ImageView(tank);
    public void  setLine(int a , int b ,int c ,int d ,Line line){
        line = new Line(a , b ,c ,d);
    }
}

/**
 *  картада арқылы танктардың позициясы оқылмайды, столкновение үшін бір способ керек
 *  Гет позиция сет позиция бәрі жасайды бірақ проблема в том что картадағы чарлар өзгермейді
 *  и біз getValueAt арқылы салыстыра алмаймыз,
 *
 *  *
 * Thread қате жасап тұр, запуск жасар кезде бірнеше рет қайта ашпаса боттар қозғалмайды және
 *
 *
 *
 *
 *
 *
 * */