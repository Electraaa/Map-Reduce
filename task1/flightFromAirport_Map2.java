package cs3ac16.mapreduce.task1;
import java.util.ArrayList;
import cs3ac16.mapreduce.buildingblocks.*;

public class flightFromAirport_Map2 extends Mapper {

	protected ArrayList<KeyValuePair> map(String line) {
		
		// Second array value is Flight id
		// Third array value is airport IATA/FAA code

		String[] col = line.split(",");

		ArrayList<KeyValuePair> results = new ArrayList<>();

		if (col[0] != line) {
			String flightId = col[1];
			String airportCode = col[2];

			if (airportCode.matches("[A-Z]{3}") && flightId.matches("[A-Z]{3}[0-9]{4}[A-Z]")) {
				results.add(new KeyValuePair(airportCode, flightId));
			}
		}

		return results;
	}
}
