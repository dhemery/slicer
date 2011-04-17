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

public class ParameterCountTests {
	private final Object[][] cellValues = new Object[][] {
			{"a1", "b1", "c1", "d1", "e1", "f1", "g1" },
			{"a2", "b2", "c2", "d2", "e2", "f2", "g2" },
			{"a3", "b3", "c3", "d3", "e3", "f3", "g3" },
			{"a4", "b4", "c4", "d4", "e4", "f4", "g4" },
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
	public void suppliesOneParameter(String columnA) {
		counter.increment("feeds one parameter");
		int row = counter.count("feeds one parameter");
		String expectedColumnA = expected("a", row);
		assertThat(columnA, is(expectedColumnA));
	}

	@Test(dataProvider="rows")
	public void suppliesTwoParameters(String columnA, String columnB) {
		counter.increment("feeds two parameters");
		int row = counter.count("feeds two parameters");
		assertThat(columnA, is(expected("a", row)));
		assertThat(columnB, is(expected("b", row)));
	}

	@Test(dataProvider="rows")
	public void suppliesManyParameters(String columnA, String columnB, String columnC, String columnD, String columnE, String columnF, String columnG) {
		counter.increment("feeds many parameters");
		int row = counter.count("feeds many parameters");
		assertThat(columnA, is(expected("a", row)));
		assertThat(columnB, is(expected("b", row)));
		assertThat(columnC, is(expected("c", row)));
		assertThat(columnD, is(expected("d", row)));
		assertThat(columnE, is(expected("e", row)));
		assertThat(columnF, is(expected("f", row)));
		assertThat(columnG, is(expected("g", row)));
	}

	private String expected(String column, int row) {
		return String.format("%s%d", column, row);
	}
}