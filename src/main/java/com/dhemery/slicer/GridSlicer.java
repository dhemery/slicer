package com.dhemery.slicer;

import java.util.Iterator;
import java.util.List;

import com.dhemery.slicer.converters.RowConverter;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;


public class GridSlicer<T> implements Iterator<T> {
	private final RowConverter<T> rowConverter;
	private final Iterator<List<String>> rowSource;

	public GridSlicer(Iterator<List<String>> rowSource, RowConverter<T> rowConverter) {
		this.rowSource = rowSource;
		this.rowConverter = rowConverter;
	}

	@Override
	public boolean hasNext() {
		return rowSource.hasNext();
	}

	@Override
	public T next() {
		return rowConverter.convertRow(rowSource.next());
	}

	@Override
	public void remove() {
		throw new NotImplementedException();
	}
}
