package guiPlots;

import data.Station;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.Background;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;

public class PlotBase extends GridPane
{
	Station station; 
	/* So that we can overlay the same things 
	 * on each plot child class */
	StackPane plotStack;
	ToolBar toolBar;
	String refreshButtonLabel = "↻";
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
		
		toolBar = new ToolBar(refreshButton);
		
		// Allow children to resize vertically
        ColumnConstraints columnConstraints = new ColumnConstraints();
        columnConstraints.setHgrow(Priority.ALWAYS);
        this.getColumnConstraints().add(columnConstraints);
        
        // Allow children to resize horizontally
        RowConstraints rowConstraints = new RowConstraints();
        rowConstraints.setVgrow(Priority.ALWAYS);
        this.getRowConstraints().add(rowConstraints);
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
		add(toolBar, 0, 0);
		add(node, 0, 1);
	}
	
	protected void reassembleFrom(Node node)
	{
		plotStack.getChildren().clear();
		add(toolBar, 0, 0);
		add(node, 0, 1);
	}
	
	protected void onRefresh()
	{
		
	}
}
