package JavaFX_213.Lab3.controller;

import JavaFX_213.Lab3.model.PetModel;
import JavaFX_213.Lab3.view.PetView;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
        VBox vBox = new VBox(10);
        vBox.setAlignment(Pos.CENTER);
        vBox.setPadding(new Insets(5, 5, 5, 5));

        //создаем объект вида и передаем в него объект модели
        PetView petView = new PetView(petModel);
        vBox.getChildren().add(petView.getGridPane());//передаем панель, полученную через метод объекта вида

        //кнопка для организации взаимодействия пользователя и модели
        Button button =new Button("+ 1 месяц");
        button.setPrefSize(300, 20);
        button.setFont(Font.font("Tahoma", FontWeight.NORMAL, 25));
        button.setOnAction(event -> {
            petModel.setAge();//метод добавления 1 месяца к возрасту питомца
            petView.setInformation();//метод обновления данных
        });
        vBox.getChildren().add(button);

        Label label = new Label();
        TextField edit = new TextField();
        label.textProperty().bind(edit.textProperty());
        //настраиваем сцену
        Scene scene = new Scene(vBox, 500, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
