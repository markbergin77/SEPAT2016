package backend;

import java.io.IOException;

import bomData.JSONScraper;

public class Main 
{

	public static void main(String[] args) throws IOException {
		long startTotalTime = System.nanoTime();
		
		Controller controller = new Controller();
		controller.createLocations();
		long startFetchTime = System.nanoTime();
		//controller.fetchData(controller.getLocations().get(0));
		//System.out.println(controller.getLocations().get(0).getName());
		long endFetchTime = System.nanoTime();
		long endTotalTime = System.nanoTime();
		double fetchTime = ((double) (endFetchTime - startFetchTime))/Math.pow(10, 9);
		double totalTime = ((double) (endTotalTime - startTotalTime))/Math.pow(10, 9);
		
		System.out.println("Fetch Time: " + fetchTime + " sec");
		System.out.println("Total Time: " + totalTime + " sec");
	}
}