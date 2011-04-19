package com.dhemery.slicer;

import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;

import com.dhemery.slicer.converters.MethodParameterRowConverter;
import com.dhemery.slicer.converters.StringListRowConverter;


public class SlicerBuilder {
	private final Grid grid;

	public SlicerBuilder(Grid grid) {
		this.grid = grid;
	}

	public Iterator<Object[]> asParametersFor(Method method) {
		return new GridSlicer<Object[]>(grid, new MethodParameterRowConverter(method));
	}

	public Iterator<List<String>> asStrings() {
		return new GridSlicer<List<String>>(grid, new StringListRowConverter());
	}
}
