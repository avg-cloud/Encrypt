package sample.main.Controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXPasswordField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.code.crypting.Crypter;
import sample.main.Dispatcher;
import sample.main.Main;
import sample.main.data.Languages;
import sample.main.data.dataProfiles;
import sample.main.data.profile;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Samsung on 17.03.2018.
 */
public class ControllerChoose {

    @FXML private TableView<profile> profileTable;
    @FXML private TableColumn<profile, String> profilesColumn;
    @FXML private TableColumn<profile, ImageView> profilesColumnPassword;
    @FXML private JFXButton chooseBut;
    @FXML private JFXButton changeBut;
    @FXML private JFXButton deleteBut;
    @FXML private AnchorPane paneThis;
    @FXML private StackPane dialog;
    private profile selItem;
    private static Stage stageChs;
    private static Label lbl;
    private static String nameC, keyC;
    private static profile selectedProfileC;
    protected static ControllerChoose Controller;

    protected static int methodNumber;
    @FXML
    public void initialize(){
        profilesColumn.setCellValueFactory(new PropertyValueFactory<profile, String>("Name"));


        profileTable.setItems(Dispatcher.getDataList());
        Main.getL().langForChoose(chooseBut, changeBut, deleteBut);
        Main.getL().chgLangForChoose();

        dialog.setVisible(false);
    }

    @FXML
    void chooseMethod(ActionEvent event){

        if(profileTable.getSelectionModel().getSelectedItem()==null){
            JOptionPane.showMessageDialog(null,"You didn't select a profile", new String("Error"), JOptionPane.ERROR_MESSAGE);
        }else {
             if(profileTable.getSelectionModel().getSelectedItem().getPassworded()==true) {
                 methodNumber = 1;
                 checkPassword();
             }else{
                 selectItem();
             }
        }
    }
        private void selectItem(){
            try {
                selItem = profileTable.getSelectionModel().getSelectedItem();
                Crypter.setKey(selItem.getKey());
                Dispatcher.setProfileN(selItem.getName());
                Dispatcher.setK(selItem.getKey());
                lbl.setText(Dispatcher.getProfileN());
            } catch (NullPointerException e) {
                JOptionPane.showMessageDialog(null, "You didn't select a profile", new String("Error"), JOptionPane.ERROR_MESSAGE);
            }
        }
    @FXML
    void changeButton(ActionEvent event){
        if(profileTable.getSelectionModel().getSelectedItem()==null){
            JOptionPane.showMessageDialog(null,"You didn't select a profile", new String("Error"), JOptionPane.ERROR_MESSAGE);
        }else {
            if(profileTable.getSelectionModel().getSelectedItem().getPassworded()==true) {
                methodNumber = 2;
                checkPassword();
            }else{
                chgProf();
            }
        }
    }

    private void chgProf(){

        selItem = profileTable.getSelectionModel().getSelectedItem();
        this.nameC = selItem.getName();
        this.keyC = selItem.getKey();
        this.selectedProfileC = selItem;
        try {
            stageChs = new Stage();
            stageChs.setTitle("Change");
            stageChs.setResizable(false);
            Parent root = FXMLLoader.load(getClass().getResource("/sample/fxml/changeWindow.fxml"));
            stageChs.getIcons().add(new Image(getClass().getResourceAsStream("/sample/fxml/images/icon4.png")));
            Scene scene = new Scene(root, 200, 200);
            scene.getStylesheets().add(getClass().getResource("/sample/fxml/styles/configStyle.css").toExternalForm());
            stageChs.setScene(scene);
            stageChs.initModality(Modality.WINDOW_MODAL);
            stageChs.initOwner(paneThis.getScene().getWindow());
            stageChs.initStyle(StageStyle.UNDECORATED);
            stageChs.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void deleteButton(ActionEvent event) {
        if (profileTable.getSelectionModel().getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "You didn't select a profile", new String("Error"), JOptionPane.ERROR_MESSAGE);
        } else {
            if (profileTable.getSelectionModel().getSelectedItem().getPassworded() == true) {

                methodNumber = 3;
                checkPassword();
            } else {
                    deleteProf();
                    }
                }
            }



    private void deleteProf(){
        selItem = profileTable.getSelectionModel().getSelectedItem();
        String title = "Delete";
        if (JOptionPane.showConfirmDialog(null, new String("Are you sure?"), title, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            dataProfiles dataP = Dispatcher.getData();
            dataP.deleteProfile(selItem);
            Dispatcher.deleteFromList(selItem);
            Dispatcher.saveProfile(dataP);
            Dispatcher.setData(dataP);
        }
    }

    private void checkPassword(){
        JFXDialogLayout content = new JFXDialogLayout();
        content.setHeading(new Text("Input password"));
        JFXPasswordField psw = new JFXPasswordField();
        psw.setPrefWidth(20);
        content.setBody(psw);
        JFXDialog fxd = new JFXDialog(dialog, content, JFXDialog.DialogTransition.CENTER);
        JFXButton Ok = new JFXButton("Ok");
        JFXButton Cancel = new JFXButton("Cancel");
        Ok.addEventHandler(ActionEvent.ANY, e -> {
            if (psw.getText().equals(profileTable.getSelectionModel().getSelectedItem().getPassword())) {
                if(ControllerChoose.methodNumber==1){
                    selectItem();
                }if(ControllerChoose.methodNumber==2){
                    chgProf();
                }if(ControllerChoose.methodNumber==3){
                    deleteProf();
                }
                fxd.close();
                dialog.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(null, "Incorrect password", new String("Incorrect"), JOptionPane.ERROR_MESSAGE);
            }
        });
        Cancel.addEventHandler(ActionEvent.ANY, e -> {
            fxd.close();
            dialog.setVisible(false);
        });
        content.setActions(Ok, Cancel);
        dialog.setVisible(true);
        fxd.show();
    }

    public static void setLbl(Label l){
        lbl = l;
    }
    public static void closeChs(){
        stageChs.close();
    }
    public static String getNameC(){return nameC;}
    public static String getKeyC(){return keyC;}
    public static profile getSelectedProfileC(){return  selectedProfileC;}
    public static void setController(ControllerChoose controller){
        Controller = (ControllerChoose)controller;
    }
}
