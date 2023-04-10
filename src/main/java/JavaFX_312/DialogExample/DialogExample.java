package JavaFX_312.DialogExample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

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
        Button button1 = new Button("Информационное окно");
        button1.setOnAction((ActionEvent e)-> {
            informDialog1();
        });

        Button button2 = new Button("Информационное коно без картинки");
        button2.setOnAction((ActionEvent e)->{
            informDialog2();
        });

        Button button3 = new Button("Информационное коно без заголовка");
        button3.setOnAction((ActionEvent e)->{
            informDialog3();
        });

        Button button4 = new Button("Окно предупреждение");
        button4.setOnAction((ActionEvent e)->{
            informDialog4();
        });

        Button button5 = new Button("Окно соглашение");
        button5.setOnAction((ActionEvent e)->{
            informDialog5();
        });

        vBox.getChildren().addAll(label, button1, button2, button3, button4, button5);

        Scene scene = new Scene(vBox, 700, 400);
        primaryStage.setScene(scene);
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
            label.setText("CANSEL");
        }
    }
}
