package guiPlotsTest;


import java.net.URL;
import java.time.YearMonth;
import java.util.Enumeration;

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
		Station charlton = allStations.get(0);
		graph.setTitle("Line Chart Sample");
		
		//Category axis is needed for non ints, while number is needed for ints
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Date");
        yAxis.setLabel("Temperature in Degrees");
        
        //Creates linechart based on string and number, can be changed to other things
        final LineChart<String,Number> lineChart = new LineChart<String,Number>(xAxis,yAxis);              
        lineChart.setTitle(charlton.getName());       
        XYChart.Series seriesMinTemp = new XYChart.Series();
        XYChart.Series seriesMaxTemp = new XYChart.Series();
        XYChart.Series series9amTemp = new XYChart.Series();
        XYChart.Series series3pmTemp = new XYChart.Series();
        
        seriesMinTemp.setName("Minimum Temperature");
        seriesMaxTemp.setName("Maximum Temperature");
        series9amTemp.setName("9am Temperature");
        series3pmTemp.setName("3pm Temperature");
         
        YearMonth start = YearMonth.now().minusMonths(12);
        YearMonth end = YearMonth.now().plusMonths(1);
        
        WthrSamplesDaily dataCharlton = new WthrSamplesDaily();
        dataCharlton = Bom.getWthrRange(charlton, start, end);
        
        /* these were reversed Rad */
        for(WthrSampleDaily sample: dataCharlton) {
        	String date = sample.getDate();
        	String tempMax = sample.getMaxTemp();
        	String tempMin = sample.getMinTemp();
        	String temp9am = sample.getTemp9am();
        	String temp3pm = sample.getTemp3pm();
        	
        	if (tempMin.length() > 0)
        		seriesMinTemp.getData().add(new XYChart.Data(date,Float.parseFloat(tempMin)));
        	if (tempMax.length() > 0)
        		seriesMaxTemp.getData().add(new XYChart.Data(date,Float.parseFloat(tempMax)));
        	if (temp9am.length() > 0)
        		series9amTemp.getData().add(new XYChart.Data(date,Float.parseFloat(temp9am)));
        	if (temp3pm.length() > 0)
        		series3pmTemp.getData().add(new XYChart.Data(date,Float.parseFloat(temp3pm)));
        }
        
        Scene scene  = new Scene(lineChart,800,600);
        lineChart.getData().addAll(seriesMinTemp, seriesMaxTemp, series9amTemp, series3pmTemp);
        lineChart.setCreateSymbols(false);
        
        Legend legend = (Legend)lineChart.lookup(".chart-legend");
        ObservableList legendChildren = legend.getChildren();
        Object legendMinTemp = legendChildren.get(0);
        ((Node) legendMinTemp).setOnMouseClicked(new EventHandler<MouseEvent>(){
        	 
            @Override
            public void handle(MouseEvent arg0) {
               lineChart.getData();
            }
   
        });
        
        graph.setScene(scene);
        URL url = this.getClass().getResource("graph.css");
        String css = url.toExternalForm();
        scene.getStylesheets().add(css);
        graph.show();
	}
}
