package com.dhemery.excelrowiterator.util;
import java.util.HashMap;
import java.util.Map;


public class InvocationCounter {
	private Map<String, Integer> invocationCountByName = new HashMap<String,Integer>();
	
	public int count(String name) {
		return invocationCountByName.get(name);
	}

	public void increment(String name) {
		ensureInvocationCountExistsFor(name);
		incrementInvocationCountFor(name);
	}

	private void incrementInvocationCountFor(String name) {
		invocationCountByName.put(name, count(name) + 1);
	}

	private void ensureInvocationCountExistsFor(String name) {
		if(!invocationCountByName.containsKey(name)) invocationCountByName.put(name, 0);
	}
}