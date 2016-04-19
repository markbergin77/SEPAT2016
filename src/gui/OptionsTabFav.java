package gui;

import javafx.scene.control.Button;
import user.Favourite;

public class OptionsTabFav extends OptionsTabBase
{
	OptionsTabFav(Favourite fav, GuiEventInterface eventHandler)
	{
		super(fav.getStation(), eventHandler);
		
	}
}
