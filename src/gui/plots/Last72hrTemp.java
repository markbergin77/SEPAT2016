package gui.plots;

import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import data.Bom;
import data.Fio;
import data.Station;
import data.samples.WthrSampleFine;
import data.samples.WthrSamplesFine;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class Last72hrTemp extends PlotBase
{
	private String cssPath;
	static String cssFileName = "CurrTempPlot.css";
	WthrSamplesFine wthrSamplesFine;
	final CategoryAxis xAxis = new CategoryAxis();
    final NumberAxis yAxis = new NumberAxis();
    LineChart<String,Number> lineChart = new LineChart<String,Number>(xAxis, yAxis);
    
    XYChart.Series<String, Number> seriesAirTemp = new XYChart.Series<String, Number>();
    
	public Last72hrTemp(Station station) 
	{
		super(station);
		setName(station.getName() + " 72 Hours Temperatures");
		URL url = this.getClass().getResource(cssFileName);
        cssPath = url.toExternalForm();
		
		xAxis.setLabel("Date/Time");
        yAxis.setLabel("Temperature in Degrees");
        lineChart.setTitle(station.getName());
        seriesAirTemp.setName("Air Temperature");
        
        // Remove markers from line
        lineChart.setCreateSymbols(false);
        lineChart.getData().add(seriesAirTemp);

		StackPane plotContainer = new StackPane();
		Rectangle clipRect = new Rectangle(1000,467);
		Rectangle backGroundRect = new Rectangle(1000,467,Color.rgb(42, 40, 41));
     	plotContainer.setClip(clipRect);
		plotContainer.getChildren().addAll(backGroundRect,lineChart);

		final double SCALE_DELTA = 1.1;
		lineChart.setOnScroll(new EventHandler<ScrollEvent>() {
			public void handle(ScrollEvent event) {
				event.consume();

				if (event.getDeltaY() == 0) {
					return;
				}

				double scaleFactor = (event.getDeltaY() > 0) ? SCALE_DELTA : 1 / SCALE_DELTA;

				lineChart.setScaleX(lineChart.getScaleX() * scaleFactor);
				lineChart.setScaleY(lineChart.getScaleY() * scaleFactor);
			}
		});

		plotContainer.setMaxSize(1000,467);
		plotContainer.setMinSize(400,305);

        //plot(station, seriesAirTemp);
        
        // add the lineChart to the gridPane

		assembleFrom(plotContainer);
	}
	
	@Override
	public void resize(int x, int y)
	{
		
	}

	@Override
	public void changeWidth(int x)
	{
		
	}

	@Override
	public void changeHeight(int x)
	{
		
	}
	
	@Override 
	public String getCssPath()
	{
		return cssPath;
	}
	
	private void addToSeries(WthrSamplesFine samples, XYChart.Series<String, Number> series)
	{
		int samplesSize = samples.size();
        WthrSampleFine sample = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE-HH:mm");
        for(int i = samplesSize - 1; i > -1; i--) {
        	sample = samples.get(i);
        	String date = sample.getLocalDateTime().format(formatter);
        	String airTemp = sample.getAirTemp();
        	
        	// Check if the string is null or blank
        	if (airTemp.length() > 0)
        		series.getData().add(new Data<String, Number>(date,Float.parseFloat(airTemp)));
        }
	}
	
	@Override
	public void fetchData(Bom bom, Fio fio)
	{
		try {
			wthrSamplesFine = bom.getWthrLast72hr(station);
		} catch (JsonIOException | JsonSyntaxException | IOException e) {
			gui.Alert alert = new gui.Alert("Error","Cannot access BoM server, check your connection",
					event -> System.exit(0));
		}
	}
	
	@Override
	public void plotData(ObservableList<String> options) 
	{
        addToSeries(wthrSamplesFine, seriesAirTemp);
	}
}
