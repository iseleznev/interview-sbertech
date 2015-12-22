package ru.sbertech.interview.json.provider;

import static org.junit.Assert.*;

import java.io.CharArrayReader;

import org.junit.Test;

import ru.sbertech.interview.json.CommonTestSpecification;
import ru.sbertech.interview.json.provider.ReaderJsonStringProvider;

public class TestReaderJsonStringProvider {

	@Test
	public void testReadJsonString() {
		
		CharArrayReader reader = 
				new CharArrayReader(CommonTestSpecification.TEST_VALID_JSON_STRING.toCharArray());  
		ReaderJsonStringProvider jsonProvider = new ReaderJsonStringProvider();
		jsonProvider.setReader(reader);
		jsonProvider.readJsonString();
		
		assertTrue(jsonProvider.isValid());
	}

}
