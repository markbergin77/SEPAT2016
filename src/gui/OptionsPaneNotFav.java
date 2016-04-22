package gui;

import data.Station;
import guiCallbacks.GuiEventInterface;
import javafx.scene.control.Button;

/* Adds options for a station that is 
 * not a favourite */
public class OptionsPaneNotFav extends OptionsPaneBase
{
	String addToFavsMsg = "Add To Favourites";
	Button addToFavsButton = new Button(addToFavsMsg);
	
	public OptionsPaneNotFav(Station station, GuiEventInterface eventHandler) 
	{
		super(station, eventHandler);
		addToFavsButton.setOnMouseClicked(e -> onAddFav());
		addOptionTop(addToFavsButton);
	}
	
	void onAddFav()
	{
		removeOption(addToFavsButton);
		eventHandler.onAddFav(station);
	}

}
