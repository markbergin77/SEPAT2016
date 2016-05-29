package dataTest;

import java.io.IOException;

import data.Bom;
import data.Fio;
import data.Station;
import data.StationList;
import data.samples.FioSampleDaily;
import data.samples.FioSamplesDaily;
import data.samples.WthrSamplesFine;

public class FioTest
{
	public static void main(String[] args) throws IOException 
	{
		Bom bom = new Bom();
		Fio fio = new Fio();
		StationList stations = bom.getAllStations();
		Station testStation = stations.get(0);
		// Need to fetch data to access lat and lon
		
		WthrSamplesFine wthrSamplesFine = bom.getWthrLast72hr(testStation);
		String lat = wthrSamplesFine.get(0).getLat();
		String lon = wthrSamplesFine.get(0).getLon();
		FioSamplesDaily testSamples = fio.getFioDaily(testStation, lat, lon);
		
		System.out.println("Readings for: " + testStation.getName());
		for (FioSampleDaily sample: testSamples) {
			System.out.println("Time: " + sample.getDate());
			System.out.println("    Min: " + sample.getTempMin());
			System.out.println("    Max: " + sample.getTempMax());
			System.out.println("    9am: " + sample.getTemp9am());
			System.out.println("    3pm: " + sample.getTemp3pm());
		}
	}
}
