package ru.sbertech.interview.core.value;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import ru.sbertech.interview.core.value.target.TargetType;
import ru.sbertech.interview.core.value.target.TargetTypeJsonDeserializer;

@Entity
@Table(name = "entity")
public class ValueContainer {
	
	@JsonProperty("target")
	@JsonDeserialize(using = TargetTypeJsonDeserializer.class)
	private TargetType targetType;
	
	@Column(name = "value")
	private Object value;
	
	public TargetType getTargetType() {
		return targetType;
	}
	public void setTargetType(TargetType targetType) {
		this.targetType = targetType;
	}

	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
}
