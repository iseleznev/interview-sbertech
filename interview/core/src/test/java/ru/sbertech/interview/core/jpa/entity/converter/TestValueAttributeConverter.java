package ru.sbertech.interview.core.jpa.entity.converter;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

public class TestValueAttributeConverter {

	private ValueAttributeConverter converter;
	
	@Before
	public void setUp() throws Exception {
		converter = new ValueAttributeConverter();
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testConvertToDatabaseColumn() throws IOException, ClassNotFoundException {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("order", "first");
		
		byte[] serializedMap = converter.convertToDatabaseColumn(map);
		
		HashMap<String, String> restoredMap = null;
		
		ByteArrayInputStream byteArrayStream = new ByteArrayInputStream(serializedMap);		
		
		try (ObjectInputStream objectStream = new ObjectInputStream(byteArrayStream)) {
		
			Object valueEntity = objectStream.readObject();
			restoredMap = (HashMap<String, String>) valueEntity;
			objectStream.close();
		}
		catch (IOException | ClassNotFoundException ex) {
			throw ex;
		}
		
		assertTrue(map.containsKey("order") && restoredMap.containsKey("order")
				&& map.get("order").equals(restoredMap.get("order")));
		
	}

	@Test
	public void testConvertToEntityAttribute() throws IOException {
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("order", "first");
		byte[] serializedArray = null;
		
		ByteArrayOutputStream byteArrayStream = new ByteArrayOutputStream();
		try (ObjectOutputStream outputStream = new ObjectOutputStream(byteArrayStream)) {

			outputStream.writeObject(map);
			serializedArray = byteArrayStream.toByteArray();

			outputStream.close();
		}
		catch (IOException ex) {
			throw ex;
		}
		
		Object valueEntity = converter.convertToEntityAttribute(serializedArray);
		
		@SuppressWarnings("unchecked")
		HashMap<String, String> restoredMap = (HashMap<String, String>) valueEntity; 
		
		assertTrue(map.containsKey("order") && restoredMap.containsKey("order")
				&& map.get("order").equals(restoredMap.get("order")));
		
	}

}
