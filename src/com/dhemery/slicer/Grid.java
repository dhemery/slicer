package com.dhemery.slicer;

import java.util.List;

public class Grid {
	private final List<List<String>> cells;

	public Grid(List<List<String>> cells) {
		this.cells = cells;
	}

	public int numberOfRows() {
		return cells.size();
	}

	public List<String> row(int i) {
		return cells.get(i);
	}
}
