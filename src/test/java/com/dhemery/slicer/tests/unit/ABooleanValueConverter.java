package com.dhemery.slicer.tests.unit;

import com.dhemery.slicer.converters.BooleanValueConverter;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ABooleanValueConverter {
	private final BooleanValueConverter converter = new BooleanValueConverter();

	@Test
	public void convertsTheWordTrueRegardlessOfCase() {
		assertThat((Boolean) converter.valueOf("true"), is(true));
		assertThat((Boolean) converter.valueOf("True"), is(true));
		assertThat((Boolean) converter.valueOf("TRUE"), is(true));
		assertThat((Boolean) converter.valueOf("tRuE"), is(true));
	}

	@Test
	public void convertsTheWordFalseRegardlessOfCase() {
		assertThat((Boolean) converter.valueOf("false"), is(false));
		assertThat((Boolean) converter.valueOf("False"), is(false));
		assertThat((Boolean) converter.valueOf("FALSE"), is(false));
		assertThat((Boolean) converter.valueOf("FaLsE"), is(false));
	}
	
	@Test
	public void convertsOtherValuesAsFalse() {
		assertThat((Boolean) converter.valueOf(""), is(false));
		assertThat((Boolean) converter.valueOf("1"), is(false));
		assertThat((Boolean) converter.valueOf("fred"), is(false));
		assertThat((Boolean) converter.valueOf(" false"), is(false));
		assertThat((Boolean) converter.valueOf("true "), is(false));
	}
}
