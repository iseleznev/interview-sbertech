package ru.sbertech.interview.core.converter;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ru.sbertech.interview.core.value.ValueContainer;
import ru.sbertech.interview.json.provider.JsonStringProvider;
import ru.sbertech.interview.store.converter.ValueConverter;

public class JsonValueConverter implements ValueConverter<ValueContainer> {

	private JsonStringProvider jsonProvider;
	
	@Autowired
	public JsonValueConverter(JsonStringProvider jsonProvider) {
		this.setJsonProvider(jsonProvider);
	}
	
	@Override
	public ValueContainer convert() throws JsonParseException, JsonMappingException, IOException {
		
		JsonStringProvider provider = getJsonProvider();
		String jsonString = provider.readJsonString();
		
		ObjectMapper mapper = new ObjectMapper();
		ValueContainer entity = mapper.readValue(jsonString, ValueContainer.class);
		
		return entity;
	}

	public JsonStringProvider getJsonProvider() {
		return jsonProvider;
	}

	public void setJsonProvider(JsonStringProvider jsonProvider) {
		this.jsonProvider = jsonProvider;
	}

}
