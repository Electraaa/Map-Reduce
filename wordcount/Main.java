package cs3ac16.mapreduce.wordcount;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import cs3ac16.mapreduce.buildingblocks.*;


class Main {

	public static void main(String[] args) throws Exception {
		Job job = new Job();

		job.setMapper(Map.class);
		job.setReducer(Reduce.class);

		BufferedReader input = new BufferedReader(new FileReader("data.txt"));
		BufferedWriter output = new BufferedWriter(new FileWriter("word_count_output.txt"));
		job.setInput(input);
		job.setOutput(output);

		job.run();
	}
}