package ru.sbertech.interview;

import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import ru.sbertech.interview.configuration.ApplicationConfiguration;
import ru.sbertech.interview.configuration.EntityStoreConfiguration;
import ru.sbertech.interview.core.entity.converter.JsonEntityConverter;
import ru.sbertech.interview.core.entity.dispatcher.ValueEntityStoreDispatcher;
import ru.sbertech.interview.core.json.provider.ReaderJsonStringProvider;

public class InterviewApplication 
{
	
	public static final String PROPERTY_OUTPUT_FILENAME = "output.filename";
	public static final String DEFAULT_PROPERTY_OUTPUT_FILENAME = "value.bin";
	
	@Autowired
	private ValueEntityStoreDispatcher dispatcher;
	
	@Autowired
	private JsonEntityConverter jsonEntityConverter;
	
	private final HashMap<String, String> parameters = new HashMap<String, String>(); 
	
    public static void main( String[] args )
    {
    	try {

    		initializeApplicationContext();

    		String filename = DEFAULT_PROPERTY_OUTPUT_FILENAME;
    		
    		if (args.length > 0) {
    			filename = args[0];
    		}
    		setProperty(PROPERTY_OUTPUT_FILENAME, filename);
    		
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
    	jsonEntityConverter.setProvider(provider);
    	
    	dispatcher.dispatch(jsonEntityConverter);
    	
    }
    
	public HashMap<String, String> getParameters() {
		return parameters;
	}
    
}
