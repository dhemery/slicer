package com.dhemery.slicer.converters;

class DoubleValueConverter implements ValueConverter {
	@Override
	public Object valueOf(String text) {
		return Double.parseDouble(text);
	}
}