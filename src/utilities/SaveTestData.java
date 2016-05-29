package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.UnknownHostException;

import data.Bom;
import data.StationList;
import data.samples.WthrSamplesFine;

public class SaveTestData 
{
	static Bom bom;
	static String pathTestData = "TestObjects/";
	
	static String pathAllStations = 
			FolderPathHome.get() + pathTestData + "StationList.to";
	
	static String pathWthrSamplesFine = 
			FolderPathHome.get() + pathTestData + "WthrSamplesFine.to";
	
	public static void main(String args[])
    {
		StationList stations;
		WthrSamplesFine fineSamples;
		try {
			stations = bom.getAllStations();
			SaveObjectFile.save(stations, pathAllStations);
			fineSamples = bom.getWthrLast72hr(stations.get(0));
			SaveObjectFile.save(fineSamples, pathWthrSamplesFine);
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
	
	public static WthrSamplesFine loadFineSamples()
	{
		WthrSamplesFine samples = null;
		try 
		{
			FileInputStream fis = new FileInputStream(pathWthrSamplesFine);
			ObjectInputStream ois = new ObjectInputStream(fis);
			samples = (WthrSamplesFine) ois.readObject();
			ois.close();
			fis.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.exit(0);
		}
		return samples;
	}
}
