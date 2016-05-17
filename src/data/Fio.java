package data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import data.samples.FioSampleDaily;
import data.samples.FioSamplesDaily;
import data.samples.WthrSamplesFine;

public class Fio
{
	private static String baseUrl = "https://api.forecast.io/forecast/";
	private static String apiKey = "04645e618b31cc361b70a8d5f7136f6f";

	public static FioSamplesDaily getFioDaily(Station station)
			throws JsonIOException, JsonSyntaxException, MalformedURLException, IOException
	{
		String exclude = "[currently,minutely,hourly,alerts,flags]";
		
		// Need to get lat and lon from a wthrSample
		WthrSamplesFine wthrSamplesFine = Bom.getWthrLast72hr(station);
		String lon = wthrSamplesFine.get(0).getLon();
		String lat = wthrSamplesFine.get(0).getLat();
		
		// Need to specify units=si for standard measurements (celcius)
		String requestString = baseUrl + apiKey + "/" + lat + "," + lon + "?" + "units=si&exclude=" + exclude;
		
		
		HttpURLConnection connection = (HttpURLConnection) new URL(requestString).openConnection();
		connection.setRequestMethod("GET");
		BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		
		FioSamplesDaily samples = new FioSamplesDaily();
		JsonArray rootArray = new JsonParser().parse(reader).getAsJsonObject().getAsJsonObject("daily").getAsJsonArray("data");

		for (JsonElement element : rootArray)
		{
			JsonObject reading = element.getAsJsonObject();
			
			String timeString;
			LocalDateTime time = null;
			JsonElement timeJson = reading.get("time");
			if (timeJson.isJsonNull()) {
				time = null;
			}
			else {
				timeString = timeJson.getAsString();
				time = LocalDateTime.ofEpochSecond(Long.parseLong(timeString), 0, ZoneOffset.UTC);
			}
			String icon = reading.get("icon").getAsString();
			String min = reading.get("temperatureMin").getAsString();
			String max = reading.get("temperatureMax").getAsString();

			samples.add(new FioSampleDaily(time, icon, min, max));
		}
		connection.disconnect();
		return samples;
	}
}
