package backend;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.Vector;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Controller {
	
	public static void createObjectFile() {
		String locationURLs = "/home/uni/git/SEPAT2016/src/backend/locations";
		String locationURL;
		
		Vector<Location> locations = new Vector<Location>();
		
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(locationURLs))) {
			int i = 0;
			while((locationURL = bufferedReader.readLine()) != null) {
				JsonObject rootObject = new JsonParser().parse(new BufferedReader(new InputStreamReader(new URL(locationURL).openStream())))
						.getAsJsonObject().getAsJsonObject("observations");
				
				JsonObject header = rootObject.getAsJsonArray("header").get(0).getAsJsonObject();
				// Only getting first element to construct file of object skeletons
				JsonObject data = rootObject.getAsJsonArray("data").get(0).getAsJsonObject();
				
				Location newLocation = new Location(header.get("name").getAsString(), locationURL, data.get("lat").getAsString(), data.get("lon").getAsString(), header.get("state").getAsString());
				locations.addElement(newLocation);
				i++;
				System.out.print(i+"\r");
			}
		}
		// TODO
		catch (IOException e) {};
		
		try (ObjectOutputStream out = new ObjectOutputStream(
		        new BufferedOutputStream(new FileOutputStream("src/backend/objects")))) {
			
			out.writeObject(locations);
		}
		// TODO
		catch (IOException e) {};
	}
	
	public static void readObjectFile() {
		
		
		try (ObjectInputStream in = new ObjectInputStream(
		        new BufferedInputStream(new FileInputStream("src/backend/objects")))) {
			
			Vector<Location> locations = (Vector<Location>) in.readObject();
			
			int i = 0;
		    for(Location location: locations) {
		    	i++;
		    	System.out.println(i + " " + location.getName());
		    }
		}
		// TODO
		catch (FileNotFoundException e) {}
		catch (IOException e) {}
		catch (ClassNotFoundException e) {}
	}
	
	public static void fetchData(Location location) {
		
	}

}
