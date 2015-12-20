package ru.sbertech.interview.store.converter;

import ru.sbertech.interview.store.provider.SerializedEntityProvider;

public abstract class AbstractEntityConverter<T, E> implements EntityConverter<E> {

	private SerializedEntityProvider<T> provider;
	
	public AbstractEntityConverter(SerializedEntityProvider<T> provider) {
		this.setProvider(provider); 
	}
	
	@Override
	public abstract E convert() throws Exception;

	public SerializedEntityProvider<T> getProvider() {
		return provider;
	}

	public void setProvider(SerializedEntityProvider<T> provider) {
		this.provider = provider;
	}

}
