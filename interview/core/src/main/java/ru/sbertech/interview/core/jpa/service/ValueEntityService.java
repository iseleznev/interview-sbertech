package ru.sbertech.interview.core.jpa.service;


import org.springframework.data.repository.NoRepositoryBean;

import ru.sbertech.interview.core.jpa.entity.ValueEntity;

@NoRepositoryBean
public interface ValueEntityService {
	
	public void save(ValueEntity entity);

}
