package guiTest.home;

import data.Station;
import data.StationList;
import gui.home.HomeScreen;
import gui.home.HomeScreenInit;
import guiTest.GuiTestBase;
import javafx.scene.Scene;
import javafx.stage.Stage;
import user.Favourite;
import user.User;
import utilities.TestData;

public class HomeScreenTest extends GuiTestBase
	implements HomeScreen.EventInterface
{
	//testing class for entire homescreen view
	StationList allStations;	
	@Override
	public void start(Stage window) throws Exception 
	{
	    window.setTitle("Home screen test");
        window.setOnCloseRequest(e -> System.exit(0));
        try {
				allStations = TestData.loadAllStations();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
        User user = new User();
        HomeScreenInit homeInit = new HomeScreenInit(allStations, user);
        HomeScreen homeScreen = new HomeScreen(homeInit, this);
        //homeScreen.getExplorer().addStationsAll(allStations);
        Scene scene = new Scene(homeScreen);
		window.setScene(scene);
    	window.sizeToScene();
    	window.centerOnScreen();
    	window.show();
	}
	
	public static void main(String args[])
    {
        launch(args);
    }

	@Override
	public void onOpen72TempPlot(Station station)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onOpenHisTempPlot(Station station)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onAddFav(Station station)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onOpenHisTable(Station station)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onOpen72HrTable(Station station)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onCloseAllPlots(Station station)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onCloseAllPlots()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onClearFavs()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFavRemove(Favourite fav)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onExperimentalPlot(Station station)
	{
		// TODO Auto-generated method stub
		
	}
}
