package JavaFX_312.DialogExample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DialogExample extends Application {

    Label label;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Примеры диалоговых окон");
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setPadding(new Insets(10));
        vBox.setSpacing(20);

        //метка над кнопками
        label = new Label("пример");
        label.setFont(Font.font(12));

        //кнопка для вывода окна сообщения
        Button btn1 = new Button("Информационное окно");
        btn1.setOnAction((ActionEvent e)-> {
            informDialog1();
        });

        Button btn2 = new Button("Информационное окно без картинки");
        btn2.setOnAction((ActionEvent e)->{
            informDialog2();
        });

        Button btn3 = new Button("Информационное окно без заголовка");
        btn3.setOnAction((ActionEvent e)->{
            informDialog3();
        });

        Button btn4 = new Button("Окно предупреждение");
        btn4.setOnAction((ActionEvent e)->{
            informDialog4();
        });

        Button btn5 = new Button("Окно соглашение");
        btn5.setOnAction((ActionEvent e)->{
            informDialog5();
        });

        Button btn6 = new Button("Окно ввода текста");
        btn6.setOnAction(ActionEvent ->{
            textInputDialog();
        });

        Button btn7 = new Button("Окно с выбором из списка");
        btn7.setOnAction(ActionEvent ->{
            choiceDialog();
        });

        Button btn8 = new Button("Диалог выбора файла");
        btn8.setOnAction(ActionEvent ->{
            fileChooser(primaryStage);
        });

        Button btn9 = new Button("Диалог выбора цвета");
        btn9.setOnAction(ActionEvent -> colorPicker());

        vBox.getChildren().addAll(label, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9);

        primaryStage.setScene(new Scene(vBox, 700, 500));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    //метод создает окно сообщения (оъект класса Alert)
    private void informDialog1(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);//тип окна -информационное
        alert.setTitle("Информационное окно");//титул окна
        alert.setHeaderText("Это информационное окно");//заголовок информационного сообщения
        alert.setContentText("Непосредственно сообщение информационного окна. У окна при момощи параметра initModality отключена модельность.");//текст сообщения окна
        alert.initModality(Modality.NONE);//дополнительный параметр, устанавливающий модельность окна (модельность отключена)
        //модельность включена - нельзя использовать остальнык окна приложения
        alert.showAndWait();//делаем окно видимым
    }

    private void informDialog2(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Инфомационное окно (2)");
        alert.setHeaderText("Это второе информационное окно");
        alert.setContentText("У этого окна при помощи метода setGraphic отключена картинка и при помощи метода getDialogPane().setMinSize(600, 200)" +
                "заданы минимальные размеры окна. Также окно является модельным по умолчанию в коде нет метода initModality");
        alert.setGraphic(null);//отключаем картинку у окна
        alert.getDialogPane().setMinSize(600, 200);//задаем минимальные размеры окна
        alert.showAndWait();//делаем окно видимым.
    }

    private void informDialog3(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Информационное окно без заголовка");
        alert.setHeaderText(null);//нет заголовка окна
        alert.setContentText("У этого окна при помощи метода setHeandlerText(null) отключен заголовок окна. У окна заданы минимальные размеры окна");
        alert.getDialogPane().setMinSize(600, 300);//заданы минимальные размеры окна
        alert.showAndWait();
    }

    private void informDialog4(){
        Alert alert = new Alert(Alert.AlertType.WARNING);//окно типа (предупреждение)
        alert.setTitle("Окно предупреждения");
        alert.setHeaderText("Окно типа предупреждения");
        alert.setContentText("Сообщение окна предупреждения (WARNING)");
        alert.getDialogPane().setMinSize(600, 300);
        alert.showAndWait();
    }

    private void informDialog5(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);//тип окна - соглашение
        alert.setTitle("Окно соглашение");
        alert.setHeaderText("Окно типа соглашение");
        alert.setContentText("Сообщение окна соглашения.");
        alert.getDialogPane().setMinSize(600, 300);
        //алгоритм при выборе и нажатии кнопок окна
        Optional<ButtonType> result = alert.showAndWait();//создаем специальный объект
        //если результатом является нажание кнопки типа ОК то метка меняет текст на ОК
        if (result.get()==ButtonType.OK){
            label.setText("OK");
        }else {
            label.setText("CANCEL");
        }
    }

    //метод. создающий диалог, который предполагает ввод пользователей строки текста
    private void textInputDialog(){
        TextInputDialog textInputDialog = new TextInputDialog();//создаем объект
        textInputDialog.setTitle("Диалог ввода текста");
        textInputDialog.setHeaderText("Окно ввода текста");
        textInputDialog.setContentText("Введите наше имя:");
        textInputDialog.getDialogPane().setMinSize(500, 200);

        Optional<String> result = textInputDialog.showAndWait();//создаем специальный объект в котором будет содержаться результат
        //метод реализации результата
//        if (result.isPresent()){
//            //если результат существует
//            label.setText("Ваше имя: "+result.get());
//        }else{
//            label.setText("Имя не введено");
//        }

        label.setText(result.isPresent() ? "Ваше имя: "+result.get() : "Имя не введено");
    }

    //метод, создающий диалог, предоставляющий выбор пользователем из набора строк
    private void choiceDialog(){
        //набор строк задается ввиде списка
        List<String> choiceList = new ArrayList<>();
        choiceList.add("кошка");
        choiceList.add("собака");
        choiceList.add("попугай");

        //для работы со списком используется класс choiceDialog
        ChoiceDialog<String> choiceDialog = new ChoiceDialog<>("собака", choiceList);
        //настройки окна
        choiceDialog.setTitle("Диалог с выбором из списка");
        choiceDialog.setHeaderText("Заголовок диалогового окна");
        choiceDialog.setContentText("Выберите вид питомца:");
        choiceDialog.getDialogPane().setMinSize(500, 200);

        //создаем объект, содержащий результат выбора пользователя
        Optional<String> result = choiceDialog.showAndWait();
        //обрабатываем результат
        label.setText(result.isPresent() ? "Ваш питомец: "+result.get() : "Вид питомца не выбран");
    }

    //метод для диалога выбора файла
    private void fileChooser(Stage primaryStage){
        FileChooser fileChooser = new FileChooser();//создаем объект
        fileChooser.setTitle("Диалог выбора файла");//указываем заголовок
        File file = fileChooser.showOpenDialog(primaryStage);//создаем объект класса File при этом в метод передается
        // зависимое окно данного диалога. т.е. окно будет модельным и перекроет порождающее его окно в данном случае - главное окно приложения
        label.setText(file.getAbsolutePath());//отображаем путь к файлу в тексте метки
    }

    //метод диалога выбора цвета
    private void colorPicker(){
        //в этом случае объект класса Stage нужно создавать самому т.е. тут мы создаем элемент для организации диалога
        Stage stage = new Stage();
        stage.setTitle("Окно выбора цвета");

        //создаем панель
        HBox hBox = new HBox(20);
        hBox.setAlignment(Pos.CENTER);
        hBox.setPadding(new Insets(15));

        //создаем элемент для выбора цвета - объект colorpicker
        ColorPicker colorPicker = new ColorPicker();
        colorPicker.setValue(Color.AZURE);//задаем цвет по умолчанию

        //создаем текст который будет перекрашиваться
        Text text = new Text("Проба выбора цвета");
        text.setFont(Font.font("Arial", 20));
        text.setFill(colorPicker.getValue());//задаем цвет текста и передаем в метод возвращенное значение колорпикера

        colorPicker.setOnAction(event -> text.setFill(colorPicker.getValue()));//обрабатываем событие как у кнопки

        //настраиваем панель и сцену
        hBox.getChildren().addAll(colorPicker, text);//передаем элементы в панель
        stage.setScene(new Scene(hBox, 600, 200));//передаем сцену с панелью в подмосток
        stage.show();
    }
}
