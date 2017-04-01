package com.jdbc.Entity;

import java.util.Date;

public class Emp {
	private int empno;
	private String eName;
	private String job;
	private int MGR;
	private Date hireDate;
	private double sal;
	private double comm;
	private int deptno;
	
	public Emp() {
		super();
	}

	public Emp(int empno, String eName) {
		super();
		this.empno = empno;
		this.eName = eName;
	}

	public Emp(int empno, String eName, String job, int mGR, Date hireDate,
			double sal, double comm, int deptno) {
		super();
		this.empno = empno;
		this.eName = eName;
		this.job = job;
		MGR = mGR;
		this.hireDate = hireDate;
		this.sal = sal;
		this.comm = comm;
		this.deptno = deptno;
	}

	public int getEmpno() {
		return empno;
	}

	public void setEmpno(int empno) {
		this.empno = empno;
	}

	public String geteName() {
		return eName;
	}

	public void seteName(String eName) {
		this.eName = eName;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public int getMGR() {
		return MGR;
	}

	public void setMGR(int mGR) {
		MGR = mGR;
	}

	public Date getHireDate() {
		return hireDate;
	}

	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}

	public double getSal() {
		return sal;
	}

	public void setSal(double sal) {
		this.sal = sal;
	}

	public double getComm() {
		return comm;
	}

	public void setComm(double comm) {
		this.comm = comm;
	}

	public int getDeptno() {
		return deptno;
	}

	public void setDeptno(int deptno) {
		this.deptno = deptno;
	}
	
	
}
