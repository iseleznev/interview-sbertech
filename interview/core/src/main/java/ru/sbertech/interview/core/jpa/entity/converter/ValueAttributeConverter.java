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

		if (null == attribute) {
			return null;
		}
		ByteArrayOutputStream byteArrayStream = new ByteArrayOutputStream();		
		ObjectOutputStream objectStream = null;
		try {
			objectStream = new ObjectOutputStream(byteArrayStream);
			objectStream.writeObject(attribute);
			objectStream.close();
			return byteArrayStream.toByteArray();
			
		} catch (IOException e) {
			throw new IllegalArgumentException("Attribute can not be serialized in bytes array.", e);
		}
	}

	@Override
	public Object convertToEntityAttribute(byte[] dbData) {
		
		if (null == dbData) {
			return null;
		}
		
		ByteArrayInputStream byteArrayStream = new ByteArrayInputStream(dbData);
		ObjectInputStream objectStream = null;
		
		try {			
			objectStream = new ObjectInputStream(byteArrayStream);
			Object result = objectStream.readObject();
			objectStream.close();
			
			return result;
			
		} catch (IOException ex) {			
			StringBuilder message = new StringBuilder();
			message
			.append("An IOException thrown in deserialization method fro database: ")
			.append(ex.getMessage());
			
			throw new IllegalArgumentException(message.toString(), ex);
			
		} catch (ClassNotFoundException ex) {			
			StringBuilder message = new StringBuilder();
			message
			.append("An ClassNotFoundException thrown in deserialization method from database: ")
			.append(ex.getMessage());

			throw new IllegalArgumentException(message.toString(), ex);
			
		}
	}

}
