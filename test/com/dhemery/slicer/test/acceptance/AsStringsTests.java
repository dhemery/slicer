package com.dhemery.slicer.test.acceptance;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import static com.dhemery.slicer.Slicer.*;

import com.dhemery.slicer.test.util.CsvTmpFileCreator;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class AsStringsTests {
	private String csvFile;
	private String[][] csvValues;
	
	@Before
	public void setUp() throws FileNotFoundException, IOException, SecurityException, NoSuchMethodException {
		csvValues = new String[][] {
			{"a1", "false", "1.1", "101", },
			{"a2", "true",  "2.2", "202", },
			{"a3", "false", "3.3", "303", },
			{"a4", "true",  "4.4", "404", },
		};
		csvFile = CsvTmpFileCreator.create(csvValues);
	}

	@Test
	public void yieldsEveryRow() throws FileNotFoundException, IOException {
		Iterator<List<String>> rows = slice(csvFile).asStrings();
		
		for(int rowNumber = 0 ; rowNumber < csvValues.length ; rowNumber++) {
			rows.next();
		}
		assertThat(rows.hasNext(), is(false));
	}

	@Test
	public void retrievedValuesMatchSpreadsheetValues() throws FileNotFoundException, IOException {
		Iterator<List<String>> rows = slice(csvFile).asStrings();
		
		for(int rowNumber = 0 ; rowNumber < csvValues.length ; rowNumber++) {
			List<String> actualValues = rows.next();
			List<String> expectedValues = Arrays.asList(csvValues[rowNumber]);
			assertThat(actualValues, is(expectedValues));
		}
	}
}
