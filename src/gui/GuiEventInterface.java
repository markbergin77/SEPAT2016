package gui;

import data.Station;

public interface GuiEventInterface 
{
	abstract void onOpen72TempPlot(Station station);
	abstract void onAddFav(Station station);
}
