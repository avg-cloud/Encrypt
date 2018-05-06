package sample.main.Controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import sample.main.Dispatcher;
import sample.main.Main;
import sample.main.data.dataProfiles;
import sample.main.data.Languages;
import sample.main.data.profile;

;import javax.swing.*;

/**
 * Created by Samsung on 25.03.2018.
 */
public class changeController {
    private static String name, key;
    @FXML private JFXTextField nameField;
    @FXML private JFXTextField keyField;
    @FXML private JFXButton chgBut;
    @FXML private JFXButton cancelBut1;
    @FXML private JFXTextField passwordField;
    private profile profSel;

    Languages languages;
    @FXML
    public void initialize(){
        nameField.setText(ControllerChoose.getNameC());
        keyField.setText(ControllerChoose.getKeyC());
        profSel = ControllerChoose.getSelectedProfileC();
        if(profSel.getPassworded()==true){
            passwordField.setVisible(true);
            passwordField.setText(profSel.getPassword());
        }else{
            passwordField.setText(null);
        }
        languages = new Languages();
        Main.getL().langForChangeProfile(chgBut,cancelBut1, nameField, keyField, passwordField);
        Main.getL().chgLangForChangeProfile();
    }
    @FXML
    void changeButton(ActionEvent event){
        dataProfiles dataP = Dispatcher.getData();
        if(nameField.getText().length()>20) {
            String title = "Name must not be longer than 20";
            JOptionPane.showMessageDialog(null,title,"Error", JOptionPane.ERROR_MESSAGE);
        }else {
            profile pf = new profile(nameField.getText(), keyField.getText(), passwordField.getText(), profSel.getPassworded() );
            dataP.changeProfile(pf, profSel);
            Dispatcher.changeList(profSel, pf);
            Dispatcher.saveProfile(dataP);
            Dispatcher.setData(dataP);
        }
        ControllerChoose.closeChs();
    }
    @FXML
    void cancelButton(ActionEvent event){
        ControllerChoose.closeChs();
    }


}
