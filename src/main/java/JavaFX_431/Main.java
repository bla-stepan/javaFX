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

import java.util.stream.Collectors;

public class Main extends Application {

    private ObservableList<Organization> data = FXCollections.observableArrayList();//объекты для наблюдения
    private ListView<Organization> dataView;//основной элемент отображения
    private OrgView view = new OrgView();//отображение информации об одной организации

    //все три элемента связываются в метода
    private ListView<Organization> createOrgList() {
        //связываем объекты наблюдения с элементом отображения
        dataView = new ListView<>(data);
        //у объекта наблюдения получаем ссылку на текущую модель выделения и у нее запрошиваем выделенный параметр
        dataView.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> {
            if (newValue!=null) view.setOrg(newValue);
        }));
        dataView.getSelectionModel().selectFirst();
        return dataView;
    }

    private VBox createButtons(){
        VBox boxButtons = new VBox();
        boxButtons.setPadding(new Insets(5));
        boxButtons.setAlignment(Pos.CENTER);
        boxButtons.setSpacing(10);

        Button filterBossName = new Button("Фильр по имени боса");
        filterBossName.setOnAction(ActionEvent -> {
            Organization org = dataView.getSelectionModel().getSelectedItem();
            if(org!=null){
                ObservableList<Organization> dataFilter = FXCollections.observableArrayList();
                dataFilter.setAll(data.stream().filter(organization -> organization.isTheSameBoss(org)).collect(Collectors.toList()));
                dataFilterView(dataFilter);
            } else {
                showMessage("Нет выделенного элемента.");
            }
        });

        Button showBossName = new Button("показать имя боса");
        showBossName.setOnAction(ActionEvent -> {
            Organization org = dataView.getSelectionModel().getSelectedItem();
            if (org != null) {
                ObservableList<Organization> dataShow = FXCollections.observableArrayList();
                dataShow.setAll(data.stream().filter(organization -> organization.isTheSameBoss(org)).collect(Collectors.toList()));
                dataView.setItems(dataShow);
            } else {
                showMessage("No selected item!");
            }
        });
        Button showAll = new Button("Show all");
        showAll.setOnAction((ActionEvent e) -> dataView.setItems(data));
        boxButtons.getChildren().addAll(filterBossName, showBossName, showAll);
        return boxButtons;
    }

    private void dataFilterView(ObservableList<Organization> dataFilter){
        Stage view = new Stage();
        ListView<Organization> dataFilterView = new ListView<>(dataFilter);
        dataFilterView.setStyle("-fx-font-size: 20px;");
        Button ok = new Button("Ok");
        ok.setOnAction( e-> view.close());
        VBox root = new VBox();
        root.setPadding(new Insets(10));
        root.setSpacing(10);
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(dataFilterView, ok);
        view.setScene(new Scene(root, 200, 450));
        view.show();

    }

    private MenuBar createMenu(){
        Menu editMenu = new Menu("Edit");
        MenuItem edit = new MenuItem("Edit organization");
        editMenu.getItems().add(edit);
        edit.setOnAction((ActionEvent event) -> {
            handleButtonEdit();
        });
        MenuItem add = new MenuItem("Add organization");
        editMenu.getItems().add(add);
        add.setOnAction((ActionEvent event) -> {
            handleButtonAdd();
        });

        Menu exitMenu = new Menu("Exit");
        MenuItem exitItem = new MenuItem("Exit");
        exitItem.setOnAction(e-> Platform.exit());
        exitMenu.getItems().add(exitItem);

        return new MenuBar(editMenu,exitMenu);
    }

    private void handleButtonEdit() {
        Organization organization = dataView.getSelectionModel().getSelectedItem();
        if (organization != null) {
            OrgDialogEdit orgEditDialog = new OrgDialogEdit(organization, "");
            data.sort((o1,o2)->o1.getName().compareToIgnoreCase(o2.getName()));
        } else {
            showMessage("No selected item!");
        }
    }

    private void handleButtonAdd() {
        DialogEditOrg orgEditDialog = new DialogEditOrg(null);
        Organization organization = orgEditDialog.getOrg();
        if(organization!= null) {
            data.add(organization);
            data.sort((o1,o2)->o1.getName().compareToIgnoreCase(o2.getName()));
        }
    }


    private void showMessage(String text){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Ошибка");
        alert.setHeaderText(null);
        alert.setContentText(text);
        alert.showAndWait();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane root = new BorderPane();
        root.setCenter(createOrgList());
        root.setTop(createMenu());
        root.setLeft(createButtons());
        root.setRight();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
