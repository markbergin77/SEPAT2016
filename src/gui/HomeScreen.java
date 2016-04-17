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
import user.Favourite;


public class HomeScreen extends GridPane
{
    Explorer explorer;
    OptionsArea optionsArea;
    
    static double defaultHeight = 400;
    
    GuiEventInterface callbackObj;

    /* for testing purposes, no interaction */
    public HomeScreen()
    {
    	super();
    	explorer = new Explorer();
    	optionsArea = new OptionsArea(callbackObj);
        add(explorer, 0, 0);
        add(optionsArea, 1, 0);
    }
    
    public HomeScreen(HomeInitInfo init, GuiEventInterface callbackObj)
    {
    	super();
    	explorer = new Explorer();
    	optionsArea = new OptionsArea(callbackObj);
        add(explorer, 0, 0);
        add(optionsArea, 1, 0);
        this.callbackObj = callbackObj;
        explorer.addStationsAll(init.getAllStations(),
        		StationButton -> onStationClicked(StationButton));
        explorer.addStationsFav(init.user.getFavs(), f -> onFavClicked(f));
    }
    
    void onFavClicked(Favourite fav)
    {
    	optionsArea.setStationFav(fav);
    }
    
    void onStationClicked(Station station)
    {
    	optionsArea.setStation(station);
    }
    
    public Explorer getExplorer()
    {
    	return explorer;
    }
    
    public void startShowing()
    {   	
    	
    }
}