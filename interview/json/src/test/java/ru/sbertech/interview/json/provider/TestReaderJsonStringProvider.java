package ru.sbertech.interview.json.provider;

import static org.junit.Assert.*;

import java.io.CharArrayReader;

import org.junit.Test;

import ru.sbertech.interview.json.provider.ReaderJsonStringProvider;

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
		jsonConsole.readJsonString();
		
		assertTrue(jsonConsole.isValid());
	}

}
