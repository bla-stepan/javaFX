package JavaFX_332.MouseEvent;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MouseEventExample extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Tab simpleEvent = new Tab("Simple Event", createPaneSimpleEvent());

        TabPane root = new TabPane(simpleEvent);

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

    private TextField createTextField(){

        //объекты класса textFieldавтоматически участвуют в переключении фокуса
        TextField textField = new TextField();
        textField.setPrefSize(300, 100);
        textField.setFont(Font.font(20));

        //обработка двух событий нажание на клавишу и отпускаение
        textField.setOnKeyPressed((KeyEvent ke) ->{
            //обрабатыжваем события нажатия клавишы
            System.out.println("TextField. Key pressed " + ke.getText());
            //обрабатываем события при условии (ctrl+а) этот способ проверки удобен если есть символ клавиши
            if (ke.getText().equals("a") && ke.isAltDown()){
                //меняем шрифт текса в поле
                textField.setFont(Font.font("Arial", 30));
            }
            //обработка нажатия того же условия что и выше но по другому (сравниваем код) этот способ удобен для конкретной клавишы
            if (ke.getCode()== KeyCode.T && ke.isAltDown()){
                textField.setFont(Font.font("Times New Roman", 24));
            }
        });
        //обработка события отпускания клавиши
        textField.setOnKeyReleased((KeyEvent ke)->{
            System.out.println("TextField. Key released" + ke.getText());
        });
        return textField;
    }

    private Text createText(){
        Text text = new Text("TEXT");
        text.setFont(Font.font(30));
        text.prefWidth(60);
        text.setFocusTraversable(true);//настраиваем доступность перемещения фокуса т.к. объект text не участвуее в переключении фокуса
        //обработка события мыши
        text.setOnMouseClicked((MouseEvent me) ->{
            text.requestFocus();//перемещаем фокус
            System.out.println("Text. Mouse clicked");
        });
        //обработка события нажатия на клавишу
        text.setOnKeyPressed((KeyEvent ke)->{
            System.out.println("Text. Key pressed "+ ke.getText());
            if (ke.getText().equals("r")){
                text.setFill(Color.RED);
            }
            if (ke.getCode()==KeyCode.B){
                text.setFill(Color.BLACK);
            }
        });
        return text;
    }
}
