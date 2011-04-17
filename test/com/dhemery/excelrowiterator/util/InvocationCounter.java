package com.dhemery.excelrowiterator.util;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;


public class InvocationCounter {
	private Map<Method, Integer> invocationCountByName = new HashMap<Method,Integer>();
	
	public int count(Method method) {
		ensureInvocationCountExistsFor(method);
		return invocationCountByName.get(method);
	}

	public void increment(Method method) {
		incrementInvocationCountFor(method);
	}

	private void incrementInvocationCountFor(Method method) {
		invocationCountByName.put(method, count(method) + 1);
	}

	private void ensureInvocationCountExistsFor(Method method) {
		if(!invocationCountByName.containsKey(method)) invocationCountByName.put(method, 0);
	}
}