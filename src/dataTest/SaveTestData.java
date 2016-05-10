package dataTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.UnknownHostException;

import data.Bom;
import data.StationList;
import user.User;
import utilities.FolderPathHome;
import utilities.SaveObjectFile;

public class SaveTestData 
{
	static String pathAllStations = FolderPathHome.get() + "TestObjects/StationList";
	public static void main(String args[])
    {
		StationList stations;
		try {
			stations = Bom.getAllStations();
			SaveObjectFile.save(stations, pathAllStations);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    }
	
	public static StationList loadAllStations()
	{
		StationList stations = null;
		try 
		{
			FileInputStream fis = new FileInputStream(pathAllStations);
			ObjectInputStream ois = new ObjectInputStream(fis);
			stations = (StationList) ois.readObject();
			ois.close();
			fis.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.exit(0);
		}
		return stations;
	}
	
}
