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
		double docTimeSum = 0;
		for(String state: states) {
			long startDocTime = System.nanoTime();
			Document doc = Jsoup.connect("http://www.bom.gov.au/" + state + "/observations/" + state + "all.shtml").get();
			long endDocTime = System.nanoTime();
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
			double docTime = ((double) (endDocTime - startDocTime))/Math.pow(10, 9);
			docTimeSum += docTime;
			System.out.println(state + " Doc Time: " + docTime + " sec");
		}
		System.out.println("Total Doc Time: " + docTimeSum + " sec");
		out.close();
	}
}
