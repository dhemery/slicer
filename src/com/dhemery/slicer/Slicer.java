package com.dhemery.slicer;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import au.com.bytecode.opencsv.CSVReader;

public class Slicer {
	public static SlicerBuilder slice(String fileName) throws IOException {
		return new SlicerBuilder(gridFromCsvFile(fileName));
	}

	private static Grid gridFromCsvFile(String fileName) throws FileNotFoundException, IOException {
		CSVReader reader = new CSVReader(new FileReader(fileName));
		ArrayList<List<String>> cells = new ArrayList<List<String>>();
		String[] row;
		while((row = reader.readNext()) != null) {
			cells.add(Arrays.asList(row));
		}
		return new Grid(cells);
	}
}
