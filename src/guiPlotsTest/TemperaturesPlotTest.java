package guiPlotsTest;

import java.net.URL;

import data.Bom;
import data.Station;
import data.StationList;
import guiPlots.TemperaturesPlot;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TemperaturesPlotTest extends Application{
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
		
		TemperaturesPlot lineChart = new TemperaturesPlot(station);
		
		Scene scene  = new Scene(lineChart);
		primaryStage.setScene(scene);
        URL url = this.getClass().getResource("graph.css");
        String css = url.toExternalForm();
        scene.getStylesheets().add(css);
        primaryStage.show();
		
	}
}
