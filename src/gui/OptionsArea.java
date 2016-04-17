package gui;

import data.Station;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import user.Favourite;
/* This goes to the right of the explorer
 * and has buttons to open plots of weather
 * data for the selected station in "Explorer" 
 * 18/4/16: This area has tabs for having
 * multiple stations open at once.
 * */
public class OptionsArea extends VBox
{
	static double defaultWidth = 400;
	/* When a button is pressed, send a
	 * message to this object through one 
	 * of the documented interface methods */
	GuiEventInterface callbackObj;
	TabPane tabPane = new TabPane();
	boolean noTabs;
	/* Text displayed before any station selected */
	String promptText = "Please select a station";
	Label promptLabel = new Label(promptText);
	/* Looks ugly if right at top */
	static double promptInsetY = 16;
	VBox promptSpacingBox = new VBox();
	
		
	public OptionsArea(GuiEventInterface callbackObj)
	{
		this.callbackObj = callbackObj;
		this.setPrefSize(defaultWidth, HomeScreen.defaultHeight);
		noTabs = true;
		setupPrompt();
		addPrompt();
	}
	
	void addingTabChecks()
	{
		if(noTabs)
		{
			removePrompt();
			addTabPane();
			noTabs = false;
		}
	}
	
	void setupPrompt()
	{
		promptLabel.setPrefWidth(defaultWidth);
		promptLabel.setAlignment(Pos.BASELINE_CENTER);
		promptLabel.setFont(new Font(24));
		promptSpacingBox.setPrefHeight(promptInsetY);
	}
	
	
	
	void addTabPane()
	{
		getChildren().add(tabPane);
	}
	
	void addPrompt()
	{
		getChildren().addAll(promptSpacingBox, promptLabel);
	}
	
	void removePrompt()
	{
		getChildren().removeAll(promptSpacingBox, promptLabel);
		noTabs = false;
	}

	public void addTabForFav(Favourite fav) 
	{
		addingTabChecks();
	}

	public void addTabForStation(Station station) 
	{
		addingTabChecks();
	}	
}
