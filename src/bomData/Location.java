package bomData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Vector;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

public class Location 
{
	private String name;
	private String jsonUrl;
	private String htmlUrl;
	private String state;
	
	public Location(String name, String jsonUrl, String htmlUrl, String state) {
		this.name = name;
		this.jsonUrl = jsonUrl;
		this.htmlUrl = htmlUrl;
		this.state = state;
	}
	
	//Function for filling Empty location with data (periods of 30 minutes up to 3 days)
	public WthrPast72hr getWthrLast72hr()
	{
		WthrPast72hr samples = new WthrPast72hr();
		try {
			JsonArray rootArray = new JsonParser().parse(new BufferedReader(
					new InputStreamReader(new URL(jsonUrl).openStream())))
				.getAsJsonObject().getAsJsonObject("observations").getAsJsonArray("data");
			for (JsonElement element: rootArray) 
			{
				//Grabs information through BOM's JSON Data 
				JsonObject reading = element.getAsJsonObject();
				
				String localDateTime;
				JsonElement localDateTimeJson = reading.get("local_date_time");
				if (localDateTimeJson.isJsonNull())
					localDateTime = "-";
				else
					localDateTime = localDateTimeJson.getAsString();
				
				String localDateTimeFull;
				JsonElement localDateTimeFullJson = reading.get("local_date_time_full");
				if (localDateTimeFullJson.isJsonNull())
					localDateTimeFull = "-";
				else
					localDateTimeFull = localDateTimeFullJson.getAsString();
				
				String apparentT;
				JsonElement apparentTJson = reading.get("apparent_t");
				if (apparentTJson.isJsonNull())
					apparentT = "-";
				else
					apparentT = apparentTJson.getAsString();
				
				String cloud;
				JsonElement cloudJson = reading.get("cloud");
				if (cloudJson.isJsonNull())
					cloud = "-";
				else
						cloud = cloudJson.getAsString();
				
				String gustKmh;
				JsonElement gustKmhJson = reading.get("gust_kmh");
				if (gustKmhJson.isJsonNull())
					gustKmh = "-";
				else
					gustKmh = gustKmhJson.getAsString();
				
				String gustKt;
				JsonElement gustKtJson = reading.get("gust_kt");
				if (gustKtJson.isJsonNull())
					gustKt = "-";
				else
					gustKt = gustKtJson.getAsString();
				
				String airTemp;
				JsonElement airTempJson = reading.get("air_temp");
				if (airTempJson.isJsonNull())
					airTemp = "-";
				else
					airTemp = airTempJson.getAsString();
				
				String relHumidity;
				JsonElement relHumidityJson = reading.get("rel_hum");
				if (relHumidityJson.isJsonNull())
					relHumidity = "-";
				else
					relHumidity = relHumidityJson.getAsString();
				
				String dewPt;
				JsonElement dewPtJson = reading.get("dewpt");
				if (dewPtJson.isJsonNull())
					dewPt = "-";
				else
					dewPt = dewPtJson.getAsString();
				
				String windDir;
				JsonElement windDirJson = reading.get("wind_dir");
				if (windDirJson.isJsonNull())
					windDir = "-";
				else
					windDir = windDirJson.getAsString();
				
				String windSpdKmh;
				JsonElement windSpdKmhJson = reading.get("wind_spd_kmh");
				if (windSpdKmhJson.isJsonNull())
					windSpdKmh = "-";
				else
					windSpdKmh = windSpdKmhJson.getAsString();
				
				String windSpdKt;
				JsonElement windSpdKtJson = reading.get("wind_spd_kt");
				if (windSpdKtJson.isJsonNull())
					windSpdKt = "-";
				else
					windSpdKt = windSpdKtJson.getAsString();
				//Add's location's observation data to vector
				samples.add(new WthrSample72hr(localDateTime,  localDateTimeFull, apparentT, 
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
	
	// Month in the format YYYYMM, 201603 would be March 2016
	public WthrPastMonth getWthrLastMonth(String month) throws IOException
	{
		String url;
		String csvUrl;
		String htmlUrl = this.getHtmlUrl();
		Document doc = Jsoup.connect(htmlUrl).get();
		Elements links = doc.select("a");
		for(Element link: links)
		{
			if (link.text().contains("Recent months")) 
			{
				url = link.attr("href");
				csvUrl = "http://www.bom.gov.au" + url.replace("dwo/", "dwo/" + month + "/text/").replace("latest.shtml", month + ".csv");
				System.out.println("DEBUG: " + csvUrl);
			}
		}
		
		
		WthrPastMonth samples = new WthrPastMonth();
		
		return samples;
	}
	
	public String getName() {
		return name;
	}
	
	public String getJsonUrl() {
		return jsonUrl;
	}
	
	public String getHtmlUrl() {
		return htmlUrl;
	}
	
	public String getState() {
		return state;
	}
}
