package cs3ac16.mapreduce.task1;
import java.util.ArrayList;
import cs3ac16.mapreduce.buildingblocks.*;

public class flightFromAirport_Map3 extends Mapper{

	@Override
	protected ArrayList<KeyValuePair> map(String line) {

		String[] col = line.split(",");

		ArrayList<KeyValuePair> results = new ArrayList<>();

		if (col[0] != line) {
			String code = col[0];
			String subtotal = col[1];

			if (code.matches("[A-Z]{3}")) {
				results.add(new KeyValuePair(code, subtotal));
			}
		}

		return results;
	}
}
