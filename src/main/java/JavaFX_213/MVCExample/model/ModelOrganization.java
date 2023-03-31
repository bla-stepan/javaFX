package JavaFX_213.MVCExample.model;

import java.time.LocalDate;

//в этом классе будем хранить данные
public class ModelOrganization {
    //создаем поля
    private String organizationName;
    private int personal;
    private String holiday;
    private LocalDate date;
    private Double bonus;

    //пищем методы взаимодейтсия модели с другими частями приложения геттеры и сеттеры
    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public int getPersonal() {
        return personal;
    }

    public void setPersonal(int personal) {
        this.personal = personal;
    }

    public String getHoliday() {
        return holiday;
    }

    public void setHoliday(String holiday) {
        this.holiday = holiday;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getBonus() {
        return bonus;
    }

    public void setBonus(Double bonus) {
        this.bonus = bonus;
    }

    //создаем конструктор
    public ModelOrganization(String organizationName, int personal, String holiday, LocalDate date, Double bonus) {
        this.organizationName = organizationName;
        this.personal = personal;
        this.holiday = holiday;
        this.date = date;
        this.bonus = bonus;
    }

    //создаем метод обработки данных (увеличение премиального фонда на 1 денежную единицу
    public void incrementBonus() {
        bonus++;
    }
}
