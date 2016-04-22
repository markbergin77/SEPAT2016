package guiPlotsTest;

import data.Bom;
import data.Station;
import data.StationList;
import guiPlots.PlotLast72hrTemp;
import guiPlots.Table72HrData;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Table72HrTest extends Application{
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
		
		Table72HrData table = new Table72HrData(station);
		
		Scene scene  = new Scene(table);
		primaryStage.setScene(scene);
        scene.getStylesheets().add(table.getCssPath());
        primaryStage.show();
		
	}
}
