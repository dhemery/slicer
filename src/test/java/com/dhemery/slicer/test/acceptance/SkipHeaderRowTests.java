package com.dhemery.slicer.test.acceptance;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import static com.dhemery.slicer.Slicer.slice;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class SkipHeaderRowTests {
	private String[][] csvValues;
	
	@Before
	public void setUp() throws FileNotFoundException, IOException, SecurityException, NoSuchMethodException {
		csvValues = new String[][] {
			{"Name", "City",		"State",	"Zip Code",	},
			{"fred", "New York",	"NY",		"04909",	},
			{"jane", "Pittsburg",	"PA",		"51515",	},
			{"bill", "San Leandro",	"CA",		"32398",	},
			{"anna", "Berwick",		"ME",		"03901",	},
			{"dude", "Somersworth",	"NH",		"03878",	},
		};
	}
	
	@Test
	public void skipsTheHeaderRow() throws IOException {
		Iterator<List<String>> rows = slice(csvValues).skipHeaderRow().asStrings();
		int firstRowAfterHeader = 1;
		for(int i = firstRowAfterHeader ; i < csvValues.length ; i++) {
			assertThat(rows.next(), is(Arrays.asList(csvValues[i])));
		}
	}
}
