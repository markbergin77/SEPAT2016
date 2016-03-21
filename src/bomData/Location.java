package bomData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Vector;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

public class Location implements Serializable 
{
	private String name;
	private String url;
	private String state;
	
	public Location(String name, String url) {
		this.name = name;
		this.url = url;
	}
	
	public Location(String name, String url, String state) {
		this.name = name;
		this.url = url;
		this.state = state;
	}
	
	//Function for filling Empty location with data (periods of 30 minutes up to 3 days)
	public Vector<WthrSample> getSamples()
	{
		Vector<WthrSample> samples = new Vector<WthrSample>();
		try {
			JsonArray rootArray = new JsonParser().parse(new BufferedReader(
					new InputStreamReader(new URL(url).openStream())))
				.getAsJsonObject().getAsJsonObject("observations").getAsJsonArray("data");
			for (JsonElement element: rootArray) 
			{
				//Grabs information through BOM's JSON Data 
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
				//Add's location's observation data to vector
				samples.add(new WthrSample(localDateTime,  localDateTimeFull, apparentT, 
						cloud, gustKmh, gustKt, airTemp, relHumidity, dewPt,
						windDir, windSpdKmh, windSpdKt));
			}
		}
		// TODO
		catch (JsonIOException e) {}
		catch (JsonSyntaxException e) {}
		catch (MalformedURLException e) {}
		catch (IOException e) {}
		return samples;
	}
	
	public String getName() {
		return name;
	}
	
	public String getURL() {
		return url;
	}
	
	public String getState() {
		return state;
	}
}
