package smallUnitTests;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.UnknownHostException;

import data.Bom;
import data.Fio;
import data.Station;
import data.StationList;
import data.samples.WthrSamplesFine;
import junit.framework.*;
import org.junit.*;
import org.junit.Test;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

public class ConnectionTests {
	Bom bom = new Bom();
    Fio fio = new Fio();
	
	@Test
	public void BomIncorectConfigPath()
	{		
		
		bom.setBaseUrl("192.168.0.1");
		try {
			bom.getAllStations();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}	
	
	@Test
	public void FioIncorectConfigPath() throws IOException
	{
		StationList stations = bom.getAllStations();
		Station testStation = stations.get(0);
		// Need to fetch data to access lat and lon
		
		WthrSamplesFine wthrSamplesFine = bom.getWthrLast72hr(testStation);
		String lat = wthrSamplesFine.get(0).getLat();
		String lon = wthrSamplesFine.get(0).getLon();
	    fio.setBaseUrl("192.168.0.1");
	    fio.getFioDaily(lat, lon);
		
		
	}	
	
	@Test
	public void CorrectBomConfigPath()
	{
		bom.setBaseUrl("http://www.bom.gov.au/");
		try {
			bom.getAllStations();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void FioCorectConfigPath() throws IOException
	{
	    
		StationList stations = bom.getAllStations();
		Station testStation = stations.get(0);
		// Need to fetch data to access lat and lon
		
		WthrSamplesFine wthrSamplesFine = bom.getWthrLast72hr(testStation);
		String lat = wthrSamplesFine.get(0).getLat();
		String lon = wthrSamplesFine.get(0).getLon();
		
		fio.setBaseUrl("https://api.forecast.io/forecast/");
	    fio.getFioDaily(lat, lon);
		
		
	}	
}
