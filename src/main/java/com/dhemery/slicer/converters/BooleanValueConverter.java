package com.dhemery.slicer.converters;

public class BooleanValueConverter implements ValueConverter {
	@Override
	public Object valueOf(String text) {
		return Boolean.parseBoolean(text);
	}
}