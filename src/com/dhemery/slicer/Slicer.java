package com.dhemery.slicer;

public class Slicer {
	public static SpreadsheetSlicerBuilder slice(String fileName) {
		return new SpreadsheetSlicerBuilder(fileName);
	}
}
