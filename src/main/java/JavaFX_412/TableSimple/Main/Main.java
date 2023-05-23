package JavaFX_412.TableSimple.Main;

import JavaFX_412.TableSimple.Model.Organization;
import javafx.application.Application;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.converter.IntegerStringConverter;



public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        Label label = new Label("Список организаций");

        createTable();//пример создания таблицы
        createTableEditable();//пример создания таблицы с гибкими настройками

        VBox root = new VBox();
        root.setSpacing(5);
        root.setPadding(new Insets(10, 0, 0, 10));
        root.setStyle("-fx-font-size: 18 pt");
        root.getChildren().addAll(label, table, organizationsListEditGroup());

        primaryStage.setTitle("Простая таблица");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    //создаем таблицу для отображения данных
    private TableView<Organization> table = new TableView<>();

    //создаем основной элемент модели - список организаций
    ObservableList<Organization> organizations = FXCollections.observableArrayList(
            //явно создаем элементы списка
            new Organization("Компания шитья", "Петя", 15),
            new Organization("Строительная группа", "Вася", 145),
            new Organization("ИТ-технологии", "Степа", 251),
            new Organization("Водоканал", "Федя", 1042),
            new Organization("ИвестКонсалт", "Анна", 169)
    );

    //метод создания таблицы (пример 4.1.2)
    private void createTable() {
        //создаем и настраиваем каждый столбец таблицы
        TableColumn nameCol = new TableColumn("Название организации");//создаем обхект - столбец
        nameCol.setMinWidth(200);//настраиваем минимальную ширину
        nameCol.setCellValueFactory(new PropertyValueFactory<Organization, String>("name"));//способ отображения данных модели,
        // которые будут отображаться в таблице.
        // В СТРОКЕ "name" ЭТО НАЗВАНИЕ ПЕРЕМЕННОЙ (ПАРАМЕТРА) МОДЕЛИ, КОТОРАЯ БУДЕТ ЯВЛЯТЬСЯ ИСТОЧНИКОМ
        nameCol.setCellFactory(TextFieldTableCell.forTableColumn());//настравиваем правило создания ячейки таблицы

        TableColumn bossNameCol = new TableColumn("Имя руководителя");
        bossNameCol.setMinWidth(200);
        bossNameCol.setCellValueFactory(new PropertyValueFactory<Organization, String>("bossName"));
        bossNameCol.setCellFactory(TextFieldTableCell.forTableColumn());

        TableColumn personnelCol = new TableColumn("Персонал");
        personnelCol.setMinWidth(150);
        personnelCol.setCellValueFactory(new PropertyValueFactory<Organization, Integer>("personnel"));
        personnelCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));//добавляем метод преобразования типа значения

        table.setItems(organizations);//загружаем данные
        table.getColumns().addAll(nameCol, bossNameCol, personnelCol);//выводим стобцы таблицы
    }

    //метод создания и настройки таблицы (пример 4.2.1.) гибкая настройка
    private void createTableEditable() {
        //настраиваем редактируемость таблицы
        table.setEditable(true);

        //первый столбец таблицы (номер позиции)
        TableColumn<Organization, Number> indexCol = new TableColumn<>("№");
        indexCol.setMinWidth(50);
        indexCol.setSortable(false);//отменяем сортировку у столбца
        //изменяем способ вычисления значения ячейки столбца таблицы
        //значение должно стать индексом отображаемым в строке
        indexCol.setCellValueFactory(column -> new ReadOnlyObjectWrapper<Number>(table.getItems().indexOf(column.getValue()) + 1));

        //столбец название организации
        TableColumn nameCol = new TableColumn("Название");
        nameCol.setMinWidth(170);
        //изменяем формирование ячейки (по сути метки) так чтобы параметры отображения текста другим цветом и другим шрифтом.
        nameCol.setCellValueFactory(new PropertyValueFactory<Organization, String>("name"));//отображение данных модели
        //метод, который формирует визуальную ячейку таблицы, для этого описываем реализацию интерфейса колдэк
        nameCol.setCellFactory(new Callback<TableColumn, TableCell>() {
            @Override
            public TableCell call(TableColumn param) {
                return new TableCell<Organization, String>() {
                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (isEmpty()) {
                            setText("");
                        } else {
                            setTextFill(Color.FIREBRICK);
                            setFont(Font.font("Verbana", 28));
                            setText(item);
                        }
                    }
                };
            }
        });

        //столбец имя руководителя
        TableColumn bossNameCol = new TableColumn("Имя руководителя");
        bossNameCol.setMinWidth(200);
        bossNameCol.setCellValueFactory(new PropertyValueFactory<Organization, String>("bossName"));
        bossNameCol.setCellFactory(TextFieldTableCell.forTableColumn());//текс может быть полем для редактирования
        //добавляем обработку события редактирования
        bossNameCol.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Organization, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Organization, String> t) {
                        ((Organization) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())).setBossName(t.getNewValue());
                    }
                }
        );


        //столбец персонала
        TableColumn personnelCol = new TableColumn("Персонал");
        personnelCol.setMinWidth(150);
        personnelCol.setCellValueFactory(new PropertyValueFactory<Organization, Integer>("personnel"));
        personnelCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));//добавляем метод преобразования типа значения

        table.setItems(organizations);//загружаем данные
        table.getColumns().addAll(nameCol, bossNameCol, personnelCol);//выводим стобцы таблицы
    }


    private HBox organizationsListEditGroup() {
        //Метод работает только со списком
        HBox editPane = new HBox();
        //создаем поля ввода
        TextField addName = new TextField();
        addName.setPromptText("Название организации");
        addName.setMaxWidth(170);

        TextField addBossName = new TextField();
        addBossName.setPromptText("Имя руководителя");
        addBossName.setMaxWidth(200);

        TextField addPersonnel = new TextField();
        addPersonnel.setPromptText("Количество сотрудников");
        addPersonnel.setMaxWidth(100);

        //создаем кнопку
        final Button addButton = new Button("ввод");
        addButton.setOnAction((ActionEvent ae) -> {
            //добавляется новый объект в список организаций (без проверки ввода данных)
            organizations.add(new Organization(
                    addName.getText(),
                    addBossName.getText(),
                    Integer.parseInt(addPersonnel.getText())
            ));
            //очистка полей ввода данных
            addName.clear();
            addBossName.clear();
            addPersonnel.clear();
        });

        //загружаем элементы на панель
        editPane.getChildren().addAll(addName, addBossName, addPersonnel, addButton);
        editPane.setSpacing(3);
        return editPane;
    }
}