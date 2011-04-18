package com.dhemery.slicer.internal;

import java.util.List;

public interface RowConverter<T> {
	public T convertRow(List<String> row);
}
