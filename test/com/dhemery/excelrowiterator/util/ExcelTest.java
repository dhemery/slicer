package com.dhemery.excelrowiterator.util;

import java.lang.reflect.Method;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;


public class ExcelTest {
	private final InvocationCounter counter = new InvocationCounter();
	private int currentRow;

	@BeforeMethod
	public void saveCurrentRowNumber(Method method) {
		currentRow = counter.count(method);
	}

	@AfterMethod
	public void countInvocation(Method method) {
		counter.increment(method);
	}

	public int row() {
		return currentRow;
	}
	
	public int invocationCount(Method method) {
		return counter.count(method);
	}
}
