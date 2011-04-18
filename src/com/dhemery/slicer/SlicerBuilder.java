package com.dhemery.slicer;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class SlicerBuilder {
	private final Grid grid;

	public SlicerBuilder(Grid grid) {
		this.grid = grid;
	}

	public Iterator<List<Object>> asParametersFor(Method method) {
		return new MethodParameterSlicer<Object>(grid, Arrays.asList(method.getParameterTypes()));
	}

	public Iterator<List<String>> asStrings() {
		return new StringSlicer(grid);
	}
}
