package com.dhemery.slicer.converters;

public class DoubleValueConverter implements ValueConverter {
	@Override
	public Object valueOf(String text) {
		return Double.parseDouble(text);
	}
}