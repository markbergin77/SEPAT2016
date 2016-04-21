package guiPlots;

import java.io.IOException;
import java.net.URL;
import java.time.YearMonth;

import com.sun.javafx.charts.Legend;

import data.Bom;
import data.Station;
import data.WthrSampleDaily;
import data.WthrSamplesDaily;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;

public class PlotHistoricalTemp extends PlotBase
{
	private String cssPath;
	static String cssFileName = "HisTempPlot.css";
	
	public PlotHistoricalTemp(Station station) throws IOException
	{
		super();
		URL url = this.getClass().getResource(cssFileName);
        cssPath = url.toExternalForm();
		final CategoryAxis xAxis = new CategoryAxis();
	    final NumberAxis yAxis = new NumberAxis();
	    LineChart<String,Number> lineChart = new LineChart<String,Number>(xAxis, yAxis);
		
		xAxis.setLabel("Date");
        yAxis.setLabel("Temperature in Degrees");
        lineChart.setTitle(station.getName());
        
        YearMonth start = YearMonth.now().minusMonths(12);
        YearMonth end = YearMonth.now().plusMonths(1);
        
        WthrSamplesDaily wthrSamplesDaily = new WthrSamplesDaily();
        wthrSamplesDaily = Bom.getWthrRange(station, start, end);
        
        // Init series
        XYChart.Series<String, Number> seriesTempMin = new XYChart.Series<String, Number>();
        XYChart.Series<String, Number> seriesTempMax = new XYChart.Series<String, Number>();
        XYChart.Series<String, Number> seriesTemp9am = new XYChart.Series<String, Number>();
        XYChart.Series<String, Number> seriesTemp3pm = new XYChart.Series<String, Number>();
        
        // Set Legend labels
        seriesTempMin.setName("Minimum Temperature");
        seriesTempMax.setName("Maximum Temperature");
        seriesTemp9am.setName("9am Temperature");
        seriesTemp3pm.setName("3pm Temperature");
        
        for(WthrSampleDaily sample: wthrSamplesDaily) {
        	String date = sample.getDate();
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
        
        lineChart.getData().addAll(seriesTempMin, seriesTempMax, seriesTemp9am, seriesTemp3pm);
        
        // Remove markers from line
        lineChart.setCreateSymbols(false);
        
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
        
        // Allow children to resize vertically
        ColumnConstraints columnConstraints = new ColumnConstraints();
        columnConstraints.setHgrow(Priority.ALWAYS);
        this.getColumnConstraints().add(columnConstraints);
        
        // Allow children to resize horizontally
        RowConstraints rowConstraints = new RowConstraints();
        rowConstraints.setVgrow(Priority.ALWAYS);
        this.getRowConstraints().add(rowConstraints);
        
        // add the lineChart to the gridPane
        this.add(lineChart, 0, 0);
	}
	
	@Override
	public String getCssPath()
	{
		return cssPath;
	}
}
