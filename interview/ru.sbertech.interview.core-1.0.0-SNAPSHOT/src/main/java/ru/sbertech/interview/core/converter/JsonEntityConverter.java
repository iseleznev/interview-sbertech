package ru.sbertech.interview.core.converter;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ru.sbertech.interview.core.entity.ValueEntity;
import ru.sbertech.interview.core.json.provider.JsonStringProvider;
import ru.sbertech.interview.store.converter.AbstractEntityConverter;

public class JsonEntityConverter extends AbstractEntityConverter<String, ValueEntity> {

	@Autowired
	public JsonEntityConverter(JsonStringProvider provider) {
		super(provider);
	}
	
	@Override
	public ValueEntity convert() throws JsonParseException, JsonMappingException, IOException {
		
		JsonStringProvider provider = (JsonStringProvider) getProvider();
		String jsonString = provider.readSerializedEntity();
		
		ObjectMapper mapper = new ObjectMapper();
		ValueEntity entity = mapper.readValue(jsonString, ValueEntity.class);
		
		return entity;
	}

}
