package backend;

public class Main {
	
	public static void main(String[] args) {
		Controller controller = new Controller();
		//controller.createObjectFile();
		controller.readObjectFile();
		controller.fetchData(controller.getLocations().get(0));
		// testing code
		System.out.println(controller.getLocations().get(0).getName());
		System.out.println(controller.getLocations().get(0).getData().get(0).getAirTemp());
		
	}
}
