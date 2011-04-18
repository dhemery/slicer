package com.dhemery.slicer.tests;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import static com.dhemery.slicer.Slicer.slice;

import com.dhemery.slicer.util.CsvCreator;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class AsStringsTests {
	private String excelFileName;
	private String[][] spreadsheetValues;
	
	@Before
	public void setUp() throws FileNotFoundException, IOException, SecurityException, NoSuchMethodException {
		spreadsheetValues = new String[][] {
			{"a1", "false", "1.1", "101", },
			{"a2", "true",  "2.2", "202", },
			{"a3", "false", "3.3", "303", },
			{"a4", "true",  "4.4", "404", },
		};
		excelFileName = CsvCreator.createCsvFile(spreadsheetValues);
	}

	@Test
	public void yieldsEveryRow() throws FileNotFoundException, IOException {
		Iterator<List<String>> rows = slice(excelFileName).asStrings();
		
		for(int rowNumber = 0 ; rowNumber < spreadsheetValues.length ; rowNumber++) {
			rows.next();
		}
		assertThat(rows.hasNext(), is(false));
	}

	@Test
	public void retrievedValuesMatchSpreadsheetValues() throws FileNotFoundException, IOException {
		Iterator<List<String>> rows = slice(excelFileName).asStrings();
		
		for(int rowNumber = 0 ; rowNumber < spreadsheetValues.length ; rowNumber++) {
			List<String> actualValues = rows.next();
			List<String> expectedValues = Arrays.asList(spreadsheetValues[rowNumber]);
			assertThat(actualValues, is(expectedValues));
		}
	}
}
