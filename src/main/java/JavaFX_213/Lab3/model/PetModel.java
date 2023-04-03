package JavaFX_213.Lab3.model;

import javafx.scene.image.Image;

public class PetModel {
    private String type, nickname, ownerName, photoName;
    private int ageYears, ageMonth;

    //Конструктор
    public PetModel(String type, String nickname, String ownerName, int ageYears, int ageMonth, String photoName) {
        this.type = type;
        this.nickname = nickname;
        this.ownerName = ownerName;
        this.ageYears = ageYears;
        this.ageMonth = ageMonth;
        this.photoName = photoName;
    }

    //геттеры
    public String getType() {
        return type;
    }

    public String getNickname() {
        return nickname;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public Integer getAgeYears() {
        return ageYears;
    }

    public Integer getAgeMonth() {
        return ageMonth;
    }

    public Image getPetPhoto(String photoName) {
        return new Image(getClass().getResourceAsStream(photoName));
    }

    public String getPhotoName() {
        return photoName;
    }

    //метод для увеличения возраста питомца
    public void setAge() {
        if (ageMonth == 11) {
            ageYears++;
            ageMonth = 0;
        } else {
            ageYears = ageYears;
            ageMonth++;
        }
    }


}
