package com.dhemery.slicer.internal;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MethodParameterConverter implements RowConverter<Object[]> {
	private final Class<?>[] typesByColumn;
	private final Map<Class<?>,ValueConverter> convertersByType = createConverters();

	public MethodParameterConverter(Method method) {
		typesByColumn = method.getParameterTypes();
	}
	
	@Override
	public Object[] convertRow(List<String> row) {
		List<Object> parameterValues = new ArrayList<Object>();
		for(int columnNumber = 0 ; columnNumber < typesByColumn.length ; columnNumber++) {
			String textValue = row.get(columnNumber);
			Object convertedValue = convertValue(textValue, columnNumber);
			parameterValues.add(convertedValue);
		}
		return parameterValues.toArray(new Object[0]);
	}

	public Object convertValue(String textValue, int columnNumber) {
		Class<?> type = typesByColumn[columnNumber];
		return convertersByType.get(type).valueOf(textValue);
	}

	public interface ValueConverter {
		Object valueOf(String cell);
	}

	private static Map<Class<?>,ValueConverter> createConverters() {
		Map<Class<?>,ValueConverter> converters = new HashMap<Class<?>,ValueConverter>();
		ValueConverter booleanConverter = new ValueConverter() {
			@Override
			public Object valueOf(String text) {
				return Boolean.parseBoolean(text);
			}		
		};

		ValueConverter doubleConverter = new ValueConverter() {
			@Override
			public Object valueOf(String text) {
				return Double.parseDouble(text);
			}		
		};

		ValueConverter integerConverter = new ValueConverter() {
			@Override
			public Object valueOf(String text) {
				return Integer.parseInt(text);
			}		
		};

		ValueConverter stringConverter = new ValueConverter() {
			@Override
			public Object valueOf(String text) {
				return text;
			}		
		};
		converters.put(boolean.class, booleanConverter);
		converters.put(Boolean.class, booleanConverter);
		converters.put(double.class, doubleConverter);
		converters.put(Double.class, doubleConverter);
		converters.put(int.class, integerConverter);
		converters.put(Integer.class, integerConverter);
		converters.put(String.class, stringConverter);
		return converters;
	}
}
