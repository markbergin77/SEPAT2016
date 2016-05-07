package utilities;

import javafx.concurrent.Task;

public class EasyTask extends Task
{
	Runnable onCall;
	public EasyTask(Runnable onCall)
	{
		this.onCall = onCall;
	}
	
	@Override
	protected Object call() throws Exception 
	{
		// TODO Auto-generated method stub
		onCall.run();
		return null;
	}
}
