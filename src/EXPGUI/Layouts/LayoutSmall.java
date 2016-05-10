package EXPGUI.Layouts;


import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/**
 * Created by Pavel Nikolaev on 4/05/2016.
 */
public class LayoutSmall {

    public static Scene returnSceneSmall(){

    /*    // cre
        rootPane = new StackPane();
        // Create Plot
        plotPane = new StackPane();
        plotRect = new Rectangle(710,350);
        Rectangle clipRect = new Rectangle(712,352);
        clipRect.setArcHeight(20);
        clipRect.setArcWidth(20);
        plotPane.setClip(clipRect);

        weatherPlot = new LineChart<Number,Number>(new NumberAxis(),new NumberAxis());
        weatherPlot.setMaxSize(710,350);
        weatherPlot.setOpacity(0);
        plotRect.setArcHeight(20);
        plotRect.setArcWidth(20);
        plotRect.setOpacity(0);
        plotRect.setStroke(Color.LIGHTSLATEGRAY);
        plotRect.setFill(Color.rgb(38,38,38));
        plotRect.setStrokeWidth(2);
        plotPane.getChildren().addAll(plotRect,weatherPlot);
        plotPane.setMaxSize(710,350);

        StackPane.setAlignment(weatherPlot, Pos.TOP_CENTER);
        StackPane.setAlignment(plotPane,Pos.TOP_CENTER);
        StackPane.setMargin(weatherPlot,new Insets(0,15,0,0));

        exitButton = new Button("X");
        exitButton.setId("exit");
        exitButton.setMaxSize(50,25);


        //applying different styles when user hovers over button
        exitButton.setOnMouseEntered(e -> exitButton.setId("exit-h"));
        exitButton.setOnMouseExited(e -> exitButton.setId("exit"));
        exitButton.setOnMousePressed(e ->  System.exit(1));

        // creating triangular buttons on either side iof the graph
        // by creating a polygon and passing it to the button's
        // set shape method

        Polygon poly, poly2;
        poly = new Polygon();
        poly.getPoints().addAll(new Double[]{
                0.0, 0.0,
                20.0, -28.0,
                20.0, 28.0 });

        poly2= new Polygon();
        poly2.getPoints().addAll(new Double[]{
                -20.0, -28.0,
                0.0, 0.0,
                -20.0, 28.0 });

        // setting button ID for styling purposes
        buttonLeft = new Button();
        buttonLeft.setId("t1");
        buttonLeft.setShape(poly);

        buttonRight = new Button();
        buttonRight.setId("t2");
        buttonRight.setShape(poly2);


        //creating separators that separate two buttons in the
        Separator sp = new Separator(Orientation.VERTICAL);
        sp.setOpacity(0);
        sp.setMinWidth(1);

        Separator sp2 = new Separator(Orientation.VERTICAL);
        sp2.setOpacity(0);
        sp2.setMinWidth(1);


        //aplying different style when user hovers over buttons
        buttonLeft.setOnMouseEntered(e -> buttonLeft.setId("button-hover-t"));
        buttonLeft.setOnMouseExited(e -> buttonLeft.setId("t1"));

        buttonRight.setOnMouseEntered(e -> buttonRight.setId("t2-hover"));
        buttonRight.setOnMouseExited(e -> buttonRight.setId("t2"));


        // pushes buttons to the front of the scene
        buttonLeft.toFront();
        buttonRight.toFront();

        // remove default styling in main.css
        buttonLeft.getStyleClass().remove("button");
        buttonRight.getStyleClass().remove("button");

        // setting initial opacity as 0 so elements fade in
        buttonLeft.setOpacity(0);
        buttonRight.setOpacity(0);

        // creating the export graph button for exporting graph
        // to another window
        // not functional

        exportGraph = new Button("Open Graph");
        exportGraph.setMinSize(180, 35);
        exportGraph.toFront();
        exportGraph.setOpacity(0);

        // introducing a show table button
        // as by default, the UI only shows the graph
        // since for this layout, there isnt enough space

        showTable = new Button("View data table");
        showTable.setMinSize(180,35);
        showTable.toFront();
        showTable.setOpacity(0);


        // creating a burger bar button, which will later
        // open a sliding menu
        menu = new Button();
        menu.setId("menubutton");
        menu.setMaxSize(50, 25);
        menu.toFront();

        // applying different style when user hovers over button
        menu.setOnMouseEntered(e -> menu.setId("menu-h"));
        menu.setOnMouseExited(e -> menu.setId("menubutton"));

        // creating a toolbar with two buttons separated by a separator object
        toolBar = new ToolBar(exitButton,sp,menu);
        toolBar.setMaxSize(950,35);
        toolBar.setOpacity(0);


        // applying different style when user hovers over button
        exportGraph.setOnMouseEntered(e -> exportGraph.getStyleClass().add("export-button-bright"));
        exportGraph.setOnMouseExited(e -> exportGraph.getStyleClass().remove("export-button-bright"));

        showTable.setOnMouseEntered(e -> showTable.getStyleClass().add("export-button-bright"));
        showTable.setOnMouseExited(e -> showTable.getStyleClass().remove("export-button-bright"));


        // experimenting with sliding graphs off the window and fading in a new one
        // not functional
        buttonRight.setOnMouseClicked(e -> {

            FadeTransition fT1 = new FadeTransition(Duration.millis(300), weatherPlot);
            fT1.setFromValue(1.0);
            fT1.setToValue(0.0);
            fT1.play();

            Timeline slideRight = new Timeline();
            slideRight.setCycleCount(1);
            final KeyValue key1 = new KeyValue(weatherPlot.translateXProperty(),800);
            final KeyFrame kfDwn = new KeyFrame(Duration.millis(400), key1);
            slideRight.getKeyFrames().add(kfDwn);
            slideRight.play();

        });


        // experimenting with sliding graphs off the window and fading in a new one
        // not functional
        buttonLeft.setOnMouseClicked(e -> {

            FadeTransition fT1 = new FadeTransition(Duration.millis(200), weatherPlot);
            fT1.setFromValue(1.0);
            fT1.setToValue(0.0);
            fT1.play();

            Timeline slideRight = new Timeline();
            slideRight.setCycleCount(1);
            final KeyValue key1 = new KeyValue(weatherPlot.translateXProperty(), -800);
            final KeyFrame kfDwn = new KeyFrame(Duration.millis(400), key1);
            slideRight.getKeyFrames().add(kfDwn);
            slideRight.play();
        });

        // giving the user the ability to drag the window
        dragWindow(rootPane);
        dragWindow2(toolBar);

        // positioning elements
        rootPane.getChildren().addAll(toolBar,plotPane,buttonLeft,buttonRight,exportGraph,showTable);
        StackPane.setMargin(toolBar, new Insets(0,0,456,0));
        StackPane.setMargin(plotPane, new Insets(50,0,0,0));
        StackPane.setMargin(buttonLeft, new Insets(0,785,35,0));
        StackPane.setMargin(buttonRight, new Insets(0,0,35,785));
        StackPane.setMargin(exportGraph, new Insets(370,200,0,0));
        StackPane.setMargin(showTable, new Insets(370,0,0,200));

        Scene scene = new Scene(rootPane,1320,740);
        scene.setFill(Color.TRANSPARENT);

        WindowDragger util = new WindowDragger();
        util.getCss(scene);

        return scene;*/
        return null;
    }

}
