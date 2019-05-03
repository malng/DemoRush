package design.maliang.jdcp.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DruidUtil {
    // 1. 构造方法私有
    private DruidUtil() {
    }

    // 2. 定义一个变量, 表示: 数据库连接池对象
    private static DataSource ds;

    // 3. 在静态代码块中, 对数据库连接池对象赋值
    static {
        Properties pp = new Properties();
        // 读取配置文件
        try {
            pp.load(new FileInputStream("D:\\workspace\\DemoRush\\jdbc_demo\\src\\main\\resources\\config.properties"));
            ds = DruidDataSourceFactory.createDataSource(pp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 4. 对外提供一个公共的获取数据库连接池对象的方法.
    public static DataSource getDataSource() {
        return ds;
    }

    // 5. 对外提供一个获取 连接对象的方法
    public static Connection getConnection() {
        try {
            return ds.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    // 6. 释放资源
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
