package com.dhemery.slicer;

import java.util.List;

public class StringTypeMap implements TypeMap<String> {

	@Override
	public int numberOfCellsForRow(List<String> row) {
		return row.size();
	}

	@Override
	public String convert(String textValue, int columnNumber) {
		return textValue;
	}
}
