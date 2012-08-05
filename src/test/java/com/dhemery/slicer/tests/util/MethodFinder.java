package com.dhemery.slicer.tests.util;

import java.lang.reflect.Method;

public class MethodFinder {
	public static Method find(Object object, String methodName) {
		for(Method method : object.getClass().getMethods()) {
			if(method.getName().equals(methodName)) return method;
		}
		return null;
	}
}
