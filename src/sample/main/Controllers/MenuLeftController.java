package sample.main.Controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXToggleButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import sample.main.Dispatcher;
import sample.main.Main;
import sample.main.data.Languages;

import java.io.IOException;

/**
 * Created by Samsung on 20.04.2018.
 */
public class MenuLeftController {

    @FXML private JFXButton helpID;
    @FXML private Label langID;
    @FXML private JFXToggleButton rusTogButID;
    @FXML private Label profName;
    @FXML private JFXToggleButton engTogButID;
    @FXML private JFXButton savecloseID;
    private static Languages languages;
    private static String[] lang;



    @FXML
    void initialize(){
        languages = new Languages();
        ControllerChoose.setLbl(profName);
        engTogButID.setDisable(true);
        setTextAll();
    }
    @FXML
    void rusToggle(ActionEvent event) {
        engTogButID.setSelected(false);
        engTogButID.setDisable(false);
        rusTogButID.setDisable(true);
        Dispatcher.langInt = 2;
        setTextAll();
       chgLang();
    }

    @FXML
    void engToggle(ActionEvent event) {
        rusTogButID.setSelected(false);
        rusTogButID.setDisable(false);
        engTogButID.setDisable(true);
        Dispatcher.langInt = 1;
        setTextAll();
        chgLang();
    }
    private void chgLang(){
        Main.getL().chgLangForMain();
                Main.getL().chgLangForCrypt();
                Main.getL().chgLangForChoose();
                Main.getL().chgLangForCreate();
                Main.getL().chgLangForCryptFiles();
    }
    @FXML
    void saveClose(ActionEvent event) {
        Dispatcher.saveProfile(Dispatcher.getData());
        Dispatcher.getData().setLangInt(Dispatcher.langInt);
        Dispatcher.saveProfile(Dispatcher.getData());
        System.exit(0);
    }
    @FXML
    void helpAction(ActionEvent event){
        try {
            Stage stageHelp = new Stage();
            stageHelp.setTitle("Help");
            stageHelp.setResizable(false);
            Parent rootChoose = FXMLLoader.load(getClass().getResource("/sample/fxml/Help.fxml"));
            stageHelp.getIcons().add(new Image(getClass().getResourceAsStream("/sample/fxml/images/icon4.png")));
            Scene scene = new Scene(rootChoose, 365, 400);
            scene.getStylesheets().add(getClass().getResource("/sample/fxml/styles/configStyle.css").toExternalForm());
            stageHelp.setScene(scene);
            stageHelp.show();
        }catch (IOException e){e.printStackTrace();}
    }
    public static void setProfName(){
        new MenuLeftController().prfName();
    }

    private void prfName(){
        profName.setText(Dispatcher.getProfileN());
    }
    private void setTextAll(){
        languages.setLang(Dispatcher.langInt);
        lang = languages.getMainLang();
        helpID.setText(lang[8]);
        savecloseID.setText(lang[9]);
        engTogButID.setText(lang[12]);
        rusTogButID.setText(lang[11]);

        langID.setText(lang[7]);
        lang = null;
    }



}
