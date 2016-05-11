package gui.plots;

import data.Station;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.StageStyle;

public class PlotBase extends VBox
{
	public interface EventInterface
	{
		public void onRefresh(PlotBase plot);
		public void onGoHome();
	}
	EventInterface eventHandler = voidHandler;
	
	Station station; 
	/* So that we can overlay the same things 
	 * on each plot child class */
	String refreshButtonLabel = "Refresh";
	Button refreshButton = new Button(refreshButtonLabel);;
	String homeButtonLabel = "Home";
	Button homeButton = new Button(homeButtonLabel);
	ToolBar toolBar = new ToolBar(refreshButton, homeButton);
	
	String name = "";
	
	public PlotBase(Station station)
	{
		super();
		this.station = station;		
	}
	
	public void setEventHandler(EventInterface handler)
	{
		this.eventHandler = handler;
		refreshButton.setOnMouseClicked(e -> 
		{
			eventHandler.onRefresh(this);
		});
		homeButton.setOnMouseClicked(e -> 
		{
			eventHandler.onGoHome();
		});
	}
	
	public void plotData()
	{
		
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

	public void fetchData()
	{
		
	}
	
	private static class VoidEventHandler implements EventInterface
	{
		@Override
		public void onRefresh(PlotBase plot)
		{
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onGoHome()
		{
			// TODO Auto-generated method stub
			
		}
	}
	private static VoidEventHandler voidHandler = new VoidEventHandler();
}
