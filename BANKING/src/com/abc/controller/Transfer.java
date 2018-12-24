package com.abc.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.abc.model.Model;

/**
 * Servlet implementation class Transfer
 */
public class Transfer extends HttpServlet {
	public void service(HttpServletRequest x, HttpServletResponse y) {
		HttpSession session = x.getSession();
		int toAccno = Integer.parseInt(x.getParameter("toAccno"));
		int toAmnt = Integer.parseInt(x.getParameter("toAmnt"));

		Model m = new Model();
		int faccno = (int) session.getAttribute("ACCNO");
		m.setAccno(faccno);
		if (m.transfer(toAmnt, toAccno) == true) {
			try {
				y.sendRedirect("/BANKING/transfersuccess.jsp");
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
