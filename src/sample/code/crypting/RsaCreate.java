package sample.code.crypting;

/**
 * Полезно с сайта
 * https://stackoverflow.com/questions/18757114/java-security-rsa-public-key-private-key-code-issue
 */


import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.util.Base64;


public class RsaCreate {

    private static String key, privateKey, publicKey, exponenta, modulka;
    private static String strCipherE, strCipherD;
    private static String strOpenTextE, strOpenTextD;
    private static Cipher cipher;
    private static int mode = 1;
    private static PublicKey pubKey;
    private static PrivateKey priKey;
    private static RSAPublicKeySpec pub;
    private static BigInteger modulus, exponent;
    private static RSAPrivateKeySpec priv;
    private static byte[] deStr;
    private static int count = 0;


    public static void Start() {
        try {
            Shifrated();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void regenKey2() throws NoSuchAlgorithmException, InvalidKeySpecException {
        KeyPairGenerator pairgen = KeyPairGenerator.getInstance("RSA");
        KeyPair keypair = pairgen.generateKeyPair();
        pubKey = keypair.getPublic();
        priKey = keypair.getPrivate();
        KeyFactory fact = KeyFactory.getInstance("RSA");
        pub = fact.getKeySpec(pubKey,
                RSAPublicKeySpec.class);

        publicKey = (""+pub.getModulus() + pub.getPublicExponent());
    }

    private static void Shifrated() throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, InvalidKeySpecException, IOException {

        cipher = Cipher.getInstance("RSA");

        if (mode == 1) {
            cipher.init(Cipher.ENCRYPT_MODE, pubKey);

//            byte[] bytes = cipher.doFinal(strOpenTextE.getBytes(StandardCharsets.UTF_8));//Конвертит строку в поток байтов с кодировкой UTF-8 и шифрует
            byte[] bytes = cipher.doFinal(Base64.getDecoder().decode(strOpenTextE));
//            strCipherE =  DatatypeConverter.printHexBinary(bytes); //поток байтов конвертится в строку как HexCode
            strCipherE =  Base64.getEncoder().encodeToString(bytes);



        } else if (mode == 2) {
            cipher.init(Cipher.DECRYPT_MODE, priKey);

//            byte[] bytes1 = cipher.doFinal(DatatypeConverter.parseHexBinary(strCipherD)); //HexCode конвертит в поток байтов и расшифровывает его
            byte[] bytes1 = cipher.doFinal(Base64.getDecoder().decode(strCipherD));
//            strOpenTextD =  new String(bytes1);//Байты преобразуются в строку из кодировки UTF-8
            strOpenTextD =  Base64.getEncoder().encodeToString(bytes1);

        }

    }


    public static void madePubKey() {
        char[] arr = publicKey.toCharArray();
        String modul = "";
        String expon = "";
        for(int i = 0; i < publicKey.length()-5; i++){
            modul += arr[i];
        }
        for (int i = publicKey.length() - 5; i < publicKey.length(); i++){
            expon += arr[i];
        }

        modulus = new BigInteger(modul);
        exponent = new BigInteger(expon);
        try {
            KeyFactory fact = KeyFactory.getInstance("RSA");
            pubKey = fact.generatePublic(new RSAPublicKeySpec(modulus, exponent));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void regenKey() {
        try {
            regenKey2();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void setStrOpenTextE(String str) {
        strOpenTextE = str;
    }

    public static String getStrCipherE() {
        return strCipherE;
    }
    public static void setStrCipherD(String str) {
        strCipherD = str;
    }

    public static String getStrOpenTextD() {
        return strOpenTextD;
    }

    public static void setPublicKey(String k) {
        publicKey = k;
    }

    public static String getPublicKey() {
        return publicKey;
    }

    public static void setMode(int mod) {
        mode = mod;
    }//1-crypt 2-decrypt
    public static byte[] deCryptStr(){return deStr;}
    public static String getPrivateKey() {
        return privateKey;
    }

    public static void setModulka(String s) {
        modulka = s;
    }

    public static void setExponenta(String s) {
        exponenta = s;
    }

}
