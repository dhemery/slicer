package com.dhemery.slicer;

import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;

import com.dhemery.slicer.converters.MethodParameterRowConverter;
import com.dhemery.slicer.converters.StringListRowConverter;
import com.dhemery.slicer.rowfilters.AllRowsFilter;
import com.dhemery.slicer.rowfilters.SkipHeaderRowFilter;


public class SlicerBuilder {
	private final Grid grid;
	private Iterator<List<String>> rowFilter;

	public SlicerBuilder(Grid grid) {
		this.grid = grid;
		rowFilter = new AllRowsFilter(grid);
	}

	public Iterator<Object[]> asParametersFor(Method method) {
		return new GridSlicer<Object[]>(rowFilter, new MethodParameterRowConverter(method));
	}

	public Iterator<List<String>> asStrings() {
		return new GridSlicer<List<String>>(rowFilter, new StringListRowConverter());
	}

	public SlicerBuilder skipHeaderRow() {
		rowFilter = new SkipHeaderRowFilter(grid);
		return this;
	}
}
