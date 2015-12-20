package ru.sbertech.interview.core.entity;

import ru.sbertech.interview.core.entity.target.TargetType;

public class ValueEntity {
	
	private transient TargetType targetType;
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
