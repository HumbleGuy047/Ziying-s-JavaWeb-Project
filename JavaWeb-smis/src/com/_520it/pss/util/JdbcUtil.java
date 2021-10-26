package com._520it.pss.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSourceFactory;

public class JdbcUtil {
	private static DataSource dataSource;

	static { // static code block: ȷ��ֻ��һ��ʼ���ز���ֻһ��
		try {
			Properties p = new Properties();
			ClassLoader loader = Thread.currentThread().getContextClassLoader();
			// ��classpath�ĸ�·��ȥѰ��db.properties�ļ�
			InputStream inStream = loader.getResourceAsStream("db.properties"); // �Ѹ�
			p.load(inStream);
			// =================================
			dataSource = DruidDataSourceFactory.createDataSource(p); // �Ѹ�
			// =================================
		} catch (Exception e) {
			throw new RuntimeException("Couldn't load file dbcp.properties under classpath.", e);
		}
	}

	// return a Connection
	public static Connection getConn() {

		try {
			return dataSource.getConnection();
		} catch (Exception e) {

			e.printStackTrace();
		}
		throw new RuntimeException("Database Connection Exception");
	}

	// release resources
	public static void close(Connection conn, Statement st, ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (Exception e2) {
			e2.printStackTrace();
		} finally {
			try {
				if (st != null) {
					st.close();
				}
			} catch (Exception e3) {
				e3.printStackTrace();
			} finally {
				try {
					if (conn != null) {
						conn.close();
					}
				} catch (Exception e4) {
					e4.printStackTrace();
				}
			}
		}
	}

	private JdbcUtil() {
	} // Encapsulate constructor, so no-one can create object
}
