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

    private StackPane rootPane,graphPane,dataTable,explorerPane;

    public  Scene returnSceneMedLow() {

        GraphPane gP = new GraphPane();
        TablePane tP = new TablePane();
        ExplorerPane eP = new ExplorerPane();

        gP.setId("gp");
        tP.setId("tp");
        eP.setId("ep");

        this.rootPane = new StackPane();
        rootPane.setId("rootpane");

        this.graphPane = new StackPane();
        graphPane = gP.getGraphsmall(graphPane);
        graphPane.setId("graphpane");
        graphPane.setOpacity(0);

        this.dataTable = new StackPane();
        dataTable = tP.getTable(dataTable, 3);
        dataTable.setId("datatablepane");
        dataTable.setOpacity(0);

        this.explorerPane = new StackPane();
        explorerPane = eP.getExplorerSmall(explorerPane);
        explorerPane.setId("explorerpane");
        explorerPane.setOpacity(0);

        ToolBar toolBar = new ToolBar();
        toolBar = CustomToolBar.returnToolbar(toolBar, 3);
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

        //StackPane.setMargin(exportGraph, new Insets(35, 0, 0, 870));
        StackPane.setMargin(graphPane, new Insets(0, 0, 180, 430));
        StackPane.setMargin(dataTable, new Insets(310, 0, 0, 430));
        StackPane.setMargin(explorerPane, new Insets(40, 660, 0, 0));
        StackPane.setAlignment(toolBar, Pos.TOP_CENTER);

        rootPane.setAlignment(Pos.CENTER);
        rootPane.getChildren().addAll(toolBar, graphPane, dataTable, explorerPane);

        Scene scene = new Scene(rootPane, 1100, 550);
        scene.setFill(Color.TRANSPARENT);

        Utilities util = new Utilities();
        util.getCss(scene);


        return scene;
    }


}
