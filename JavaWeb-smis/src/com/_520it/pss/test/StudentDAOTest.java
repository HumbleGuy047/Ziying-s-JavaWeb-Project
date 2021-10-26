package com._520it.pss.test;

import java.util.List;

import org.junit.Test;

import com._520it.pss.dao.IStudentDAO;
import com._520it.pss.dao.impl.StudentDAOImply;
import com._520it.pss.domain.Student;

public class StudentDAOTest {
	
	private IStudentDAO dao = new StudentDAOImply(); // Dependent on a DAO object
	
	@Test
	public void testSave() {
		Student stu = new Student();
		stu.setName("Doja Cat");
		stu.setAge(28);
		dao.save(stu);
	}

	@Test
	public void testDelete() {
		dao.delete(17L, new Student());
	}

	@Test
	public void testUpdate() throws Exception {
		Student stu = new Student();
		stu.setName("Jascon");
		stu.setAge(31);
		dao.update(18L, stu);
	}

	@Test
	public void testGet() {
		Student stu = dao.get(15L);
		System.out.println(stu);
	}

	@Test
	public void testList() {
		List<Student> stuList = dao.list();
		for (Student student : stuList) {
			System.out.println(student);
		}
	}


}
