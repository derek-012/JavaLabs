package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Лабораторная работа 7");
        primaryStage.setScene(new Scene(root));
        Controller controller = loader.getController();
        controller.setStageAndListeners(primaryStage);
        primaryStage.show();
    }



    public static void main(String[] args) {
        launch(args);
    }
}
