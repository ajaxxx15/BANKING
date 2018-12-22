package com.abc.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import oracle.jdbc.driver.OracleDriver;

public class Model {
	private int accno;
	private String custid;
	private String pwd;
	private int balance;
	private String email;
	private String name;

	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet res = null;

	public Model() {
		super();
		try {
			DriverManager.registerDriver(new OracleDriver());
			con = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/XE", "ajay", "ajay123");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getAccno() {
		return accno;
	}

	public void setAccno(int accno) {
		this.accno = accno;
	}

	public String getCustid() {
		return custid;
	}

	public void setCustid(String custid) {
		this.custid = custid;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean login() {
		try {
			pstmt = con.prepareStatement("SELECT * FROM BANK WHERE CUSTID=? AND PWD=?");
			pstmt.setString(1, custid);
			pstmt.setString(2, pwd);

			res = pstmt.executeQuery();

			if (res.next() == true) {
				accno = res.getInt("ACCNO");
				return true;

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return false;

	}

}
