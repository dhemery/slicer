package com.dhemery.slicer.test.unit;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.dhemery.slicer.Grid;

public class AGrid {
	String[][] stringArrays = new String[][] {
		{ "a", "b", "c", "d", },
		{ "1", "3", "5", "42", "42 again", },
		{ "-", "$", "*", },
		{ "the", "quick", "brown", "fox", },
	};

	@Test
	public void yieldsTheGivenLists() {
		List<List<String>> stringLists = new ArrayList<List<String>>();
		stringLists.add(Arrays.asList("a", "b", "c", "d"));
		stringLists.add(Arrays.asList("1", "2", "3", "4", "5", "9"));
		stringLists.add(Arrays.asList(" ", "  ", ".", "_"));
		
		Grid grid = new Grid(stringLists);

		for(int i = 0 ; i < stringLists.size() ; i++) {			
			assertThat(grid.row(i), is(stringLists.get(i)));
		}
	}

	@Test
	public void yieldsTheGivenArraysAsLists() {
		Grid grid = new Grid(stringArrays);

		for(int i = 0 ; i < stringArrays.length ; i++) {
			assertThat(grid.row(i), is(Arrays.asList(stringArrays[i])));
		}
	}
	
	@Test
	public void knowsHowManyRowsItHas() {
		assertThat(new Grid(stringArrays).numberOfRows(), is(stringArrays.length));
	}
	
	@Test
	public void removeHeaderCreatesANewGridWithoutTheHeaderRow() {
		String[][] withHeader = {
				{ "col 1",    "col 2",    "col3",     },
				{ "cell 1 1", "cell 1 2", "cell 1 3", },
				{ "cell 2 1", "cell 2 2", "cell 2 3", },
				{ "cell 3 1", "cell 3 2", "cell 3 3", },
		};
		String[][] withoutHeader = {
				{ "cell 1 1", "cell 1 2", "cell 1 3", },
				{ "cell 2 1", "cell 2 2", "cell 2 3", },
				{ "cell 3 1", "cell 3 2", "cell 3 3", },
		};
		Grid gridWithHeader = new Grid(withHeader);
		Grid gridWithoutHeader = new Grid(withoutHeader);

		assertThat(gridWithHeader.removeHeader(), is(gridWithoutHeader));
	}
	
	@Test
	public void rows() {
		String[][] original = {
				{ "cell 1 1", "cell 1 2", "cell 1 3", },
				{ "cell 2 1", "cell 2 2", "cell 2 3", },
				{ "cell 3 1", "cell 3 2", "cell 3 3", },
				{ "cell 4 1", "cell 4 2", "cell 4 3", },
				{ "cell 5 1", "cell 5 2", "cell 5 3", },
		};
		String[][] selected = {
				{ "cell 1 1", "cell 1 2", "cell 1 3", },
				{ "cell 4 1", "cell 4 2", "cell 4 3", },
				{ "cell 5 1", "cell 5 2", "cell 5 3", },
		};
		Grid originalGrid = new Grid(original);
		Grid selectedRowsGrid = new Grid(selected);

		assertThat(originalGrid.rows(0, 3, 4), is(selectedRowsGrid));
	}
	
	@Test
	public void columns() {
		String[][] original = {
				{ "cell 1 1", "cell 1 2", "cell 1 3", "cell 1 4", "cell 1 5", },
				{ "cell 2 1", "cell 2 2", "cell 2 3", "cell 2 4", "cell 2 5", },
				{ "cell 3 1", "cell 3 2", "cell 3 3", "cell 3 4", "cell 3 5", },
		};
		String[][] selected = {
				{ "cell 1 1", "cell 1 2", "cell 1 5", },
				{ "cell 2 1", "cell 2 2", "cell 2 5", },
				{ "cell 3 1", "cell 3 2", "cell 3 5", },
		};
		Grid originalGrid = new Grid(original);
		Grid selectedColumnsGrid = new Grid(selected);
		
		assertThat(originalGrid.columns(0, 1, 4), is(selectedColumnsGrid));
	}
	
	@Test
	public void skipRows() {
		String[][] original = {
				{ "cell 1 1", "cell 1 2", "cell 1 3", },
				{ "cell 2 1", "cell 2 2", "cell 2 3", },
				{ "cell 3 1", "cell 3 2", "cell 3 3", },
				{ "cell 4 1", "cell 4 2", "cell 4 3", },
				{ "cell 5 1", "cell 5 2", "cell 5 3", },
		};
		String[][] skipped = {
				{ "cell 2 1", "cell 2 2", "cell 2 3", },
				{ "cell 3 1", "cell 3 2", "cell 3 3", },
		};
		Grid originalGrid = new Grid(original);
		Grid skippedRowsGrid = new Grid(skipped);

		assertThat(originalGrid.skipRows(0, 3, 4), is(skippedRowsGrid));
	}	

	@Test
	public void skipColumns() {
		String[][] original = {
				{ "cell 1 1", "cell 1 2", "cell 1 3", "cell 1 4", "cell 1 5", },
				{ "cell 2 1", "cell 2 2", "cell 2 3", "cell 2 4", "cell 2 5", },
				{ "cell 3 1", "cell 3 2", "cell 3 3", "cell 3 4", "cell 3 5", },
		};
		String[][] skipped = {
				{ "cell 1 1", "cell 1 2", "cell 1 5", },
				{ "cell 2 1", "cell 2 2", "cell 2 5", },
				{ "cell 3 1", "cell 3 2", "cell 3 5", },
		};
		Grid originalGrid = new Grid(original);
		Grid skippedColumnsGrid = new Grid(skipped);
		
		assertThat(originalGrid.skipColumns(2, 3), is(skippedColumnsGrid));
	}

	@Test
	public void column() {
		String[][] original = {
				{ "cell 1 1", "cell 1 2", "cell 1 3", },
				{ "cell 2 1", "cell 2 2", "cell 2 3", },
				{ "cell 3 1", "cell 3 2", "cell 3 3", },
		};
		String[] columnTwo = {
				"cell 1 3",
				"cell 2 3",
				"cell 3 3",
		};
		Grid grid = new Grid(original);
		List<String> columnTwoStrings = Arrays.asList(columnTwo);

		assertThat(grid.column(2), is(columnTwoStrings));
	}
	
}
