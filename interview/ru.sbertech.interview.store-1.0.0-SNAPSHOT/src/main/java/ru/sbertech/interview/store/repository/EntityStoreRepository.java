package ru.sbertech.interview.store.repository;

import org.springframework.transaction.annotation.Transactional;

public interface EntityStoreRepository {
	
	@Transactional
	public void save(Object entity) throws Exception;
	
}
