package sample.main.data;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.Serializable;

/**
 * Created by Samsung on 22.03.2018.
 */
public class profile implements Serializable{
    private String Name, Key, Password;
    private boolean passworded;
    private transient Image Img1 = new Image("sample/fxml/images/zamokOpen.png");
    private transient Image Img2 = new Image("sample/fxml/images/zamokClose.jpg");
    private transient ImageView Img = new ImageView(Img1);
    public profile(String name, String key, String pas, boolean psded){
        this.Name = name;
        this.Key = key;
        this.Password = pas;
        this.passworded = psded;
        Img.setFitHeight(20);
        Img.setFitWidth(20);
        if(psded == true){
            this.Img.setImage(Img2);
        }
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getKey() {
        return Key;
    }

    public void setKey(String key) {
        this.Key = key;
    }

    public void setPassword(String pas){this.Password = pas;}

    public String getPassword(){return this.Password;}

    public boolean getPassworded(){return this.passworded;}

    public ImageView getImg(){
        Img1 = new Image("/sample/fxml/images/zamokOpen.png");
        Img2 = new Image("/sample/fxml/images/zamokClose.jpg");
        Img = new ImageView(Img1);
        if(passworded==true)
            Img.setImage(Img2);
        Img.setFitHeight(20);
        Img.setFitWidth(20);
        return this.Img;
    }
}
