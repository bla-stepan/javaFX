package JavaFX_322.MenuExample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;


public class Main extends Application {

    //внутренний вспомогательный класс
    private class Season {
        //поля класса
        public String name, description;
        public Image image;

        //метод класса конструктор
        public Season(String name, String description) {
            this.name = name;//имя времени года
            this.description = description;//описание времени года
            image = new Image(getClass().getResourceAsStream("images/" + name + ".jpg"));//название картинки соотвествует названию времен года
        }
    }

    //хранилище данных (создаем массив объектов времени года)
    private final Season[] seasons = new Season[]{
            new Season("Зима", "Когда приближается декабрь, в осеннем воздухе чувствуется дыхание зимы. +" +
                    "С каждым днем становится все холоднее." +
                    "От холода руки становятся холодными и негнущимися. Люди надевают теплую одежду." +
                    "И вдруг мягкие, белые снежинки падают густо и быстро, покрывая землю и крыши." +
                    "Когда ветви елей и сосен покрыты снегом, они выглядят красиво." +
                    "Снег толстым слоем лежит на земле, и птицы прыгают вокруг в поисках пищи." +
                    "С крыш домов свисает множество сверкающих на солнце сосулек. Кажется, что все накрыто белым одеялом." +
                    "Реки и озера покрыты толстым слоем льда, и по ним можно кататься на коньках." +
                    "Зима - веселое время для детей. Они лепят снеговиков и играют в снежки." +
                    "В хорошую погоду дети и взрослые катаются на лыжах, коньках или скатываются с заснеженных холмов."),
            new Season("Лето", "Яркое солнце согревает Землю. Когда стоит невыносимая жара, приятно выбраться за город и прогуляться по лесу." +
                    "Поля зеленые. Летом природа окружает нас всевозможными цветами: веселыми одуванчиками, прекрасными розами, застенчивыми маргаритками." +
                    "Иногда летом погода становится слишком жаркой, и воздух становится душным. Тогда гроза обычно приносит облегчение." +
                    "Небо внезапно покрывается темными тучами, и слышны отдаленные раскаты грома. Позже за яркими вспышками молнии следуют раскаты грома, и начинается дождь." +
                    "После грозы воздух становится удивительно свежим, и очень часто мы можем увидеть красивый мост через небо, который называется радугой."),
            new Season("Осень", "В сентябре лето уступает место осени. Дни становятся короче, а ночи длиннее. Солнечные лучи уже не такие яркие и теплые." +
                    "Фруктовые деревья, которые не так давно цвели цветами, отяжелели от сочных яблок и груш. Ранняя осень - это период ярких красок, когда пейзаж становится золотисто-коричневым." +
                    "Днем в воздухе парит серебристая паутинка. Но, к сожалению, этот период хорошей погоды очень короток, и за ним следует глубокая осень." +
                    "Октябрь - лучший месяц для посадки деревьев. Это время года, когда деревья фантастические — желтые, красные, зеленые и коричневые. Деревья сбрасывают свои листья." +
                    "Теперь земля похожа на разноцветный ковер. Темнеет раньше, и часто небо заволакивают тяжелые тучи, принося дождь. Птицы не поют своих песен." +
                    "Они начинают уезжать в теплые страны." +
                    "Ноябрь - месяц туманов, дождей и снега. Животные надевают свои зимние пальто. Пейзаж становится довольно унылым.Природа медленно засыпает на зимуи"),
            new Season("Весна", "Весна - чудесное время года. Светит теплое солнце, которое растопляет зимний лед и снег." +
                    "Природа начинает пробуждаться." +
                    "Все деревья и кустарники покрыты маленькими бутонами, которые становятся все больше и больше и через некоторое время превращаются в молодые зеленые листья." +
                    "Тогда все начинает цвести, цветы повсюду. Мы слышим пение птиц и вдыхаем сладкий аромат цветущих деревьев." +
                    "Погода почти все время хорошая. Сейчас не жарко, поэтому мы с большим удовольствием отправляемся на прогулку." +
                    "Иногда весной идут дожди, и воздух наполняется запахом мокрой земли. Чем первая весенняя гроза, сопровождающаяся яркими вспышками молнии и громом." +
                    "Это делает почву плодородной, чтобы летом и осенью мы могли наслаждаться фруктами и овощами с наших огородов.")
    };

    //Информация о шрифтах в массиве
    private final Font[] fonts = new Font[]{
            new Font("Verdana Bold", 15),
            new Font("Arial Italic", 18),
            new Font("Tahoma", 20),
            new Font("Times New Roman", 10)
    };

    //создаем элементы для вида
    private Label name, description;
    private ImageView pic;
    private VBox vBox;
    private int currentIndex = -1;

