package com.jdbc.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class BaseDAO {
	// �����������ɶ����ݿ���ʲ㷽���ļ򻯷�װ
	// ��׼��JDBCд��
	private static String DRIVERNAME;
	private static String URL;
	private static String USERNAME;
	private static String PASSWORD;
	
	//�������η�д�ܱ���������Լ̳�
	protected Connection conn = null;
	protected PreparedStatement ps = null;
	protected ResultSet rs = null;
	//������̬����飬������Ĺ�����ͬʱ����properties�ļ�
	static{
		//����properties����
		Properties p = new Properties();
		try {
			p.load(BaseDAO.class.getResourceAsStream("/jdbc.properties"));
			//����KEY����ȡVALUEֵ
			DRIVERNAME = p.getProperty("driver");
			URL = p.getProperty("url");
			USERNAME = p.getProperty("username");
			PASSWORD = p.getProperty("password");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//��ȡConnection����ķ���
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
	
	//�ر�������Դ�ķ���
	public void closeAll(){
		try {
			//Ϊ��ֹ��ָ���쳣����Ҫ��������ж�
			if(rs != null){
				rs.close();
				rs =null;//�ֶ��ͷŶ���
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
	
	//��ɾ��ͨ�÷���
	//obj�ڱ������δ���
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
	
	//����ͨ�÷���
	//��ʱ����ResultSet���󣬲��Ƽ�����ResultSet����
	//��Ϊfinallyʱ��ر�������Դ�����ܻ�Ӱ��
	//obj�ڱ������δ���
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
