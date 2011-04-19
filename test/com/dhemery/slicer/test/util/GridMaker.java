package com.dhemery.slicer.test.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.dhemery.slicer.Grid;

public class GridMaker {
	public static Grid gridFrom(String[][] arrays) {
		List<List<String>> cells = new ArrayList<List<String>>();
		for(String[] array : arrays) {
			cells.add(Arrays.asList(array));
		}
		return new Grid(cells);
	}
}
