package ru.sbertech.interview.core.jpa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.sbertech.interview.core.jpa.entity.ValueEntity;
import ru.sbertech.interview.core.jpa.repository.ValueEntityRepository;
import ru.sbertech.interview.core.jpa.service.ValueEntityService;

@Service
@Transactional
public class ValueEntityServiceImpl implements ValueEntityService {
	
	@Autowired
	private ValueEntityRepository crudRepository;

	public void save(ValueEntity entity) {
		
		crudRepository.save(entity);

	}


}
