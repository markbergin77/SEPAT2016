package gui;


import data.Station;
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


public class StationButton extends Button 
{
	static double buttonWidth = 150;
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
    	// Text/image alignment
    	setAlignment(Pos.CENTER_LEFT);
    }

	public Station getStation() 
	{
		return station;
	}
}
