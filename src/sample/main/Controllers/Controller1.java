package sample.main.Controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import sample.main.Dispatcher;
import sample.main.Main;
import sample.main.data.Languages;


import java.util.concurrent.TimeUnit;

public class Controller1{

    @FXML private Text profileName;
    @FXML private JFXButton chooseBut;
    @FXML private JFXButton createBut;
    @FXML private Text profileStr;
    @FXML private AnchorPane paneContent;
    @FXML private AnchorPane paneMain;
    @FXML private JFXButton cryptChoosePaneBut;
    @FXML private StackPane stackPane;
    @FXML private ImageView imageCrypt;
    @FXML private ImageView imageProfile;
    @FXML private HBox hbox;
    @FXML private JFXHamburger menuHamburger;
    @FXML private JFXDrawer drawer;
    @FXML private JFXButton cryptFilesPaneBut;
    @FXML private Label cryptingLabel;
    @FXML private Label profilesLabel;

    private static int active = 1;
    private static String keys;
    private static boolean Status = false;
    private Languages languages ;
    private String[] lang;
    private static double xOffset;
    private static double yOffset;
    private boolean cryptB = true;
    private boolean profileB = true;
    private Image imgminus = new Image("sample/fxml/images/minus.png");
    private Image imgplus = new Image("sample/fxml/images/plus.png");
    @FXML
    public void initialize() {

        languages = new Languages();
        FXMLLoader loader;
        try {
            loader = new FXMLLoader(getClass().getResource("/sample/fxml/cryptPane.fxml"));
            stackPane.getChildren().add((Node) loader.load());
            stackPane.getChildren().get(0).setVisible(true);
            loader = null;

            loader = new FXMLLoader(getClass().getResource("/sample/fxml/ChooseWindow.fxml"));
            stackPane.getChildren().add((Node) loader.load());
            ControllerChoose.setController(loader.getController());
            stackPane.getChildren().get(1).setVisible(false);
            loader = null;

            loader = new FXMLLoader(getClass().getResource("/sample/fxml/CreateWindow.fxml"));
            stackPane.getChildren().add((Node) loader.load());
            stackPane.getChildren().get(2).setVisible(false);
            loader = null;

            loader = new FXMLLoader(getClass().getResource("/sample/fxml/cryptFiles.fxml"));
            stackPane.getChildren().add((Node) loader.load());
            CryptFilesController.setController(loader.getController());
            stackPane.getChildren().get(3).setVisible(false);
            loader = null;

            loader = new FXMLLoader(getClass().getResource("/sample/fxml/menuLeft.fxml"));
            AnchorPane pane = loader.load();
            loader = null;

            drawer.setSidePane(pane);
        }catch (Exception e){e.printStackTrace();}

        Main.getL().langForMain(cryptChoosePaneBut, createBut, chooseBut, cryptFilesPaneBut, cryptingLabel, profilesLabel);
        Main.getL().chgLangForMain();

        HamburgerBackArrowBasicTransition burgertask = new HamburgerBackArrowBasicTransition(menuHamburger);
        burgertask.setRate(-1);
        drawer.setVisible(false);
        menuHamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, (MouseEvent e) ->{
            if(!drawer.isOpened()) {
                drawer.setVisible(true);
                drawer.open();
                burgertask.setRate(1);
                burgertask.play();
            }
            else{
                drawer.close();
                burgertask.setRate(-1);
                burgertask.play();

                //waiting(1);
                //drawer.setVisible(false);

            }
        });
        drawer.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if(!drawer.isOpened()) drawer.setVisible(false);
        });

        paneMain.setOnMousePressed(e -> {
            xOffset = e.getSceneX();
            yOffset = e.getSceneY();
            paneMain.setCursor(Cursor.CLOSED_HAND);
        });

        paneMain.setOnMouseDragged(e -> {
            Main.getStageObj().setX(e.getScreenX() - xOffset);
            Main.getStageObj().setY(e.getScreenY() - yOffset);
        });
        paneMain.setOnMouseReleased(event -> {
            paneMain.setCursor(Cursor.DEFAULT);
        });
        drawer.setOnMouseReleased(event ->{
            if(drawer.isClosed())
                drawer.setVisible(false);
        });
    }

    private void waiting(int i){
        try {
        TimeUnit.SECONDS.wait(1, 1);
         }catch (Exception exc){exc.printStackTrace();}
    }

    @FXML
    void closeListener(MouseEvent event){
        System.exit(0);
    }
    @FXML
    void mouse(MouseEvent event){

    }
    @FXML
    void ChooseProfile(ActionEvent event){

        stackPane.getChildren().get(0).setVisible(false);
        stackPane.getChildren().get(1).setVisible(true); active = 2;
        stackPane.getChildren().get(2).setVisible(false);
        stackPane.getChildren().get(3).setVisible(false);
            Status = false;

    }

    @FXML
    void CreateProfile(ActionEvent event){


        stackPane.getChildren().get(0).setVisible(false);
        stackPane.getChildren().get(1).setVisible(false);
        stackPane.getChildren().get(2).setVisible(true);active = 3;
        stackPane.getChildren().get(3).setVisible(false);

    }

    @FXML
    void cryptPaneListener(ActionEvent event){

        stackPane.getChildren().get(0).setVisible(true); active = 1;
        stackPane.getChildren().get(1).setVisible(false);
        stackPane.getChildren().get(2).setVisible(false);
        stackPane.getChildren().get(3).setVisible(false);
    }
    @FXML
    void cryptFilesListener(ActionEvent event){
        stackPane.getChildren().get(0).setVisible(false);
        stackPane.getChildren().get(1).setVisible(false);
        stackPane.getChildren().get(2).setVisible(false);
        stackPane.getChildren().get(3).setVisible(true);active = 4;
    }
    @FXML
    void svernut(MouseEvent event){

    }

    @FXML
    void imageCryptClicked(MouseEvent event){
        if(languages==null) {languages = new Languages(); }
        languages.setLang(Dispatcher.langInt);
        lang = languages.getMainLang();
        if(cryptB){
            imageCrypt.setImage(imgplus);
            try{
                cryptChoosePaneBut.setText(null);
                cryptFilesPaneBut.setText(null);
                cryptChoosePaneBut.setPrefHeight(0);
                cryptFilesPaneBut.setPrefHeight(0);
                cryptChoosePaneBut.setVisible(false);
                cryptFilesPaneBut.setVisible(false);
            }catch (Exception e){e.printStackTrace();}
        }else {
            imageCrypt.setImage(imgminus);
            try{
                cryptChoosePaneBut.setText(lang[10]);
                cryptFilesPaneBut.setText(lang[13]);
                cryptChoosePaneBut.setPrefHeight(39);
                cryptFilesPaneBut.setPrefHeight(39);
                cryptChoosePaneBut.setVisible(true);
                cryptFilesPaneBut.setVisible(true);
            }catch (Exception e){e.printStackTrace();}
        }
        cryptB = !cryptB;

    }
    @FXML
    void imageProfileClicked(MouseEvent event){
        if(languages==null) {languages = new Languages();}
        languages.setLang(Dispatcher.langInt);
        lang = languages.getMainLang();
        if(profileB){
            imageProfile.setImage(imgplus);
            try{
                createBut.setText(null);
                chooseBut.setText(null);
                createBut.setPrefHeight(0);
                chooseBut.setPrefHeight(0);
                createBut.setVisible(false);
                chooseBut.setVisible(false);
            }catch (Exception e){e.printStackTrace();}
        }else {
            imageProfile.setImage(imgminus);
            try{
                createBut.setText(lang[3]);
                chooseBut.setText(lang[2]);
                createBut.setPrefHeight(39);
                chooseBut.setPrefHeight(39);
                createBut.setVisible(true);
                chooseBut.setVisible(true);
            }catch (Exception e){e.printStackTrace();}
        }
        profileB = !profileB;

    }
    public static int getActive(){return active;}
    public static String getKeys(){return keys;}

}
