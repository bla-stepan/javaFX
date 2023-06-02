package JavaFX_213.Lab3.model;

import javafx.beans.property.*;
import javafx.scene.image.Image;

public class PetModel {
    private StringProperty type, nickName, ownerName, age;
    private IntegerProperty ageMonth, ageYear;
    private Image photo;

    //Конструктор
    public PetModel(String type, String nickName, String ownerName, int ageMonth, int ageYear) {//, String photoName) {
        setType(type);//this.type = type;
        setNickName(nickName);//this.nickName = nickName;
        setOwnerName(ownerName);//this.ownerName = ownerName;
        setAgeMonth(ageMonth);//this.ageYears = ageYears;
        setAgeYear(ageYear);//this.ageMonth = ageMonth;
//        photo = new Image(getClass().getResourceAsStream("/Lab3/images/"+nickName+".jpg"));
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
        if (age == null) {
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

    public IntegerProperty ageYearProperty() {
        ageYear = (ageYear == null) ? ageYear = new SimpleIntegerProperty() : ageYear;
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

    public String getAge() {
        return ageProperty().get();
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

    public void setAgeMonth(int ageMonthValue) {
        ageMonthProperty().set(ageMonthValue);
    }

    public void setAge() {
        ageProperty().set(getStrAge());
    }

    //метод для увеличения возраста питомца
    public String getStrAge() {
        String strY;
        if (getAgeYear() == 0) {
            strY = "";
        } else if (getAgeYear() == 1) {
            strY = "1 год ";
        } else if (getAgeYear() > 1 && getAgeYear() <= 4) {
            strY = getAgeYear().toString()+" года ";
        } else strY = getAgeYear().toString()+" лет ";
        String strM;
        if (getAgeMonth() == 0) {
            strM = "";
        } else if (getAgeMonth() == 1) {
            strM = "1 месяц";
        } else if (getAgeMonth() > 1 && getAgeYear() <= 4) {
            strM = getAgeMonth().toString()+" месяца";
        } else strM = getAgeMonth().toString()+" месяцев";
        return strY+strM;
    }


}
