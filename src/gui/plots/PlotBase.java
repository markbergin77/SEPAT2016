package gui.plots;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Vector;

import data.Bom;
import data.Station;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class PlotBase extends GridPane
{
	public PlotBase(Station station)
	{
		super();
		this.station = station;
		construct();
	}
	
	public void setEventHandler(EventInterface handler)
	{
		this.eventHandler = handler;
		refreshButton.setOnMouseClicked(e -> 
		{
			eventHandler.onRefresh(this);
		});

        refreshButton.setOnMouseEntered(e -> refreshButton.getStyleClass().add("button-hover"));
        refreshButton.setOnMouseExited(e -> refreshButton.getStyleClass().remove("button-hover"));
	}
	
	public void fetchNewData(Bom bom)
	{
		
	}
	
	public void plotLatestData()
	{
		
	}
	
	protected void setName(String name)
	{
		this.name = name;
	}
	
	protected void setChartTitle(String string)
	{
		
	}
	
	protected void setXLabel(String label)
	{
		xAxis.setLabel(label);
	}
	
	protected void setYLabel(String label)
	{
		yAxis.setLabel(label);
	}
	
	public String getCssPath()
	{
		return null;
	}
	
	public interface EventInterface
	{
		public void onRefresh(PlotBase plot);
	}
	EventInterface eventHandler = voidHandler;
	
	static String refreshButtonLabel = "Refresh";
	Button refreshButton;
	HBox toolBar;
	private Station station;
	StackPane stackPane;
	private CategoryAxis xAxis;
    private NumberAxis yAxis;
    LineChart<String,Number> lineChart;
	//private Vector<XYChart.Series<String, Number>> series;
	
	String name = "";
	
	void construct()
	{
		eventHandler = voidHandler;
		refreshButton = new Button(refreshButtonLabel);;
		toolBar = new HBox(refreshButton);
		stackPane = new StackPane();
		xAxis = new CategoryAxis();
	    yAxis = new NumberAxis();
	    lineChart = new LineChart<String,Number>(xAxis, yAxis);
		//series = new Vector<XYChart.Series<String, Number>>();
		lineChart.setCreateSymbols(false);
		name = "";
		
		getChildren().add(stackPane);
		stackPane.getChildren().add(lineChart);
		stackPane.getChildren().add(toolBar);
		StackPane.setAlignment(toolBar, Pos.TOP_LEFT);
		Rectangle clipRect = new Rectangle(500,300);
	}
	
	protected Station getStation()
	{
		return station;
	}
	
	protected void addSeries(XYChart.Series<String, Number>... series)
	{
		lineChart.getData().addAll(series);
	}
	
	protected void addSeries(XYChart.Series<String, Number> series)
	{
		lineChart.getData().add(series);
	}

	protected void onRefresh()
	{
		eventHandler.onRefresh(this);
	}

	public String getName() 
	{
		return name;
	}
	
	private static class VoidEventHandler implements EventInterface
	{
		@Override
		public void onRefresh(PlotBase plot)
		{
			// TODO Auto-generated method stub
			
		}
	}
	private static VoidEventHandler voidHandler = new VoidEventHandler();
}
