package gui;


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

        Rectangle rect = new Rectangle(362,65,Color.rgb(20,20,20,0.9));
        rect.setArcHeight(10);
        rect.setArcWidth(10);
        rect.setStroke(Color.LIGHTSLATEGRAY);
        rect.setStrokeWidth(2);

        StackPane pane = new StackPane();
        pane.setPrefSize(362,65);
        pane.getStyleClass().add("container");
        pane.getChildren().add(rect);


        getChildren().add(pane);


    }


}
