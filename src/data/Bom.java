package data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

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
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

/* Use this class to communicate with the 
 * bureau of meteorology and get information
 * about it.
 */

public class Bom 
{
	enum State { vic, nsw, tas, wa, sa, nt, qld, ant };
	
	static String[] bomStateAliases()
	{
		String[] states = { "vic", "nsw", "tas", "wa", "sa", "nt", "qld", "ant" };
		return states;
	}
	
	/* The URL of a shtml page that contains a table 
	 * of all of the state's stations. */
	static String stateAllUrl(State state)
	{
		String stateStr = stateName(state);
		return "http://www.bom.gov.au/" + stateStr + "/observations/" + stateStr + "all.shtml";
	}
	// TO DO - ARCHIVE   MARK JOB ONLY 
	public static StationList getAllStations() throws IOException
	{
		StationList stations = new StationList();
		for (State state : State.values()) {
			stations.addAll(getStations(state));
		}
		return stations;
	}
	// Adds station details to separate list, 
	public static StationList getAllStations(LoadingUpdater progressNotifier) throws IOException
	{
		StationList stations = new StationList();
		//Also provides info for user on current state
		for (State state : State.values())
		{
			progressNotifier.loadingUpdate("Loading " + state + " stations");
			stations.addAll(getStations(state));
		}
		progressNotifier.loadingUpdate("");
		return stations;
	}
	//Grabs station location, saves details to object instances
	public static StationList getStations(State state) throws IOException
	{
		//Use of Jsoup Framework
		StationList stations = new StationList();
		Document doc = Jsoup.connect(stateAllUrl(state))
				.get();
		Elements tbodies = doc.select("tbody");
		Elements links = tbodies.select("a");
		for (Element link : links)
		{
			String url = link.attr("href");
			if (url.contains("products") && !url.contains("#"))
			{
				String htmlUrl = "http://www.bom.gov.au" + url;
				String jsonUrl = 
						"http://www.bom.gov.au" + url.replace("products", "fwo").replace("shtml", "json");
				String name = link.text();
				// Some names have an asterisk on the page
				if (name.endsWith("*"))
				{
					name = name.substring(0, name.length() - 1);
				}
				// Returns station of Observations
				stations.add(new Station(name, jsonUrl, htmlUrl, stateName(state)));
				
			}
		}
		return stations;
	}
	
	//List of fixed states needed to find appropriate stations
	static String stateName(State state)
	{
		String stateStr = "";
		switch (state)
		{
		case ant:
			stateStr = "ant";
			break;
		case nsw:
			stateStr = "nsw";
			break;
		case nt:
			stateStr = "nt";
			break;
		case qld:
			stateStr = "qld";
			break;
		case sa:
			stateStr = "sa";
			break;
		case tas:
			stateStr = "tas";
			break;
		case vic:
			stateStr = "vic";
			break;
		case wa:
			stateStr = "wa";
			break;
		default:
			return null;
		}
		return stateStr;
	}
	
	// Function for filling Empty Station with data (periods of 30 minutes up to
	// 3 days)
	public static WthrSamplesFine getWthrLast72hr(Station station) {
		WthrSamplesFine samples = new WthrSamplesFine();
		// Weather data for Recent observations stored as Json format.
		try {
			JsonArray rootArray = new JsonParser()
					.parse(new BufferedReader(new InputStreamReader(new URL(station.getJsonUrl()).openStream())))
					.getAsJsonObject().getAsJsonObject("observations").getAsJsonArray("data");
			for (JsonElement element : rootArray) {
				// Grabs all information through BOM's JSON Data
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
				// Add's Station's observation data to vector
				samples.add(new WthrSampleFine(localDateTime, localDateTimeFull, apparentT, cloud, gustKmh, gustKt,
						airTemp, relHumidity, dewPt, windDir, windSpdKmh, windSpdKt));
			}
		}
		// TODO
		catch (JsonIOException e) {
		} catch (JsonSyntaxException e) {
		} catch (MalformedURLException e) {
		} catch (IOException e) {
		}
		return samples;
	}

