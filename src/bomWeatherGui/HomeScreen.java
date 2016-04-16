package bomWeatherGui;

import java.awt.Dimension;
import java.awt.Point;
import java.util.Timer;
import java.util.TimerTask;

import bomData.Station;
import bomData.StationList;

/**
 * Created by Pavel Nikolaev on 13/03/2016.
 */


import javafx.animation.*;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.*;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.stage.*;
import javafx.util.Duration;


public class HomeScreen extends GridPane
{
	// Old stuff,
	/*I'll leave this until near-ish the end.
	 * Then i'll remove unused functions just in case
	 * You never know
	 */
    Explorer explorer;

    public HomeScreen()
    {
    	super();
    	explorer = new Explorer();
        add(explorer, 0, 0);
    }
    
    public void addStationsAll(StationList stations, StationButtonListener listener)
    {
    	explorer.addStationsAll(stations, listener);
    }
    
    public void startShowing()
    {   	

    }
}
