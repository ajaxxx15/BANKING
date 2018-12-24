package com.abc.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.abc.model.Model;

/**
 * Servlet implementation class Statement
 */
public class GetStatement extends HttpServlet {
	public void service(HttpServletRequest x, HttpServletResponse y) {
		HttpSession session = x.getSession();
		int accno = (int) session.getAttribute("ACCNO");
		ArrayList al = new ArrayList();

		Model m = new Model();
		m.setAccno(accno);

		al = m.getStatemnet();

		if (al != null) {
			session.setAttribute("STMT", al);
			try {
				y.sendRedirect("/BANKING/getstatementsuccess.jsp");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			try {
				y.sendRedirect("/BANKING/getstatementfail.jsp");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
}
