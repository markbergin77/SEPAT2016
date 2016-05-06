package gui;

import data.Station;
import gui.OptionsPaneFav.EventInterface;
import javafx.scene.control.Button;
import user.Favourite;

/* Adds options for a station that is 
 * not a favourite */
public class OptionsPaneNotFav extends OptionsPaneBase
{
	public interface EventInterface
	{
		abstract void onOpen72TempPlot(Station station);
		abstract void onOpenHisTempPlot(Station station);
		abstract void onAddFav(Station station);
		abstract void onOpenHisTable (Station station);
		abstract void onOpen72HrTable (Station station);
		abstract void onCloseAllPlots(Station station);
	}
	
	EventInterface eventHandler;
	String addToFavsMsg = "Add To Favourites";
	Button addToFavsButton = new Button(addToFavsMsg);
	
	public OptionsPaneNotFav(Station station, EventInterface eventHandler) 
	{
		super(station);
		this.eventHandler = eventHandler;
		addToFavsButton.setOnMouseClicked(e -> eventHandler.onAddFav(station));
		plot72hrButton.setOnMouseClicked(e -> eventHandler.onOpen72TempPlot(station));
		plotHisButton.setOnMouseClicked(e -> eventHandler.onOpenHisTempPlot(station));
		table72hrButton.setOnMouseClicked(e -> eventHandler.onOpen72HrTable(station));
		tableHisButton.setOnMouseClicked(e -> eventHandler.onOpenHisTable(station));
		closePlotsButton.setOnMouseClicked(e -> eventHandler.onCloseAllPlots(station));
		addOptionTop(addToFavsButton);
	}
	
	void onAddFav()
	{
		removeOption(addToFavsButton);
		eventHandler.onAddFav(station);
	}

}
