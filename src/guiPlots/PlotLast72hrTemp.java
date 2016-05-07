package guiPlots;

import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import data.Bom;
import data.Station;
import data.WthrSampleFine;
import data.WthrSamplesDaily;
import data.WthrSamplesFine;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class PlotLast72hrTemp extends PlotBase
{
	private String cssPath;
	static String cssFileName = "CurrTempPlot.css";
	WthrSamplesFine wthrSamplesFine;
	final CategoryAxis xAxis = new CategoryAxis();
    final NumberAxis yAxis = new NumberAxis();
    LineChart<String,Number> lineChart = new LineChart<String,Number>(xAxis, yAxis);
    
    XYChart.Series<String, Number> seriesAirTemp = new XYChart.Series<String, Number>();
    
	public PlotLast72hrTemp(Station station) 
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
        //plot(station, seriesAirTemp);
        
        // add the lineChart to the gridPane
        assembleFrom(lineChart);
	}
	
	@Override 
	public String getCssPath()
	{
		return cssPath;
	}
	
	private WthrSamplesFine getData(Station station) {
        WthrSamplesFine wthrSamplesFine = new WthrSamplesFine();
        try {
			wthrSamplesFine = Bom.getWthrLast72hr(station);
			return wthrSamplesFine;
		} catch (JsonIOException | JsonSyntaxException | IOException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Cannot access BoM JSON server");
			alert.setContentText("Please check your internet connection and try again");

			alert.showAndWait();
			return null;
		}
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
	public void fetchData()
	{
		wthrSamplesFine = getData(station);
	}
	
	@Override
	public void plotData() 
	{
        addToSeries(wthrSamplesFine, seriesAirTemp);
	}
	
	@Override
	protected void onRefresh() 
	{
		seriesAirTemp.getData().clear();
		fetchData();
		plotData();
	}
}
