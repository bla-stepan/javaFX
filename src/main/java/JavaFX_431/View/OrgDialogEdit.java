package JavaFX_431.View;

import JavaFX_431.Model.Organization;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class OrgDialogEdit {
    //создаем элементы
    private Organization org;//объект модели
    private Stage editDialog;//этап
    private GridPane root;//панель
    private TextField nameEdit, bossNameEdit;//поля редактирования
    private Spinner<Integer> personnelEdit;//спинер персонала

    public OrgDialogEdit(Organization org, String title) {
        this.org = org;

        root = new GridPane();
        root.setAlignment(Pos.CENTER);
        root.setVgap(5);
        root.setHgap(5);
        root.setStyle("-fx-font-size: 20px");

        createNameEdit();
        createPersonnelEdit();
        createButtons();

        editDialog = new Stage();
        editDialog.setTitle(title);
        editDialog.initModality(Modality.APPLICATION_MODAL);
        editDialog.setScene(new Scene(root, 600, 300));
        editDialog.showAndWait();
    }

    private void createNameEdit() {
        Label name = new Label("Название:");
        root.add(name, 0, 0);

        nameEdit = new TextField("название");
        root.add(nameEdit, 1, 0);

        Label bossName = new Label("Бос");
        root.add(bossName, 0, 1);

        bossNameEdit = new TextField("руководитель");
        root.add(bossNameEdit, 1, 1);
        //диалог создан для добавления и редактирования организации для этого вводим провреку организации из параметра метода OrgDialogEdit
        if (org != null) {
            nameEdit.setText(org.getName());
            bossNameEdit.setText(org.getBossName());
        }
    }

    private void createPersonnelEdit() {
        Label personnel = new Label("Персонал:");
        root.add(personnel, 0, 2);
        if (org != null) {
            personnelEdit = new Spinner(0, 100, org.getPersonnel());
        } else personnelEdit = new Spinner(0, 100, 1);

        personnelEdit.setEditable(true);
        root.add(personnelEdit, 1, 2);

    }

    private void createButtons() {
        Button btnOk = new Button("Ok");
        root.add(btnOk, 0, 5);
        btnOk.setOnAction((ActionEvent e) -> {
            if (isInputValid()) {
                if (org == null) {
                    org = new Organization(nameEdit.getText(), bossNameEdit.getText(), personnelEdit.getValue());
                } else {
                    org.setName(nameEdit.getText());
                    org.setBossName(bossNameEdit.getText());
                    org.setPersonnel(personnelEdit.getValue());
                }
                editDialog.close();
            } else {
                Alert error = new Alert(Alert.AlertType.ERROR);
                error.setTitle("Ошшибка ввода данных");
                error.setHeaderText(null);
                error.setContentText("Название организации состоит из букв, цифр, пробелов, +, -!!!\n" + "Имя руководителя начинается с заглавной буквы!!!");
                error.showAndWait();
            }
        });

        Button btnCancel = new Button("Отмена");
        root.add(btnCancel, 1, 5);
        btnCancel.setOnAction(ActionEvent -> {
            org = null;
            editDialog.close();
        });
    }

    private boolean isInputValid() {
        return nameEdit.getText().matches("[а-яА-Я0-9&\\-\\+ ]+") && bossNameEdit.getText().matches("[А-Я][а-я]*");
    }

    public Organization getOrg() {
        return org;
    }
}
