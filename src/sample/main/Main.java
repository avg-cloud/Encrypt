package sample.main;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.main.Controllers.LanguageChanger;

public class Main extends Application {
    private static Stage stageObj;
    private static LanguageChanger l;
    @Override
    public void start(Stage primaryStage) throws Exception{
        Dispatcher d = new Dispatcher();

        Dispatcher.setLangChg(l);

        stageObj = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("/sample/fxml/main.fxml"));
        primaryStage.setTitle("Crypter");
        Scene scene = new Scene(root, 643, 431);
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/sample/fxml/images/icon4.png")));
        scene.getStylesheets().add(getClass().getResource("/sample/fxml/styles/configStyle.css").toExternalForm());
        primaryStage.setResizable(false);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();

    }


    public static void main(String[] args) {
        l = new LanguageChanger(); launch(args);
    }

    public static Stage getStageObj(){
        return stageObj;
    }

    public static void setL(LanguageChanger lang){
        l = lang;
    }
    public static LanguageChanger getL(){
        return l;
    }
}
