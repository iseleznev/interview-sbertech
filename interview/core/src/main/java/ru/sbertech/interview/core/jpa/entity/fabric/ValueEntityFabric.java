package ru.sbertech.interview.core.jpa.entity.fabric;

import org.springframework.stereotype.Component;

import ru.sbertech.interview.core.jpa.entity.ValueEntity;
import ru.sbertech.interview.core.value.ValueContainer;

@Component
public class ValueEntityFabric {
	
	public ValueEntity createValueEntity(ValueContainer container) {
		
		if (null == container) {
			throw new IllegalArgumentException("Value container cannot be null.");
		}
		
		ValueEntity entity = new ValueEntity(container.getValue());
		return entity;
		
	}


}
