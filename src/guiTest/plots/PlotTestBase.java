package guiTest.plots;

import data.StationList;
import gui.plots.PlotBase;
import guiTest.GuiTestBase;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utilities.EasyTask;
import utilities.JavaFXSafeTask;
import utilities.TestData;

public abstract class PlotTestBase extends GuiTestBase
	implements PlotBase.EventInterface
{
	StationList allStations;
	PlotBase plot;
	
	@Override
	public void onRefresh(PlotBase plot)
	{
		this.queueTask(new EasyTask(
				() -> 
				{
					plot.fetchData(bom, fio);
				}));
				this.queueTask(new JavaFXSafeTask(
				() ->
				{
					plot.plotData(plot.getCheckedItems());
				}));
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception
	{
		//Grabbing stations
		try {
			allStations = TestData.loadAllStations();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		plot = setPlot();
		plot.setEventHandler(this);
		onRefresh(plot);
		Scene scene  = new Scene(plot);
		scene.widthProperty().addListener(new ChangeListener<Number>() 
		{
            @Override public void changed(
            		ObservableValue<? extends Number> observableValue, 
            		Number oldSceneWidth, Number newSceneWidth) 
            {
            	plot.changeWidth(newSceneWidth.intValue());
            }
        });
		scene.heightProperty().addListener(new ChangeListener<Number>() 
		{
            @Override public void changed(
            		ObservableValue<? extends Number> observableValue, 
            		Number oldSceneHeight, Number newSceneHeight) 
            {
                plot.changeHeight(newSceneHeight.intValue());
            }
        });
		primaryStage.setScene(scene);
        primaryStage.show();
		
	}
	
	public abstract PlotBase setPlot();
}
