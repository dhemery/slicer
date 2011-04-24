package com.dhemery.slicer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Grid {
	private final List<List<String>> cells = new ArrayList<List<String>>();

	public Grid(List<List<String>> lists) {
		cells.addAll(lists);
	}

	public Grid(String[][] arrays) {
		for(String[] array : arrays) {
			cells.add(Arrays.asList(array));
		}
	}

	public int numberOfRows() {
		return cells.size();
	}

	public List<String> row(int i) {
		return cells.get(i);
	}
}
