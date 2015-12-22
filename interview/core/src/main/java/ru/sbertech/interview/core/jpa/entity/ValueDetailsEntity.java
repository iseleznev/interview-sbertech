package ru.sbertech.interview.core.jpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "value_details")
public class ValueDetailsEntity {

	@Id
	private long id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "value_id")
	private long value_id;
	
	@Column(name = "details")
	private Object details;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getValue_id() {
		return value_id;
	}

	public void setValue_id(long value_id) {
		this.value_id = value_id;
	}

	public Object getDetails() {
		return details;
	}

	public void setDetails(Object details) {
		this.details = details;
	}
	
	
}
