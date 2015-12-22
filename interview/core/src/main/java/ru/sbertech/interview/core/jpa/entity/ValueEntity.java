package ru.sbertech.interview.core.jpa.entity;

import java.util.Map;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import antlr.collections.List;
import ru.sbertech.interview.core.jpa.entity.converter.TestValueAttributeConverter;

@Entity
@Table(name = "values")
public class ValueEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "typename")
	private String typeName;
	
	@Column(name = "stringvalue")
	private String stringValue;
	
	@Column(name = "valuedetails")
	@Lob
	@Convert(converter = TestValueAttributeConverter.class)
	private Object value;
	
	public ValueEntity() {
		
	}
	
	public ValueEntity(Object value) {
		setValue(value);
	}

	public long getId() {
		return id;
	}

	public String getTypeName() {
		return typeName;
	}

	protected void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Object getStringValue() {
		return stringValue;
	}

	protected void setStringValue(String stringValue) {
		this.stringValue = stringValue;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		String typeName = value.getClass().toString();
		setTypeName(typeName);
		if (value instanceof Map || value instanceof List || value instanceof Set) {
			this.value = value;
			setStringValue("");
			return;
		}
		setStringValue(value.toString());
		this.value = null;
	}

}
