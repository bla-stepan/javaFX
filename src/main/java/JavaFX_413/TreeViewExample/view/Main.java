package JavaFX_413.TreeViewExample.view;

import JavaFX_413.TreeViewExample.model.ClassDescription;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {

    private TreeView<ClassDescription> createTreeView() {
        //создаем элементы
        TreeItem<ClassDescription> byteos = new TreeItem<>(new ClassDescription("OutputStream", "Байтовый поток вывода"));
        TreeItem<ClassDescription> filteros = new TreeItem<>(new ClassDescription("FilterOutputStream", "Специализированные потоки вывода"));
        TreeItem<ClassDescription> bufos = new TreeItem<>(new ClassDescription("BufferedOutputStream", "Вывод через буфер"));
        TreeItem<ClassDescription> prints = new TreeItem<>(new ClassDescription("PrintStream", "Форматный вывод"));
        filteros.getChildren().addAll(bufos, prints);
        TreeItem<ClassDescription> fileos = new TreeItem<>(new ClassDescription("FileOutputStream", "Вывод данных в файл"));
        TreeItem<ClassDescription> objos = new TreeItem<>(new ClassDescription("ObjectOutputStream", "Запись объектов в поток"));
        byteos.getChildren().addAll(filteros, fileos, objos);

        TreeItem<ClassDescription> writer = new TreeItem<>(new ClassDescription("Writer", "Символьный поток вывода"));
        TreeItem<ClassDescription> bw = new TreeItem<>(new ClassDescription("BufferedWriter", "Вывод через буфер"));
        TreeItem<ClassDescription> osw = new TreeItem<>(new ClassDescription("OutputStreamWriter", "Кодировка символов"));
        TreeItem<ClassDescription> fw = new TreeItem<>(new ClassDescription("FileWriter", "Вывод данных в файл"));
        osw.getChildren().add(fw);
        TreeItem<ClassDescription> pw = new TreeItem<>(new ClassDescription("PrintWriter", "Форматный вывод"));
        writer.getChildren().addAll(bw, osw, pw);

        TreeItem<ClassDescription> byteis = new TreeItem<>(new ClassDescription("InputStream", "Байтовый поток ввода"));
        TreeItem<ClassDescription> filteris = new TreeItem<>(new ClassDescription("FilterInputStream", "Специализированные потоки ввода"));
        TreeItem<ClassDescription> bufis = new TreeItem<>(new ClassDescription("BufferedInputStream", "Ввод через буфер"));
        filteris.getChildren().add(bufis);
        TreeItem<ClassDescription> fileis = new TreeItem<>(new ClassDescription("FileInputStream", "Ввод данных из файла"));
        TreeItem<ClassDescription> objis = new TreeItem<>(new ClassDescription("ObjectInputStream", "Чтение объектов из потока"));
        byteis.getChildren().addAll(filteris, fileis, objis);

        TreeItem<ClassDescription> reader = new TreeItem<>(new ClassDescription("Reader", "Символьный поток ввода"));
        TreeItem<ClassDescription> br = new TreeItem<>(new ClassDescription("BufferedReader", "Ввод через буфер"));
        TreeItem<ClassDescription> isr = new TreeItem<>(new ClassDescription("InputStreamReader", "Кодировка символов"));
        TreeItem<ClassDescription> fr = new TreeItem<>(new ClassDescription("FileReader", "Ввод данных из файла"));
        isr.getChildren().add(fr);
        reader.getChildren().addAll(br, isr);


        //корневой элемент
        TreeItem<ClassDescription> root = new TreeItem<>(new ClassDescription("In/Out", "Корневой элемент"));
        //передаем писок потомков
        root.getChildren().addAll(byteos, writer, byteis, reader);

        //создаем объект класса TreeView и передаем в него корневой элимент
        TreeView<ClassDescription> io = new TreeView<>(root);
        io.setPrefSize(350, 750);
        return io;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane root = new GridPane();
        root.setHgap(30);
        root.setVgap(20);
        root.setPadding(new Insets(20));
        root.setStyle("-fx-font-size: 14pt;");

        //одиночное выделение
        Label labelSingleSelect = new Label();
        GridPane.setValignment(labelSingleSelect, VPos.TOP);

        //создаем объект дерева
        TreeView<ClassDescription> tvSingleSelect = createTreeView();//дерево создаем через метод
        //выбираем модель выделения
        SelectionModel<TreeItem<ClassDescription>> selectionModel = tvSingleSelect.getSelectionModel();//выделение одного элемента
        //назначаем слушателя выделению
        selectionModel.selectedItemProperty().addListener((observable, oldValue, newValue) ->
                //меняем значение на мекте
                labelSingleSelect.setText(newValue.getValue().getDescriptionClass()));
        root.add(tvSingleSelect, 0, 0);
        root.add(labelSingleSelect, 0, 1);

        //несколько выделений
        Label labelMultiSelect = new Label();

        TreeView<ClassDescription> tvMultiSelect = createTreeView();

        MultipleSelectionModel<TreeItem<ClassDescription>> multiSelectionModel = tvMultiSelect.getSelectionModel();
        multiSelectionModel.setSelectionMode(SelectionMode.MULTIPLE);//дополнительно настраиваем модель выделения и передаем тип выделения
        multiSelectionModel.selectedItemProperty().addListener(((observable, oldValue, newValue) -> {
            String selected = "";
            for (TreeItem<ClassDescription> item : multiSelectionModel.getSelectedItems()) selected +=
                    newValue.getValue().getDescriptionClass() +"\n";
            labelMultiSelect.setText(selected);
        }));

        root.add(tvMultiSelect, 1, 0);
        root.add(labelMultiSelect, 1, 1);

        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }


}
