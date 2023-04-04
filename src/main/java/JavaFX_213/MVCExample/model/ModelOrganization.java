package JavaFX_213.MVCExample.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;

//в этом классе будем хранить данные
public class ModelOrganization {
    //создаем поля
    //синхронизируем поля названия организаии и донуса для организации взаимодействия м видом
    private StringProperty name;//делаем строковое поле свойством
    private int personal;
    private String holiday;
    private LocalDate date;
    private DoubleProperty bonus;//переделан

    //в соотвествии с соглашением об именах для параметра имени создаем три метода
    // (соблюдение соглашения об именах обеспечивает работу компонентов javafx)
    //метод, возвращающий свойство
    public  StringProperty nameProperty(){
        if (name==null){
            name= new SimpleStringProperty();
        }
        return name;
    }
    //метод изменения свойства
    public void setName(String value){
        nameProperty().set(value);//меняем имеено свойство с момощью метода nameStringProperty свойства, а не строковое значение
    }
    //метод вовзращающийз значение свойства имени
    public String getName() {
        return nameProperty().getName();//возвращаем значение свойства имени через nameStringProperty через метод get().
    }
    public DoubleProperty bonusProperty(){
        if (bonus==null){
            bonus=new SimpleDoubleProperty();
        }
        return bonus;
    }

    //пищем методы взаимодейтсия модели с другими частями приложения геттеры и сеттеры


    public int getPersonal() {
        return personal;
    }

    public String getHoliday() {
        return holiday;
    }

    public LocalDate getDate() {
        return date;
    }
    //переделываем метод на свойство
    public Double getBonus() {
        return bonusProperty().get();
    }
    //переделываем метод на ствойство
    public void setBonus(Double bonusValue) {
        bonusProperty().set(bonusValue);
    }

    //создаем конструктор
    public ModelOrganization(String name, int personal, String holiday, LocalDate date, Double bonus) {
        setName(name);//this.name = organizationName;
        this.personal = personal;
        this.holiday = holiday;
        this.date = date;
        setBonus(bonus);//this.bonus = bonus;
    }

    //создаем метод обработки данных (увеличение премиального фонда на 1 денежную единицу
    public void incrementBonus() {
//        bonus++;
    }
}
