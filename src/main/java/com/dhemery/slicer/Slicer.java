package com.dhemery.slicer;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;

import com.dhemery.slicer.converters.MethodParameterRowConverter;
import com.dhemery.slicer.converters.StringListRowConverter;

public class Slicer {
	private Grid grid;

	private Slicer(Grid grid) {
		this.grid = grid;
	}

	public static Slicer slice(Grid grid) {
		return new Slicer(grid);
	}

	public static Slicer slice(String fileName) throws IOException {
		return slice(new GridReader(new FileReader(fileName)).grid());
	}

	public static Slicer slice(String[][] stringArrays) {
		return slice(new Grid(stringArrays));
	}

	public static Slicer slice(List<List<String>> stringLists) {
		return slice(new Grid(stringLists));
	}

	public Iterator<Object[]> asParametersFor(Method method) {
		return new GridRowIterator<Object[]>(grid, new MethodParameterRowConverter(method));
	}

	public Iterator<List<String>> asStringLists() {
		return new GridRowIterator<List<String>>(grid, new StringListRowConverter());
	}

	public List<String> column(int column) {
		return grid.column(column);
	}

	public Slicer columns(Integer...selected) {
		grid = grid.columns(selected);
		return this;
	}

	public Slicer rows(Integer...selected) {
		grid = grid.rows(selected);
		return this;
	}
	
	public Slicer skipColumns(Integer...skipped) {
		grid = grid.skipColumns(skipped);
		return this;
	}
	
	public Slicer skipRows(Integer...skipped) {
		grid = grid.skipRows(skipped);
		return this;
	}
	
	public Slicer skipHeaderRow() {
		return skipRows(0);
	}
}
