package com.dhemery.slicer;

import java.util.Iterator;

import com.dhemery.slicer.converters.RowConverter;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;


public class GridRowIterator<T> implements Iterator<T> {
	private final Grid grid;
	private int nextRow = 0;
	private final RowConverter<T> rowConverter;

	public GridRowIterator(Grid grid, RowConverter<T> rowConverter) {
		this.grid = grid;
		this.rowConverter = rowConverter;
	}

	@Override
	public boolean hasNext() {
		return nextRow < grid.numberOfRows();
	}

	@Override
	public T next() {
		return rowConverter.convertRow(grid.row(nextRow++));
	}

	@Override
	public void remove() {
		throw new NotImplementedException();
	}
}
