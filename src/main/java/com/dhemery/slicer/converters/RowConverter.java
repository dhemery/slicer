package com.dhemery.slicer.converters;

import java.util.List;

public interface RowConverter<T> {
	public T convertRow(List<String> row);
}
