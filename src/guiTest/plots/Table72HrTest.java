package guiTest.plots;

import data.Bom;
import data.Station;
import data.StationList;
import gui.plots.PlotBase;
import gui.plots.Table72Hr;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Table72HrTest extends PlotTestBase
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
		
		Table72Hr table = new Table72Hr(station);
		
		Scene scene  = new Scene(table);
		primaryStage.setScene(scene);
        scene.getStylesheets().add(table.getCssPath());
        primaryStage.show();
		
	}
	@Override
	public void onRefresh(PlotBase plot)
	{
		// TODO Auto-generated method stub
		
	}
}
