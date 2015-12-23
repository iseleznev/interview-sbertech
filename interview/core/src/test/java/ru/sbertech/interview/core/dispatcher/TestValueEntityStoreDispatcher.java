package ru.sbertech.interview.core.dispatcher;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ru.sbertech.interview.core.value.ValueContainer;
import ru.sbertech.interview.core.value.repository.file.FileValueStoreRepository;
import ru.sbertech.interview.core.value.target.TargetType;
import ru.sbertech.interview.store.repository.ValueStoreRepository;

public class TestValueEntityStoreDispatcher {

	private ValueEntityStoreDispatcher dispatcher;
	
	@Before
	public void setUp() {
		dispatcher = new ValueEntityStoreDispatcher();
	}
	
	@Test
	public void testGetStoreRepositoryValueContainer() {
		
		ValueContainer container = new ValueContainer();
		container.setTargetType(TargetType.FILE);
		container.setValue("storeValueTestCase");
		
		ValueStoreRepository repository = dispatcher.getStoreRepository(container);
		assertTrue(repository instanceof FileValueStoreRepository);
	}

	@Test
	public void testDispatch() {
		fail("Not yet implemented");
	}

}
