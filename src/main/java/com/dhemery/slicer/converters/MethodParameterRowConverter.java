package com.dhemery.slicer.converters;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MethodParameterRowConverter implements RowConverter<Object[]> {
	private final Class<?>[] typesByColumn;
	private final Map<Class<?>,ValueConverter> convertersByType = createConverters();

	public MethodParameterRowConverter(Method method) {
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
		ValueConverter converter = convertersByType.get(type);
		return converter.valueOf(textValue);
	}

	private static Map<Class<?>,ValueConverter> createConverters() {
		ValueConverter booleanConverter = new BooleanValueConverter();
		ValueConverter doubleConverter = new DoubleValueConverter();
		ValueConverter integerConverter = new IntegerValueConverter();
		ValueConverter stringConverter = new StringValueConverter();

		Map<Class<?>,ValueConverter> converters = new HashMap<Class<?>,ValueConverter>();
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
