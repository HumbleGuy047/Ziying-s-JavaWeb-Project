package com._520it.pss.template;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com._520it.pss.util.JdbcUtil;

// JDBC 的操作模板
public class JdbcTemplate {

	/**
	 * DML 操作模板
	 * 
	 * @param sql    DML 操作具体的SQL, 包括: INSERT INTO, UPDATE, DELETE FROM
	 * @param params SQL 中的占位符参数
	 * @return 受影响的行数
	 */
	public static int update(String sql, Object... params) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = JdbcUtil.getConn();
			ps = conn.prepareStatement(sql);
			for (int i = 0; i < params.length; i++) {
				//System.out.println(params[i]);
				ps.setObject(i + 1, params[i]);
			}
			return ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, ps, null);
		}
		return 0;
	}

	// ！！！！！！！静态方法 第一个<T>代表泛型声明，第二个T方法返回的东西，T有第三个<T>在参数中决定
	public static <T> T query(String sql, IResultSetHandler<T> rsh, Object... params) {

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtil.getConn();
			ps = conn.prepareStatement(sql);
			for (int i = 0; i < params.length; i++) {

				ps.setObject(i + 1, params[i]);
			}
			rs = ps.executeQuery();
			// return RSHandler
			return rsh.handle(rs);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// ---------------------------------5. Release
			// Resources----------------------------------
			JdbcUtil.close(conn, ps, null);
		}
		return null;
	}


}
