package gui.plots;

import java.io.IOException;
import java.net.URL;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

import org.apache.log4j.Logger;

import com.sun.javafx.charts.Legend;

import data.Bom;
import data.Fio;
import data.Station;
import data.samples.WthrSampleDaily;
import data.samples.WthrSamplesDaily;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;

public class HistoricalTemp extends PlotBase
{	
	private static Logger logger = Logger.getLogger(HistoricalTemp.class);
	
	private String cssPath;
	static String cssFileName = "HisTempPlot.css";
	WthrSamplesDaily wthrSamplesDaily;
	final CategoryAxis xAxis = new CategoryAxis();
    final NumberAxis yAxis = new NumberAxis();
    LineChart<String,Number> lineChart = new LineChart<String,Number>(xAxis, yAxis);
    
    XYChart.Series<String, Number> seriesTempMin = new XYChart.Series<String, Number>();
    XYChart.Series<String, Number> seriesTempMax = new XYChart.Series<String, Number>();
    XYChart.Series<String, Number> seriesTemp9am = new XYChart.Series<String, Number>();
    XYChart.Series<String, Number> seriesTemp3pm = new XYChart.Series<String, Number>();
    
    YearMonth start = YearMonth.now().minusMonths(12);
    YearMonth end = YearMonth.now().plusMonths(1);
	
	public HistoricalTemp(Station station)
	{
		super(station);
		setName(station.getName() + " Historical Temperatures");
		URL url = this.getClass().getResource(cssFileName);
        cssPath = url.toExternalForm();
        this.addCheckComboBoxOption("Maximum Temperature");
        this.addCheckComboBoxOption("Minimum Temperature");
        this.addCheckComboBoxOption("9am Temperature");
        this.addCheckComboBoxOption("3pm Temperature");
        		
		xAxis.setLabel("Date");
        yAxis.setLabel("Temperature in Degrees");
        lineChart.setTitle(station.getName());
        seriesTempMin.setName("Minimum Temperature");
        seriesTempMax.setName("Maximum Temperature");
        seriesTemp9am.setName("9am Temperature");
        seriesTemp3pm.setName("3pm Temperature");
        
        // Remove markers from line
        lineChart.setCreateSymbols(false);
        lineChart.horizontalGridLinesVisibleProperty().set(false);
        lineChart.verticalGridLinesVisibleProperty().set(false);
        
        lineChart.getData().addAll(seriesTempMin, seriesTempMax, seriesTemp9am, seriesTemp3pm);
        
        // add the lineChart to the gridPane
        assembleFrom(lineChart);
	}
	
	private Series<String, Number> getSeries(String option) {
		switch (option) {
		case "Minimum Temperature":
			return seriesTempMin;
		case "Maximum Temperature":
			return seriesTempMax;
		case "9am Temperature":
			return seriesTemp9am;
		case "3pm Temperature":
			return seriesTemp3pm;
		default:
			return null;
		}
	}
	
	private String getReading(WthrSampleDaily sample, String option) {
		switch (option) {
		case "Minimum Temperature":
			return sample.getMaxTemp();
		case "Maximum Temperature":
			return sample.getMinTemp();
		case "9am Temperature":
			return sample.getTemp9am();
		case "3pm Temperature":
			return sample.getTemp3pm();
		default:
			return null;
		}
	}
	
	private void addAllOptions(ObservableList<String> options) {
		if (wthrSamplesDaily == null) {
			System.out.println("Null");
			return;
		}
		
		clearAllSeries();
		
		for(WthrSampleDaily sample: wthrSamplesDaily) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-LL-yy");
			for (String option: options) {
				String date = sample.getDate().format(formatter);
				String reading = getReading(sample, option);
				if (reading.length() > 0) {
					getSeries(option).getData().add(new Data<String, Number>(date,Float.parseFloat(reading)));
				}
				
			}
		}
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
	
	private void clearAllSeries() {
		seriesTempMin.getData().clear();
		seriesTempMax.getData().clear();
		seriesTemp9am.getData().clear();
		seriesTemp3pm.getData().clear();
	}
	
	@Override 
	public void fetchData(Bom bom, Fio fio)
	{
		try {
			logger.debug("Calling Bom::getWthrRange()");
			wthrSamplesDaily = bom.getWthrRange(station, start, end);
		} catch (IOException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Cannot access BoM JSON server");
			alert.setContentText("Please check your internet connection and try again");

			alert.showAndWait();
			e.printStackTrace();
		}
	}
	
	@Override
	public void plotData(ObservableList<String> options) 
	{
        addAllOptions(options);
	}
	
	@Override
	public String getCssPath()
	{
		return cssPath;
	}
}
