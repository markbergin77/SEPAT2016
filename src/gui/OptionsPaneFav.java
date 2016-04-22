package gui;

import guiCallbacks.GuiEventInterface;
import javafx.scene.control.Button;
import user.Favourite;
import user.FavouriteData;

/* Adds buttons/controls specific to Favourites stations */
public class OptionsPaneFav extends OptionsPaneBase
{
	FavouriteData favData;
	OptionsPaneFav(Favourite fav, GuiEventInterface eventHandler)
	{
		super(fav.getStation(), eventHandler);
		favData = fav.getData();
	}
}
