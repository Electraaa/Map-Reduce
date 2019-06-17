package cs3ac16.mapreduce.buildingblocks;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Job {

	private final HashMap<String, ArrayList<String>> mapResults = new HashMap<>();
	private final HashMap<String, String> reduceResults = new HashMap<>();
	private Class<? extends Mapper> mapperClass;
	private Class<? extends Reducer> reducerClass;
	private BufferedReader input;
	private BufferedWriter output;
	private boolean verbose;

	public Job() {
		this(true);
	}

	public Job(boolean verbose) {
		this.verbose = verbose;
	}

	/**
	 * Sets the input stream for the job.
	 *
	 * @param input Input stream read in each line at a time.
	 */
	public void setInput(BufferedReader input) {
		this.input = input;
	}

	/**
	 * Configures the Mapper class.
	 *
	 * @param mapperClass A class that extends Mapper.
	 */
	public void setMapper(Class<? extends Mapper> mapperClass) {
		this.mapperClass = mapperClass;
	}

	/**
	 * Configures the Reducer class.
	 *
	 * @param reducerClass A class that extends Reducer.
	 */
	public void setReducer(Class<? extends Reducer> reducerClass) {
		this.reducerClass = reducerClass;
	}

	/**
	 * Run the MapReduce Algorithm.
	 */
	public void run() {
		try {
			this.map();
			this.reduce();
			this.end();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Runs the Map's map method using a multi-threaded pool.
	 *
	 * @throws NoSuchMethodException
	 * @throws IOException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws InstantiationException
	 * @throws ExecutionException
	 * @throws InterruptedException
	 */
	private void map() throws NoSuchMethodException, IOException, IllegalAccessException, InvocationTargetException, InstantiationException, ExecutionException, InterruptedException {
		String line;
		BufferedReader input = this.input;
		Constructor constructor = this.mapperClass.getConstructor();

		int Threads = Runtime.getRuntime().availableProcessors();
		ExecutorService pool = Executors.newFixedThreadPool(Threads);
		Set<Future<ArrayList<KeyValuePair>>> set = new HashSet<>();

		while ((line = input.readLine()) != null) {
			Mapper mapper = (Mapper) constructor.newInstance();
			mapper.setInput(line);
			mapper.setVerbose(this.verbose);
			Future f = pool.submit(mapper);
			set.add(f);
		}

		for (Future<ArrayList<KeyValuePair>> future : set) {
			this.appendMapResults(future.get());
		}

		pool.shutdown();
	}

	/**
	 * Runs the Reduce's reduce method method using a multi-threaded pool.
	 *
	 * @throws NoSuchMethodException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws InstantiationException
	 * @throws ExecutionException
	 * @throws InterruptedException
	 */
	private void reduce() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, ExecutionException, InterruptedException {
		Constructor constructor = this.reducerClass.getConstructor();

		int Threads = Runtime.getRuntime().availableProcessors();
		ExecutorService pool = Executors.newFixedThreadPool(Threads);
		Set<Future<KeyValuePair>> set = new HashSet<>();

		for (HashMap.Entry<String, ArrayList<String>> entry : this.mapResults.entrySet()) {
			Reducer reducer = (Reducer) constructor.newInstance();
			reducer.setData(entry.getKey(), entry.getValue());
			reducer.setVerbose(this.verbose);

			Future f = pool.submit(reducer);
			set.add(f);
		}

		for (Future<KeyValuePair> future : set) {
			this.appendReduceResult(future.get());
		}

		pool.shutdown();
	}

	/**
	 * Write the result to the output buffer.
	 */
	private void end() throws IOException {
		String contents = "";

		for (HashMap.Entry<String, String> result : this.reduceResults.entrySet()) {
			KeyValuePair keyValuePair = new KeyValuePair(result.getKey(), result.getValue());
			contents += String.format("%s\n", keyValuePair);
		}

		BufferedWriter output = this.output;
		output.write(contents);
		output.close();
	}

	/**
	 * Sets the output buffer for the results
	 *
	 * @param output Buffer
	 */
	public void setOutput(BufferedWriter output) {
		this.output = output;
	}

	/**
	 * Writes an ArrayList of KeyValuePairs from the Mapping process
	 * into memory to be used by the reducer. 
	 * 
	 * @param results An ArrayList of zero or more KeyValuePairs.
	 */
	private void appendMapResults(ArrayList<KeyValuePair> results) {
		for (KeyValuePair result : results) {

			ArrayList<String> values = this.mapResults.get(result.getKey());
			if (values == null) {
				this.mapResults.put(result.getKey(), new ArrayList<>());
			}

			this.mapResults.get(result.getKey()).add(result.getValue());
		}
	}

	/**
	 * Writes the a KeyValuePair to memory from reducing. 
	 * 
	 * @param result A KeyValuePair formed from the reduction step
	 */
	private void appendReduceResult(KeyValuePair result) {
		this.reduceResults.put(result.getKey(), result.getValue());
	}
}
