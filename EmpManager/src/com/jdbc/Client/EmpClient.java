package com.jdbc.Client;

import java.util.Scanner;

import com.jdbc.Entity.Emp;
import com.jdbc.dao.EmpDAO;

public class EmpClient {
	public static void main(String[] args) {
		//1�ͻ���
		Scanner in = new Scanner(System.in);
		System.out.println("��ӭʹ��Ա������ϵͳ");
		System.out.println("������Ա�����");
		int empno = in.nextInt();
		System.out.println("������Ա������");
		String ename = in.next();
		///////
		
		//2ҵ���߼���
		EmpDAO empdao = new EmpDAO();
		Emp emp = empdao.login(empno, ename);
		if(emp != null){
			System.out.println("��½�ɹ�");
		}else{
			System.out.println("��½ʧ��");
		}
	}
}
