package ru.sbertech.interview.store.repository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.sbertech.interview.store.exception.FailedSaveValueException;

@Service
public interface ValueStoreRepository {
	
	@Transactional
	public void save(Object value) throws FailedSaveValueException;
	
}
