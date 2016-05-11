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
public class layoutMedHigh {

    private StackPane rootPane,graphPane,dataTable,explorerPane;

    public  Scene returnSceneMedHigh() {

        GraphPane gP = new GraphPane();
        TablePane tP = new TablePane();
        ExplorerPane eP = new ExplorerPane();

        gP.setId("gp");
        tP.setId("tp");
        eP.setId("ep");

        this.rootPane = new StackPane();
        rootPane.setId("rootpane");

        this.graphPane = new StackPane();
        graphPane = gP.getGraphMedium(graphPane);
        graphPane.setId("graphpane");
        graphPane.setOpacity(0);

        this.dataTable = new StackPane();
        dataTable = tP.getTable(dataTable, 2);
        dataTable.setId("datatablepane");
        dataTable.setOpacity(0);

        this.explorerPane = new StackPane();
        explorerPane = eP.getExplorerMedium(explorerPane);
        explorerPane.setId("explorerpane");
        explorerPane.setOpacity(0);

        ToolBar toolBar = new ToolBar();
        toolBar = CustomToolBar.returnToolbar(toolBar, 2);
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


     //   StackPane.setMargin(exportGraph, new Insets(90, 0, 0, 985));
        StackPane.setMargin(graphPane, new Insets(0, 0, 190, 460));
        StackPane.setMargin(dataTable, new Insets(415, 0, 0, 460));
        StackPane.setMargin(explorerPane, new Insets(40, 760, 0, 0));
        StackPane.setAlignment(toolBar, Pos.TOP_CENTER);

        rootPane.setAlignment(Pos.CENTER);
        rootPane.getChildren().addAll(toolBar, graphPane, dataTable, explorerPane);

        // creating the scene with all the elements
        Scene scene = new Scene(rootPane, 1260, 680);
        scene.setFill(Color.TRANSPARENT);

        Utilities util = new Utilities();
        util.getCss(scene);

        return scene;

    }


}
