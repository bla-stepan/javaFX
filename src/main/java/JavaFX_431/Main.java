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

    private ObservableList<Organization> data = FXCollections.observableArrayList();//������� ��� ����������
    private ListView<Organization> dataView;//�������� ������� �����������
    private OrgView view = new OrgView();//����������� ���������� �� ����� �����������
    private String filePath = "src/main/java/JavaFX_431/Resource/data431.txt";
    private String DATA_SEPARATOR = "/";

    //��� ��� �������� ����������� � ������
    private ListView<Organization> createOrgList() {
        //��������� ������� ���������� � ��������� �����������
        dataView = new ListView<>(data);
        //� ������� ���������� �������� ������ �� ������� ������ ��������� � � ��� ����������� ���������� ��������
        dataView.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> {
            if (newValue != null) view.setOrg(newValue);
        }));
        dataView.getSelectionModel().selectFirst();
        return dataView;
    }

    //����� �������� ������
    private VBox createButtons() {
        VBox boxButtons = new VBox();
        boxButtons.setPadding(new Insets(5));
        boxButtons.setAlignment(Pos.CENTER);
        boxButtons.setSpacing(10);

        //������ ������� �� ����� ����
        Button filterBossName = new Button("����� �� ����� ����");
        filterBossName.setOnAction(ActionEvent -> {
            //�������� ���������� � ���������� �����������
            Organization orgSelected = dataView.getSelectionModel().getSelectedItem();//������ ���������.���������� �������
            //���� ���-�� ��������
            if (orgSelected != null) {
                //������� ����� ������ ��� ���������� ����������� � ��� �� �������������
                ObservableList<Organization> dataFilter = FXCollections.observableArrayList();
                //��� ������ ���� ��������� �����. �������� ����� ������ � �������� � ���� ���������� ���������� ������, ����� ���������� ����� ������������� � ������ ������� �������
                dataFilter.setAll(data.stream().filter(organization -> organization.isTheSameBoss(orgSelected)).collect(Collectors.toList()));
                //�������� ��������� � ����� �������� ���������� ����
                dataFilterView(dataFilter);
            } else {
                showMessage("��� ����������� ��������.");
            }
        });

        Button showBossName = new Button("�������� ��� ����");
        showBossName.setOnAction(ActionEvent -> {
            Organization orgSelected = dataView.getSelectionModel().getSelectedItem();
            if (orgSelected != null) {
                ObservableList<Organization> dataShow = FXCollections.observableArrayList();
                dataShow.setAll(data.stream().filter(organization -> organization.isTheSameBoss(orgSelected)).collect(Collectors.toList()));
                dataView.setItems(dataShow);
            } else {
                showMessage("��� ����������� ��������.");
            }
        });
        Button showAll = new Button("�������� ���");
        showAll.setOnAction((ActionEvent e) -> dataView.setItems(data));
        boxButtons.getChildren().addAll(filterBossName, showBossName, showAll);
        return boxButtons;
    }

    //����� �������� ���������� ����
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

    //����� �������� ����
    private MenuBar createMenu() {
        Menu editMenu = new Menu("�������������");
        MenuItem edit = new MenuItem("������������� �����������");
        editMenu.getItems().add(edit);
        edit.setOnAction((ActionEvent event) -> {
            handleButtonEdit();
        });

        MenuItem add = new MenuItem("�������� �����������");
        editMenu.getItems().add(add);
        add.setOnAction((ActionEvent event) -> {
            handleButtonAdd();
        });

        Menu exitMenu = new Menu("�����");
        MenuItem exitItem = new MenuItem("�����");
        exitItem.setOnAction(e -> Platform.exit());
        exitMenu.getItems().add(exitItem);

        return new MenuBar(editMenu, exitMenu);
    }

    //����� �������� ���� ����
    private void handleButtonEdit() {
        Organization orgEditable = dataView.getSelectionModel().getSelectedItem();
        if (orgEditable != null) {
            OrgDialogEdit editDialog = new OrgDialogEdit(orgEditable, "�������������� �����������");
            data.sort((o1, o2) -> o1.getName().compareToIgnoreCase(o2.getName()));//���������� �� �������� ��� ������������ ������� ������
        } else {
            showMessage("��� ����������� ��������.");
        }
    }

    //����� ��������� ���� ����
    private void handleButtonAdd() {
        OrgDialogEdit orgAddDialog = new OrgDialogEdit(null, "���������� �����������");
        Organization orgAdded = orgAddDialog.getOrg();
        if (orgAdded != null) {
            data.add(orgAdded);
            data.sort((o1, o2) -> o1.getName().compareToIgnoreCase(o2.getName()));//���������� �� �������� ����������
        }
    }

    //����� �������� ���������
    private void showMessage(String text) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("������");
        alert.setHeaderText(null);
        alert.setContentText(text);
        alert.showAndWait();
    }

    //������ ���� �������� �� ������ ������ � �����
    @Override
    public void init() {
        int ARRAY_LENGTH = 3;
        try {
//            BufferedReader dataIn = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "UTF8"));
            Scanner dataIn = new Scanner(new File(filePath));//������ ����
            while (dataIn.nextLine()!=null) {
                String[] strData = dataIn.nextLine().split(DATA_SEPARATOR);//������� ������ �����
                //��������� ������������ ���������� ���� � ������ � ���������� ��������� ������� � ������
                if (strData.length != ARRAY_LENGTH || !strData[2].matches("[0-9]+")) {
                    throw new IOException("������ ������� ������ � �����.");
                }
                //���� ��� ������ ������ ������
                data.add(new Organization(strData[0], strData[1], Integer.parseInt(strData[2])));
            }
            dataIn.close();
            data.sort(((o1, o2) -> o1.getName().compareToIgnoreCase(o2.getName())));//��������� �� �����
        } catch (IOException e) {
            messageShow("������ ������ �����", e.getStackTrace().toString());
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane root = new BorderPane();
        root.setCenter(createOrgList());
        root.setTop(createMenu());
        root.setLeft(createButtons());
        root.setRight(view.getPane());

        primaryStage.setTitle("������ �����������");
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
            messageShow("������ ������ � ����", e.getStackTrace().toString());
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
