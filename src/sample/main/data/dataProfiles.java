package sample.main.data;


import java.io.Serializable;
import java.util.ArrayList;

public class dataProfiles implements Serializable{

    private ArrayList<profile> profiles = new ArrayList<profile>();
    public static int type = 1;
    private int langInt;
    public dataProfiles(){
        if(profiles.size() < 1) {
            profile pf = new profile("Standart","0000", null, false);
            profiles.add(pf);
        }
    }
    public void addProfile(profile pf){
       // profile pf = new profile(name,key);
        profiles.add(pf);
    }

    public void setLangInt(int l){langInt = l;}
    public int getLangInt(){return langInt;}
    public  void deleteProfile(profile pf){
        profiles.remove(pf);
    }
    public profile getProfile(int id){
        return profiles.get(id);
    }
    public int getSize(){
        return profiles.size();
    }
    public void changeProfile(profile pchange, profile pselect){
        profiles.set(profiles.indexOf(pselect), pchange);
    }
    public void setList(ArrayList<profile> ls){profiles = ls;}
    public ArrayList<profile> getList(){
        return profiles;
    }


}
