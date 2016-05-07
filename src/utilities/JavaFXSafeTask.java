package utilities;
/* You can set a lambda to run safely 
 * on the javafx thread when this finishes
 * with the setOnFinishedSafe() method
 */

import javafx.application.Platform;
import javafx.concurrent.Task;
/* This class wraps the call in a 
 * runLater() so that it'll run on the
 * javafx gui thread. */
public class JavaFXSafeTask extends Task
{
	Runnable onCall;
	public JavaFXSafeTask(Runnable onCall)
	{
		this.onCall = onCall;
	}
	
	@Override
	protected Object call() throws Exception 
	{
		// TODO Auto-generated method stub
		Platform.runLater(() -> 
		{
			onCall.run();
		});
		return null;
	}
}
