package com.dhemery.slicer.tests.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import au.com.bytecode.opencsv.CSVWriter;

public class CsvTmpFileCreator {
	public static String create(String[][] cellValues) throws IOException {
		File file = createTmpFile();
		writeValues(file, cellValues);
		return file.getAbsolutePath();
	}

	private static void writeValues(File file, String[][] cellValues) throws IOException {
		CSVWriter writer = new CSVWriter(new FileWriter(file));
		for(String[] row : cellValues) { 
			writer.writeNext(row);
		}
		writer.close();
	}

	private static File createTmpFile() throws IOException {
		File file = File.createTempFile("slicer", ".csv");
		file.deleteOnExit();
		return file;
	}
}
