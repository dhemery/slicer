package com.dhemery.slicer.rowfilters;

import java.util.Iterator;
import java.util.List;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import com.dhemery.slicer.Grid;

public class AllRowsFilter implements Iterator<List<String>> {
	private final Grid grid;
	private int nextRow = 0;

	public AllRowsFilter(Grid grid) {
		this.grid = grid;
		
	}
	@Override
	public boolean hasNext() {
		return nextRow < grid.numberOfRows();
	}

	@Override
	public List<String> next() {
		return grid.row(nextRow++);
	}

	@Override
	public void remove() {
		throw new NotImplementedException();
	}
}
