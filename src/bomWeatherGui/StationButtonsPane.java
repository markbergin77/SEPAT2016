package bomWeatherGui;

import bomData.Station;
import bomData.StationList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.chart.LineChart;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.util.LinkedList;
import java.util.Vector;

public class StationButtonsPane extends StackPane
{
    VBox vbox;
    ScrollPane scrollPane;
    /* why the variable? Because otherwise,
     * if we change the handler, we have to 
       loop through all the buttons and change
       them individually. */
    StationButtonListener clickHandler;

    
    
    public StationButtonsPane()
    {
    	super();
    	vbox = new VBox(8);
    	vbox.setPadding(new Insets(15, 0, 15, 0));
    	scrollPane = new ScrollPane();
    	scrollPane.setVbarPolicy(ScrollBarPolicy.ALWAYS);
    	scrollPane.setContent(vbox);
    	scrollPane.setPrefHeight(400);
    	scrollPane.setFitToWidth(true);
    	getChildren().addAll(scrollPane);
    }
    
    public void createStationButtons(StationList bomStations, 
    		StationButtonListener clickHandler)
    {
    	this.clickHandler = clickHandler;
    	for (Station bomStation : bomStations)
    	{
    		StationButton node = new StationButton(bomStation);
    		node.setOnMouseClicked(
    				e -> this.clickHandler.onStationClicked(node.getStation()));
            vbox.getChildren().add(node);
            node.toFront();
    	}
    }
    
    public void setOnButtonClicked(StationButtonListener clickHandler)
    {
    	this.clickHandler = clickHandler;
    }

    public VBox getVBox(){
        return vbox;
    }

    public VBox updateList(){


        return null;
    }

    // need to think of a way to visually update the GUI when we search for a station. maybe have a listUpdate() method
    // and return that list for the gui to fade in
    // if you do think of a way to do this, implement all the back end for it and tell me i will find a way to represent it


}
