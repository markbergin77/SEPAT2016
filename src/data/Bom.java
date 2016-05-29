package data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.UnknownHostException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.zip.GZIPInputStream;

import org.apache.log4j.Logger;
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

import data.samples.WthrSampleDaily;
import data.samples.WthrSampleFine;
import data.samples.WthrSamplesDaily;
import data.samples.WthrSamplesFine;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import utilities.FolderPathHome;

/**
 * Static Class that accesses the BoM website to fetch weather data. Currently
 * supports two types of historic weather data. Fine: 72hrs of ~30min readings
 * in a JSON format. Coarse: up to 12mths of daily readings in a CSV format.
 * 
 * @author Mark Bergin, Radmilo Lega, Lawrence Millar-Madigan, Pavel Nikolaev
 */

public class Bom
{
	private static Logger logger = Logger.getLogger(Bom.class);
	static String defaultConfigPath = FolderPathHome.get() + "data/BomConfig.cfg";
	private String pathToConfig;
	private String baseUrl;
	
	enum State
	{
		vic, nsw, tas, wa, sa, nt, qld, ant
	};

	static String[] bomStateAliases()
	{
		String[] states =
		{ "vic", "nsw", "tas", "wa", "sa", "nt", "qld", "ant" };
		return states;
	}
	
	public Bom() 
	{
		this.pathToConfig = defaultConfigPath;
		this.baseUrl = getBaseUrl(pathToConfig);
	}
	
	public Bom(String pathToConfig) 
	{
		this.pathToConfig = pathToConfig;
		this.baseUrl = getBaseUrl(pathToConfig);
	}
	
	private String getBaseUrl(String pathToConfig) 
	{
		return "http://www.bom.gov.au/";
	}

	/*
	 * The URL of a shtml page that contains a table of all of the state's
	 * stations.
	 */
	String stateAllUrl(State state)
	{
		String stateStr = stateName(state);
		return baseUrl + stateStr + "/observations/" + stateStr + "all.shtml";
	}

	// TO DO - ARCHIVE MARK JOB ONLY
	public StationList getAllStations() throws UnknownHostException, IOException
	{

		StationList stations = new StationList();
		for (State state : State.values())
		{
			StationList allStations = getStations(state);
			if (allStations != null)
			{
				stations.addAll(allStations);
			} else
			{
				return null;
			}

		}
		return stations;
	}

	/**
	 * Facilitates acquiring all stations on program startup and allows the
	 * display of a progress bar.
	 * 
	 * @param progressNotifier
	 *            LoadingUpdater to track fetch progress for loading bar
	 * @return StationList containing Station objects of all stations
	 * @throws IOException
	 */
	public StationList getAllStations(LoadingUpdater progressNotifier) throws IOException
	{
		logger.debug("Starting Bom::getAllStations()");
		StationList stations = new StationList();
		// Also provides info for user on current state
		for (State state : State.values())
		{
			progressNotifier.loadingUpdate("Loading " + state + " stations");

			logger.debug("Calling Bom::getStations()");
			stations.addAll(getStations(state));
		}
		progressNotifier.loadingUpdate("");
		return stations;
	}

