package com.logistica.numbercounter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.logistica.numbercounter.repository.NumberCounterRepository;

@Service
public class NumberCounterService {
	
	/* Count of API calls */
	int count;
	
	@Autowired
	private NumberCounterRepository counterRepository;
	
	
	/*
	 * Checks state of the table. Increments count value and calls method to persist
	 * increased count value
	 */	 
	public int incrementCounter() throws Exception
	{
		synchronized (this) { 
			boolean isNull = counterRepository.checkTable();
			 if(isNull){ 
			  counterRepository.initializeTable();
			  count = 0;
			  count++;
			  counterRepository.updateCount(count); 
			  }else { 
			  count = counterRepository.getExistingCount() + 1;
			  counterRepository.updateCount(count); 
			  }
		}
		 return count;
	}

}
