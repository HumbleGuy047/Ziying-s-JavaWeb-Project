package com._520it.pss.dao.impl;

import java.util.List;

import com._520it.pss.dao.IStudentDAO;
import com._520it.pss.domain.Student;
import com._520it.pss.template.JdbcTemplate;
//import com._520it.pss.template.StudentResultSetHandler;
import com._520it.pss.template.handler.BeanHandler;
import com._520it.pss.template.handler.BeanListHandler;

public class StudentDAOImply implements IStudentDAO{

	//============================== DML(Data Manipulation Language) ============================
	@Override
	public void save(Student stu) {
		/*
		 * String sql = "INSERT INTO jdbcdemo.t_student_02(name, age) VALUES (?, ?)";
		 * Object[] params = { stu.getName(), stu.getAge() };
		 * System.out.println("Have run " + JdbcTemplate.update(sql, params) +
		 * " row/s");
		 */
		try {
			HibernatorTemplate.save(stu);
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	@Override
	public void delete(Long id, Student stu) {
		/*String sql = "DELETE FROM jdbcdemo.t_student_02 WHERE id = ?";
		System.out.println("Have run " + JdbcTemplate.update(sql, id) + " row/s");*/
		HibernatorTemplate.delete(id, stu);
	}

	@Override
	public void update(Long id, Student stu) {
		/*String sql = "UPDATE jdbcdemo.t_student_02 SET name = ?, age = ? WHERE id = ?";
		System.out
				.println("Have run " + JdbcTemplate.update(sql, stu.getName(), stu.getAge(), id) + " row/s");*/
		try {
			HibernatorTemplate.update(id, stu);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//============================== DQL(Data Query Language) ==================================
	@Override
	public Student get(Long id) {
		String sql = "SELECT * FROM jdbcdemo.t_student_02 WHERE id = ?";
		return JdbcTemplate.query(sql, new BeanHandler<>(Student.class), id);
	}

	@Override
	public List<Student> list() {
		String sql = "SELECT * FROM jdbcdemo.t_student_02";
		List<Student> stuList = JdbcTemplate.query(sql, new BeanListHandler<>(Student.class));
		return stuList;
	}
}
