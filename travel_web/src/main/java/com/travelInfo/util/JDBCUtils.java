package com.travelInfo.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCUtils {
    /**
     * druid 工具类
     */

    // 定义成员变量
    private static DataSource ds;

    static {
        try {
            // 加载配置文件
            Properties pro = new Properties();
            InputStream is = JDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties");
            pro.load(is);
            // 获取DataSource
            ds = DruidDataSourceFactory.createDataSource(pro);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    /**
     * 获取连接对象
     */
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    /**
     * 释放资源
     */
    public static void close(Statement stm, Connection conn) {
       /* if (stm != null) {
            try {
                stm.close();
            } catch (SQLException E) {
                E.printStackTrace();
            }
        }

        if (conn != null) {
            try {
                conn.close();  // 归还连接
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }*/
        close(null, stm, conn);
    }


    public static void close(ResultSet rs, Statement stm, Connection conn) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException E) {
                E.printStackTrace();
            }
        }

        if (stm != null) {
            try {
                stm.close();
            } catch (SQLException E) {
                E.printStackTrace();
            }
        }

        if (conn != null) {
            try {
                conn.close();  // 归还连接
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取连接池的方法
     */
    public static DataSource getDataSource(){
        return ds;
    }
}
