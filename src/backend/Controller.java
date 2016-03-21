package backend;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Vector;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import bomData.JSONScraper;
import bomData.Location;
import bomData.WthrSample;

public class Controller {
	
	Vector<Location> locations = new Vector<Location>();
	String locationURLs = "src/bomData/locations";
	
	public void createLocations() throws IOException 
	{
		long startScrapeTime = System.nanoTime();
		long endScrapeTime = System.nanoTime();
		double scrapeTime = ((double) (endScrapeTime - startScrapeTime))/Math.pow(10, 9);
		System.out.println("Scrape Time: " + scrapeTime + " sec");
		
		
//		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(locationURLs))) {
//			String location;
//			String[] locationArray;
//			String locationName;
//			String locationURL;
//			
//			while((location = bufferedReader.readLine()) != null) {
//				locationArray = location.split("#");
//				locationName = locationArray[0];
//				locationURL = locationArray[1];
//				Location newLocation = new Location(locationName, locationURL);
//				locations.addElement(newLocation);
//			}
//		}
	}
	
	public Vector<Location> getLocations() 
	{
		return locations;
	}
	
	public void fetchData(Location location) 
	{
		String locationURL = location.getURL();
		
		try {
			long startFetchFileTime = System.nanoTime();
			JsonArray rootArray = new JsonParser().parse(new BufferedReader(
					new InputStreamReader(new URL(locationURL).openStream())))
				.getAsJsonObject().getAsJsonObject("observations").getAsJsonArray("data");
			long endFetchFileTime = System.nanoTime();
			long startFetchProcessingTime = System.nanoTime();
			for (JsonElement element: rootArray) 
			{
				WthrSample newSample = null;
				JsonObject reading = element.getAsJsonObject();
				String localDateTime = reading.get("local_date_time").getAsString();
				String localDateTimeFull = reading.get("local_date_time_full").getAsString();
				String apparentT = reading.get("apparent_t").getAsString();
				String cloud = reading.get("cloud").getAsString();
				String gustKmh = reading.get("gust_kmh").getAsString();
				String gustKt = reading.get("gust_kt").getAsString();
				String airTemp = reading.get("air_temp").getAsString();
				String relHumidity = reading.get("rel_hum").getAsString();
				String dewPt = reading.get("dewpt").getAsString();
				String windDir = reading.get("wind_dir").getAsString();
				String windSpdKmh = reading.get("wind_spd_kmh").getAsString();
				String windSpdKt = reading.get("wind_spd_kt").getAsString();
				
				newSample = new WthrSample(localDateTime,  localDateTimeFull, apparentT, 
						cloud, gustKmh, gustKt, airTemp, relHumidity, dewPt,
						windDir, windSpdKmh, windSpdKt);
			}
			long endFetchProcessingTime = System.nanoTime();
			double FetchFileTime = ((double) (endFetchFileTime - startFetchFileTime))/Math.pow(10, 9);
			double FetchProcessingTime = ((double) (endFetchProcessingTime - startFetchProcessingTime))/Math.pow(10, 9);
			System.out.println("Fetch File Time: " + FetchFileTime + " sec");
			System.out.println("Fetch Processing Time: " + FetchProcessingTime + " sec");
		}
		// TODO
		catch (JsonIOException e) {}
		catch (JsonSyntaxException e) {}
		catch (MalformedURLException e) {}
		catch (IOException e) {}
		
	}

}
