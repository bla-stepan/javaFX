package JavaFX_413.TreeViewExample.model;

public class ClassDescription {

    private String nameClass;//название класса

    //метод для описания способа отображения класса
    public String toString() {
        return nameClass;
    }

    private String descriptionClass;//описание класса

    //метод получния описания
    public String getDescriptionClass() {
        return descriptionClass;
    }

    //конструктор
    public ClassDescription(String name, String description) {
        nameClass = name;
        descriptionClass = description;
    }
}
