package com.bridgelabz.factory;

import com.bridgelabz.dao.DatabaseDao;
import com.bridgelabz.dao.MySqlDaoImpl;

public class DaoFactory {
	//implementing the factory pattern for database operations 
	public DatabaseDao getDatabaseImpl(String databaseName){
		if(databaseName == null){
			return null;
		}
		if(databaseName.equalsIgnoreCase("MySQL")){
			return new MySqlDaoImpl();
		}
		if(databaseName.equalsIgnoreCase("Oracle")){
			//return object for oracle dao implementation
		}
		if(databaseName.equalsIgnoreCase("PostgreSql")){
			//return object for PostgreSql dao implementation
		}
		
		return null;
	}
}
