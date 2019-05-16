package design.maliang.jdbc;

import java.sql.*;

/**
 * 1.导入驱动
 * 2.注册驱动
 * 3.获取连接对象
 * 4.获取可以操作SQL语句的对象
 * 5.执行SQL语句，获取结果集
 * 6.操作结果集
 * 7.释放资源
 */
public class JDBCBase7Step {
    public static void main(String[] args) {
        Connection conn = null;
        Statement stat = null;
        ResultSet rs = null;
        try {
            //2.注册驱动
            //DriverManager.registerDriver(new Driver());
            Class.forName("com.mysql.cj.jdbc.Driver");

            //3.获取连接对象
            //Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/web03", "root", "123456");
            //Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/web03", "root", "123456");
//            conn = DriverManager.getConnection("jdbc:mysql:///db_demorush", "root", "123456");
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/db_demorush?serverTimezone=GMT%2B8","root","123456");

            //4.获取可以操作SQL语句的对象
            stat = conn.createStatement();

            //5.执行SQL语句，获取结果集
            String sql = "select * from users;";
            rs = stat.executeQuery(sql);

            //6.操作结果集
            while (rs.next()) {
                //可以用字符串查找
                int uid = rs.getInt("id");
                //也可以用index查找
                String username = rs.getString(2);
                String password = rs.getString("password");
                System.out.println(uid + "---" + username + "---" + password);
            }

            //5*.执行SQL语句，获取结果集
            //String sql = "insert into t_users VALUES (null,\"admin05\",\"pwd555\")";
            //int num = stat.executeUpdate(sql);

            //6*.操作结果集
            //System.out.println(num > 0 ? "添加成功" : "添加失败");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            //7.释放资源
            try {
                if (rs != null) {
                    rs.close();
                    rs = null;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                try {
                    if (stat != null) {
                        stat.close();
                        stat = null;
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }finally {
                    try {
                        if (conn != null) {
                            conn.close();
                            conn = null;
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
