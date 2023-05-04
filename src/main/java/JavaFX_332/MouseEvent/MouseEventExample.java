package JavaFX_332.MouseEvent;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Random;

public class MouseEventExample extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Tab simpleEvent = new Tab("Simple Event", createPaneSimpleEvent());
        Tab eventHandler = new Tab("Event Handler", createPaneEventHandler());
        Tab eventRout = new Tab("Источники и цели событий", createPaneEventRout());

        TabPane root = new TabPane(simpleEvent, eventHandler, eventRout);

        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    //метод создания основной панели
    private VBox createPaneSimpleEvent() {
        VBox vBox = new VBox();
        vBox.setPadding(new Insets(10));
        vBox.setSpacing(30);
        vBox.getChildren().addAll(createCircle(), createTextField(), createText());
        return vBox;
    }

    //метод создания круга
    private Circle createCircle() {

        double radius = 50;
        //создаем объект круга
        Circle circle = new Circle(radius, Color.GOLD);
        //прописываем обработку событий для объекта круга
        //при вхождении курсора мыши на круг
        circle.setOnMouseEntered((MouseEvent me) -> {
            System.out.println("Circle. Mouse entered");
            circle.setFill(Color.GREEN);
        });
        //при перемещении в пределах круга
        circle.setOnMouseMoved((MouseEvent me) -> {
            System.out.println("Circle. Mouse moved");
        });
        //при выходе из области круга
        circle.setOnMouseDragExited((MouseEvent me) -> {
            System.out.println("Circle. Mouse exited");
            circle.setFill(Color.GOLD);
        });
        //обработка нажантия на мышь
        circle.setOnMousePressed((MouseEvent me) -> {
            System.out.println("Circle. Mouse pressed");
            circle.setFill(Color.GREEN);
        });
        //обработка события нажатия клавиатуры
        //данная обработка относится к другому пакету и по этому автоматически фокус а него не перемещается
        circle.setOnKeyPressed((KeyEvent ke) -> {
            System.out.println("Circle. Key Pressed " + ke.getText());
        });
        return circle;
    }

    private TextField createTextField() {

        //объекты класса textFieldавтоматически участвуют в переключении фокуса
        TextField textField = new TextField();
        textField.setPrefSize(300, 100);
        textField.setFont(Font.font(20));

        //обработка двух событий нажание на клавишу и отпускаение
        textField.setOnKeyPressed((KeyEvent ke) -> {
            //обрабатыжваем события нажатия клавишы
            System.out.println("TextField. Key pressed " + ke.getText());
            //обрабатываем события при условии (ctrl+а) этот способ проверки удобен если есть символ клавиши
            if (ke.getText().equals("a") && ke.isAltDown()) {
                //меняем шрифт текса в поле
                textField.setFont(Font.font("Arial", 30));
            }
            //обработка нажатия того же условия что и выше но по другому (сравниваем код) этот способ удобен для конкретной клавишы
            if (ke.getCode() == KeyCode.T && ke.isAltDown()) {
                textField.setFont(Font.font("Times New Roman", 24));
            }
        });
        //обработка события отпускания клавиши
        textField.setOnKeyReleased((KeyEvent ke) -> {
            System.out.println("TextField. Key released" + ke.getText());
        });
        return textField;
    }

    private Text createText() {
        Text text = new Text("TEXT");
        text.setFont(Font.font(30));
        text.prefWidth(60);
        text.setFocusTraversable(true);//настраиваем доступность перемещения фокуса т.к. объект text не участвуее в переключении фокуса
        //обработка события мыши
        text.setOnMouseClicked((MouseEvent me) -> {
            text.requestFocus();//перемещаем фокус
            System.out.println("Text. Mouse clicked");
        });
        //обработка события нажатия на клавишу
        text.setOnKeyPressed((KeyEvent ke) -> {
            System.out.println("Text. Key pressed " + ke.getText());
            if (ke.getText().equals("r")) {
                text.setFill(Color.RED);
            }
            if (ke.getCode() == KeyCode.B) {
                text.setFill(Color.BLACK);
            }
        });
        return text;
    }

    private AnchorPane createPaneEventHandler() {
        //AnchorPane позволяет программисту самостоятельно расчитывать координаты расположения элементов
        AnchorPane pane = new AnchorPane();
        pane.setStyle("-fx-font-size: 22");
        //через цикл создаем 5 меток
        for (int i = 0; i < 5; i++) {
            pane.getChildren().add(createLabel());
        }
        return pane;
    }

    private Label createLabel() {
        Label label = new Label("События");
        label.setPrefSize(200, 60);
        label.setAlignment(Pos.CENTER);
        label.setBackground(new Background(new BackgroundFill(Color.GOLD, null, null)));
        Random random = new Random();//создаем переменную случайных числе
        //задам при помощи случайного числа координаты расположения метки на панели
        label.setLayoutX(random.nextDouble() * 500);//расположение по горизонтали (от верхнего левого угла сцены)
        label.setLayoutY(random.nextDouble() * 500);//расположение по вертикали (от левого верхнего угла сцены)
        label.setRotate(random.nextDouble() * 100);//угол наклона метки
        label.setFocusTraversable(true);//настраиваем параметр фокусировки для метки
        //обработка событий выводим в отдельный метод
        addTranslate(label);
        return label;
    }

    private Point2D delta;//объект двумерная точка
    private String style = "-fx-background-color: linear-gradient(from 0% 0% to 100% 100%, aqua 0%, red 100%);\n" +
            "    -fx-border-color: black;\n" +
            "    -fx-border-style: dashed;\n" +
            "    -fx-border-width: 2;";

    //метод обрабатывает все типы события с помощью универсального метода addEventHandler
    private void addTranslate(Label node) {
        //добавляем обработчик события мыши (любого) - по наступлении события -> перемещяется фокус
        node.addEventHandler(MouseEvent.ANY, (event -> node.requestFocus()));

        //обрабатывается событие нажатия мыши
        node.addEventHandler(MouseEvent.MOUSE_PRESSED, (MouseEvent me) -> {
            //метка перемещается поверх всех остальных элементов
            node.toFront();
            //запоминается отклонение кординат курсора мыши от левого верхнего угла (учитывается разница координат ВЛУ сцены и курсора Мыши)
            delta = new Point2D((me.getSceneX() - node.getLayoutX()), (me.getSceneY() - node.getLayoutY()));
        });

        //обработка события перетаскивания метки мышью
        node.addEventHandler(MouseEvent.MOUSE_DRAGGED, (MouseEvent me) -> {
            //проверка - если нажата 2-я кнопка мыши то происходит изменение угла повоторота
            if (me.getButton() == MouseButton.SECONDARY) {
                double dx1 = me.getSceneX() - node.getLayoutX();
                double dy1 = me.getSceneY() - node.getLayoutY();
                //рассчитываем угол перемещения мыши и расчитывается угол поворота метки
                double angle = Math.acos(dx1 / Math.sqrt(dx1 * dx1 + dy1 * dy1));
                //если угол меньше 0 то угол меняется
                if (dy1 < 0) {
                    angle = Math.PI - angle;
                }
                //выполняем поворот метки на расчитанный агол (переведя его значение в градусы)
                node.setRotate(Math.toDegrees(angle));
                //если нажата первая кнопка мыши то
            } else {
                //меняем координаты метки по движению мыши
                node.setLayoutX(me.getSceneX() - delta.getX());
                node.setLayoutY(me.getSceneY() - delta.getY());
            }
        });

        //обработка нажатия мыши
        node.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent me) -> {
            //проверяем количество щелчков
            if (me.getClickCount() == 2) {
                node.setStyle(style);
            }
        });

        //обработка события клавиатуры
        node.addEventHandler(KeyEvent.KEY_PRESSED, (KeyEvent ke) -> {
            //проверяем нажатие клавиши "A"
            if (ke.getCode() == KeyCode.A) {
                node.setText("Нажата клавиша А");
            }
            if (ke.getCode() == KeyCode.A && ke.isShiftDown()) {
                node.setText("Нажаты Shift+A");
            }
            System.out.println(ke.getText());
        });
    }

    //Рассмотрим пример перехвата события на этапе фильтрации и передачи события для обработки другому элементу сцены.
    private VBox createPaneEventRout() {
        Label labelSource = new Label("Источник события");
        Label labelTarget = new Label("Цель события");
        Button btn = new Button("Инф. об источнике и цели события");
        btn.setOnAction(event -> {
            labelSource.setText("Источник события: " + event.getSource().toString());
            labelTarget.setText("Цель события " + event.getTarget().toString());
        });
        Button btn1 = new Button("Кнопка-1");
        Button btn2 = new Button("Кнопка-2");

        //кнопка-1 передает обработку события кнопке-2
        btn1.setOnAction(event -> Event.fireEvent(btn2, event));
        btn2.setOnAction(event -> {
            labelSource.setText("Источник события: " + event.getSource().toString());
            labelTarget.setText("Цель события " + event.getTarget().toString());
        });

        //перехват события (перехват осуществляется между кнопной-3 и контейнером vBox
        Button btn3 = new Button("Кнопка-3");
        btn3.setOnAction(event -> {
            labelSource.setText("Источник события: " + event.getSource().toString());
            labelTarget.setText("Цель события " + event.getTarget().toString());
        });
        //обработка собития происходит в контейнере на этапе фильтрации событий
        VBox box = new VBox(btn3);
        box.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-color: blue;");
        box.setAlignment(Pos.CENTER);
        //при перемещении события от корневого элемента кнопки на пути попадает контейнер box и вем присутствует
        //обработчик события на этапе фильтрации
        box.addEventFilter(ActionEvent.ACTION, event -> {
            labelSource.setText("Источник события: " + event.getSource().toString());
            labelTarget.setText("Цель события " + event.getTarget().toString());
            //для того чтобы события не проходило дальше надо прописать для события его остановку
            event.consume();
        });
        VBox pane = new VBox();
        pane.setStyle("-fx-font-size: 24");
        pane.setPrefSize(800, 600);
        pane.setAlignment(Pos.CENTER);
        pane.getChildren().addAll(labelSource, labelTarget, btn, btn1, btn2, btn3);

        return pane;
    }


}

