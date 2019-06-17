package cs3ac16.mapreduce.task3;


import cs3ac16.mapreduce.buildingblocks.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

class Main {

	public static void main(String[] args) throws Exception {
		Job job = new Job();

		job.setMapper(NumberOfPassengers_Map.class);
		job.setReducer(NumberOfPassengers_Reduce.class);

		BufferedReader input = new BufferedReader(new FileReader("AComp_Passenger_data.csv"));
		BufferedWriter output = new BufferedWriter(new FileWriter("NumberOfPassengersPerFlight.txt"));
		job.setInput(input);
		job.setOutput(output);

		job.run();
	}

}
