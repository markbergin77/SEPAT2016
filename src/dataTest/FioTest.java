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
		StationList stations = Bom.getAllStations();
		Station testStation = stations.get(0);
		// Need to fetch data to access lat and lon
		WthrSamplesFine testTemp = Bom.getWthrLast72hr(testStation);
		
		FioSamplesDaily testSamples = Fio.getFioDaily(testStation);
		
		System.out.println("Readings for: " + testStation.getName());
		for (FioSampleDaily sample: testSamples) {
			System.out.println("Time: " + sample.getTime());
			System.out.println("    Min: " + sample.getTempMin());
			System.out.println("    Max: " + sample.getTempMax());
		}
	}
}
