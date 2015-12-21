package ru.sbertech.interview.app;

import java.io.InputStreamReader;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import ru.sbertech.interview.app.configuration.ApplicationConfiguration;
import ru.sbertech.interview.app.configuration.EntityStoreConfiguration;
import ru.sbertech.interview.core.converter.JsonEntityConverter;
import ru.sbertech.interview.core.dispatcher.ValueEntityStoreDispatcher;
import ru.sbertech.interview.core.repository.file.FileEntityStoreRepository;
import ru.sbertech.interview.json.provider.ReaderJsonStringProvider;

public class InterviewApplication 
{
	
	@Autowired
	private ValueEntityStoreDispatcher dispatcher;
	
	@Autowired
	private JsonEntityConverter jsonEntityConverter;
	
    public static void main( String[] args )
    {
    	try {

    		initializeApplicationContext();

    		String filename = FileEntityStoreRepository.DEFAULT_PROPERTY_OUTPUT_FILENAME;
    		
    		if (args.length > 0) {
    			filename = args[0];
    		}
    		setProperty(FileEntityStoreRepository.PROPERTY_OUTPUT_FILENAME, filename);
    		
    		InterviewApplication application = new InterviewApplication();
        	application.start();
    	}
    	catch (Exception ex) {
    		ex.printStackTrace();
    	}
    	
    }
    
    public static AnnotationConfigApplicationContext initializeApplicationContext() {

    	AnnotationConfigApplicationContext applicationContext = 
    			new AnnotationConfigApplicationContext(); 

    	applicationContext.register(ApplicationConfiguration.class);
    	applicationContext.register(EntityStoreConfiguration.class);
    	applicationContext.refresh();
    	return applicationContext;
    	
    }
    
    private static void setProperty(String key, String value) {
    	Properties properties = System.getProperties();
    	properties.setProperty(key, value);
    }

    public void start() throws Exception { 
    	
    	ReaderJsonStringProvider provider = new ReaderJsonStringProvider();
    	provider.setReader(new InputStreamReader(System.in));
    	jsonEntityConverter.setJsonProvider(provider);
    	
    	dispatcher.dispatch(jsonEntityConverter);
    	
    }
    
}
