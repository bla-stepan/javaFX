package Lab;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/*Создаем однооконное приложение для расчёта калорийности продуктов по их весу. Известно, что 100 грамм продукта содержат
примерно 301 Ккал. В окне приложения расположим поле для ввода веса продукта, метку с соответствующим пояснением для
пользователя, метку или текст для отображения результата и кнопку «Расчет калорийности». После нажатия на кнопку выводится
сообщение о количестве ккал. Если ввод данных неверный, то выводится сообщение пользователю об ошибке.
Дополнительно: добавить возможность выбора разных продуктов.
Справочно: Индейка 198 ккал; Омлет 209 ккал; Вафли 543 ккал; Вишня 52 ккал; Огурцы 13 ккал.
*/
public class LabMain extends Application {


    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane root = gridPane();
        Scene scene = new Scene(root, 500, 500);
        primaryStage.setTitle("Расчет колорийности продуктов");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static GridPane gridPane() {
        GridPane root = new GridPane();//создаем таблицу
        root.setAlignment(Pos.CENTER);//задаем позиционирование по центру
        root.setPadding(new Insets(10));//задаем поля от краев сцены
        root.setHgap(5);//задаем поля по высоте между полями таблицы
        root.setVgap(5);//задаем поля по горизонтали между полями таблицы

        //создаем титул
        Text titleText = new Text("Расчет колорийности продуктов");//создаем надпись титул
        Font font = Font.font("Arial", FontWeight.NORMAL, 14);//создаем свой шрифт
        titleText.setFont(font);//передаем шрифт
        root.add(titleText, 0, 0, 2, 1);//размещаем в таблице на 1 стр. в 1 стл. на 2-х стл. в 1 стр.

        //создаем метку для выбора названия продукта
        Label productName = new Label("Выбрать продкут для расчета:");
        productName.setFont(font);
        root.add(productName, 0, 1);//размещаем в таблице

        //создаем выпадающее меню со списком продуктов
        //создаем список
        ObservableList<String> list = FXCollections.observableArrayList("Колбаса", "Индейка", "Омлет", "Вафли", "Вишня", "Огурцы");
        ComboBox<String> listComboBox = new ComboBox<String>(list);//загружаем список в комбобокс
        root.add(listComboBox, 1, 1);//размещаем

        //создаем метку для введения веса продукта

        Label weightLabel = new Label("Введите вес продукта для расчета");
        weightLabel.setFont(font);
        root.add(weightLabel, 0, 2);

        //поле для ввода веса
        TextField weightEnter = new TextField();
        weightEnter.setFont(font);
        root.add(weightEnter, 1, 2);

        //вывод результата
        Text result = new Text();
        result.setFont(font);
        root.add(result, 0, 6, 2, 1);

        //создаем кнопку
        Button button = new Button("Рассчитать");
        button.setOnAction(event -> {

            if (weightEnter.getText().matches("[0-9]+")) {
                int ccal;
                try {
                    switch (listComboBox.getValue()) {
                        case "Колбаса":
                            ccal = 100;
                            break;
                        case "Индейка":
                            ccal = 198;
                            break;
                        case "Омлет":
                            ccal = 209;
                            break;
                        case "Вафли":
                            ccal = 543;
                            break;
                        case "Вишня":
                            ccal = 52;
                            break;
                        case "Огурцы":
                            ccal = 13;
                            break;
                        default:
                            ccal = 0;
                    }
                    int colorfulness = ccal * Integer.parseInt(weightEnter.getText());
                    result.setText("Колорийность продукта (" + listComboBox.getValue() + ") составляет: " + String.valueOf(colorfulness) + " ккал.");
                    result.setFill(Color.GREEN);
                } catch (NullPointerException e) {
                    result.setText("Не выбран продукт для расчета.");
                    result.setFill(Color.RED);
                    ccal = 0;
                }

            } else {
                result.setText("Не правильно введен вес продукта.");
                result.setFill(Color.AQUA);
            }
        });
        button.setFont(font);
        root.add(button, 0, 4, 2, 1);
        return root;
    }
}
