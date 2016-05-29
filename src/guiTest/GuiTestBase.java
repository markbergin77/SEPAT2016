package guiTest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import data.Bom;
import data.Fio;
import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.stage.Stage;

public abstract class GuiTestBase extends Application
{
	@Override
	public abstract void start(Stage arg0) throws Exception;
	
	protected Bom bom = new Bom();
	protected Fio fio = new Fio();
	
	
	
	static ExecutorService exec = 
    		Executors.newSingleThreadExecutor(
		    r -> 
		    {
		        Thread t = new Thread(r);
		        // allow app to exit if tasks are running
		        t.setDaemon(true); 
		        return t ;
		    });
	
	protected void queueTask(Task<?> task)
	{
		exec.submit(task);
	}
}
