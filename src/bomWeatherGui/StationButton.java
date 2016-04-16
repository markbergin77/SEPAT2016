package bomWeatherGui;


import bomData.Station;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.TextAlignment;

/**
 * Created by Pavel Nikolaev on 26/03/2016.
 */
public class StationButton extends Button 
{
	double buttonWidth = 170;
  //  private String LOCATION;
  //  private int TEMP;
  //  private Boolean RAINING, SUNNY;
    private Station station;

    public StationButton(Station station)
    {
    	super(station.getName());
    	this.station = station;
    	setMinWidth(buttonWidth);
    	setMaxWidth(buttonWidth);
    	setAlignment(Pos.CENTER_LEFT);
    }

	public Station getStation() 
	{
		return station;
	}
}
