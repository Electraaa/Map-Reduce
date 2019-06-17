package cs3ac16.mapreduce.task1;

import cs3ac16.mapreduce.buildingblocks.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class flightFromAirport_Reduce2 extends Reducer {

	@Override
	protected KeyValuePair reduce(String key, ArrayList<String> values) {
		Set<String> uniques = new HashSet<>();
		uniques.addAll(values);
		return new KeyValuePair(key, String.valueOf(uniques.size()));
	}
}