	// Function For grabbing broader (later) historical observations
	// Month in the format YYYYMM, 201603 would be March 2016.
	public static WthrSamplesDaily getWthrLastMonth(Station station, YearMonth date) throws IOException {
		String dateString = date.format(DateTimeFormatter.ofPattern("yyyyMM"));
		WthrSamplesDaily samples = null;
		Boolean fileExists = false;
		File filePath = new File("data/" + station.getName() + '-' + dateString + ".csv");
		File dirPath = new File("data");
		// Checks directory for any previous downloads
		if (!dirPath.isDirectory()) {
			dirPath.mkdir();
		} else {
			for (String fileName : dirPath.list()) {
				if (fileName.equals(dateString))
					fileExists = true;
			}
		}
		// Grabs File, BOM represents this data in CSV format.
		if (!fileExists) {
			String[] nextLine;
			String url;
			String csvUrl;
			String htmlUrl = station.getHtmlUrl();
			Document doc = Jsoup.connect(htmlUrl).get();
			Elements links = doc.select("a");
			for (Element link : links) {
				if (link.text().contains("Recent months")) {
					url = link.attr("href");
					csvUrl = "http://www.bom.gov.au"
							+ url.replace("dwo/", "dwo/" + dateString + "/text/").replace("latest.shtml", dateString + ".csv");

					// Testing code
					// System.out.println(this.getName() + " " + csvUrl);

					try (BufferedReader csvStream = new BufferedReader(
							new InputStreamReader(new URL(csvUrl).openStream()))) {
						CSVReader csvReader = new CSVReader(csvStream);
						try (CSVWriter csvWriter = new CSVWriter(new FileWriter(filePath))) {
							while ((nextLine = csvReader.readNext()) != null) {
								csvWriter.writeNext(nextLine);
							}
						}
						csvReader.close();
					}
					// Some locations lack desired data
					catch (FileNotFoundException e) {
						System.out.println("FILE NOT FOUND");
						return null;
					}
				}
			}
		}
		try (BufferedReader csvStream = new BufferedReader(new FileReader(filePath))) {
			CSVReader csvReader = new CSVReader(csvStream);
			samples = processCsv(csvReader);
		}
		return samples;
	}

	/*
	 * Scrapes data from CSV file on line by line basis And adds to object
	 */
	private static WthrSamplesDaily processCsv(CSVReader csvReader) throws IOException {
		String[] nextLine = null;
		WthrSamplesDaily samples = new WthrSamplesDaily();

		while ((nextLine = csvReader.readNext()) != null) {
			// The csv files are irregular, don't know how many lines to skip
			if (nextLine.length != 22 || nextLine[1].equals("Date")) {
				continue;
			}
			String date = nextLine[1];
			String minTemp = nextLine[2];
			String maxTemp = nextLine[3];
			String rain = nextLine[4];
			String evap = nextLine[5];
			String sun = nextLine[6];
			String maxWindGustDir = nextLine[7];
			String maxWindGustSpd = nextLine[8];
			String maxWindGustTime = nextLine[9];
			String temp9am = nextLine[10];
			String relHumidity9am = nextLine[11];
			String cloud9am = nextLine[12];
			String windDir9am = nextLine[13];
			String windSpd9am = nextLine[14];
			String meanSeaLevelPressure9am = nextLine[15];
			String temp3pm = nextLine[16];
			String relHumidity3pm = nextLine[17];
			String cloud3pm = nextLine[18];
			String windDir3pm = nextLine[19];
			String windSpd3pm = nextLine[20];
			String meanSeaLevelPressure3pm = nextLine[21];
			samples.add(new WthrSampleDaily(date, minTemp, maxTemp, rain, evap, sun, maxWindGustDir, maxWindGustSpd,
					maxWindGustTime, temp9am, relHumidity9am, cloud9am, windDir9am, windSpd9am, meanSeaLevelPressure9am,
					temp3pm, relHumidity3pm, cloud3pm, windDir3pm, windSpd3pm, meanSeaLevelPressure3pm));
		}

		return samples;
	}
}
