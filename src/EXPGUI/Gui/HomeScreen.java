package EXPGUI.Gui;

/**
 * Created by Pavel Nikolaev on 13/03/2016.
 */

import EXPGUI.Layouts.GuiElements.GuiEffects;
import EXPGUI.Layouts.LayoutLarge;
import EXPGUI.Layouts.LayoutMedLow;
import EXPGUI.Layouts.LayoutSmall;
import EXPGUI.Layouts.layoutMedHigh;
import javafx.geometry.Rectangle2D;
import javafx.scene.*;
import javafx.scene.paint.Color;
import javafx.stage.*;


public class HomeScreen {

    // setting all the variables to be used in the creation of the gui

    private static Scene SCENE1;
    private static double xPos, yPos;
    private static Stage WINDOW;
    private static String size;

    public static void display(Stage window) {


        // instantiating a utilities class object
        // for performing housekeeping functions

        WINDOW = window;

        //getting the size of the screen the program is being run on.
        Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();
        double screenWidth2 = screenSize.getWidth();
        double screenHeight = screenSize.getHeight();


        // display a different layout depending on the screen size
        int screenWidth = (int) screenSize.getWidth();
        int switchCase = 0;

        //-----------------------------------------------------------------------//

        if (screenWidth > 1919) {
            switchCase = 1;
        } else if (screenWidth > 1439) {
            switchCase = 2;
        } else if (screenWidth > 1279) {
            switchCase = 3;
        } else if (screenWidth > 1023) {
            switchCase = 4;
        }

        //-----------------------------------------------------------------------//
        // slowly increase window size to specified dimensions and over specified amount of time

        switch (switchCase) {

            case 2:
                LayoutLarge layoutLarge = new LayoutLarge();
                SCENE1 = layoutLarge.returnSceneLarge();
                //layoutLarge.populateList();
                size = "L";
               GuiEffects.resizeWindowIncrease(WINDOW, 1320, 740,screenWidth2,screenHeight);
                break;

            case 3:layoutMedHigh layoutMedHigh = new layoutMedHigh();
                SCENE1 = layoutMedHigh.returnSceneMedHigh();
                size = "MH";
                GuiEffects.resizeWindowIncrease(WINDOW, 1260, 680,screenWidth2,screenHeight);
                break;
            case 1:
                LayoutMedLow layoutMedLow = new LayoutMedLow();
                SCENE1 = layoutMedLow.returnSceneMedLow();
                size = "ML";
                GuiEffects.resizeWindowIncrease(WINDOW, 1100, 550,screenWidth2,screenHeight);
                break;
            case 4:
                SCENE1 = LayoutSmall.returnSceneSmall();
                size = "S";
                GuiEffects.resizeWindowIncrease(WINDOW, 900, 500,screenWidth2,screenHeight);
                break;
            default:
                SCENE1 = LayoutSmall.returnSceneSmall();
                size = "S";
                GuiEffects.resizeWindowIncrease(WINDOW,900,500,screenWidth2,screenHeight);
                break;
        }

        //-----------------------------------------------------------------------//
        // setting the the moving background image
//        backgroundImageView = new ImageView(getClass().getResource( "background.jpg").toExternalForm());
//       // rootPane.getChildren().add( backgroundImageView);
//        StackPane.setMargin(backgroundImageView,new Insets(0,0,0,0));
//        backgroundImageView.toBack();
//        backgroundImageView.setOpacity(0);
//
//
//        // a scale transition to give the illusion that the background image is moving
//        ScaleTransition scaleTransition1 = new ScaleTransition(Duration.seconds(20), backgroundImageView);
//        scaleTransition1.setByX(0.5f);
//        scaleTransition1.setByY(0.5f);
//        scaleTransition1.setCycleCount(Animation.INDEFINITE);
//        scaleTransition1.setAutoReverse(true);
//        scaleTransition1.play();

        // WindowResizeListener resizer = new WindowResizeListener(SCENE1);
        //resizer.setResizeListener();

        SCENE1.setFill(Color.TRANSPARENT);
        dragWindow(SCENE1);
        dragWindow2(SCENE1);
        WINDOW.setTitle("Home");
        WINDOW.setScene(SCENE1);
        Main.returnControl().populateScrollPane();

    }

