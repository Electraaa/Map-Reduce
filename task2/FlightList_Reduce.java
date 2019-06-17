package cs3ac16.mapreduce.task2;

import cs3ac16.mapreduce.buildingblocks.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class FlightList_Reduce extends Reducer {

	@Override
	protected KeyValuePair reduce(String key, ArrayList<String> values) {
		String[] col = values.get(0).split(",");

		/*
		Create a list of flights based on the Flight id, 
		this output should include the passenger Id, 
		relevant IATA/FAA codes, the departure time, 
		the arrival time (times to be converted to HH:MM:SS format), 
		and the flight times.
		 */

		String airportStart = col[1];
		String airportEnd = col[2];
		String departureTime = col[3];
		String duration = col[4];

		// Parse varialbes to integers
		int timestamp_int = Integer.parseInt(departureTime, 10);
		int duration_int = Integer.parseInt(duration, 10);

		Date departed = new Date((long) timestamp_int * 1000);
		String departedAt = new SimpleDateFormat("HH:mm:ss, dd-MM-yyyy").format(departed);

		Date arrived = new Date(departed.getTime() + (duration_int * 60 * 1000));
		String arrivedAt = new SimpleDateFormat("HH:mm:ss, dd-MM-yyyy").format(arrived);

		int hours = duration_int / 60;
		int mins = duration_int % 60;

		String durationOfFlight = String.format("%d hours and %d minutes", hours, mins);

		ArrayList<String> passengerId = new ArrayList<>();

		for (String value : values) {
			passengerId.add(value.split(",")[0]);
		}

		String passengers = String.join("\n\t", passengerId);
		String val = "";

		try {
			val = String.format(
					" from %s to %s\n" +
					"Departed: %s (GMT)\n" +
					"Arrived: %s (GMT)\n" +
					"Duration: %s\n" +
					"Passengers: \n\t%s\n\n",
					airportStart,
					airportEnd,
					departedAt,
					arrivedAt,
					durationOfFlight,
					passengers);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new KeyValuePair(key, val);
	}
}