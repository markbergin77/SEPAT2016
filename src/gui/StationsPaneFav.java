package gui;

import guiCallbacks.FavClicked;
import javafx.scene.input.MouseEvent;
import user.Favourite;
import user.FavouritesList;

public class StationsPaneFav extends StationsPane
{
	FavClicked clickHandler;
	
	public void createFavButtons(FavouritesList favs, 
    		FavClicked clickHandler)
    {
    	this.clickHandler = clickHandler;
    	for (Favourite fav : favs)
    	{
    		FavButton node = new FavButton(fav);
    		node.setOnMouseClicked(e -> onFavClicked(e));
    		getVBox().getChildren().add(node);
            node.toFront();
    	}
    }
	
	void onFavClicked(MouseEvent e)
	{
		FavButton button = (FavButton)e.getSource();
		clickHandler.favClicked(button.getFav());
	}
}
