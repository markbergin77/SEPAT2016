package app;
import java.awt.Dimension;
import java.net.URL;
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
import gui.plots.PlotWindow;
import gui.plots.PlotWindows;
import gui.plots.Table72Hr;
import gui.plots.TableHistorical;
import gui.plots.ExperimentalPlot;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.apache.log4j.Logger;
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

	private static Logger logger = Logger.getLogger(Main.class);
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
    private double xPos,yPos;
	
	public static void main(String args[])
    {
        launch(args);
    }
	
	@Override
	public void start(Stage window) throws Exception 
	{
		logger.debug("------------Starting-------------");

		this.window = window;
		if(preventMultInstances.isAppActive())
		{
			Alert close = new Alert("Stop", "An instance of this program is already open. "
					+ "\nPlease close the other instance or switch to it.",
					e -> System.exit(0));
		}
		else
		{
            logger.debug("Calling Main::startAppNotDuplicate()");
			startAppNotDuplicate();
		}
	}
	
	private void startAppNotDuplicate() 
	{
        logger.debug("Starting Main::startAppNotDuplicate()");
		window.setTitle(appName);
        window.setOnCloseRequest(e -> onQuit());
        SplashScreen splash = new SplashScreen();

        EasyTask getStationsTask = new EasyTask(() ->
        { 
        	try {
        		/* Pass in splash so that this function can update
        		 * the splash screen's text when something changes.*/
			logger.debug("Calling BOM::getALLStations()");
			allStations = Bom.getAllStations(splash);
		} catch (UnknownHostException e) {
                /* TODO User might not be able to connect to BOM!
				 * Must put something on the splash screen,
				 * Use splash.loadingUpdate(String)
				 * maybe retry connecting in a loop */

			logger.fatal("Threw UnknownHostException :",e);
			splash.loadingUpdate("Error: couldn't connect. Please restart");
			return;

		} catch (Exception e) {
                logger.fatal("Threw Error :",e);
                splash.loadingUpdate("Something went wrong. Please restart");
			return;
		}
        	// Tricky: loadingUpdate actually does a runLater()
        	splash.loadingUpdate("Loading user");
			try {
                logger.debug("Calling User::LoadUser()");
				user = User.loadUser("data/user");
			} catch (Exception e1) {

                logger.warn("Couldn't load user :",e1);
				newUser = true;
				user = new User();
			}
            logger.debug("Initializing HomeScreen");
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

            logger.debug("Closing splash screen");
			splash.loadingUpdate("");
			splash.startClosing();
        });
        
        splash.setOnClosed(e -> 
        {
            logger.debug("Calling Main::dragWindow()");
            dragWindow(scene);

            logger.debug("Calling Main::getCss()");
            getCss(scene);

        	window.setScene(scene);
        	window.sizeToScene();
        	if(newUser)
        	{
                logger.debug("New user: centering window");
        		window.centerOnScreen();
        	}
        	else
        	{
                logger.debug("Existing user : setting to previous window location");
        		window.setX(user.getWindowX());
            	window.setY(user.getWindowY());
        	}
            logger.debug("Calling Main::reconstructPlotWindows()");
        	addPlotWindows(user.reconstructPlotWindows());

            logger.debug("Calling Main::fillPlots()");
        	fillPlots();

        	plotWindows.showAll();
        	window.show();
            splashClosed = true;

            window.setMinWidth(935);
            window.setMinHeight(495);
            window.setMaxWidth(1300);
            window.setMaxHeight(700);
        });
        
        /* Now start the chain of tasks, 
         * getStationsTask was the first.*/
        queueTask(getStationsTask);
	}

	void onQuit()
	{
        logger.debug("Starting Main::onQuit() ");
        logger.debug("Calling User::setMainWindowPosSave()");
		user.setMainWindowPosSave(window.getX(), window.getY());

        logger.debug("Calling User::storePlotWindows()");
		user.storePlotWindows(plotWindows);

        logger.debug("Calling User::saveUser()");
		User.saveUser(user, "data/user");
		System.exit(0);
	}
	
	private void queueTask(Task<?> task)
	{
		exec.submit(task);
	}
	
	void openPlot(PlotBase plot)
	{
		logger.debug("Starting Main::openPlot()");
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
        logger.debug("Starting Main::fillPlot()");
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
        logger.debug("Starting Main::fillPlots()");
		for (PlotWindow window : plotWindows)
		{
			PlotBase plot = window.getPlot();
			fillPlot(plot);
		}
	}
	
	void addPlotWindows(PlotWindows windows)
	{
        logger.debug("Starting Main::addPlotWindows()");
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
        logger.debug("Starting Main::onAddFav()");
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
        logger.debug("Starting Main::onOpen72TempPlot()");
		PlotWindow existingPlotWindow = plotWindows.windowFor(station, Last72hrTemp.class);
		if(existingPlotWindow == null)
		{
            logger.debug("Calling Main::openPlot()");
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
        logger.debug("Starting Main::onOpenHisTempPlot()");
		PlotWindow existingPlotWindow = plotWindows.windowFor(station, HistoricalTemp.class);
		if (existingPlotWindow == null)
		{
            logger.debug("Calling Main::openPlot()");
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
        logger.debug("Starting Main::onExperimentalPlot()");
        PlotWindow existingPlotWindow = plotWindows.windowFor(station, ExperimentalPlot.class);
		if (existingPlotWindow == null)
		{
            logger.debug("Calling Main::openPlot()");
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
        logger.debug("Starting Main::onOpen72HrTable()");
        PlotWindow existingPlotWindow = plotWindows.windowFor(station, Table72Hr.class);
		if (existingPlotWindow == null)
		{
            logger.debug("Calling Main::openPlot()");
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
        logger.debug("Starting Main::onOpenHisTable()");

        PlotWindow existingPlotWindow = plotWindows.windowFor(station, TableHistorical.class);
		if (existingPlotWindow == null)
		{
            logger.debug("Calling Main::openPlot()");
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
        logger.debug("Starting Main::onFavRemove()");
		user.getFavs().remove(fav);
		homeScreen.removeFavourite(fav);
	}

    public void dragWindow(Object obj) {

        logger.debug("Starting Main::dragWindow()");
        if (obj instanceof Scene) {
            ((Scene)obj).setOnMousePressed(e -> {
                this.xPos = window.getX() - e.getScreenX();
                this.yPos = window.getY() - e.getScreenY();

            });

            ((Scene)obj).setOnMouseDragged(e -> {
                window.setX(e.getScreenX() + this.xPos);
                window.setY(e.getScreenY() + this.yPos);
            });
        }
        //else if()......
    }
    public void getCss(Scene scene){

        logger.debug("starting Main::getCss()");
        try {
            URL url = this.getClass().getResource("main.css");
            if (url == null) {
                logger.fatal("Failed to load resource : main.css");
                Alert alert = new Alert("Error","Could not load resource : main.css ",event -> System.exit(-1));

            }
            String css = url.toExternalForm();
            scene.getStylesheets().add(css);
        }
        catch(Exception e){
            logger.fatal("Failed to load resource : main.css");
            Alert alert = new Alert("Error","Could not load resource : main.css ",event -> System.exit(-1));
        }

    }
}