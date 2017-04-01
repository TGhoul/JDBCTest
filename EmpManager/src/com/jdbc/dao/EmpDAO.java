package com.jdbc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.jdbc.Entity.Emp;

public class EmpDAO extends BaseDAO{
	
	public Emp login(int empno, String ename){
		Emp emp = null;
		try {
			conn = getConnection();
			
			ResultSet rs = 
				doQuery("select * from emp where empno = ? and ename = ?", new Object []{empno,ename});
			if(rs.next()){
				//System.out.println("11111");
				emp = new Emp(rs.getInt(1),rs.getString(2));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return emp;
	}
}
