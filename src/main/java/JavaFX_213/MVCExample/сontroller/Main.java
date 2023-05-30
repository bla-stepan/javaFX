package JavaFX_213.MVCExample.сontroller;

import JavaFX_213.MVCExample.model.Organization;
import JavaFX_213.MVCExample.view.ViewOrganisation;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.time.LocalDate;

public class Main extends Application {
    //формирование списка из организаций
    private ObservableList<Organization> organizations = FXCollections.observableArrayList();
    private TableView<Organization> organizationTableView = new TableView<>();
    private String errorMessage = "";
    private String filePathName = "src/main/java/JavaFX_213/MVCExample/resource/organizations.txt";

    private void showMessage(String message) {
        Alert messageAlert = new Alert(Alert.AlertType.WARNING, message, ButtonType.OK);
        messageAlert.showAndWait();
    }

    @Override
    public void init() {
        //в данном методе указан путь к файлу для считывания данных
        readDataFromFile(new File(filePathName));
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //снячало окно об ошибке если таковая случится при загрузке файла в программую.
        if (!errorMessage.isEmpty()) showMessage(errorMessage);
        //заголовок сцены
        primaryStage.setTitle("Пример MVC проекта");

        BorderPane root = new BorderPane();//создаем корневой элемент
        root.setPadding(new Insets(5));//настраиваем корневой элемент

        //создаем и настравиваем таблицу
        createTableView();//создаем таблицу
        organizationTableView.setItems(organizations);//загружаем в таблицу объекты листа

        //настраиваем корневой элемент
        root.setCenter(organizationTableView);//загружаем на панель таблицу с данными
//        root.setStyle("-fx-font-size: 18px");//задаем формат
        root.setTop(new MenuBar(createFileMenu(), createEditMenu(), createShowMenu()));//добавляем меню

        //создание и настройк и визуализация сцены
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    @Override
    public void stop() {
        //метод для сохранения изменний в файл
        saveDataToFile(new File(filePathName));
    }

    //создаем отдельный блок редактирования
    private VBox editBlock(Organization org) {
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
            BufferedReader dataFromFile1 = new BufferedReader(new FileReader(datafile));//считываем файл
            BufferedReader dataFromFile = new BufferedReader(new InputStreamReader(new FileInputStream(datafile), "UTF8"));
            String str;
            //запускаем цикл чтения строк
            while ((str = dataFromFile.readLine()) != null) {
                try {
                    if (str.isEmpty()) break;//выходим из цикла если есть пустая строка
                    String[] dataArray = str.split(" ");//превращаем слова в массив по 4 слова
                    //если слов не 4 то выбрасываем ошибку
                    if (dataArray.length != 4) throw new Exception("Ошибка в данных");
                    String name = dataArray[0];
                    int personnel = Integer.parseInt(dataArray[1]);
                    String date = dataArray[2];
                    double bonusValue = Double.parseDouble(dataArray[3]);
                    String holiday = dataFromFile.readLine();
                    if (holiday == null || holiday.isEmpty()) throw new Exception("Ошибка данных праздника");
                    Organization organization = new Organization(name, personnel, holiday, LocalDate.parse(date), bonusValue);
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
//            FileWriter dataToFile = new FileWriter(dataFile);
            Writer dataToFile = new OutputStreamWriter(new FileOutputStream(dataFile), "UTF8");
            //пробегаемся по списку и записываем строки по организациям
            for (Organization organization : organizations) {
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
        TableColumn<Organization, String> nameCol = new TableColumn<>("Название");
        nameCol.setCellValueFactory(new PropertyValueFactory("name"));
        nameCol.setMinWidth(170);

        TableColumn<Organization, String> personnelCol = new TableColumn<>("Персонал");
        personnelCol.setCellValueFactory(new PropertyValueFactory("personal"));
        personnelCol.setMinWidth(100);

        TableColumn<Organization, String> holidayCol = new TableColumn<>("Праздник");
        holidayCol.setCellValueFactory(new PropertyValueFactory("holiday"));
        holidayCol.setMinWidth(310);

        TableColumn<Organization, String> dateCol = new TableColumn<>("Дата");
        dateCol.setCellValueFactory(new PropertyValueFactory("date"));
        dateCol.setMinWidth(140);

        TableColumn<Organization, String> bonusValueCol = new TableColumn<>("Премия");
        bonusValueCol.setCellValueFactory(new PropertyValueFactory("bonus"));
        bonusValueCol.setMinWidth(100);

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

    private void handlerButtonAdd() {
        //создаем объект класса организация
        Organization organizationAdded = new Organization("имя", 0, "событие", LocalDate.of(2023, 5, 30), 0.0);
        //создаем диалоговое окно редактирования объекта организации
        EditDialogOrganization editDialogOrganization = new EditDialogOrganization(organizationAdded);
        //если нажата кнопка ок то
        if (editDialogOrganization.getResult() == ButtonType.OK) {
            organizations.add(organizationAdded);
        }
    }

    private void handlerButtonEdit() {
        //получаем ссылку на выделенный объект
        Organization organizationEditable = organizationTableView.getSelectionModel().getSelectedItem();
        if (organizationEditable != null) {
            EditDialogOrganization editDialogOrganization = new EditDialogOrganization(organizationEditable);
        } else {
            showMessage("Нет  выделенной организации для редактирования");
        }
    }

    private void handlerButtonDell() {
        int selectionIndex = organizationTableView.getSelectionModel().getSelectedIndex();
        if (selectionIndex >= 0) {
            organizationTableView.getItems().remove(selectionIndex);
        } else showMessage("Нет элементов для удаления");
    }

    private void handlerButtonShow() {
        Organization organizationShow = organizationTableView.getSelectionModel().getSelectedItem();
        if (organizationShow != null) {
            showOrganization(organizationShow);
        } else {
            showMessage("Нет выделенных элементов для отображения");
        }
    }

    private void showOrganization(Organization organizationSelected) {
        Stage stage = new Stage();
        stage.setTitle("Информация о " + organizationSelected.getName());
        BorderPane pane = new BorderPane();
        ViewOrganisation viewOrg = new ViewOrganisation(organizationSelected);
        pane.setCenter(viewOrg.getGridPane());
        stage.setScene(new Scene(pane));
        stage.showAndWait();
    }

    private Menu createFileMenu() {
        Menu menuFile = new Menu("Файл");

        MenuItem openFile = new MenuItem("Открыть");
        openFile.setOnAction(Event -> handleFileOpen());

        MenuItem saveFile = new MenuItem("Сохранить");
        saveFile.setOnAction(Event -> handleFileSave());

        MenuItem exit = new MenuItem("Закрыть");
        exit.setOnAction(Event -> Platform.exit());

        menuFile.getItems().addAll(openFile, saveFile, new SeparatorMenuItem(), exit);
        return menuFile;
    }

    private Menu createEditMenu() {
        Menu menuEditFile = new Menu("Изменение записей");

        MenuItem addOrganization = new MenuItem("Добавить запись");
        addOrganization.setOnAction(Event -> handlerButtonAdd());

        MenuItem editOrganization = new MenuItem("Редактировать запись");
        editOrganization.setOnAction(Event -> handlerButtonEdit());

        MenuItem dellOrganization = new MenuItem("Удалить запись");
        dellOrganization.setOnAction(Event -> handlerButtonDell());

        menuEditFile.getItems().addAll(addOrganization, editOrganization, dellOrganization);
        return menuEditFile;
    }

    private Menu createShowMenu() {
        Menu menuShow = new Menu("Показать");
        MenuItem showOrganization = new MenuItem("Показать организацию");
        showOrganization.setOnAction(ActionEvent -> handlerButtonShow());

        MenuItem about = new MenuItem("О приложении");
        about.setOnAction((ActionEvent event) -> {
            Alert alertInfo = new Alert(Alert.AlertType.INFORMATION);
            alertInfo.setTitle("Информация о приложении");
            alertInfo.setHeaderText("Сведения о приложении");
            alertInfo.setContentText("Приложение для обработки информации об организациях." +
                    "\n Приложение разработано в рамках обучения JavaFX." +
                    "\n Приложение написано Бландинским Степаном.");
            alertInfo.show();
        });

        menuShow.getItems().addAll(showOrganization, about);
        return menuShow;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
