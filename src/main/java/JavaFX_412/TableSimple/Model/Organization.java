package JavaFX_412.TableSimple.Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Organization {
    //данный класс является моделью
    //для реализации функциональности таблицы все поля организации будут свойствами
    //параметр - название организации
    private StringProperty name;

    public StringProperty nameProperty() {
        if (name == null) {
            name = new SimpleStringProperty();
        }
        return name;
    }

    public void setName(String value) {
        nameProperty().set(value);
    }

    public String getName() {
        return nameProperty().get();
    }

    //параметр - имя руководителя организации
    private StringProperty bossName;

    public StringProperty bossNameProperty() {
        if (bossName == null) {
            bossName = new SimpleStringProperty();
        }
        return bossName;
    }

    public final void setBossName(String bossNameValue) {
        bossNameProperty().set(bossNameValue);
    }

    public final String getBossName() {
        return bossNameProperty().get();
    }

    //параметр - персонал организации
    private IntegerProperty personnel;

    public IntegerProperty personnelProperty() {
        if (personnel == null) {
            personnel = new SimpleIntegerProperty();
        }
        return personnel;
    }

    public final void setPersonnel(int personnelNumber){
        personnelProperty().set(personnelNumber);
    }

    public final int getPersonnel(){
        return personnelProperty().get();
    }

    //конструктор организации
    public Organization(String name, String bossName, Integer personnel) {
        nameProperty().set(name);
        bossNameProperty().set(bossName);
        personnelProperty().set(personnel);
    }
}
