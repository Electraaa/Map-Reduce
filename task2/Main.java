package cs3ac16.mapreduce.task2;

import cs3ac16.mapreduce.buildingblocks.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

class Main {

	public static void main(String[] args) throws Exception {
		Job job = new Job();

		job.setMapper(FlightList_Map.class);
		job.setReducer(FlightList_Reduce.class);

		BufferedReader input = new BufferedReader(new FileReader("AComp_Passenger_data.csv"));
		BufferedWriter output = new BufferedWriter(new FileWriter("FlightList.txt"));
		job.setInput(input);
		job.setOutput(output);

		job.run();
	}

}
