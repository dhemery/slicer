package com.dhemery.slicer.tests.unit;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import com.dhemery.slicer.Grid;
import com.dhemery.slicer.GridReader;
import com.dhemery.slicer.tests.util.CsvTmpFileCreator;

public class AGridReader {
	String[][] cells = {
			{ "a", "b", "c", "d", },
			{ "1", "3", "5", "42", "42 again", },
			{ "-", "$", "*", },
			{ "the", "quick", "brown", "fox", },
	};
	private String csvFileName;

	@Before
	public void writeCellsToCsvFile() throws IOException {
		csvFileName = CsvTmpFileCreator.create(cells);
	}

	@Test
	public void createsAGridWithTheValuesFromTheCsvFile() throws FileNotFoundException, IOException  {
		GridReader gridReader = new GridReader(new FileReader(csvFileName));
		Grid grid = gridReader.grid();
		for(int i = 0 ; i < cells.length ; i++) {
			assertThat(grid.row(i), is(Arrays.asList(cells[i])));			
		}
	}
}
