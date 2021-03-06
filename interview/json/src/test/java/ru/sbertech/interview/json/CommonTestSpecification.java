package ru.sbertech.interview.json;

public class CommonTestSpecification {
	
	/*
	 * ничего лучше не придумал, чем создать отдельный класс в каталоге test для 
	 * хранения константных значений, 
	 * из других вариантов:
	 * ссылаться одному тесту на константу другого теста - каждый тест не должен ничего 
	 * знать о возможности существования других тестов
	 * второй вариант - дублировать константы в каждом тесте, в случае необходимости
	 * изменить константу придется искать, где же мы по "сто раз" упомянули ее,
	 * в каких еще тестах, и почему одни тесты проходят, а другие нет 
	 */
	public static final String TEST_VALID_JSON_STRING = 
			"{ \n" +
            "    \"target\": \"database\", \n" +
            "    \"value\": \"test\" \n" +
            "}";


}
