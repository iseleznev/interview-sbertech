package ru.sbertech.interview.core.entity.dispatcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import ru.sbertech.interview.core.entity.ValueEntity;
import ru.sbertech.interview.core.entity.repository.file.FileEntityStoreRepository;
import ru.sbertech.interview.core.entity.repository.jpa.JpaEntityStoreRepository;
import ru.sbertech.interview.core.entity.target.TargetType;
import ru.sbertech.interview.entity.repository.EntityStoreRepository;
import ru.sbertech.interview.store.dispatcher.AbstractEntityStoreDispatcher;

public class ValueEntityStoreDispatcher extends AbstractEntityStoreDispatcher<ValueEntity> {

	@Autowired
	private ApplicationContext applicationContext;
	
	@Override
	protected EntityStoreRepository getStoreRepository(ValueEntity entity) {

		EntityStoreRepository repository = null;
		

		if (entity.getTargetType() == TargetType.DATABASE)
			repository = applicationContext.getBean(JpaEntityStoreRepository.class);

		if (entity.getTargetType() == TargetType.FILE)
			repository = applicationContext.getBean(FileEntityStoreRepository.class);

		return repository;
	}

}
