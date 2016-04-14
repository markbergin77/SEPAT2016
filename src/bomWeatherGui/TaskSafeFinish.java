package bomWeatherGui;
/* You can set a lambda to run safely 
 * on the javafx thread when this finishes
 * with the setOnFinishedSafe() method
 */

import javafx.concurrent.Task;

public class TaskSafeFinish extends Task
{
	Runnable onCall, onFinished;
	public TaskSafeFinish(Runnable onCall)
	{
		this.onCall = onCall;
		this.onFinished = null;
	}

	public TaskSafeFinish(Runnable onCall, Runnable onFinished)
	{
		this.onCall = onCall;
		this.onFinished = onFinished;
	}
	
	@Override
	protected Object call() throws Exception {
		// TODO Auto-generated method stub
		onCall.run();
		return null;
	}
	public void setOnFinished(Runnable onFinished)
	{
		this.onFinished = onFinished;
	}
	
	@Override
	protected void succeeded()
	{
		if(onFinished != null)
			onFinished.run();
	}
}
