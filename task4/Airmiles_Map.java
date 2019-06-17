package cs3ac16.mapreduce.task4;

import cs3ac16.mapreduce.buildingblocks.*;
import java.util.ArrayList;

public class Airmiles_Map extends Mapper {

	@Override
	protected ArrayList<KeyValuePair> map(String line) {

		String[] col = line.split(",");

		ArrayList<KeyValuePair> results = new ArrayList<>();

		if (!col[0].equals(line)) {
			String passengerId = col[0];
			String flightId = col[1];
			String airportStart = col[2];
			String airportEnd = col[3];

			if (airportStart.matches("[A-Z]{3}") && airportEnd.matches("[A-Z]{3}")) {
				if (flightId.matches("[A-Z]{3}[0-9]{4}[A-Z]")) {
					results.add(new KeyValuePair(flightId, airportStart + "-" + airportEnd));
				}
				if (passengerId.matches("[A-Z]{3}[0-9]{4}[A-Z]{2}[0-9]")) {
					results.add(new KeyValuePair(passengerId, airportStart + "-" + airportEnd));
				}
			}
		}

		return results;
	}
}