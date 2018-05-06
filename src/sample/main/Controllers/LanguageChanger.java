package sample.main.Controllers;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import sample.main.Dispatcher;
import sample.main.data.Languages;

public class LanguageChanger {
    private static Languages languages;
    private static String[] lang;
    //Crypt
    private static TextArea TakeText;
    private static JFXButton  ccryptBut;
    private static JFXButton dcb;
    //main
    private JFXButton chooseBut;
    private JFXButton createBut;
    private JFXButton cryptChoosePaneBut;
    private JFXButton cryptFilesPane;
    private Label cryptingLabel;
    private Label profilesLabel;
    //Choose
    private JFXButton chgBut;
    private JFXButton delBut;
    private JFXButton selBut;
    //Create
    private JFXButton generateBut;
    private JFXButton useBut;
    private JFXButton saveBut;
    private JFXTextField friendkeyID;
    private JFXTextField yourkeyID;
    private JFXTextField getID;
    private JFXTextField newfriendkeyID;
    private JFXTextField profilekeyID;
    private JFXTextField nameID;
    private JFXTextField publicKey;
    //Change profile
     private JFXTextField nameStr;
     private JFXTextField keyStr;
     private JFXButton chgButProf;
     private JFXButton cancelBut;
     private JFXTextField passwordField;
    //Crypt flles
    private JFXButton inputFile;
    private JFXButton outputFile;
    private JFXButton startButton;
    private JFXRadioButton encryptMode;
    private JFXRadioButton decryptMode;
    public void langForCrypt(TextArea txt, JFXButton but, JFXButton cryptBut){
        ccryptBut = cryptBut;
        dcb = but;
        TakeText = txt;
    }

    public void chgLangForCrypt(){
        languages = new Languages();
        languages.setLang(Dispatcher.langInt);
        lang = languages.getMainLang();
        dcb.setText(lang[0]);
        ccryptBut.setText(lang[1]);
        TakeText.setText(lang[5]);

        lang=null;
        languages = null;
    }
    //Lang crypt end
    public  void langForMain(JFXButton cryPane, JFXButton crePane, JFXButton choPane, JFXButton files, Label cryptingLab, Label profilesLab){
        chooseBut = choPane;
        cryptChoosePaneBut = cryPane;
        createBut = crePane;
        cryptFilesPane = files;
        cryptingLabel = cryptingLab;
        profilesLabel = profilesLab;
    }
    public void chgLangForMain(){
        languages = new Languages();
        try{languages.setLang(Dispatcher.langInt);
        }catch (Exception e){languages.setLang(1);}
        lang = languages.getMainLang();
        if(Dispatcher.langInt==2){//1-English 2-Russian
            cryptChoosePaneBut.setStyle("-fx-font-size: 13;");
            chooseBut.setStyle("-fx-font-size: 13;");
            createBut.setStyle("-fx-font-size: 13;");
            cryptFilesPane.setStyle("-fx-font-size: 13;");
        }else{
            cryptFilesPane.setStyle("-fx-font-size: 13;");
            cryptChoosePaneBut.setStyle("-fx-font-size: 13;");
            chooseBut.setStyle("-fx-font-size: 13;");
            createBut.setStyle("-fx-font-size: 13;");
        }
        cryptChoosePaneBut.setText(lang[10]);
        chooseBut.setText(lang[2]);
        createBut.setText(lang[3]);
        cryptFilesPane.setText(lang[13]);
        cryptingLabel.setText(lang[14]);
        profilesLabel.setText(lang[15]);
        lang=null;
        languages = null;
    }
    //Lang main end
    public void langForChoose(JFXButton selBut1, JFXButton chgBut1, JFXButton delBut1){
        selBut = selBut1;
        chgBut = chgBut1;
        delBut = delBut1;
    }

    public void chgLangForChoose(){
        languages = new Languages();
        languages.setLang(Dispatcher.langInt);
        if (Dispatcher.langInt == 2) {
            chgBut.setStyle("-fx-font-size: 11;");
            selBut.setStyle("-fx-font-size: 11");
            delBut.setStyle("-fx-font-size: 11");
        }else{
            chgBut.setStyle("-fx-font-size: 11");
            selBut.setStyle("-fx-font-size: 11");
            delBut.setStyle("-fx-font-size: 11");
        }
        String[] lang = languages.getChooseLang();
        selBut.setText(lang[0]);
        chgBut.setText(lang[1]);
        delBut.setText(lang[2]);
        lang = null;
        languages = null;
    }
    //Lang choose end

    public void langForCreate(JFXButton generateBut1, JFXButton useBut1, JFXButton saveBut1, JFXTextField friendkeyID1,
                              JFXTextField yourkeyID1, JFXTextField getID1, JFXTextField newfriendkeyID1, JFXTextField profilekeyID1, JFXTextField nameID1,
                              JFXTextField publicK){
        generateBut = generateBut1;
        useBut = useBut1;
        saveBut = saveBut1;
        friendkeyID = friendkeyID1;
        yourkeyID = yourkeyID1;
        getID = getID1;
        newfriendkeyID = newfriendkeyID1;
        profilekeyID = profilekeyID1;
        nameID = nameID1;
        publicKey = publicK;
    }
    public void chgLangForCreate(){
        languages = new Languages();
        languages.setLang(Dispatcher.langInt);
        String[] lang = languages.getCreateLangLang();
        generateBut.setText(lang[0]);
        useBut.setText(lang[1]);
        saveBut.setText(lang[2]);
        friendkeyID.setPromptText(lang[3]);
        yourkeyID.setPromptText(lang[4]);
        getID.setPromptText(lang[5]);
        newfriendkeyID.setPromptText(lang[6]);
        profilekeyID.setPromptText(lang[7]);
        nameID.setPromptText(lang[8]);
        publicKey.setPromptText(lang[9]);
        lang = null;
        languages = null;
    }
    //Lang create end
    public void langForChangeProfile(JFXButton chgButProf1,JFXButton cancelBut1, JFXTextField nameStr1, JFXTextField keyStr1, JFXTextField passwordField1){
        chgButProf = chgButProf1;
        nameStr = nameStr1;
        keyStr = keyStr1;
        cancelBut = cancelBut1;
        passwordField = passwordField1;
    }
    public void chgLangForChangeProfile(){
        languages = new Languages();
        languages.setLang(Dispatcher.langInt);
        String[] lang = languages.getChgLang();
        nameStr.setPromptText(lang[0]);
        keyStr.setPromptText(lang[1]);
        chgButProf.setText(lang[2]);
        cancelBut.setText(lang[3]);
        passwordField.setPromptText(lang[4]);
        lang = null;
        languages = null;
    }
    //Lang change profile end
    public void langForCryptFiles(JFXButton in, JFXButton os, JFXButton start, JFXRadioButton encrypt, JFXRadioButton decrypt){
        inputFile = in;
        outputFile = os;
        startButton = start;
        encryptMode = encrypt;
        decryptMode = decrypt;
    }
    public void chgLangForCryptFiles(){
        languages = new Languages();
        languages.setLang(Dispatcher.langInt);
        String[] lang = languages.getFilesLang();
        inputFile.setText(lang[0]);
        outputFile.setText(lang[1]);
        startButton.setText(lang[2]);
        encryptMode.setText(lang[3]);
        decryptMode.setText(lang[4]);
        lang = null;
        languages = null;
    }
    //Lang change crypt files end
}
