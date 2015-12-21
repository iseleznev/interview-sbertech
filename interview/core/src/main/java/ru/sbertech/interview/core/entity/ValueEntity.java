package ru.sbertech.interview.core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import ru.sbertech.interview.core.entity.target.TargetType;

@Entity
@Table(name = "entity")
public class ValueEntity {
	
	private transient TargetType targetType;
	
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
