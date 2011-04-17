package com.dhemery.slicer.tests;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Iterator;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.dhemery.slicer.SpreadsheetSlicer.slice;

import com.dhemery.slicer.util.SpreadsheetSlicerTest;
import com.dhemery.slicer.util.WorkbookCreator;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class ParameterCountTests extends SpreadsheetSlicerTest {
	private final Object[][] cellValues = new Object[][] {
			{"a1", "b1", "c1", "d1", "e1", "f1", "g1" },
			{"a2", "b2", "c2", "d2", "e2", "f2", "g2" },
			{"a3", "b3", "c3", "d3", "e3", "f3", "g3" },
			{"a4", "b4", "c4", "d4", "e4", "f4", "g4" },
	};
	private String excelFileName;
	
	@BeforeClass
	public void createWorkbookFile() throws FileNotFoundException, IOException {
		excelFileName = WorkbookCreator.createWorkbookFile(cellValues);
	}

	@DataProvider
	public Iterator<Object[]> rows(Method method) throws FileNotFoundException, IOException {
		return slice(excelFileName).method(method).iterator();
	}

	@Test(dataProvider="rows")
	public void suppliesOneParameter(String columnA) {
		assertThat(columnA, is(cellValues[row()][0]));
	}

	@Test(dataProvider="rows")
	public void suppliesTwoParameters(String columnA, String columnB) {
		assertThat(columnA, is(cellValues[row()][0]));
		assertThat(columnB, is(cellValues[row()][1]));
	}

	@Test(dataProvider="rows")
	public void suppliesManyParameters(String columnA, String columnB, String columnC, String columnD, String columnE, String columnF, String columnG) {
		assertThat(columnA, is(cellValues[row()][0]));
		assertThat(columnB, is(cellValues[row()][1]));
		assertThat(columnC, is(cellValues[row()][2]));
		assertThat(columnD, is(cellValues[row()][3]));
		assertThat(columnE, is(cellValues[row()][4]));
		assertThat(columnF, is(cellValues[row()][5]));
		assertThat(columnG, is(cellValues[row()][6]));
	}
}
