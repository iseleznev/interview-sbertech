package ru.sbertech.interview.json.provider;

import static org.junit.Assert.*;

import java.io.CharArrayReader;

import org.junit.Test;

import ru.sbertech.interview.json.provider.ReaderJsonStringProvider;

public class TestReaderJsonStringProvider {

	/*
	 * ничего лучше не придумал, чем дублирование константы, отдельных класс для хранения
	 * констант создавать не хочется, зачем в пакете болтаться еще одному классу,
	 * ссылаться одному тесту на константу другого теста... зачем тесту знать о 
	 * существовании другого теста?
	 * данных подход тоже плох (дублирование вообще есть зло), в случае необходимости
	 * придется искать, где же мы по сто раз упомянули этот код, и почему одни тесты
	 * проходят, а другие не проходят? 
	 */
	private static final String TEST_VALID_JSON_STRING = 
			"{ \n" +
            "    \"target\": \"database\", \n" +
            "    \"value\": \"test\" \n" +
            "}";

	@Test
	public void testReadJsonString() {
		
		CharArrayReader reader = new CharArrayReader(TEST_VALID_JSON_STRING.toCharArray());  
		ReaderJsonStringProvider jsonProvider = new ReaderJsonStringProvider();
		jsonProvider.setReader(reader);
		jsonProvider.readJsonString();
		
		assertTrue(jsonProvider.isValid());
	}

}
