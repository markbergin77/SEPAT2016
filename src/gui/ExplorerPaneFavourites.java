package gui;

import java.util.Vector;

import guiCallbacks.FavClicked;
import javafx.scene.input.MouseEvent;
import user.Favourite;
import user.FavouritesList;

/* List of all stations in User's favourites list */
public class ExplorerPaneFavourites extends ExplorerPaneBase
{
	FavClicked clickHandler;
		
	// used for when favourites are selected
	public void createFavButtons(FavouritesList favs, 
    		FavClicked clickHandler)
    {
    	this.clickHandler = clickHandler;
    	for (Favourite fav : favs)
    	{
    		addFavourite(fav);
    	}
    }
	
	void onFavClicked(MouseEvent e)
	{
		ExplorerButtonFav button = (ExplorerButtonFav)e.getSource();
		clickHandler.favClicked(button.getFav());
	}
	
	public void addFavourite(Favourite fav)
	{
		ExplorerButtonFav node = new ExplorerButtonFav(fav);
		node.setOnMouseClicked(e -> onFavClicked(e));
		getVBox().getChildren().add(node);
        node.toFront();
	}
}
