package guiLookTest;


import data.Bom;
import data.Station;
import data.StationList;
import data.Wthr72hr;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
//Testing class for line graph, includes viewing temperature of locations
public class PlotTest extends Application{
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
		//Just using Charlston as a test
		Station charlston = allStations.get(0);	
		graph.setTitle("Line Chart Sample");
		//Category axis is needed for non ints, while number is needed for ints
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Every 30 mins");
        yAxis.setLabel("Temperature in Degrees");
        //Creates linechart based on string and number, can be changed to other things
        final LineChart<String,Number> lineChart = new LineChart<String,Number>(xAxis,yAxis);              
        lineChart.setTitle("TESTER");       
        XYChart.Series temperatures = new XYChart.Series();
        temperatures.setName(charlston.getName());
        //Prints the weather for Charlston in the most recent time 
        Wthr72hr tester = charlston.getWthrLast72hr();      
        for(int i=tester.size()-1 ; i > 0; i--)
        {
        	/*Per requirements state
        	 * b. The program should be able to graph the temperature history for each site
			 * in the favourites list, including the max, min, 9am and 3pm temperatures 
        	 */
        	if(tester.get(i).getLocalDateTime().contains("09:00am") || tester.get(i).getLocalDateTime().contains("03:00pm"))
        	{
        		temperatures.getData().add(new XYChart.Data(tester.get(i).getLocalDateTime(),Float.parseFloat(tester.get(i).getAirTemp())));
        	}
        }
        Scene scene  = new Scene(lineChart,800,600);
        lineChart.getData().add(temperatures);
       
        graph.setScene(scene);
        graph.show();
	}
	
}
