package data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
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

public class Station implements Serializable
{
	/**
	 * 
	 */
	private String name;
	private String jsonUrl;
	private String htmlUrl;
	private String state;

	public Station(String name, String jsonUrl, String htmlUrl, String state)
	{
		this.name = name;
		this.jsonUrl = jsonUrl;
		this.htmlUrl = htmlUrl;
		this.state = state;
	}

	public Station(Station loc)
	{
		// TODO Auto-generated constructor stub
	}
	
	public boolean equals(Station station)
	{
		return this.name.equals(station.name) 
				&& this.jsonUrl.equals(station.jsonUrl) 
				&& this.htmlUrl.equals(station.htmlUrl)
				&& this.state.equals(station.state);
	}

	// Function for filling Empty Station with data (periods of 30 minutes up to 3 days)
	@Deprecated
	public WthrSamplesFine getWthrLast72hr()
	{
		WthrSamplesFine samples = new WthrSamplesFine();
	    //Weather data for Recent observations stored as Json format.
		try
		{
			JsonArray rootArray = new JsonParser()
					.parse(new BufferedReader(new InputStreamReader(new URL(jsonUrl).openStream()))).getAsJsonObject()
					.getAsJsonObject("observations").getAsJsonArray("data");
			for (JsonElement element : rootArray)
			{
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
		catch (JsonIOException e)
		{
		} catch (JsonSyntaxException e)
		{
		} catch (MalformedURLException e)
		{
		} catch (IOException e)
		{
		}
		return samples;
	}
    // Function For grabbing broader (later) historical observations
	// Month in the format YYYYMM, 201603 would be March 2016.
	@Deprecated
	public WthrSamplesDaily getWthrLastMonth(String month) throws IOException
	{
		WthrSamplesDaily samples = null;
		Boolean fileExists = false;
		File filePath = new File("data/"+ this.getName() + '-' +month + ".csv");
		File dirPath = new File("data");
		//Checks directory for any previous downloads
		if (!dirPath.isDirectory()) {
			dirPath.mkdir();
		}
		else {
			for (String fileName: dirPath.list()) {
				if (fileName.equals(month))
					fileExists = true;
			}
		}
		//Grabs File, BOM represents this data in CSV format.
		if (!fileExists) {
			String[] nextLine;
			String url;
			String csvUrl;
			String htmlUrl = this.getHtmlUrl();
			Document doc = Jsoup.connect(htmlUrl).get();
			Elements links = doc.select("a");
			for (Element link : links)
			{
				if (link.text().contains("Recent months"))
				{
					url = link.attr("href");
					csvUrl = "http://www.bom.gov.au"
							+ url.replace("dwo/", "dwo/" + month + "/text/").replace("latest.shtml", month + ".csv");
					
					// Testing code
					// System.out.println(this.getName() + " " + csvUrl);
					
					try  (BufferedReader csvStream = new BufferedReader(new InputStreamReader(new URL(csvUrl).openStream())))
					{
						CSVReader csvReader = new CSVReader(csvStream);
						try (CSVWriter csvWriter = new CSVWriter(new FileWriter(filePath))) {
							while ((nextLine = csvReader.readNext()) != null) {
								csvWriter.writeNext(nextLine);
							}
						}
						csvReader.close();
					}
					//Some locations lack desired data
					catch(FileNotFoundException e) {
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
	/*Scrapes data from CSV file on line by line basis
	  And adds to object*/
	@Deprecated
	private WthrSamplesDaily processCsv(CSVReader csvReader) throws IOException {
		String[] nextLine = null;
		WthrSamplesDaily samples = new WthrSamplesDaily();
		
		
		while ((nextLine = csvReader.readNext()) != null)
		{
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
					maxWindGustTime, temp9am, relHumidity9am, cloud9am, windDir9am, windSpd9am,
					meanSeaLevelPressure9am, temp3pm, relHumidity3pm, cloud3pm, windDir3pm, windSpd3pm,
					meanSeaLevelPressure3pm));
		}
		
		return samples;
	}

        public float[] findAverageTemps(Station station){
        
        WthrSamplesFine location = station.getWthrLast72hr();

        //Need seperate list to associate each time of day with a specific "zone" or period
        WthrSamplesFine earlyMorns = new WthrSamplesFine();
        WthrSamplesFine middays = new WthrSamplesFine();
        WthrSamplesFine nights = new WthrSamplesFine();
        WthrSamplesFine lateNights = new WthrSamplesFine();
        
        //Goes through entire list of recorded location entries
        for(int i=location.size()-1 ; i > 0; i--)
        {
        	int time24hours = 0;
        	
        	//Splits the given time date into it's raw form of xxxxam or pm
        	String[] dateSplit = location.get(i).getLocalDateTime().split("/");
        	String[] timeSplit = dateSplit[1].split(":");
        	String timeconcat = timeSplit[0] + timeSplit[1];
        	String finalTime;
        	//Checks if it contains am or pm, if pm, we need to change it to 24 hour format
        	if(timeconcat.contains("pm"))
        	{
        	String[] pmHourTime = timeconcat.split("pm");
        	time24hours = Integer.parseInt(pmHourTime[0]);
        	finalTime = Integer.toString(time24hours + 1200);
        	}
        	else{
        	String[] amHourTime = timeconcat.split("am");
        	time24hours = Integer.parseInt(amHourTime[0]);
        	finalTime = Integer.toString(time24hours);
        	}
        	/* To deal with the late night period of 11pm to 4am
        	 * we can't really check for a value in between 11pm and 4am 
        	 * so we make 4am into a 24 hour next day kind of thing
        	 * from 2300 - 0400 the next day we make 0400 = 2800 
        	 */
        	if(Integer.parseInt(finalTime) < 400)
        	{
        		int earlyHours = Integer.parseInt(finalTime);
        		finalTime = Integer.toString(earlyHours + 2400);
        	}
        	
        	if(500 <= Integer.parseInt(finalTime) & Integer.parseInt(finalTime) <= 1000)
        	{        		
        		earlyMorns.add(location.get(i));
        		
        	}
        	//Weird situation checking inbetween periods of both am and pm (11am - 4pm), 12pm now turns to 2400 and we don't want to check 12
        	//as that's 12 in the morning
        	if(1100 <= Integer.parseInt(finalTime) & Integer.parseInt(finalTime) <= 1600 & Integer.parseInt(finalTime) != 1200 
        	   || Integer.parseInt(finalTime) == 2400)
        	{
        		middays.add(location.get(i));	
        	}
        	if(1700 <= Integer.parseInt(finalTime) & Integer.parseInt(finalTime) <= 2200)
        	{
        		nights.add(location.get(i));
        	
        	}
        	//once again, we don't want 2400 as that's 12 pm, we want am for this check
        	if(2300 <= Integer.parseInt(finalTime) & Integer.parseInt(finalTime) <= 2800 & Integer.parseInt(finalTime) != 2400 
             	   || Integer.parseInt(finalTime) == 1200)
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
		float average = 0;
		for(WthrSampleFine index : samples)
		{
			average += Float.parseFloat(index.getAirTemp());
		}
		average = average/samples.size();
		return average;
	}
	
	public String getName()
	{
		return name;
	}

	public String getJsonUrl()
	{
		return jsonUrl;
	}

	public String getHtmlUrl()
	{
		return htmlUrl;
	}

	public String getState()
	{
		return state;
	}
}
