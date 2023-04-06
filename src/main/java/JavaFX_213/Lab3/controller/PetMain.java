package JavaFX_213.Lab3.controller;

import JavaFX_213.Lab3.model.PetModel;
import JavaFX_213.Lab3.view.PetView;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class PetMain extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        //заголовок сцены
        primaryStage.setTitle("Практическое задание про животных");
        //создаем объект класса Pet
        PetModel petModel = new PetModel("собака", "Ника", "Аннушка", 2, 11, "images/toto.jpg");

        //создаем панель
        HBox hBox = new HBox(10);
        hBox.setAlignment(Pos.CENTER);
        hBox.setPadding(new Insets(5, 5, 5, 5));

        //создаем объект вида и передаем в него объект модели
        PetView petView = new PetView(petModel);
        hBox.getChildren().addAll(editPane(petModel), petView.getGridPane());//new Separator(Orientation.VERTICAL), передаем панель, полученную через метод объекта вида

        //кнопка для организации взаимодействия пользователя и модели
//        Button button = new Button("+ 1 месяц");
//        button.setPrefSize(300, 20);
//        button.setFont(Font.font("Tahoma", FontWeight.NORMAL, 25));
//        button.setOnAction(event -> {
//            petModel.editAge();//метод добавления 1 месяца к возрасту питомца
//            petView.setInformation();//метод обновления данных
//        });
//        vBox.getChildren().add(button);
//
//        Label label = new Label();
//        TextField edit = new TextField();
//        label.textProperty().bind(edit.textProperty());
        //настраиваем сцену
        Scene scene = new Scene(hBox, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public GridPane editPane(PetModel pet) {
        GridPane editGridPane = new GridPane();
        editGridPane.setPadding(new Insets(10, 10, 10, 10));
        Label labelTitle = new Label("Редактирование данных о питомце.");
        editGridPane.add(labelTitle, 0, 0, 5, 1);

        //метки
        Label labelType = new Label("тип:");
        editGridPane.add(labelType, 0, 1);

        Label labelName = new Label("кличка:");
        editGridPane.add(labelType, 0, 2);

        Label labelAge = new Label("возраст:");
        editGridPane.add(labelAge, 0, 3);

        Label labelYear = new Label("лет");
        editGridPane.add(labelAge, 1, 3);

        Label labelMonth = new Label("мес.");
        editGridPane.add(labelMonth, 3, 3);

        Label labelPhoto = new Label("адрес фото:");
        editGridPane.add(labelPhoto, 0, 4);

        Label labelOwner = new Label("владелец:");
        editGridPane.add(labelMonth, 0, 5);

        //формы
        ObservableList<String> options = FXCollections.observableArrayList("Кошка", "Собака", "Птица");
        ComboBox editType = new ComboBox<>(options);
        editGridPane.add(editType, 1, 1, 4, 1);

        TextField editNickName = new TextField();
        editGridPane.add(editNickName, 1, 2, 4, 1);

        Spinner<Integer> editAgeYear = new Spinner<>(0, 50, 1);
        editGridPane.add(editAgeYear, 2, 3);

        Spinner<Integer> editAgeMonth = new Spinner<>(0, 11, 1);
        editGridPane.add(editAgeMonth, 4, 3);

        TextField editPhoto = new TextField();
        editGridPane.add(editPhoto, 1, 4, 4, 1);

        TextField editOwner = new TextField();
        editGridPane.add(editOwner, 1, 5, 4, 1);

        Button button = new Button("сохранить");
        button.setOnAction(event -> {
            pet.setType(editType.getValue().toString());
            pet.setOwnerName(editOwner.getText());
            pet.setAgeYear(editAgeYear.getValue());
            pet.setAgeMonth(editAgeMonth.getValue());
        });
        return editGridPane;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
