package JavaFX_213.MVCExample.сontroller;

import JavaFX_213.MVCExample.model.ModelOrganization;
import JavaFX_213.MVCExample.view.ViewOrganisation;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

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
        //передаем на панель объект вида, сепаратор-горизонтальный, блок редактирования с параметром объекта модели
        vBox.getChildren().addAll(viewOrganisation.getGridPane(), new Separator(Orientation.HORIZONTAL), editBlock(organization));

        //создаем кнопку для управления взаимодействием модели и вида (НЕ ЗАГРУЖАЕТСЯ НА ПАНЕЛЬ)
//        Button button = new Button("добавить премии");
//        //настройки кнопки
//        button.setPrefSize(150, 50);
//        button.setOnAction(event -> {
//            //вызываем метод МОДЕЛИ увеличения премии
//            organization.incrementBonus();
//            //вызываем метод ВИДА изменения элементов вида
//            viewOrganisation.setInform();
//        });

        //передаем в корневой контейнер кнопку
//        vBox.getChildren().add(button);

        //создание и настройк и визуализация сцены
        Scene scene = new Scene(vBox, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    //создаем отдельный блок редактирования
    private VBox editBlock(ModelOrganization org){
        VBox vBoxEdit = new VBox();
        vBoxEdit.setPadding(new Insets(10, 10, 10, 10));
        //метка инструкции
        Label labelTitle = new Label("Ввелите имя организации\n и бонус:");
        labelTitle.setFont(Font.font(20));
        //поле ввода названия организации
        TextField editName = new TextField();
        editName.setFont(Font.font(20));
        editName.setPrefSize(150, 40);

        //спинер для ввода премии бонуса
        Spinner<Double> editBonus = new Spinner<>(0, 100, 0, 0.5);
        editBonus.setPrefSize(150, 40);
        editBonus.setStyle("-fx-font-size: 20");

        //кнопка для сохранения данных
        Button button = new Button("сохранить");
        button.setPrefSize(150, 40);
        button.setFont(Font.font(20));
        button.setOnAction(event -> {
            //передача данных, введенных пользователем в модель
            org.setName(editName.getText());
            org.setBonus(editBonus.getValue());
        });
        //размещаем все в панели
        vBoxEdit.getChildren().addAll(labelTitle, editName, editBonus, button);
        return vBoxEdit;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
