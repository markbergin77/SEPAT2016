package gui.home.options;

import data.Station;
import gui.home.HomeScreen;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import user.Favourite;
/* This goes to the right of the explorer
 * and has buttons to open plots of weather
 * data for the selected station in "Explorer" 
 * 18/4/16: This area has tabs for having
 * multiple stations open at once.
 * */
public class OptionsArea extends GridPane
	implements PaneFav.EventInterface, PaneNotFav.EventInterface
{	
	public OptionsArea(EventInterface callbackObj)
	{
		this.callbackObj = callbackObj;

		setupPrompt();
		addPrompt();
	}
	
	public void addTab(Favourite fav) 
	{
		PaneFav newPane = new PaneFav(fav, this);
		addTabFor(newPane);
	}

	public void addTab(Station station) 
	{
		PaneNotFav newPane = new PaneNotFav(station, this);
		addTabFor(newPane);
	}

	public void selectTabFor(Station station) 
	{
		Tab tab = findTabFor(station);
		tabSelectionModel.select(tab);
	}
	
	public void selectLastAddedTab()
	{
		tabSelectionModel.select(
				tabPane.getTabs().get(
						tabPane.getTabs().size() - 1));
	}

	
	public void closeAllTabs()
	{
		if(tabPane.getTabs().size() != 0)
		{
			tabPane.getTabs().clear();
			removeTabPane();
			addPrompt();
		}
	}
	
	public boolean hasTabFor(Station station)
	{
		
		for(Tab optionsTab : tabPane.getTabs())
		{
			PaneBase pane = (PaneBase) optionsTab.getContent();
			if(pane.getStation().equals(station))
			{
				return true;
			}
		}
		return false;
	}
	
	
	static double defaultWidth = 500;
	/* Looks ugly if right at top */
	static double promptInsetY = 16;
	/* When a button is pressed, send a
	 * message to this object through one 
	 * of the documented interface methods */
	EventInterface callbackObj;
	TabPane tabPane = new TabPane();
	SingleSelectionModel<Tab> tabSelectionModel = tabPane.getSelectionModel();
	/* Text displayed before any station selected */
	String promptText = "Select a station";
	Label promptLabel = new Label(promptText);
	
	VBox promptSpacingBox = new VBox();
	
	void setupPrompt()
	{
        setHgrow(tabPane, Priority.ALWAYS);
        tabPane.setMaxWidth(1300);
		tabPane.getStyleClass().add("tabpane-options");

		promptLabel.setPrefWidth(defaultWidth);
		promptLabel.setAlignment(Pos.BASELINE_CENTER);
		promptLabel.setFont(new Font(24));
		promptSpacingBox.setPrefHeight(promptInsetY);
	}
	
	void onAddingTab()
	{
		if(tabPane.getTabs().size() == 0)
		{
			removePrompt();
			addTabPane();
		}		
	}
		
	void onTabClosed()
	{
		if(tabPane.getTabs().size() == 0)
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
			PaneBase pane = (PaneBase) optionsTab.getContent();
			if(pane.getStation().equals(station))
			{
				return optionsTab;
			}
		}
		return null;
	}
	
	void addTabFor(PaneBase newPane)
	{
		Tab newTab = new Tab(newPane.getStation().getName());
		newTab.setOnClosed(e -> onTabClosed());
		newTab.setContent(newPane);
		onAddingTab();
		tabPane.getTabs().add(newTab);
	}
	
	public interface EventInterface 
	{
		abstract void onOpen72TempPlot(Station station);
		abstract void onOpenHisTempPlot(Station station);
		abstract void onAddFav(Station station);
		abstract void onOpenHisTable (Station station);
		abstract void onOpen72HrTable (Station station);
		abstract void onCloseAllPlots(Station station);
		abstract void onExperimentalPlot(Station station);
	}

	@Override
	public void onOpen72TempPlot(Favourite favourite)
	{
		callbackObj.onOpen72TempPlot(favourite.getStation());
	}

	@Override
	public void onOpenHisTempPlot(Favourite favourite)
	{
		callbackObj.onOpenHisTempPlot(favourite.getStation());
	}

	@Override
	public void onAddFav(Favourite favourite)
	{
		callbackObj.onAddFav(favourite.getStation());
	}

	@Override
	public void onOpenHisTable(Favourite favourite)
	{
		callbackObj.onOpenHisTable(favourite.getStation());
	}

	@Override
	public void onOpen72HrTable(Favourite favourite)
	{
		callbackObj.onOpen72HrTable(favourite.getStation());
	}

	@Override
	public void onCloseAllPlots(Favourite favourite)
	{
		callbackObj.onCloseAllPlots(favourite.getStation());
	}

	@Override
	public void onOpen72TempPlot(Station station)
	{
		callbackObj.onOpen72TempPlot(station);
	}

	@Override
	public void onOpenHisTempPlot(Station station)
	{
		callbackObj.onOpenHisTempPlot(station);
	}

	@Override
	public void onAddFav(Station station)
	{
		callbackObj.onAddFav(station);
	}

	@Override
	public void onOpenHisTable(Station station)
	{
		callbackObj.onOpenHisTable(station);
	}

	@Override
	public void onOpen72HrTable(Station station)
	{
		callbackObj.onOpen72HrTable(station);
	}

	@Override
	public void onCloseAllPlots(Station station)
	{
		callbackObj.onCloseAllPlots(station);
	}
	
	@Override
	public void onExperimentalPlot(Station station)
	{
		callbackObj.onExperimentalPlot(station);
	}
}
