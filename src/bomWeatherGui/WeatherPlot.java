package bomWeatherGui;

import javafx.scene.chart.Axis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class WeatherPlot extends LineChart<Number, Number>
{
	

	public WeatherPlot(Axis<Number> xAxis, Axis<Number> yAxis)
	{
		super(xAxis, yAxis);
	}

	public static WeatherPlot getChart()

	{
		
	    final NumberAxis xAxis = new NumberAxis();
	    final NumberAxis yAxis = new NumberAxis();
	    //creating the chart
	    final WeatherPlot plotOut =
	            new WeatherPlot(xAxis,yAxis);


	    //defining a series
	    Series series = new Series();
	    //populating the series with data

	    series.getData().add(new Data(1, 23));
	    series.getData().add(new Data(2, 14));
	    series.getData().add(new Data(3, 15));
	    series.getData().add(new Data(4, 24));
	    series.getData().add(new Data(5, 34));
	    series.getData().add(new Data(6, 36));
	    series.getData().add(new Data(7, 22));
	    series.getData().add(new Data(8, 45));
	    series.getData().add(new Data(9, 43));
	    series.getData().add(new Data(10, 17));
	    series.getData().add(new Data(11, 29));
	    series.getData().add(new Data(12, 25));
	    series.getData().add(new Data(12, 50));


	    plotOut.getData().add(series);

	    return plotOut;
	}
}
