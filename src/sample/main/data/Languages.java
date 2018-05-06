package sample.main.data;


import java.io.Serializable;

public class Languages implements Serializable{

    String[] chooseLang = new String[4];
    String[] mainLang = new String[16];
    String[] createLang = new String[10];
    String[] chgLang = new String[5];
    String[] filesLang = new String[5];
    private static int type = 1;//1-English, 2-Russian
    //mainGroup
    public  String deCryptButton,CryptButton,ChooseButton,lang,CreateButton,menuFile,TextF,profileName,menuItemClose, menuItemLanguage, menuItemAbout;
    //chooseGroup
    public  String profilesStr, chooseBut, chgBut, delBut;
    //createGroup
    public  String generateBut, useBut, saveBut, friendKey, yourKey, Get, NewFriendKey, profileKey, nameStr;
    //changeGroup
    public  String chgProfBut, nameProfStr, keyStr;
    //crypt files group
    public String inputFile, outputFile, startButton, decryptMode, encryptMode;
    public void setLang(int t){
        type = t;
        if(type==1){
            mainLang[0] = "Decrypt"; mainLang[1] = "Crypt"; mainLang[2] ="Select profile"; mainLang[3]="Create profile"; mainLang[4]="Menu";
            mainLang[5]="Write your text here..."; mainLang[6] ="Profile:";mainLang[7] = "Language";mainLang[8]="Help"; mainLang[9]="Save and Close"; mainLang[10] = "Text crypting";
            mainLang[11] ="Russian"; mainLang[12] = "English"; mainLang[13] = " Crypt files"; mainLang[14] = "Crypting"; mainLang[15] = "Profiles";

            chooseLang[3]="Profiles";chooseLang[0]="Select";chooseLang[1]="Change";chooseLang[2]="Delete";

            createLang[0]="Generate key";createLang[1]="Use friend key"; createLang[2]="Save profile";createLang[3]="Friend key";createLang[4]="Your key"; createLang[5]="Get";
            createLang[6]="New friend key"; createLang[7]="Profile key"; createLang[8]="Name"; createLang[9] = "Public key";

            chgLang[2]="Change";chgLang[0]="Name";chgLang[1]="Key"; chgLang[3] = "Cancel";chgLang[4] = "Password";

            filesLang[0]="Input file"; filesLang[1]="Output file"; filesLang[2]="Start"; filesLang[3]="Crypt"; filesLang[4]="Decrypt";

        }else{
            mainLang[0] = "Дешифровать";mainLang[1] = "Зашифровать"; mainLang[2] ="Выбрать профиль"; mainLang[3]="Создать профиль"; mainLang[4]="Меню";
            mainLang[5]="Введите ваш текст здесь..."; mainLang[6] ="Профиль:";mainLang[7] = "Язык"; mainLang[8]="Помощь";  mainLang[10] = "Шифрование текста";
            mainLang[9]="Сохранить и закрыть"; mainLang[11] = "Русский"; mainLang[12] = "Английский"; mainLang[13] = "Шифрование файлов"; mainLang[14] = "Шифрование"; mainLang[15] = "Работа с профилями";

            chooseLang[3]="Профили";chooseLang[0]="Выбрать";chooseLang[1]="Изменить";chooseLang[2]="Удалить";

            createLang[0]="Сгенерировать ключ";createLang[1]="Использовать ключ"; createLang[2]="Сохранить";createLang[3]="Ключ друга";createLang[4]="Ваш ключ"; createLang[5]="Получить";
            createLang[6]="Новый ключ"; createLang[7]="Ключ профиля"; createLang[8]="Имя профиля";createLang[9] = "Публичный ключ";

            chgLang[2]="Изменить";chgLang[0]="Имя";chgLang[1]="Ключ";chgLang[3] = "Отмена";chgLang[4] = "Пароль";

            filesLang[0]="Ввод файла"; filesLang[1]="Вывод файла"; filesLang[2]="Начать"; filesLang[3]="Шифрование"; filesLang[4]="Дешифрование";
        }
    }

    public String[] getMainLang(){
        return mainLang;
    }
    public String[] getChooseLang(){
        return chooseLang;
    }
    public String[] getChgLang(){
        return chgLang;
    }
    public String[] getCreateLangLang(){
        return createLang;
    }
    public String[] getFilesLang(){return filesLang;}
}

