package com.dhemery.slicer;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;

import com.dhemery.slicer.internal.MethodParameterConverter;
import com.dhemery.slicer.internal.StringListRowConverter;

public class SlicerBuilder {
	private final Grid grid;

	public SlicerBuilder(String fileName) throws IOException {
		this(new CsvGridReader(fileName).grid());
	}

	public SlicerBuilder(Grid grid) {
		this.grid = grid;
	}

	public Iterator<Object[]> asParametersFor(Method method) {
		return new Slicer<Object[]>(grid, new MethodParameterConverter(method));
	}

	public Iterator<List<String>> asStrings() {
		return new Slicer<List<String>>(grid, new StringListRowConverter());
	}
}
