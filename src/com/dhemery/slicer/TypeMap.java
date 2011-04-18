package com.dhemery.slicer;

import java.util.List;

public interface TypeMap<T> {
	public List<T> convertRow(List<String> row);
}
