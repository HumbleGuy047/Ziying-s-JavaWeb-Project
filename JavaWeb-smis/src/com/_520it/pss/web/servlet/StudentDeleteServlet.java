package com._520it.pss.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com._520it.pss.dao.IStudentDAO;
import com._520it.pss.dao.impl.StudentDAOImply;
import com._520it.pss.domain.Student;
import com._520it.pss.util.StringUtils;

@WebServlet("/student/delete")
public class StudentDeleteServlet extends HttpServlet {
	private IStudentDAO dao;

	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		dao = new StudentDAOImply();
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		if (StringUtils.hasLength(id)) {
			dao.delete(Long.valueOf(id), new Student());
		}
		resp.sendRedirect("/student/list");
	}
}
