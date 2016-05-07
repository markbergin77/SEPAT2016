package gui.home.options;

import data.Station;
import user.Favourite;
import user.FavouriteData;

/* Adds buttons/controls specific to Favourites stations */
public class PaneFav extends PaneBase
{
	public interface EventInterface
	{
		abstract void onOpen72TempPlot(Favourite station);
		abstract void onOpenHisTempPlot(Favourite station);
		abstract void onAddFav(Favourite station);
		abstract void onOpenHisTable (Favourite station);
		abstract void onOpen72HrTable (Favourite station);
		abstract void onCloseAllPlots(Favourite station);
	}
	
	EventInterface eventHandler;
	
	FavouriteData favData;
	PaneFav(Favourite fav, EventInterface eventHandler)
	{
		super(fav.getStation());
		this.eventHandler = eventHandler;
		plot72hrButton.setOnMouseClicked(e -> eventHandler.onOpen72TempPlot(fav));
		plotHisButton.setOnMouseClicked(e -> eventHandler.onOpenHisTempPlot(fav));
		table72hrButton.setOnMouseClicked(e -> eventHandler.onOpen72HrTable(fav));
		tableHisButton.setOnMouseClicked(e -> eventHandler.onOpenHisTable(fav));
		closePlotsButton.setOnMouseClicked(e -> eventHandler.onCloseAllPlots(fav));
		favData = fav.getData();
	}
}
