package gui;

import data.Station;

public interface GuiEventInterface 
{
	abstract void onOpenTempPlot(Station station);
	abstract void onAddFav(Station station);
}
