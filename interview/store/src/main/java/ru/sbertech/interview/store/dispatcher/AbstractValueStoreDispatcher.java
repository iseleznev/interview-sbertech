package ru.sbertech.interview.store.dispatcher;

import ru.sbertech.interview.store.converter.ValueConverter;
import ru.sbertech.interview.store.service.ValueService;

public abstract class AbstractValueStoreDispatcher<T> {
	
	public void dispatch(ValueConverter<T> valueConverter) throws Exception {
		
		T value = valueConverter.convert();
		
	    ValueService repository = getStoreRepository(value);
		repository.save(value);
			
	}
	
	public abstract ValueService getStoreRepository(T value);

}
