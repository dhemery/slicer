package com.dhemery.slicer;

import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;

public class SlicerBuilder {
	private final Grid grid;

	public SlicerBuilder(Grid grid) {
		this.grid = grid;
	}

	public Iterator<List<Object>> asParametersFor(Method method) {
		return new GridSlicer<Object>(grid, new MethodParameterTypeMap(method));
	}

	public Iterator<List<String>> asStrings() {
		return new GridSlicer<String>(grid, new StringTypeMap());
	}
}
