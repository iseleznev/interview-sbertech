package ru.sbertech.interview.store.provider;

public interface SerializedEntityProvider<T> {
	
	public T readSerializedEntity();
}
