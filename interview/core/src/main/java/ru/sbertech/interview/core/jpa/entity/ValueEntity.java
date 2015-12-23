package ru.sbertech.interview.core.jpa.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import ru.sbertech.interview.core.jpa.entity.converter.ValueAttributeConverter;

@Entity
@Table(name = "values_table")
public class ValueEntity implements Serializable {
	
	private static final long serialVersionUID = -1054706498556457763L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "typename")
	private String typeName;
	
	@Column(name = "stringvalue")
	private String stringValue;
	
	@Column(name = "valuedetails")
	@Convert(converter = ValueAttributeConverter.class)
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

		if (value instanceof Number || value instanceof String) {
			setStringValue(value.toString());
			this.value = null;
			return;
		}

		this.value = value;
		setStringValue("");
		return;
	}

}
