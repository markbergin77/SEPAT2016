package guiPlotsTest;

import java.net.URL;

import data.Bom;
import data.Station;
import data.StationList;
import guiPlots.HisTempPlot;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HisTempPlotTest extends Application{
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
			allStations = Bom.getAllStations();
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
		
		HisTempPlot lineChart = new HisTempPlot(station);
		
		Scene scene  = new Scene(lineChart);
		primaryStage.setScene(scene);
        scene.getStylesheets().add(lineChart.getCssPath());
        primaryStage.show();
		
	}
}
