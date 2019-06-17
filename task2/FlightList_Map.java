package cs3ac16.mapreduce.task2;
import cs3ac16.mapreduce.buildingblocks.*;
import java.util.ArrayList;

public class FlightList_Map extends Mapper {

	@Override
	protected ArrayList<KeyValuePair> map(String line) {
		// 0 = Passenger id : XXXnnnnXXn
		// 1 = Flight id : XXXnnnnX
		// 2 = From airport IATA/FAA code: XXX
		// 3 = Destination airport IATA/FAA code: XXX 
		// 4 = Departure time (GMT): n[10] Unix epoch time
		// 5 = Duration (mins): n[1..4] 

		String[] col = line.split(",");

		ArrayList<KeyValuePair> results = new ArrayList<>();

		if (col[0] != line) {
			boolean valid = true;

			String passengerId = col[0];
			valid &= passengerId.matches("[A-Z]{3}[0-9]{4}[A-Z]{2}[0-9]");

			String flightId = col[1];
			valid &= flightId.matches("[A-Z]{3}[0-9]{4}[A-Z]");

			String airportStart = col[2];
			valid &= airportStart.matches("[A-Z]{3}");

			String airportEnd = col[3];
			valid &= airportEnd.matches("[A-Z]{3}");

			String departureTime = col[4];
			valid &= departureTime.matches("[0-9]{10}");

			String duration = col[5];
			valid &= duration.matches("[0-9]{1,4}");

			if (valid) {
				results.add(new KeyValuePair(flightId, String.format("%s,%s,%s,%s,%s",
						passengerId, airportStart, airportEnd, departureTime, duration)));
			}
		}

		return results;
	}
}
