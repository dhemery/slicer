package com.dhemery.excelrowiterator.util;
import java.util.HashMap;
import java.util.Map;


public class InvocationCounter {
	private Map<String, Integer> invocationCountByName = new HashMap<String,Integer>();
	
	public int count(String name) {
		ensureInvocationCountExistsFor(name);
		incrementInvocationCountFor(name);
		return invocationCountByName.get(name);
	}

	private void incrementInvocationCountFor(String name) {
		int invocationCount = invocationCountByName.get(name);
		invocationCount++;
		invocationCountByName.put(name, invocationCount);
	}

	private void ensureInvocationCountExistsFor(String name) {
		if(!invocationCountByName.containsKey(name)) invocationCountByName.put(name, 0);
	}
}