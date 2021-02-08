package com.logistica.numbercounter.repository;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.logistica.numbercounter.NumberCounterApplication;

@Repository
public class NumberCounterRepository {
	
	private static final Logger logger = Logger.getLogger(NumberCounterApplication.class);
	
	 @Autowired
	 JdbcTemplate jdbcTemplate;	 
	
	/* Persists count value in database*/
	public void updateCount(int count) throws Exception
	{
		try {
		jdbcTemplate.update("UPDATE NUMBER SET COUNT = ?", count);
		}catch(Exception e) {
			logger.error("Exception occured at database | in () in updateCount" + e.getMessage());
			throw new IOException("Failed to connect");		
			}
	}	
	
	/* Checks Table state. */
	public boolean checkTable() throws Exception 
	{
		String sql = "SELECT COUNT FROM NUMBER";
		try {
		List<String> currentCount = jdbcTemplate.queryForList(sql, String.class);
		if(currentCount.isEmpty())
			return true;
		   else
			return false;
		}catch(Exception e){
			logger.error("Exception occured at database | in checkTable()" + e.getMessage());
			throw new IOException("Failed to connect");		
			}
	}
	
	/* Initializes the table with count as 0 */
	public void initializeTable() throws Exception 
	{
		int zero = 0;
		try {
		jdbcTemplate.update("INSERT INTO NUMBER (COUNT) VALUES(?)",zero);
		}catch(Exception e) {
			logger.error("Exception occured at database | in () in initializeTable" + e.getMessage());
			throw new IOException("Failed to connect");		
			}
	}
	
	/* Returns the current value of count */
	public int getExistingCount() throws Exception{
		String sql = "SELECT COUNT FROM NUMBER";
		try {
		List<String> currentCount = jdbcTemplate.queryForList(sql, String.class);
		int existingCount = Integer.parseInt(currentCount.get(0));
		return existingCount;
		}catch(Exception e) {
			logger.error("Exception occured at database | in () in getExistingCount" + e.getMessage());
			throw new IOException("Failed to connect");		
			}
	}
}
