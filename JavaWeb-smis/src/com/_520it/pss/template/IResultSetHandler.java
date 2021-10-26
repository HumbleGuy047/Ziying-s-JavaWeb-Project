package com._520it.pss.template;

import java.sql.ResultSet;

public interface IResultSetHandler<T> {
	T handle(ResultSet rs) throws Exception;
}
