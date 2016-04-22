package app;
import java.awt.Dimension;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import data.Bom;
import data.Station;
import data.StationList;
import gui.GuiEventInterface;
import gui.HomeScreen;
import gui.HomeScreenInit;
import gui.SplashScreen;
import guiPlots.PlotLast72hrTemp;
import guiPlots.PlotBase;
import guiPlots.PlotHistoricalTemp;
import guiPlots.PlotType;
import guiPlots.PlotWindow;
import guiPlots.PlotWindows;
import guiTables.TableWindows72Hr;
import guiTables.TableWindowsYearlyData;
import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import user.Favourite;
import user.User;

public class Main extends Application 
	implements GuiEventInterface
{
	/* This service will finish tasks one at a time
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
    
    
    Stage window;
    Dimension homeWindowSize;
	StationList allStations;
	HomeScreen homeScreen;
	Scene scene;
	User user;
	boolean newUser = false;
	PlotWindows plotWindows = new PlotWindows();
	TableWindows72Hr tables72Hr = new TableWindows72Hr();
	TableWindowsYearlyData tablesYearlyData = new TableWindowsYearlyData();
	
	public static void main(String args[])
    {
        launch(args);
    }
	
	@Override
	public void start(Stage window) throws Exception 
	{
		this.window = window;
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
			} catch (UnknownHostException e) {
				/* TODO User might not be able to connect to BOM!
				 * Must put something on the splash screen,  
				 * Use splash.loadingUpdate(String)
				 * maybe retry connecting in a loop */
				splash.loadingUpdate("Error: couldn't connect. Please restart");
				return;
			} catch (Exception e) {
				splash.loadingUpdate("Something went wrong. Please restart");
				return;
			}
        	// Tricky: loadingUpdate actually does a runLater()
        	splash.loadingUpdate("Loading user");
			try {
				user = User.loadUser("data/user");
			} catch (Exception e1) {
				newUser = true;
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
        	if(newUser)
        	{
        		window.centerOnScreen();
        	}
        	else
        	{
        		window.setX(user.getWindowX());
            	window.setY(user.getWindowY());
        	}
        	window.show();
        	addPlotWindows(user.reconstructPlotWindows());
        	plotWindows.showAll();
        });
        
        /* Now start the chain of tasks, 
         * getStationsTask was the first.*/
        queueTask(getStationsTask);
	}
	
	void onQuit()
	{
		user.setMainWindowPosSave(window.getX(), window.getY());
		user.storePlotWindows(plotWindows);
		User.saveUser(user, "data/user");
		System.exit(0);
	}
	
	private void queueTask(Task<?> task)
	{
		exec.submit(task);
	}
	
	void openPlot(PlotBase plot)
	{
		PlotWindow newWindow = new PlotWindow(plot);
		newWindow.show();
		newWindow.setOnCloseRequest(e -> {
			plotWindows.remove(newWindow);
		});
		plotWindows.add(newWindow);
	}
	
	void addPlotWindows(PlotWindows windows)
	{
		plotWindows.addAll(windows);
    	plotWindows.setOnCloseRequest(event -> 
    	{
    		plotWindows.remove((PlotWindow)event.getSource());
    	});
	}

	@Override
	public void onOpen72TempPlot(Station station) 
	{
		PlotWindow existingPlotWindow = plotWindows.windowFor(station, PlotType.Last72Hr);
		if(existingPlotWindow == null)
		{		
			openPlot(new PlotLast72hrTemp(station));
		}
		else
		{
			existingPlotWindow.toFront();
		}
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
		PlotWindow existingPlotWindow = plotWindows.windowFor(station, PlotType.Historical);
		if (existingPlotWindow == null)
		{
			openPlot(new PlotHistoricalTemp(station));
		}
		else
		{
			existingPlotWindow.toFront();
		}
	}
	
	
	// test please --------------------------------

	public void onOpen72HrTable (Station station){
		
		String stationName = station.getName();
		
		if (tables72Hr.search(stationName) == false){
			
		}else{
			tables72Hr.toFront(stationName);	
		}
	}
	
	// test please -------------------------------
	
	public void onOpenYearlyTable (Station station){
		
		String stationName = station.getName();
		
		if (tablesYearlyData.search(stationName) == false){
			
		}else{
			tablesYearlyData.toFront(stationName);	
		}
	}
	
	
	
}