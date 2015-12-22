package ru.sbertech.interview.core.jpa.entity.converter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.persistence.AttributeConverter;

public class ValueAttributeConverter implements AttributeConverter<Object, byte[]> {

	@Override
	public byte[] convertToDatabaseColumn(Object attribute) {
		
		ByteArrayOutputStream byteArrayStream = new ByteArrayOutputStream();		
		ObjectOutputStream outputStream = null;
		try {
			outputStream = new ObjectOutputStream(byteArrayStream);
			outputStream.writeObject(attribute);
			outputStream.close();
			return byteArrayStream.toByteArray();
			
		} catch (IOException e) {
			throw new IllegalArgumentException("Attribute can not be serialized in bytes array.", e);
		}
	}

	@Override
	public Object convertToEntityAttribute(byte[] dbData) {
		
		ByteArrayInputStream byteArrayStream = new ByteArrayInputStream(dbData);
		ObjectInputStream inputStream = null;
		
		try {			
			inputStream = new ObjectInputStream(byteArrayStream);
			Object result = inputStream.readObject();
			inputStream.close();
			
			return result;
			
		} catch (IOException ex) {			
			throw new IllegalArgumentException("Input stream error has occured.", ex);
			
		} catch (ClassNotFoundException ex) {			
			throw new IllegalArgumentException("Class not found for deserialization from database.", ex);
			
		}
	}

}
