package com.jdbc.Client;

import java.util.Scanner;

import com.jdbc.Entity.Emp;
import com.jdbc.dao.EmpDAO;

public class EmpClient {
	public static void main(String[] args) {
		//1客户端
		Scanner in = new Scanner(System.in);
		System.out.println("欢迎使用员工管理系统");
		System.out.println("请输入员工编号");
		int empno = in.nextInt();
		System.out.println("请输入员工姓名");
		String ename = in.next();
		///////
		
		//2业务逻辑层
		EmpDAO empdao = new EmpDAO();
		Emp emp = empdao.login(empno, ename);
		if(emp != null){
			System.out.println("登陆成功");
		}else{
			System.out.println("登陆失败");
		}
	}
}
