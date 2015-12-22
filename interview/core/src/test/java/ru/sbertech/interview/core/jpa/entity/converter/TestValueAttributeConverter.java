package ru.sbertech.interview.core.jpa.entity.converter;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

public class TestValueAttributeConverter {

	private ValueAttributeConverter converter;
	
	@Before
	public void setUp() throws Exception {
		converter = new ValueAttributeConverter();
	}

	@Test
	public void testConvertToDatabaseColumn() throws IOException, ClassNotFoundException {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("order", "first");
		
		byte[] serializedMap = converter.convertToDatabaseColumn(map);
		
		ByteArrayInputStream byteArrayStream = new ByteArrayInputStream(serializedMap);
		
		ObjectInputStream objectStream = new ObjectInputStream(byteArrayStream);
		
		@SuppressWarnings("unchecked")
		HashMap<String, String> restoredMap = (HashMap<String, String>) objectStream.readObject();
		objectStream.close();
		
		assertTrue(map.containsKey("order") && restoredMap.containsKey("order")
				&& map.get("order").equals(restoredMap.get("order")));
		
	}

	@Test
	public void testConvertToEntityAttribute() {
	}

}
