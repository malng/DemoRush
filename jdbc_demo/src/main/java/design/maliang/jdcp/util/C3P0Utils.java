package design.maliang.jdcp.util;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 操作C3P0数据库连接池的 工具类
 * @author Liu
 *
 */
public class C3P0Utils {
    //1. 构造方法私有
    private C3P0Utils() {}

    //2. 定义变量, 记录数据库连接池对象
    private static DataSource ds = new ComboPooledDataSource();

    //3. 对外提供获取数据库连接池对象的方法.
    public static DataSource getDataSource() {
        return ds;
    }

    //4. 对外提供获取连接对象的方法.
    public static Connection getConnection() {
        try {
            return ds.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //5. 释放资源
    public static void release(Connection conn, Statement stat, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
                rs = null; // GC会优先回收null对象
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stat != null) {
                    stat.close();
                    stat = null; // GC会优先回收null对象
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (conn != null) {
                        conn.close();
                        conn = null; // GC会优先回收null对象
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void release(Connection conn, Statement stat) {
        try {
            if (stat != null) {
                stat.close();
                stat = null; // GC会优先回收null对象
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                    conn = null; // GC会优先回收null对象
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

