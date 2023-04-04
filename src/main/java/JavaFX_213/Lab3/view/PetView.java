package JavaFX_213.Lab3.view;

import JavaFX_213.Lab3.model.PetModel;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class PetView {
    //поле для связис с моделью
    private PetModel pet;
    private GridPane gridPane;//контейнер
    private Text type, nickname, ownerName, photoName, age;
    private Label petPhoto;
    private Font font = Font.font("Tahoma", FontWeight.NORMAL, 20);//для сокращения кода пишем шрифт
    private int prefW = 200, prefH = 20;

    public void createPane() {
        //панель таблицы
        gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setPadding(new Insets(10, 10, 10, 10));

        //оглавление
        Text paneTile = new Text("Питомец");
        paneTile.setFont(Font.font("Tahoma", FontWeight.BOLD, 25));
        GridPane.setHalignment(paneTile, HPos.CENTER);
        gridPane.add(paneTile, 0, 0, 4, 1);

        //изображение
        petPhoto = new Label();
//        petPhoto.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("images/toto.jpg"))));
        petPhoto.setPrefSize(210, 270);
        gridPane.add(petPhoto, 0, 1, 1, 4);

        //метки
        Label labelNickName = new Label("Кличка:");
        labelNickName.setFont(font);
        labelNickName.setPrefSize(prefW, prefH);
        gridPane.add(labelNickName, 1, 2);

        Label labelType = new Label("Тип:");
        labelType.setFont(font);
        labelType.setPrefSize(prefW, prefH);
        gridPane.add(labelType, 1, 1);

        Label labelOwner = new Label("Хозяин:");
        labelOwner.setFont(font);
        labelOwner.setPrefSize(prefW, prefH);
        gridPane.add(labelOwner, 1, 3);

        Label labelAge = new Label("Возраст:");
        labelAge.setFont(font);
        labelAge.setPrefSize(prefW, prefH);
        gridPane.add(labelAge, 1, 4);

        //отображение информации о питомце
        type = new Text();
        type.setFont(font);
        gridPane.add(type, 2, 1);

        nickname = new Text();
        nickname.setFont(font);
        gridPane.add(nickname, 2, 2);

        ownerName = new Text();
        ownerName.setFont(font);
        gridPane.add(ownerName, 2, 3);

        age = new Text();
        age.setFont(font);
        gridPane.add(age, 2, 4);
    }

    //метод изменения информации текстовых объектов
    public void setInformation() {
        type.setText(pet.getType());
        nickname.setText(pet.getNickname());
        ownerName.setText(pet.getOwnerName());
        age.setText(createAgeString());
//        petPhoto.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("images/toto.jpg"))));
    }

    //метод загрузки питомца
    public void setPet(PetModel pet){
        this.pet=pet;
        //метод обновления данных
        setInformation();
    }

    //конструктор вида
    public PetView(PetModel pet){
        //метод создания панели
        createPane();
        //метод изменения данных
        setPet(pet);//передаем в параметр метод объект pet
    }

    //метод, возвращающий панель
    public GridPane getGridPane(){
        return gridPane;
    }
    //метод генерации строки о возрасте
    private String createAgeString() {
        String str1;
        int years = pet.getAgeYears();
        if (years == 1) {
            str1 = " год";
        } else if (years >= 2 && years <= 4) {
            str1 = " года";
        } else {
            str1 = " лет";
        }
        String str2;
        int month = pet.getAgeMonth();
        if (month == 1) {
            str2 = " месяц";
        } else if (month >= 2 && month <= 4) {
            str2 = " месяца";
        } else {
            str2 = " месяцев";
        }
        return pet.getAgeYears() + str1 + " " + pet.getAgeMonth() + str2;
    }
}
