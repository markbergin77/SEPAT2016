package guiPlots;

import data.Station;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

public class PlotBase extends GridPane
{
	Station station; 
	/* So that we can overlay the same things 
	 * on each plot child class */
	StackPane plotStack;
	String refreshButtonLabel = "Refresh";
	Button refreshButton;
	
	public PlotBase(Station station)
	{
		super();
		plotStack = new StackPane();
		this.station = station;
		refreshButton = new Button(refreshButtonLabel);
		refreshButton.setOnMouseClicked(e -> 
		{
			onRefresh();
		});
		add(plotStack, 0, 0);
	}
	
	public String getCssPath()
	{
		return null;
	}
	
	public Station getStation()
	{
		return station;
	}
	
	protected void assembleFrom(Node node)
	{
		plotStack.getChildren().add(node);
		plotStack.getChildren().add(refreshButton);
		StackPane.setAlignment(refreshButton, Pos.BOTTOM_RIGHT);
	}
	
	protected void reassembleFrom(Node node)
	{
		plotStack.getChildren().clear();
		plotStack.getChildren().add(node);
		plotStack.getChildren().add(refreshButton);
		StackPane.setAlignment(refreshButton, Pos.BOTTOM_RIGHT);
	}
	
	protected void onRefresh()
	{
		
	}
}
