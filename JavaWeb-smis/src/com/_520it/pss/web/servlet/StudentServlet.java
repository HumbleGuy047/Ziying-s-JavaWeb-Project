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
import com._520it.pss.util.StringUtils;

@WebServlet("/student")
public class StudentServlet extends HttpServlet {
	private IStudentDAO dao;

	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		dao = new StudentDAOImply();
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 获取请求的类型（i.e.List/SaveOrUpdate/...）
		req.setCharacterEncoding("UTF-8");
		String cmd = req.getParameter("cmd");
		if ("edit".equals(cmd)) {
			edit(req, resp);
		} else if ("delete".equals(cmd)) {
			delete(req, resp);
		}
		else if ("saveOrUpdate".equals(cmd)) {
			saveOrUpdate(req, resp);
		} else {
			list(req, resp);
		}
	}

	protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("查询");
		List<Student> stuList = dao.list();
		req.setAttribute("list", stuList);
		// 请求转发
		req.getRequestDispatcher("/WEB-INF/views/student/list.jsp").forward(req, resp);
	}

	protected void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("编辑");
		req.setCharacterEncoding("UTF-8");
		String id = req.getParameter("id");
		// 如果有提供id就一定是更改而不是新增
		if (StringUtils.hasLength(id)) {
			Student stu = dao.get(Long.valueOf(id));
			req.setAttribute("stu", stu);
		}
		req.getRequestDispatcher("/WEB-INF/views/student/edit.jsp").forward(req, resp);
	}

	protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("删除");
		String id = req.getParameter("id");
		if (StringUtils.hasLength(id)) {
			dao.delete(Long.valueOf(id), new Student());
		}
		resp.sendRedirect("/student?cmd=list");
	}

	protected void saveOrUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("添加或保存");
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
		resp.sendRedirect("/student?cmd=list");
	}
}
