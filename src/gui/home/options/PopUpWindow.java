package gui.home.options;

import data.Station;
import gui.plots.Last72hrTemp;
import javafx.geometry.Pos;
import javafx.scene.layout.*;


/**
 * Created by Pavel Nikolaev on 28/05/2016.
 */
public class PopUpWindow extends GridPane {

    public PopUpWindow(Station station){


        StackPane pane  = new StackPane();
        pane.setMaxSize(1300,900);
        pane.setMinSize(900,500);
        add(pane,0,0);

        Last72hrTemp plot = new Last72hrTemp(station);

        plot.setMaxSize(600,300);
        plot.setMinSize(600,300);

        pane.getChildren().add(plot);
        StackPane.setAlignment(plot, Pos.CENTER);

        setFillHeight(pane,true);
        setFillWidth(pane,true);

        setHgrow(pane, Priority.ALWAYS);
        setVgrow(pane,Priority.ALWAYS);

        ColumnConstraints c1 = new ColumnConstraints();
        c1.setPercentWidth(100);
        getColumnConstraints().add(c1);

    }
}
