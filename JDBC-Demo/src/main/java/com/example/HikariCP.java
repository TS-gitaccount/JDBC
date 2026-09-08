package com.example;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class HikariCP {
	
public static HikariDataSource dataSource;
	
	static {
		HikariConfig config = new HikariConfig();
		config.setJdbcUrl("jdbc:postgresql://localhost:3000/jdbcdb");
		config.setUsername("postgres");
		config.setPassword("2004");
		
		config.setMaximumPoolSize(10);
		config.setMinimumIdle(3);
		
		config.setIdleTimeout(5000);
		
		dataSource = new HikariDataSource(config);
		
	}
	
	public static HikariDataSource getDataSource() {
		return dataSource;
	}

}
