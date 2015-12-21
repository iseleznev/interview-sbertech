package ru.sbertech.interview.json;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ru.sbertech.interview.json.builder.JsonStringBuilder;

public class TestJsonStringBuilder {


	private JsonStringBuilder jsonBuilder;
	
	@Before
	public void setUp() throws Exception {
		jsonBuilder = new JsonStringBuilder();
	}

	@Test
	public void testAdd() {
		jsonBuilder
		.add("{ \n")
		.add("    \"target\": \"database\", \n")
		.add("    \"value\": \"test\" \n")
		.add("}");
		assertTrue(jsonBuilder.toString(), jsonBuilder.isValid());
	}

	@Test
	public void testIsValid() {
		
		//неполный ввод json должен возвращать !isValid()
		jsonBuilder.add("{ \n");
		assertFalse(jsonBuilder.toString(), jsonBuilder.isValid());

		jsonBuilder.add("    \"target\": \"database\", \n");
		assertFalse(jsonBuilder.toString(), jsonBuilder.isValid());

		jsonBuilder.add("    \"value\": \"test\" \n");
		assertFalse(jsonBuilder.toString(), jsonBuilder.isValid());

		//завершается ввод json, isValid() должен вернуть true 
		jsonBuilder.add("}");
		assertTrue(jsonBuilder.toString(), jsonBuilder.isValid());
		
		//проверка на избыточность ввода закрывающих скобок
		jsonBuilder.add("} \n");
		assertFalse(jsonBuilder.toString(), jsonBuilder.isValid());		
		
		jsonBuilder.add("{ \n");
		assertFalse(jsonBuilder.toString(), jsonBuilder.isValid());
	}

	@Test
	public void testToString() {
		jsonBuilder
		.add("{ \n")
		.add("    \"target\": \"database\", \n")
		.add("    \"value\": \"test\" \n")
		.add("}");
		assertTrue(jsonBuilder.toString(), jsonBuilder.toString().equals(jsonBuilder.toString()));
	}


}
