package gui.home.explorer;


import javafx.geometry.Pos;
import javafx.scene.control.Button;
import user.Favourite;

public class ButtonFav extends Button
{
	Favourite fav;
	
	static double buttonWidth = 150;

	public ButtonFav(Favourite fav)
	    {
	    	super(fav.getStation().getName() + ", " + fav.getStation().getState());
	    	this.fav = fav;
	    	setMinWidth(buttonWidth);
	    	setMaxWidth(buttonWidth);
	    	// Text/image alignment
	    	setAlignment(Pos.CENTER_LEFT);
	    }

	public Favourite getFav() 
	{
		return fav;
	}
}
