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

@WebServlet("/student/save")
public class StudentSaveServlet extends HttpServlet {

	private IStudentDAO dao;

	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		dao = new StudentDAOImply();
	}

	// 编辑和添加都用一个servlet
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String id = req.getParameter("id");
		String name = req.getParameter("name");
		String age = req.getParameter("age");
		Student stu = new Student(null, name, Integer.valueOf(age));

		//判断是保存还是更新
		if (StringUtils.hasLength(id)) {
			try {
				dao.update(Long.valueOf(id), stu);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			dao.save(stu);
		}
		resp.sendRedirect("/student/list");
	}
}