    //метод для создания вида
    private void createSceneElement() {
        name = new Label();//метка для отображения имени
        name.setFont(fonts[0]);
        pic = new ImageView();
        pic.setFitHeight(200);
        pic.setPreserveRatio(true);//пропорции высоты и ширины фиксированы
        description = new Label();
        description.setFont(fonts[3]);
        description.setWrapText(true);//устанавливается перенос строк
        description.setTextAlignment(TextAlignment.JUSTIFY);//выравнивание текста по ширине

        vBox = new VBox();
        vBox.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));//цвет задан, граница нет, отступы нет
        vBox.setAlignment(Pos.CENTER);//размещаем в центр
        vBox.setSpacing(10);//расстояние между элементами
        vBox.setPadding(new Insets(0, 10, 0, 10));//отступы по краям
        vBox.getChildren().addAll(name, pic, description);

        shuffle();//метод устанавливает содержимое времени года
    }

    //метод для выбора случайного времени года
    private void shuffle() {
        int i = currentIndex;
        while (i == currentIndex) {
            i = (int) Math.random() * seasons.length;
        }
        pic.setImage(seasons[i].image);//загрузка картинки, сгенерированной конструктором по имени времени года
        name.setText(seasons[i].name);//загрузка имени времени года
        description.setText(seasons[i].description);
        currentIndex = i;//делаем вспомогательный рандомный индекс равным i
    }

    //метода для создания меню файл
    private Menu createFileMenu(){
        //создаем обюъек меню
        Menu menuFile = new Menu("Файл");
        MenuItem shuffle = new MenuItem("Сезон", new ImageView(new Image(getClass().getResourceAsStream("images/ssow.jpg"))));
        shuffle.setOnAction((ActionEvent t) -> {
            shuffle();//вызываем метод рандомного подбора времени года
            vBox.setVisible(true);//открываем видимость панели
        });

        MenuItem clean = new MenuItem("Стереть");
        clean.setAccelerator(KeyCombination.keyCombination("Ctrl+x"));//назначаем комбинацию клавиш
        clean.setOnAction((ActionEvent c) -> {
            vBox.setVisible(false);//скрываем видимость панели
            });

        MenuItem exit = new MenuItem("Выйти");
        exit.setOnAction((ActionEvent e) -> {
            Platform.exit();//выходим из программы
        });
        //загружаем созданные меню в гравное меню (перед выходом ставим разделитель
        menuFile.getItems().addAll(shuffle, clean, new SeparatorMenuItem(), exit);
        return menuFile;
    }

    //метод для управления видом элементов на панели (параметры заголовок пункта меню и узел сцены к которому он будет привязан
    private CheckMenuItem createViewMenuItem(String nameChackMenu, final Node node){
        //создаем поле-флажок
        CheckMenuItem checkMenuItem = new CheckMenuItem(nameChackMenu);
        checkMenuItem.setSelected(true);//устанавлимваем включенным
        //назначаем слушателя
        //обращаемся к свойству selectedProperty(), добавляем к нему слушателя
        checkMenuItem.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override//переопределяем метод инфтерфейчса changed
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                node.setVisible(newValue);//устанавливается видимость в соотвествии с новым значением newValue
            }
        });
    }

    //метод создания меню вида
    private Menu createViewMenu(){
        //меню содержит поля-флажки которые будут управлять видимостью элементов на панели
        Menu menuView = new Menu("Вид");
        CheckMenuItem titleView = createViewMenuItem("Заголовок", name);
        CheckMenuItem imageView = createViewMenuItem("Картинка", pic);
        CheckMenuItem descriptionView = createViewMenuItem("Описание", description);
        menuView.getItems().addAll(titleView, imageView, descriptionView);
        return menuView;
    }

    private Menu createEditMenu(){
        //состоит из двух частей 1-я часть состоит из подменю из 3-х пунктов позволяющих переключить шрифт
//        2-я часть - обычный пункт меню переключающий шрифт по умолчанию
        Menu menuEdit = new Menu("Шрифты");

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Пример программы с меню");
        BorderPane borderPane = new BorderPane();//создаем корневой элемент сцены (расположение в 5-ти частях)
        borderPane.setStyle("-fx-font-size: 16 pt");

        createSceneElement();//метод для создания вида (контейнер Hbox содержащий заголовок, картинку и описание

        //создаем главное меню приложения
        MenuBar menu = new MenuBar();
        //загружаем внутренние меню (отдельные методы) в гллавное меню
        menu.getMenus().addAll(createFileMenu(), createViewMenu(), createEditMenu());

        //дополнительное кнопочное меню (отдельный метод)
        SplitMenuButton menuColor = createEditColorMenu();

        //контекстное меню (отдельный метод)
        createCopyIngMenu();

        //размещаем элменты в конревом элементе
        borderPane.setTop(menu);
        borderPane.setCenter(vBox);
        borderPane.setBottom(menuColor);

        primaryStage.setScene(new Scene(borderPane, 600, 600));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
