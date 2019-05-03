package design.maliang.jdbc.util;




import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * 版本一：基本抽取
 */
public class JDBCUtilDemo {
    /*
        工具类的两个特点：
            1.构造方法私有化。
            2.成员都是静态的。
     */

    //1.构造私有化
    private JDBCUtilDemo() { }

    //2.注册驱动
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //3.获取连接对象
    public static Connection getConnection(){
        try {
            return DriverManager.getConnection("jdbc:mysql:///web03", "root", "123456");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //4.释放资源
    public static void release(Connection conn, Statement stat, ResultSet rs){
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

    public static void release(Connection conn,Statement stat){
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
