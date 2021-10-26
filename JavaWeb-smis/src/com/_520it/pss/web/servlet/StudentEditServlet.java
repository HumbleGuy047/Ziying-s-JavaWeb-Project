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

@WebServlet("/student/edit")
public class StudentEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IStudentDAO dao;

	@Override
	public void init() throws ServletException {
		dao = new StudentDAOImply();
	}

	// 编辑和添加都用一个servlet
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String id = req.getParameter("id");
		// 如果有提供id就一定是更改而不是新增
		if (StringUtils.hasLength(id)) {
			Student stu = dao.get(Long.valueOf(id));
			req.setAttribute("stu", stu);
		}
		req.getRequestDispatcher("/WEB-INF/views/student/edit.jsp").forward(req, resp);
	}
}
