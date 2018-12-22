package com.abc.controller;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.abc.model.Model;

public class Home extends HttpServlet {
	public void service(HttpServletRequest x, HttpServletResponse y) {
		String custid = x.getParameter("custid");
		String pwd = x.getParameter("pwd");
		
		Model m = new Model();
		m.setCustid(custid);
		m.setPwd(pwd);
		
		if(m.login()==true)
		{
			int accno=m.getAccno();
			HttpSession session = x.getSession(true);
			session.setAttribute("ACCNO", accno);
			try {
				y.sendRedirect("/BANKING/home.jsp");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else {
			try {
				y.sendRedirect("/BANKING/loginfail.jsp");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
