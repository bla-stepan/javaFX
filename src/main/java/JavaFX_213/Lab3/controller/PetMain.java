package JavaFX_213.Lab3.controller;

import JavaFX_213.Lab3.model.petEditDialog;
import JavaFX_213.Lab3.model.PetModel;
import JavaFX_213.Lab3.view.PetView;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.Optional;

public class PetMain extends Application {
    PetModel petModel = new PetModel("собака", "Тотошка", "Аннушка", 2, 11);//, "images/Тотошка.jpg");
    private BorderPane rootPane;

    @Override
    public void start(Stage primaryStage) throws Exception {

        //заголовок сцены
        primaryStage.setTitle("Практическое задание про животных");
        //создаем объект класса Pet


        rootPane = new BorderPane();//создаем панель (5 областей)

        createSceneElement(petModel);//создаем панель отображения данных о зверушке

        //создаем главное меню
        MenuBar menu = new MenuBar();
        //загружаем элементы в меню
        menu.getMenus().addAll(createMainMenu(), createColorMenu(), createInformMenu());

        rootPane.setTop(menu);
        rootPane.setCenter(createSceneElement(petModel));

        //контекстное меню
        contextMenu();

        primaryStage.setScene(new Scene(rootPane));
        primaryStage.show();
    }

    private Menu createMainMenu() {
        Menu menuMain = new Menu("Главное");
        MenuItem editData = new MenuItem("Измеить данные в окне");
        editData.setOnAction((ActionEvent d) -> {
            new petEditDialog(petModel);
        });

        MenuItem exit = new MenuItem("Выйти");
        exit.setOnAction((ActionEvent e) -> {
            Platform.exit();
        });

        menuMain.getItems().addAll(editData, new SeparatorMenuItem(), exit);
        return menuMain;
    }

    private Menu createInformMenu() {
        Menu menuInfo = new Menu("Автор");
        MenuItem name = new MenuItem("Имя автора");
        name.setOnAction((ActionEvent e) -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Имя автора");
            alert.setContentText("Добрый день, меня зовут Степан Бландинский! Мне нравится программировать и я хочу стать программистом.");
            alert.setGraphic(null);
            alert.showAndWait();
        });
        MenuItem info = new MenuItem("О программе");
        info.setOnAction((ActionEvent i) -> {
            Alert alertInfo = new Alert(Alert.AlertType.CONFIRMATION, "Готовли я стать программистом?", ButtonType.YES, ButtonType.NO);
            alertInfo.setTitle("О программе");
            alertInfo.setHeaderText("Немного о программе");
            alertInfo.setContentText("Данную програму я пишу в рамках повторения материалов по JavaFX. Также главной целью написания является наработка опыта написания кода. Готов ли я стать программистом?");
            Optional<ButtonType> result = alertInfo.showAndWait();

            if (result.get() == ButtonType.YES) {
                Alert thanks = new Alert(Alert.AlertType.INFORMATION);
                thanks.setTitle("Спасибо за оценку");
                thanks.setGraphic(null);
                thanks.setHeaderText("Здорово!");
                thanks.setContentText("Значит, у меня что-то получается и это хороший старт");
                thanks.showAndWait();
            } else {
                Alert ok = new Alert(Alert.AlertType.INFORMATION);
                ok.setTitle("Будем свершенствоваться");
                ok.setHeaderText("Еще много работы в переди");
                ok.setContentText("Походу я еще в начале большого и творческого пути, надо засучить рукова и вперед.");
                ok.showAndWait();
            }
        });
        menuInfo.getItems().addAll(name, info);

