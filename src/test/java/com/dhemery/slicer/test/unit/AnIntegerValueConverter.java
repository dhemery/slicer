package com.dhemery.slicer.test.unit;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

import com.dhemery.slicer.converters.IntegerValueConverter;

public class AnIntegerValueConverter {
	private final IntegerValueConverter converter = new IntegerValueConverter();

	@Test
	public void convertsZero() {
		assertThat((Integer) converter.valueOf("0"), is(0));
	}
}
