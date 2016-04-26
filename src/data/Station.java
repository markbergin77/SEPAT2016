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

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Station implements Serializable
{
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
		return this.name.equals(station.name) && this.jsonUrl.equals(station.jsonUrl)
				&& this.htmlUrl.equals(station.htmlUrl) && this.state.equals(station.state);
	}

	public float[] findAverageTemps(Station station)
	{

		WthrSamplesFine location = new WthrSamplesFine();
		try
		{
			location = Bom.getWthrLast72hr(station);
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

		// Goes through entire list of recorded location entries
		for (int i = location.size() - 1; i > 0; i--)
		{
			int time24hours = 0;

			// Splits the given time date into it's raw form of xxxxam or pm
			String[] dateSplit = location.get(i).getLocalDateTime().split("/");
			String[] timeSplit = dateSplit[1].split(":");
			String timeconcat = timeSplit[0] + timeSplit[1];
			String finalTime;
			// Checks if it contains am or pm, if pm, we need to change it to 24
			// hour format
			if (timeconcat.contains("pm"))
			{
				String[] pmHourTime = timeconcat.split("pm");
				time24hours = Integer.parseInt(pmHourTime[0]);
				finalTime = Integer.toString(time24hours + 1200);
			} else
			{
				String[] amHourTime = timeconcat.split("am");
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
		float average = 0;
		for (WthrSampleFine index : samples)
		{
			average += Float.parseFloat(index.getAirTemp());
		}
		average = average / samples.size();
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
