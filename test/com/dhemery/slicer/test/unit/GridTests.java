package com.dhemery.slicer.test.unit;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.dhemery.slicer.Grid;

public class GridTests {
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
}
