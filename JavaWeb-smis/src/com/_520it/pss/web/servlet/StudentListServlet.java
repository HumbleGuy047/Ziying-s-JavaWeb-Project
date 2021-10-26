package com._520it.pss.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com._520it.pss.dao.IStudentDAO;
import com._520it.pss.dao.impl.StudentDAOImply;
import com._520it.pss.domain.Student;

@WebServlet("/student/list")
public class StudentListServlet extends HttpServlet {

	private IStudentDAO dao;

	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		dao = new StudentDAOImply();
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Student> stuList = dao.list();
		req.setAttribute("list", stuList);
		// 请求转发
		req.getRequestDispatcher("/WEB-INF/views/student/list.jsp").forward(req, resp);
	}
}
