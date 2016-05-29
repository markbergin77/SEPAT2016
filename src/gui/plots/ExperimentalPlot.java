package gui.plots;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.sun.javafx.charts.Legend;

import data.Bom;
import data.Fio;
import data.Station;
import data.samples.FioSampleDaily;
import data.samples.FioSamplesDaily;
import data.samples.WthrSampleDaily;
import data.samples.WthrSamplesDaily;
import data.samples.WthrSamplesFine;
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

public class ExperimentalPlot extends PlotBase
{
	private String cssPath;
	static String cssFileName = "HisTempPlot.css";
	WthrSamplesDaily wthrSamplesDaily;
	FioSamplesDaily fioSamplesDaily;
	WthrSamplesFine wthrSamplesFine;
	final CategoryAxis xAxis = new CategoryAxis();
    final NumberAxis yAxis = new NumberAxis();
    LineChart<String,Number> lineChart = new LineChart<String,Number>(xAxis, yAxis);
	private static Logger logger = Logger.getLogger(ExperimentalPlot.class);

	XYChart.Series<String, Number> seriesTempMin = new XYChart.Series<String, Number>();
    XYChart.Series<String, Number> seriesTempMax = new XYChart.Series<String, Number>();
    XYChart.Series<String, Number> seriesTempMinForecast = new XYChart.Series<String, Number>();
    XYChart.Series<String, Number> seriesTempMaxForecast = new XYChart.Series<String, Number>();
    
    YearMonth start = YearMonth.now().minusMonths(1);
    YearMonth end = YearMonth.now().plusMonths(1);
	
