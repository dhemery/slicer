package com.dhemery.slicer;

import java.util.List;

public class StringTypeMap implements TypeMap<String> {
	@Override
	public List<String> convertRow(List<String> row) {
		return row;
	}
}
