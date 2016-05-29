package utilities;

import java.net.URL;

import gui.Alert;

public class CssLoader
{
	public static String FromRelative(Object onObject, String relPath) throws Exception
	{
		URL url = onObject.getClass().getResource(relPath);
		if (url == null) 
		{
			throw new Exception();
		}
		return url.toExternalForm();
	}
}
