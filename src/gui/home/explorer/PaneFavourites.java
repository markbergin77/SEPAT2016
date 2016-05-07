package gui.home.explorer;

import guiCallbacks.FavClicked;
import javafx.scene.input.MouseEvent;
import user.Favourite;
import user.FavouritesList;

/* List of all stations in User's favourites list */
public class PaneFavourites extends PaneBase
{
	FavClicked clickHandler;
		
	// used for when favourites are selected
	public void createFavButtons(FavouritesList favs, 
    		FavClicked clickHandler)
    {
    	this.clickHandler = clickHandler;
    	for (Favourite fav : favs)
    	{
    		addFavButton(fav);
    	}
    }
	
	void onFavClicked(MouseEvent e)
	{
		ButtonFav button = (ButtonFav)e.getSource();
		clickHandler.favClicked(button.getFav());
	}
	
	public void addFavButton(Favourite fav)
	{
		ButtonFav node = new ButtonFav(fav);
		node.setOnMouseClicked(e -> onFavClicked(e));
		addButton(node);
	}

	
}
