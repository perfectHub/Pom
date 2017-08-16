package com.rest.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.mapping.MappedStatement;

public class SQLHelper {

	/**
	 * 获取分页的总记录数
	 */
	public static int getCount(final String sql,final Connection connection,final MappedStatement mappedStatement){
		
		String countSql = "select count(1) from (" + sql + ") tmp_count";
        Connection conn = connection;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
        	//获取连接
        	if (conn == null){
        		conn = mappedStatement.getConfiguration().getEnvironment().getDataSource().getConnection();
            }
			ps = conn.prepareStatement(countSql);
			rs = ps.executeQuery();
			int count = 0;
			if(rs.next()){
				count = rs.getInt(1);
			}
			return count;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}finally{
            if (rs != null) {
                try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
                rs = null;
            }
            if (ps != null) {
            	try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
            	ps = null;
            }
            if (conn != null) {
            	try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
            	conn = null;
            }

		}
	}
}
