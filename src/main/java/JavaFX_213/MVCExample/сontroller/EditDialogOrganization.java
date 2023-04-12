package JavaFX_213.MVCExample.сontroller;

import JavaFX_213.MVCExample.model.ModelOrganization;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;

//import java.awt.*;
import java.time.LocalDate;

/*
Цель данного класса - создание пользователдского диалога, позволяющего пользователю отредактировать данные, хрянящиеся
внутри приложения. Получение от пользователя данные и подстверждение ввода данных и далее инициировать соответствующие
изменения в приложении (модели)
создание и вывод окна редактирования
 */
public class EditDialogOrganization {
    //загружаем все элементы, необходимые для построения окна редактирования
    private ModelOrganization org;//модель
    private Stage dialog;//этап подмосток
    private TextField nameEdit;//редактор имени
    private Spinner<Integer> personalEdit;//редактор персонала
    private ComboBox<String> holidayEdit;//выбор прадника
    private DatePicker dateEdit;//редактор даты
    private Spinner<Double> bonusEdit;//редактор премии
    private GridPane paneEdit;//панель для размещения
    private VBox pane;//панель для размещения панели редактирования разделителя и панели с кнопками
    private HBox paneBtn;//
    private Font font;//сталь текста
    private ButtonType result = ButtonType.CANCEL;//тип кнопки для результата
    private Double prefW=200.0, prefH=10.0;

    //метод, позволяющий получить информацию о результате
    public ButtonType getResult() {
        return result;
    }

    //отдельные методы создания элементов для диалогового окна
    private void createNameEdit(){
        Label labelName = new Label("Название организации:");
        labelName.setFont(font);
        paneEdit.add(labelName, 0,0);

        nameEdit = new TextField();
        nameEdit.setFont(font);
        nameEdit.setPrefSize(prefW, prefH);
        nameEdit.setAlignment(Pos.CENTER);
        nameEdit.setText(org.getName());
        paneEdit.add(nameEdit, 1, 0);
    }

    private void createPersonalEdit(){
        Label labelPersonal = new Label("Количество персонала:");
        labelPersonal.setFont(font);
        paneEdit.add(labelPersonal, 0, 1);

        personalEdit = new Spinner<>(1, 150, org.getPersonal());
        personalEdit.setPrefSize(prefW, prefH);
        personalEdit.setEditable(true);
        paneEdit.add(personalEdit, 1, 1);
    }

    private void createBonusEdit(){
        Label labelBonus = new Label("Размер премии:");
        labelBonus.setFont(font);
        paneEdit.add(labelBonus, 0, 2);

        bonusEdit = new Spinner<>(0, 500, org.getBonus(), 0.1);
        bonusEdit.setPrefSize(prefW, prefH);
        bonusEdit.setEditable(true);
        paneEdit.add(bonusEdit, 1, 2);
    }

    private void createHolidayEdit(){
        Label labelHoliday = new Label("Праздник:");
        labelHoliday.setFont(font);
        paneEdit.add(labelHoliday, 0, 3);

//        ObservableList<String> list = FXCollections.observableArrayList("Новый год", "1-е мая", "День подебы", "День компании");
        holidayEdit = new ComboBox<>();
        holidayEdit.getItems().addAll("Новый год", "1-е мая", "День подебы", "День компании");
        holidayEdit.setValue(org.getHoliday());
        holidayEdit.setStyle("-fx-font-size: 15px");
        holidayEdit.setPrefSize(prefW, prefH);
        paneEdit.add(holidayEdit, 1, 3);
    }

    private void createDateEdit(){
        Label labelDate = new Label("Дата события:");
        labelDate.setFont(font);
        paneEdit.add(labelDate, 0, 4);

        dateEdit = new DatePicker(org.getDate());
        dateEdit.setPrefSize(prefW, prefH);
        paneEdit.add(dateEdit, 1, 4);
    }

    private void createButtons(){
        Button btnSave = new Button("Сохранить");
        btnSave.setFont(font);
        //обработка события кнопки
        btnSave.setOnAction((ActionEvent e) ->{
            if (isInputValid()){
                //при прохождении проверки названия организации вызывается метод сохранения в ином случае вывод сообщения об ошибке
                saveСhanges();
                result = ButtonType.OK;
                dialog.close();
            } else message();
        });

        Button btnCancel = new Button("Отмена");
        btnCancel.setFont(font);
        btnCancel.setOnAction((ActionEvent e) ->{
            result = ButtonType.CANCEL;
            dialog.close();
        });
        paneBtn.getChildren().addAll(btnSave, btnCancel);
    }
    //метод проверки названия организации
    private boolean isInputValid(){
        return nameEdit.getText().matches("[а-яА-Я0-9&\\-+ ]+");//проверяем на соотвествие допустимым сиволам (регулярка)
    }

    //метод изменения данных в модели
    private void saveСhanges(){
        //инициируем изменения в модели
        org.setName(nameEdit.getText());
        org.setPersonal(personalEdit.getValue());
        org.setHoliday(holidayEdit.getValue());
        org.setBonus(bonusEdit.getValue());
        org.setDate(dateEdit.getValue());
        //инициируем изменение собственного поля результата
//        result = ButtonType.OK;
//        dialog.close();//закрываем окно диалога
    }

    //метод сообщения об ошибке
    private void message(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Ошибка ввода данных!");
        alert.setHeaderText("Ошибка в названии организации");
        alert.setContentText("Название организации состоит из букв, цифр, пробелов, +, -");
        alert.showAndWait();
    }

    //конструктор класса EditDialogOrganization - задача метода создание отдельного окна для ввода данных
    public EditDialogOrganization(ModelOrganization org) {
        this.org = org;//связь с моделью
        dialog = new Stage();//создание окна
        dialog.initModality(Modality.APPLICATION_MODAL);//настравиваем модельность
        dialog.setTitle("Окно редактирования данных");//заголовок окна
        //настравиваем панель
        paneEdit = new GridPane();
        paneEdit.setPadding(new Insets(20));
        paneEdit.setAlignment(Pos.CENTER);
        //панель для кнопок
        paneBtn = new HBox();
        paneBtn.setAlignment(Pos.CENTER);
        //панель для панели редактирования и панели кнопок
        pane = new VBox();
        pane.setAlignment(Pos.CENTER);
        //настравиваем шрифт
        font = Font.font("Arial", FontWeight.NORMAL, 15);
        //отдельные методы создания элементов диалога
        createNameEdit();//создаем метку и текстовое поле ввода
        createPersonalEdit();//создаем метку и спинер
        createBonusEdit();
        createHolidayEdit();//создаем метку и комбобокс
        createDateEdit();//создаем метку и элменет выбора длаты
        createButtons();//создаем две кнопки ок и отмена
        //настраиваем основную панель
        pane.getChildren().addAll(paneEdit, new Separator(Orientation.HORIZONTAL), paneBtn);
        //настраиваем окно
        dialog.setScene(new Scene(pane, 600, 500));
        dialog.showAndWait();
    }
}
