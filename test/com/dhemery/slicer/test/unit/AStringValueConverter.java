package com.dhemery.slicer.test.unit;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

import com.dhemery.slicer.converters.StringValueConverter;

public class AStringValueConverter {
	private final StringValueConverter converter = new StringValueConverter();

	@Test
	public void returnsAStringWithTheSameValueAsGiven() {
		assertThat((String) converter.valueOf("fred"), is("fred"));
	}
}
