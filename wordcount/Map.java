package cs3ac16.mapreduce.wordcount;
import java.util.ArrayList;
import cs3ac16.mapreduce.buildingblocks.*;


public class Map extends Mapper {

	public ArrayList<KeyValuePair> map(String line) {
		String[] words = line.split(" ");
		ArrayList<KeyValuePair> results = new ArrayList<>();

		for (String word : words) {
			word = word.replaceAll("[^a-zA-Z]", "").toLowerCase();

			if (word.length() != 0) {
				results.add(new KeyValuePair(word, String.valueOf(1)));
			}
		}

		return results;
	}
}