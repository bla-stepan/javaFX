package JavaFXChartExample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;

//создаем класс для описания функции для при мера LineChart
public class Function {
    //инициируем необхдимые для функции переменные
    double xMin, xMax;
    int step;

    //создаем конструктор касса Function, который принимает параметры мин, макс, и шаг и рассчитывает значения для функции
    public Function(double min, double max, int numStep) {
        xMin = min;
        xMax = max;
        step = numStep;
    }

    //метод вычисления результата
    private double funcResult(double x) {
        return (3 - 4 * x) / (x * x + 1);
    }

    //метод создания сери значений для графика
    public XYChart.Series getSeries() {

        //создаем коллекцию значений (набор точек для функции)
        ObservableList<XYChart.Data> dataList = FXCollections.observableArrayList();
        //
        double h = (xMax - xMin) / step;
        //выполняем итеррацию для расчета точек
        for (int i = 0; i < step; i++) {
            dataList.add(new XYChart.Data(xMin + h * i, funcResult(xMin + h * i)));
        }
        XYChart.Series series = new XYChart.Series();
        series.setName("(3-4*x)/(x*x+1)");
        series.setData(dataList);
        return series;
    }
}
