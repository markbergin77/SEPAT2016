package gui.plots;

import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import data.Bom;
import data.Station;
import data.samples.WthrSampleFine;
import data.samples.WthrSamplesFine;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class Last72hrTemp extends PlotBase
{
	private String cssPath;
	static String cssFileName = "CurrTempPlot.css";
	WthrSamplesFine wthrSamplesFine;
	XYChart.Series<String, Number> seriesAirTemp;
	Station station;
        
	public Last72hrTemp(Station station) 
	{
		super();
		this.station = station;
		setName(station.getName() + " 72 Hours Temperatures");
        seriesAirTemp = new XYChart.Series<String, Number>();
		setXLabel("Date/Time");
        setYLabel("Temperature in Degrees");
        setChartTitle(station.getName());
        seriesAirTemp.setName("Air Temperature");
        addSeries(seriesAirTemp);
        
        URL url = this.getClass().getResource(cssFileName);
        cssPath = url.toExternalForm();
        
        // Remove markers from line
        

		StackPane plotContainer = new StackPane();
		Rectangle clipRect = new Rectangle(500,300);
		plotContainer.setClip(clipRect);
		//plotContainer.getChildren().add(lineChart);

		plotContainer.setMaxSize(1200,750);
		plotContainer.setMinSize(400, 250);

        //plot(station, seriesAirTemp);
        
        // add the lineChart to the gridPane
	}
	
	@Override
	public void refresh(Bom bom)
	{
		seriesAirTemp.getData().clear();
        try {
			wthrSamplesFine = bom.getWthrLast72hr(station);
		} catch (JsonIOException | JsonSyntaxException | IOException e) {
			gui.Alert alert = new gui.Alert(
					"Error","Cannot access BoM server, check your connection",
					event -> System.exit(0));
		}
        addToSeries(wthrSamplesFine, seriesAirTemp);
	}
	
	public void setData(WthrSamplesFine data)
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
}
