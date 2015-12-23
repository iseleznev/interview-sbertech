package ru.sbertech.interview.app;

import java.util.Properties;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import ru.sbertech.interview.app.configuration.ApplicationConfiguration;
import ru.sbertech.interview.core.converter.JsonValueConverter;
import ru.sbertech.interview.core.dispatcher.ValueStoreDispatcher;
import ru.sbertech.interview.core.value.repository.file.FileValueService;

public class InterviewApplication 
{
	
	private static ValueStoreDispatcher dispatcher;
	
	private static JsonValueConverter jsonValueConverter;
	
    public static void main( String[] args ) 
    {
    	try {

    		System.out.println("Application started. Create application context...");
    		AnnotationConfigApplicationContext applicationContext = initializeApplicationContext();
    		System.out.println("Application context created");
        	dispatcher = applicationContext.getBean(ValueStoreDispatcher.class);
        	jsonValueConverter = applicationContext.getBean(JsonValueConverter.class);
        	
    		
    		String filename = FileValueService.DEFAULT_PROPERTY_OUTPUT_FILENAME;
    		
    		if (args.length > 0) {
    			filename = args[0];
    		}
    		setProperty(FileValueService.PROPERTY_OUTPUT_FILENAME, filename);
    		
    		System.out.println("Please enter text in JSON format. For done close your JSON-code with figure bracket or press [ENTER] twice.");
        	dispatcher.dispatch(jsonValueConverter);
        	System.out.println("Program execution finished.");
    	}
    	catch (Exception ex) {
    		ex.printStackTrace();
    	}
    }
    
    public static AnnotationConfigApplicationContext initializeApplicationContext() {

    	AnnotationConfigApplicationContext applicationContext = 
    			new AnnotationConfigApplicationContext(ApplicationConfiguration.class);    	

    	return applicationContext;
    	
    }
    
    private static void setProperty(String key, String value) {
    	Properties properties = System.getProperties();
    	properties.setProperty(key, value);
    }

}
