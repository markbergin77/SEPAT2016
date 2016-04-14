package bomWeatherGui;


import bomData.Station;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Created by Pavel Nikolaev on 26/03/2016.
 */
public class ListNode extends Parent {

  //  private String LOCATION;
  //  private int TEMP;
  //  private Boolean RAINING, SUNNY;
    private Station station;

    public ListNode (String s, Station station) {
        setContent(s);
        this.station = station;
    }

    public void setContent(String s){

        if(!s.equals("s")) {

            Rectangle rect = new Rectangle(340, 65, Color.rgb(20, 20, 20, 0.9));
            rect.setArcHeight(10);
            rect.setArcWidth(10);
            rect.setStroke(Color.LIGHTSLATEGRAY);
            rect.setStrokeWidth(2);
            rect.setStyle("-fx-effect: dropshadow(three-pass-box,black, 20, 0.3, 0, 0);");

            rect.setOnMouseEntered(e -> rect.setStyle("-fx-effect: dropshadow(three-pass-box,#606060, 20, 0.3, 0, 0);"));
            rect.setOnMouseExited(e -> rect.setStyle("-fx-effect: dropshadow(three-pass-box,black, 20, 0.3, 0, 0);"));


            StackPane pane = new StackPane();
            pane.setPrefSize(340, 65);
            pane.getStyleClass().add("container");
            pane.getChildren().add(rect);
            StackPane.setAlignment(rect, Pos.CENTER);
            getChildren().add(rect);

        }

        else{

            Rectangle rect = new Rectangle(340, 55, Color.rgb(20, 20, 20, 0.9));
            rect.setArcHeight(10);
            rect.setArcWidth(10);
            rect.setStroke(Color.LIGHTSLATEGRAY);
            rect.setStrokeWidth(2);
            rect.setStyle("-fx-effect: dropshadow(three-pass-box,black, 10, 0.1, 0, 0);");

            rect.setOnMouseEntered(e -> rect.setStyle("-fx-effect: dropshadow(three-pass-box,#606060, 10, 0.3, 0, 0);"));
            rect.setOnMouseExited(e -> rect.setStyle("-fx-effect: dropshadow(three-pass-box,black, 10, 0.1, 0, 0);"));

            StackPane pane = new StackPane();
            pane.setPrefSize(340, 55);
            pane.getStyleClass().add("container");
            pane.getChildren().add(rect);
            StackPane.setAlignment(rect, Pos.CENTER);
            getChildren().add(pane);

        }

    }

    public LineChart<Number,Number> returnChart(){

        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        //creating the chart
        final LineChart<Number,Number> lineChart =
                new LineChart<Number,Number>(xAxis,yAxis);


        //defining a series
        XYChart.Series series = new XYChart.Series();
        //populating the series with data
        //should have 12 points on the graph to represent 12 months

        series.getData().add(new XYChart.Data(1, 23)); //should get data from the station
        series.getData().add(new XYChart.Data(2, 14));
        series.getData().add(new XYChart.Data(3, 15));
        series.getData().add(new XYChart.Data(4, 24));
        series.getData().add(new XYChart.Data(5, 34));
        series.getData().add(new XYChart.Data(6, 36));
        series.getData().add(new XYChart.Data(7, 22));
        series.getData().add(new XYChart.Data(8, 45));
        series.getData().add(new XYChart.Data(9, 43));
        series.getData().add(new XYChart.Data(10, 17));
        series.getData().add(new XYChart.Data(11, 29));
        series.getData().add(new XYChart.Data(12, 25));

        lineChart.getData().add(series);

        return lineChart;

    }

    public TableView<String> getTable(int width,int height){

        TableView<String> table = new TableView<String>();
        table.setMaxSize(width,height);
        table.setEditable(false);
        table.getItems().addAll("HEY");
        table.setPadding(new Insets(1,1,10,1));
        TableColumn<String,String> collumn1 = new TableColumn<String,String>("Temp");
        TableColumn<String,String> collumn2 = new TableColumn<String,String>("Humidity");
        TableColumn<String,String> collumn3 = new TableColumn<String,String>("Blah");
        TableColumn<String,String> collumn4 = new TableColumn<String,String>("Blah");

        collumn1.setPrefWidth((width/4)-1);
        collumn2.setPrefWidth((width/4)-1);
        collumn3.setPrefWidth((width/4)-1);
        collumn4.setPrefWidth((width/4)-1);

        collumn1.setResizable(false);
        collumn2.setResizable(false);   ////should get data from the station being passed in the constructor
        collumn3.setResizable(false);
        collumn4.setResizable(false);


        table.getColumns().addAll( collumn1, collumn2, collumn3, collumn4);

        return table;
    }
}
