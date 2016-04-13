package bomWeatherGui;

/**
 * Created by Pavel Nikolaev on 13/03/2016.
 */

import bomData.Station;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.*;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.*;
import javafx.util.Duration;

import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;



public class HomeScreen {

    private static Button exitButton,exportGraph;
    private static Scene SCENE1;
    private static StackPane rootPane,tablePane;
    private static  ImageView backgroundImageView;
    private static Rectangle plotRect,tableRect,explorerRect ,screenSize;
    private static TextField text;
    private static ToolBar toolBar;
    private static Stage WINDOW;
    double xPos,yPos;
    private static TabPane explorerTabsPane;
    private static LineChart<Number,Number> weatherPlot;
    private static TableView<String> dataTable;

    public HomeScreen(){

    }

    public void display(Stage window){


        WINDOW = window;
        Utilities util = new Utilities();


        Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();

        int screenWidth = (int)screenSize.getWidth();
        int switchCase = 0;

        //-----------------------------------------------------------------------//

        if(screenWidth > 1919){
            switchCase = 1;
        }
        else if(screenWidth > 1439){
            switchCase = 2;
        }
        else if( screenWidth > 1279){
            switchCase = 3;
        }
        else if(screenWidth > 1023){
            switchCase = 4;
        }

        //-----------------------------------------------------------------------//

        switch(switchCase){

            case 1:SCENE1 = setSceneLarge();
                util.resizeWindowIncrease(WINDOW,1320,740,2,4);
                break;

            case 2: SCENE1 = setSceneMedHigh();
                util.resizeWindowIncrease(WINDOW,1260,680,2,4);
                break;
            case 3: SCENE1 = setSceneMedLow();
                util.resizeWindowIncrease(WINDOW,1100,550,2,4);
                break;
            case 4: SCENE1= setSceneLow();
                util.resizeWindowIncrease(WINDOW,500,250,2,4);
                break;
            default:SCENE1= setSceneLow();
                break;
        }

        //-----------------------------------------------------------------------//

        backgroundImageView = new ImageView( getClass().getResource( "background.jpg").toExternalForm());

        rootPane.getChildren().add( backgroundImageView);
        StackPane.setMargin(backgroundImageView,new Insets(0,0,0,0));
        backgroundImageView.toBack();
        backgroundImageView.setOpacity(0);

        ScaleTransition scaleTransition1 = new ScaleTransition(Duration.seconds(20), backgroundImageView);
        scaleTransition1.setByX(0.5f);
        scaleTransition1.setByY(0.5f);
        scaleTransition1.setCycleCount(Animation.INDEFINITE);
        scaleTransition1.setAutoReverse(true);
        scaleTransition1.play();


        //leave these for the smaller screen sizes-----------------
        //which i will implement later------------------------
       // Polygon poly, poly2;

       // poly= new Polygon();
       // poly.getPoints().addAll(new Double[]{
           //     0.0, 0.0,
           //     20.0, -28.0,
           //     20.0, 28.0 });


       // poly2= new Polygon();
      //  poly2.getPoints().addAll(new Double[]{
        //        -20.0, -28.0,
          //      0.0, 0.0,
          //      -20.0, 28.0 });


       // BUTTON = new Button();
       // BUTTON.setId("t1");
       // BUTTON.setShape(poly);

       // BUTTON2 = new Button();
      //  BUTTON2.setId("t2");
       // BUTTON2.setShape(poly2);

       // exit = new Button("X");
       // exit.setId("exit");
      //  exit.setMaxSize(50,25);

       // minimize = new Button("O");
      //  minimize.setId("minimize");
      //  minimize.setMaxSize(50,25);


       //Separator sp = new Separator(Orientation.VERTICAL);
       // sp.setOpacity(0);
       // sp.setMinWidth(1);

       // Separator sp2 = new Separator(Orientation.VERTICAL);
       // sp2.setOpacity(0);
       // sp2.setMinWidth(1);


       // text = new TextField();
       // text.setPromptText("Search stations");

       // minimize.setOnMouseEntered(e -> minimize.setId("minimize-h"));
       // minimize.setOnMouseExited(e -> minimize.setId("minimize"));

       // BUTTON.setOnMouseEntered(e -> BUTTON.setId("button-hover-t"));
       // BUTTON.setOnMouseExited(e -> BUTTON.setId("t1"));

        //BUTTON2.setOnMouseEntered(e -> BUTTON2.setId("t2-hover"));
       // BUTTON2.setOnMouseExited(e -> BUTTON2.setId("t2"));

       // BUTTON.toFront();
       // BUTTON2.toFront();
       // BUTTON.getStyleClass().remove("button");
       // BUTTON2.getStyleClass().remove("button");
       // BUTTON.setOpacity(0);
       // BUTTON2.setOpacity(0);


        WindowResizeListener resizer = new WindowResizeListener(SCENE1);
        resizer.setResizeListener();

        WINDOW.setTitle("Home");
        WINDOW.setScene(SCENE1);
    }
    
