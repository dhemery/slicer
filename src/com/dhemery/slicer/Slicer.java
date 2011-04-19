package com.dhemery.slicer;

import java.io.FileReader;
import java.io.IOException;


public class Slicer {
	public static SlicerBuilder slice(String fileName) throws IOException {
		return slice(new GridReader(new FileReader(fileName)).grid());
	}

	public static SlicerBuilder slice(Grid grid) {
		return new SlicerBuilder(grid);
	}
}