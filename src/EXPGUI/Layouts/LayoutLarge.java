package EXPGUI.Layouts;

import EXPGUI.Layouts.GuiElements.CustomToolBar.CustomToolBar;
import EXPGUI.Layouts.GuiElements.ExplorerPane.ExplorerPane;
import EXPGUI.Layouts.GuiElements.ExplorerPane.ScrollPaneCreator;
import EXPGUI.Layouts.GuiElements.GraphPane.GraphPane;
import EXPGUI.Layouts.GuiElements.TablePane.TablePane;
import EXPGUI.Gui.Utilities;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 * Created by Pavel Nikolaev on 4/05/2016.
 */
public class LayoutLarge {

    private StackPane rootPane,graphPane,dataTable,explorerPane;

    public Scene returnSceneLarge() {

        GraphPane gP = new GraphPane();
        TablePane tP = new TablePane();
        ExplorerPane eP = new ExplorerPane();

        gP.setId("gp");
        tP.setId("tp");
        eP.setId("ep");

        this.rootPane = new StackPane();
        rootPane.setId("rootpane");

        this.graphPane = new StackPane();
        graphPane = gP.getGraphLarge(graphPane);
        graphPane.setId("graphpane");
        graphPane.setOpacity(0);

        this.dataTable = new StackPane();
        dataTable = tP.getTable(dataTable, 1);
        dataTable.setId("datatablepane");
        dataTable.setOpacity(0);

        this.explorerPane = new StackPane();
        explorerPane = eP.getExplorerLarge(explorerPane);
        explorerPane.setId("explorerpane");
        explorerPane.setOpacity(0);

        ToolBar toolBar = new ToolBar();
        toolBar = CustomToolBar.returnToolbar(toolBar, 1);
        toolBar.setId("toolbar");
        toolBar.setOpacity(0);

        // button for creating a graph in a new window
//        Button exportGraph = new Button("Open Graph");
//        exportGraph.setMinSize(150, 25);
//        exportGraph.setId("exportbutton");
//        exportGraph.toFront();
//        exportGraph.setOpacity(0);

        //set different css styles when user hovers over button
//        exportGraph.setOnMouseEntered(e -> exportGraph.getStyleClass().add("export-button-bright"));
//        exportGraph.setOnMouseExited(e -> exportGraph.getStyleClass().remove("export-button-bright"));

        // positioning the objects within the rootPane by using margins
//        StackPane.setMargin(exportGraph, new Insets(140, 0, 0, 1045));
        StackPane.setMargin(graphPane, new Insets(0, 0, 200, 450));
        StackPane.setMargin(dataTable, new Insets(455, 0, 0, 450));
        StackPane.setMargin(explorerPane, new Insets(30, 830, 0, 0));
        StackPane.setAlignment(toolBar, Pos.TOP_CENTER);

        // set default alignment
        rootPane.setAlignment(Pos.CENTER);

        // adding all elements to be displayed
        rootPane.getChildren().addAll(toolBar, graphPane, dataTable, explorerPane,eP,tP,gP);

        // set the size of the current scene
        Scene scene = new Scene(rootPane, 1320, 740);
        scene.setFill(Color.TRANSPARENT);

        Utilities util = new Utilities();
        util.getCss(scene);

        return scene;
    }

    public StackPane getDataTable() {
        return dataTable;
    }

    public StackPane getExplorerPane() {
        return explorerPane;
    }

    public StackPane getGraphPane() {
        return graphPane;
    }


}