    public StackPane createExplorerPane()
    {
    	return null;
    }


    public void fadeIn(){

        Rectangle clipRect = new Rectangle(WINDOW.getWidth(),WINDOW.getHeight());
        clipRect.setArcHeight(20.0);
        clipRect.setArcWidth(20.0);
        rootPane.setClip(clipRect);

      //  FadeTransition fT1
      //          = new FadeTransition(Duration.millis(1000), BUTTON);
      ////  fT1.setFromValue(0.0);
     ///   fT1.setToValue(1.0);
      //  fT1.setDelay(Duration.millis(500));
      //  fT1.play();

        FadeTransition fT2
                = new FadeTransition(Duration.millis(1000), plotRect);
        fT2.setFromValue(0.0);
        fT2.setToValue(1.0);
        fT2.setDelay(Duration.millis(500));
        fT2.play();

      //  FadeTransition fT3
         //       = new FadeTransition(Duration.millis(1000), BUTTON2);
      //  fT3.setFromValue(0.0);
       // fT3.setToValue(1.0);
      //  fT3.setDelay(Duration.millis(500));
        //fT3.play();


        FadeTransition fT4
                = new FadeTransition(Duration.millis(1000), backgroundImageView);
        fT4.setFromValue(0.0);
        fT4.setToValue(0.2);
        fT4.setDelay(Duration.millis(500));
        fT4.play();

              FadeTransition fT5
                = new FadeTransition(Duration.millis(1000), explorerRect);
        fT5.setFromValue(0.0);
        fT5.setToValue(0.97);
        fT5.setDelay(Duration.millis(500));
        fT5.play();

        FadeTransition fT6
                = new FadeTransition(Duration.millis(1000), toolBar);
        fT6.setFromValue(0.0);
        fT6.setToValue(1.0);
        fT6.setDelay(Duration.millis(500));
        fT6.play();

        FadeTransition fT7
                = new FadeTransition(Duration.millis(1000), explorerTabsPane);
        fT7.setFromValue(0.0);
        fT7.setToValue(0.97);
        fT7.setDelay(Duration.millis(500));
        fT7.play();

               FadeTransition fT8
                = new FadeTransition(Duration.millis(1000), weatherPlot);
        fT8.setFromValue(0.0);
        fT8.setToValue(0.97);
        fT8.setDelay(Duration.millis(500));
        fT8.play();

        FadeTransition fT9
                = new FadeTransition(Duration.millis(1000), tablePane);
        fT9.setFromValue(0.0);
        fT9.setToValue(0.97);
        fT9.setDelay(Duration.millis(500));
        fT9.play();

        FadeTransition fT10
                = new FadeTransition(Duration.millis(1000), exportGraph);
        fT10.setFromValue(0.0);
        fT10.setToValue(1.0);
        fT10.setDelay(Duration.millis(500));
        fT10.play();

    }

    public void dragWindow(StackPane pane){


        pane.setOnMousePressed(e -> {
            this.xPos = WINDOW.getX() - e.getScreenX();
            this.yPos = WINDOW.getY() - e.getScreenY();
        });

        pane.setOnMouseDragged(e -> {
            WINDOW.setX(e.getScreenX() + this.xPos);
            WINDOW.setY(e.getScreenY() + this.yPos);
        });

    }
    public void dragWindow2(ToolBar bar){

        bar.setOnMousePressed(e -> {
            this.xPos = WINDOW.getX() - e.getScreenX();
            this.yPos = WINDOW.getY() - e.getScreenY();
        });

        bar.setOnMouseDragged(e -> {
            WINDOW.setX(e.getScreenX() + this.xPos);
            WINDOW.setY(e.getScreenY() + this.yPos);
        });

    }

