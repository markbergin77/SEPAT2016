package guiPlotsTest;


import java.util.Enumeration;

import data.Bom;
import data.Station;
import data.StationList;
import data.WthrMonth;
import data.WthrSampleCoarse;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
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
		Station charlton = allStations.get(0);
		//Just using Edenhope as a test
		Station edenhope = allStations.get(7);
		graph.setTitle("Line Chart Sample");
		
		//Category axis is needed for non ints, while number is needed for ints
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Date");
        yAxis.setLabel("Temperature in Degrees");
        
        //Creates linechart based on string and number, can be changed to other things
        final LineChart<String,Number> lineChart = new LineChart<String,Number>(xAxis,yAxis);              
        lineChart.setTitle("Daily Readings");       
        XYChart.Series seriesCharlton = new XYChart.Series();
        XYChart.Series seriesEdenhope = new XYChart.Series();
        seriesCharlton.setName(charlton.getName());
        seriesEdenhope.setName(edenhope.getName());
        
        //Prints the weather for Charlton, March 2016 
        WthrMonth dataCharlton = new WthrMonth();
        for (WthrSampleCoarse sample: charlton.getWthrLastMonth("201602")) {
        	dataCharlton.addElement(sample);
        }
        for (WthrSampleCoarse sample: charlton.getWthrLastMonth("201603")) {
        	dataCharlton.addElement(sample);
        }
        
        WthrMonth dataEdenhope = new WthrMonth();
        for (WthrSampleCoarse sample: edenhope.getWthrLastMonth("201602")) {
        	dataEdenhope.addElement(sample);
        }
        for (WthrSampleCoarse sample: edenhope.getWthrLastMonth("201603")) {
        	dataEdenhope.addElement(sample);
        }
        
        /* these were reversed Rad */
        for(int i= 0; i < dataCharlton.size(); i++)
        	seriesCharlton.getData().add(new XYChart.Data(dataCharlton.get(i).getDate(),Float.parseFloat(dataCharlton.get(i).getTemp3pm())));
        for(int i= 0; i < dataEdenhope.size(); i++)
        	seriesEdenhope.getData().add(new XYChart.Data(dataEdenhope.get(i).getDate(),Float.parseFloat(dataEdenhope.get(i).getTemp3pm())));
        
        Scene scene  = new Scene(lineChart,800,600);
        lineChart.getData().addAll(seriesCharlton, seriesEdenhope);
        graph.setScene(scene);
        graph.show();
	}
	
}
