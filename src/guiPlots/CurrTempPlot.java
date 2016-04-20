package guiPlots;

import java.io.IOException;
import java.net.URL;
import java.time.YearMonth;

import com.sun.javafx.charts.Legend;

import data.Bom;
import data.Station;
import data.WthrSampleDaily;
import data.WthrSampleFine;
import data.WthrSamplesDaily;
import data.WthrSamplesFine;
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

public class CurrTempPlot extends GridPane
{
	private String cssPath;
	static String cssFileName = "CurrTempPlot.css";
	
	public CurrTempPlot(Station station) throws IOException
	{
		super();
		URL url = this.getClass().getResource(cssFileName);
        cssPath = url.toExternalForm();
		final CategoryAxis xAxis = new CategoryAxis();
	    final NumberAxis yAxis = new NumberAxis();
	    LineChart<String,Number> lineChart = new LineChart<String,Number>(xAxis, yAxis);
		
		xAxis.setLabel("Date/Time");
        yAxis.setLabel("Temperature in Degrees");
        lineChart.setTitle(station.getName());
        
        WthrSamplesFine wthrSamplesFine = new WthrSamplesFine();
        wthrSamplesFine = Bom.getWthrLast72hr(station);
        
        // Init series
        XYChart.Series<String, Number> seriesAirTemp = new XYChart.Series<String, Number>();
        
        // Set Legend labels
        seriesAirTemp.setName("Air Temperature");
        
        int samplesSize = wthrSamplesFine.size();
        WthrSampleFine sample = null;
        for(int i = samplesSize - 1; i > -1; i--) {
        	sample = wthrSamplesFine.get(i);
        	String date = sample.getLocalDateTime();
        	String airTemp = sample.getAirTemp();
        	
        	// Check if the string is null or blank
        	if (airTemp.length() > 0)
        		seriesAirTemp.getData().add(new Data<String, Number>(date,Float.parseFloat(airTemp)));
        }
        
        lineChart.getData().add(seriesAirTemp);
        
        // Remove markers from line
        lineChart.setCreateSymbols(false);

        
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

	public String getCssPath()
	{
		return cssPath;
	}
}
