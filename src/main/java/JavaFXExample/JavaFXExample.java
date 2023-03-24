package JavaFXExample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

//Примеры кода ComboBox, TaggleButton, RadioButton, Spinner на панели TabPane
public class JavaFXExample extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        //создание элемента Tab с названием и панелью с элементами
        Tab tabComboBox = new Tab("Пример ComboBox", comboBoxExample());
        Tab tabToggleButton = new Tab("Пример ToggleButton", toggleButtonExample());

        //создание TabPane
        TabPane examples = new TabPane();

        //добавляем вкладки в Tab
        examples.getTabs().addAll(tabComboBox, tabToggleButton);

        //создаем заголовок
        primaryStage.setTitle("Примеры визуальных компонентов JavaFX");

        //загружам сцену
        primaryStage.setScene(new Scene(examples));

        //запускаем
        primaryStage.show();
    }

    //значения параметров единого стиля шрифта
    int elementHeightSize = 25;
    int elementWidthSize = 300;
    Font font = Font.font("Tahoma", 15);
    //для копонентов вкладки comboBox создаем панель с вертикальным расположением элементов
    private VBox comboBoxExample() {
        //для comboBox создаем список с значениями (опциями)
        ObservableList<String> options = FXCollections.observableArrayList(
                "Понедельник",
                "Вротник",
                "Среда",
                "Четверг",
                "Пятница",
                "Суббота",
                "Воскресение");

        //создаем comboBox
        ComboBox comboBox = new ComboBox(options);
        //возможно задание следующих параметров
        //comboBox.setValue("Пятница");//загрузка значения по умолчанию
        comboBox.setEditable(true);//возможность редактирования
        comboBox.setPrefSize(300, 25);
        comboBox.setStyle("-fx-font-size: 15px");//для комбобок стиль задается так

        //сздаем метку
        Label label = new Label();
        label.setFont(font);
        label.setPrefSize(400, elementHeightSize);
        label.setAlignment(Pos.CENTER);

        //создаем кнопку для демонстрации работы
        Button button = new Button("Пример ComboBox");
        button.setPrefSize(300, elementHeightSize);
        button.setFont(font);
        button.setOnAction(event -> {
            //пишем код для работы кнопки
            //проверка значения комбо
            if (comboBox.getValue() != null && !comboBox.getValue().toString().isEmpty()) {
                String result = comboBox.getValue().toString();
                String weekend = null;
                if (result.matches("Суббота") || result.matches("Воскресение")) {
                    weekend = " (выходной)";
                } else {
                    weekend = " (будний)";
                }
                label.setText("выбранный день недели: " + result + weekend);
            } else {
                label.setText("день недели не определен");
            }
        });
        VBox vBoxComboBox = new VBox();
        vBoxComboBox.setAlignment(Pos.CENTER);
        vBoxComboBox.setPadding(new Insets(50, 100, 50, 100));
        vBoxComboBox.setSpacing(10);
        vBoxComboBox.getChildren().addAll(comboBox, button, label);
        return vBoxComboBox;
    }

    //для компонентов вкладки toggleButton создаем панель VBox с компонентами
    private VBox toggleButtonExample(){
        //создаем параметры шрифта

        //создаем метку
        Label result = new Label();
        result.setFont(font);
        result.setPrefSize(elementWidthSize, elementHeightSize);
        result.setAlignment(Pos.CENTER);

        //создаем кнопку переключатель
        ToggleButton toggleButton = new ToggleButton("ToggleButton");
        toggleButton.setFont(font);
        toggleButton.setPrefSize(elementWidthSize, elementHeightSize);
        toggleButton.setOnAction(event -> {
            //проверяем включена ли кнопка
            if (toggleButton.isSelected()) {
                //меняем цвет поля метки
                result.setText("ВКЛЮЧЕНО");
                result.setTextFill(Color.BLACK);
                result.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
                result.setFont(Font.font("Tahoma", FontWeight.BOLD, 15));
            }else {
                result.setText("ВЫКЛЮЧЕНО");
                result.setTextFill(Color.INDIGO);
                result.setBackground(new Background(new BackgroundFill(Color.AQUA, CornerRadii.EMPTY, Insets.EMPTY)));
                result.setFont(Font.font("Tahoma", FontWeight.LIGHT, 15));
            }
        });

        VBox vBoxToggleButton = new VBox();
        vBoxToggleButton.setPadding(new Insets(10, 100, 10, 100));
        vBoxToggleButton.setSpacing(10);
        vBoxToggleButton.setAlignment(Pos.CENTER);
        vBoxToggleButton.getChildren().addAll(toggleButton, result);

        return vBoxToggleButton;
    }

    public static void main(String[] args) {
        launch();
    }
}
