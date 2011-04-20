package com.dhemery.slicer.converters;

public class StringValueConverter implements ValueConverter {
	@Override
	public Object valueOf(String text) {
		return text;
	}
}