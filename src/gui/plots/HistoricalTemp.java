package gui.plots;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

import com.sun.javafx.charts.Legend;
import data.Bom;
import data.Fio;
import data.Station;
import data.samples.WthrSampleDaily;
import data.samples.WthrSamplesDaily;
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

public class HistoricalTemp extends PlotBase
{	
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

        //plot();
        lineChart.getData().addAll(seriesTempMin, seriesTempMax, seriesTemp9am, seriesTemp3pm);
        // Hacky solution, add a css class to each line
        String[] lineClasses = {"tempMin", "tempMax", "temp9am", "temp3pm"};
        seriesTempMin.getNode().getStyleClass().add(lineClasses[0]);
        seriesTempMax.getNode().getStyleClass().add(lineClasses[1]);
        seriesTemp9am.getNode().getStyleClass().add(lineClasses[2]);
        seriesTemp3pm.getNode().getStyleClass().add(lineClasses[3]);
        
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
        assembleFrom(lineChart);
	}
	
	private void addToAllSeries(WthrSamplesDaily wthrSamplesDaily) {
		if (wthrSamplesDaily == null) {
			System.out.println("Null");
			return;
		}
		for(WthrSampleDaily sample: wthrSamplesDaily) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-LL-yy");
        	String date = sample.getDate().format(formatter);
        	String tempMax = sample.getMaxTemp();
        	String tempMin = sample.getMinTemp();
        	String temp9am = sample.getTemp9am();
        	String temp3pm = sample.getTemp3pm();
        	
        	// Check if the string is null or blank, due to FILE NOT FOUND
        	if (tempMin.length() > 0)
        		seriesTempMin.getData().add(new Data<String, Number>(date,Float.parseFloat(tempMin)));
        	if (tempMax.length() > 0)
        		seriesTempMax.getData().add(new XYChart.Data<String, Number>(date,Float.parseFloat(tempMax)));
        	if (temp9am.length() > 0)
        		seriesTemp9am.getData().add(new XYChart.Data<String, Number>(date,Float.parseFloat(temp9am)));
        	if (temp3pm.length() > 0)
        		seriesTemp3pm.getData().add(new XYChart.Data<String, Number>(date,Float.parseFloat(temp3pm)));
        }
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
	public void plotData() 
	{
        addToAllSeries(wthrSamplesDaily);
	}
	
	@Override
	public String getCssPath()
	{
		return cssPath;
	}
}
