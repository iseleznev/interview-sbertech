package ru.sbertech.interview.core.jpa.entity;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Test;

public class TestValueEntity {

	@Test
	public void testSetValue() {
		HashMap<String, String> objectValue = new HashMap<String, String>(); 
		ValueEntity entity = new ValueEntity();
		entity.setValue(objectValue);
		assertTrue("".equals(entity.getStringValue()));
		assertTrue(entity.getValue() != null);
		assertTrue(entity.getValue().getClass().equals(objectValue.getClass()));
		assertTrue(entity.getTypeName().equals(objectValue.getClass().toString()));
		
		entity.setValue("testCase");
		assertTrue("testCase".equals(entity.getStringValue()));
		assertTrue(entity.getValue() == null);
		assertTrue(entity.getTypeName().equals("testCase".getClass().toString()));
		
	}

}
