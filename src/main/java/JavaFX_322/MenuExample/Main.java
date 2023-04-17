package JavaFX_322.MenuExample;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.awt.*;

public class Main extends Application {

    //внутренний вспомогательный класс
    private class Season {
        //поля класса
        private String name, description;
        private Image image;

        //метод класса конструктор
        public Season(String name, String description) {
            this.name = name;//имя времени года
            this.description = description;//описание времени года
            image = new Image(getClass().getResourceAsStream("images/" + name + ".jpg"));//название картинки соотвествует названию времен года
        }
    }

    //хранилище данных (создаем массив объектов времени года)
    private final Season[] seasons = new Season[]{
            new Season("Зима", "Описание к времени года - зима"),
            new Season("Лето", "Описание времени года лето"),
            new Season("Осень", "Описание к времени года - осени"),
            new Season("Весна", "Весна прекрасна")
    };

    //Информация о шрифтах в массиве
    private final Font[] fonts = new Font[]{
            new Font("Verdana Bold", 15),
            new Font("Arial Italic", 18),
            new Font("Tahoma", 20),
            new Font("Times New Roman", 10)
    };

    //
    @Override
    public void start(Stage primaryStage) throws Exception {

    }

    public static void main(String[] args) {
        launch(args);
    }
}