    private static void dragWindow(Scene scene) {

        scene.setOnMousePressed(e -> {

            xPos = WINDOW.getX() - e.getScreenX();
            yPos = WINDOW.getY() - e.getScreenY();

        });

        scene.setOnMouseDragged(e -> {
            WINDOW.setX(e.getScreenX() + xPos);
            WINDOW.setY(e.getScreenY() + yPos);
        });
    }

    private static void dragWindow2(Scene scene) {

        // this gives the ability for the user to drag the window
        // when their mouse in certain locations
        // eg the background of the app or the toolbar
        scene.lookup("#toolbar").setOnMousePressed(e -> {
            xPos = WINDOW.getX() - e.getScreenX();
            yPos = WINDOW.getY() - e.getScreenY();
        });

        scene.lookup("#toolbar").setOnMouseDragged(e -> {
            WINDOW.setX(e.getScreenX() + xPos);
            WINDOW.setY(e.getScreenY() + yPos);
        });

    }

    public static Scene getScene(){
        return SCENE1;
    }
    public static String getSize(){
        return size;
    }

    /*
    // creating layout for larger screens
    public Scene setSceneLarge() {

        // set root pane which contains all elements
        // a stack pane is used as it is easy to do
        // absolute positioning and can be styled easily
        rootPane = new StackPane();

        // create a stackpane for the graph area
        plotPane = new StackPane();

        // create the plotRect which will be used to shape and style
        // the plotPane
        plotRect = new Rectangle(800, 400);

        // upon startup, set the linechart as empty
        // when the user clicks on a station, the relevant info will come up
        weatherPlot = new LineChart<Number, Number>(new NumberAxis(), new NumberAxis());

        //styling and setting dimensions
        weatherPlot.setMaxSize(800, 350);
        weatherPlot.setOpacity(0);
        plotRect.setArcHeight(20);
        plotRect.setArcWidth(20);
        plotRect.setOpacity(0);
        plotRect.setStroke(Color.LIGHTSLATEGRAY);
        plotRect.setFill(Color.rgb(38, 38, 38));
        plotRect.setStrokeWidth(2);


        //add the graph/plot and the rectangle to the stackpane
        plotPane.getChildren().addAll(plotRect, weatherPlot);
        plotPane.setMaxSize(800, 350);

        // setting the alignment of the graph to the top center
        // of the stack pane
        StackPane.setAlignment(weatherPlot, Pos.TOP_CENTER);


        // Create table
        tablePane = new StackPane();
        dataTable = new TableView<>();
        dataTable.setOpacity(0.9);


        // user has to select a station for its data to be displayed first
        dataTable.setPlaceholder(new Label("Select a station"));
        tablePane.getChildren().addAll(dataTable);
        StackPane.setAlignment(dataTable, Pos.CENTER);
        tablePane.setMaxSize(800, 200);
        tablePane.setOpacity(0);

        // creating the area for where the user searches for
        // stations and can click on any station
        StackPane explorerPane = new StackPane();
        explorerRect = new Rectangle(430, 625);
        explorerRect.setArcHeight(20);
        explorerRect.setArcWidth(20);
        explorerRect.setOpacity(0);
        explorerRect.setStroke(Color.LIGHTSLATEGRAY);
        explorerRect.setFill(Color.rgb(38, 38, 38));
        explorerRect.setStrokeWidth(2);
        explorerPane.getChildren().addAll(explorerRect);
        explorerPane.setMaxSize(430, 635);

        // a tab pane for having multiple tabs
        explorerTabsPane = new TabPane();
        explorerTabsPane.setMaxSize(420, 615);
        explorerTabsPane.setOpacity(0);

        // tab for where all stations are listed
        Tab allStationsTab = new Tab();
        allStationsTab.setText("All stations");
        StackPane allStationsPane = new StackPane();
        allStationsPane.setPrefSize(420, 615);

        // a scroll pane for scrolling through the list of stations
        ScrollPane stationsScroll = new ScrollPane();
        stationsScroll.setPrefSize(420, 615);
        stationsScroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        stationsScroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        TextField searchBar = new TextField();

        // creating a search bar
        searchBar.setMaxSize(180, 25);
        searchBar.setPromptText("Search stations");
        searchBar.setOpacity(0.9);

        // setting padding and adding the scrollbar and searchbar
        // to the tab pane
        allStationsPane.getChildren().addAll(stationsScroll, searchBar);
        allStationsPane.setPadding(new Insets(0, 0, 10, 0));
        StackPane.setMargin(searchBar, new Insets(0, 210, 530, 0));
        StackPane.setAlignment(stationsScroll, Pos.CENTER);

        // getting the list of stations in the form of
        // a listNode object
        StationsList list = new StationsList(this);
        VBox content = list.returnList();
        stationsScroll.setContent(content);
        stationsScroll.setFitToWidth(true);
        stationsScroll.setFitToHeight(true);
        allStationsTab.setContent(allStationsPane);


        //------------------------------------------------------------------------------------------//
        //call the updateList() in Stationslist.java

        searchBar.setOnKeyPressed(e -> {

            //  final VBox updatedContent = list.updateList();
            //  stationsScroll.setContent(updatedContent);

        });
        //------------------------------------------------------------------------------------------//


        //----------------------------------------------------//
        //need to implement this for the 3 larger screen sizes
        Tab favsTab = new Tab();
        favsTab.setText("Favourites");
        StackPane paneTab2 = new StackPane();
        allStationsTab.setClosable(false);
        favsTab.setClosable(false);
        //----------------------------------------------------//

        explorerTabsPane.getTabs().addAll(allStationsTab, favsTab);
        explorerTabsPane.setTabMinWidth(180);
        explorerTabsPane.setTabMinHeight(35);

        explorerPane.getChildren().add(explorerTabsPane);
        StackPane.setMargin(explorerTabsPane, new Insets(2, 0, 0, 0));
        explorerPane.setPadding(new Insets(0, 0, 0, 0));

        //applying the css to the rectangles created previously
        plotRect.setId("rect");
        plotRect.applyCss();

        explorerRect.setId("rect3");
        explorerRect.applyCss();

        // creating a exit button for the toolbar
        exitButton = new Button("X");
        exitButton.setId("exit");
        exitButton.setMaxSize(50, 25);

        // apply a different css style when user hovers over
        // the button
        exitButton.setOnMouseEntered(e -> exitButton.setId("exit-h"));
        exitButton.setOnMouseExited(e -> exitButton.setId("exit"));
        exitButton.setOnMousePressed(e -> System.exit(1));

        //create a toolbar
        toolBar = new ToolBar(exitButton);
        toolBar.setMaxSize(1350, 35);
        toolBar.setOpacity(0);

        // enable window dragging
        // on the root and toolbar
        // via method defined previously
        dragWindow(rootPane);
        dragWindow2(toolBar);

        // button for creating a graph in a new window
        exportGraph = new Button("Open Graph");
        exportGraph.setMinSize(150, 25);
        exportGraph.setId("exportbutton");
        exportGraph.toFront();
        exportGraph.setOpacity(0);

        //set different css styles when user hovers over button
        exportGraph.setOnMouseEntered(e -> exportGraph.getStyleClass().add("export-button-bright"));
        exportGraph.setOnMouseExited(e -> exportGraph.getStyleClass().remove("export-button-bright"));

        exportGraph.setOnMousePressed(e -> {
            // not functional yet
        });

        // currently not in use
        exportGraph.setOnMouseReleased(e -> exportGraph.getStyleClass().remove("export-button-press"));


        // positioning the objects within the rootPane by using margins
        StackPane.setMargin(exportGraph, new Insets(140, 0, 0, 1045));
        StackPane.setMargin(plotPane, new Insets(0, 0, 200, 450));
        StackPane.setMargin(tablePane, new Insets(455, 0, 0, 450));
        StackPane.setMargin(explorerPane, new Insets(30, 830, 0, 0));
        StackPane.setAlignment(toolBar, Pos.TOP_CENTER);

        // set default alignment
        rootPane.setAlignment(Pos.CENTER);

        // adding all elements to be displayed
        rootPane.getChildren().addAll(toolBar, plotPane, tablePane, explorerPane, exportGraph);

        // set the size of the current scene
        Scene scene = new Scene(rootPane, 1320, 740);
        scene.setFill(Color.TRANSPARENT);

        Utilities util = new Utilities();
        util.getCss(scene);

        return scene;
    }

    public Scene setSceneMedHigh() {

        // set the exact same layout
        // just for a smaller screen width.
        // each of these will later be modular


        // set root pane which contains all elements
        // a stack pane is used as it is easy to do
        // absolute positioning and can be styled easily
        rootPane = new StackPane();
        // Create Plot

        // create a stackpane for the graph area
        plotPane = new StackPane();

        // create the plotRect which will be used to shape and style
        // the plotPane
        plotRect = new Rectangle(730, 350);


        // upon startup, set the linechart as empty
        // when the user clicks on a station, the relevant info will come up
        weatherPlot = new LineChart<Number, Number>(new NumberAxis(), new NumberAxis());

        //styling and setting dimensions
        weatherPlot.setMaxSize(730, 300);
        weatherPlot.setOpacity(0);
        plotRect.setArcHeight(20);
        plotRect.setArcWidth(20);
        plotRect.setOpacity(0);
        plotRect.setStroke(Color.LIGHTSLATEGRAY);
        plotRect.setFill(Color.rgb(38, 38, 38));
        plotRect.setStrokeWidth(2);

        //add all children to the plotRect
        //and set dimensions/alignment
        plotPane.getChildren().addAll(plotRect, weatherPlot);
        plotPane.setMaxSize(730, 300);
        StackPane.setAlignment(weatherPlot, Pos.TOP_CENTER);
        plotPane.setOpacity(0.97);


        // Create table and set as empty on startup until user clicks on a station
        tablePane = new StackPane();
        dataTable = new TableView<>();
        dataTable.setOpacity(0.9);
        dataTable.setPlaceholder(new Label("Select a station"));


        tablePane.getChildren().addAll(dataTable);
        StackPane.setAlignment(dataTable, Pos.CENTER);
        tablePane.setMaxSize(730, 200);
        tablePane.setOpacity(0);

        // creating the area for where the user searches for
        // stations and can click on any station
        StackPane explorerPane = new StackPane();
        explorerRect = new Rectangle(430, 580);
        explorerRect.setArcHeight(20);
        explorerRect.setArcWidth(20);
        explorerRect.setOpacity(0);
        explorerRect.setStroke(Color.LIGHTSLATEGRAY);
        explorerRect.setFill(Color.rgb(38, 38, 38));
        explorerRect.setStrokeWidth(2);
        explorerPane.getChildren().addAll(explorerRect);
        explorerPane.setMaxSize(430, 580);

        explorerTabsPane = new TabPane();
        explorerTabsPane.setMaxSize(420, 580);
        explorerTabsPane.setOpacity(0);

        //creating a tabpane so th user can alternate between
        // a list of all stations and favourited stations;
        Tab allStationsTab = new Tab();
        allStationsTab.setText("All stations");
        StackPane allStationsPane = new StackPane();
        allStationsPane.setPrefSize(420, 580);


        // creating a scrol pane for scrolling through the stations
        ScrollPane stationsScroll = new ScrollPane();
        stationsScroll.setPrefSize(420, 580);

        //never show the horizontal scroll bar and alwasy show the vertical one
        stationsScroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        stationsScroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        //creating search bar
        TextField searchBar = new TextField();
        searchBar.setMaxSize(180, 25);
        searchBar.setPromptText("Search stations");
        searchBar.setOpacity(0.9);

        //add the scroller for stations and search bar to the tabPane
        allStationsPane.getChildren().addAll(stationsScroll, searchBar);
        allStationsPane.setPadding(new Insets(0, 0, 10, 0));
        StackPane.setMargin(searchBar, new Insets(0, 210, 490, 0));
        StackPane.setAlignment(stationsScroll, Pos.CENTER);

        //set the content of the stationsScroller
        StationsList list = new StationsList(this);
        VBox content = list.returnList();

        stationsScroll.setContent(content);
        stationsScroll.setFitToWidth(true);
        stationsScroll.setFitToHeight(true);
        allStationsTab.setContent(allStationsPane);


        //------------------------------------------------------------------------------------------//
        //call the updateList() in Stationslist.java

        searchBar.setOnKeyPressed(e -> {

            //  final VBox updatedContent = list.updateList();
            //  stationsScroll.setContent(updatedContent);

        });
        //------------------------------------------------------------------------------------------//

        //----------------------------------------------------//
        Tab favsTab = new Tab();
        favsTab.setText("Favourites");
        StackPane paneTab2 = new StackPane();
        allStationsTab.setClosable(false);
        favsTab.setClosable(false);
        //----------------------------------------------------//


        // add all tabs to the tabPane
        explorerTabsPane.getTabs().addAll(allStationsTab, favsTab);
        explorerTabsPane.setTabMinWidth(180);
        explorerTabsPane.setTabMinHeight(35);

        explorerPane.getChildren().add(explorerTabsPane);
        StackPane.setMargin(explorerTabsPane, new Insets(2, 0, 0, 0));
        explorerPane.setPadding(new Insets(0, 0, 0, 0));

        //applying css to the various rectabgles used in styling
        plotRect.setId("rect");
        plotRect.applyCss();
        explorerRect.setId("rect3");
        explorerRect.applyCss();

        // creating an exit button
        exitButton = new Button("X");
        exitButton.setId("exit");
        exitButton.setMaxSize(50, 25);

        // applying different styling when user  hovers over buttons
        exitButton.setOnMouseEntered(e -> exitButton.setId("exit-h"));
        exitButton.setOnMouseExited(e -> exitButton.setId("exit"));

        //quit program
        exitButton.setOnMousePressed(e -> System.exit(1));

        // create toolbar with the exitButton
        toolBar = new ToolBar(exitButton);
        toolBar.setMaxSize(1260, 35);
        toolBar.setOpacity(0);

        //button for exporting the graph to a new window
        exportGraph = new Button("Open Graph");
        exportGraph.setMinSize(150, 25);
        exportGraph.setId("exportbutton");
        exportGraph.toFront();
        exportGraph.setOpacity(0);

        exportGraph.setOnMouseEntered(e -> exportGraph.getStyleClass().add("export-button-bright"));
        exportGraph.setOnMouseExited(e -> exportGraph.getStyleClass().remove("export-button-bright"));

        //giving the user the ability to drag the window
        dragWindow(rootPane);
        dragWindow2(toolBar);


        // positioning the gui elements by using margins
        StackPane.setMargin(exportGraph, new Insets(90, 0, 0, 985));
        StackPane.setMargin(plotPane, new Insets(0, 0, 190, 460));
        StackPane.setMargin(tablePane, new Insets(415, 0, 0, 460));
        StackPane.setMargin(explorerPane, new Insets(40, 760, 0, 0));
        StackPane.setAlignment(toolBar, Pos.TOP_CENTER);

        rootPane.setAlignment(Pos.CENTER);
        rootPane.getChildren().addAll(toolBar, plotPane, tablePane, explorerPane, exportGraph);

        // creating the scene with all the elements
        Scene scene = new Scene(rootPane, 1260, 680);
        scene.setFill(Color.TRANSPARENT);

        Utilities util = new Utilities();
        util.getCss(scene);

        return scene;

    }

    public Scene setSceneMedLow() {

        //exactly the same as previous two
        // just slightly smaller

        // in the future will create a responsive and fluid resizing module
        // in WindowResizeListener
        // that will let the user resize the window and when the window
        // size reaches a certain threshold, the gui will snap to the
        // appropriate layout

        rootPane = new StackPane();
        // Create Plot
        plotPane = new StackPane();
        plotRect = new Rectangle(630, 260);
        weatherPlot = new LineChart<Number, Number>(new NumberAxis(), new NumberAxis());
        weatherPlot.setMaxSize(630, 230);
        weatherPlot.setOpacity(0);
        plotRect.setArcHeight(20);
        plotRect.setArcWidth(20);
        plotRect.setOpacity(0);
        plotRect.setStroke(Color.LIGHTSLATEGRAY);
        plotRect.setFill(Color.rgb(38, 38, 38));
        plotRect.setStrokeWidth(2);
        plotPane.getChildren().addAll(plotRect, weatherPlot);
        plotPane.setMaxSize(630, 260);
        StackPane.setAlignment(weatherPlot, Pos.TOP_CENTER);
        plotPane.setOpacity(0.97);


        // Create table
        tablePane = new StackPane();
        dataTable = new TableView<>();
        dataTable.setPlaceholder(new Label("Select a station"));
        dataTable.setOpacity(0.9);

        tablePane.getChildren().addAll(dataTable);
        StackPane.setAlignment(dataTable, Pos.CENTER);
        tablePane.setMaxSize(630, 200);
        tablePane.setOpacity(0);


        StackPane explorerPane = new StackPane();
        explorerRect = new Rectangle(400, 475);
        explorerRect.setArcHeight(20);
        explorerRect.setArcWidth(20);
        explorerRect.setOpacity(0);
        explorerRect.setStroke(Color.LIGHTSLATEGRAY);
        explorerRect.setFill(Color.rgb(38, 38, 38));
        explorerRect.setStrokeWidth(2);
        explorerPane.getChildren().addAll(explorerRect);
        explorerPane.setMaxSize(400, 475);

        explorerTabsPane = new TabPane();
        explorerTabsPane.setMaxSize(400, 475);
        explorerTabsPane.setOpacity(0);


        Tab allStationsTab = new Tab();
        allStationsTab.setText("All stations");
        StackPane allStationsPane = new StackPane();
        allStationsPane.setPrefSize(390, 475);

        ScrollPane stationsScroll = new ScrollPane();
        stationsScroll.setPrefSize(350, 475);
        stationsScroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        stationsScroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        TextField searchBar = new TextField();

        searchBar.setMaxSize(180, 25);
        searchBar.setPromptText("Search stations");
        searchBar.setOpacity(0.9);

        allStationsPane.getChildren().addAll(stationsScroll, searchBar);
        allStationsPane.setPadding(new Insets(0, 0, 10, 0));
        StackPane.setMargin(searchBar, new Insets(0, 190, 390, 0));
        StackPane.setAlignment(stationsScroll, Pos.CENTER);


        StationsList list = new StationsList(this);
        VBox content = list.returnList();
        stationsScroll.setContent(content);
        stationsScroll.setFitToWidth(true);
        stationsScroll.setFitToHeight(true);
        allStationsTab.setContent(allStationsPane);

        //------------------------------------------------------------------------------------------//
        //call the updateList() in Stationslist.java

        searchBar.setOnKeyPressed(e -> {

            //  final VBox updatedContent = list.updateList();
            //  stationsScroll.setContent(updatedContent);

        });
        //------------------------------------------------------------------------------------------//

        //----------------------------------------------------//
        Tab favsTab = new Tab();
        favsTab.setText("Favourites");
        StackPane paneTab2 = new StackPane();
        allStationsTab.setClosable(false);
        favsTab.setClosable(false);
        //----------------------------------------------------//

        explorerTabsPane.getTabs().addAll(allStationsTab, favsTab);
        explorerTabsPane.setTabMinWidth(165);
        explorerTabsPane.setTabMinHeight(35);

        explorerPane.getChildren().add(explorerTabsPane);
        StackPane.setMargin(explorerTabsPane, new Insets(2, 0, 0, 0));
        explorerPane.setPadding(new Insets(0, 0, 0, 0));

        plotRect.setId("rect");
        plotRect.applyCss();

        explorerRect.setId("rect3");
        explorerRect.applyCss();

        exitButton = new Button("X");
        exitButton.setId("exit");
        exitButton.setMaxSize(50, 25);
        exitButton.toFront();

        exitButton.setOnMouseEntered(e -> exitButton.setId("exit-h"));
        exitButton.setOnMouseExited(e -> exitButton.setId("exit"));

        exitButton.setOnMousePressed(e -> System.exit(1));

        toolBar = new ToolBar(exitButton);

        toolBar.setMaxSize(1260, 35);
        toolBar.setOpacity(0);

        dragWindow(rootPane);
        dragWindow2(toolBar);

        exportGraph = new Button("Open Graph");
        exportGraph.setMinSize(130, 25);
        exportGraph.setId("exportbuttonsmall");
        exportGraph.toFront();
        exportGraph.setOpacity(0);

        exportGraph.setOnMouseEntered(e -> exportGraph.getStyleClass().add("export-button-bright"));
        exportGraph.setOnMouseExited(e -> exportGraph.getStyleClass().remove("export-button-bright"));

        StackPane.setMargin(exportGraph, new Insets(35, 0, 0, 870));
        StackPane.setMargin(plotPane, new Insets(0, 0, 180, 430));
        StackPane.setMargin(tablePane, new Insets(310, 0, 0, 430));
        StackPane.setMargin(explorerPane, new Insets(40, 660, 0, 0));
        StackPane.setAlignment(toolBar, Pos.TOP_CENTER);

        rootPane.setAlignment(Pos.CENTER);
        rootPane.getChildren().addAll(toolBar, plotPane, tablePane, explorerPane, exportGraph);


        Scene scene = new Scene(rootPane, 1100, 550);
        scene.setFill(Color.TRANSPARENT);

        Utilities util = new Utilities();
        util.getCss(scene);

        return scene;


    }


    //this layout is different, and isnt fully functional
    // will be complete for extremely small screen sizes
    // in part 2

    public Scene setSceneLow() {

        // cre
        rootPane = new StackPane();
        // Create Plot
        plotPane = new StackPane();
        plotRect = new Rectangle(710, 350);
        Rectangle clipRect = new Rectangle(712, 352);
        clipRect.setArcHeight(20);
        clipRect.setArcWidth(20);
        plotPane.setClip(clipRect);

        weatherPlot = new LineChart<Number, Number>(new NumberAxis(), new NumberAxis());
        weatherPlot.setMaxSize(710, 350);
        weatherPlot.setOpacity(0);
        plotRect.setArcHeight(20);
        plotRect.setArcWidth(20);
        plotRect.setOpacity(0);
        plotRect.setStroke(Color.LIGHTSLATEGRAY);
        plotRect.setFill(Color.rgb(38, 38, 38));
        plotRect.setStrokeWidth(2);
        plotPane.getChildren().addAll(plotRect, weatherPlot);
        plotPane.setMaxSize(710, 350);

        StackPane.setAlignment(weatherPlot, Pos.TOP_CENTER);
        StackPane.setAlignment(plotPane, Pos.TOP_CENTER);
        StackPane.setMargin(weatherPlot, new Insets(0, 15, 0, 0));

        exitButton = new Button("X");
        exitButton.setId("exit");
        exitButton.setMaxSize(50, 25);


        //applying different styles when user hovers over button
        exitButton.setOnMouseEntered(e -> exitButton.setId("exit-h"));
        exitButton.setOnMouseExited(e -> exitButton.setId("exit"));
        exitButton.setOnMousePressed(e -> System.exit(1));

        // creating triangular buttons on either side iof the graph
        // by creating a polygon and passing it to the button's
        // set shape method

        Polygon poly, poly2;
        poly = new Polygon();
        poly.getPoints().addAll(new Double[]{
                0.0, 0.0,
                20.0, -28.0,
                20.0, 28.0});

        poly2 = new Polygon();
        poly2.getPoints().addAll(new Double[]{
                -20.0, -28.0,
                0.0, 0.0,
                -20.0, 28.0});

        // setting button ID for styling purposes
        buttonLeft = new Button();
        buttonLeft.setId("t1");
        buttonLeft.setShape(poly);

        buttonRight = new Button();
        buttonRight.setId("t2");
        buttonRight.setShape(poly2);


        //creating separators that separate two buttons in the
        Separator sp = new Separator(Orientation.VERTICAL);
        sp.setOpacity(0);
        sp.setMinWidth(1);

        Separator sp2 = new Separator(Orientation.VERTICAL);
        sp2.setOpacity(0);
        sp2.setMinWidth(1);


        //aplying different style when user hovers over buttons
        buttonLeft.setOnMouseEntered(e -> buttonLeft.setId("button-hover-t"));
        buttonLeft.setOnMouseExited(e -> buttonLeft.setId("t1"));

        buttonRight.setOnMouseEntered(e -> buttonRight.setId("t2-hover"));
        buttonRight.setOnMouseExited(e -> buttonRight.setId("t2"));


        // pushes buttons to the front of the scene
        buttonLeft.toFront();
        buttonRight.toFront();

        // remove default styling in main.css
        buttonLeft.getStyleClass().remove("button");
        buttonRight.getStyleClass().remove("button");

        // setting initial opacity as 0 so elements fade in
        buttonLeft.setOpacity(0);
        buttonRight.setOpacity(0);

        // creating the export graph button for exporting graph
        // to another window
        // not functional

        exportGraph = new Button("Open Graph");
        exportGraph.setMinSize(180, 35);
        exportGraph.toFront();
        exportGraph.setOpacity(0);

        // introducing a show table button
        // as by default, the UI only shows the graph
        // since for this layout, there isnt enough space

        showTable = new Button("View data table");
        showTable.setMinSize(180, 35);
        showTable.toFront();
        showTable.setOpacity(0);


        // creating a burger bar button, which will later
        // open a sliding menu
        menu = new Button();
        menu.setId("menubutton");
        menu.setMaxSize(50, 25);
        menu.toFront();

        // applying different style when user hovers over button
        menu.setOnMouseEntered(e -> menu.setId("menu-h"));
        menu.setOnMouseExited(e -> menu.setId("menubutton"));

        // creating a toolbar with two buttons separated by a separator object
        toolBar = new ToolBar(exitButton, sp, menu);
        toolBar.setMaxSize(950, 35);
        toolBar.setOpacity(0);


        // applying different style when user hovers over button
        exportGraph.setOnMouseEntered(e -> exportGraph.getStyleClass().add("export-button-bright"));
        exportGraph.setOnMouseExited(e -> exportGraph.getStyleClass().remove("export-button-bright"));

        showTable.setOnMouseEntered(e -> showTable.getStyleClass().add("export-button-bright"));
        showTable.setOnMouseExited(e -> showTable.getStyleClass().remove("export-button-bright"));


        // experimenting with sliding graphs off the window and fading in a new one
        // not functional
        buttonRight.setOnMouseClicked(e -> {

            FadeTransition fT1 = new FadeTransition(Duration.millis(300), weatherPlot);
            fT1.setFromValue(1.0);
            fT1.setToValue(0.0);
            fT1.play();

            Timeline slideRight = new Timeline();
            slideRight.setCycleCount(1);
            final KeyValue key1 = new KeyValue(weatherPlot.translateXProperty(), 800);
            final KeyFrame kfDwn = new KeyFrame(Duration.millis(400), key1);
            slideRight.getKeyFrames().add(kfDwn);
            slideRight.play();

        });


        // experimenting with sliding graphs off the window and fading in a new one
        // not functional
        buttonLeft.setOnMouseClicked(e -> {

            FadeTransition fT1 = new FadeTransition(Duration.millis(200), weatherPlot);
            fT1.setFromValue(1.0);
            fT1.setToValue(0.0);
            fT1.play();

            Timeline slideRight = new Timeline();
            slideRight.setCycleCount(1);
            final KeyValue key1 = new KeyValue(weatherPlot.translateXProperty(), -800);
            final KeyFrame kfDwn = new KeyFrame(Duration.millis(400), key1);
            slideRight.getKeyFrames().add(kfDwn);
            slideRight.play();
        });

        // giving the user the ability to drag the window
        dragWindow(rootPane);
        dragWindow2(toolBar);

        // positioning elements
        rootPane.getChildren().addAll(toolBar, plotPane, buttonLeft, buttonRight, exportGraph, showTable);
        StackPane.setMargin(toolBar, new Insets(0, 0, 456, 0));
        StackPane.setMargin(plotPane, new Insets(50, 0, 0, 0));
        StackPane.setMargin(buttonLeft, new Insets(0, 785, 35, 0));
        StackPane.setMargin(buttonRight, new Insets(0, 0, 35, 785));
        StackPane.setMargin(exportGraph, new Insets(370, 200, 0, 0));
        StackPane.setMargin(showTable, new Insets(370, 0, 0, 200));

        Scene scene = new Scene(rootPane, 1320, 740);
        scene.setFill(Color.TRANSPARENT);

        Utilities util = new Utilities();
        util.getCss(scene);

        return scene;

    }

    // method for updating the chart
    // this method allows for the chart to be set regardless of what data is placed in the chart as that
    // should be processed outside of the main gui

    public void setChart(LineChart<Number, Number> chart) {
        plotPane.getChildren().remove(1);
        //remove current chart and set a new one
        // depending on the size of the layout


        if (size.equals("L")) {
            weatherPlot = chart;
            weatherPlot.setMaxSize(800, 350);
            plotPane.getChildren().add(weatherPlot);
            StackPane.setAlignment(weatherPlot, Pos.TOP_CENTER);
        } else if (size.equals("MH")) {
            weatherPlot = chart;
            weatherPlot.setMaxSize(730, 300);
            plotPane.getChildren().add(weatherPlot);
            StackPane.setAlignment(weatherPlot, Pos.TOP_CENTER);
        } else if (size.equals("ML")) {
            weatherPlot = chart;
            weatherPlot.setMaxSize(630, 230);
            plotPane.getChildren().add(weatherPlot);
            StackPane.setAlignment(weatherPlot, Pos.TOP_CENTER);
        } else {
            weatherPlot = chart;                //might change functionality slightly for small scene
            weatherPlot.setMaxSize(630, 220);
            plotPane.getChildren().add(weatherPlot);
            StackPane.setAlignment(weatherPlot, Pos.TOP_CENTER);
        }
    }

    // method for updating the table
    // this method allows for the table to be set irregardless of what data is placed in the table as that
    // should be processed outside of the main gui

    public void setTable(TableView<Station> table) {
        tablePane.getChildren().remove(0);
        dataTable = table;
        tablePane.getChildren().add(dataTable);
    }*/

    // returns the current size/ layout of the window


}
