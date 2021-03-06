package com.dhemery.slicer.tests.acceptance;

import com.dhemery.slicer.tests.util.CsvTmpFileCreator;
import com.dhemery.slicer.tests.util.MethodFinder;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;

import static com.dhemery.slicer.Slicer.slice;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class AsMethodParametersTests {
	private String csvFile;
	private String[][] csvValues;
	public Method targetMethod;
	
	@Before
	public void setUp() throws FileNotFoundException, IOException, SecurityException, NoSuchMethodException {
		csvValues = new String[][] {
			{"a1", "false", "false", "1.1", "11.11", "101", "11011", },
			{"a2", "false", "true",  "2.2", "22.22", "202", "22022", },
			{"a3", "true",  "false", "3.3", "33.33", "303", "33033", },
			{"a4", "true",  "true",  "4.4", "44.44", "404", "44044", },
		};
		csvFile = CsvTmpFileCreator.create(csvValues);
		targetMethod = MethodFinder.find(this, "theMethod");
	}

	public void theMethod(String S, boolean b, Boolean B, double d, Double D, int i, Integer I) {}

	@Test
	public void yieldsEveryRow() throws FileNotFoundException, IOException {
		Iterator<Object[]> rows = slice(csvFile).asParametersFor(targetMethod);

		for(int rowNumber = 0 ; rowNumber < csvValues.length ; rowNumber++) {
			rows.next();
		}
		assertThat(rows.hasNext(), is(false));
	}

	@Test
	public void eachRowHasAValueForEachParameter() throws FileNotFoundException, IOException {
		Iterator<Object[]> rows = slice(csvFile).asParametersFor(targetMethod);
		
		for(int rowNumber = 0 ; rowNumber < csvValues.length ; rowNumber++) {
			assertThat(rows.next().length, is(targetMethod.getParameterTypes().length));
		}
		assertThat(rows.hasNext(), is(false));
	}

	@Test
	public void eachValueHasATypeAppropriateForTheMethod() throws FileNotFoundException, IOException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		Iterator<Object[]> rows = slice(csvFile).asParametersFor(targetMethod);
		
		for(int rowNumber = 0 ; rowNumber < csvValues.length ; rowNumber++) {
			Object[] row = rows.next();
			// If the types are wrong, the invocation will throw an exception.
			targetMethod.invoke(this, row);
		}
	}

	@Test
	public void yieldsCorrectValues() throws FileNotFoundException, IOException {
		Iterator<Object[]> rows = slice(csvFile).asParametersFor(targetMethod);

		for(int rowNumber = 0 ; rowNumber < csvValues.length ; rowNumber++) {
			Object[] actualValues = rows.next();
			for(int columnNumber = 0 ; columnNumber < csvValues[rowNumber].length ; columnNumber++) {				
				assertThat(actualValues[columnNumber].toString(), is(csvValues[rowNumber][columnNumber]));
			}
		}
	}
}
