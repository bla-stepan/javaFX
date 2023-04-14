package JavaFX_213.Lab3.controller;

import JavaFX_213.Lab3.model.PetEditDialog;
import JavaFX_213.Lab3.model.PetModel;
import JavaFX_213.Lab3.view.PetView;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PetMain extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        //заголовок сцены
        primaryStage.setTitle("Практическое задание про животных");
        //создаем объект класса Pet
        PetModel petModel = new PetModel("собака", "Ника", "Аннушка", 2, 11);//, "images/toto.jpg");

        //создаем панель
        VBox hBox = new VBox(10);
        hBox.setAlignment(Pos.CENTER);
        hBox.setPadding(new Insets(5, 5, 5, 5));

        //создаем объект вида и передаем в него объект модели
        PetView petView = new PetView(petModel);


        //кнопка для организации взаимодействия пользователя и модели
        Button button = new Button("изменить данные");
        button.setPrefSize(300, 20);
        button.setFont(Font.font("Tahoma", FontWeight.NORMAL, 25));
        button.setOnAction(event -> {
            PetEditDialog petEditDialog = new PetEditDialog(petModel);
        });

        hBox.getChildren().addAll(petView.getDataPane(), new Separator(Orientation.VERTICAL), button);//new Separator(Orientation.VERTICAL), передаем панель, полученную через метод объекта вида
//
//        Label label = new Label();
//        TextField edit = new TextField();
//        label.textProperty().bind(edit.textProperty());
        primaryStage.setScene(new Scene(hBox));
        primaryStage.show();
    }
    private int prefW = 160, prefH = 10;
    public GridPane editPane(PetModel pet) {

//        VBox editPane = new VBox();
        GridPane editPane = new GridPane();
        editPane.setAlignment(Pos.CENTER);
        editPane.setVgap(10);
        editPane.setHgap(10);
        editPane.setPadding(new Insets(10, 10, 10, 10));

        Label labelTitle = new Label("Редактирование данных о питомце.");
        labelTitle.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
        GridPane.setHalignment(labelTitle, HPos.CENTER);
        GridPane.setValignment(labelTitle, VPos.TOP);
        labelTitle.setMaxWidth(prefW);
        labelTitle.setWrapText(true);
        editPane.add(labelTitle, 0, 0, 5, 1);

        //метки
        Label labelType = new Label("тип:");
        labelType.setPrefSize(prefW/2, prefH);
        labelType.setAlignment(Pos.TOP_RIGHT);
        editPane.add(labelType, 0, 1);

        Label labelName = new Label("кличка:");
        labelName.setPrefSize(prefW/2, prefH);
        labelName.setAlignment(Pos.TOP_RIGHT);
        editPane.add(labelName, 0, 2);

        Label labelAge = new Label("возраст:");
        labelAge.setPrefSize(prefW/2, prefH);
        labelAge.setAlignment(Pos.TOP_RIGHT);
        editPane.add(labelAge, 0, 3);

        Label labelOwner = new Label("владелец:");
        labelOwner.setPrefSize(prefW/2, prefH);
        labelOwner.setAlignment(Pos.TOP_RIGHT);
        editPane.add(labelOwner, 0, 4);

        //формы
        ObservableList<String> options = FXCollections.observableArrayList("Кошка", "Собака", "Птица");
        ComboBox editType = new ComboBox<>(options);
        editType.setPrefSize(prefW+45, prefH);
        editPane.add(editType, 1, 1, 4, 1);

        TextField editNickName = new TextField();
        editNickName.setPrefSize(prefW, prefH);
        editPane.add(editNickName, 1, 2, 4, 1);

        Spinner<Integer> editAgeYear = new Spinner<>(0, 50, 1);
        editAgeYear.setPrefSize(prefW/2, prefH);
        editPane.add(editAgeYear, 2, 3);

        Spinner<Integer> editAgeMonth = new Spinner<>(0, 11, 1);
        editAgeMonth.setPrefSize(prefW/2, prefH);
        editPane.add(editAgeMonth, 4, 3);

        TextField editOwner = new TextField();
        editOwner.setPrefSize(prefW, prefH);
        editPane.add(editOwner, 1, 4, 4, 1);

        Button button = new Button("сохранить");
        button.setOnAction(event -> {
            pet.setType(editType.getValue().toString());
            pet.setNickName(editNickName.getText());
            pet.setOwnerName(editOwner.getText());
            pet.setAgeYear(editAgeYear.getValue());
            pet.setAgeMonth(editAgeMonth.getValue());
            pet.setAge();
        });
        button.setPrefSize(prefW*2, prefH);
        button.setAlignment(Pos.CENTER);
        editPane.add(button, 0, 6, 5, 1);

        return editPane;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
