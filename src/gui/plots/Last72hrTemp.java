package gui.plots;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import data.Bom;
import data.Fio;
import data.Station;
import data.samples.FioSampleDaily;
import data.samples.FioSampleFine;
import data.samples.FioSamplesDaily;
import data.samples.FioSamplesFine;
import data.samples.WthrSampleDaily;
import data.samples.WthrSampleFine;
import data.samples.WthrSamplesFine;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class Last72hrTemp extends PlotBase
{
	private String cssPath;
	static String cssFileName = "CurrTempPlot.css";
	WthrSamplesFine wthrSamplesFine;
	FioSamplesFine fioSamplesFine;
	final CategoryAxis xAxis = new CategoryAxis();
    final NumberAxis yAxis = new NumberAxis();
    LineChart<String,Number> lineChart = new LineChart<String,Number>(xAxis, yAxis);
	private static Logger logger = Logger.getLogger(ExperimentalPlot.class);

    
    XYChart.Series<String, Number> seriesAirTemp = new XYChart.Series<String, Number>();
    XYChart.Series<String, Number> seriesAirTempForecast = new XYChart.Series<String, Number>();
    
    
	public Last72hrTemp(Station station) 
	{
		super(station);
		setName(station.getName() + " 72 Hours Temperatures");
		URL url = this.getClass().getResource(cssFileName);
        cssPath = url.toExternalForm();
        
        this.addCheckComboBoxOption("Historic Temperature");
        this.addCheckComboBoxOption("Forecast Temperature");
		
		xAxis.setLabel("Date/Time");
        yAxis.setLabel("Temperature in Degrees");
        lineChart.setTitle(station.getName());
        seriesAirTemp.setName("Temperature");
        seriesAirTempForecast.setName("Forecast Temperature");
        
        // Remove markers from line
        lineChart.setCreateSymbols(false);
        lineChart.getData().addAll(seriesAirTemp, seriesAirTempForecast);

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
	
	private FioSamplesFine getFioData(Fio fio, String lat, String lon) {

		logger.debug("Starting ExperimentalPlot::getFioData");
		FioSamplesFine samples = new FioSamplesFine();
		try {
			samples = fio.getFioFine(lat, lon);
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
	
	private Series<String, Number> getSeries(String option) {
		switch (option) {
		case "Historic Temperature":
			return seriesAirTemp;
		case "Forecast Temperature":
			return seriesAirTempForecast;
		default:
			return null;
		}
	}
	
	private String getHistoricReading(WthrSampleFine sample, String option) {
		switch (option) {
		case "Historic Temperature":
			return sample.getAirTemp();
		default:
			return null;
		}
	}
	
	private String getForecastReading(FioSampleFine sample, String option) {
		switch (option) {
		case "Temperature Forecast":
			return sample.getAirTemp();
		default:
			return null;
		}
	}
	
	private Series<String, Number> getMatchingSeries(String forecastOption) {
		switch (forecastOption) {
		case "Minimum Temperature Forecast":
			return seriesAirTemp;
		default:
			return null;
		}
	}
	
	private void clearAllSeries() {
        seriesAirTemp.getData().clear();
        seriesAirTempForecast.getData().clear();
	}
	
	private void addAllOptions(ObservableList<String> options) {
		 WthrSampleFine sample = null;
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE-HH:mm");
		 
		 ArrayList<String> forecastOptions = new ArrayList<String>();
			ArrayList<String> historicOptions = new ArrayList<String>();
			
			for (String option: options) {
				if (option.contains("Forecast"))
					forecastOptions.add(option);
				else
					historicOptions.add(option);
			}
		 
		for (String option: historicOptions) {
			int samplesSize = wthrSamplesFine.size();
			for(int i = samplesSize - 1; i > -1; i--) {
				sample = wthrSamplesFine.get(i);
				String date = sample.getLocalDateTime().format(formatter);
	        	String reading = getHistoricReading(sample, option);
	        	
	        	if (reading.length() > 0)
	        		getSeries(option).getData().add(new Data<String, Number>(date,Float.parseFloat(reading)));
			}
		}
	}
	
	@Override
	public void fetchData(Bom bom, Fio fio)
	{
		try {
			wthrSamplesFine = bom.getWthrLast72hr(station);
			String lat = wthrSamplesFine.get(0).getLat();
			String lon = wthrSamplesFine.get(0).getLon();
			fioSamplesFine = getFioData(fio, lat, lon);
		} catch (JsonIOException | JsonSyntaxException | IOException e) {
			gui.Alert alert = new gui.Alert("Error","Cannot access BoM server, check your connection",
					event -> System.exit(0));
		}
	}
	
	@Override
	public void plotData(ObservableList<String> options) 
	{
		clearAllSeries();
        addAllOptions(options);
	}
}
