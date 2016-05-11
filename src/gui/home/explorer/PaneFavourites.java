package gui.home.explorer;

import javafx.scene.input.MouseEvent;
import user.Favourite;
import user.FavouritesList;

/* List of all stations in User's favourites list */
public class PaneFavourites extends PaneBase
{
	public interface EventInterface
    {
		void onFavClicked(Favourite fav);
    }
	EventInterface eventHandler;
		
	public PaneFavourites(EventInterface eventHandler)
	{
		this.eventHandler = eventHandler;
	}
	
	// used for when favourites are selected
	public void createFavButtons(FavouritesList favs)
    {
    	for (Favourite fav : favs)
    	{
    		addFavButton(fav);
    	}
    }
	
	void onFavClicked(MouseEvent e)
	{
		ButtonFav button = (ButtonFav)e.getSource();
		eventHandler.onFavClicked(button.getFav());
	}
	
	public void addFavButton(Favourite fav)
	{
		ButtonFav node = new ButtonFav(fav);
		node.setOnMouseClicked(e -> onFavClicked(e));
		addButton(node);
	}	
}
