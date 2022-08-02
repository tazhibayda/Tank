package Project2;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
public class Client extends Application {
    TextField name= new TextField() ;
    /**
     * I created by javafx scene with control buttons and added socket for connection
     *
     * */
    private static  DataOutputStream output;
    VBox AddButtons;
    VBox create;
    public VBox createVBox(){
        return create;
    }
    public VBox addToBox(Node e){
        create.getChildren().add(e);
        return create;
    }
    @Override
    public void start(Stage primaryStage)  {
        Button join = new Button("Join"); //adding buttons
        Button W = new Button("W"); //adding buttons
        Button S = new Button("S"); //adding buttons
        Button D = new Button("D"); //adding buttons
        Button A = new Button("A"); //adding buttons
        Button SPACE = new Button("SPACE"); //adding buttons
        VBox vBox = new VBox(); //adding buttons
        vBox.setSpacing(10); //adding buttons
        name.setPromptText("Name"); //adding buttons
        name.setMaxWidth(300); //adding buttons
        name.setPrefSize(30,30); //adding buttons
        join.setPrefSize(50,30); //adding buttons
        join.setOnAction(e->{ //adding buttons
            vBox.getChildren().clear(); //adding buttons
            VBox test = new VBox(); //adding buttons
            HBox bp = new HBox(); //adding buttons
            AddButtons = new VBox(); //adding buttons
            bp.setSpacing(2.5); //adding buttons
            SPACE.setPrefSize(190, 30);  //adding buttons
            A.setPrefSize(60, 60); //adding buttons
            D.setPrefSize(60, 60); //adding buttons
            W.setMinSize(22.5, 22.5); //adding buttons
            AddButtons.setAlignment(Pos.CENTER); //adding buttons
            S.setMinSize(22.5, 22.5); //adding buttons
            AddButtons.setMinSize(55, 50); //adding buttons
            bp.setMinSize(400, 200); //adding buttons
            AddButtons.setSpacing(5); //adding buttons
            bp.setAlignment(Pos.CENTER); //adding buttons
            AddButtons.getChildren().addAll(W, S); //adding buttons
            bp.getChildren().addAll(A, AddButtons, D); //adding buttons
            test.getChildren().addAll(bp,SPACE); //adding buttons
            test.setAlignment(Pos.CENTER); //adding buttons
            vBox.getChildren().add(test); //adding buttons
           // System.out.println(text_x.getText());

            try {
                output.writeUTF(name.getText());
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

        });
        vBox.getChildren().addAll(name , join);
        primaryStage.setScene(new Scene(vBox,600,600));
        vBox.setAlignment(Pos.CENTER);
        vBox.setStyle("-fx-background-color: #008d01");
        primaryStage.show();
        vBox.requestFocus();

        Thread thread = new Thread();
    /**
     * Adding socket
     * There i cant understand how thread works i took this from book
     * */
    new Thread(()->{
            try {
                Socket socket = new Socket("127.0.0.1", 111);
                output = new DataOutputStream(socket.getOutputStream());
                while (true) {
                    W.setOnAction(ex -> {
                        try {
                            output.writeUTF("U");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
                    S.setOnAction(ex -> {
                        try {
                            output.writeUTF("D");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
                    A.setOnAction(ex -> {
                        try {
                            output.writeUTF("L");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
                    D.setOnAction(ex -> {
                        try {
                            output.writeUTF("R");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
                    SPACE.setOnAction(ex -> {
                        try {
                            output.writeUTF("S");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
                }
            }
            catch(IOException ex){
                System.out.println("SERVER CONNECTION ERROR!");
            }
            thread.start();
        }).start();


    }

    private Application thread(Object o) {
        return null;
    }
}