    public Scene setSceneLarge(){
        rootPane = new StackPane();
        // Create Plot
        StackPane plotPane = new StackPane();
        plotRect = new Rectangle(800,400);
        weatherPlot = new LineChart<Number,Number>(new NumberAxis(),new NumberAxis());
        weatherPlot.setMaxSize(800,350);
        weatherPlot.setOpacity(0);
        plotRect.setArcHeight(20);
        plotRect.setArcWidth(20);
        plotRect.setOpacity(0);
        plotRect.setStroke(Color.LIGHTSLATEGRAY);
        plotRect.setFill(Color.rgb(38,38,38));
        plotRect.setStrokeWidth(2);
        plotPane.getChildren().addAll(plotRect,weatherPlot);
        plotPane.setMaxSize(800,350);
        StackPane.setAlignment(weatherPlot,Pos.TOP_CENTER);

        // Create table
        tablePane = new StackPane();
        dataTable = new TableView<>();
        dataTable.setOpacity(0.9);
        tablePane.getChildren().addAll(dataTable);
        StackPane.setAlignment(dataTable,Pos.CENTER);
        tablePane.setMaxSize(800,200);
        tablePane.setOpacity(0);


        StackPane explorerPane = new StackPane();
        explorerRect = new Rectangle(430,625);
        explorerRect.setArcHeight(20);
        explorerRect.setArcWidth(20);
        explorerRect.setOpacity(0);
        explorerRect.setStroke(Color.LIGHTSLATEGRAY);
        explorerRect.setFill(Color.rgb(38, 38, 38));
        explorerRect.setStrokeWidth(2);
        explorerPane.getChildren().addAll(explorerRect);
        explorerPane.setMaxSize(430,635);

        explorerTabsPane = new TabPane();
        explorerTabsPane.setMaxSize(420, 615);
        explorerTabsPane.setOpacity(0);


        Tab allStationsTab = new Tab();
        allStationsTab.setText("All stations");
        StackPane allStationsPane = new StackPane();
        allStationsPane.setPrefSize(420,615);

        ScrollPane stationsScroll = new ScrollPane();
        stationsScroll.setPrefSize(420,615);
        stationsScroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        stationsScroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        TextField searchBar = new TextField();

        searchBar.setMaxSize(180,25);
        searchBar.setPromptText("Search stations");
        searchBar.setOpacity(0.9);

        allStationsPane.getChildren().addAll(stationsScroll, searchBar);
        allStationsPane.setPadding(new Insets(0,0,10,0));
        StackPane.setMargin(searchBar, new Insets(0,210,530,0));
        StackPane.setAlignment(stationsScroll,Pos.CENTER);

        StationsList list = new StationsList();
        VBox content = list.returnList();
        stationsScroll.setContent(content);
        stationsScroll.setFitToWidth(true);
        stationsScroll.setFitToHeight(true);

        allStationsTab.setContent(allStationsPane);

        //----------------------------------------------------//
        Tab favsTab = new Tab();
        favsTab.setText("Favourites");
        StackPane paneTab2 = new StackPane();
        allStationsTab.setClosable(false);
        favsTab.setClosable(false);
        //----------------------------------------------------//

        explorerTabsPane.getTabs().addAll(allStationsTab,favsTab);
        explorerTabsPane.setTabMinWidth(180);
        explorerTabsPane.setTabMinHeight(35);

        explorerPane.getChildren().add(explorerTabsPane);
        StackPane.setMargin(explorerTabsPane, new Insets(2, 0, 0, 0));
        explorerPane.setPadding(new Insets(0,0,0,0));

        plotRect.setId("rect");
        plotRect.applyCss();

        explorerRect.setId("rect3");
        explorerRect.applyCss();

        exitButton = new Button("X");
        exitButton.setId("exit");
        exitButton.setMaxSize(50,25);

        exitButton.setOnMouseEntered(e -> exitButton.setId("exit-h"));
        exitButton.setOnMouseExited(e -> exitButton.setId("exit"));
        exitButton.setOnMousePressed(e ->  System.exit(1));

        toolBar = new ToolBar(exitButton);
        toolBar.setMaxSize(1350,35);
        toolBar.setOpacity(0);

        dragWindow(rootPane);
        dragWindow2(toolBar);

        exportGraph = new Button("Open Graph");
        exportGraph.setMinSize(150, 25);
        exportGraph.setId("exportbutton");
        exportGraph.toFront();
        exportGraph.setOpacity(0);

        exportGraph.setOnMouseEntered(e -> exportGraph.getStyleClass().add("export-button-bright"));
        exportGraph.setOnMouseExited(e -> exportGraph.getStyleClass().remove("export-button-bright"));

        exportGraph.setOnMousePressed(e -> {



        });
        exportGraph.setOnMouseReleased(e -> exportGraph.getStyleClass().remove("export-button-press"));

        StackPane.setMargin(exportGraph,new Insets(140,0,0,1045));
        StackPane.setMargin(plotPane,new Insets(0,0,200,450));
        StackPane.setMargin(tablePane,new Insets(455,0,0,450));
        StackPane.setMargin(explorerPane,new Insets(30,830,0,0));
        StackPane.setAlignment(toolBar, Pos.TOP_CENTER);

        rootPane.setAlignment(Pos.CENTER);
        rootPane.getChildren().addAll(toolBar,plotPane,tablePane,explorerPane,exportGraph);


        Scene scene = new Scene(rootPane,1320,740);
        scene.setFill(Color.TRANSPARENT);

        Utilities util = new Utilities();
        util.getCss(scene);

        return scene;
    }

