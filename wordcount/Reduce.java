package cs3ac16.mapreduce.wordcount;
import java.util.ArrayList;
import cs3ac16.mapreduce.buildingblocks.*;

public class Reduce extends Reducer {

	public KeyValuePair reduce(String key, ArrayList<String> values) {
		int count = 0;
		for (String value : values) {
			count += Integer.parseInt(value, 10);
		}

		return new KeyValuePair(key, String.valueOf(count));
	}
}