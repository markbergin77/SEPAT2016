package gui;

import java.awt.Dimension;
import java.awt.Point;
import java.util.Timer;
import java.util.TimerTask;

import data.Station;
import data.StationList;
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
    Explorer explorer;
    
    GuiCallbacks callbackObj;

    /* for testing purposes, no interaction */
    public HomeScreen()
    {
    	super();
    	explorer = new Explorer();
        add(explorer, 0, 0);
    }

    
    public HomeScreen(HomeInitObjects init, GuiCallbacks callbackObj)
    {
    	super();
    	explorer = new Explorer();
        add(explorer, 0, 0);
        this.callbackObj = callbackObj;
        explorer.addStationsAll(init.getAllStations(), 
        		StationButton -> callbackObj.onStationClicked(StationButton));
    }
    
    public Explorer getExplorer()
    {
    	return explorer;
    }
    
    public void startShowing()
    {   	

    }
}
