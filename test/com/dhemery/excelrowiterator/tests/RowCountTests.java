package com.dhemery.excelrowiterator.tests;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Iterator;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.dhemery.excelrowiterator.ExcelRowIterator;
import com.dhemery.excelrowiterator.util.ExcelTest;
import com.dhemery.excelrowiterator.util.WorkbookCreator;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class RowCountTests extends ExcelTest {
	private final Object[][] cellValues = new Object[][] {
			{"a1"},
			{"a2"},
			{"a3"},
			{"a4"}
	};
	private String excelFileName;

	@BeforeClass
	public void createWorkbookFile() throws FileNotFoundException, IOException {
		excelFileName = WorkbookCreator.createWorkbookFile(cellValues);
	}

	@DataProvider
	public Iterator<Object[]> rows(Method method) throws FileNotFoundException, IOException {
		return new ExcelRowIterator(excelFileName, method);
	}

	@Test(dataProvider="rows")
	public void aParameterizedTest(String columnA) {
	}

	@Test(dependsOnMethods = { "aParameterizedTest" })
	public void suppliesEveryRowToParameterizedTest() throws SecurityException, NoSuchMethodException {
		Method method = getClass().getMethod("aParameterizedTest", String.class);
		assertThat(invocationCount(method), is(cellValues.length));
	}
}
