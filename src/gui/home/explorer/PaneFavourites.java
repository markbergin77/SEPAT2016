package gui.home.explorer;

import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import user.Favourite;
import user.FavouritesList;

/* List of all stations in User's favourites list */
public class PaneFavourites extends PaneBase
{
	public PaneFavourites(EventInterface eventHandler)
	{
		this.eventHandler = eventHandler;
		contextMenu.getItems().add(removeFavItem);
	}
	
	public void addFavButtons(FavouritesList favs)
    {
    	for (Favourite fav : favs)
    	{
    		addFavButton(fav);
    	}
    }
	
	public void addFavButton(Favourite fav)
	{
		ButtonFav node = new ButtonFav(fav);
		node.setOnMouseClicked(e -> onFavClicked(e));
		node.setContextMenu(contextMenu);
		addButton(node);
	}	
	
	public void removeFav(Favourite newFav)
	{
		ButtonFav buttonToRemove = null;
		for(Node node : allButtons)
		{
			ButtonFav favButton = (ButtonFav)node;
			if(favButton.getFav().equals(newFav))
			{
				buttonToRemove = favButton;
			}
		}
		removeButton(buttonToRemove);
	}
	
	void onFavClicked(MouseEvent e)
	{
		ButtonFav button = (ButtonFav)e.getSource();
		if (e.getButton() == MouseButton.PRIMARY)
			eventHandler.onFavClicked(button.getFav());
		else if (e.getButton() == MouseButton.SECONDARY)
		{
			removeFavItem.setOnAction(a -> {
				eventHandler.onFavRemove(button.getFav());
			});
			contextMenu.show(button, Side.BOTTOM, 0, 0);
		}
	}
	
	EventInterface eventHandler;
	ContextMenu contextMenu = new ContextMenu();
	String removeFavLabel = "Remove";
	MenuItem removeFavItem = new MenuItem(removeFavLabel);
	
	public interface EventInterface
    {
		abstract void onFavClicked(Favourite fav);
		abstract void onFavRemove(Favourite fav);
    }
}
