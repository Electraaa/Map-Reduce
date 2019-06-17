package cs3ac16.mapreduce.buildingblocks;

public class KeyValuePair {

	private final String key;
	private final String value;

	/**
	 * Creates a new immutable key value pair.
	 *
	 * @param key   The key.
	 * @param value The value.
	 */
	public KeyValuePair(String key, String value) {
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return this.key;
	}

	public String getValue() {
		return this.value;
	}

	/**
	 * Converts the key value pair into a CSV-style string.
	 *
	 * @return String representation.
	 */
	public String toString() {
		return this.key + "," + this.value;
	}
}
