package guiPlots;

import java.io.IOException;
import java.net.URL;
import java.time.YearMonth;

import EXPGUI.Gui.Utilities;
import com.sun.javafx.charts.Legend;
import data.Bom;
import data.Station;
import data.WthrSampleDaily;
import data.WthrSamplesDaily;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;

public class PlotHistoricalTemp //extends PlotBase
{
    // private String cssPath;
    // static String cssFileName = "HisTempPlot.css";
//
//	final CategoryAxis xAxis = new CategoryAxis();
//	final NumberAxis yAxis = new NumberAxis();
//	LineChart<String, Number> lineChart = new LineChart<String, Number>(xAxis, yAxis);
//
//	XYChart.Series<String, Number> seriesTempMin = new XYChart.Series<String, Number>();
//	XYChart.Series<String, Number> seriesTempMax = new XYChart.Series<String, Number>();
//	XYChart.Series<String, Number> seriesTemp9am = new XYChart.Series<String, Number>();
//	XYChart.Series<String, Number> seriesTemp3pm = new XYChart.Series<String, Number>();
//
//	YearMonth start = YearMonth.now().minusMonths(12);
//	YearMonth end = YearMonth.now().plusMonths(1);


    public static LineChart<String, Number> getPlotHistorical(Station station) {


        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        LineChart<String, Number> lineChart = new LineChart<String, Number>(xAxis, yAxis);

        XYChart.Series<String, Number> seriesTempMin = new XYChart.Series<String, Number>();
        XYChart.Series<String, Number> seriesTempMax = new XYChart.Series<String, Number>();
        XYChart.Series<String, Number> seriesTemp9am = new XYChart.Series<String, Number>();
        XYChart.Series<String, Number> seriesTemp3pm = new XYChart.Series<String, Number>();


        YearMonth start = YearMonth.now().minusMonths(12);
        YearMonth end = YearMonth.now().plusMonths(1);

        xAxis.setLabel("Date");
        yAxis.setLabel("Temperature in Degrees");
        lineChart.setTitle(station.getName());
        seriesTempMin.setName("Minimum Temperature");
        seriesTempMax.setName("Maximum Temperature");
        seriesTemp9am.setName("9am Temperature");
        seriesTemp3pm.setName("3pm Temperature");


        // Remove markers from line
        lineChart.setCreateSymbols(false);
        lineChart.horizontalGridLinesVisibleProperty().set(false);
        lineChart.verticalGridLinesVisibleProperty().set(false);

        WthrSamplesDaily wthrSamplesDaily = getData(station, start, end);


        for (WthrSampleDaily sample : wthrSamplesDaily) {
            String date = sample.getDate();
            String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sept", "Oct", "Nov", "Dec"};
            String[] dateArray = date.split("-");
            int monthInt = Integer.parseInt(dateArray[1]);
            String month = months[monthInt - 1];
            date = dateArray[2] + "-" + month;
            String tempMax = sample.getMaxTemp();
            String tempMin = sample.getMinTemp();
            String temp9am = sample.getTemp9am();
            String temp3pm = sample.getTemp3pm();


            // Check if the string is null or blank, due to FILE NOT FOUND
            if (tempMin.length() > 0)
                seriesTempMin.getData().add(new Data<String, Number>(date, Float.parseFloat(tempMin)));
            if (tempMax.length() > 0)
                seriesTempMax.getData().add(new XYChart.Data<String, Number>(date, Float.parseFloat(tempMax)));
            if (temp9am.length() > 0)
                seriesTemp9am.getData().add(new XYChart.Data<String, Number>(date, Float.parseFloat(temp9am)));
            if (temp3pm.length() > 0)
                seriesTemp3pm.getData().add(new XYChart.Data<String, Number>(date, Float.parseFloat(temp3pm)));
        }


        lineChart.getData().addAll(seriesTempMin, seriesTempMax, seriesTemp9am, seriesTemp3pm);

        return lineChart;
    }


