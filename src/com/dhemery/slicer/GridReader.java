package com.dhemery.slicer;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



import au.com.bytecode.opencsv.CSVReader;


public class GridReader {
	private final Grid grid;

	public GridReader(Reader fileReader) throws IOException {
		CSVReader csvReader = new CSVReader(fileReader);
		ArrayList<List<String>> cells = new ArrayList<List<String>>();
		String[] row;
		while((row = csvReader.readNext()) != null) {
			cells.add(Arrays.asList(row));
		}
		grid = new Grid(cells);
	}

	public Grid grid() {
		return grid;
	}
}
