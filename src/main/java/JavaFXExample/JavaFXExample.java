package JavaFXExample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
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
        Tab tabRadioButton = new Tab("Пример RadioButton", radioButtonExample());

        //создание TabPane
        TabPane examples = new TabPane();

        //добавляем вкладки в Tab
        examples.getTabs().addAll(tabComboBox, tabToggleButton, tabRadioButton);

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

        VBox vBoxToggleButton = new VBox(10);
        vBoxToggleButton.setPadding(new Insets(10, 100, 10, 100));
        //vBoxToggleButton.setSpacing(10); параметр указан в конструкторе
        vBoxToggleButton.setAlignment(Pos.CENTER);
        vBoxToggleButton.getChildren().addAll(toggleButton, result);

        return vBoxToggleButton;
    }

    private HBox radioButtonExample(){
        Insets insets = new Insets(20, 20, 20, 20);
        //создаем панель с размещением по горизонтали для расположения двух таблиц с радиокнопками и метками
        HBox hBoxRadioButtonExample = new HBox();
        //создаем панель таблицы
        GridPane gridPane1 = new GridPane();
        gridPane1.setVgap(40);
        gridPane1.setHgap(110);
        gridPane1.setPadding(insets);

        //создаем метку для отображения картинки
        Label labelImage1 = new Label();
        labelImage1.setPrefSize(210, 270);

        gridPane1.add(labelImage1, 1, 0, 1, 3);//размещаем картинку во 2-й колонке в 1-й строке на 1 колонке и 3-х строках

        //создаем группу, для задания ее радиокнопкам
        ToggleGroup group1 = new ToggleGroup();

        //создаем радиокнопку
        RadioButton radioButtonDog = new RadioButton("Собака");
        radioButtonDog.setToggleGroup(group1);//назначаем группу
        radioButtonDog.setFont(font);//задаем шрифт
        //radioButtonDog.setSelected(true);//можно задать параметр по умолчанию вкл. или выкл.
        //создаем обработку действия нажания на кнопку
        radioButtonDog.setOnAction(event -> {
            //создаем доступ к изображению как к ресурсу проекта, класс Images хранит но не отображает информацию об изображении
            Image imageDog = new Image(getClass().getResourceAsStream("images/dog.jpg"));
            //для отображения изображения, его нужно передать в объект класса ImageView
            ImageView imageViewDog = new ImageView(imageDog);
            labelImage1.setGraphic(imageViewDog);
        });
        gridPane1.add(radioButtonDog, 0, 0);

        //радиокнопка дом
        RadioButton radioButtonHouse = new RadioButton("Дом");
        radioButtonHouse.setToggleGroup(group1);
        radioButtonHouse.setFont(font);
        radioButtonHouse.setOnAction(event -> {
            labelImage1.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("images/house.jpg"))));
        });
        gridPane1.add(radioButtonHouse, 0, 1);

        //радиокнопка дерево
        RadioButton radioButtonTree = new RadioButton("Дерево");
        radioButtonTree.setToggleGroup(group1);
        radioButtonTree.setFont(font);
        radioButtonTree.setOnAction(event -> {
            labelImage1.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("images/tree.jpg"))));
        });
        gridPane1.add(radioButtonTree, 0, 2);

        //помещаем панель в конревой элемент
        hBoxRadioButtonExample.getChildren().add(gridPane1);

        //создаем разделитель вертикальный
        Separator separator = new Separator();
        separator.setOrientation(Orientation.VERTICAL);//задаем вертикальную ориентацию

        //создаем новую панель
        GridPane gridPane2 = new GridPane();
        gridPane2.setVgap(40);
        gridPane2.setHgap(110);
        gridPane2.setPadding(insets);

        //создаем метку
        Label labelImage2 = new Label();
        labelImage2.setPrefSize(210, 270);
        gridPane2.add(labelImage2, 1, 0, 1, 3);

        //создаем вторую группу радиокнопок
        ToggleGroup group2 = new ToggleGroup();

        //создаем радиокнопки
        RadioButton radioButtonDog2 = new RadioButton("Собака");
        radioButtonDog2.setToggleGroup(group2);
        radioButtonDog2.setFont(font);
        gridPane2.add(radioButtonDog2, 0,0);

        RadioButton radioButtonHouse2 = new RadioButton("Дом");
        radioButtonHouse2.setToggleGroup(group2);
        radioButtonHouse2.setFont(font);
        gridPane2.add(radioButtonHouse2, 0, 1);

        RadioButton radioButtonTree2 = new RadioButton("Дерево");
        radioButtonTree2.setToggleGroup(group2);
        radioButtonTree2.setFont(font);
        gridPane2.add(radioButtonTree2, 0, 2);

        Button button = new Button("Ok");
        button.setFont(font);
        button.setOnAction(event -> {
            if (radioButtonDog2.isSelected()){
                labelImage2.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("images/dog.jpg"))));
            }
            if (radioButtonHouse2.isSelected()){
                labelImage2.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("images/house.jpg"))));
            }
            if (radioButtonTree2.isSelected()){
                labelImage2.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("images/tree.jpg"))));
            }
        });
        gridPane2.add(button, 0, 3, 2, 1);

        hBoxRadioButtonExample.getChildren().add(gridPane2);
        //возвращаем корневой элемент HBox
        return hBoxRadioButtonExample;
    }

    public static void main(String[] args) {
        launch();
    }
}
