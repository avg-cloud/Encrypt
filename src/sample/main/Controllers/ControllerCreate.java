package sample.main.Controllers;


import com.jfoenix.controls.*;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.MouseEvent;
import sample.main.Dispatcher;
import sample.main.Main;
import sample.main.data.dataProfiles;
import sample.main.data.Languages;
import sample.main.data.profile;
import sample.code.crypting.Crypter;
import sample.code.crypting.RsaCreate;

import javax.swing.*;

public class ControllerCreate {

    @FXML
    private JFXTextField nameProfile;
    @FXML
    private JFXTextField profileKey;
    @FXML
    private JFXTextField newFriendKey;
    @FXML
    private JFXTextField friendKey;
    @FXML
    private JFXTextField yourKey;
    @FXML
    private JFXTextField getKey;
    @FXML
    private JFXTextField publicKey;
    @FXML private JFXCheckBox checkPass;
    @FXML private JFXPasswordField passwordField;
    private profile pf;
    @FXML private JFXButton generateBut;
    @FXML private JFXButton useBut;
    @FXML private JFXButton saveBut;
    final ClipboardContent clipboardContent = new ClipboardContent();
    Languages languages;

    @FXML
    void initialize(){
        languages = new Languages();
        Main.getL().langForCreate(generateBut, useBut, saveBut, friendKey, yourKey, getKey, newFriendKey, profileKey, nameProfile, publicKey);
        Main.getL().chgLangForCreate();
    }

    @FXML
    void saveMethod(ActionEvent event){
        try {
            dataProfiles data = Dispatcher.getData();
            if(checkPass.isSelected()){
                if (!nameProfile.getText().equals("") && !profileKey.getText().equals("") && !passwordField.getText().equals("")){
                    pf = new profile(nameProfile.getText(), profileKey.getText(), passwordField.getText(), true);
                    data.addProfile(pf);
                }
                else{
                    JOptionPane.showMessageDialog(null, "Fill in all fields pls", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }else {
                if (!nameProfile.getText().equals("") && !profileKey.getText().equals("")) {
                    pf = new profile(nameProfile.getText(), profileKey.getText(), null, false);
                    data.addProfile(pf);
                }else{
                    JOptionPane.showMessageDialog(null, "Fill in all fields pls", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            Dispatcher.saveProfile(data);
            Dispatcher.setData(data);
            Dispatcher.saveList(pf);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @FXML
    void useMethod(ActionEvent event){

        RsaCreate.setPublicKey(friendKey.getText());
        RsaCreate.madePubKey();
        RsaCreate.setStrOpenTextE(yourKey.getText());

        RsaCreate.setMode(1);
        RsaCreate.Start();

        getKey.setText(RsaCreate.getStrCipherE());
        profileKey.setText(yourKey.getText());
    }
    @FXML
    void generateKey(ActionEvent event){
        RsaCreate.regenKey();
        String pubKey1 = RsaCreate.getPublicKey();

        publicKey.setText(pubKey1);

    }
    @FXML
    void generateFieldClick(MouseEvent event){
        publicKey.selectAll();
//        clipboardContent.putString(publicKey.getText());
//        Clipboard.getSystemClipboard().setContent(clipboardContent);
    }
    @FXML
    void passwordFieldNeed(ActionEvent event){
        if(checkPass.isSelected()) passwordField.setVisible(true);
        else passwordField.setVisible(false);
    }

    @FXML
    void getFieldClick(MouseEvent event){
        getKey.selectAll();
    }
    @FXML
    void mouse(Event event){

        if(!newFriendKey.getText().equals("")) {
            RsaCreate.setStrCipherD(newFriendKey.getText());
            RsaCreate.setMode(2);
            RsaCreate.Start();

                profileKey.setText(RsaCreate.getStrOpenTextD());

        }
            if(yourKey.getText().equals("")){
            yourKey.setText(Crypter.getRandomKey());
            }
    }



    }


