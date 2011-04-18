package com.dhemery.slicer;
import java.util.Iterator;
import java.util.List;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class StringSlicer implements Iterator<List<String>> {
	private final Grid grid;
	private int rowNumber = 0;

	public StringSlicer(Grid grid) {
		this.grid = grid;
	}

	@Override
	public boolean hasNext() {
		return rowNumber < grid.numberOfRows();
	}

	@Override
	public List<String> next() {
		return grid.row(rowNumber++);
	}

	@Override
	public void remove() {
		throw new NotImplementedException();
	}
}
