package cs3ac16.mapreduce.task3;

import cs3ac16.mapreduce.buildingblocks.*;
import java.util.ArrayList;

public class NumberOfPassengers_Reduce extends Reducer {

	@Override
	protected KeyValuePair reduce(String key, ArrayList<String> values) {

		int total = 0;

		for (String count : values) {
			total += Integer.parseInt(count, 10);
		}

		return new KeyValuePair(key, String.valueOf(total));
	}
}