package gui;

import data.Station;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import user.Favourite;
/* This goes to the right of the explorer
 * and has buttons to open plots of weather
 * data for the selected station in "Explorer" */
public class OptionsArea extends VBox
{
	/* When a button is pressed, send a
	 * message to this object through one 
	 * of the documented interface methods */
	GuiEventInterface callbackObj;
	/* Text displayed before any station selected */
	String promptText = "Please select a station";
	Label promptLabel = new Label(promptText);
	/* Looks ugly if right at top */
	static double promptInsetY = 16;
	VBox spacingBox = new VBox();
	
	static double defaultWidth = 400;
	
	Station selectedStation;
	
	public OptionsArea(GuiEventInterface callbackObj)
	{
		this.callbackObj = callbackObj;
		this.setPrefSize(defaultWidth, HomeScreen.defaultHeight);
		promptLabel.setPrefWidth(defaultWidth);
		promptLabel.setAlignment(Pos.BASELINE_CENTER);
		promptLabel.setFont(new Font(24));
		spacingBox.setPrefHeight(promptInsetY);
		getChildren().addAll(spacingBox, promptLabel);
	}
	
	void removePrompt()
	{
		getChildren().removeAll(spacingBox, promptLabel);
	}
	

	public void setStationFav(Favourite fav) 
	{
		removePrompt();
		
	}

	public void setStation(Station station) 
	{
		removePrompt();
	}
	
	
}