	public ExperimentalPlot(Station station)
	{
		super(station);
		logger.debug("Creating ExperimentalPlot");
        this.addCheckComboBoxOption("Maximum Temperature");
        this.addCheckComboBoxOption("Minimum Temperature");
        this.addCheckComboBoxOption("Minimum Temperature Forecast");
        this.addCheckComboBoxOption("Maximum Temperature Forecast");

		setName(station.getName() + " Past and Present Temperatures");
		URL url = this.getClass().getResource(cssFileName);
        cssPath = url.toExternalForm();
		
		xAxis.setLabel("Date");
        yAxis.setLabel("Temperature in Degrees");
        lineChart.setTitle(station.getName());
        seriesTempMin.setName("Minimum Temperature");
        seriesTempMax.setName("Maximum Temperature");
        seriesTempMinForecast.setName("Minimum Temperature Forecast");
        seriesTempMaxForecast.setName("Maximum Temperature Forecast");
        
        // Remove markers from line
        lineChart.setCreateSymbols(false);
        lineChart.horizontalGridLinesVisibleProperty().set(false);
        lineChart.verticalGridLinesVisibleProperty().set(false);

        //plot();
        lineChart.getData().addAll(seriesTempMin, seriesTempMax, seriesTempMinForecast, seriesTempMaxForecast);
     
        // add the lineChart to the gridPane
        logger.debug("Calling ExperimentalPlot::assembleFrom()");
        assembleFrom(lineChart);
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
	
	private Series<String, Number> getSeries(String option) {
		switch (option) {
		case "Minimum Temperature":
			return seriesTempMin;
		case "Maximum Temperature":
			return seriesTempMax;
		case "Minimum Temperature Forecast":
			return seriesTempMinForecast;
		case "Maximum Temperature Forecast":
			return seriesTempMaxForecast;
		default:
			return null;
		}
	}
	
	private Series<String, Number> getMatchingSeries(String forecastOption) {
		switch (forecastOption) {
		case "Minimum Temperature Forecast":
			return seriesTempMin;
		case "Maximum Temperature Forecast":
			return seriesTempMax;
		default:
			return null;
		}
	}
	
	private String getHistoricReading(WthrSampleDaily sample, String option) {
		switch (option) {
		case "Minimum Temperature":
			return sample.getMaxTemp();
		case "Maximum Temperature":
			return sample.getMinTemp();
		default:
			return null;
		}
	}
	
	private String getForecastReading(FioSampleDaily sample, String option) {
		switch (option) {
		case "Minimum Temperature Forecast":
			return sample.getTempMax();
		case "Maximum Temperature Forecast":
			return sample.getTempMin();
		default:
			return null;
		}
	}
	
	private WthrSamplesDaily getBomData(Bom bom, Station station, YearMonth start, YearMonth end) {
		logger.debug("Starting ExperimentalPlot::getBomData");

		WthrSamplesDaily wthrSamplesDaily = new WthrSamplesDaily();
        try {
			wthrSamplesDaily = bom.getWthrRange(station, start, end);
			return wthrSamplesDaily;

		} catch (IOException e) {

            logger.error("Failed to connect/retrieve weather samples from Bom",e);
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Cannot access BoM JSON server");
			alert.setContentText("Please check your internet connection and try again");
			alert.showAndWait();
			return null;

		}
	}
	
	private FioSamplesDaily getFioData(Fio fio, String lat, String lon) {

		logger.debug("Starting ExperimentalPlot::getFioData");
		FioSamplesDaily samples = new FioSamplesDaily();
		try {
			samples = fio.getFioDaily(lat, lon);
		} catch (JsonIOException e) {
            logger.error("Failed to connect/retrieve weather samples from FIO",e);
		} catch (JsonSyntaxException e) {
            logger.error("Failed to connect/retrieve weather samples from FIO",e);

		} catch (MalformedURLException e) {
            logger.error("Failed to connect/retrieve weather samples from FIO",e);

		} catch (IOException e) {
            logger.error("Failed to connect/retrieve weather samples from FIO",e);
		}
		return samples;
	}
	
	private void addToAllSeries(WthrSamplesDaily wthrSamplesDaily, FioSamplesDaily fioSamplesDaily, ObservableList<String> options) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-LL-yy");
		
		ArrayList<String> forecastOptions = new ArrayList<String>();
		ArrayList<String> historicOptions = new ArrayList<String>();
		
		for (String option: options) {
			if (option.contains("Forecast"))
				forecastOptions.add(option);
			else
				historicOptions.add(option);
		}
		
		for (String option: historicOptions) {
			System.out.println(option);
			for (WthrSampleDaily sample: wthrSamplesDaily) {
				String date = sample.getDate().format(formatter);
				String reading = getHistoricReading(sample, option);
				System.out.println(option);
				if (reading != null && reading.length() > 0) {
					getSeries(option).getData().add(new Data<String, Number>(date,Float.parseFloat(reading)));
				}
					
			}
		}
		
		for (String option: forecastOptions) {
			for(FioSampleDaily sample: fioSamplesDaily) {
				LocalDateTime dateTime = sample.getDate();
				LocalDate date = dateTime.toLocalDate();
				String dateString = date.format(formatter);
				
				LocalDate lastReadingTime = LocalDate.MIN;
				int matchingSeriesMinSize = getMatchingSeries(option).getData().size();
				if (matchingSeriesMinSize > 0) {
					String lastString = getMatchingSeries(option).getData().get(matchingSeriesMinSize - 1).getXValue();
					lastReadingTime = LocalDate.parse(lastString, formatter);
				}
				
				if (date.isAfter(lastReadingTime)) {
					String reading = getForecastReading(sample, option);
					getSeries(option).getData().add(new Data<String, Number>(dateString,Float.parseFloat(reading)));
					
				}
			}
		}
	}
	
	private void clearAllSeries() {
		seriesTempMin.getData().clear();
		seriesTempMax.getData().clear();
		seriesTempMinForecast.getData().clear();
		seriesTempMaxForecast.getData().clear();
	}
	
	@Override 
	public void fetchData(Bom bom, Fio fio)
	{
		logger.debug("Calling ExperimentalPlot::getBomData");
		wthrSamplesDaily = getBomData(bom, station, start, end);

		logger.debug("Calling ExperimentalPlot::getFioData");
		try {
			wthrSamplesFine = bom.getWthrLast72hr(station);
		} catch (JsonIOException | JsonSyntaxException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String lat = wthrSamplesFine.get(0).getLat();
		String lon = wthrSamplesFine.get(0).getLon();
		fioSamplesDaily = getFioData(fio, lat, lon);
	}
	
	@Override
	public void plotData(ObservableList<String> options) 
	{
		clearAllSeries();
        addToAllSeries(wthrSamplesDaily, fioSamplesDaily, options);
	}
}
