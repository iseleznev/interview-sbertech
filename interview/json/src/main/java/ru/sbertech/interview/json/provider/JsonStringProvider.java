package ru.sbertech.interview.json.provider;

import org.springframework.stereotype.Service;

@Service
public interface JsonStringProvider {
	
	public String readJsonString();
	
	public boolean isValid();
	
}
