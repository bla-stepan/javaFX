package JavaFX_213.Lab3.model;

import javafx.beans.property.*;
import javafx.scene.image.Image;

public class PetModel {
    private StringProperty type, nickName, ownerName, age;
    private String photoName;
//    private String age;
    private IntegerProperty ageMonth, ageYear;

    //Конструктор
    public PetModel(String type, String nickName, String ownerName, int ageMonth, int ageYear, String photoName) {
        setType(type);//this.type = type;
        setNickName(nickName);//this.nickName = nickName;
        setOwnerName(ownerName);//this.ownerName = ownerName;
        setAgeMonth(ageMonth);//this.ageYears = ageYears;
        setAgeYear(ageYear);//this.ageMonth = ageMonth;
        this.photoName = photoName;
    }

    //методы Property для свойст
    public StringProperty typeProperty() {
        type = (type == null) ? type = new SimpleStringProperty() : type;
        return type;
    }

    public StringProperty nickNameProperty() {
        nickName = (nickName == null) ? nickName = new SimpleStringProperty() : nickName;
        return nickName;
    }

    public StringProperty ownerNameProperty() {
        ownerName = (ownerName == null) ? ownerName = new SimpleStringProperty() : ownerName;
        return ownerName;
    }

    public StringProperty ageProperty() {
        if (age==null){
            age = new SimpleStringProperty();
        }
        return age;
    }

    public IntegerProperty ageMonthProperty() {
        if (ageMonth == null) {
            ageMonth = new SimpleIntegerProperty();
        }
        return ageMonth;
    }

    public IntegerProperty ageYearProperty(){
        ageYear = (ageYear==null) ? ageYear = new SimpleIntegerProperty() : ageYear;
        return ageYear;
    }

    //геттеры
    public String getType() {
        return typeProperty().get();
    }

    public String getNickName() {
        return nickNameProperty().get();
    }

    public String getOwnerName() {
        return ownerNameProperty().get();
    }

    public Integer getAgeYear() {
        return ageYearProperty().get();
    }

    public Integer getAgeMonth() {
        return ageMonthProperty().get();
    }

    public Image getPetPhoto(String photoName) {
        return new Image(getClass().getResourceAsStream(photoName));
    }

    public String getPhotoName() {
        return photoName;
    }

    //сеттеры для параметров
    public void setType(String typeValue) {
        typeProperty().set(typeValue);
    }

    public void setNickName(String nickNameValue) {
        nickNameProperty().set(nickNameValue);
    }

    public void setOwnerName(String ownerNameValue) {
        ownerNameProperty().set(ownerNameValue);
    }

    public void setAgeYear(int ageYearValue) {
        ageYearProperty().set(ageYearValue);
    }

    public void setAgeMonth(int ageMonthValue){
        ageMonthProperty().set(ageMonthValue);
    }
    //метод для увеличения возраста питомца
//    public void editAge() {
//        int month = (int)(10 * ageProperty().get() - 10 * (int)ageProperty().get())/10;
//        if ( month==11){
//            ageProperty().set(ageProperty().get()+1-month/10);
//            ageYears++;
//            ageMonth = 0;
//        } else{
//            ageProperty().set(ageProperty().get()+0.1);
//            ageYears = ageYears;
//            ageMonth++;
//        }
//    }


}
