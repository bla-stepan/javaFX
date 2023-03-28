package JavaFXChartExample;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.Spinner;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
//ПРИНЦИПИАЛЬНЫЙ ПРИМЕР СОЗДАНИЯ ГИСТОГРАММЫ
public class ChartExample extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        //создаем вкладки
        Tab tabBarChart = new Tab("BarChart", createBarChart());
        Tab tabLineChart = new Tab("LineChart", createLineChart());

        //создаем корневой элемент панели для вкладок
        TabPane tabPaneMin = new TabPane();
        tabPaneMin.getTabs().addAll(tabBarChart, tabLineChart);
        primaryStage.setTitle("Примеры графиков и диаограмм");
        primaryStage.setScene(new Scene(tabPaneMin));
        primaryStage.show();
    }

    //создаем гистограмму - объект BarChart
    private Group createBarChart() {

        //создаем панель
        Group group = new Group();

        //создаем переменные для построения гистрограммы
        //создаем переменные строк с категориями
        String rossiya = "Россия", belarus = "Балоруссия", china = "Китай", iran = "Иран", kuba = "Куба";
        String[] countries = new String[]{"Россия", "Балоруссия", "Китай", "Иран", "Куба"};

        //создаем ось объектов (категорий)
        final CategoryAxis xAxis = new CategoryAxis();

        //создаем ось значений
        final NumberAxis yAxis = new NumberAxis();

        //создаем конструктор для пострения гимстограммы
        final BarChart<String, Number> barChart = new BarChart(xAxis, yAxis);
        barChart.setTitle("Погода в странах");//создаем заголовок для barChart
        xAxis.setLabel("Страны");//задаем подпись оси с категориями
        yAxis.setLabel("Температура");//задаем подпись к оси со значениями температуры

        //для отображения гистограммы создаем объект XYChart.series
        XYChart.Series march = new XYChart.Series();
        march.setName("Март");
        march.getData().add(new XYChart.Data(rossiya, 10.1));
        march.getData().add(new XYChart.Data(belarus, 11.1));
        march.getData().add(new XYChart.Data(china, 9.11));
        march.getData().add(new XYChart.Data(iran, 15.1));
        march.getData().add(new XYChart.Data(kuba, 28.9));

        XYChart.Series april = new XYChart.Series();
        april.setName("Апрель");
        april.getData().add(new XYChart.Data(countries[0], 12.5));
        april.getData().add(new XYChart.Data(countries[1], 16.5));
        april.getData().add(new XYChart.Data(countries[2], 18.0));
        april.getData().add(new XYChart.Data(countries[3], 22.1));
        april.getData().add(new XYChart.Data(countries[4], 29.55));

        XYChart.Series may = new XYChart.Series();
        may.setName("Май");
        may.getData().add(new XYChart.Data(countries[0], 15.5));
        may.getData().add(new XYChart.Data(countries[1], 17.2));
        may.getData().add(new XYChart.Data(countries[2], 19.5));
        may.getData().add(new XYChart.Data(countries[3], 26.1));
        may.getData().add(new XYChart.Data(countries[4], 32.55));

        XYChart.Series june= new XYChart.Series();
        june.setName("Июнь");
        june.getData().add(new XYChart.Data(countries[0], 22.5));
        june.getData().add(new XYChart.Data(countries[1], 24.5));
        june.getData().add(new XYChart.Data(countries[2], 25.0));
        june.getData().add(new XYChart.Data(countries[3], 29.1));
        june.getData().add(new XYChart.Data(countries[4], 36.55));

        XYChart.Series july= new XYChart.Series();
        july.setName("Июль");
        july.getData().add(new XYChart.Data(countries[0], 21.5));
        july.getData().add(new XYChart.Data(countries[1], 23.5));
        july.getData().add(new XYChart.Data(countries[2], 25.9));
        july.getData().add(new XYChart.Data(countries[3], 28.1));
        july.getData().add(new XYChart.Data(countries[4], 33.55));

        //загружаем серии данных в конструктор гистрограммы
        barChart.getData().addAll(march, april, june, july);
        //загружаем гистрограмму (один объект) в панель
        group.getChildren().add(barChart);

        return group;
    }

    //создаем график - объект LineChart
    private Group createLineChart(){
        Group groupLineChart = new Group();

        //создаем объект класса Function
        Function function = new Function(-5, 3, 600);

        //создаем оси для графика (обе оси будут числовые)
        NumberAxis numberAxisX = new NumberAxis();
        NumberAxis numberAxisY = new NumberAxis();

        //создаем объект графика
        LineChart<Number, Number> lineChart = new LineChart<>(numberAxisX, numberAxisY);
        //методы настройки
        lineChart.setCreateSymbols(false);//делаем график вв виде линии (по умолчанию точки)
        lineChart.setLayoutX(400);
        lineChart.setLayoutY(60);
        lineChart.setMinHeight(600);
        lineChart.setMinWidth(600);
        lineChart.getData().add(function.getSeries());

        groupLineChart.getChildren().add(lineChart);

        return groupLineChart;
    }

    public static void main(String[] args) {
        launch();
    }
}
