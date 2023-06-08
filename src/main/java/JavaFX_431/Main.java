package JavaFX_431;

import JavaFX_431.Model.Organization;
import JavaFX_431.View.OrgDialogEdit;
import JavaFX_431.View.OrgView;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main extends Application {

    private ObservableList<Organization> data = FXCollections.observableArrayList();//объекты для наблюдения
    private ListView<Organization> dataView;//основной элемент отображения
    private OrgView view = new OrgView();//отображение информации об одной организации
    private String filePath = "src/main/java/JavaFX_431/Resource/data431.txt";
    private String DATA_SEPARATOR = "/";

    //все три элемента связываются в метода
    private ListView<Organization> createOrgList() {
        //связываем объекты наблюдения с элементом отображения
        dataView = new ListView<>(data);
        //у объекта наблюдения получаем ссылку на текущую модель выделения и у нее запрошиваем выделенный параметр
        dataView.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> {
            if (newValue != null) view.setOrg(newValue);
        }));
        dataView.getSelectionModel().selectFirst();
        return dataView;
    }

    //метод создания кнопок
    private VBox createButtons() {
        VBox boxButtons = new VBox();
        boxButtons.setPadding(new Insets(5));
        boxButtons.setAlignment(Pos.CENTER);
        boxButtons.setSpacing(10);

        //кнопка фильтра по имени боса
        Button filterBossName = new Button("Фильр по имени боса");
        filterBossName.setOnAction(ActionEvent -> {
            //получаем информацию о выделенной организации
            Organization orgSelected = dataView.getSelectionModel().getSelectedItem();//модель выделения.выделенный элемент
            //если что-то выделено
            if (orgSelected != null) {
                //создаем новый список для отобранных организация с тем же руководителем
                ObservableList<Organization> dataFilter = FXCollections.observableArrayList();
                //для списка дата применяем поток. вызываем метод фильтр с условием в виде реализации интерфейса модели, после фильтрации поток преобразуется в список методом коллект
                dataFilter.setAll(data.stream().filter(organization -> organization.isTheSameBoss(orgSelected)).collect(Collectors.toList()));
                //передаем результат в метод создания отдельного окна
                dataFilterView(dataFilter);
            } else {
                showMessage("Нет выделенного элемента.");
            }
        });

        Button showBossName = new Button("показать имя боса");
        showBossName.setOnAction(ActionEvent -> {
            Organization orgSelected = dataView.getSelectionModel().getSelectedItem();
            if (orgSelected != null) {
                ObservableList<Organization> dataShow = FXCollections.observableArrayList();
                dataShow.setAll(data.stream().filter(organization -> organization.isTheSameBoss(orgSelected)).collect(Collectors.toList()));
                dataView.setItems(dataShow);
            } else {
                showMessage("Нет выделенного элемента.");
            }
        });
        Button showAll = new Button("Показать все");
        showAll.setOnAction((ActionEvent e) -> dataView.setItems(data));
        boxButtons.getChildren().addAll(filterBossName, showBossName, showAll);
        return boxButtons;
    }

    //мотод создания отдельного окна
    private void dataFilterView(ObservableList<Organization> dataFilter) {
        Stage view = new Stage();
        ListView<Organization> dataFilterView = new ListView<>(dataFilter);
        dataFilterView.setStyle("-fx-font-size: 20px;");
        Button ok = new Button("Ok");
        ok.setOnAction(e -> view.close());
        VBox root = new VBox();
        root.setPadding(new Insets(10));
        root.setSpacing(10);
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(dataFilterView, ok);
        view.setScene(new Scene(root, 200, 450));
        view.show();
    }

    //метод создания меню
    private MenuBar createMenu() {
        Menu editMenu = new Menu("Редактировать");
        MenuItem edit = new MenuItem("Редактировать организацию");
        editMenu.getItems().add(edit);
        edit.setOnAction((ActionEvent event) -> {
            handleButtonEdit();
        });

        MenuItem add = new MenuItem("Добавить организацию");
        editMenu.getItems().add(add);
        add.setOnAction((ActionEvent event) -> {
            handleButtonAdd();
        });

        Menu exitMenu = new Menu("Выход");
        MenuItem exitItem = new MenuItem("Выход");
        exitItem.setOnAction(e -> Platform.exit());
        exitMenu.getItems().add(exitItem);

        return new MenuBar(editMenu, exitMenu);
    }

    //метод обаботки поля меню
    private void handleButtonEdit() {
        Organization orgEditable = dataView.getSelectionModel().getSelectedItem();
        if (orgEditable != null) {
            OrgDialogEdit editDialog = new OrgDialogEdit(orgEditable, "Редактирование организации");
            data.sort((o1, o2) -> o1.getName().compareToIgnoreCase(o2.getName()));//сортировка по названию это обновременно обновит данные
        } else {
            showMessage("Нет выделенного элемента.");
        }
    }

    //метод обработки поля меню
    private void handleButtonAdd() {
        OrgDialogEdit orgAddDialog = new OrgDialogEdit(null, "Добавление организации");
        Organization orgAdded = orgAddDialog.getOrg();
        if (orgAdded != null) {
            data.add(orgAdded);
            data.sort((o1, o2) -> o1.getName().compareToIgnoreCase(o2.getName()));//сортировка по названию оргаизаций
        }
    }

    //метод создания сообщения
    private void showMessage(String text) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Ошибка");
        alert.setHeaderText(null);
        alert.setContentText(text);
        alert.showAndWait();
    }

    //методы инит отвечает за чтение данных и файла
    @Override
    public void init() {
        int ARRAY_LENGTH = 3;
        try {
//            BufferedReader dataIn = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "UTF8"));
            Scanner dataIn = new Scanner(new File(filePath));//читаем файл
            while (dataIn.nextLine()!=null) {
                String[] strData = dataIn.nextLine().split(DATA_SEPARATOR);//создаем массив строк
                //проверяем правильность количества слов в строке и количество персонала указано в цифрах
                if (strData.length != ARRAY_LENGTH || !strData[2].matches("[0-9]+")) {
                    throw new IOException("Ошибка формата записи в файле.");
                }
                //если все хорошо делаем запись
                data.add(new Organization(strData[0], strData[1], Integer.parseInt(strData[2])));
            }
            dataIn.close();
            data.sort(((o1, o2) -> o1.getName().compareToIgnoreCase(o2.getName())));//сортируем по имени
        } catch (IOException e) {
            messageShow("Ошибка чтения файла", e.getStackTrace().toString());
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane root = new BorderPane();
        root.setCenter(createOrgList());
        root.setTop(createMenu());
        root.setLeft(createButtons());
        root.setRight(view.getPane());

        primaryStage.setTitle("Список организаций");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    @Override
    public void stop() {
        try {
            Writer dataOut = new OutputStreamWriter(new FileOutputStream(filePath), "UTF8");
            for (Organization org : data) {
                dataOut.write(org.getName() + DATA_SEPARATOR +
                        org.getBossName() + DATA_SEPARATOR +
                        org.getPersonnel() + "\n");
            }
            dataOut.close();
        } catch (IOException e) {
            messageShow("Ошибка записи в файл", e.getStackTrace().toString());
        }

    }

    public void messageShow(String titleText, String contentText) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titleText);
        alert.setHeaderText(titleText);
        alert.setContentText(contentText);
        alert.showAndWait();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
