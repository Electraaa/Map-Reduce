package cs3ac16.mapreduce.buildingblocks;

import java.util.ArrayList;
import java.util.concurrent.Callable;

public abstract class Mapper implements Callable {
	private String line;
	private boolean verbose;

	/**
	 * Maps a line from the data to a KeyValuePair
	 *
	 * @param line A line from the data
	 * @return An ArrayList of KeyValuePairs
	 */
	protected abstract ArrayList<KeyValuePair> map(String line);

	/**
	 * Set the input data to be the line read in
	 *
	 * @param line A line from the data
	 */
	void setInput(String line) {
		this.line = line;
	}

	/**
	 * Implements the Callable interface for multi-threading by the job.
	 *
	 * @return The return value of the map function
	 */
	@Override
	public ArrayList<KeyValuePair> call() {
		if (this.verbose) {
			System.out.println("Map ( " + this.line + " ) - " + Thread.currentThread().getName());
		}
		return this.map(this.line);
	}

	public void setVerbose(boolean verbose) {
		this.verbose = verbose;
	}
}