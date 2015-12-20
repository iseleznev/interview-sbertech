package ru.sbertech.interview.core.jpa.repository;

import org.springframework.data.repository.CrudRepository;

import ru.sbertech.interview.core.entity.ValueEntity;

public interface ValueEntityRepository extends CrudRepository<ValueEntity, Long> {

}
