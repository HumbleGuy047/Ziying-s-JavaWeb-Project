package com._520it.pss.dao;

import java.util.List;

import com._520it.pss.domain.Student;

/**
 * Includes the Student object's CRUD operations
 * @author Administrator
 *
 */
public interface IStudentDAO {
	
	/**
	 * Transfers the information stored in a Student object into the database.
	 * @param stu	The Student object that contains the primary key, name, age
	 */
	void save(Student stu);
	
	/**
	 * Deletes a single row of data from the database. 
	 * @param id		The given id is unique and determines which row to delete
	 */
	void delete(Long id, Student stu);
	
	/**
	 * Updates the data in one row.
	 * 
	 * @param id  The given id is unique and determines which row to alter the
	 *            information
	 * @param stu The Student object that contains the altered information: name,
	 *            age
	 * @throws Exception
	 */
	void update(Long id, Student stu) throws Exception;
	
	/**
	 * Query one row of data
	 * @param id		The given id is unique and determines which row to query
	 * @return 	Student		The returned Student object has the information for the query
	 */
	Student get(Long id);
	
	/**
	 * Query the whole table of data
	 * @return List<Student>		The returned collection of Student objects contains all the data of the table
	 */
	List<Student> list();
}
