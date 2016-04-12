package bomWeatherGui;


import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Created by Pavel Nikolaev on 26/03/2016.
 */
public class ListNode extends Parent {

  //  private String LOCATION;
  //  private int TEMP;
  //  private Boolean RAINING, SUNNY;

    public ListNode () {
        setContent();
    }

    public void setContent(){

        Rectangle rect = new Rectangle(340,65,Color.rgb(20,20,20,0.9));
        rect.setArcHeight(10);
        rect.setArcWidth(10);
        rect.setStroke(Color.LIGHTSLATEGRAY);
        rect.setStrokeWidth(2);
        rect.setStyle("-fx-effect: dropshadow(three-pass-box,black, 20, 0.3, 0, 0);");

        rect.setOnMouseEntered(e -> rect.setStyle("-fx-effect: dropshadow(three-pass-box,#606060, 20, 0.3, 0, 0);"));
        rect.setOnMouseExited(e -> rect.setStyle("-fx-effect: dropshadow(three-pass-box,black, 20, 0.3, 0, 0);"));

        StackPane pane = new StackPane();
        pane.setPrefSize(340,65);
        pane.getStyleClass().add("container");
        pane.getChildren().add(rect);
        StackPane.setAlignment(rect, Pos.CENTER);
        getChildren().add(rect);

    }


}
