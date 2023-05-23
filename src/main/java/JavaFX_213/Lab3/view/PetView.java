package JavaFX_213.Lab3.view;

import JavaFX_213.Lab3.model.PetModel;
import javafx.beans.binding.Bindings;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class PetView {
    //поле для связис с моделью
    private PetModel pet;
    private GridPane dataPane;//контейнер
    private Text type, nickname, ownerName, age, ageYear, ageMonth;
    private Image photo;
    private Font font = Font.font("Arial", FontWeight.NORMAL, 15);//для сокращения кода пишем шрифт
    private int prefW = 180, prefH = 10;
    private Text[] nodes;

    public void createPane() {
        //панель таблицы
        dataPane = new GridPane();
        dataPane.setAlignment(Pos.CENTER);
        dataPane.setVgap(5);
        dataPane.setHgap(5);
        dataPane.setPadding(new Insets(5, 5, 5, 5));

        //оглавление
        Label petTile = new Label("Информация о питомце");
        petTile.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
        GridPane.setHalignment(petTile, HPos.CENTER);
        GridPane.setValignment(petTile, VPos.TOP);
        petTile.setMaxWidth(180);
        petTile.setWrapText(true);
        dataPane.add(petTile, 0, 0, 4, 1);

        //метки
        Label labelNickName = new Label("Кличка:");
        labelNickName.setFont(font);
        labelNickName.setPrefSize(prefW, prefH);
        dataPane.add(labelNickName, 0, 2);

        Label labelType = new Label("Тип:");
        labelType.setFont(font);
        labelType.setPrefSize(prefW, prefH);
        dataPane.add(labelType, 0, 1);

        Label labelOwner = new Label("Хозяин:");
        labelOwner.setFont(font);
        labelOwner.setPrefSize(prefW, prefH);
        dataPane.add(labelOwner, 0, 3);

        Label labelAge = new Label("Возраст:");
        labelAge.setFont(font);
        labelAge.setPrefSize(prefW, prefH);
        dataPane.add(labelAge, 0, 4);

        //отображение информации о питомце
        type = new Text();
        type.setFont(font);
        //обработка события мыши для текста (кличка)
        type.setOnMouseEntered(MouseEvent -> {
            type.setFill(Color.RED);
        });
        type.setOnMouseExited(MouseEvent -> {
            type.setFill(Color.BLACK);
        });

        dataPane.add(type, 1, 1);

        nickname = new Text();
        nickname.setFont(font);
        nickname.setFocusTraversable(true);
        keyEvent(nickname);

        //обработка события мыши для текста (кличка)
        nickname.setOnMouseEntered(MouseEvent -> {
            nickname.setFill(Color.RED);
        });
        nickname.setOnMouseExited(MouseEvent -> {
            nickname.setFill(Color.BLACK);
        });

        dataPane.add(nickname, 1, 2);

        ownerName = new Text();
        ownerName.setFont(font);
        dataPane.add(ownerName, 1, 3);

        ageYear = new Text();
        ageMonth = new Text();

        age = new Text();
        age.setFont(font);
        dataPane.add(age, 1, 4);
    }

    private void keyEvent(Text node){
        node.addEventHandler(KeyEvent.KEY_PRESSED, (KeyEvent ke) -> {
            if (ke.getCode() == KeyCode.L && ke.isControlDown()) {
//                textSize(ke.getCode());
                node.setFont(Font.font(node.getFont().getSize()+0.5));
            }
            if (ke.getCode() == KeyCode.S && ke.isControlDown()) {
                if (node.getFont().getSize() - 0.5 > 0) {
                    node.setFont(Font.font(node.getFont().getSize() - 0.5));
                }
            }
        });
    }

    //метод назначения слушателей
    public void addListenerPet() {
        Bindings.bindBidirectional(type.textProperty(), pet.typeProperty());
        nickname.textProperty().bind(pet.nickNameProperty());
        ownerName.textProperty().bind(pet.ownerNameProperty());
        pet.ageYearProperty().addListener((observable, oldValue, newValue) -> {
            age.setText(pet.getStrAge());
        });
        pet.ageMonthProperty().addListener((observable, oldValue, newValue) -> {
            age.setText(pet.getStrAge());
        });
    }

    //метод загрузки питомца
    public void setPet(PetModel pet) {
        this.pet = pet;
        addListenerPet();//слушатели
        age.setText(pet.getStrAge());//обновление данных возраста
    }

    //конструктор вида
    public PetView(PetModel pet) {
        //метод создания панели
        createPane();
        //метод изменения данных
        setPet(pet);//передаем в параметр метод объект pet
    }

    //метод, возвращающий панель
    public GridPane getDataPane() {
        return dataPane;
    }
}
