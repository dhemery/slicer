package com.dhemery.slicer;

import java.io.IOException;
import java.util.Iterator;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import com.dhemery.slicer.internal.RowConverter;

public class Slicer<T> implements Iterator<T> {
	private final Grid grid;
	private int rowNumber = 0;
	private final RowConverter<T> rowConverter;

	public Slicer(Grid grid, RowConverter<T> rowConverter) {
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

	public static SlicerBuilder slice(String fileName) throws IOException {
		return new SlicerBuilder(fileName);
	}
	
	public static SlicerBuilder slice(Grid grid) {
		return new SlicerBuilder(grid);
	}
}