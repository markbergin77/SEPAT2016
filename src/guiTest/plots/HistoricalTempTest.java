package guiTest.plots;

import data.Bom;
import data.Station;
import data.StationList;
import gui.plots.HistoricalTemp;
import gui.plots.PlotBase;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HistoricalTempTest extends PlotTestBase
{
	StationList allStations;
	public static void main(String args[])
    {
        launch(args);
    }
	@Override
	public void start(Stage primaryStage) throws Exception
	{
		//Grabbing stations
		try {
			allStations = bom.getAllStations();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		// Pick a station to test
		Station station = null;
		if (allStations != null) {
			station = allStations.get(0);
		}
		else {
			return;
		}
		
		HistoricalTemp lineChart = new HistoricalTemp(station);
		
		Scene scene  = new Scene(lineChart);
		primaryStage.setScene(scene);
        scene.getStylesheets().add(lineChart.getCssPath());
        primaryStage.show();
		
	}
	@Override
	public void onRefresh(PlotBase plot)
	{
		// TODO Auto-generated method stub
		
	}
}
