package ru.sbertech.interview.core.converter;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ru.sbertech.interview.core.entity.ValueEntity;
import ru.sbertech.interview.json.provider.JsonStringProvider;
import ru.sbertech.interview.store.converter.EntityConverter;

public class JsonEntityConverter implements EntityConverter<ValueEntity> {

	private JsonStringProvider jsonProvider;
	
	@Autowired
	public JsonEntityConverter(JsonStringProvider jsonProvider) {
		this.setJsonProvider(jsonProvider);
	}
	
	@Override
	public ValueEntity convert() throws JsonParseException, JsonMappingException, IOException {
		
		JsonStringProvider provider = getJsonProvider();
		String jsonString = provider.readJsonString();
		
		ObjectMapper mapper = new ObjectMapper();
		ValueEntity entity = mapper.readValue(jsonString, ValueEntity.class);
		
		return entity;
	}

	public JsonStringProvider getJsonProvider() {
		return jsonProvider;
	}

	public void setJsonProvider(JsonStringProvider jsonProvider) {
		this.jsonProvider = jsonProvider;
	}

}