	/**
	 * 
	 * @param state
	 *            The state to fetch data for, e.g. vic, nsw, qld.
	 * @return StationList containing Station objects of specified station
	 * @throws IOException
	 */
	public StationList getStations(State state) throws IOException, UnknownHostException
	{
		logger.debug("Starting Bom::getStations()");
		// Use of Jsoup Framework
		StationList stations = new StationList();
		int i = 0;
		int numTries = 3;
		Document doc = null;
		Boolean successfulConnection = false;
		while (i < numTries)
		{
			try
			{
				doc = Jsoup.connect(stateAllUrl(state)).get();
				successfulConnection = true;
				break;
			} catch (SocketTimeoutException e)
			{
                logger.warn("WARNING:",e);
				int attemptNum = i + 1;
				System.out.println("Attempting to Fetch Stations " + attemptNum);
			}
			i++;
		}

		if (successfulConnection)
		{
			Elements tbodies = doc.select("tbody");
			Elements links = tbodies.select("a");
			for (Element link : links)
			{
				String url = link.attr("href");
				if (url.contains("products") && !url.contains("#"))
				{
					// Remove the '/' from baseUrl
					String htmlUrl = baseUrl.substring(0, baseUrl.length()-1) + url;
					String jsonUrl = baseUrl.substring(0, baseUrl.length()-1) + url.replace("products", "fwo").replace("shtml", "json");
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
			logger.debug("Connection successful");
			return stations;
		} else
		{
            logger.fatal("Could not connect/retrieve data from BOM");
			// could not connect
			return null;
		}

	}

	// List of fixed states needed to find appropriate stations
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

	/**
	 * 
	 * @param station
	 *            the station to fetch fine data from
	 * @return adds data to Station object supplied to method
	 * @throws IOException
	 * @throws MalformedURLException
	 * @throws JsonSyntaxException
	 * @throws JsonIOException
	 */
	public WthrSamplesFine getWthrLast72hr(Station station)
			throws JsonIOException, JsonSyntaxException, MalformedURLException, IOException
	{
        logger.debug("Starting Bom::getWthrLast72hr()");
		WthrSamplesFine samples = new WthrSamplesFine();

		// Weather data for Recent observations stored as Json format.
		HttpURLConnection connection = (HttpURLConnection) new URL(station.getJsonUrl()).openConnection();
		connection.setRequestProperty("Accept-Encoding", "gzip");
		connection.connect();
		String encoding = connection.getContentEncoding();
		BufferedReader reader;

		if (encoding != null && encoding.equalsIgnoreCase("gzip"))
		{
			reader = new BufferedReader(new InputStreamReader(new GZIPInputStream(connection.getInputStream())));
		} else
		{
			reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		}
		JsonArray rootArray = new JsonParser().parse(reader).getAsJsonObject().getAsJsonObject("observations")
				.getAsJsonArray("data");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

		for (JsonElement element : rootArray)
		{
			// Grabs all information through BOM's JSON Data
			JsonObject reading = element.getAsJsonObject();
				
			String localDateTimeFullString;
			LocalDateTime localDateTime;
			JsonElement localDateTimeFullJson = reading.get("local_date_time_full");
			if (localDateTimeFullJson.isJsonNull())
				localDateTimeFullString = null;
			else
				localDateTimeFullString = localDateTimeFullJson.getAsString();
				localDateTime = LocalDateTime.parse(localDateTimeFullString, formatter);

			String lat;
			JsonElement latJson = reading.get("lat");
			if (latJson.isJsonNull())
				lat = "-";
			else
				lat = latJson.getAsString();

			String lon;
			JsonElement lonJson = reading.get("lon");
			if (lonJson.isJsonNull())
				lon = "-";
			else
				lon = lonJson.getAsString();

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
			samples.add(new WthrSampleFine(localDateTime, lat, lon, apparentT, cloud, gustKmh,
					gustKt, airTemp, relHumidity, dewPt, windDir, windSpdKmh, windSpdKt));
		}
		return samples;
	}

	/**
	 * 
	 * @param station
	 *            Station to fetch coarse data from
	 * @param date
	 *            which month of data to fetch, yyyyMM
	 * @return adds data to Station object supplied to method
	 * @throws IOException
	 */
	public WthrSamplesDaily getWthrLastMonth(Station station, YearMonth date) throws IOException
	{
        logger.debug("Starting Bom::getWthrLastMonth()");
        String dateString = date.format(DateTimeFormatter.ofPattern("yyyyMM"));
		String thisMonth = YearMonth.now().format(DateTimeFormatter.ofPattern("yyyyMM"));
		WthrSamplesDaily samples = null;
		Boolean fileExists = false;
		File filePath = new File("csv/" + station.getName() + '-' + dateString + ".csv");
		File monthPath = new File("csv/" + station.getName() + '-' + thisMonth + ".csv");
		File dirPath = new File("csv");
		// Checks directory for any previous downloads
		if (!dirPath.isDirectory())
		{
			dirPath.mkdir();
		} else
		{
			for (String fileName : dirPath.list())
			{
				// Don't use local data for current month, could have been
				// updated
				if (fileName.equals(filePath.getName()) && !fileName.equals(monthPath.getName()))
					fileExists = true;
			}
		}
		// Grabs File, BOM represents this data in CSV format.
		if (!fileExists)
		{
			String[] nextLine;
			String url;
			String csvUrl;
			String htmlUrl = station.getHtmlUrl();
			int i = 0;
			int numTries = 3;
			Document doc = null;
			Boolean successfulConnection = false;

			while (i < numTries)
			{
				try
				{
					doc = Jsoup.connect(htmlUrl).get();
					successfulConnection = true;
					break;
				} catch (SocketTimeoutException e)
				{
                    logger.warn("WARNING :",e);
                    int attemptNum = i + 1;
					System.out.println("Attempting to Fetch CSV " + attemptNum);
				}
				i++;
			}

			if (successfulConnection)
			{
				Elements links = doc.select("a");
				for (Element link : links)
				{
					if (link.text().contains("Recent months"))
					{
						url = link.attr("href");
						csvUrl = baseUrl.substring(0, baseUrl.length()-1) + url.replace("dwo/", "dwo/" + dateString + "/text/")
								.replace("latest.shtml", dateString + ".csv");

						try (BufferedReader csvStream = new BufferedReader(
								new InputStreamReader(new URL(csvUrl).openStream())))
						{
							CSVReader csvReader = new CSVReader(csvStream);
							try (CSVWriter csvWriter = new CSVWriter(new FileWriter(filePath)))
							{
								while ((nextLine = csvReader.readNext()) != null)
								{
									csvWriter.writeNext(nextLine);
								}
							}
							csvReader.close();
						}
						// Some locations lack desired data
					}
				}
			} else
			{
                logger.fatal("Could not connect/retrieve data from BOM");
				// Could not connect
				return null;
			}
		}
		try (BufferedReader csvStream = new BufferedReader(new FileReader(filePath)))
		{
			CSVReader csvReader = new CSVReader(csvStream);
			logger.debug("Calling Bom::processCsv()");
			samples = processCsv(csvReader);
		}
		return samples;
	}

	/**
	 * [start, end), excludes end
	 * 
	 * @param station
	 *            Station to fetch coarse data from
	 * @param start
	 *            Starting month, inclusive
	 * @param end
	 *            Ending month, exclusive
	 * @return
	 * @throws IOException
	 */
	public WthrSamplesDaily getWthrRange(Station station, YearMonth start, YearMonth end) throws IOException
	{
        logger.debug("Starting Bom::getWthrRange()");
		WthrSamplesDaily samples = new WthrSamplesDaily();
		while (!start.equals(end))
		{
			WthrSamplesDaily sampleMonth = getWthrLastMonth(station, start);
			if (sampleMonth != null)
			{
				for (WthrSampleDaily sample : sampleMonth)
				{
					samples.addElement(sample);
				}
			}
			start = start.plusMonths(1);
		}

		return samples;
	}

	/**
	 * Processes csv supplied into WthrSampleDaily objects.
	 * 
	 * @param csvReader
	 *            access to csv data to process
	 * @return
	 * @throws IOException
	 */
	private WthrSamplesDaily processCsv(CSVReader csvReader) throws IOException
	{
        logger.debug("Starting Bom::processCsv()");
        String[] nextLine = null;
		WthrSamplesDaily samples = new WthrSamplesDaily();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");

		while ((nextLine = csvReader.readNext()) != null)
		{
			// The csv files are irregular, don't know how many lines to skip
			if (nextLine.length != 22 || nextLine[1].equals("Date"))
			{
				continue;
			}
			String dateString = nextLine[1];
			LocalDate date = LocalDate.parse(dateString, formatter);
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
	
	public float[] findAverageTemps(Station station)
	{

        logger.debug("Starting Bom::findAverageTemps()");
		WthrSamplesFine location = new WthrSamplesFine();
		try
		{
			location = this.getWthrLast72hr(station);
		} catch (IOException e)
		{
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Cannot access BoM JSON server");
			alert.setContentText("Please check your internet connection and try again");

			alert.showAndWait();
			e.printStackTrace();
			return null;
		}
		// Need seperate list to associate each time of day with a specific
		// "zone" or period
		WthrSamplesFine earlyMorns = new WthrSamplesFine();
		WthrSamplesFine middays = new WthrSamplesFine();
		WthrSamplesFine nights = new WthrSamplesFine();
		WthrSamplesFine lateNights = new WthrSamplesFine();
		
		// Hacky fix for now, delete when you figure it out Mark
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/hh:mma");

		// Goes through entire list of recorded location entries
		for (int i = location.size() - 1; i > 0; i--)
		{
			int time24hours = 0;

			// Splits the given time date into it's raw form of xxxxam or pm
			String[] dateSplit = location.get(i).getLocalDateTime().format(formatter).split("/");
			String[] timeSplit = dateSplit[1].split(":");
			String timeconcat = timeSplit[0] + timeSplit[1];
			String finalTime;
			// Checks if it contains am or pm, if pm, we need to change it to 24
			// hour format
			if (timeconcat.contains("PM"))
			{
				String[] pmHourTime = timeconcat.split("PM");
				time24hours = Integer.parseInt(pmHourTime[0]);
				finalTime = Integer.toString(time24hours + 1200);
			} else
			{
				String[] amHourTime = timeconcat.split("AM");
				time24hours = Integer.parseInt(amHourTime[0]);
				finalTime = Integer.toString(time24hours);
			}
			/*
			 * To deal with the late night period of 11pm to 4am we can't really
			 * check for a value in between 11pm and 4am so we make 4am into a
			 * 24 hour next day kind of thing from 2300 - 0400 the next day we
			 * make 0400 = 2800
			 */
			if (Integer.parseInt(finalTime) < 400)
			{
				int earlyHours = Integer.parseInt(finalTime);
				finalTime = Integer.toString(earlyHours + 2400);
			}

			if (500 <= Integer.parseInt(finalTime) & Integer.parseInt(finalTime) <= 1000)
			{
				earlyMorns.add(location.get(i));

			}
			// Weird situation checking inbetween periods of both am and pm
			// (11am - 4pm), 12pm now turns to 2400 and we don't want to check
			// 12
			// as that's 12 in the morning
			if (1100 <= Integer.parseInt(finalTime) & Integer.parseInt(finalTime) <= 1600
					& Integer.parseInt(finalTime) != 1200 || Integer.parseInt(finalTime) == 2400)
			{
				middays.add(location.get(i));
			}
			if (1700 <= Integer.parseInt(finalTime) & Integer.parseInt(finalTime) <= 2200)
			{
				nights.add(location.get(i));

			}
			// once again, we don't want 2400 as that's 12 pm, we want am for
			// this check
			if (2300 <= Integer.parseInt(finalTime) & Integer.parseInt(finalTime) <= 2800
					& Integer.parseInt(finalTime) != 2400 || Integer.parseInt(finalTime) == 1200)
			{
				lateNights.add(location.get(i));
			}

		}

		float[] sortedList = new float[4];
		sortedList[0] = averageTemp(earlyMorns);
		sortedList[1] = averageTemp(middays);
		sortedList[2] = averageTemp(nights);
		sortedList[3] = averageTemp(lateNights);

		return sortedList;
	}

	float averageTemp(WthrSamplesFine samples)
	{
        logger.debug("Starting Bom::averageTemp()");
		float average = 0;
		for (WthrSampleFine index : samples)
		{
			average += Float.parseFloat(index.getAirTemp());
		}
		average = average / samples.size();
		return average;
	}
}
