package design.maliang.jdbc.util;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * 版本二：结合配置文件使用
 */
public class JDBCUtilDemo2 {

    //1.构造私有化
    private JDBCUtilDemo2() {
    }

    //2.定义变量记录读取到的数据
    private static String driverClass;
    private static String url;
    private static String username;
    private static String password;

    //3.读取配置文件中的信息，并且设置给变量
    private static void readConfig() {
        Properties pp = new Properties();
        try {
            pp.load(new FileReader("D:\\workspace\\DemoRush\\jdbc_demo\\src\\main\\resources\\config.properties"));
            driverClass = pp.getProperty("driverClass");
            url = pp.getProperty("url");
            username = pp.getProperty("username");
            password = pp.getProperty("password");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //4.注册驱动
    static {
        try {
            readConfig();
            Class.forName(driverClass);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //5.获取连接对象
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //6.释放资源
    public static void release(Connection conn, Statement stat, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
                rs = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (stat != null) {
                    stat.close();
                    stat = null;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                try {
                    if (conn != null) {
                        conn.close();
                        conn = null;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static void release(Connection conn, Statement stat) {
        try {
            if (stat != null) {
                stat.close();
                stat = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
