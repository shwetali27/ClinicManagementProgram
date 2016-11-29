package com.bridgelabz.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface DatabaseDao {
	public <T> void insertData(List<ArrayList<T>> pAllModelList) throws ClassNotFoundException, SQLException;
}
