package sample.main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.main.Controllers.LanguageChanger;
import sample.main.data.dataProfiles;
import sample.main.data.profile;
import sample.code.crypting.Crypter;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by Samsung on 22.03.2018.
 */
public class Dispatcher {
        private static dataProfiles data = new dataProfiles();
        private ObjectInputStream obj;
        private static String key = Crypter.getRandomKey();
        private static String profileN = "None";
        public static int langInt = 1;
        private static ObservableList<profile> dataList = FXCollections.observableArrayList();
        private static LanguageChanger langChg;
        public static int languageType = 1;
    private ArrayList<profile> alist;




        public Dispatcher(){

            loadData();
            fullDataList();
           // loadMusic();
        }
        public static void saveProfile(dataProfiles dataP){
            try{
                ObjectOutputStream obj = new ObjectOutputStream(new FileOutputStream("profiles"));
                obj.writeObject(dataP);
                obj.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
//    public static void loadMusic(){
//            MusicClass.playSound(musPath);
//    }


    private void loadData(){
            try {
                obj = new ObjectInputStream(new FileInputStream("profiles"));
                data = (dataProfiles) obj.readObject();
                langInt = data.getLangInt();
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                try{if(obj!=null) obj.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        private void fullDataList(){
            alist = data.getList();
            for(int i=0;i<alist.size();i++){
                dataList.add(alist.get(i));
            }
        }
        public static void setLangChg(LanguageChanger lg){langChg = lg;}
        public static LanguageChanger getLangChg(){
            return langChg;
        }
        public static dataProfiles getData(){
            return data;
        }
        public static ObservableList<profile> getDataList(){
            return dataList;
        }
        public static String getProfileN() {
        return profileN;
    }
        public static void setProfileN(String profileN) {
        Dispatcher.profileN = profileN;
    }
        public static void setData(dataProfiles d){
            data = d;
        }
        public static void saveList(profile pf){
            dataList.add(pf);
        }
        public static void deleteFromList(profile pf){
            dataList.remove(pf);
        }
        public static void changeList(profile needChg, profile toChg){
            dataList.set(dataList.indexOf(needChg),toChg);
        }
        public static void setK(String k){
        key = k;
    }
        public static String getK(){return key;}
}
