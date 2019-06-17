package cs3ac16.mapreduce.buildingblocks;

import java.util.ArrayList;
import java.util.concurrent.Callable;

public abstract class Reducer implements Callable {
	private String key;
	private ArrayList<String> values;
	private boolean verbose;

	/**
	 * Reduce the mapped output
	 *
	 * @param key    The key from the mapped output
	 * @param values An ArrayList values from the mapped output
	 * @return An ArrayList KeyValuePairs
	 */
	protected abstract KeyValuePair reduce(String key, ArrayList<String> values);

	/**
	 * Set the input data to the output from the mapper
	 *
	 * @param key    The key from the mapped output.
	 * @param values An ArrayList values from the mapped output
	 */
	void setData(String key, ArrayList<String> values) {
		this.key = key;
		this.values = values;
	}

	/**
	 * Implements the Callable interface for multi-threading by the job.
	 *
	 * @return The return value of reduce function
	 */
	@Override
	public KeyValuePair call() {
		if (this.verbose) {
			System.out.println("Reduce - ( " + this.key + ", " + values.size() + ") - " + Thread.currentThread().getName());
		}

		return this.reduce(this.key, this.values);
	}

	public void setVerbose(boolean verbose) {
		this.verbose = verbose;
	}
}
