package ru.sbertech.interview.core.repository.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.sbertech.interview.core.jpa.entity.ValueEntity;
import ru.sbertech.interview.core.jpa.entity.fabric.ValueEntityFabric;
import ru.sbertech.interview.core.jpa.service.impl.ValueEntityServiceImpl;
import ru.sbertech.interview.core.value.ValueContainer;
import ru.sbertech.interview.store.exception.FailedSaveValueException;
import ru.sbertech.interview.store.repository.ValueStoreRepository;

@Component
public class JpaEntityStoreRepository implements ValueStoreRepository {

	@Autowired
	private ValueEntityFabric valueEntityFabric;
	
	@Autowired
	private ValueEntityServiceImpl crudService;
	
	@Transactional
	public void save(Object value) throws FailedSaveValueException {
		
		ValueContainer valueContainer = (ValueContainer) value;
		ValueEntity entity = valueEntityFabric.createValueEntity(valueContainer); 
		crudService.save(entity);
	}

}
