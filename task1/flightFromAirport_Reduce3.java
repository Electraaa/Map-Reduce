package cs3ac16.mapreduce.task1;
import java.util.ArrayList;
import cs3ac16.mapreduce.buildingblocks.*;

public class flightFromAirport_Reduce3 extends Reducer {

	@Override
	protected KeyValuePair reduce(String key, ArrayList<String> values) {

		int count = 0;

		for (String val : values) {
			count += Integer.parseInt(val, 10);
		}

		return new KeyValuePair(key, String.valueOf(count));
	}
}
