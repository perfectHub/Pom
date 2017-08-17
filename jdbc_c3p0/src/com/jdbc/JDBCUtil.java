package com.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JDBCUtil {

	private static ComboPooledDataSource pool = null;
	static{
		//读取配置文件中名字为mysql的配置，不写的话读取默认配置
		//pool = new ComboPooledDataSource("mysql");
		pool = new ComboPooledDataSource();
	}
	
	public static Connection getConnection() throws SQLException{
		return pool.getConnection();
	}
	
	public static void main(String[] args) throws SQLException {
		//测试连接数据库是否成功 false为连接成功。
		System.out.println(getConnection().isClosed());
	}
	
	//释放资源
	public static void release(Connection conn, Statement st, ResultSet rs) {
		if(conn!=null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				if(st!=null) {
					try {
						st.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}finally{
						if(rs!=null) {
							try {
								rs.close();
							} catch (SQLException e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
		}
	}
}
