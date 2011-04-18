package com.dhemery.slicer;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MethodParameterTypeMap implements TypeMap<Object> {
	private final Class<?>[] types;
	private final Map<Class<?>,ValueConverter> convertersByType = createConverters();

	public MethodParameterTypeMap(Method method) {
		types = method.getParameterTypes();
	}

	@Override
	public int numberOfCellsForRow(List<String> row) {
		return types.length;
	}

	public Class<?> getTypeForColumn(int columnNumber) {
		return types[columnNumber];
	}

	@Override
	public Object convert(String textValue, int columnNumber) {
		Class<?> type = getTypeForColumn(columnNumber);
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
