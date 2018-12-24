package com.abc.controller;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.abc.model.Model;

/**
 * Servlet implementation class Balance
 */
public class Balance extends HttpServlet {
	public void service(HttpServletRequest x, HttpServletResponse y) {
		Model m = new Model();
		HttpSession session = x.getSession();
		int accno = (int) session.getAttribute("ACCNO");
		m.setAccno(accno);
		if (m.checkBalance() == true) {
			int bal = m.getBalance();
			session.setAttribute("BALANCE", bal);
			try {
				y.sendRedirect("/BANKING/balancesuccess.jsp");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				y.sendRedirect("BANKING/loginfail.jsp");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
