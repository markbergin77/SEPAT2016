package guiPlots;

import java.io.IOException;
import java.net.URL;

import data.Station;
import data.WthrSampleFine;
import data.WthrSamplesFine;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;

public class PlotAveTemp extends PlotBase 
{

	private String cssPath;
	static String cssFileName = "HisTempPlot.css";
	
	public PlotAveTemp(Station station) throws IOException {
		super();
		URL url = this.getClass().getResource(cssFileName);
        cssPath = url.toExternalForm();
		final CategoryAxis xAxis = new CategoryAxis();
	    final NumberAxis yAxis = new NumberAxis();
	    LineChart<String,Number> lineChart = new LineChart<String,Number>(xAxis, yAxis);
        lineChart.setTitle(station.getName());
        xAxis.setLabel("Time Periods");
        yAxis.setLabel("Average Temperature in Degrees");
        
        WthrSamplesFine location = station.getWthrLast72hr();
        
        XYChart.Series<String, Number> temperatures = new XYChart.Series<String, Number>();

        //Need seperate list to associate each time of day with a specific "zone" or period
        WthrSamplesFine earlyMorns = new WthrSamplesFine();
        WthrSamplesFine middays = new WthrSamplesFine();
        WthrSamplesFine nights = new WthrSamplesFine();
        WthrSamplesFine lateNights = new WthrSamplesFine();
        
        //Goes through entire list of recorded location entries
        for(int i=location.size()-1 ; i > 0; i--)
        {
        	int time24hours = 0;
        	
        	//Splits the given time date into it's raw form of xxxxam or pm
        	String[] dateSplit = location.get(i).getLocalDateTime().split("/");
        	String[] timeSplit = dateSplit[1].split(":");
        	String timeconcat = timeSplit[0] + timeSplit[1];
        	String finalTime;
        	//Checks if it contains am or pm, if pm, we need to change it to 24 hour format
        	if(timeconcat.contains("pm"))
        	{
        	String[] pmHourTime = timeconcat.split("pm");
        	time24hours = Integer.parseInt(pmHourTime[0]);
        	finalTime = Integer.toString(time24hours + 1200);
        	}
        	else{
        	String[] amHourTime = timeconcat.split("am");
        	time24hours = Integer.parseInt(amHourTime[0]);
        	finalTime = Integer.toString(time24hours);
        	}
        	/* To deal with the late night period of 11pm to 4am
        	 * we can't really check for a value in between 11pm and 4am 
        	 * so we make 4am into a 24 hour next day kind of thing
        	 * from 2300 - 0400 the next day we make 0400 = 2800 
        	 */
        	if(Integer.parseInt(finalTime) < 400)
        	{
        		int earlyHours = Integer.parseInt(finalTime);
        		finalTime = Integer.toString(earlyHours + 2400);
        	}
        	
        	if(500 <= Integer.parseInt(finalTime) & Integer.parseInt(finalTime) <= 1000)
        	{        		
        		earlyMorns.add(location.get(i));
        		
        	}
        	//Weird situation checking inbetween periods of both am and pm (11am - 4pm), 12pm now turns to 2400 and we don't want to check 12
        	//as that's 12 in the morning
        	if(1100 <= Integer.parseInt(finalTime) & Integer.parseInt(finalTime) <= 1600 & Integer.parseInt(finalTime) != 1200 
        	   || Integer.parseInt(finalTime) == 2400)
        	{
        		middays.add(location.get(i));
        		
        	}
        	if(1700 <= Integer.parseInt(finalTime) & Integer.parseInt(finalTime) <= 2200)
        	{
        		nights.add(location.get(i));
        	
        	}
        	//once again, we don't want 2400 as that's 12 pm, we want am for this check
        	if(2300 <= Integer.parseInt(finalTime) & Integer.parseInt(finalTime) <= 2800 & Integer.parseInt(finalTime) != 2400 
             	   || Integer.parseInt(finalTime) == 1200)
        	{
        		lateNights.add(location.get(i));
        		
        	}
        	
        }     	
        
        temperatures.getData().add(new XYChart.Data("Early Morning Average",averageTemp(earlyMorns)));
        temperatures.getData().add(new XYChart.Data("Midday Average",averageTemp(middays)));  
        temperatures.getData().add(new XYChart.Data("Night Average",averageTemp(nights)));
        temperatures.getData().add(new XYChart.Data("Late Night Average",averageTemp(lateNights)));
   
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
	

	float averageTemp(WthrSamplesFine samples)
	{
		float average = 0;
		for(WthrSampleFine index : samples)
		{
			average += Float.parseFloat(index.getAirTemp());
		}
		average = average/samples.size();
		System.out.println(average);
		return average;
	}
	
	public String getCssPath()
	{
		return cssPath;
	}
}

