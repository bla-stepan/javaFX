package JavaFX_432_Task;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.swing.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class MainTask extends Application {

    ObservableList<String> data = FXCollections.observableArrayList("Степан", "Анна", "Фёдор", "Яна", "Гена", "Гога");

    @Override
    public void start(Stage primaryStage) throws Exception {
        Label status = new Label("Статус");
        Button btnRun = new Button("Пуск");
        ListView<String> listView = new ListView<String>();
        listView.setPrefSize(450, 300);

        ProgressBar progressBar = new ProgressBar();
        progressBar.setPrefWidth(450);

        btnRun.setOnAction(ActionEvent -> {
            //внутри описания действия создаем класс task
            Task task = new Task<ObservableList<String>>() {
                @Override
                protected ObservableList<String> call() throws InterruptedException {
                    updateMessage("Поиск друзей ...");
                    ObservableList<String> friends = FXCollections.observableArrayList();
                    int i = 0;
                    Charset charset = StandardCharsets.UTF_8;
                    for (String item: data){
                        friends.add(item);
                        Thread.sleep(350);
                        updateProgress(i+1, data.size());
                        i++;
                    }
                    updateMessage("Поиск завершен");
                    return friends;
                }
            };
            status.textProperty().bind(task.messageProperty());
            btnRun.disableProperty().bind(task.runningProperty());//пока задача выполняется кнопка не доступна
            listView.itemsProperty().bind(task.valueProperty());
            progressBar.progressProperty().bind(task.progressProperty());

            task.stateProperty().addListener(new ChangeListener<Worker.State>() {
                @Override
                public void changed(ObservableValue<? extends Worker.State> observableValue, Worker.State oldState, Worker.State newState) {
                    if (newState == Worker.State.SUCCEEDED){
                        btnRun.setText("Ready");
                    }
                }
            });

            new Thread(task).start();
        });
        final VBox root = new VBox();
        root.setSpacing(10);
        root.getChildren().addAll(btnRun, status, progressBar, listView);
        root.setStyle("-fx-background-color: cornsilk; -fx-padding:20; -fx-font-size: 20pt;");
        primaryStage.setTitle("Example Task");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();


    }

    public static void main(String[] args) {
        launch(args);
    }
}
