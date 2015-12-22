package ru.sbertech.interview.core.repository.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import ru.sbertech.interview.core.entity.ValueEntity;
import ru.sbertech.interview.core.jpa.service.impl.ValueEntityServiceImpl;
import ru.sbertech.interview.store.repository.EntityStoreRepository;


public class DaoEntityStoreRepository implements EntityStoreRepository {

	@Autowired
	private ValueEntityServiceImpl crudService;
	
	@Transactional
	public void save(Object entity) {
		
		ValueEntity valueEntity = (ValueEntity) entity; 
		crudService.save(valueEntity);
	}

}
