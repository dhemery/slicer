package com.dhemery.slicer.converters;

final class IntegerValueConverter implements ValueConverter {
	@Override
	public Object valueOf(String text) {
		return Integer.parseInt(text);
	}
}