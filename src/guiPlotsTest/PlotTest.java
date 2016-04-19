package guiPlotsTest;


import data.Bom;
import data.Station;
import data.StationList;
import data.WthrSampleFine;
import data.WthrSamplesFine;
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
        WthrSamplesFine tester = charlston.getWthrLast72hr();
        
        //Variables needed for min/max temps in any given day
        String currentDay = null;
        boolean dateChosen = false;
        //important: arrays fixed for the time being with 9am being index 0
        WthrSampleFine[] wthrArray = new WthrSampleFine[3];
        
        for(int i=tester.size()-1 ; i > 0; i--)
        {
        	/*Per requirements state
        	 * b. The program should be able to graph the temperature history for each site
			 * in the favourites list, including the max, min, 9am and 3pm temperatures 
        	 */
        	String[] dateSplit = tester.get(i).getLocalDateTime().split("/");
        	
        	if(dateChosen = false)
        	{       		
            	currentDay = dateSplit[0];
            	dateChosen = true;
        	}
        	
        	if(dateSplit[0] != currentDay)
        	{        		
        		dateChosen = false;
        		sortArray(wthrArray);
        		//Then I have to add them to graph
        		
        	}
        	
        	//Prints 9ams and 3pms
        	if(tester.get(i).getLocalDateTime().contains("09:00am"))
        	{        		
        		wthrArray[0] = tester.get(i);
        	}
        	if(tester.get(i).getLocalDateTime().contains("03:00pm"))
        	{
        		wthrArray[1] = tester.get(i);
        	}
        	if(Integer.parseInt(tester.get(i).getAirTemp()) < Integer.parseInt(wthrArray[2].getAirTemp()) | wthrArray[2] == null)
        	{
        		wthrArray[2] = tester.get(i);
        	}
        	if(Integer.parseInt(tester.get(i).getAirTemp()) > Integer.parseInt(wthrArray[2].getAirTemp()))
        	{
        		wthrArray[3] = tester.get(i);
        	}
        	
        	//Now have to sort them by time in an array
        	
        	
        	
        	
        }
        Scene scene  = new Scene(lineChart,800,600);
        lineChart.getData().add(temperatures);
       
        graph.setScene(scene);
        graph.show();
	}
	
	void sortArray(WthrSampleFine[] wthrArray)
	{
		 int n = wthrArray.length;
		 //Setting temp as the first int, don't want to fill out constructor
         WthrSampleFine temp = wthrArray[0];
         
         for(int i=0; i < n; i++){
                 for(int j=1; j < (n-i); j++){
                	 //Splits from 17/02:00pm to now   02 and 00pm
                	 //only two instances of data per hour, 
                     String[] dateSplit = wthrArray[j].getLocalDateTime().split("/");
                     String[] timeSplit = dateSplit[1].split(":");
                     
                     
                         if(wthrArray[j-1] > wthrArray[j]){
                                 //swap the elements!
                                 temp = wthrArray[j-1];
                                 wthrArray[j-1] = wthrArray[j];
                                 wthrArray[j] = temp;
                         }
                 }
	}
}
}