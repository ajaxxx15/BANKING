package com.abc.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import oracle.jdbc.driver.OracleDriver;

/**
 * DB activity for Login verification
 *
 */
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
		} finally {
			try {
				pstmt.close();
				res.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		return false;

	}

	public boolean checkBalance() {
		try {
			pstmt = con.prepareStatement("SELECT BALANCE FROM BANK WHERE ACCNO=?");
			pstmt.setInt(1, accno);
			res = pstmt.executeQuery();
			if (res.next() == true) {
				balance = res.getInt("BALANCE");
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				res.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		return false;
	}

	public boolean transfer(int toAmnt, int toAccno) {
		try {
			pstmt = con.prepareStatement("UPDATE BANK SET BALANCE=BALANCE-? WHERE ACCNO=?");
			pstmt.setInt(1, toAmnt);
			pstmt.setInt(2, accno);
			int rowCount = pstmt.executeUpdate();

			pstmt = con.prepareStatement("INSERT INTO BANK_STMT VALUES(?,?,?)");
			pstmt.setInt(1, accno);
			pstmt.setInt(2, toAccno);
			pstmt.setInt(3, toAmnt);
			pstmt.executeUpdate();

			if (rowCount != 0) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		return false;
	}

	public ArrayList getStatemnet() {
		ArrayList al = new ArrayList();

		try {
			pstmt = con.prepareStatement("SELECT * FROM BANK_STMT WHERE FROM_ACCNO=?");
			pstmt.setInt(1, accno);
			res = pstmt.executeQuery();

			while (res.next() == true) {
				
				al.add(res.getInt("TO_ACCNO") + " " + res.getInt("AMNT"));
				

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				res.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		return al;
		
		
	}
	
	
	
	public boolean changePassword(String npwd) {
		int rowcount=0;
		try {
			pstmt=con.prepareStatement("UPDATE BANK SET PWD=? WHERE PWD=? AND ACCNO=?");
			pstmt.setString(1, npwd);
			pstmt.setString(2, pwd);
			pstmt.setInt(3, accno);
			rowcount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(rowcount==1) {
			return true;
		}
		return false;
	}

	
	
}
