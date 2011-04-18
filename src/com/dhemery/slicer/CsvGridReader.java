package com.dhemery.slicer;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import au.com.bytecode.opencsv.CSVReader;


public class CsvGridReader {
	private final Grid grid;

	public CsvGridReader(String fileName) throws IOException {
		CSVReader reader = new CSVReader(new FileReader(fileName));
		ArrayList<List<String>> cells = new ArrayList<List<String>>();
		String[] row;
		while((row = reader.readNext()) != null) {
			cells.add(Arrays.asList(row));
		}
		grid = new Grid(cells);
	}

	public Grid grid() {
		return grid;
	}
}
