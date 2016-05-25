package app;
import java.awt.Dimension;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import data.Bom;
import data.Station;
import data.StationList;
import gui.Alert;
import gui.SplashScreen;
import gui.home.HomeScreen;
import gui.home.HomeScreenInit;
import gui.plots.HistoricalTemp;
import gui.plots.Last72hrTemp;
import gui.plots.PlotBase;
import gui.plots.PlotType;
import gui.plots.PlotWindow;
import gui.plots.PlotWindows;
import gui.plots.Table72Hr;
import gui.plots.TableHistorical;
import gui.plots.ExperimentalPlot;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import user.Favourite;
import user.User;
import utilities.EasyTask;
import utilities.JavaFXSafeTask;
import utilities.MultipleInstanceLock;

public class Main extends Application 
	implements HomeScreen.EventInterface
	, PlotBase.EventInterface
{
	/* This service will finish tasks one at a time
	 * on another thread so that we don't block
	 * the gui thread */
    static ExecutorService exec = 
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
	static String appName = "SEPAT2016";
	static MultipleInstanceLock preventMultInstances = 
			new MultipleInstanceLock(appName);
    private boolean splashClosed = false;
	
	public static void main(String args[])
    {
        launch(args);
    }
	
	@Override
	public void start(Stage window) throws Exception 
	{
		this.window = window;
		if(preventMultInstances.isAppActive())
		{
			Alert close = new Alert("Stop", "An instance of this program is already open. "
					+ "\nPlease close the other instance or switch to it.",
					e -> System.exit(0));
		}
		else
		{
			startAppNotDuplicate();
		}
	}
	
	private void startAppNotDuplicate() 
	{
		window.setTitle(appName);
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

			// adding height and width changed listeners to the scene
           // to allow proportional resizing of gui elements within the scene/window
            scene.widthProperty().addListener(new ChangeListener<Number>() {
                @Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth) {
                    if(splashClosed) {
                        homeScreen.widthChanged((double) oldSceneWidth, (double) newSceneWidth);
                        //doesnt actually do anything yet
                    }
                }
            });
            scene.heightProperty().addListener(new ChangeListener<Number>() {
                @Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneHeight, Number newSceneHeight) {
                    if(splashClosed) {
                        homeScreen.heightChanged((double) oldSceneHeight, (double) newSceneHeight);
                    }
                }
            });

			splash.loadingUpdate("");
			splash.startClosing();
        });
        
        splash.setOnClosed(e -> 
        {
        	window.setScene(scene);
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
        	addPlotWindows(user.reconstructPlotWindows());
        	fillPlots();
        	plotWindows.showAll();
        	window.show();
            splashClosed = true;
            window.setMinWidth(935);
            window.setMinHeight(495);
            window.setMaxWidth(1200);
            window.setMaxHeight(600);
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
		plot.setEventHandler(this);
		fillPlot(plot);
	}
	
	void fillPlot(PlotBase plot)
	{
		EasyTask fetchDataTask = new EasyTask(() ->
		{
			plot.fetchData();
		});
		JavaFXSafeTask plotTask = new JavaFXSafeTask(() ->
		{
			plot.plotData();
		});
		queueTask(fetchDataTask);
		queueTask(plotTask);
	}
	
	void  fillPlots()
	{
		for (PlotWindow window : plotWindows)
		{
			PlotBase plot = window.getPlot();
			fillPlot(plot);
		}
	}
	
	void addPlotWindows(PlotWindows windows)
	{
		plotWindows.addAll(windows);
    	for(PlotWindow window : windows)
    	{
    		window.setOnCloseRequest(e -> {
    			plotWindows.remove(window);
    		});
    	}
    	for (PlotWindow win : windows)
    	{
    		win.getPlot().setEventHandler(this);
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
			homeScreen.addFavourite(newFav);
		}
	}
	
	@Override
	public void onOpen72TempPlot(Station station) 
	{
		PlotWindow existingPlotWindow = plotWindows.windowFor(station, Last72hrTemp.class);
		if(existingPlotWindow == null)
		{		
			openPlot(new Last72hrTemp(station));
		}
		else
		{
			existingPlotWindow.toFront();
		}
	}

	@Override
	public void onOpenHisTempPlot(Station station) 
	{
		PlotWindow existingPlotWindow = plotWindows.windowFor(station, HistoricalTemp.class);
		if (existingPlotWindow == null)
		{
			openPlot(new HistoricalTemp(station));
		}
		else
		{
			existingPlotWindow.toFront();
		}
	}
	
	@Override
	public void onExperimentalPlot(Station station) 
	{
		PlotWindow existingPlotWindow = plotWindows.windowFor(station, ExperimentalPlot.class);
		if (existingPlotWindow == null)
		{
			openPlot(new ExperimentalPlot(station));
		}
		else
		{
			existingPlotWindow.toFront();
		}
	}
	
	
	// test please --------------------------------
	
	@Override
	public void onOpen72HrTable (Station station)
	{
		PlotWindow existingPlotWindow = plotWindows.windowFor(station, Table72Hr.class);
		if (existingPlotWindow == null)
		{
			openPlot(new Table72Hr(station));
		}
		else
		{
			existingPlotWindow.toFront();
		}
	}
	
	// test please -------------------------------
	
	@Override
	public void onOpenHisTable (Station station)
	{
		PlotWindow existingPlotWindow = plotWindows.windowFor(station, TableHistorical.class);
		if (existingPlotWindow == null)
		{
			openPlot(new TableHistorical(station));
		}
		else
		{
			existingPlotWindow.toFront();
		}
	}

	@Override
	public void onCloseAllPlots(Station station) 
	{
		plotWindows.removePlotsFor(station);	
	}

	@Override
	public void onRefresh(PlotBase plot)
	{
		fillPlot(plot);
	}

	@Override
	public void onGoHome()
	{
		window.toFront();
	}

	@Override
	public void onCloseAllPlots()
	{
		plotWindows.closeAll();
		plotWindows.clear();
	}

	@Override
	public void onClearFavs()
	{
		
	}

	@Override
	public void onFavRemove(Favourite fav)
	{
		user.getFavs().remove(fav);
		homeScreen.removeFavourite(fav);
	}
}