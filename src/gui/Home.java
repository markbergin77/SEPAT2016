package gui;

/**
 * Created by Pavel Nikolaev on 13/03/2016.
 */

import javafx.animation.FadeTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.TriangleMesh;
import javafx.stage.*;
import javafx.util.Duration;


public class Home {

    private static Button BUTTON, BUTTON2;
    private static Scene SCENE1;
    private static StackPane stackPane;
    private static Rectangle rect;

    public void display(Stage window){

        Utilities util = new Utilities();

        stackPane = new StackPane();

        Polygon poly, poly2;

        poly= new Polygon();
        poly.getPoints().addAll(new Double[]{
                0.0, 0.0,
                20.0, -28.0,
                20.0, 28.0 });


        poly2= new Polygon();
        poly2.getPoints().addAll(new Double[]{
                -20.0, -28.0,
                0.0, 0.0,
                -20.0, 28.0 });

        VBox box = new VBox(0);
        rect = new Rectangle(600,300);
        rect.setArcHeight(20);
        rect.setArcWidth(20);
        rect.setOpacity(0);
        rect.setStroke(Color.LIGHTSLATEGRAY);
        rect.setFill(Color.TRANSPARENT);
        rect.setStrokeWidth(2);
        box.getChildren().addAll(rect);
        box.setMaxSize(600,300);

        BUTTON = new Button();
        BUTTON.setId("t1");
        BUTTON.setShape(poly);

        BUTTON2 = new Button();
        BUTTON2.setId("t2");
        BUTTON2.setShape(poly2);

        BUTTON.setOnMouseEntered(e -> BUTTON.setId("button-hover-t"));
        BUTTON.setOnMouseExited(e -> BUTTON.setId("t1"));

        BUTTON2.setOnMouseEntered(e -> BUTTON2.setId("t2-hover"));
        BUTTON2.setOnMouseExited(e -> BUTTON2.setId("t2"));

        BUTTON.toFront();
        BUTTON2.toFront();

        util.resizeWindowIncrease(window,800,400,3,6);

        StackPane.setMargin(box,new Insets(162,0,310,0));
        StackPane.setMargin(BUTTON,new Insets(0,700,150,0));
        StackPane.setMargin(BUTTON2,new Insets(0,0,150,700));
        stackPane.setAlignment(Pos.CENTER);
        stackPane.getChildren().addAll(BUTTON,BUTTON2,box);

        SCENE1 = new Scene(stackPane,924,733);
        SCENE1.setFill(Color.TRANSPARENT);


        util.getCss(SCENE1);
        window.setTitle("Home");
        window.setScene(SCENE1);
        window.setResizable(true);


    }

    public void fadeIn(){
        FadeTransition fT1
                = new FadeTransition(Duration.millis(2000), BUTTON);
        fT1.setFromValue(0.0);
        fT1.setToValue(1.0);
        fT1.play();

        FadeTransition fT2
                = new FadeTransition(Duration.millis(2000), rect);
        fT2.setFromValue(0.0);
        fT2.setToValue(1.0);
        fT2.play();

    }




}
