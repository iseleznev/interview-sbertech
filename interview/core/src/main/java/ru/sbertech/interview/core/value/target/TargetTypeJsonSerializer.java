package ru.sbertech.interview.core.value.target;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class TargetTypeJsonSerializer extends JsonSerializer<TargetType> {

	@Override
    public void serialize(final TargetType enumValue, final JsonGenerator gen, final SerializerProvider serializer) 
    		throws IOException, JsonProcessingException {
    	
		String value;
		switch (enumValue) {
		case DATABASE: value = TargetType.DATABASE_STRING_VALUE; break;
		case FILE: value = TargetType.FILE_STRING_VALUE; break;
		default: value = TargetType.UNKNOWN_STRING_VALUE; 
			break;
		}
	    gen.writeString(value);
    	
    	
    }

}