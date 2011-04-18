package com.dhemery.slicer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class MethodParameterSlicer <T> implements Iterator<List<T>> {
	public interface ValueConverter {
		Object valueOf(String cell);
	}
	private int rowNumber = 0;
	private final Grid grid;
	private final List<Class<?>> types;
	private final Map<Class<?>,ValueConverter> convertersByType = createConverters();

	public MethodParameterSlicer(Grid grid, List<Class<?>> types) {
		this.grid = grid;
		this.types = types;
		rowNumber = 0;
	}

	@Override
	public boolean hasNext() {
		return rowNumber < grid.numberOfRows();
	}

	@Override
	public List<T> next() {
		List<T> parameterValues = new ArrayList<T>();
		for(int columnNumber = 0 ; columnNumber < types.size() ; columnNumber++) {
			parameterValues.add(valueOfCell(columnNumber));
		}
		rowNumber++;
		return parameterValues;
	}

	@SuppressWarnings("unchecked")
	private T valueOfCell(int columnNumber) {
		String textValue = grid.row(rowNumber).get(columnNumber);
		Class<?> type = types.get(columnNumber);
		return (T) convertersByType.get(type).valueOf(textValue);
	}

	@Override
	public void remove() {
		throw new NotImplementedException();
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
