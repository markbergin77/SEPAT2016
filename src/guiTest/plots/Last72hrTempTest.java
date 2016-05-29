package guiTest.plots;

import data.Bom;
import data.Station;
import data.StationList;
import data.samples.WthrSamplesFine;
import gui.plots.Last72hrTemp;
import gui.plots.PlotBase;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Last72hrTempTest extends PlotTestBase
{
	StationList allStations;
	
	Bom bom = new Bom();
	
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
				
		Last72hrTemp plot = new Last72hrTemp(station);
		plot.fetchData(bom, fio);
		plot.plotData();
		
		Scene scene  = new Scene(plot);
		primaryStage.setScene(scene);
        scene.getStylesheets().add(plot.getCssPath());
        primaryStage.show();
		
	}
	@Override
	public void onRefresh(PlotBase plot)
	{
		// TODO Auto-generated method stub
		
	}
}
