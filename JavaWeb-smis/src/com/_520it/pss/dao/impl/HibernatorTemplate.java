package com._520it.pss.dao.impl;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com._520it.pss.domain.Column;
import com._520it.pss.domain.ID;
import com._520it.pss.domain.Table;
import com._520it.pss.template.JdbcTemplate;

public class HibernatorTemplate {
	private HibernatorTemplate() {
	}

	public static void save(Object obj) throws Exception {
		StringBuilder sql = new StringBuilder(80);
		StringBuilder columnList = new StringBuilder(80);
		StringBuilder placeHolderList = new StringBuilder(80);
		String tableName = obj.getClass().getSimpleName();
		List<Object> params = new ArrayList<Object>();

		Class classType = obj.getClass();
		BeanInfo beaninfo = Introspector.getBeanInfo(classType, Object.class);
		PropertyDescriptor[] pds = beaninfo.getPropertyDescriptors();

		// determine which table
		if (obj.getClass().isAnnotationPresent(Table.class)) {
			tableName = obj.getClass().getAnnotation(Table.class).value();
		}

		// try to get the values of age, name from reflection
		for (PropertyDescriptor pd : pds) {
			// get the field names
			String name = pd.getName();
			String fieldName = name;
			Field field = classType.getDeclaredField(name);
			if (!field.isAnnotationPresent(ID.class)) {
				if (field.isAnnotationPresent(Column.class)) {
					fieldName = field.getAnnotation(Column.class).value();
				}
				//System.out.println(fieldName);
				// pd.getPropertyType().getField(pd.getName()).isAnnotationPresent(Column.class)
				columnList.append(fieldName + ",");
				params.add(pd.getReadMethod().invoke(obj));
				placeHolderList.append("?,");
			}
		}
		columnList.delete(columnList.length() - 1, columnList.length());
		placeHolderList.delete(placeHolderList.length() - 1, placeHolderList.length());

		// INSERT INTO jdbcdemo.t_student_02(name, age) VALUES (?, ?)
		sql.append("INSERT INTO ");

		sql.append(tableName);

		sql.append(" (");
		sql.append(columnList);
		sql.append(")");

		// set values
		sql.append(" VALUES (");
		sql.append(placeHolderList);
		sql.append(")");

		// end result of building the SQL
		/*System.out.println(sql.toString());
		System.out.println(params);*/

		System.out.println("Have run " + JdbcTemplate.update(sql.toString(), params.toArray()) + " line/s");
	}

	public static void delete(Long id, Object obj) {
		//DELETE FROM jdbcdemo.t_student_02 WHERE id = ?
		StringBuilder sql = new StringBuilder(80);
		
		Class classType = obj.getClass();
		String tableName = classType.getSimpleName();
		
		if (obj.getClass().isAnnotationPresent(Table.class)) {
			tableName = obj.getClass().getAnnotation(Table.class).value();
		}

		sql.append("DELETE FROM ");
		sql.append(tableName);
		sql.append(" WHERE id = ?");

		//System.out.println(sql.toString());

		System.out.println("Have run " + JdbcTemplate.update(sql.toString(), id) + " row/s");
	}

	public static void update(Long id, Object obj) throws Exception {
		//UPDATE jdbcdemo.t_student_02 SET name = ?, age = ? WHERE id = ?

		StringBuilder sql = new StringBuilder(80);
		StringBuilder columnList = new StringBuilder(80);
		List<Object> params = new ArrayList<>();

		Class classType = obj.getClass();
		String tableName = classType.getSimpleName();
		BeanInfo beanInfo = Introspector.getBeanInfo(classType, Object.class);
		PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();

		if (obj.getClass().isAnnotationPresent(Table.class)) {
			tableName = obj.getClass().getAnnotation(Table.class).value();
		}

		for (PropertyDescriptor pd : pds) {
			String name = pd.getName();
			String fieldName = name;

			Field field = classType.getDeclaredField(fieldName);
			if (!field.isAnnotationPresent(ID.class)) {
				if (field.isAnnotationPresent(Column.class)) {
					fieldName = field.getAnnotation(Column.class).value();
				}
				columnList.append(fieldName + "=?,");
				params.add(pd.getReadMethod().invoke(obj));
			}
		}
		columnList.deleteCharAt(columnList.length() - 1);

		sql.append("UPDATE ");
		// tableName
		sql.append(tableName);
		sql.append(" SET ");
		// columnList
		sql.append(columnList);

		sql.append(" WHERE id=");
		// id column
		sql.append(id);

		/*System.out.println(sql.toString());
		System.out.println(params);*/

		System.out.println("Have run " + JdbcTemplate.update(sql.toString(), params.toArray()) + " row/s");
	}
}
