package JavaFX_213.Lab3.view;

import JavaFX_213.Lab3.model.PetModel;
import javafx.beans.binding.Bindings;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class PetView {
    //поле для связис с моделью
    private PetModel pet;
    private GridPane dataPane;//контейнер
    private Text type, nickname, ownerName, photoName, age, ageYear, ageMonth;
    private Label petPhoto;
    private Font font = Font.font("Tahoma", FontWeight.NORMAL, 15);//для сокращения кода пишем шрифт
    private int prefW = 180, prefH = 10;

    public void createPane() {
        //панель таблицы
        dataPane = new GridPane();
        dataPane.setAlignment(Pos.CENTER);
        dataPane.setVgap(10);
        dataPane.setHgap(10);
        dataPane.setPadding(new Insets(10, 10, 10, 10));

        //оглавление
        Label petTile = new Label("Информация о питомце");
        petTile.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
        GridPane.setHalignment(petTile, HPos.CENTER);
        GridPane.setValignment(petTile, VPos.TOP);
        petTile.setMaxWidth(180);
        petTile.setWrapText(true);
        dataPane.add(petTile, 0, 0, 4, 1);

        //изображение
//        petPhoto = new Label();
//        petPhoto.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("images/toto.jpg"))));
//        petPhoto.setPrefSize(210, 270);
//        dataPane.add(petPhoto, 0, 1, 1, 4);

        //метки
        Label labelNickName = new Label("Кличка:");
        labelNickName.setFont(font);
        labelNickName.setPrefSize(prefW, prefH);
        dataPane.add(labelNickName, 1, 2);

        Label labelType = new Label("Тип:");
        labelType.setFont(font);
        labelType.setPrefSize(prefW, prefH);
        dataPane.add(labelType, 1, 1);

        Label labelOwner = new Label("Хозяин:");
        labelOwner.setFont(font);
        labelOwner.setPrefSize(prefW, prefH);
        dataPane.add(labelOwner, 1, 3);

        Label labelAge = new Label("Возраст:");
        labelAge.setFont(font);
        labelAge.setPrefSize(prefW, prefH);
        dataPane.add(labelAge, 1, 4);

        //отображение информации о питомце
        type = new Text();
        type.setFont(font);
        dataPane.add(type, 2, 1);

        nickname = new Text();
        nickname.setFont(font);
        dataPane.add(nickname, 2, 2);

        ownerName = new Text();
        ownerName.setFont(font);
        dataPane.add(ownerName, 2, 3);

        ageYear = new Text();
        ageMonth = new Text();

        age = new Text();
        age.setFont(font);
        dataPane.add(age, 2, 4);
    }

    //метод изменения информации текстовых объектов
    public void setInformation() {
        type.setText(pet.getType());
        nickname.setText(pet.getNickName());
        ownerName.setText(pet.getOwnerName());
//        age.setText(createAgeString());
//        petPhoto.setGraphic(new ImageView(pet.getPetPhoto("images/toto.jpg")));
    }

    //метод загрузки питомца
    public void setPet(PetModel pet){
        this.pet=pet;
        Bindings.bindBidirectional(type.textProperty(), this.pet.typeProperty());
        nickname.textProperty().bind(this.pet.nickNameProperty());
        ownerName.textProperty().bind(this.pet.ownerNameProperty());
        ageYear.textProperty().bind(this.pet.ageYearProperty().asString());
        ageMonth.textProperty().bind(this.pet.ageMonthProperty().asString());
//        age.textProperty().set(this.pet.getAgeYear().toString()+ageYearStr(this.pet.getAgeYear())+" "
//                +this.pet.getAgeMonth().toString()+ageMonthValue(this.pet.getAgeMonth()));//передаем результат метода генерации строки
        //метод обновления данных
//        setInformation();
    }

    //конструктор вида
    public PetView(PetModel pet){
        //метод создания панели
        createPane();
        //метод изменения данных
        setPet(pet);//передаем в параметр метод объект pet
    }

    //метод, возвращающий панель
    public GridPane getDataPane(){
        return dataPane;
    }
    //метод генерации строки о возрасте
//    private String createAgeString(PetModel pet) {
//        String str1;
//        int years = this.pet.getAgeYear();
//        if (years == 1) {
//            str1 = " год";
//        } else if (years >= 2 && years <= 4) {
//            str1 = " года";
//        } else {
//            str1 = " лет";
//        }
//        String str2;
//        int month = this.pet.getAgeMonth();
//        if (month == 1) {
//            str2 = " месяц";
//        } else if (month >= 2 && month <= 4) {
//            str2 = " месяца";
//        } else {
//            str2 = " месяцев";
//        }
//        return this.pet.getAgeYear() + str1 + " " + this.pet.getAgeMonth() + str2;
//    }
    public String ageYearStr(int yearValue){
        String str;
        if (yearValue == 1) {
            str = " год";
        } else if (yearValue >= 2 && yearValue <= 4) {
            str = " года";
        } else {
            str = " лет";
        }
        return str;
    }

    public String ageMonthValue(int monthValue){
        String str;
        if (monthValue == 1) {
            str = " месяц";
        } else if (monthValue >= 2 && monthValue <= 4) {
            str = " месяца";
        } else {
            str = " месяцев";
        }
        return str;
    }
}
