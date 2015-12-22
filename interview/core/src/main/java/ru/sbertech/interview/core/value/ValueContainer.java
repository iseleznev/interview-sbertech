package ru.sbertech.interview.core.value;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import ru.sbertech.interview.core.value.target.TargetType;

@Entity
@Table(name = "entity")
public class ValueContainer {
	
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
