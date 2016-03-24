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
import javafx.scene.shape.Rectangle;
import javafx.stage.*;
import javafx.util.Duration;


public class Home {

    private static Button BUTTON, BUTTON2,exit,minimize;
    private static Scene SCENE1;
    private static StackPane stackPane;
    private static Rectangle rect,rect2,rect3;
    private static TextField text;
    private static ToolBar toolBar;
    private static Stage WINDOW;
    double xPos,yPos;

    public void display(Stage window){

        WINDOW = window;

        Utilities util = new Utilities();

        stackPane = new StackPane();


       // Polygon poly, poly2;

       // poly= new Polygon();
       // poly.getPoints().addAll(new Double[]{
           //     0.0, 0.0,
           //     20.0, -28.0,
           //     20.0, 28.0 });


       // poly2= new Polygon();
      //  poly2.getPoints().addAll(new Double[]{
        //        -20.0, -28.0,
          //      0.0, 0.0,
          //      -20.0, 28.0 });


        VBox box = new VBox(0);
        VBox box2 = new VBox(0);
        VBox box3 = new VBox(0);

        rect = new Rectangle(800,350);
        rect.setArcHeight(20);
        rect.setArcWidth(20);
        rect.setOpacity(0);
        rect.setStroke(Color.LIGHTSLATEGRAY);
        rect.setFill(Color.rgb(38,38,38));
        rect.setStrokeWidth(2);

        rect2 = new Rectangle(800,250);
        rect2.setArcHeight(20);
        rect2.setArcWidth(20);
        rect2.setOpacity(0);
        rect2.setStroke(Color.LIGHTSLATEGRAY);
        rect2.setFill(Color.rgb(38,38,38));
        rect2.setStrokeWidth(2);

        rect3 = new Rectangle(430,625);
        rect3.setArcHeight(20);
        rect3.setArcWidth(20);
        rect3.setOpacity(0);
        rect3.setStroke(Color.LIGHTSLATEGRAY);
        rect3.setFill(Color.rgb(38, 38, 38));
        rect3.setStrokeWidth(2);

        box.getChildren().addAll(rect);
        box.setMaxSize(800,450);

        box2.getChildren().addAll(rect2);
        box2.setMaxSize(800,250);

        box3.getChildren().addAll(rect3);
        box3.setMaxSize(430,625);

        rect.setId("rect");
        rect.applyCss();

        rect2.setId("rect2");
        rect2.applyCss();

        rect3.setId("rect3");
        rect3.applyCss();

       // BUTTON = new Button();
       // BUTTON.setId("t1");
       // BUTTON.setShape(poly);

       // BUTTON2 = new Button();
      //  BUTTON2.setId("t2");
       // BUTTON2.setShape(poly2);

        exit = new Button("X");
        exit.setId("exit");
        exit.setMaxSize(50,25);

       // minimize = new Button("O");
      //  minimize.setId("minimize");
      //  minimize.setMaxSize(50,25);


       //Separator sp = new Separator(Orientation.VERTICAL);
       // sp.setOpacity(0);
       // sp.setMinWidth(1);

       // Separator sp2 = new Separator(Orientation.VERTICAL);
       // sp2.setOpacity(0);
       // sp2.setMinWidth(1);


       // text = new TextField();
       // text.setPromptText("Search stations");


        exit.setOnMouseEntered(e -> exit.setId("exit-h"));
        exit.setOnMouseExited(e -> exit.setId("exit"));

        exit.setOnMousePressed(e ->
        {
            FadeTransition fadeTransition
                    = new FadeTransition(Duration.millis(500),exit);
            fadeTransition.setFromValue(0.5);
            fadeTransition.setToValue(1.0);
            fadeTransition.play();
            fadeTransition.setOnFinished(ex -> {
                System.exit(1);
            });
        });

       // minimize.setOnMouseEntered(e -> minimize.setId("minimize-h"));
       // minimize.setOnMouseExited(e -> minimize.setId("minimize"));

       // BUTTON.setOnMouseEntered(e -> BUTTON.setId("button-hover-t"));
       // BUTTON.setOnMouseExited(e -> BUTTON.setId("t1"));

        //BUTTON2.setOnMouseEntered(e -> BUTTON2.setId("t2-hover"));
       // BUTTON2.setOnMouseExited(e -> BUTTON2.setId("t2"));

       // BUTTON.toFront();
       // BUTTON2.toFront();
       // BUTTON.getStyleClass().remove("button");
       // BUTTON2.getStyleClass().remove("button");
       // BUTTON.setOpacity(0);
       // BUTTON2.setOpacity(0);

        toolBar = new ToolBar(exit);
        toolBar.setMaxSize(1350,35);
        toolBar.setOpacity(0);

        util.resizeWindowIncrease(WINDOW,800,400,2,4);

        StackPane.setMargin(box,new Insets(0,0,150,450));
        StackPane.setMargin(box2,new Insets(400,0,0,450));
        StackPane.setMargin(box3,new Insets(30,830,0,0));
        StackPane.setAlignment(toolBar, Pos.TOP_CENTER);

       // StackPane.setMargin(BUTTON,new Insets(0,460,300,0));
       // StackPane.setMargin(BUTTON2,new Insets(0,0,300,1100));
        stackPane.setAlignment(Pos.CENTER);
        stackPane.getChildren().addAll(toolBar,box,box2,box3);

        SCENE1 = new Scene(stackPane,1345,789);
        SCENE1.setFill(Color.TRANSPARENT);
        dragWindow(SCENE1);

        util.getCss(SCENE1);
        WINDOW.setTitle("Home");
        WINDOW.setScene(SCENE1);

    }

    public void fadeIn(){
        FadeTransition fT1
                = new FadeTransition(Duration.millis(1000), BUTTON);
        fT1.setFromValue(0.0);
        fT1.setToValue(1.0);
        fT1.setDelay(Duration.millis(500));
        fT1.play();

        FadeTransition fT2
                = new FadeTransition(Duration.millis(1000), rect);
        fT2.setFromValue(0.0);
        fT2.setToValue(1.0);
        fT2.setDelay(Duration.millis(500));
        fT2.play();

        FadeTransition fT3
                = new FadeTransition(Duration.millis(1000), BUTTON2);
        fT3.setFromValue(0.0);
        fT3.setToValue(1.0);
        fT3.setDelay(Duration.millis(500));
        fT3.play();


        FadeTransition fT4
                = new FadeTransition(Duration.millis(1000), rect2);
        fT4.setFromValue(0.0);
        fT4.setToValue(1.0);
        fT4.setDelay(Duration.millis(500));
        fT4.play();

              FadeTransition fT5
                = new FadeTransition(Duration.millis(1000), rect3);
        fT5.setFromValue(0.0);
        fT5.setToValue(1.0);
        fT5.setDelay(Duration.millis(500));
        fT5.play();

        FadeTransition fT6
                = new FadeTransition(Duration.millis(1000), toolBar);
        fT6.setFromValue(0.0);
        fT6.setToValue(1.0);
        fT6.setDelay(Duration.millis(500));
        fT6.play();


    }

    public void dragWindow(Scene scene){

        scene.setOnMousePressed(e -> {
            this.xPos = WINDOW.getX() - e.getScreenX();
            this.yPos = WINDOW.getY() - e.getScreenY();
        });

        scene.setOnMouseDragged(e -> {
            WINDOW.setX(e.getScreenX() + this.xPos);
            WINDOW.setY(e.getScreenY() + this.yPos);
        });

    }




}
