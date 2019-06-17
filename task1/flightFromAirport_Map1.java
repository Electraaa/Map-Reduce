package cs3ac16.mapreduce.task1;
import java.util.ArrayList;
import cs3ac16.mapreduce.buildingblocks.*;

public class flightFromAirport_Map1 extends Mapper {

	@Override
	protected ArrayList<KeyValuePair> map(String line) {
		
		
		// Airport IATA/FAA code: XXX
		// This code is the second value in csv file
		
		String[] col = line.split(",");

		ArrayList<KeyValuePair> results = new ArrayList<>();

		if (col[0] != line) {
			String airportCode = col[1];

			if (airportCode.matches("[A-Z]{3}")) {
				results.add(new KeyValuePair(airportCode, String.valueOf(0)));
			}
		}
		return results;
	}
}