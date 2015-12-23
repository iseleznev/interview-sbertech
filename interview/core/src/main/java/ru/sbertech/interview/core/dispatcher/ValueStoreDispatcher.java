package ru.sbertech.interview.core.dispatcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import ru.sbertech.interview.core.value.ValueContainer;
import ru.sbertech.interview.core.value.target.TargetType;
import ru.sbertech.interview.store.dispatcher.AbstractValueStoreDispatcher;
import ru.sbertech.interview.store.service.ValueService;

@Component
public class ValueStoreDispatcher extends AbstractValueStoreDispatcher<ValueContainer> {

	@Autowired
	private ApplicationContext applicationContext;
	
	@Override
	public ValueService getStoreRepository(ValueContainer entity) {

		ValueService repository = null;		

		if (entity.getTargetType() == TargetType.DATABASE)
			repository = (ValueService) applicationContext.getBean("jpaValueService");

		if (entity.getTargetType() == TargetType.FILE)
			repository = (ValueService) applicationContext.getBean("fileValueService");

		return repository;
	}

}
