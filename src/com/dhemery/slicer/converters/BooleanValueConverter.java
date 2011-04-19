package com.dhemery.slicer.converters;

class BooleanValueConverter implements ValueConverter {
	@Override
	public Object valueOf(String text) {
		return Boolean.parseBoolean(text);
	}
}