package com.dhemery.slicer;

import java.util.List;

public interface TypeMap<T> {
	public int numberOfCellsForRow(List<String> row);
	public T convert(String textValue, int columnNumber);
}
