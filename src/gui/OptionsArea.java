package gui;

import java.util.Vector;

import data.Station;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
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
	/* Looks ugly if right at top */
	static double promptInsetY = 16;
	/* When a button is pressed, send a
	 * message to this object through one 
	 * of the documented interface methods */
	GuiEventInterface callbackObj;
	TabPane tabPane = new TabPane();
	SingleSelectionModel<Tab> tabSelectionModel = tabPane.getSelectionModel();
	/* Text displayed before any station selected */
	String promptText = "Select a station";
	Label promptLabel = new Label(promptText);
	
	VBox promptSpacingBox = new VBox();
	int nTabs;
	
	public OptionsArea(GuiEventInterface callbackObj)
	{
		this.callbackObj = callbackObj;
		this.setPrefSize(defaultWidth, HomeScreen.defaultHeight);
		nTabs = 0;
		setupPrompt();
		addPrompt();
	}

	void setupPrompt()
	{
		promptLabel.setPrefWidth(defaultWidth);
		promptLabel.setAlignment(Pos.BASELINE_CENTER);
		promptLabel.setFont(new Font(24));
		promptSpacingBox.setPrefHeight(promptInsetY);
	}
	
	void onAddingTab()
	{
		if(nTabs == 0)
		{
			removePrompt();
			addTabPane();
		}
		nTabs++;
		
	}
	
	void onTabClosed()
	{
		if(--nTabs == 0)
		{
			removeTabPane();
			addPrompt();
		}
	}
	
	void addTabPane()
	{
		getChildren().add(tabPane);
	}
	
	void removeTabPane()
	{
		getChildren().remove(tabPane);
	}
	
	void addPrompt()
	{
		getChildren().addAll(promptSpacingBox, promptLabel);
	}
	
	void removePrompt()
	{
		getChildren().removeAll(promptSpacingBox, promptLabel);
	}
	
	Tab findTabFor(Station station)
	{
		for(Tab optionsTab : tabPane.getTabs())
		{
			OptionsPaneBase pane = (OptionsPaneBase) optionsTab.getContent();
			if(pane.getStation().equals(station))
			{
				return optionsTab;
			}
		}
		return null;
	}
	
	public boolean hasTabFor(Station station)
	{
		
		for(Tab optionsTab : tabPane.getTabs())
		{
			OptionsPaneBase pane = (OptionsPaneBase) optionsTab.getContent();
			if(pane.getStation().equals(station))
			{
				return true;
			}
		}
		return false;
	}
	
	public void selectLastAddedTab()
	{
		tabSelectionModel.select(
				tabPane.getTabs().get(
						tabPane.getTabs().size() - 1));
	}
	
	void addTabFor(OptionsPaneBase newPane)
	{
		Tab newTab = new Tab(newPane.getStation().getName());
		newTab.setOnClosed(e -> onTabClosed());
		newTab.setContent(newPane);
		onAddingTab();
		tabPane.getTabs().add(newTab);
	}
	
	public void addTab(Favourite fav) 
	{
		OptionsPaneFav newPane = new OptionsPaneFav(fav, callbackObj);
		addTabFor(newPane);
	}

	public void addTab(Station station) 
	{
		OptionsPaneNotFav newPane = new OptionsPaneNotFav(station, callbackObj);
		addTabFor(newPane);
	}

	public void selectTabFor(Station station) 
	{
		Tab tab = findTabFor(station);
		tabSelectionModel.select(tab);
	}	
}
