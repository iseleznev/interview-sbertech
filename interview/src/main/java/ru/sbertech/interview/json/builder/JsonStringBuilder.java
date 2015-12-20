package ru.sbertech.interview.json.builder;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonStringBuilder {

	private final StringBuilder stringBuilder = new StringBuilder();
	private final ObjectMapper jsonMapper = new ObjectMapper();
	
	private int figureBracketsCount = 0;
	private int squareBracketsCount = 0;
	private boolean quotesOpened = false;
	
	public JsonStringBuilder add(String line) {
		
		stringBuilder.append(line);
		parseLine(line);
		return this;
		
	}
	
	public boolean isValid() {
		
		return (!quotesOpened && 0 == figureBracketsCount && 0 == squareBracketsCount  
				&& validateBuildResult()); 
		
	}
	
	public String toString() {
		return stringBuilder.toString();
	}
	
	private void parseLine(String line) {
		
		int length = line.length();
		
		for(int i = 0; i < length; i++) {
			char symbol = line.charAt(i);
			if ('"' == symbol && (i == 0 || line.charAt(i - 1) != '\\')) {
				quotesOpened = !quotesOpened;
			}
			if (quotesOpened) {
				continue;
			}
			
			/* Проверки figureBracketsCount >= 0 и squareBracketsCount >= 0 имеют смысл, 
			 * так-как если закрывающих скобок уже встретилось больше, чем открывающих,
			 * общая json-строка должна признаваться невалидной в любом случае 
			 */
			if ('{' == symbol && figureBracketsCount >= 0) {
				figureBracketsCount++;				
			}
			if ('[' == symbol && squareBracketsCount >= 0) {
				squareBracketsCount++;
			}
			if ('}' == symbol) {
				figureBracketsCount--;
			}
			if (']' == symbol) {
				squareBracketsCount--;
			}
				
		}
		
	}
	
	private boolean validateBuildResult() {
		
	    try {
	    	
	    	final JsonNode jsonNode = jsonMapper.readTree(stringBuilder.toString());
	    	return (null != jsonNode);
			
		} catch (JsonParseException e) {
			return false;
		} catch (IOException e) {
			return false;
		}
	    
	}

}
