package guiTest.plots;

import data.Bom;
import data.Station;
import data.StationList;
import gui.plots.PlotBase;
import gui.plots.TableHistorical;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utilities.SaveTestData;

public class TableHistoricalTest extends PlotTestBase
{
	StationList allStations;
	Bom bom;
	public static void main(String args[])
    {
        launch(args);
    }
	
	@Override
	public void start(Stage primaryStage) throws Exception
	{
		//Grabbing stations
		try {
			allStations = SaveTestData.loadAllStations();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		Station station = allStations.get(0);
		
		TableHistorical table = new TableHistorical(station);
		
		Scene scene  = new Scene(table);
		primaryStage.setScene(scene);
        scene.getStylesheets().add(table.getCssPath());
        primaryStage.show();
	}
	
	@Override
	public void onRefresh(PlotBase plot)
	{
		
	}
}
