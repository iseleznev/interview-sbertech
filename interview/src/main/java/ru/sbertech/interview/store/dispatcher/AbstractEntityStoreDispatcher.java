package ru.sbertech.interview.store.dispatcher;

import ru.sbertech.interview.entity.repository.EntityStoreRepository;
import ru.sbertech.interview.store.converter.EntityConverter;

public abstract class AbstractEntityStoreDispatcher<E> {
	
	public void dispatch(EntityConverter<E> entityConverter) throws Exception {
		
		E entity = entityConverter.convert();
		
	    EntityStoreRepository repository = getStoreRepository(entity);
		repository.save(entity);
			
	}
	
	protected abstract EntityStoreRepository getStoreRepository(E entity);

}
