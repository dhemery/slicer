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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cells == null) ? 0 : cells.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Grid other = (Grid) obj;
		if (!cells.equals(other.cells)) return false;
		return true;
	}

	public int numberOfRows() {
		return cells.size();
	}

	public List<String> row(int i) {
		return cells.get(i);
	}

	public Grid removeHeader() {
		List<List<String>> newCells = new ArrayList<List<String>>();
		for(int i = 1 ; i < cells.size() ; i++) {
			newCells.add(row(i));
		}
		return new Grid(newCells);
	}
}
