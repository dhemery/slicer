package com.dhemery.slicer.converters;

public class IntegerValueConverter implements ValueConverter {
	@Override
	public Object valueOf(String text) {
		return Integer.parseInt(text);
	}
}