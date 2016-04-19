package gui;

import data.Station;
import javafx.scene.control.Button;

/* Options tab for a station that is 
 * not a favourite */
public class OptionsTabNotFav extends OptionsTabBase
{
	String addToFavsMsg = "Add To Favourites";
	Button addToFavsButton = new Button(addToFavsMsg);
	
	public OptionsTabNotFav(Station station, GuiEventInterface eventHandler) 
	{
		super(station, eventHandler);
		addToFavsButton.setOnMouseClicked(e -> eventHandler.onAddFav(station));
		addOptionTop(addToFavsButton);
	}

}
