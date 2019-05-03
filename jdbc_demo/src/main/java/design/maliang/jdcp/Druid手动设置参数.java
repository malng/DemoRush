package design.maliang.jdcp;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;
import com.mysql.jdbc.Driver;
import design.maliang.jdbc.util.JDBCUtilDemo2;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Druid手动设置参数 {

    private static DruidPooledConnection conn;
    private static Statement stat;
    private static ResultSet rs;

    public static void main(String[] args) {
        try {
            DruidDataSource ds = new DruidDataSource();
            ds.setDriver(new Driver());
            ds.setUsername("root");
            ds.setPassword("123456");
            ds.setUrl("jdbc:mysql://127.0.0.1:3306/web03");
            conn = ds.getConnection();
            stat = conn.createStatement();
            String sql = "SELECT * FROM t_money";
            rs = stat.executeQuery(sql);
            while (rs.next()) {
                int uid = rs.getInt("uid");
                String name = rs.getString("name");
                long money = rs.getLong("money");
                System.out.println(uid + "---" + name + "----" + money);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtilDemo2.release(conn, stat, rs);
        }
    }
}
