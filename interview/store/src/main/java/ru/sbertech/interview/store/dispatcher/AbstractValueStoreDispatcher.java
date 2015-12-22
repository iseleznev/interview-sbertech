package ru.sbertech.interview.store.dispatcher;

import ru.sbertech.interview.store.repository.ValueStoreRepository;
import ru.sbertech.interview.store.converter.ValueConverter;

public abstract class AbstractValueStoreDispatcher<T> {
	
	public void dispatch(ValueConverter<T> valueConverter) throws Exception {
		
		T value = valueConverter.convert();
		
	    ValueStoreRepository repository = getStoreRepository(value);
		repository.save(value);
			
	}
	
	protected abstract ValueStoreRepository getStoreRepository(T value);

}
