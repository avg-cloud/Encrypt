package sample.code.crypting;


import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.*;
import javax.xml.bind.DatatypeConverter;
import java.util.Base64;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.*;
public class Crypter{

    private static String key;
    private static String strToEncrypt, encrypted,  strToDecrypt, decrypted;
    private static String keysa;
    private static int mode;//1-Шифровать 2- Расшифровать
    private static Key secretKey;
    public Crypter(){

    }
    public static void Start(){
        try {
            Crypter.methodShifrated();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private static void methodShifrated() throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException, UnsupportedEncodingException {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

//            SecretKeySpec skey = new SecretKeySpec(DatatypeConverter.parseBase64Binary(keysa), "AES");
        SecretKeySpec skey = new SecretKeySpec(Base64.getDecoder().decode(keysa), "AES");
            if(mode == 1){
                SecureRandom rnd = new SecureRandom();
                byte[] ivSpec = new byte[16];
                rnd.nextBytes(ivSpec);
                cipher.init(Cipher.ENCRYPT_MODE, skey, new IvParameterSpec(ivSpec));
                byte[] cryptoText = cipher.doFinal(strToEncrypt.getBytes(StandardCharsets.UTF_8));
//                byte[] cryptoText = cipher.doFinal(Base64.getDecoder().decode(strToEncrypt));
                byte[] result = new byte[16 + cryptoText.length];
                for (int i = 0; i < 16; i++){
                    result[i] = ivSpec[i];
                }
                for (int i = 0; i < cryptoText.length; i++){
                    result[i + 16] = cryptoText[i];
                }
//                encrypted = DatatypeConverter.printBase64Binary(result);
                encrypted = Base64.getEncoder().encodeToString(result);

            }else if(mode == 2){
                byte[] ivSpec = new byte[16];
//                byte[] fullArr = DatatypeConverter.parseBase64Binary(strToDecrypt);
                byte[] fullArr = Base64.getDecoder().decode(strToDecrypt);

                for (int i = 0; i < 16; i++){
                    ivSpec[i] = fullArr[i];
                }
                byte[] bytesToDecrypt = new byte[fullArr.length-16];
                for (int i = 0; i< bytesToDecrypt.length; i++){
                    bytesToDecrypt[i] = fullArr[i+16];
                }
                cipher.init(Cipher.DECRYPT_MODE, skey, new IvParameterSpec(ivSpec));
                byte[] result = cipher.doFinal(bytesToDecrypt);

                decrypted = new String(result, "UTF-8");
//                decrypted = Base64.getEncoder().encodeToString(result);
            }else JOptionPane.showMessageDialog(null, "Mode didn't selected");
    }
    public static String getRandomKey()  {
        try{
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            kgen.init(256);
            secretKey = kgen.generateKey();
//       key = DatatypeConverter.printBase64Binary(secretKey.getEncoded());
            key = Base64.getEncoder().encodeToString(secretKey.getEncoded());
        }catch (Exception e){e.printStackTrace();}
        return key;
    }
    public static void setMode(int i){
        mode = i;
    }


    public static void setKey(String k){
        keysa = k;
    }

    public static String getKeysa() {
        return keysa;
    }

    public static void setStrToEncrypt(String strToEncrypt) {
        Crypter.strToEncrypt = strToEncrypt;
    }

    public static String getEncrypted() {
        return encrypted;
    }

    public static void setStrToDecrypt(String strToDecrypt) {
        Crypter.strToDecrypt = strToDecrypt;
    }

    public static String getDecrypted() {
        return decrypted;
    }
}
