package ru.sbertech.interview.core.value.target;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class TargetTypeJsonDeserializer extends JsonDeserializer<TargetType> {
	
	@Override
	public TargetType deserialize(final JsonParser parser, final DeserializationContext context) throws IOException, JsonProcessingException
	{
	    final String jsonText = parser.getText();
	    if (TargetType.DATABASE_STRING_VALUE.equals(jsonText.toLowerCase())) {
	    	return TargetType.DATABASE;
	    }
	    if (TargetType.FILE_STRING_VALUE.equals(jsonText.toLowerCase())) {
	    	return TargetType.FILE;
	    }
	    throw new JsonParseException("Unrecognized target type identifier found.", parser.getCurrentLocation());
	    	
	}
}
