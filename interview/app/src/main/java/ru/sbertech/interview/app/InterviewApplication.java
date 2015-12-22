package ru.sbertech.interview.app;

import java.util.Properties;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import ru.sbertech.interview.app.configuration.ApplicationConfiguration;
import ru.sbertech.interview.core.converter.JsonValueConverter;
import ru.sbertech.interview.core.dispatcher.ValueEntityStoreDispatcher;
import ru.sbertech.interview.core.repository.file.FileEntityStoreRepository;

public class InterviewApplication 
{
	
	private static ValueEntityStoreDispatcher dispatcher;
	
	private static JsonValueConverter jsonEntityConverter;
	
    public static void main( String[] args ) 
    {
    	try {

    		AnnotationConfigApplicationContext applicationContext = initializeApplicationContext();
        	dispatcher = applicationContext.getBean(ValueEntityStoreDispatcher.class);
        	jsonEntityConverter = applicationContext.getBean(JsonValueConverter.class);
        	
    		
    		String filename = FileEntityStoreRepository.DEFAULT_PROPERTY_OUTPUT_FILENAME;
    		
    		if (args.length > 0) {
    			filename = args[0];
    		}
    		setProperty(FileEntityStoreRepository.PROPERTY_OUTPUT_FILENAME, filename);
    		
        	dispatcher.dispatch(jsonEntityConverter);
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
