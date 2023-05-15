package JavaFX_213.Lab3.model;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class petEditDialog {
    private PetModel pet;//модель
    private Stage dialog;//окно
    private VBox pane;//основная панель
    private GridPane editPane;//панель редактирования
    private HBox btnPane;//панель кнопок
    //поля редактирования
    private ComboBox<String> typeEdit;//тип
    private TextField nameEdit, ownerEdit;//кличка хозяин
    private Spinner<Integer> ageYearEdit, ageMonthEdit;//лет и месяцев
    private ButtonType result = ButtonType.CANCEL;//результат
    private Font font;//шрифт
    private Double prefW = 150.0, prefH = 10.0;//параметры высоты и ширины

    //конструктор
    public petEditDialog(PetModel pet) {
        this.pet = pet;
        dialog = new Stage();
        dialog.setTitle("Ввод данных о питомце");
        dialog.initModality(Modality.APPLICATION_MODAL);

        pane = new VBox();
        pane.setAlignment(Pos.CENTER);

        editPane = new GridPane();
        editPane.setAlignment(Pos.CENTER);
        editPane.setPadding(new Insets(10));

        btnPane = new HBox();
        btnPane.setAlignment(Pos.CENTER);

        font = Font.font("Arial", FontWeight.NORMAL, 10);

        createComboEdit();
        createTextEdit();
        createSpinnerEdit();
        createButtons();

        pane.getChildren().addAll(editPane, new Separator(Orientation.HORIZONTAL), btnPane);
        dialog.setScene(new Scene(pane, prefW*1.5, prefH*19));//без указания размеров
        dialog.setMinHeight(120);
        dialog.showAndWait();
    }

    public void createComboEdit() {
        Label labelType = new Label("Тип:");
        labelType.setPrefSize(prefW/2, prefH);
        labelType.setAlignment(Pos.CENTER_RIGHT);
        editPane.add(labelType, 0, 0);

        typeEdit = new ComboBox<>();
        typeEdit.getItems().addAll("собака", "кошка", "птица", "зверушка");
        typeEdit.setValue(pet.getType());
        typeEdit.setPrefSize(prefW, prefH);
        editPane.add(typeEdit, 1, 0, 2, 1);
    }

    public void createTextEdit() {
        Label labelName = new Label("Кличка:");
        labelName.setPrefSize(prefW/2, prefH);
        labelName.setAlignment(Pos.CENTER_RIGHT);
        editPane.add(labelName, 0, 1);

        nameEdit = new TextField();
        nameEdit.setText(pet.getNickName());
        nameEdit.setPrefSize(prefW, prefH);
//        nameEdit.setAlignment(Pos.CENTER);
        editPane.add(nameEdit, 1, 1, 2, 1);

        Label labelOwner = new Label("Владелец:");
        labelOwner.setPrefSize(prefW/2, prefH);
        labelOwner.setAlignment(Pos.CENTER_RIGHT);
        editPane.add(labelOwner, 0, 2);

        ownerEdit = new TextField();
        ownerEdit.setText(pet.getOwnerName());
        ownerEdit.setPrefSize(prefW, prefH);
//        ownerEdit.setAlignment(Pos.CENTER);
        editPane.add(ownerEdit, 1, 2, 2, 1);
    }

    public void createSpinnerEdit() {
        Label labelAge = new Label("Возраст:");
        labelAge.setPrefSize(prefW/2, prefH);
        labelAge.setAlignment(Pos.CENTER_RIGHT);
        editPane.add(labelAge, 0, 3, 1, 2);

        Label labelYear = new Label("лет");
        editPane.add(labelYear, 1, 3);
        ageYearEdit = new Spinner<>(0, 50, pet.getAgeYear(), 1);
        ageYearEdit.setEditable(true);
        ageYearEdit.setPrefSize(prefW / 2, prefH);
        editPane.add(ageYearEdit, 1, 4);

        Label labelMonth = new Label("месяцев");
        editPane.add(labelMonth, 2, 3);
        ageMonthEdit = new Spinner<>(0, 11, pet.getAgeMonth(), 1);
        ageMonthEdit.setEditable(true);
        ageMonthEdit.setPrefSize(prefW / 2, prefH);
        editPane.add(ageMonthEdit, 2, 4);
    }

    public void createButtons() {
        Button btnSave = new Button("Сохранить");
        btnSave.setOnAction(ActionEvent -> {
            if (validName() && validOwner()) {
                changesSave();
                result = ButtonType.OK;
                dialog.close();
            } else message();
        });

        Button btnCancel = new Button("Отмена");
        btnCancel.setOnAction(ActionEvent -> {

        });

        btnPane.getChildren().addAll(btnSave, btnCancel);
    }

    //метод проверки клички и хозяина
    private boolean validName() {
        return nameEdit.getText().matches("[А-Яа-я]+");
    }

    public boolean validOwner() {
        return ownerEdit.getText().matches("^[А-ЯЁа-яё '-]+$");
    }

    //метод сохранения
    private void changesSave() {
        pet.setType(typeEdit.getValue());
        pet.setNickName(nameEdit.getText());
        pet.setOwnerName(ownerEdit.getText());
        pet.setAgeYear(ageYearEdit.getValue());
        pet.setAgeMonth(ageMonthEdit.getValue());
    }

    //метод для вывода сообщения об ошибке ввода
    private void message() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Сообщение об ошибке");
        String header, content;
        if (validName() == false && validOwner() == true) {
            header = "клички";
            content = "Не правильно записано кличка питомца!\nИмя питомца пишется на русском и с заглавной буквы.";
        } else if (validName() == true && validOwner() == false) {
            header = "имени владельца";
            content = "Не правильно записано имя владельца питомца.\nИмя владельца пишется с использованием русского алфавита";
        } else {
            header = "имени владельца и клички";
            content = "Имя владельца пишется с использованием русского алфавита и с Заглавной буквы.\n Имя владельца пишется с использованием русского алфавита";
        }
        alert.setHeaderText("Ошибка ввода данных " + header + " питомца");
        alert.setContentText(content);
        alert.showAndWait();
    }
}
