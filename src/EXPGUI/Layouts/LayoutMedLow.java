package EXPGUI.Layouts;

import EXPGUI.Gui.Utilities;
import EXPGUI.Layouts.GuiElements.CustomToolBar.CustomToolBar;
import EXPGUI.Layouts.GuiElements.ExplorerPane.ExplorerPane;
import EXPGUI.Layouts.GuiElements.GraphPane.GraphPane;
import EXPGUI.Layouts.GuiElements.TablePane.TablePane;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;


/**
 * Created by Pavel Nikolaev on 4/05/2016.
 */
public class LayoutMedLow {

    public  Scene returnSceneMedLow() {

//        StackPane rootPane = new StackPane();
//
//        StackPane graphPane = new StackPane();
//        graphPane = GraphPane.getGraphsmall(graphPane);
//
//        StackPane dataTable = new StackPane();
//        dataTable = TablePane.getTable(dataTable, 3);
//
//
//        StackPane explorerPane = new StackPane();
//        explorerPane = ExplorerPane.getExplorerSmall(explorerPane);
//
//        ToolBar toolBar = new ToolBar();
//        toolBar = CustomToolBar.returnToolbar(toolBar, 3);
//
//        // button for creating a graph in a new window
//        Button exportGraph = new Button("Open Graph");
//        exportGraph.setMinSize(150, 25);
//        exportGraph.setId("exportbutton");
//        exportGraph.toFront();
//        // exportGraph.setOpacity(0);
//
//        //set different css styles when user hovers over button
//        exportGraph.setOnMouseEntered(e -> exportGraph.getStyleClass().add("export-button-bright"));
//        exportGraph.setOnMouseExited(e -> exportGraph.getStyleClass().remove("export-button-bright"));
//
//
//        // positioning the objects within the rootPane by using margins
//        StackPane.setMargin(exportGraph, new Insets(35, 0, 0, 870));
//        StackPane.setMargin(graphPane, new Insets(0, 0, 180, 430));
//        StackPane.setMargin(dataTable, new Insets(310, 0, 0, 430));
//        StackPane.setMargin(explorerPane, new Insets(40, 660, 0, 0));
//        StackPane.setAlignment(toolBar, Pos.TOP_CENTER);
//
//        // set default alignment
//        rootPane.setAlignment(Pos.CENTER);
//
//        // adding all elements to be displayed
//        rootPane.getChildren().addAll(toolBar, graphPane, dataTable, explorerPane, exportGraph);
//
//        // set the size of the current scene
//        Scene scene = new Scene(rootPane, 1100, 550);
//        scene.setFill(Color.TRANSPARENT);
//
//        Utilities util = new Utilities();
//        util.getCss(scene);
//
//        return scene;

        return null;
    }


}
