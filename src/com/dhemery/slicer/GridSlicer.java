package com.dhemery.slicer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class GridSlicer <T> implements Iterator<List<T>> {
	private final Grid grid;
	private int rowNumber = 0;
	private final TypeMap<T> typeMap;

	public GridSlicer(Grid grid, TypeMap<T> typeMap) {
		this.grid = grid;
		this.typeMap = typeMap;
	}

	@Override
	public boolean hasNext() {
		return rowNumber < grid.numberOfRows();
	}

	@Override
	public List<T> next() {
		List<T> parameterValues = new ArrayList<T>();
		for(int columnNumber = 0 ; columnNumber < typeMap.numberOfCellsForRow(grid.row(rowNumber)) ; columnNumber++) {
			String textValue = grid.row(rowNumber).get(columnNumber);
			parameterValues.add(valueOfCell(textValue, columnNumber));
		}
		rowNumber++;
		return parameterValues;
	}

	@Override
	public void remove() {
		throw new NotImplementedException();
	}
	
	private T valueOfCell(String textValue, int columnNumber) {
		return typeMap.convert(textValue, columnNumber);
	}
}
