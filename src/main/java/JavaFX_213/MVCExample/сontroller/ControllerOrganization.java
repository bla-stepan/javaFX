package JavaFX_213.MVCExample.сontroller;

import JavaFX_213.MVCExample.model.ModelOrganization;
import JavaFX_213.MVCExample.view.ViewOrganisation;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Button;

import java.time.LocalDate;

public class ControllerOrganization extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        //заголовок сцены
        primaryStage.setTitle("Пример MVC проекта");

        //создаем объект модели
        ModelOrganization organization = new ModelOrganization("ООО Рога и копыта", 10, "Юбилей компании", LocalDate.of(2023, 3, 31), 0.0);

        //создаем корневой контейнер
        VBox vBox = new VBox(10);
        //настройки корневого контейнера
        vBox.setAlignment(Pos.CENTER);

        //создаем объект вида и передаем в его конструктор объекто модели
        ViewOrganisation viewOrganisation = new ViewOrganisation(organization);

        //передаем в корневой контейнер панель с объектами, построенный при помощи метода объекта вида
        vBox.getChildren().add(viewOrganisation.getGridPane());

        //создаем кнопку для управления взаимодействием модели и вида
        Button button = new Button("добавить премии");
        //настройки кнопки
        button.setPrefSize(150, 50);
        button.setOnAction(event -> {
            //вызываем метод МОДЕЛИ увеличения премии
            organization.incrementBonus();
            //вызываем метод ВИДА изменения элементов вида
            viewOrganisation.setInform();
        });

        //передаем в корневой контейнер кнопку
        vBox.getChildren().add(button);

        //создание и настройк и визуализация сцены
        Scene scene = new Scene(vBox, 500, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
