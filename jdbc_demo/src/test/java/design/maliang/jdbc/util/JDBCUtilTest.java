package design.maliang.jdbc.util;

import design.maliang.jdcp.util.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * 1.导入驱动
 * 2.注册驱动
 * 3.获取连接对象
 * 4.获取可以操作SQL语句的对象
 * 5.执行SQL语句，获取结果集
 * 6.操作结果集
 * 7.释放资源
 */
public class JDBCUtilTest {

    private Connection conn;
    private Statement stat;
    private ResultSet rs;

    @Test
    public void release() {
        try {
            //3
            conn = JDBCUtils.getConnection();
            //4
            stat = conn.createStatement();
            //5
            String sql ="select * from t_users;";
            rs = stat.executeQuery(sql);
            //6
            while (rs.next()) {
                int uid = rs.getInt("uid");
                String username = rs.getString("username");
                String password = rs.getString("password");
                System.out.println(uid + "---" + username + "---" + password);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCUtilDemo.release(conn, stat, rs);
        }
    }
}