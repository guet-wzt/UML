package myGame;

import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;


public class MyGame extends Application {
    public static int flag;
    @Override
    public void start(Stage primaryStage) {
        Window myWindow = new Window();
        myWindow.theFirstWindow();
        Optimum optimum = new Optimum();
        String line = optimum.getBest(myWindow.getOption1(),myWindow.getOption2());
        myWindow.setFild(line);
        Alert alert = myWindow.getLastWindow();
        HBox hBox = myWindow.mainWindow(optimum.getBestN(),alert);
        Task task = new Task() {
            @Override
            protected Object call() throws Exception {
                for (int i = 0; i < 20; i++) {
                    try{
                        Thread.sleep(1000);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    myWindow.getReaminTime().setText(""+(19-i)+"s");
                }
                flag = 1;
                alert.show();
                return null;
            }
        };
        new Thread(task).start();
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(20);
        Scene scene = new Scene(hBox,560,560);

        primaryStage.setScene(scene);
        primaryStage.setTitle("曹冲称象");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
