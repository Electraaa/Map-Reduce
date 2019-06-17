package cs3ac16.mapreduce.task4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import cs3ac16.mapreduce.buildingblocks.*;

class Main {

	public static void main(String[] args) throws Exception {
		Job job = new Job();

		job.setMapper(Airmiles_Map.class);
		job.setReducer(Airmiles_Reduce.class);

		BufferedReader input = new BufferedReader(new FileReader("AComp_Passenger_data.csv"));
		BufferedWriter output = new BufferedWriter(new FileWriter("Airmiles.txt"));
		job.setInput(input);
		job.setOutput(output);

		job.run();
	}

}
