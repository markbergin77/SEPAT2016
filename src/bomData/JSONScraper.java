package bomData;
import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class JSONScraper {
	public static void main(String[] args) throws IOException {
		scrape();
	}
	
	public static void scrape() throws IOException {
		// RMIT Proxy Settings
		// System.setProperty("http.proxyHost", "aproxy.rmit.edu.au");
		// System.setProperty("http.proxyPort", "8080");
		
		String[] states = {"vic", "nsw", "tas", "wa", "sa", "nt", "qld", "ant"};
		String locationURLs = "src/bomData/locations";
		String relURL;
		String location;
		
		BufferedWriter out = new BufferedWriter(new FileWriter(locationURLs));
		double FileTimeSum = 0;
		double ProcessingTimeSum = 0;
		for(String state: states) {
			long startFileTime = System.nanoTime();
			Document doc = Jsoup.connect("http://www.bom.gov.au/" + state + "/observations/" + state + "all.shtml").get();
			long endFileTime = System.nanoTime();
			long startProcessingTime = System.nanoTime();
			Elements tbodies = doc.select("tbody");
			Elements links = tbodies.select("a");
			for (Element link: links) {
				relURL = link.attr("href");
				if (relURL.contains("products") && !relURL.contains("#")) {
					relURL = "http://www.bom.gov.au" + relURL.replace("products", "fwo").replace("shtml", "json");
					location = link.text();
					out.write(location + "#" + relURL + "\n");
				}			
			}
			long endProcessingTime = System.nanoTime();
			double FileTime = ((double) (endFileTime - startFileTime))/Math.pow(10, 9);
			double ProcessingTime = ((double) (endProcessingTime - startProcessingTime))/Math.pow(10, 9);
			FileTimeSum += FileTime;
			ProcessingTimeSum += ProcessingTime;
		}
		System.out.println("Scrape File Time: " + FileTimeSum + " sec");
		System.out.println("Scrape Processing Time: " + ProcessingTimeSum + " sec");
		out.close();
	}
}
