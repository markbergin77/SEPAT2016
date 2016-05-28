package gui.home.explorer;


import data.Station;
import javafx.geometry.Pos;
import javafx.scene.control.Button;


public class ButtonNotFav extends Button 
{
	static double buttonWidth = 260;
	static double buttonHeight = 45;
  //  private String LOCATION;
  //  private int TEMP;
  //  private Boolean RAINING, SUNNY;
    private Station station;

    public ButtonNotFav(Station station)
    {
    	super(station.getName() + ", " + station.getState());
    	this.station = station;
        setMinWidth(buttonWidth);
        setMaxWidth(buttonWidth+300);
        setMaxHeight(buttonHeight);
        setMinHeight(buttonHeight);
    	// Text/image alignment
    	setAlignment(Pos.CENTER_LEFT);
        setOnMouseEntered(e -> getStyleClass().add("button-hover"));
        setOnMouseExited(e -> getStyleClass().remove("button-hover"));
    }
    
	public Station getStation() 
	{
		return station;
	}
}
