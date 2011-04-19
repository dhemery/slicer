package com.dhemery.slicer.converters;

final class StringValueConverter implements ValueConverter {
	@Override
	public Object valueOf(String text) {
		return text;
	}
}