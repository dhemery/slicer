package com.dhemery.slicer;

import java.util.Iterator;

import com.dhemery.slicer.converters.RowConverter;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;


public class GridSlicer<T> implements Iterator<T> {
	private final Grid grid;
	private int rowNumber = 0;
	private final RowConverter<T> rowConverter;

	public GridSlicer(Grid grid, RowConverter<T> rowConverter) {
		this.grid = grid;
		this.rowConverter = rowConverter;
	}

	@Override
	public boolean hasNext() {
		return rowNumber < grid.numberOfRows();
	}

	@Override
	public T next() {
		return rowConverter.convertRow(grid.row(rowNumber++));
	}

	@Override
	public void remove() {
		throw new NotImplementedException();
	}
}