    public Scene setSceneMedHigh(){

        rootPane = new StackPane();
        // Create Plot
        StackPane plotPane = new StackPane();
        plotRect = new Rectangle(730,300);
        weatherPlot.setMaxSize(730,250);
        weatherPlot.setOpacity(0);
        plotRect.setArcHeight(20);
        plotRect.setArcWidth(20);
        plotRect.setOpacity(0);
        plotRect.setStroke(Color.LIGHTSLATEGRAY);
        plotRect.setFill(Color.rgb(38,38,38));
        plotRect.setStrokeWidth(2);
        plotPane.getChildren().addAll(plotRect,weatherPlot);
        plotPane.setMaxSize(730,300);
        StackPane.setAlignment(weatherPlot,Pos.TOP_CENTER);
        plotPane.setOpacity(0.97);


        // Create table
        tablePane = new StackPane();
        dataTable.setOpacity(0.9);

        tablePane.getChildren().addAll(dataTable);
        StackPane.setAlignment(dataTable,Pos.CENTER);
        tablePane.setMaxSize(730,250);
        tablePane.setOpacity(0);


        StackPane explorerPane = new StackPane();
        explorerRect = new Rectangle(430,580);
        explorerRect.setArcHeight(20);
        explorerRect.setArcWidth(20);
        explorerRect.setOpacity(0);
        explorerRect.setStroke(Color.LIGHTSLATEGRAY);
        explorerRect.setFill(Color.rgb(38, 38, 38));
        explorerRect.setStrokeWidth(2);
        explorerPane.getChildren().addAll(explorerRect);
        explorerPane.setMaxSize(430,580);

        explorerTabsPane = new TabPane();
        explorerTabsPane.setMaxSize(420, 580);
        explorerTabsPane.setOpacity(0);


        Tab allStationsTab = new Tab();
        allStationsTab.setText("All stations");
        StackPane allStationsPane = new StackPane();
        allStationsPane.setPrefSize(420,580);

        ScrollPane stationsScroll = new ScrollPane();
        stationsScroll.setPrefSize(420,580);
        stationsScroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        stationsScroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        TextField searchBar = new TextField();

        searchBar.setMaxSize(180,25);
        searchBar.setPromptText("Search stations");
        searchBar.setOpacity(0.9);

        allStationsPane.getChildren().addAll(stationsScroll, searchBar);
        allStationsPane.setPadding(new Insets(0,0,10,0));
        StackPane.setMargin(searchBar, new Insets(0,210,490,0));
        StackPane.setAlignment(stationsScroll,Pos.CENTER);

        StationsList list = new StationsList();
        VBox content = list.returnList();

        stationsScroll.setContent(content);
        stationsScroll.setFitToWidth(true);
        stationsScroll.setFitToHeight(true);


        allStationsTab.setContent(allStationsPane);

        //----------------------------------------------------//
        Tab favsTab = new Tab();
        favsTab.setText("Favourites");
        StackPane paneTab2 = new StackPane();
        allStationsTab.setClosable(false);
        favsTab.setClosable(false);
        //----------------------------------------------------//

        explorerTabsPane.getTabs().addAll(allStationsTab,favsTab);
        explorerTabsPane.setTabMinWidth(180);
        explorerTabsPane.setTabMinHeight(35);

        explorerPane.getChildren().add(explorerTabsPane);
        StackPane.setMargin(explorerTabsPane, new Insets(2, 0, 0, 0));
        explorerPane.setPadding(new Insets(0,0,0,0));

        plotRect.setId("rect");
        plotRect.applyCss();

        explorerRect.setId("rect3");
        explorerRect.applyCss();

        exitButton = new Button("X");
        exitButton.setId("exit");
        exitButton.setMaxSize(50,25);

        exitButton.setOnMouseEntered(e -> exitButton.setId("exit-h"));
        exitButton.setOnMouseExited(e -> exitButton.setId("exit"));

        exitButton.setOnMousePressed(e ->  System.exit(1));

        toolBar = new ToolBar(exitButton);

        toolBar.setMaxSize(1260,35);
        toolBar.setOpacity(0);

        dragWindow(rootPane);
        dragWindow2(toolBar);

        exportGraph = new Button("Open Graph");
        exportGraph.setMinSize(150, 25);
        exportGraph.setId("exportbutton");
        exportGraph.toFront();
        exportGraph.setOpacity(0);

        exportGraph.setOnMouseEntered(e -> exportGraph.getStyleClass().add("export-button-bright"));
        exportGraph.setOnMouseExited(e -> exportGraph.getStyleClass().remove("export-button-bright"));

        StackPane.setMargin(exportGraph,new Insets(0,0,0,985));
        StackPane.setMargin(plotPane,new Insets(0,0,240,460));
        StackPane.setMargin(tablePane,new Insets(365,0,0,460));
        StackPane.setMargin(explorerPane,new Insets(40,760,0,0));
        StackPane.setAlignment(toolBar, Pos.TOP_CENTER);

        rootPane.setAlignment(Pos.CENTER);
        rootPane.getChildren().addAll(toolBar,plotPane,tablePane,explorerPane,exportGraph);


        Scene scene = new Scene(rootPane,1260,680);
        scene.setFill(Color.TRANSPARENT);

        Utilities util = new Utilities();
        util.getCss(scene);

        return scene;

    }

