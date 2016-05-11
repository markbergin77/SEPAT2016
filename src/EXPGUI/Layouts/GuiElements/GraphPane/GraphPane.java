package EXPGUI.Layouts.GuiElements.GraphPane;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Created by Pavel Nikolaev on 4/05/2016.
 */
public class GraphPane extends Parent{

    private LineChart weatherPlot;

    public StackPane getGraphLarge(StackPane pane) {

        Rectangle plotRect = new Rectangle(800, 400);
        this.weatherPlot = new LineChart<Number, Number>(new NumberAxis(), new NumberAxis());

        weatherPlot.setMaxSize(800, 370);
       // weatherPlot.setOpacity(0);

        plotRect.setArcHeight(20);
        plotRect.setArcWidth(20);
      //  plotRect.setOpacity(0);
        plotRect.setStroke(Color.LIGHTSLATEGRAY);
        plotRect.setFill(Color.rgb(38, 38, 38));
        plotRect.setStrokeWidth(2);

         plotRect.setId("rect");
         plotRect.applyCss();

        pane.getChildren().addAll(plotRect, weatherPlot);
        pane.setMaxSize(800, 350);

        StackPane.setAlignment(weatherPlot, Pos.TOP_CENTER);
        //pane.setOpacity(0.97);

        return pane;

    }

    public StackPane getGraphMedium(StackPane pane) {

        Rectangle plotRect = new Rectangle(730, 350);
        this.weatherPlot = new LineChart<Number, Number>(new NumberAxis(), new NumberAxis());

        weatherPlot.setMaxSize(730,320);
        //weatherPlot.setOpacity(0);

        plotRect.setArcHeight(20);
        plotRect.setArcWidth(20);
        //plotRect.setOpacity(0);
        plotRect.setStroke(Color.LIGHTSLATEGRAY);
        plotRect.setFill(Color.rgb(38,38,38));
        plotRect.setStrokeWidth(2);

        plotRect.setId("rect");
        plotRect.applyCss();

        //add all children to the plotRect
        //and set dimensions/alignment
        pane.getChildren().addAll(plotRect,weatherPlot);
        pane.setMaxSize(730,300);

        StackPane.setAlignment(weatherPlot, Pos.TOP_CENTER);
        //pane.setOpacity(0.97);

        return pane;
    }

    public StackPane getGraphsmall(StackPane pane) {

        Rectangle plotRect = new Rectangle(630,350);
        this.weatherPlot = new LineChart<Number,Number>(new NumberAxis(),new NumberAxis());

        weatherPlot.setMaxSize(630,350);
        //weatherPlot.setOpacity(0);

        plotRect.setArcHeight(20);
        plotRect.setArcWidth(20);
       // plotRect.setOpacity(0);
        plotRect.setStroke(Color.LIGHTSLATEGRAY);
        plotRect.setFill(Color.rgb(38,38,38));
        plotRect.setStrokeWidth(2);

        plotRect.setId("rect");
        plotRect.applyCss();

        pane.getChildren().addAll(plotRect,weatherPlot);
        pane.setMaxSize(630,350);
        StackPane.setAlignment(weatherPlot,Pos.TOP_CENTER);
      //  pane.setOpacity(0.97);

       return pane;
    }

    public LineChart getWeatherPlot() {
        return weatherPlot;
    }

    public void setWeatherPlot(LineChart weatherPlot) {
        this.weatherPlot = weatherPlot;
    }
}