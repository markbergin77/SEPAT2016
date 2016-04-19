package app;
import java.awt.Dimension;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import data.Bom;
import data.Station;
import data.StationList;
import gui.GuiEventInterface;
import gui.HomeScreenInit;
import gui.HomeScreen;
import gui.SplashScreen;
import guiPlots.TempPlot;
import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.stage.Stage;
import user.Favourite;
import user.User;

public class Main extends Application 
	implements GuiEventInterface
{
	/* This service will finish one at a time
	 * on another thread so that we don't block
	 * the gui thread */
    ExecutorService exec = 
    		Executors.newSingleThreadExecutor(
		    r -> 
		    {
		        Thread t = new Thread(r);
		        // allow app to exit if tasks are running
		        t.setDaemon(true); 
		        return t ;
		    });
    
    Dimension homeWindowSize;
	StationList allStations;
	HomeScreen homeScreen;
	Scene scene;
	User user;
	
	public static void main(String args[])
    {
        launch(args);
    }
	
	@Override
	public void start(Stage window) throws Exception 
	{
	    window.setTitle("aids");
		window.setResizable(false);
        window.setOnCloseRequest(e -> onQuit());
        SplashScreen splash = new SplashScreen();
        EasyTask getStationsTask = new EasyTask(() ->
        { 
        	try {
        		/* Pass in splash so that this function can update
        		 * the splash screen's text when something changes.*/
					allStations = Bom.getAllStations(splash);
			} catch (Exception e1) {
				/* TODO User might not be able to connect to BOM!
				 * Must put something on the splash screen,  
				 * Use splash.loadingUpdate(String)
				 * maybe retry connecting in a loop */
				e1.printStackTrace();
				splash.loadingUpdate("Error: couldn't connect. Please restart");
			}
        	// Tricky: loadingUpdate actually does a runLater()
        	splash.loadingUpdate("Loading user");
			try {
				user = new User("data/user");
			} catch (Exception e1) {
				user = new User();
			}
        	splash.loadingUpdate("Creating GUI elements");
        	HomeScreenInit homeInit = new HomeScreenInit(allStations, user);
			homeScreen = new HomeScreen(homeInit, this);
			scene = new Scene(homeScreen);
			splash.loadingUpdate("");
			splash.startClosing();
        });
        
        splash.setOnClosed(e -> 
        {
        	window.setScene(scene);
        	homeScreen.startShowing();
        	window.sizeToScene();
        	window.centerOnScreen();
        	window.show();
        });
        
        /* Now start the chain of tasks, 
         * getStationsTask was the first.*/
        queueTask(getStationsTask);
	}
	
	void onQuit()
	{
		user.saveUser("data/user");
		System.exit(0);
	}
	
	private void queueTask(Task<?> task)
	{
		exec.submit(task);
	}

	@Override
	public void onOpen72TempPlot(Station station) 
	{
		TempPlot tempPlot72Hr = new TempPlot(station);
		Stage newPlotWindow = new Stage();
		Scene plotScene = new Scene(tempPlot72Hr);
		newPlotWindow.setTitle("Temperature");
		newPlotWindow.setScene(plotScene);
	}

	@Override
	public void onAddFav(Station station) 
	{
		/* Add to user's fav list */
		if(! user.getFavs().hasForStation(station))
		{
			Favourite newFav = Favourite.create(station);
			user.getFavs().add(newFav);
			homeScreen.getExplorer().onAddFavourite(newFav);
		}
		
	}

	@Override
	public void onOpenHisTempPlot(Station station) 
	{
				
	}
}