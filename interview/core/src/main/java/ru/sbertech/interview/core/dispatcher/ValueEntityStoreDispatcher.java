package ru.sbertech.interview.core.dispatcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import ru.sbertech.interview.core.value.ValueContainer;
import ru.sbertech.interview.core.value.target.TargetType;
import ru.sbertech.interview.store.dispatcher.AbstractValueStoreDispatcher;
import ru.sbertech.interview.store.repository.ValueStoreRepository;

@Component
public class ValueEntityStoreDispatcher extends AbstractValueStoreDispatcher<ValueContainer> {

	@Autowired
	private ApplicationContext applicationContext;
	
	@Override
	public ValueStoreRepository getStoreRepository(ValueContainer entity) {

		ValueStoreRepository repository = null;		

		if (entity.getTargetType() == TargetType.DATABASE)
			repository = (ValueStoreRepository) applicationContext.getBean("jpaValueStoreRepository");

		if (entity.getTargetType() == TargetType.FILE)
			repository = (ValueStoreRepository) applicationContext.getBean("fileValueStoreRepository");

		return repository;
	}

}
