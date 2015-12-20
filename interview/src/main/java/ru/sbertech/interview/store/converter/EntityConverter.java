package ru.sbertech.interview.store.converter;

public interface EntityConverter<E> {
	
	public E convert() throws Exception; 

}
