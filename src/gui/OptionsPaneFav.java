package gui;

import javafx.scene.control.Button;
import user.Favourite;
import user.FavouriteData;

public class OptionsPaneFav extends OptionsPaneBase
{
	FavouriteData favData;
	OptionsPaneFav(Favourite fav, GuiEventInterface eventHandler)
	{
		super(fav.getStation(), eventHandler);
		favData = fav.getData();
	}
}
