package com.dhemery.slicer.converters;

import java.util.List;

public class StringListRowConverter implements RowConverter<List<String>> {
	@Override
	public List<String> convertRow(List<String> row) {
		return row;
	}
}
