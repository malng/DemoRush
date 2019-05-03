package design.maliang.jdcp.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class JDBCUtils {
    // 1. 构造私有
    private JDBCUtils() {
    }

    // 2. 定义一些变量, 用来记录属性值.
    private static String driverClass;
    private static String url;
    private static String user;
    private static String password;

    // 3. 定义方法, 读取配置文件, 并给变量赋值.
    private static void readConfig() {
        Properties pp = new Properties();
        try {
            pp.load(new FileInputStream("D:\\workspace\\DemoRush\\jdbc_demo\\src\\main\\resources\\config.properties"));
            driverClass = pp.getProperty("driverClass");
            url = pp.getProperty("url");
            user = pp.getProperty("user");
            password = pp.getProperty("password");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 4. 在静态代码块中. 读取配置文件, 注册驱动.
    static {
        readConfig();
        // 注册驱动
        try {
            Class.forName(driverClass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // 5. 获取连接对象的方法.
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(url, user, password);
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
