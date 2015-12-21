package ru.sbertech.interview.core.jpa.service;


import org.springframework.transaction.annotation.Transactional;

import ru.sbertech.interview.core.entity.ValueEntity;

public interface ValueEntityService {
	
	@Transactional
	public void save(ValueEntity entity);

}
