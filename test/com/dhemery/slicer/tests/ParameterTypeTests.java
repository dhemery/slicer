package com.dhemery.slicer.tests;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Iterator;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.dhemery.slicer.util.SpreadsheetSlicerTest;
import com.dhemery.slicer.util.WorkbookCreator;

import static com.dhemery.slicer.Slicer.slice;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class ParameterTypeTests extends SpreadsheetSlicerTest {
	private final Object[][] cellValues = new Object[][] {
			{true, 1.1, 1, "a1", },
			{false, 2.2, 2, "a2", },
			{true, 3.3, 3, "a3", },
			{false, 4.4, 4, "a4", },
	};
	private String excelFileName;

	@BeforeClass
	public void createWorkbookFile() throws FileNotFoundException, IOException {
		excelFileName = WorkbookCreator.createWorkbookFile(cellValues);
	}

	@DataProvider
	public Iterator<Object[]> rows(Method method) throws FileNotFoundException, IOException {
		return slice(excelFileName).asParametersFor(method);
	}

	@Test(dataProvider="rows")
	public void suppliesPrimitiveParameterTypes(boolean column1, double column2, int column3) {
		assertThat(column1, is(cellValues[row()][0]));
		assertThat(column2, is(cellValues[row()][1]));
		assertThat(column3, is(cellValues[row()][2]));
	}

	@Test(dataProvider="rows")
	public void suppliesCommonClassParameterTypes(Boolean column1, Double column2, Integer column3, String column4) {
		assertThat(column1, is(cellValues[row()][0]));
		assertThat(column2, is(cellValues[row()][1]));
		assertThat(column3, is(cellValues[row()][2]));
		assertThat(column4, is(cellValues[row()][3]));
	}
}
