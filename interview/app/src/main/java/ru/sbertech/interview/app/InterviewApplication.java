package ru.sbertech.interview.app;

import java.util.Properties;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import ru.sbertech.interview.app.configuration.ApplicationConfiguration;
import ru.sbertech.interview.core.converter.JsonValueConverter;
import ru.sbertech.interview.core.dispatcher.ValueEntityStoreDispatcher;
import ru.sbertech.interview.core.value.repository.file.FileValueStoreRepository;

public class InterviewApplication 
{
	
	private static ValueEntityStoreDispatcher dispatcher;
	
	private static JsonValueConverter jsonValueConverter;
	
    public static void main( String[] args ) 
    {
    	try {

    		AnnotationConfigApplicationContext applicationContext = initializeApplicationContext();
        	dispatcher = applicationContext.getBean(ValueEntityStoreDispatcher.class);
        	jsonValueConverter = applicationContext.getBean(JsonValueConverter.class);
        	
    		
    		String filename = FileValueStoreRepository.DEFAULT_PROPERTY_OUTPUT_FILENAME;
    		
    		if (args.length > 0) {
    			filename = args[0];
    		}
    		setProperty(FileValueStoreRepository.PROPERTY_OUTPUT_FILENAME, filename);
    		
        	dispatcher.dispatch(jsonValueConverter);
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
