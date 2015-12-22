package ru.sbertech.interview.store.converter;

import org.springframework.stereotype.Service;

@Service
public interface ValueConverter<T> {
	
	public T convert() throws Exception; 

}
