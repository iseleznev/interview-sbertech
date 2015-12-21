package ru.sbertech.interview.core.json.provider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

import ru.sbertech.interview.json.builder.JsonStringBuilder;

public class ReaderJsonStringProvider implements JsonStringProvider {

	private Reader reader;
	private JsonStringBuilder jsonBuilder;

	public String readSerializedEntity() {

		jsonBuilder = new JsonStringBuilder();
		BufferedReader bufferedReader = new BufferedReader(reader); 
		try {

			while ( read(bufferedReader.readLine()) ) {
				
			}
			if (jsonBuilder.isValid()) { 
				return jsonBuilder.toString();
			}
			
		} catch (IOException e) {
			return "";
		}
		return ""; 
	}

	public boolean isValid() {
		if (jsonBuilder != null)
			return jsonBuilder.isValid();
		return false;
	}
	
	private boolean read(String jsonLine) {
		//String jsonLine = jsonProvider.readJsonString();
		jsonBuilder.add(jsonLine);
		return !"".equals(jsonLine) && !jsonBuilder.isValid();
	}


	public Reader getReader() {
		return reader;
	}
	
	public void setReader(Reader reader) {
		this.reader = reader;
	}

}