        return menuInfo;
    }

    private Menu createColorMenu() {
        Menu menuColor = new Menu("Вид");
        MenuItem green = new MenuItem("Зеленый");
        green.setOnAction((ActionEvent c) -> {
            rootPane.setBackground(new Background(new BackgroundFill(Color.rgb(145, 238, 145), CornerRadii.EMPTY, Insets.EMPTY)));
        });
        MenuItem purple = new MenuItem("Фиолетовый");
        purple.setOnAction((ActionEvent c1) -> {
            rootPane.setBackground(new Background(new BackgroundFill(Color.rgb(238, 130, 238), CornerRadii.EMPTY, Insets.EMPTY)));

        });
        MenuItem yellow = new MenuItem("Жёлтый");
        yellow.setOnAction((ActionEvent c2) -> {
            rootPane.setBackground(new Background(new BackgroundFill(Color.rgb(245, 222, 179), CornerRadii.EMPTY, Insets.EMPTY)));
        });
        MenuItem orange = new MenuItem("Оранжевый");
        orange.setOnAction((ActionEvent c3) -> {
            rootPane.setBackground(new Background(new BackgroundFill(Color.rgb(210, 180, 140), CornerRadii.EMPTY, Insets.EMPTY)));

        });
        MenuItem blue = new MenuItem("Синий");
        blue.setOnAction((ActionEvent) -> {
            rootPane.setBackground(new Background(new BackgroundFill(Color.rgb(106, 90, 205), CornerRadii.EMPTY, Insets.EMPTY)));
        });

        menuColor.getItems().addAll(green, purple, yellow, orange, blue);
        return menuColor;
    }

    //контекстное меню
    private ContextMenu contextMenu(){
        ContextMenu contextMenu = new ContextMenu();
        MenuItem green = new MenuItem("Зеленый");
        green.setOnAction((ActionEvent c) -> {
            rootPane.setBackground(new Background(new BackgroundFill(Color.rgb(149, 238, 145), CornerRadii.EMPTY, Insets.EMPTY)));
        });
        MenuItem purple = new MenuItem("Фиолетовый");
        purple.setOnAction((ActionEvent c1) -> {
            rootPane.setBackground(new Background(new BackgroundFill(Color.rgb(245, 130, 238), CornerRadii.EMPTY, Insets.EMPTY)));

        });
        MenuItem yellow = new MenuItem("Жёлтый");
        yellow.setOnAction((ActionEvent c2) -> {
            rootPane.setBackground(new Background(new BackgroundFill(Color.rgb(245, 210, 110), CornerRadii.EMPTY, Insets.EMPTY)));
        });
        MenuItem orange = new MenuItem("Оранжевый");
        orange.setOnAction((ActionEvent c3) -> {
            rootPane.setBackground(new Background(new BackgroundFill(Color.rgb(200, 100, 140), CornerRadii.EMPTY, Insets.EMPTY)));

        });
        MenuItem blue = new MenuItem("Синий");
        blue.setOnAction((ActionEvent) -> {
            rootPane.setBackground(new Background(new BackgroundFill(Color.rgb(180, 190, 205), CornerRadii.EMPTY, Insets.EMPTY)));
        });

        contextMenu.getItems().addAll(green, yellow, blue, orange, purple);

        rootPane.setOnContextMenuRequested((ContextMenuEvent c)->{
            contextMenu.show(rootPane, c.getScreenX(), c.getScreenY());
        });
        return contextMenu;
    }

    //создаем панель отдельным методом
    private VBox createSceneElement(PetModel petModel) {
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
            petEditDialog petEditDialog = new petEditDialog(petModel);
        });

        hBox.getChildren().addAll(petView.getDataPane(), new Separator(Orientation.VERTICAL), button);//new Separator(Orientation.VERTICAL), передаем панель, полученную через метод объекта вида
        return hBox;
    }

    private int PREF_W = 160, PREF_H = 10;

    public GridPane editPane(PetModel pet) {

        GridPane editPane = new GridPane();
        editPane.setAlignment(Pos.CENTER);
        editPane.setVgap(10);
        editPane.setHgap(10);
        editPane.setPadding(new Insets(10, 10, 10, 10));

        Label labelTitle = new Label("Редактирование данных о питомце.");
        labelTitle.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
        GridPane.setHalignment(labelTitle, HPos.CENTER);
        GridPane.setValignment(labelTitle, VPos.TOP);
        labelTitle.setMaxWidth(PREF_W);
        labelTitle.setWrapText(true);
        editPane.add(labelTitle, 0, 0, 5, 1);

        //метки
        Label labelType = new Label("тип:");
        labelType.setPrefSize(PREF_W / 2, PREF_H);
        labelType.setAlignment(Pos.TOP_RIGHT);
        editPane.add(labelType, 0, 1);

        Label labelName = new Label("кличка:");
        labelName.setPrefSize(PREF_W / 2, PREF_H);
        labelName.setAlignment(Pos.TOP_RIGHT);
        editPane.add(labelName, 0, 2);

        Label labelAge = new Label("возраст:");
        labelAge.setPrefSize(PREF_W / 2, PREF_H);
        labelAge.setAlignment(Pos.TOP_RIGHT);
        editPane.add(labelAge, 0, 3);

        Label labelOwner = new Label("владелец:");
        labelOwner.setPrefSize(PREF_W / 2, PREF_H);
        labelOwner.setAlignment(Pos.TOP_RIGHT);
        editPane.add(labelOwner, 0, 4);

        //формы
        ObservableList<String> options = FXCollections.observableArrayList("Кошка", "Собака", "Птица");
        ComboBox editType = new ComboBox<>(options);
        editType.setPrefSize(PREF_W + 45, PREF_H);
        editPane.add(editType, 1, 1, 4, 1);

        TextField editNickName = new TextField();
        editNickName.setPrefSize(PREF_W, PREF_H);
        editPane.add(editNickName, 1, 2, 4, 1);

        Spinner<Integer> editAgeYear = new Spinner<>(0, 50, 1);
        editAgeYear.setPrefSize(PREF_W / 2, PREF_H);
        editPane.add(editAgeYear, 2, 3);

        Spinner<Integer> editAgeMonth = new Spinner<>(0, 11, 1);
        editAgeMonth.setPrefSize(PREF_W / 2, PREF_H);
        editPane.add(editAgeMonth, 4, 3);

        TextField editOwner = new TextField();
        editOwner.setPrefSize(PREF_W, PREF_H);
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
        button.setPrefSize(PREF_W * 2, PREF_H);
        button.setAlignment(Pos.CENTER);
        editPane.add(button, 0, 6, 5, 1);

        return editPane;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
