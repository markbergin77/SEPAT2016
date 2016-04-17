package bomWeatherGui;

/**
 * Created by Pavel Nikolaev on 12/04/2016.
 */
import javafx.scene.*;
import javafx.scene.chart.LineChart;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.geometry.*;

public class GraphWindow {
    //Design and execution of where User will view Monthly data in a graphical format
    public static void createGraphWindow(String Title, LineChart<Number,Number> chart){
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.initStyle(StageStyle.TRANSPARENT);
        
        //Data represented as line graph
        LineChart<Number,Number> graph = chart;
        StackPane graphPane = new StackPane();
        graphPane.setMaxSize(chart.getWidth()+20,chart.getHeight()+20);
        graphPane.getChildren().add(graph);
        graphPane.setAlignment(Pos.CENTER);
        Scene scene1 = new Scene(graphPane,800,450);
        Utilities util = new Utilities();
        util.getCss(scene1);
        window.setScene(scene1);
        window.show();
    }
}
