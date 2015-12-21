package ru.sbertech.interview.app;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.context.ApplicationContext;

public class TestInterviewApplication {

	@Test
	public void initialApplicationContext() {
		ApplicationContext applicationContext = 
				InterviewApplication.initializeApplicationContext();
		assertNotNull(applicationContext);
	}

}
