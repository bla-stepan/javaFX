package JavaFX_213.MVCExample.сontroller;

import JavaFX_213.MVCExample.model.ModelOrganization;
import JavaFX_213.MVCExample.view.ViewOrganisation;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.time.LocalDate;

public class ControllerOrganization extends Application {
    //формирование списка из организаций
    private ObservableList<ModelOrganization> organizations = FXCollections.observableArrayList();
    private TableView<ModelOrganization> organizationTableView = new TableView<>();
    private String errorMessage = "";

    private void showMessage(String message) {
        Alert messageAlert = new Alert(Alert.AlertType.WARNING, message, ButtonType.OK);
        messageAlert.showAndWait();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //заголовок сцены
        primaryStage.setTitle("Пример MVC проекта");

        //создаем объект модели
        ModelOrganization organization = new ModelOrganization("ООО Рога и копыта", 10, "Юбилей компании", LocalDate.of(2023, 3, 31), 0.0);

        //создаем корневой контейнер
        VBox vBox = new VBox(10);
        //настройки корневого контейнера
        vBox.setAlignment(Pos.TOP_CENTER);

        //создаем объект вида и передаем в его конструктор объекто модели
        ViewOrganisation viewOrganisation = new ViewOrganisation(organization);

        //передаем в корневой контейнер панель с объектами, построенный при помощи метода объекта вида
        //передаем на панель объект вида, сепаратор-горизонтальный, блок редактирования с параметром объекта модели
//        vBox.getChildren().add(viewOrganisation.getGridPane());

        //создаем кнопку для управления взаимодействием модели и вида (НЕ ЗАГРУЖАЕТСЯ НА ПАНЕЛЬ)
        Button button = new Button("Изменить данные");
//        //настройки кнопки
//        button.setPrefSize(150, 50);
        button.setOnAction(ActionEvent -> {
            EditDialogOrganization editDialogOrganization = new EditDialogOrganization(organization);
        });


        MenuBar menu = new MenuBar();
        menu.getMenus().addAll(createFileMenu());

        //передаем в корневой контейнер кнопку
        vBox.getChildren().addAll(menu, viewOrganisation.getGridPane(), button);

        //создание и настройк и визуализация сцены
        Scene scene = new Scene(vBox, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    //создаем отдельный блок редактирования
    private VBox editBlock(ModelOrganization org) {
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

    //метод взаимодействия с файлами
    private void readDataFromFile(File datafile) {
        try {
            organizations.clear();//очищаем список
            errorMessage = "";
            BufferedReader dataFromFile = new BufferedReader(new FileReader(datafile));//считываем файл
            String str;
            //запускаем цикл чтения строк
            while ((str = dataFromFile.readLine()) != null) {
                try {
                    if (str.isEmpty()) break;//выходим из цикла если есть пустая строка
                    String[] dataArray = str.split(" +");//превращаем слова в массив по 4 слова
                    //если слов не 4 то выбрасываем ошибку
                    if (dataArray.length != 4) throw new Exception("Ошибка в данных");
                    String name = dataArray[0];
                    int personnel = Integer.parseInt(dataArray[1]);
                    String date = dataArray[2];
                    double bonusValue = Double.parseDouble(dataArray[3]);
                    String holiday = dataFromFile.readLine();
                    if (holiday == null || holiday.isEmpty()) throw new Exception("Ошибка данных праздника");
                    ModelOrganization organization = new ModelOrganization(name, personnel, holiday, LocalDate.parse(date), bonusValue);
                    organizations.add(organization);
                } catch (Exception e) {
                    errorMessage += e.getMessage() + "\n";
                    dataFromFile.close();
                }
            }
            dataFromFile.close();
        } catch (IOException e) {
            errorMessage += e.getMessage() + "\n";
        }
    }

    private void saveDataToFile(File dataFile) {
        try {
            FileWriter dataToFile = new FileWriter(dataFile);
            //пробегаемся по списку и записываем строки по организациям
            for (ModelOrganization organization : organizations) {
                dataToFile.write(organization.getName() + " "
                        + organization.getPersonal() + " "
                        + organization.getDate().toString() + " "
                        + organization.getBonus() + "\n"
                        + organization.getHoliday() + "\n");
            }
            dataToFile.close();
        } catch (IOException e) {
            showMessage(e.getMessage());
        }
    }

    private void createTableView() {
        TableColumn<ModelOrganization, String> nameCol = new TableColumn<>("Название");
        nameCol.setCellValueFactory(new PropertyValueFactory("name"));
        nameCol.setMinWidth(170);

        TableColumn<ModelOrganization, String> personnelCol = new TableColumn<>("Персонал");
        personnelCol.setCellValueFactory(new PropertyValueFactory("personnel"));
        personnelCol.setMinWidth(150);

        TableColumn<ModelOrganization, String> holidayCol = new TableColumn<>("Праздник");
        holidayCol.setCellValueFactory(new PropertyValueFactory("holiday"));
        holidayCol.setMinWidth(310);

        TableColumn<ModelOrganization, String> dateCol = new TableColumn<>("Дата");
        holidayCol.setCellValueFactory(new PropertyValueFactory("date"));
        holidayCol.setMinWidth(140);

        TableColumn<ModelOrganization, String> bonusValueCol = new TableColumn<>("Премия");
        holidayCol.setCellValueFactory(new PropertyValueFactory("bonus"));
        holidayCol.setMinWidth(100);

        organizationTableView.getColumns().addAll(nameCol, personnelCol, bonusValueCol, dateCol, holidayCol);
    }

    //для получения информации о файле из которого будем читать данные
    private void handleFileOpen() {
        FileChooser openFileChooser = new FileChooser();
        openFileChooser.setTitle("Открыть файл с даными");
        File openFile = openFileChooser.showOpenDialog(null);//открытие
        if (openFile == null) {
            return;
        }
        readDataFromFile(openFile);
        if (!errorMessage.isEmpty()) showMessage(errorMessage);
    }

    private void handleFileSave() {
        FileChooser saveFileChooser = new FileChooser();
        saveFileChooser.setTitle("Сохранить данные в файл");
        File saveFile = saveFileChooser.showSaveDialog(null);//сохранение
        if (saveFile == null) {
            return;
        }
        saveDataToFile(saveFile);
        if (!errorMessage.isEmpty()) showMessage(errorMessage);
    }

    private Menu createFileMenu(){
        Menu menuFile = new Menu("Файл");
        MenuItem openFile = new MenuItem("Открыть");
        openFile.setOnAction(Event ->{
            handleFileOpen();
        });
        MenuItem saveFile = new MenuItem("Сохранить");
        saveFile.setOnAction(Event ->{
            handleFileSave();
        });
        MenuItem exit = new MenuItem("Закрыть");
        exit.setOnAction(Event ->{
            Platform.exit();
        });

        menuFile.getItems().addAll(openFile, saveFile, exit);
        return menuFile;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