    public Scene setSceneMedLow(){


        rootPane = new StackPane();
        // Create Plot
        StackPane plotPane = new StackPane();
        plotRect = new Rectangle(630,260);

      //  weatherPlot = WeatherPlot.getChart();

        weatherPlot.setMaxSize(630,220);
        weatherPlot.setOpacity(0);
        plotRect.setArcHeight(20);
        plotRect.setArcWidth(20);
        plotRect.setOpacity(0);
        plotRect.setStroke(Color.LIGHTSLATEGRAY);
        plotRect.setFill(Color.rgb(38,38,38));
        plotRect.setStrokeWidth(2);
        plotPane.getChildren().addAll(plotRect,weatherPlot);
        plotPane.setMaxSize(630,260);
        StackPane.setAlignment(weatherPlot,Pos.TOP_CENTER);
        plotPane.setOpacity(0.97);


        // Create table
        tablePane = new StackPane();

      //  dataTable = getTable(630,200);

        dataTable.setOpacity(0.9);

        tablePane.getChildren().addAll(dataTable);
        StackPane.setAlignment(dataTable,Pos.CENTER);
        tablePane.setMaxSize(630,200);
        tablePane.setOpacity(0);


        StackPane explorerPane = new StackPane();
        explorerRect = new Rectangle(400,475);
        explorerRect.setArcHeight(20);
        explorerRect.setArcWidth(20);
        explorerRect.setOpacity(0);
        explorerRect.setStroke(Color.LIGHTSLATEGRAY);
        explorerRect.setFill(Color.rgb(38, 38, 38));
        explorerRect.setStrokeWidth(2);
        explorerPane.getChildren().addAll(explorerRect);
        explorerPane.setMaxSize(400,475);

        explorerTabsPane = new TabPane();
        explorerTabsPane.setMaxSize(400, 475);
        explorerTabsPane.setOpacity(0);


        Tab allStationsTab = new Tab();
        allStationsTab.setText("All stations");
        StackPane allStationsPane = new StackPane();
        allStationsPane.setPrefSize(390,475);

        ScrollPane stationsScroll = new ScrollPane();
        stationsScroll.setPrefSize(350,475);
        stationsScroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        stationsScroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        TextField searchBar = new TextField();

        searchBar.setMaxSize(180,25);
        searchBar.setPromptText("Search stations");
        searchBar.setOpacity(0.9);

        allStationsPane.getChildren().addAll(stationsScroll, searchBar);
        allStationsPane.setPadding(new Insets(0,0,10,0));
        StackPane.setMargin(searchBar, new Insets(0,190,390,0));
        StackPane.setAlignment(stationsScroll,Pos.CENTER);


        StationsList list = new StationsList();
        VBox content = list.returnList();
        stationsScroll.setContent(content);
        stationsScroll.setFitToWidth(true);
        stationsScroll.setFitToHeight(true);

        allStationsTab.setContent(allStationsPane);

        //----------------------------------------------------//
        Tab favsTab = new Tab();
        favsTab.setText("Favourites");
        StackPane paneTab2 = new StackPane();
        allStationsTab.setClosable(false);
        favsTab.setClosable(false);
        //----------------------------------------------------//

        explorerTabsPane.getTabs().addAll(allStationsTab,favsTab);
        explorerTabsPane.setTabMinWidth(165);
        explorerTabsPane.setTabMinHeight(35);

        explorerPane.getChildren().add(explorerTabsPane);
        StackPane.setMargin(explorerTabsPane, new Insets(2, 0, 0, 0));
        explorerPane.setPadding(new Insets(0,0,0,0));

        plotRect.setId("rect");
        plotRect.applyCss();

        explorerRect.setId("rect3");
        explorerRect.applyCss();

        exitButton = new Button("X");
        exitButton.setId("exit");
        exitButton.setMaxSize(50,25);
        exitButton.toFront();

        exitButton.setOnMouseEntered(e -> exitButton.setId("exit-h"));
        exitButton.setOnMouseExited(e -> exitButton.setId("exit"));

        exitButton.setOnMousePressed(e ->  System.exit(1));

        toolBar = new ToolBar(exitButton);

        toolBar.setMaxSize(1260,35);
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

        StackPane.setMargin(exportGraph,new Insets(35,0,0,870));
        StackPane.setMargin(plotPane,new Insets(0,0,180,430));
        StackPane.setMargin(tablePane,new Insets(310,0,0,430));
        StackPane.setMargin(explorerPane,new Insets(40,660,0,0));
        StackPane.setAlignment(toolBar, Pos.TOP_CENTER);

        rootPane.setAlignment(Pos.CENTER);
        rootPane.getChildren().addAll(toolBar,plotPane,tablePane,explorerPane,exportGraph);


        Scene scene = new Scene(rootPane,1260,680);
        scene.setFill(Color.TRANSPARENT);

        Utilities util = new Utilities();
        util.getCss(scene);

        return scene;


    }

    public Scene setSceneLow(){

        return null;
    }

    public void setChart(LineChart<Number,Number> chart){
        weatherPlot = chart;
    }

    public void setTable(TableView<String> table){
        dataTable = table;
    }


   /* public void setEffects(Node node, double width, double height){

        Rectangle boxEffect = new Rectangle(width,height);
        boxEffect.setArcHeight(10.0);
        boxEffect.setArcWidth(10.0);

        boxEffect.setFill(Color.TRANSPARENT);
        boxEffect.setStrokeWidth(2);
        boxEffect.setStroke(Color.gray(1, 0.15));

        node.setOnMousePressed(e ->{

            ScaleTransition scaleTransition1 = new ScaleTransition(Duration.millis(800), boxEffect);
            scaleTransition1.setByX(0.1f);
            scaleTransition1.setByY(0.9f);
            scaleTransition1.setCycleCount(1);
            scaleTransition1.setAutoReverse(false);
            scaleTransition1.play();

        });
    }*/

}
