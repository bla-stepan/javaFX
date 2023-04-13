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

import java.awt.event.ActionEvent;

public class PetEditDialog {
    private PetModel pet;//модель
    private Stage dialog;//окно
    private VBox pane;//основная панель
    private GridPane editPane;//панель редактирования
    private HBox btnPane;//панель кнопок
    //поля редактирования
    private ComboBox<String> typeEdit;//тип
    private TextField nameEdit, ownerEdit;//кличка хозяин
    private Spinner<Integer> ageYearEdit, getAgeMonthEdit;//лет и месяцев
    private ButtonType result = ButtonType.CANCEL;//результат
    private Font font;//шрифт
    private Double prefW=200.0, prefH=10.0;//параметры высоты и ширины

    //конструктор
    public PetEditDialog(PetModel pet){
        this.pet=pet;
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
        dialog.setScene(new Scene(pane, 400, 400));
        dialog.showAndWait();
    }

    public void createComboEdit(){
        Label labelType = new Label("Тип:");
        editPane.add(labelType, 0, 0);

        typeEdit = new ComboBox<>();
        typeEdit.getItems().addAll("собака", "кошка", "птица", "зверушка");
        typeEdit.setValue(pet.getType());
        typeEdit.setPrefSize(prefW, prefH);
        editPane.add(typeEdit, 1, 0, 2, 1);
    }

    public void createTextEdit(){
        Label labelName = new Label("Кличка:");
        editPane.add(labelName, 0, 1);

        nameEdit = new TextField();
        nameEdit.setText(pet.getNickName());
        nameEdit.setPrefSize(prefW, prefH);
        nameEdit.setAlignment(Pos.CENTER);
        editPane.add(nameEdit, 1, 1, 2, 1);

        Label labelOwner = new Label("Владелец:");
        editPane.add(labelOwner, 0, 2);

        ownerEdit = new TextField();
        ownerEdit.setText(pet.getOwnerName());
        ownerEdit.setPrefSize(prefW, prefH);
        ownerEdit.setAlignment(Pos.CENTER);
        editPane.add(ownerEdit, 1, 2, 2, 1);
    }

    public void createSpinnerEdit(){
        Label labelAge = new Label("Возраст:");
        editPane.add(labelAge, 0, 3, 1, 2);

        Label labelYear = new Label("лет");
        editPane.add(labelAge, 1, 3);
        ageYearEdit = new Spinner<>(0, 50, pet.getAgeYear(), 1);
        ageYearEdit.setEditable(true);
        ageYearEdit.setPrefSize(prefW/2, prefH);
        editPane.add(ageYearEdit, 1, 4);

        Label labelMonth = new Label("месяцев");
        editPane.add(labelMonth, 2, 3);
        getAgeMonthEdit = new Spinner<>(0, 11, pet.getAgeMonth(), 1);
        getAgeMonthEdit.setEditable(true);
        getAgeMonthEdit.setPrefSize(prefW/2, prefH);
        editPane.add(getAgeMonthEdit, 2, 4);
    }

    public void createButtons(){
        Button btnSave = new Button("Сохранить");
        btnSave.setOnAction(ActionEvent ->{

        });

        Button btnCancel = new Button("Отмена");
        btnCancel.setOnAction(ActionEvent ->{

        });

        btnPane.getChildren().addAll(btnSave, btnCancel);
    }

    //метод проверки клички и хозяина
     private boolean validName(){
        return nameEdit.getText().matches("[А-Яа-я]+");
     }
     public boolean validOwner(){
         return nameEdit.getText().matches("[А-Яа-я]+");
     }

     //метод сохранения

    //метод для вывода сообщения об ошибке ввода
}
