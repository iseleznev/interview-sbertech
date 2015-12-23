package ru.sbertech.interview.core.dispatcher;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ru.sbertech.interview.core.value.ValueContainer;
import ru.sbertech.interview.core.value.repository.file.FileValueService;
import ru.sbertech.interview.core.value.target.TargetType;
import ru.sbertech.interview.store.service.ValueService;

public class TestValueEntityStoreDispatcher {

	private ValueStoreDispatcher dispatcher;
	
	@Before
	public void setUp() {
		dispatcher = new ValueStoreDispatcher();
	}
	
	@Test
	public void testGetStoreRepositoryValueContainer() {
		
		ValueContainer container = new ValueContainer();
		container.setTargetType(TargetType.FILE);
		container.setValue("storeValueTestCase");
		
		ValueService repository = dispatcher.getStoreRepository(container);
		assertTrue(repository instanceof FileValueService);
	}

	@Test
	public void testDispatch() {
		fail("Not yet implemented");
	}

}
