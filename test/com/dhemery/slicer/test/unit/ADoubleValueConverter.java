package com.dhemery.slicer.test.unit;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

import com.dhemery.slicer.converters.DoubleValueConverter;

public class ADoubleValueConverter {
	private final DoubleValueConverter converter = new DoubleValueConverter();

	@Test
	public void convertsAStringToADouble() {
		assertThat((Double) converter.valueOf("3.3"), is(closeTo(3.3, 0.00001)));
	}
}
