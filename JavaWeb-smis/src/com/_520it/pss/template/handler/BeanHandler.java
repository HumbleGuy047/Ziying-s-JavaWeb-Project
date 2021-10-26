package com._520it.pss.template.handler;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.sql.ResultSet;

import com._520it.pss.domain.Column;
import com._520it.pss.template.IResultSetHandler;

public class BeanHandler<T> implements IResultSetHandler<T> {
	private Class<T> classType; // JavaBean ���������

	public BeanHandler(Class<T> classType) {
		this.classType = classType;
	}

	@Override
	public T handle(ResultSet rs) throws Exception {
		if (rs.next()) {
			T obj = classType.newInstance();
			BeanInfo beanInfo = Introspector.getBeanInfo(classType, Object.class);
			PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
			for (PropertyDescriptor pd : pds) {
				String name = pd.getName();

				// ע����
				Field field = classType.getDeclaredField(name);
				if (field.isAnnotationPresent(Column.class)) {
					name = field.getAnnotation(Column.class).value();
				}

				Object value = rs.getObject(name);

				pd.getWriteMethod().invoke(obj, value);

			}
			return obj;
		}
		return null;
	}
}
