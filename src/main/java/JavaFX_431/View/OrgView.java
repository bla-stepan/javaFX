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

    public OrgView(){
        createPane();
    }

    public StackPane createPane(){
        pane = new StackPane();
        pane.setPadding(new Insets(5));

        Rectangle rectangle = new Rectangle(150, 120);
        rectangle.setFill(Color.AQUA);
        rectangle.setStroke(Color.DARKBLUE);
        rectangle.setStrokeWidth(3);

        Text textOrg = new Text();

        pane.getChildren().addAll(rectangle, textOrg);
    }
}
