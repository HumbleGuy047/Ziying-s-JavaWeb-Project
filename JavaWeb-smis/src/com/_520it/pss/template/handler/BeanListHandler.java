package com._520it.pss.template.handler;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com._520it.pss.domain.Column;
import com._520it.pss.template.IResultSetHandler;

public class BeanListHandler<T> implements IResultSetHandler<List<T>> {

	private Class<T> classType;

	public BeanListHandler(Class<T> classType) {
		this.classType = classType;
	}

	@Override
	public List<T> handle(ResultSet rs) throws Exception {
		List<T> list = new ArrayList<>();
		while (rs.next()) {
			T obj = classType.newInstance();

			BeanInfo beanInfo = Introspector.getBeanInfo(classType, Object.class);
			PropertyDescriptor[] descriptor = beanInfo.getPropertyDescriptors();
			for (PropertyDescriptor propertyDescriptor : descriptor) {
				String name = propertyDescriptor.getName();
				String fieldName = name;
				// ===============================================
				Field field = classType.getDeclaredField(name); // get each field in the object by name

				if (field.isAnnotationPresent(Column.class)) {
					Column co = field.getAnnotation(Column.class);
					fieldName = co.value();
				}
				// ===============================================
				Object value = rs.getObject(fieldName);
				propertyDescriptor.getWriteMethod().invoke(obj, value);
			}
			list.add(obj);
		}

		return list;
	}

}
