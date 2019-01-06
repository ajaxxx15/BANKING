package com.abc.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.abc.model.Model;

/**
 * Servlet implementation class ChnagePassword
 */
public class ChangePassword extends HttpServlet {
	public void service(HttpServletRequest x,HttpServletResponse y) {
		HttpSession session = x.getSession();
		String opwd=x.getParameter("opwd");
		String npwd=x.getParameter("npwd1");
		
		int accno=(int) session.getAttribute("ACCNO");		
		
		Model m = new Model();
		m.setAccno(accno);
		m.setPwd(opwd);
		
		if(m.changePassword(npwd)==true) {
			try {
				y.sendRedirect("/BANKING/changepwdsuccess.jsp");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else {
			try {
				y.sendRedirect("/BANKING/changepwdfail.jsp");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
}
