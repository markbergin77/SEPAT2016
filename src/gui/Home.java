package gui;

/**
 * Created by Pavel Nikolaev on 13/03/2016.
 */

import javafx.animation.FadeTransition;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.stage.*;
import javafx.util.Duration;


public class Home {

    private static Button BUTTON, BUTTON2,exit,minimize;
    private static Scene SCENE1;
    private static StackPane stackPane;
    private static Rectangle rect,rect2;
    private static TextField text;
    private static ToolBar toolBar;

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

        Circle c1;
        c1 = new Circle(5);

        VBox box = new VBox(0);
        VBox box2 = new VBox(0);

        rect = new Rectangle(700,300);
        rect.setArcHeight(20);
        rect.setArcWidth(20);
        rect.setOpacity(0);
        rect.setStroke(Color.LIGHTSLATEGRAY);
        rect.setFill(Color.TRANSPARENT);
        rect.setStrokeWidth(2);

        rect2 = new Rectangle(700,130);
        rect2.setArcHeight(20);
        rect2.setArcWidth(20);
        rect2.setOpacity(0);
        rect2.setStroke(Color.LIGHTSLATEGRAY);
        rect2.setFill(Color.TRANSPARENT);
        rect2.setStrokeWidth(2);

        box.getChildren().addAll(rect);
        box.setMaxSize(700,300);

        box2.getChildren().addAll(rect2);
        box2.setMaxSize(700,150);

        rect.setId("rect");
        rect.applyCss();

        rect2.setId("rect2");
        rect2.applyCss();

        BUTTON = new Button();
        BUTTON.setId("t1");
        BUTTON.setShape(poly);

        BUTTON2 = new Button();
        BUTTON2.setId("t2");
        BUTTON2.setShape(poly2);

        exit = new Button("");
        exit.setId("exit");
        exit.setShape(c1);
        exit.setMaxSize(20,20);

        minimize = new Button("");
        minimize.setId("minimize");
        minimize.setShape(c1);
        minimize.setMaxSize(20,20);

        Separator sp = new Separator(Orientation.VERTICAL);
        sp.setOpacity(0);
        sp.setMinWidth(1);

        Separator sp2 = new Separator(Orientation.VERTICAL);
        sp2.setOpacity(0);
        sp2.setMinWidth(1);


        text = new TextField();
        text.setPromptText("Search stations");

        toolBar = new ToolBar(exit,sp,minimize,sp2,text);

        exit.setOnMouseEntered(e -> exit.setId("exit-h"));
        exit.setOnMouseExited(e -> exit.setId("exit"));

        minimize.setOnMouseEntered(e -> minimize.setId("minimize-h"));
        minimize.setOnMouseExited(e -> minimize.setId("minimize"));

        BUTTON2.setOnMouseEntered(e -> BUTTON2.setId("t2-hover"));
        BUTTON2.setOnMouseExited(e -> BUTTON2.setId("t2"));

        BUTTON.toFront();
        BUTTON2.toFront();
        BUTTON.setOpacity(0);
        BUTTON2.setOpacity(0);

        util.resizeWindowIncrease(window,800,400,3,6);
        StackPane.setMargin(toolBar,new Insets(0,0,525,0));
        StackPane.setMargin(box,new Insets(162,0,270,0));
        StackPane.setMargin(box2,new Insets(400,0,0,0));
        StackPane.setMargin(BUTTON,new Insets(0,790,110,0));
        StackPane.setMargin(BUTTON2,new Insets(0,0,110,790));
        stackPane.setAlignment(Pos.CENTER);
        stackPane.getChildren().addAll(BUTTON,BUTTON2,box,box2,toolBar);

        SCENE1 = new Scene(stackPane,924,733);
        SCENE1.setFill(Color.TRANSPARENT);


        util.getCss(SCENE1);
        window.setTitle("Home");
        window.setScene(SCENE1);



    }

    public void fadeIn(){
        FadeTransition fT1
                = new FadeTransition(Duration.millis(2000), BUTTON);
        fT1.setFromValue(0.0);
        fT1.setToValue(1.0);
        fT1.setDelay(Duration.millis(500));
        fT1.play();

        FadeTransition fT2
                = new FadeTransition(Duration.millis(2000), rect);
        fT2.setFromValue(0.0);
        fT2.setToValue(1.0);
        fT2.setDelay(Duration.millis(500));
        fT2.play();

        FadeTransition fT3
                = new FadeTransition(Duration.millis(2000), BUTTON2);
        fT3.setFromValue(0.0);
        fT3.setToValue(1.0);
        fT3.setDelay(Duration.millis(500));
        fT3.play();


        FadeTransition fT4
                = new FadeTransition(Duration.millis(2000), rect2);
        fT4.setFromValue(0.0);
        fT4.setToValue(1.0);
        fT4.setDelay(Duration.millis(500));
        fT4.play();




    }




}
