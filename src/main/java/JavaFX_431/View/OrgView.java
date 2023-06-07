package JavaFX_431.View;

import JavaFX_431.Model.Organization;
import javafx.geometry.Insets;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;


public class OrgView {

    private Organization org;
    private StackPane pane;

    public OrgView() {
        createPane();
    }

    public void createPane() {
        pane = new StackPane();//создаем панель. такая панель располагает элементы (стопкой последовательно друг на друга)
        pane.setPadding(new Insets(5));//задаем отступы

        Rectangle rectangle = new Rectangle(150, 120);//создаем прямоугольник
        rectangle.setFill(Color.AQUA);//заполняем цветом
        rectangle.setStroke(Color.DARKBLUE);//цвет обводки
        rectangle.setStrokeWidth(3);//толщина обводки границы

        pane.getChildren().add(rectangle);//сначало располагаем прямоугольник на панели (индекс 2)
        Text textOrg = new Text();//создаем текст

        pane.getChildren().add(textOrg);//повер прямоугольника располагаем текст (индекс 1)
    }

    public void setOrg(Organization org) {
        this.org = org;
        //также формируем надпись
        //вариант вызова элемента в панели
        // тип  панель          №элемента работаем с методами элемента
        ((Text)pane.getChildren().get(1)).setText(org.getName()+"\n"+org.getBossName()+"\n"+org.getPersonnel());
    }

    public StackPane getPane(){
        return pane;
    }
}
