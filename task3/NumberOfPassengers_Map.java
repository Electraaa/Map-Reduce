package cs3ac16.mapreduce.task3;

import cs3ac16.mapreduce.buildingblocks.*;
import java.util.ArrayList;

public class NumberOfPassengers_Map extends Mapper {

	protected ArrayList<KeyValuePair> map(String line) {
		
		//Second csv value gives the flight Id

		String[] col = line.split(",");

		ArrayList<KeyValuePair> results = new ArrayList<>();

		if (col[0] != line) {
			String flightId = col[1];

			if (flightId.matches("[A-Z]{3}[0-9]{4}[A-Z]")); {
				results.add(new KeyValuePair(flightId, String.valueOf(1)));
			}
		}

		return results;
	}
}
