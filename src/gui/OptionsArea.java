package gui;

import data.Station;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import user.Favourite;
/* This goes to the right of the explorer
 * and has buttons to open plots of weather
 * data for the selected station in "Explorer" 
 * 18/4/16: This area has tabs for having
 * multiple stations open at once.
 * */
public class OptionsArea extends TabPane
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
	VBox promptSpacingBox = new VBox();
	
	static double defaultWidth = 400;
	
	Station selectedStation;
	
	public OptionsArea(GuiEventInterface callbackObj)
	{
		this.callbackObj = callbackObj;
		this.setPrefSize(defaultWidth, HomeScreen.defaultHeight);
		promptLabel.setPrefWidth(defaultWidth);
		promptLabel.setAlignment(Pos.BASELINE_CENTER);
		promptLabel.setFont(new Font(24));
		promptSpacingBox.setPrefHeight(promptInsetY);
		getChildren().addAll(promptSpacingBox, promptLabel);
	}
	
	void removePrompt()
	{
		getChildren().removeAll(promptSpacingBox, promptLabel);
	}
	

	public void addTabForFav(Favourite fav) 
	{
		removePrompt();
	}

	public void addTabForStation(Station station) 
	{
		removePrompt();
	}
	
	
}
