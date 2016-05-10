package EXPGUI.Controller;


import EXPGUI.Controller.WindowManager.WindowManager;
import EXPGUI.Gui.HomeScreen;
import EXPGUI.Layouts.GuiElements.ExplorerPane.ScrollPaneCreator;
import EXPGUI.Layouts.GuiElements.GuiEffects;
import data.Bom;
import data.Station;
import data.StationList;
import guiPlots.PlotHistoricalTemp;
import guiPlots.TableHistorical;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import user.User;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

/**
 * Created by Pavel Nikolaev on 4/05/2016.
 */
public class Controller implements Initializable {

    private StationList stationList;
    private Scene scene;
    private Service<Void> thread;
    private WindowManager windowManager;
    private User user;

    public Controller(Scene scene) {
        this.scene = scene;
//        this.windowManager = new WindowManager(this);
    }

    public void displayPopUp(){

        thread = new Service<Void>() {

            @Override
            protected Task<Void> createTask() {
                return new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {

                        ((StackPane)scene.lookup("#datatablepane")).setOnMouseClicked(e -> {
                            System.out.println("hello1");
                        });

                        ((StackPane)scene.lookup("#rootpane")).setOnMouseClicked(e -> {
                            System.out.println("hello2");
                        });
                        ((TabPane)scene.lookup("#tabpane")).setOnMouseClicked(e -> {
                            System.out.println("hello3");
                        });
                        ((TableView)scene.lookup("#datatable")).setOnMouseClicked(e -> {
                            System.out.println("hello4");
                        });

                        return null;
                    }
                };
            }
        };

        thread.start();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void getAllStations() {

        thread = new Service<Void>() {

            @Override
            protected Task<Void> createTask() {
                return new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {
                        stationList = Bom.getAllStations();
                        return null;
                    }
                };
            }
        };

        thread.setOnSucceeded(e -> {
            Platform.runLater(() -> {
                GuiEffects.fadeOut(scene);
//                windowManager.loadAllWindows();
            });
        });
        thread.start();

    }

    public VBox populateScrollPane() {

        VBox scrollContent = new VBox(8);
        thread = new Service<Void>() {

            @Override
            protected Task<Void> createTask() {
                return new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {

                        for (Station station : stationList) {

                            Button button = new Button(station.getName() + ", " + station.getState());
                            button.getStyleClass().add("listbutton");

                            button.setOnMouseEntered(e -> button.getStyleClass().add("listbutton-h"));
                            button.setOnMouseExited(e -> button.getStyleClass().remove("listbutton-h"));

                            button.setOnMouseClicked(e -> {
                                updateGraph(station);
                                updateTable(station);
                            });

                            button.setPrefSize(340, 65);
                            scrollContent.getChildren().add(button);
                            scrollContent.setMargin(button, new Insets(10, 0, 0, 17));
                        }
                        scene = HomeScreen.getScene();
                        TimeUnit.SECONDS.sleep(1);
                        //displayPopUp();

                        Platform.runLater(() -> {
                            ((ScrollPaneCreator) (scene.lookup("#scrollpanecreator"))).setScroll(scrollContent);
                        });

                        return null;
                    }
                };
            }
        };

        thread.setOnSucceeded(event -> {
           // displayPopUp();
        });

        thread.start();
        return scrollContent;
    }

    public void updateTable(Station station) {

        thread = new Service<Void>() {

            @Override
            protected Task<Void> createTask() {
                return new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {

                        TableView dataTable = TableHistorical.createTable(station);
                        Platform.runLater(() -> {
                            ((StackPane) scene.lookup("#datatablepane")).getChildren().remove(0);
                            ((StackPane) scene.lookup("#datatablepane")).getChildren().add(dataTable);
                        });
                        return null;
                    }
                };
            }
        };

        thread.restart();

    }

    public void updateGraph(Station station) {

        thread = new Service<Void>() {

            @Override
            protected Task<Void> createTask() {
                return new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {

                        LineChart<String, Number> weatherPlot = PlotHistoricalTemp.getPlotHistorical(station);

                        Platform.runLater(() -> {
                            ((StackPane) scene.lookup("#graphpane")).getChildren().remove(1);
                            ((StackPane) scene.lookup("#graphpane")).getChildren().add(weatherPlot);
                        });

                        return null;
                    }
                };
            }
        };
        thread.start();
    }
    public void exit(){

        thread = new Service<Void>() {

            @Override
            protected Task<Void> createTask() {
                return new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {
//                        windowManager.saveAllWindows();
                        return null;
                    }
                };
            }
        };

        thread.start();
        thread.setOnSucceeded(e -> System.exit(1));

    }
}
