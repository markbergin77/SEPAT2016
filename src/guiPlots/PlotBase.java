package guiPlots;

import data.Station;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.StageStyle;

public class PlotBase extends VBox
{
	Station station; 
	/* So that we can overlay the same things 
	 * on each plot child class */
	ToolBar toolBar;
	String refreshButtonLabel = "Refresh";
	Button refreshButton;
	
	String name = "";
	
	public PlotBase(Station station)
	{
		super();
		this.station = station;
		refreshButton = new Button(refreshButtonLabel);
		refreshButton.setOnMouseClicked(e -> 
		{
			onRefresh();
		});
		
		toolBar = new ToolBar(refreshButton);
	}
	
	protected void setName(String name)
	{
		this.name = name;
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
		getChildren().add(toolBar);
		getChildren().add(node);
		VBox.setVgrow(node, Priority.ALWAYS);
	}
	
	protected void reassembleFrom(Node node)
	{
		getChildren().clear();
		getChildren().add(toolBar);
		getChildren().add(node);
		VBox.setVgrow(node, Priority.ALWAYS);
	}
	
	protected void onRefresh()
	{
		
	}

	public String getName() 
	{
		return name;
	}
}
