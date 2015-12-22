package ru.sbertech.interview.core.dispatcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import ru.sbertech.interview.core.repository.dao.JpaEntityStoreRepository;
import ru.sbertech.interview.core.repository.file.FileEntityStoreRepository;
import ru.sbertech.interview.core.value.ValueContainer;
import ru.sbertech.interview.core.value.target.TargetType;
import ru.sbertech.interview.store.dispatcher.AbstractValueStoreDispatcher;
import ru.sbertech.interview.store.repository.ValueStoreRepository;

@Component
public class ValueEntityStoreDispatcher extends AbstractValueStoreDispatcher<ValueContainer> {

	@Autowired
	private ApplicationContext applicationContext;
	
	@Override
	protected ValueStoreRepository getStoreRepository(ValueContainer entity) {

		ValueStoreRepository repository = null;		

		if (entity.getTargetType() == TargetType.DATABASE)
			repository = applicationContext.getBean(JpaEntityStoreRepository.class);

		if (entity.getTargetType() == TargetType.FILE)
			repository = applicationContext.getBean(FileEntityStoreRepository.class);

		return repository;
	}

}
