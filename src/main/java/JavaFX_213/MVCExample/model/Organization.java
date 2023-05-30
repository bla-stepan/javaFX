package JavaFX_213.MVCExample.model;

import javafx.beans.property.*;

import java.time.LocalDate;

//в этом классе будем хранить данные
public class Organization {
    //создаем поля
    //синхронизируем поля названия организаии и донуса для организации взаимодействия м видом
    private StringProperty name;//делаем строковое поле свойством
    private IntegerProperty personal;
    private StringProperty holiday;
    private ObjectProperty<LocalDate> date;//для даты используется универсальное свойство ObjectProperty<T>
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
    public final void setName(String value){
        nameProperty().set(value);//меняем имеено свойство с момощью метода nameStringProperty свойства, а не строковое значение
    }
    //метод вовзращающийз значение свойства имени
    public final String getName() {
        return nameProperty().get();//возвращаем значение свойства имени через nameStringProperty через метод get().
    }

    //пищем методы взаимодейтсия модели с другими частями приложения геттеры и сеттеры
    public IntegerProperty personalProperty(){
        personal = personal==null ? new SimpleIntegerProperty() : personal;
        return personal;
    }

    public final void setPersonal(int value){
        personalProperty().set(value);
    }

    public final int getPersonal() {
        return personalProperty().get();
    }

    public StringProperty holidayProperty(){
        holiday = holiday==null ? new SimpleStringProperty() : holiday;
        return holiday;
    }

    public final void setHoliday(String holidayValue){
        holidayProperty().set(holidayValue);
    }

    public final String getHoliday() {
        return holidayProperty().get();
    }

    public ObjectProperty<LocalDate> dateProperty(){
        date = date==null ? new SimpleObjectProperty<>() : date;
        return date;
    }

    public final void setDate(LocalDate date) {
        dateProperty().set(date);
    }

    public final LocalDate getDate() {
        return dateProperty().get();
    }

    //переделываем метод на свойство
    public DoubleProperty bonusProperty(){
        if (bonus==null){
            bonus=new SimpleDoubleProperty();
        }
        return bonus;
    }

    public final Double getBonus() {
        return bonusProperty().get();
    }
    //переделываем метод на ствойство
    public final void setBonus(Double bonusValue) {
        bonusProperty().set(bonusValue);
    }

    //создаем конструктор
    public Organization(String name, int personal, String holiday, LocalDate date, Double bonus) {
        nameProperty().set(name);//this.name = organizationName;
        personalProperty().set(personal);
        holidayProperty().set(holiday);
        dateProperty().set(date);
        bonusProperty().set(bonus);
    }

    //создаем метод обработки данных (увеличение премиального фонда на 1 денежную единицу
    public Double getBonusPerPersonal() {
        return bonusProperty().get()/personalProperty().get();
    }
}
