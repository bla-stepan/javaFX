package EnterAge;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class Main extends Application {
    //переопределяем метод старт интерфейса application
    @Override
    public void start(Stage primaryStage) throws Exception {
        //в этом методе создаем элементы для ввода и отображения данных
        GridPane root = initRootlayout();//создаем объект таблицы, в котором будут располагаться элементы
        Scene scene = new Scene(root, 500, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Расчет возраста");
        primaryStage.show();
    }

    public static GridPane initRootlayout() {
        //в этом методе создаем корневой элемент и описываем работу с ним
        GridPane root = new GridPane();//создаем элемент таблицы
        root.setAlignment(Pos.CENTER);//задаем расположение элемента по центру
        root.setHgap(10);//задаем расстояние между элементами по горизонтали
        root.setVgap(10);//задаем расстояние по вертикали
        root.setPadding(new Insets(25, 25, 25, 25));//задаем отступы от краев сцены. параметром метода
        // специальный объект insets в котором устанавливаются отступы для всех сторон одним значением или отдельно для каждого

        //наполняем корневой элемент root
        Text sceneTitle = new Text("Рачет возраста");//создаем заголовок элемента
        Font fontBig = Font.font("Taoma", FontWeight.NORMAL, 26);//создаем шрифт текста (задаем семейство шрифта, жирность и размер)
        sceneTitle.setFont(fontBig);//изменяем шрифт заголовка

        //размещаем элементы в элементе - таблице, которая будет состоять из 2-х столбцов
        //размещаем заголовок в первом столбце, в первой строке, скрока занимает 2 столбца и 1 строку
        root.add(sceneTitle, 0, 0, 2, 1);

        //далее будем располагать метку в первом столбце и поле для ввода во 2-м столбце
        Label userName = new Label("Имя");//создаем метку
        userName.setFont(fontBig);//задаем шрифт
        root.add(userName, 0, 1);//располагаем на сцене

        //поле для ввода данных
        TextField nameTextFiled = new TextField();
        nameTextFiled.setFont(fontBig);//настраиваем шрифт
        root.add(nameTextFiled, 1, 1);//добавляем элемент

        //метка для ввода года рождения
        Label userYear = new Label("Год рождения");
        userYear.setFont(fontBig);
        root.add(userYear, 0, 2);

        //поле для ввода года рождения
        TextField yearTextField = new TextField();
        yearTextField.setFont(fontBig);
        root.add(yearTextField, 1, 2);

        //текст вывода возраста
        Text resoult = new Text("Результат");
        resoult.setFont(fontBig);

        //размещаем текст с результатом в колрневом элементе сцены
        root.add(resoult, 1, 6, 2, 1);

        //добавляем кнопку
        Button button = new Button("Расчет");
        button.setFont(fontBig);
        button.setOnAction(event -> {
            Calendar calendar = Calendar.getInstance(TimeZone.getDefault(), Locale.getDefault());//получаем временную зону по умолчанию и длокальное время
            calendar.setTime(new Date());
            String year = yearTextField.getText();//получаем текст из элемента
            if (year.matches("[0-9]+")) {//значение строки соотвествует целому числу
                int age = calendar.get(Calendar.YEAR) - Integer.parseInt(year);
                resoult.setText(nameTextFiled.getText() + " - " + age + strAge(age));
            } else {
                resoult.setFill(Color.RED);//меняем цвет шрифта
                resoult.setText("Год введен не правильно!");
            }
        });
        root.add(button, 1, 5);
        return root;
    }

    public static String strAge(Integer age) {
        String str = null;
        int count = age % 10;
        if (count<2){
            str=" год.";
        }else if (count>1 && count<5){
            str=" года.";
        }else {
            str=" лет.";
        }
        return str;
    }

    public static void main(String[] args) {
        launch();//метод, запускающий жизненный цикл приложения
    }
}
