package JavaFX_213.MVCExample.view;
//в этом классе будем создавать вид
//задача вида - организация отображения данных

import JavaFX_213.MVCExample.model.ModelOrganization;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.time.format.DateTimeFormatter;

public class ViewOrganisation {
    //создаем поля класса
    //поле для связи с моделью
    private ModelOrganization org;//поле для связи с моделью программы
    private GridPane gridPane;//поле для создания контейнера, для размещения на сцене
    private Text orgName;//для вывода названия организации
    private Text orgHoliday;//вывод праздника
    private Text orgBonus;//вывод премиальных
    private Font font = Font.font("Tahoma", FontWeight.NORMAL, 20);//для сокращения кода пишем шрифт

    //метод для сорздания вида отображения информации
    private void createPane() {
        //задаем панель
        gridPane = new GridPane();//табличная панель
        //настройки
        gridPane.setAlignment(Pos.CENTER);//размещение по центру
        gridPane.setHgap(10);//отступ элементов впо вертикали
        gridPane.setVgap(10);//отступ элементов по горизонтали
        gridPane.setPadding(new Insets(25, 25, 25, 25));//расстояние по краям

        //тектс название организации
        orgName = new Text();
        orgName.setFont(font);
        GridPane.setHalignment(orgName, HPos.CENTER);//выравниваем название организаци по середине строки
        gridPane.add(orgName, 0, 0, 2, 1);

        //название праздника
        orgHoliday = new Text();
        orgHoliday.setFont(font);
        gridPane.add(orgHoliday, 0, 1, 2, 1);

        //метка бонуса
        Label labelBonus = new Label("Премия");
        labelBonus.setFont(font);
        gridPane.add(labelBonus, 0, 2);

        //отобдражение значения премии
        orgBonus = new Text();
        orgBonus.setFont(font);
        gridPane.add(orgBonus, 1, 2);
    }

    //метод изменения тектовых полей
    public void setInform() {
        orgName.setText(org.getOrganizationName());
        orgHoliday.setText(org.getHoliday() + ". " + org.getDate().format(DateTimeFormatter.ofPattern("dd.MM.uuuu")));//приводим в нужный формат
        orgBonus.setText(Double.toString(org.getBonus()));
    }

    //метод загрузки орнанизации
    public void setOrganization(ModelOrganization org) {
        this.org = org;
        setInform();//вызываем метод обработки элементов текста
    }

    //Конструктор вида
    public ViewOrganisation(ModelOrganization org) {
        createPane();//метод создания панели (контейнера) для отображения данных
        setOrganization(org);//вызываем метод изменения текстовых полей
    }

    //создаем панель (метод будет вызываться контролером)
    public GridPane getGridPane() {
        return gridPane;
    }
}
