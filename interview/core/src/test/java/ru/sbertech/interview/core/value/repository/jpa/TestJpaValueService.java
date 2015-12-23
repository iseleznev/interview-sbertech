package ru.sbertech.interview.core.value.repository.jpa;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import ru.sbertech.interview.core.value.repository.file.ApplicationContextTestConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, 
                      classes = ApplicationContextTestConfiguration.class)
public class TestJpaValueService {

	@Test
	@Transactional
	public void testSave() {
		
	}

}
