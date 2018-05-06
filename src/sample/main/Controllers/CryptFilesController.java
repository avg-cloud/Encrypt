package sample.main.Controllers;

import com.jfoenix.controls.*;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import sample.main.Dispatcher;
import sample.main.Main;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.*;
import javax.xml.bind.DatatypeConverter;
import java.awt.event.ActionListener;
import java.io.*;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * Created by Samsung on 23.04.2018.
 */
public class CryptFilesController {

    @FXML
    private Label pathOutputFIle;
    @FXML
    private JFXButton outputFileButton;
    @FXML
    private Label pathInputFIle;
    @FXML
    private JFXRadioButton cryptMode;
    @FXML
    private JFXRadioButton decryptMode;
    @FXML
    private JFXButton startButton;
    @FXML
    private JFXButton inputFileButton;
    @FXML
    private JFXSpinner spinnerLoad;
    @FXML private ImageView outputImage;
    @FXML private ImageView inputImage;
    @FXML private AnchorPane thisPane;
    @FXML private StackPane dialogPane;
    protected boolean wasCrypted = false;
    protected String outputFilePath="";
    protected String inputFileAdd ="";
    protected String inputFilePath = "";
    protected FileInputStream inputFile;
    protected FileOutputStream outputFile;
    protected static CryptFilesController controller;
    protected static int mod;
    protected String fileExtension = "";
    protected File inputSelectedFile;
    protected File outputSelectedFile;
    private int progress = 0;
    @FXML
    void initialize(){
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }catch (Exception e){e.printStackTrace();}
        Main.getL().langForCryptFiles(inputFileButton, outputFileButton, startButton, cryptMode, decryptMode);
        Main.getL().chgLangForCryptFiles();
        spinnerLoad.setVisible(true);
        Timer timer = new Timer(1, new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                if(progress == 100){
                    progress = 0;
                }else {
                    progress += 1;
                    spinnerLoad.setProgress(progress);
                }
            }
        });

    }

    @FXML
    void selectInputFile(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(thisPane.getScene().getWindow());
        inputSelectedFile = selectedFile;
                //получаем путь выбранного файла
                inputFilePath = selectedFile.getAbsolutePath();;
                //устанавливаем в объект text путь файла
            if(!selectedFile.getAbsolutePath().contains(".crypt")) {
                inputFileAdd = ".crypt";
                cryptMode.setSelected(true);
                decryptMode.setSelected(false);
            } else {
                cryptMode.setSelected(true);
                decryptMode.setSelected(false);
            }
            pathInputFIle.setText(selectedFile.getAbsolutePath());

    }

    @FXML
    void selectOutputFile(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
            //получаем выбранный файл
        File selectedFile = fileChooser.showSaveDialog(thisPane.getScene().getWindow());
        outputSelectedFile = selectedFile;
            //получаем путь выбранного файла
        outputFilePath = selectedFile.getAbsolutePath();
            //устанавливаем в объект text путь файла
        pathOutputFIle.setText(selectedFile.getAbsolutePath()+inputFileAdd);
        inputFileAdd="";
    }
    @FXML
    void cryptRadioButton(ActionEvent event){
        decryptMode.setSelected(false);
    }
    @FXML
    void decryptRadioButton(ActionEvent event){
        cryptMode.setSelected(false);
    }
    @FXML
    void startCrypt(ActionEvent event) {
        if(inputSelectedFile!=null && outputSelectedFile!=null) {
            try {
            //spinnerLoad.setVisible(true);
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        crypt();
                    }catch (Exception e){e.printStackTrace();}
                }
            });
                if (cryptMode.isSelected()) {
                    mod = Cipher.ENCRYPT_MODE;
                    thread.start();
                }
                if (decryptMode.isSelected()) {
                    mod = Cipher.DECRYPT_MODE;
                    thread.start();
                }
                pathInputFIle.setText("Path: None");
                pathOutputFIle.setText("Path: None");
            }catch (Exception e){e.printStackTrace();}
        }else showDialog("Error", "You did not select a file!");
    }
    protected void crypt() throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IOException, InvalidAlgorithmParameterException {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
//        SecretKeySpec skey = new SecretKeySpec(DatatypeConverter.parseBase64Binary(Dispatcher.getK()), "AES");
        SecretKeySpec skey = new SecretKeySpec(Base64.getDecoder().decode(Dispatcher.getK()), "AES");
        byte[] bytes = new byte[1024];
        int numBytes;
        if(mod == Cipher.ENCRYPT_MODE) {
            inputFile = new FileInputStream(inputSelectedFile);
            fileExtension = getFileExtension(inputSelectedFile);//получаем расширение файла и его длину, чтобы записать в файл
            //System.out.println(fileExtension);//
            byte[] extension = fileExtension.getBytes("UTF-8");
            String s =  extension.length + "";
            //System.out.println(s);//
            byte[] lengthExtension =  s.getBytes("UTF-8");
            File file = new File(outputSelectedFile.getAbsolutePath()+".crypt");
            outputFile = new FileOutputStream(file);
            outputFile.write(lengthExtension);
            outputFile.write(extension);
            //Create init vector and write to file
            byte[] ivSpec = new byte[16];
            SecureRandom rnd = new SecureRandom();
            rnd.nextBytes(ivSpec);
            outputFile.write(ivSpec);
            cipher.init(mod, skey, new IvParameterSpec(ivSpec));

            CipherOutputStream cos = new CipherOutputStream(outputFile, cipher);
            while ((numBytes = inputFile.read(bytes)) != -1){
                cos.write(bytes, 0, numBytes);
            }
            inputSelectedFile = null;
            outputSelectedFile = null;
            cos.flush();
            cos.close();
            inputFile.close();
        }
        if (mod == Cipher.DECRYPT_MODE){
            inputFile = new FileInputStream(inputSelectedFile);
            byte[] len = new byte[1];
            inputFile.read(len);
            int lengthExtension = Integer.parseInt(new String(len, "UTF-8"));
            //System.out.println(lengthExtension);//
            byte[] ext = new byte[lengthExtension];
            inputFile.read(ext);
            String extension = new String(ext, "UTF-8");
            //System.out.println(extension);//
            File file = new File(outputSelectedFile.getAbsolutePath()+extension);
            outputFile = new FileOutputStream(file);
            //get init vector from file
            byte[] ivSpec = new byte[16];
            inputFile.read(ivSpec);
            cipher.init(mod, skey, new IvParameterSpec(ivSpec));

            CipherInputStream cis = new CipherInputStream(inputFile, cipher);
            while ((numBytes = cis.read(bytes)) != -1){
                outputFile.write(bytes, 0, numBytes);
            }
            inputSelectedFile = null;
            outputSelectedFile = null;
            outputFile.flush();
            outputFile.close();
            cis.close();
        }
        CryptFilesController.controller.showDialog("Succesful", "Your operation was succesful!");
    }
    private static String getFileExtension(File file) {
        String fileName = file.getName();
        // если в имени файла есть точка и она не является первым символом в названии файла
        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
            // то вырезаем все знаки после последней точки в названии файла, то есть ХХХХХ.txt -> txt
            return fileName.substring(fileName.lastIndexOf("."));
            // в противном случае возвращаем заглушку, то есть расширение не найдено
        else return "";
    }
    public synchronized void  showDialog(String name, String text) {
        JFXDialogLayout content = new JFXDialogLayout();
        content.setHeading(new Text(name));
        content.setBody(new Text(text));
        JFXButton ok = new JFXButton();
        JFXDialog dialog = new JFXDialog(dialogPane, content, JFXDialog.DialogTransition.CENTER);
        ok.addEventHandler(ActionEvent.ANY, e ->{
            dialog.show();
            dialogPane.setVisible(false);
        });
        dialogPane.setVisible(true);
        dialog.show();
    }
    public static void setController(CryptFilesController con){controller = (CryptFilesController)con; }
}