    //	public LineChart PlotHistoricalTemp(Station station)
//	{
//		//super(station);
//		setName(station.getName() + " Historical Temperatures");
//		URL url = this.getClass().getResource(cssFileName);
//        cssPath = url.toExternalForm();
//
//		  xAxis.setLabel("Date");
//        yAxis.setLabel("Temperature in Degrees");
//        lineChart.setTitle(station.getName());
//        seriesTempMin.setName("Minimum Temperature");
//        seriesTempMax.setName("Maximum Temperature");
//        seriesTemp9am.setName("9am Temperature");
//        seriesTemp3pm.setName("3pm Temperature");
//
//        // Remove markers from line
//        lineChart.setCreateSymbols(false);
//        lineChart.horizontalGridLinesVisibleProperty().set(false);
//        lineChart.verticalGridLinesVisibleProperty().set(false);
//
//        plot();
//
//        // Hacky solution, add a css class to each line
//        String[] lineClasses = {"tempMin", "tempMax", "temp9am", "temp3pm"};
//        seriesTempMin.getNode().getStyleClass().add(lineClasses[0]);
//        seriesTempMax.getNode().getStyleClass().add(lineClasses[1]);
//        seriesTemp9am.getNode().getStyleClass().add(lineClasses[2]);
//        seriesTemp3pm.getNode().getStyleClass().add(lineClasses[3]);
//
//        // Get access to the legend
//        Legend legend = (Legend)lineChart.lookup(".chart-legend");
//        ObservableList<Node> legendChildren = legend.getChildren();
//
//        // Add a click listener to each legend item
//        for (int i = 0; i < lineClasses.length; i++) {
//        	Object legendChild = legendChildren.get(i);
//        	// Need the "." as class specifier, e.g. .class
//        	Node line = lineChart.lookup("." + lineClasses[i]);
//        	((Node) legendChild).setOnMouseClicked(new EventHandler<MouseEvent>(){
//                @Override
//                public void handle(MouseEvent e) {
//                	if (line.isVisible()) {
//                		line.setVisible(false);
//                	}
//                	else {
//                		line.setVisible(true);
//                	}
//                }
//            });
//        }
//
//        // add the lineChart to the gridPane
//        assembleFrom(lineChart);
//
//		return lineChart;
//	}
//
    private static WthrSamplesDaily getData(Station station, YearMonth start, YearMonth end) {
        WthrSamplesDaily wthrSamplesDaily = new WthrSamplesDaily();
        try {
            wthrSamplesDaily = Bom.getWthrRange(station, start, end);
            return wthrSamplesDaily;
        } catch (IOException e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Cannot access BoM JSON server");
            alert.setContentText("Please check your internet connection and try again");

            alert.showAndWait();
            e.printStackTrace();
            return null;
        }
//	}
//
//	private static void addToAllSeries(WthrSamplesDaily wthrSamplesDaily) {
//
//			for(WthrSampleDaily sample: wthrSamplesDaily) {
//        	String date = sample.getDate();
//        	String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sept", "Oct", "Nov", "Dec"};
//        	String[] dateArray = date.split("-");
//        	int monthInt = Integer.parseInt(dateArray[1]);
//        	String month = months[monthInt - 1];
//        	date = dateArray[2] + "-" + month;
//        	String tempMax = sample.getMaxTemp();
//        	String tempMin = sample.getMinTemp();
//        	String temp9am = sample.getTemp9am();
//        	String temp3pm = sample.getTemp3pm();
//
//        	// Check if the string is null or blank, due to FILE NOT FOUND
//        	if (tempMin.length() > 0)
//        		seriesTempMin.getData().add(new Data<String, Number>(date,Float.parseFloat(tempMin)));
//        	if (tempMax.length() > 0)
//        		seriesTempMax.getData().add(new XYChart.Data<String, Number>(date,Float.parseFloat(tempMax)));
//        	if (temp9am.length() > 0)
//        		seriesTemp9am.getData().add(new XYChart.Data<String, Number>(date,Float.parseFloat(temp9am)));
//        	if (temp3pm.length() > 0)
//        		seriesTemp3pm.getData().add(new XYChart.Data<String, Number>(date,Float.parseFloat(temp3pm)));
//        }
//	}
//
//	private void clearAllSeries() {
//		seriesTempMin.getData().clear();
//		seriesTempMax.getData().clear();
//		seriesTemp9am.getData().clear();
//		seriesTemp3pm.getData().clear();
//	}
//
//	private void plot() {
//		WthrSamplesDaily wthrSamplesDaily  = getData(station, start, end);
//        addToAllSeries(wthrSamplesDaily);
//        lineChart.getData().addAll(seriesTempMin, seriesTempMax, seriesTemp9am, seriesTemp3pm);
//	}
//
//	@Override
//	protected void onRefresh() {
//		WthrSamplesDaily wthrSamplesDaily = getData(station, start, end);
//		clearAllSeries();
//		addToAllSeries(wthrSamplesDaily);
//	}
//
//	@Override
//	public String getCssPath()
//	{
//		return cssPath;
//	}
//}
    }
}