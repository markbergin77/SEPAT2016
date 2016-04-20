package guiPlotsTest;

import java.net.URL;
import java.time.YearMonth;

import com.sun.javafx.charts.Legend;

import data.Bom;
import data.Station;
import data.StationList;
import data.WthrSamplesDaily;
import data.WthrSampleDaily;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

//Testing class for line graph, includes viewing temperature of locations
public class MultipleMonthMultipleLine extends Application{
	StationList allStations;
	public static void main(String args[])
    {
        launch(args);
    }

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void start(Stage graph) throws Exception {
		//Grabbing stations
		try {
			allStations = Bom.getAllStations();
	} catch (Exception e1) {
		e1.printStackTrace();
	}
		
		//Just using Charlton as a test
		Station charlton = null;
		if (allStations != null) {
			charlton = allStations.get(0);
		}
		else {
			return;
		}
		graph.setTitle("Line Chart Sample");
		
		//Category axis is needed for non ints, while number is needed for ints
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Date");
        yAxis.setLabel("Temperature in Degrees");
        
        //Creates linechart based on string and number, can be changed to other things
        final LineChart<String,Number> lineChart = new LineChart<String,Number>(xAxis,yAxis);              
        lineChart.setTitle(charlton.getName());       
        XYChart.Series seriesTempMin = new XYChart.Series();
        XYChart.Series seriesTempMax = new XYChart.Series();
        XYChart.Series seriesTemp9am = new XYChart.Series();
        XYChart.Series seriesTemp3pm = new XYChart.Series();
        
        // Set Legend labels
        seriesTempMin.setName("Minimum Temperature");
        seriesTempMax.setName("Maximum Temperature");
        seriesTemp9am.setName("9am Temperature");
        seriesTemp3pm.setName("3pm Temperature");
        
        // Use 12 most recent months of data
        YearMonth start = YearMonth.now().minusMonths(12);
        YearMonth end = YearMonth.now().plusMonths(1);
        
        WthrSamplesDaily dataCharlton = new WthrSamplesDaily();
        dataCharlton = Bom.getWthrRange(charlton, start, end);
        
        for(WthrSampleDaily sample: dataCharlton) {
        	String date = sample.getDate();
        	String tempMax = sample.getMaxTemp();
        	String tempMin = sample.getMinTemp();
        	String temp9am = sample.getTemp9am();
        	String temp3pm = sample.getTemp3pm();
        	
        	// Check if the string is null or blank, due to FILE NOT FOUND
        	if (tempMin.length() > 0)
        		seriesTempMin.getData().add(new XYChart.Data(date,Float.parseFloat(tempMin)));
        	if (tempMax.length() > 0)
        		seriesTempMax.getData().add(new XYChart.Data(date,Float.parseFloat(tempMax)));
        	if (temp9am.length() > 0)
        		seriesTemp9am.getData().add(new XYChart.Data(date,Float.parseFloat(temp9am)));
        	if (temp3pm.length() > 0)
        		seriesTemp3pm.getData().add(new XYChart.Data(date,Float.parseFloat(temp3pm)));
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
        ObservableList legendChildren = legend.getChildren();
        
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
        
        Scene scene  = new Scene(lineChart,800,600);
        graph.setScene(scene);
        URL url = this.getClass().getResource("graph.css");
        String css = url.toExternalForm();
        scene.getStylesheets().add(css);
        graph.show();
	}
}
