package com.jdbc.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class BaseDAO {
	// 在这个类中完成对数据库访问层方法的简化封装
	// 标准的JDBC写法
	private static String DRIVERNAME;
	private static String URL;
	private static String USERNAME;
	private static String PASSWORD;
	
	//访问修饰符写受保护子类可以继承
	protected Connection conn = null;
	protected PreparedStatement ps = null;
	protected ResultSet rs = null;
	//创建静态代码块，加载类的过程中同时加载properties文件
	static{
		//创建properties对象
		Properties p = new Properties();
		try {
			p.load(BaseDAO.class.getResourceAsStream("/jdbc.properties"));
			//根据KEY来获取VALUE值
			DRIVERNAME = p.getProperty("driver");
			URL = p.getProperty("url");
			USERNAME = p.getProperty("username");
			PASSWORD = p.getProperty("password");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//获取Connection对象的方法
	public Connection getConnection(){
		try {
			Class.forName(DRIVERNAME);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	//关闭所有资源的方法
	public void closeAll(){
		try {
			//为防止空指针异常，需要条件语句判断
			if(rs != null){
				rs.close();
				rs =null;//手动释放对象
			}
			
			if(ps != null){
				ps.close();
				ps = null;
			}
			
			if (conn != null) {
				conn.close();
				conn = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//增删改通用方法
	//obj内必须依次传入
	public int doUpdate(String sql, Object [] obj){
		conn = getConnection();
		
		int rowNum = 0;
		try {
			ps = conn.prepareStatement(sql);
			if(obj != null){
				for (int i = 0; i < obj.length; i++) {
					ps.setObject(i + 1, obj[i]);
				}
				ps.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowNum;
	}
	
	//查找通用方法
	//暂时返回ResultSet对象，不推荐返回ResultSet对象
	//因为finally时候关闭所有资源，可能会影响
	//obj内必须依次传入
	public ResultSet doQuery(String sql, Object [] obj){
		conn = getConnection();
		
		try {
			ps = conn.prepareStatement(sql);
			if(obj != null){
				for (int i = 0; i < obj.length; i++) {
					ps.setObject(i+1, obj[i]);
				}
				return ps.executeQuery();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
