package gui;

import javafx.scene.control.Button;
import user.Favourite;

public class OptionsPaneFav extends OptionsPaneBase
{
	OptionsPaneFav(Favourite fav, GuiEventInterface eventHandler)
	{
		super(fav.getStation(), eventHandler);
	}
}
