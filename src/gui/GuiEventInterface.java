package gui;

import data.Station;
import javafx.scene.input.MouseEvent;

public interface GuiEventInterface 
{
	abstract void onOpenTempPlot(Station station);
	abstract void onAddFav(Station station);
}
