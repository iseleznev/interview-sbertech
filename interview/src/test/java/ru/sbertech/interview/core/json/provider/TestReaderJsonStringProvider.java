package ru.sbertech.interview.core.json.provider;

import static org.junit.Assert.*;

import java.io.CharArrayReader;

import org.junit.Before;
import org.junit.Test;


public class TestReaderJsonStringProvider {

	@Test
	public void testReadJsonString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder
		.append("{ \n")
		.append("    \"target\": \"database\", \n")
		.append("    \"value\": \"test\" \n")
		.append("}");
		
		CharArrayReader reader = new CharArrayReader(stringBuilder.toString().toCharArray());  
		ReaderJsonStringProvider jsonConsole = new ReaderJsonStringProvider();
		jsonConsole.setReader(reader);
		jsonConsole.readSerializedEntity();
		
		assertTrue(jsonConsole.isValid());
	}

}
