package ru.sbertech.interview.core.entity.repository.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import ru.sbertech.interview.core.entity.ValueEntity;
import ru.sbertech.interview.core.jpa.service.impl.ValueEntityServiceImpl;
import ru.sbertech.interview.entity.repository.EntityStoreRepository;

public class JpaEntityStoreRepository implements EntityStoreRepository {

	@Autowired
	private ValueEntityServiceImpl crudService;
	
	@Transactional
	public void save(Object entity) {
		
		ValueEntity valueEntity = (ValueEntity) entity; 
		crudService.save(valueEntity);
	}

}
