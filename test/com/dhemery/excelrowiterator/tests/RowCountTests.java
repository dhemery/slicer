package com.dhemery.excelrowiterator.tests;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Iterator;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.dhemery.excelrowiterator.ExcelRowIterator;
import com.dhemery.excelrowiterator.util.InvocationCounter;
import com.dhemery.excelrowiterator.util.WorkbookCreator;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class RowCountTests {
	private final Object[][] cellValues = new Object[][] {
			{"a1"},
			{"a2"},
			{"a3"},
			{"a4"}
	};
	private final InvocationCounter counter = new InvocationCounter();
	private String excelFileName;

	@BeforeClass
	public void createExcelFile() throws IOException {
		excelFileName = WorkbookCreator.createWorkbookFile(cellValues);
	}
	
	@DataProvider
	public Iterator<Object[]> rows(Method method) throws FileNotFoundException, IOException {
		return new ExcelRowIterator(excelFileName, method);
	}

	@Test(dataProvider="rows")
	public void aParameterizedTest(String columnA) {
		counter.increment("a parameterized test");
		assertThat(true, is(true));
	}

	@Test(dependsOnMethods = { "aParameterizedTest" })
	public void suppliesEveryRowToParameterizedTest() {
		assertThat(counter.count("a parameterized test"), is(cellValues.length));
	}
}
