package gui.plots;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import org.apache.log4j.Logger;

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
        // Hacky solution, add a css class to each line
        String[] lineClasses = {"tempMin", "tempMax", "tempMinForecast", "tempMaxForecast"};
        seriesTempMin.getNode().getStyleClass().add(lineClasses[0]);
        seriesTempMax.getNode().getStyleClass().add(lineClasses[1]);
        seriesTempMinForecast.getNode().getStyleClass().add(lineClasses[2]);
        seriesTempMaxForecast.getNode().getStyleClass().add(lineClasses[3]);
        
        // Get access to the legend
        Legend legend = (Legend)lineChart.lookup(".chart-legend");
        ObservableList<Node> legendChildren = legend.getChildren();
        
        // Add a click listener to each legend item
        for (int i = 0; i < lineClasses.length; i++) {
        	Object legendChild = legendChildren.get(i);
        	// Need the "." as class specifier, e.g. .class
        	Node line = lineChart.lookup("." + lineClasses[i]);
        	((Node) legendChild).setOnMouseClicked(new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent e) {
                	if (line.isVisible()) {
                		line.setVisible(false);
                	}
                	else {
                		line.setVisible(true);
                	}
                }
            });
        }
        
        // add the lineChart to the gridPane
        logger.debug("Calling ExperimentalPlot::assembleFrom()");
        assembleFrom(lineChart);
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
	
	private FioSamplesDaily getFioData(Bom bom, Fio fio, String lat, String lon) {

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
	
	private void addToAllSeries(WthrSamplesDaily wthrSamplesDaily, FioSamplesDaily fioSamplesDaily) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-LL-yy");
		for(WthrSampleDaily sample: wthrSamplesDaily) {		
        	String date = sample.getDate().format(formatter);
        	String tempMax = sample.getMaxTemp();
        	String tempMin = sample.getMinTemp();
        	
        	// Check if the string is null or blank, due to FILE NOT FOUND
        	if (tempMin.length() > 0)
        		seriesTempMin.getData().add(new Data<String, Number>(date,Float.parseFloat(tempMin)));
        	if (tempMax.length() > 0)
        		seriesTempMax.getData().add(new XYChart.Data<String, Number>(date,Float.parseFloat(tempMax)));
        }
	
		for(FioSampleDaily sample: fioSamplesDaily) {
			LocalDateTime dateTime = sample.getDate();
			LocalDate date = dateTime.toLocalDate();
			String dateString = date.format(formatter);
			
			LocalDate lastMin = LocalDate.MIN;
			int seriesMinSize = seriesTempMin.getData().size();
			if (seriesMinSize > 0) {
				String lastMinString = seriesTempMin.getData().get(seriesMinSize - 1).getXValue();
				lastMin = LocalDate.parse(lastMinString, formatter);
			}
			
			LocalDate lastMax = LocalDate.MAX;
			int seriesMaxSize = seriesTempMax.getData().size();
			if (seriesMaxSize > 0) {
				String lastMaxString = seriesTempMax.getData().get(seriesMaxSize - 1).getXValue();
				lastMax = LocalDate.parse(lastMaxString, formatter);
			}
			
			if (date.isAfter(lastMin)) {
				String tempMin = sample.getTempMin();
				seriesTempMaxForecast.getData().add(new Data<String, Number>(dateString,Float.parseFloat(tempMin)));
			}
			
			if (date.isAfter(lastMax)) {
				String tempMax = sample.getTempMax();	
				seriesTempMinForecast.getData().add(new Data<String, Number>(dateString,Float.parseFloat(tempMax)));
			}
		}
	}
	
	private void clearAllSeries() {
		seriesTempMin.getData().clear();
		seriesTempMax.getData().clear();
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
		fioSamplesDaily = getFioData(bom, fio, lat, lon);
	}
	
	@Override
	public void plotData() 
	{
        addToAllSeries(wthrSamplesDaily, fioSamplesDaily);
	}
	
//	@Override
//	protected void onRefresh() 
//	{
//		clearAllSeries();
//		fetchData(Bom bom);
//		plotData();
//	}
	
	@Override
	public String getCssPath()
	{
		return cssPath;
	}
}
