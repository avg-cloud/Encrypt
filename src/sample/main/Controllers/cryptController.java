package sample.main.Controllers;


        import com.jfoenix.controls.JFXButton;
        import com.jfoenix.controls.JFXTextField;
        import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.scene.control.TextArea;
        import javafx.scene.input.MouseEvent;
        import sample.main.Dispatcher;
        import sample.main.Main;
        import sample.main.data.Languages;
        import sample.code.crypting.Crypter;


public class cryptController {

    @FXML private JFXTextField keyShifr;
    @FXML private JFXButton cryptBut;
    @FXML private TextArea TakeText;
    @FXML private JFXButton deCryptBut;
    private static boolean Status = false;
    Languages languages;
    @FXML
    void initialize(){
        keyShifr.setText(Dispatcher.getK());
        languages = new Languages();

        Main.getL().langForCrypt(TakeText, deCryptBut, cryptBut);
        Main.getL().chgLangForCrypt();
    }

    @FXML
    void mouse(MouseEvent event){
        if((keyShifr.getText().equals("")|| keyShifr.getText() != Dispatcher.getK()) && Status == false)
            keyShifr.setText(Dispatcher.getK());
    }
    @FXML
    void deCryptButton(ActionEvent event) {
        Crypter.setStrToDecrypt(TakeText.getText());
        Crypter.setMode(2);
        Crypter.setKey(keyShifr.getText());
        Crypter.Start();
        TakeText.setText(Crypter.getDecrypted());

    }

    @FXML
    void CryptButton(ActionEvent event) {

        Crypter.setStrToEncrypt(TakeText.getText());
        Crypter.setMode(1);
        Crypter.setKey(keyShifr.getText());
        Crypter.Start();
        TakeText.setText(Crypter.getEncrypted());
    }


    @FXML
    void fieldMouse(MouseEvent event) {
        Status = true;
    }
    @FXML
    void textClick(MouseEvent event){
        TakeText.selectAll();
    }


}
