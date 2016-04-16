package gui;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.chart.LineChart;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.util.LinkedList;
import java.util.Vector;

import data.Station;
import data.StationList;
import guiCallbacks.OnStationClicked;

public class StationButtonsPane extends GridPane
{
    VBox vbox;
    ScrollPane scrollPane;
    TextField searchBox;
    /* why the variable? Because otherwise,
     * if we change the handler, we have to 
       loop through all the buttons and change
       them individually. */
    OnStationClicked clickHandler;

    public StationButtonsPane()
    {
    	super();
    	vbox = new VBox(0);
    	scrollPane = new ScrollPane();
    	scrollPane.setVbarPolicy(ScrollBarPolicy.ALWAYS);
    	scrollPane.setContent(vbox);
    	scrollPane.setPrefHeight(400);
    	scrollPane.setPrefViewportWidth(StationButton.buttonWidth);
    	scrollPane.setFitToWidth(true);
    	searchBox = new TextField();
    	searchBox.setPromptText("Search...");
    	searchBox.setPrefWidth(scrollPane.getWidth());
    	add(searchBox, 0, 0);
    	add(scrollPane, 0, 1);
    }
    
    /* For testing purposes, no click handler */
    public void createStationButtons(StationList bomStations)
    {
    	for (Station bomStation : bomStations)
    	{
    		StationButton node = new StationButton(bomStation);
            vbox.getChildren().add(node);
            node.toFront();
    	}
    }
    
    public void createStationButtons(StationList bomStations, 
    		OnStationClicked clickHandler)
    {
    	this.clickHandler = clickHandler;
    	for (Station bomStation : bomStations)
    	{
    		StationButton node = new StationButton(bomStation);
    		node.setOnMouseClicked(
    				e -> this.clickHandler.handle(node.getStation()));
            vbox.getChildren().add(node);
            node.toFront();
    	}
    }
    
    public void setOnButtonClicked(OnStationClicked clickHandler)
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
