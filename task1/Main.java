package cs3ac16.mapreduce.task1;

import cs3ac16.mapreduce.buildingblocks.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

class Main {

	public static void main(String[] args) throws Exception {
		Main.job1();
		Main.job2();
		Main.job3();
	}

	//Create data highlighting all airports with a flight count of 0
	public static void job1() throws Exception {
		Job job = new Job();

		job.setMapper(flightFromAirport_Map1.class);
		job.setReducer(flightFromAirport_Reduce1.class);

		BufferedReader input = new BufferedReader(new FileReader("Top30_airports_LatLong.csv"));
		BufferedWriter output = new BufferedWriter(new FileWriter("tmp.flightFromAirports.txt"));
		job.setInput(input);
		job.setOutput(output);

		job.run();
	}

	//Find all of the flights from a given airport
	public static void job2() throws Exception {
		Job job = new Job();

		job.setMapper(flightFromAirport_Map2.class);
		job.setReducer(flightFromAirport_Reduce2.class);

		BufferedReader input = new BufferedReader(new FileReader("AComp_Passenger_data.csv"));
		BufferedWriter output = new BufferedWriter(new FileWriter("tmp.flightFromAirports.txt", true));
		job.setInput(input);
		job.setOutput(output);

		job.run();
	}
	
	//Combine totals
		public static void job3() throws Exception {
			Job job = new Job();

			job.setMapper(flightFromAirport_Map3.class);
			job.setReducer(flightFromAirport_Reduce3.class);

			BufferedReader input = new BufferedReader(new FileReader("tmp.flightFromAirports.txt"));
			BufferedWriter output = new BufferedWriter(new FileWriter("flightsFromAirports.txt"));
			job.setInput(input);
			job.setOutput(output);

			job.run();
		}

}
